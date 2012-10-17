package evaluation;

/* interface for making a fitness function */
public interface FitnessFunction {
    
    /**
     * @param s: specimen to use the fitness function on
     * 
     * @requires s != null
     * @return FitnessScore object which provides a score to evaluate upon
     * 
     */
    public FitnessScore getScore(Specimen s);
    
}
