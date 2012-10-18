/**
 * This class serves as a time epoch inside of the genetic algorithm. Time proceeds in the algorithm by creating new TimeEpochs, and during each
 * epoch, new specimens will be grown and old ones will die.
 */

package iteration;

import java.util.List;
import evaluation.Specimen;

public abstract class TimeEpoch {

    /**
     * 
     * @param specimenList
     * @return
     */
    protected abstract List<Specimen> breedNewSpecimens(List<Specimen> specimenList);
    
    /**
     * 
     * @param specimenList The list of all current specimens
     * @return A list of specimens to kill
     * 
     */
    protected abstract List<Specimen> killOldSpecimens(List<Specimen> specimenList);
    
    /**
     * A method for getting the next time epoch.
     * 
     * @return the next timeEpoch
     * 
     */
    public abstract TimeEpoch getNextEpoch();
    
    /**
     * 
     * @return The maximum number of specimens possible in this population.
     * 
     */
    public abstract int getMaxPopulation();
    
}
