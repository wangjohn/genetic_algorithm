package evaluation;

public interface Specimen {

    /**
     * 
     * @return the score for the current specimen
     */
    public double getEvaluatedScore();
    
    /**
     * 
     * @param s
     * @return the distance between specimen s and the current specimen
     */
    public double getDistance(Specimen s);
    
}
