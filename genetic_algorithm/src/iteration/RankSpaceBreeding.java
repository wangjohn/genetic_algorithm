package iteration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import evaluation.Specimen;

public class RankSpaceBreeding implements BreedingMethod {

    private final List<Specimen> oldSpecimens; 
    private final int currentPopulationSize;
    private final Random random = new Random();
   
    public RankSpaceBreeding(List <Specimen> oldSpecimens){
        this.oldSpecimens = oldSpecimens;
        this.currentPopulationSize = oldSpecimens.size();
    }
    public int getNumberToBreed() {
        // Under this implementation, we will be creating a new specimen from each old specimen
        return this.currentPopulationSize;
    }

    public int getNumberOfCrosses(int specimensToBreed) {
        // We pick the number of crosses to be chosen uniformly from 0 to getNumberToBreed.
        return this.random.nextInt(getNumberToBreed());
    }

    public int getNumberOfMutations(int specimensToBreed) {
        return specimensToBreed;
    }

    /**
     * Private method to get the pairs of specimens that will be crossed
     * @param oldSpecimens
     */
    private makeSpecimenCrossPairs(List <Specimen> oldSpecimens){
        // Get the crosses 
        int crosses = getNumberOfCrosses(this.currentPopulationSize);
        HashMap <Integer, Boolean> alreadyUsed = new HashMap <Integer, Boolean>();
        List <Specimen> crossList = new ArrayList <Specimen> ();
        
        // Create a list of all the species that we will cross
        for (int i=0; i<crosses; i++){
            int firstKey = this.random.nextInt(this.currentPopulationSize);
            int secondKey = this.random.nextInt(this.currentPopulationSize);
            while (alreadyUsed.containsKey(firstKey*this.currentPopulationSize + secondKey) || firstKey == secondKey){
                firstKey = this.random.nextInt(this.currentPopulationSize);
                secondKey = this.random.nextInt(this.currentPopulationSize);
            }
            crossList.add(this.oldSpecimens.get(nextKey));
            alreadyUsed.put(nextKey, true);
        }
        
        // Since these specimens were chosen randomly, we can just pair together the specimens next to each other on the list.
    }

    public List<Specimen> getNewBreededSpecimens(List<Specimen> oldSpecimens) {

        
        // TODO Auto-generated method stub
        return null;
    }

}
