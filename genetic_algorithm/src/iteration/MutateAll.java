package iteration;

import interval_tree.DoubleInterval;
import interval_tree.DoubleIntervalTree;
import interval_tree.IntersectingIntervalException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

import random_sampling.InvalidProbabilityArgumentException;
import random_sampling.RandomSample;

import evaluation.Specimen;

public class MutateAll implements BreedingMethod {

    private final List<Specimen> oldSpecimens; 
    private final int currentPopulationSize;
    private double mutationRate = 0.3;
   
    /**
     * Constructor for a list of specimens
     * @param oldSpecimens
     */
    public MutateAll(List <Specimen> oldSpecimens){
        this.oldSpecimens = oldSpecimens;
        this.currentPopulationSize = oldSpecimens.size();
    }
    
    /**
     * Constructor for a list of specimens and a mutation rate
     * @param oldSpecimens
     */
    public MutateAll(List <Specimen> oldSpecimens, double mutationRate){
        this.oldSpecimens = oldSpecimens;
        this.mutationRate = mutationRate;
        this.currentPopulationSize = oldSpecimens.size();
    }
    
    public int getNumberToBreed() {
        // Under this implementation, we will be creating a new specimen from each old specimen
        return this.currentPopulationSize;
    }

    public int getNumberOfCrosses(int specimensToBreed) {
        // We pick the number of crosses to be chosen uniformly from 0 to getNumberToBreed.
        return RandomSample.getUniform(0, specimensToBreed);
    }

    public int getNumberOfMutations(int specimensToBreed) {
        return specimensToBreed;
    }
    
    /**
     * Gets an interval tree based on the probability intervals of each specimen's evaluation score
     * @param specimenList
     * @return
     * @throws IntersectingIntervalException 
     */
    public DoubleIntervalTree buildProbabilityIntervals(List <Specimen> specimenList) throws IntersectingIntervalException{
        // First convert the specimens into probabilities
        double sum = 0;
        for (Specimen specimen : specimenList){
            sum += specimen.getEvaluatedScore();
        }
        double lowerEnd = 0;
        double probability;
        DoubleInterval newInterval;
        List <DoubleInterval> intervalList = new ArrayList <DoubleInterval> ();
        for (Specimen specimen : specimenList){
            probability = (specimen.getEvaluatedScore() / sum);
            
            // Create a new interval based on this probability and add it to the end of the list
            newInterval = new DoubleInterval(lowerEnd, lowerEnd + probability, specimen);
            intervalList.add(newInterval);
            
            // Update the lowerEnd of the probability
            lowerEnd += probability;
        }
        
        return new DoubleIntervalTree(intervalList);
    }
    
    /**
     * Gets the key for the hash used in crossing specimens
     * @param firstKey
     * @param secondKey
     * @return
     */
    private double getKeyHash(double firstKey, double secondKey){
        return (firstKey+1)*2 + secondKey; 
    }

    /**
     * Private method to get the pairs of specimens that will be crossed
     * @param oldSpecimens
     * @return 
     */
    private List <Specimen> makeSpecimenCrossPairs(List <Specimen> oldSpecimens, DoubleIntervalTree probabilityTree){
        // Get the crosses 
        int crosses = getNumberOfCrosses(this.currentPopulationSize);
        HashMap <Double, Boolean> alreadyUsed = new HashMap <Double, Boolean>();
        List <Specimen> crossList = new ArrayList <Specimen> ();
        Random random = new Random();
        
        // Create a list of all the species that we will cross
        DoubleInterval firstInterval;
        DoubleInterval secondInterval;
        boolean restart;
        for (int i=0; i<crosses; i++){
            restart = true;
            double firstKey = 0;
            double secondKey = 0;
            // Continue looking until we find something which is not in the alreadyUsed hash
            while (restart){
                firstInterval = probabilityTree.find(random.nextDouble()).getInterval();
                secondInterval = probabilityTree.find(random.nextDouble()).getInterval();
                firstKey = firstInterval.getMidpoint();
                secondKey = secondInterval.getMidpoint();
                
                // If we haven't already seen this pair, then add it to the list
                if (!alreadyUsed.containsKey(getKeyHash(firstKey, secondKey))){
                    alreadyUsed.put(getKeyHash(firstKey, secondKey), true);
                    restart = false;
                    
                    // Cross these two specimens and add them to the list
                    crossList.add(firstInterval.getData().cross(secondInterval.getData()));
                }                
            }
        }
        return crossList;
    }
   

    /**
     * @throws InvalidProbabilityArgumentException 
     * @throws IntersectingIntervalException 
     * 
     */
    public List<Specimen> getNewBreededSpecimens() throws InvalidProbabilityArgumentException, IntersectingIntervalException {
        List <Specimen> newSpecimens = new ArrayList <Specimen> ();
        int numberToBreed = getNumberToBreed();
        int numberMutations = getNumberOfMutations(numberToBreed);
        DoubleIntervalTree probabilityTree = buildProbabilityIntervals(oldSpecimens);
        
        // Get the crosses
        newSpecimens.addAll(makeSpecimenCrossPairs(oldSpecimens, probabilityTree));
        
        // Get the mutations
        Set <Specimen> specimensToMutate = RandomSample.randomSample(oldSpecimens, numberMutations);
        for (Specimen s: specimensToMutate){
            newSpecimens.add(s.mutate(this.mutationRate));
        }

        return newSpecimens;
    }

}
