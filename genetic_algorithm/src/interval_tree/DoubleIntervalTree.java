package interval_tree;

import java.util.Collections;
import java.util.List;

public class DoubleIntervalTree {

    private IntervalTreeNode root = null;
    
    public DoubleIntervalTree(List <DoubleInterval> intervals) throws IntersectingIntervalException{
        Collections.sort(intervals);
        buildTree(intervals, 0, intervals.size());
    }
    
    /**
     * Builds the tree according to the ranges given.
     * @param intervals A sorted list of the intervals to build the tree on
     * @param bottom The lower bound (bottom index) of the range
     * @param top The upper bound (top index) of the range
     * @throws Exception
     */
    private void buildTree(List <DoubleInterval> intervals, int bottom, int top) throws IntersectingIntervalException{
        if (bottom < 0 || top > intervals.size()){
            return;
        }
        int mid = bottom + (top - bottom)/2;
        
        // Get the interval in the middle of the range and add it to the interval
        DoubleInterval currentInterval = intervals.get(mid);
        addInterval(currentInterval);
        
        // Continue recursively on the left and right half
        buildTree(intervals, bottom, mid-1);
        buildTree(intervals, mid+1, top);
        
    }
    
    /**
     * Adds an interval into the tree.
     * @param interval
     * @throws Exception 
     */
    public void addInterval(DoubleInterval interval) throws IntersectingIntervalException{
        IntervalTreeNode newNode = new IntervalTreeNode(interval);
        addNode(newNode);
    }
    
    /**
     * Adds a node into the tree, must add a node who's interval is not already in the tree. Thus, the interval cannot 
     * intersect with any other interval already in the tree.
     * 
     * @param node A node object to insert into the tree
     * @throws Exception
     */
    public void addNode(IntervalTreeNode node) throws IntersectingIntervalException{
        if (this.root == null){
            this.root = node;
            return;
        }
        double midpoint = node.getMidpoint();
        IntervalTreeNode foundNode = find(midpoint, true);
        
        // If the foundNode contains the midpoint, then we've already got an interval covering this area
        if (foundNode.getInterval().contains(midpoint)){
            throw new IntersectingIntervalException("Node's interval intersects with an interval already in the tree.");
        } else {
            int compareInt = foundNode.getInterval().compare(midpoint);
            // If we are in this loop, then we know that the midpoint is not contained in any interval.
            if (compareInt >= 1){
                foundNode.setRightChild(node);
            } else {
                foundNode.setLeftChild(node);
            }
        }
        
    }
    
    public IntervalTreeNode find(double number){
        return find(number, false);
    }
    
    /**
     * Finds the interval in which number resides
     * @param number Double to look for inside of the interval tree
     * @param backtrack Returns the last node that was seen if we return null
     */
    public IntervalTreeNode find(double number, boolean backtrack){
        IntervalTreeNode node = this.root;
        IntervalTreeNode lastSeenNode = null;
        int comparisonInt;
        while (node != null){
            lastSeenNode = node;
            comparisonInt = node.getInterval().compare(number);
   
            // Check whether the number is smaller, larger, or in the interval
            if (comparisonInt >= 1){
                node = node.getRightChild();
            } else if (comparisonInt <= -1){
                node = node.getLeftChild();
            } else {
                return node;
            }    
        }
        
        // If we are enabling backtracking, return the last non-null node that we visited.
        if (backtrack){
            return lastSeenNode;
        }
        // If we ever reach a null child, then we return null as well
        return null;
    }
    
    class IntersectingIntervalException extends Exception{
        public IntersectingIntervalException(String s){
            super(s);
        }
    }
}
