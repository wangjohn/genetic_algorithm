package interval_tree;

public class DoubleInterval {

    private final double x;
    private final double y;
    private final double midpoint;
    
    public DoubleInterval(Double x, Double y){
        this.x = x;
        this.y = y;
        this.midpoint = (x+y)/2;
    }
    
    public double getX(){
        return this.x;
    }
    
    public double getY(){
        return this.y;
    }
    
    public double getMidpoint(){
        return this.midpoint;
    }
    
    /**
     * Method for identifying whether this interval contains i
     * @param i Check whether this number is in the interval
     * @return True if the i is in the interval, false otherwise
     */
    public boolean contains(Double i){
        if (this.x >= i && i <= this.y){
            return true;
        }
        return false;
    }
    
    /**
     * Method to identify whether i is inside the interval, less than the interval, or greater than the interval
     * @param i The number to compare the interval against
     * @return -1 if i is less than the interval, 0 if i is contained in the interval, 1 if i is greater than the interval
     */
    public int compare(Double i){
        if (i < this.x){
            return -1;
        } else if (i >= this.x && i <= this.y) {
            return 0;
        } else {
            return 1;
        }
    }
}
