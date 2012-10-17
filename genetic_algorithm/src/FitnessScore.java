
public abstract class FitnessScore {

    /**
     * 
     * @return The specimen that corresponds to this fitness score
     */
    public abstract Specimen getSpecimen();
    
    /**
     * 
     * @return The rank of the specimen in terms of quality
     */
    public abstract int getQualityRank();
    
    /**
     * 
     * @return The rank of the specimen in terms of diversity
     */
    public abstract int getDiversityRank();
    
    /**
     * 
     * @return The total number of specimens that this fitness score is being computed out of.
     */
    public abstract int getNumSpecimens();
    
}
