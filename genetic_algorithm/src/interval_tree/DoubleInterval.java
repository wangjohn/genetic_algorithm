package interval_tree;

import evaluation.Specimen;

public class DoubleInterval implements Comparable<DoubleInterval> {

    private final double x;
    private final double y;
    private final double midpoint;
    private Specimen data = null;
    
    public DoubleInterval(Double x, Double y, Specimen data){
        this.x = x;
        this.y = y;
        this.midpoint = (x+y)/2;
        this.data = data;
    }
    
    public DoubleInterval(Double x, Double y){
        this.x = x;
        this.y = y;
        this.midpoint = (x+y)/2;
    }
    
    public Specimen getData(){
        return data;
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

    public int compareTo(DoubleInterval o) {
        double thisMidpoint = this.midpoint;
        double thatMidpoint = o.getMidpoint();
        
        if (thisMidpoint < thatMidpoint){
            return -1;
        } else if (thisMidpoint > thatMidpoint){
            return 1;
        } else {
            return 0;
        }
    }
}
