package evaluation;

public interface Specimen {

    /**
     * 
     * @return the score for the current specimen
     */
    public double getEvaluatedScore();
    
    
    /**
     * A method for crossing two specimens and obtaining a third.
     * @param other The other specimen to be crossed with
     * @return
     */
    public Specimen cross(Specimen other);
    
    /**
     * Method for mutating a specimen
     * @param mutationRate The mutation rate of a specimen, must be between 0 and 1.
     * @return Returns a mutated specimen, with the given mutation rate
     */
    public Specimen mutate(double mutationRate);
    
    
}
