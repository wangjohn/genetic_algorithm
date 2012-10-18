package interval_tree;

public class IntersectingIntervalException extends Exception{
    /**
     * Exception class for intersecting intervals
     */
    private static final long serialVersionUID = 1L;

    public IntersectingIntervalException(String s){
        super(s);
    }
}
