package iteration;

import java.util.List;

import random_sampling.InvalidProbabilityArgumentException;
import evaluation.Specimen;

public interface BreedingMethod {

    /**
     * return the number of new specimens to breed, given the current population size
     */
    public abstract int getNumberToBreed();
    
    /**
     * return the number of new specimens who will be crosses between current members of the population
     */
    public abstract int getNumberOfCrosses(int specimensToBreed);
    
    /**
     * @param specimensToBreed
     * @return the number of new specimens who will receive a mutation, note that crosses might also receive mutations.
     */
    public abstract int getNumberOfMutations(int specimensToBreed);
    
    /**
     * 
     * @param oldSpecimens List of the specimens that will create a new breed
     * @return The new specimens that have been bred from oldSpecimens
     * @throws Exception 
     */
    public abstract List <Specimen> getNewBreededSpecimens() throws Exception;
}
