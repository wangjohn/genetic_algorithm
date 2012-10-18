package interval_tree;

public class IntervalTreeNode {

    private DoubleInterval node;
    private IntervalTreeNode parent = null;
    private IntervalTreeNode left = null;
    private IntervalTreeNode right = null;
    
    public IntervalTreeNode(DoubleInterval interval){
        this.node = interval;
    }
    
    public DoubleInterval getInterval(){
        return this.node;
    }
    
    public double getMidpoint(){
        return this.node.getMidpoint(); 
    }
    
    public IntervalTreeNode getLeftChild(){
        return this.left;
    }
    
    public IntervalTreeNode getRightChild(){
        return this.right;
    }
    
    public IntervalTreeNode getParent(){
        return this.parent;
    }
    
    public void setLeftChild(IntervalTreeNode left){
        this.left = left;
    }
    
    public void setRightChild(IntervalTreeNode right){
        this.right = right;
    }
    
    public void setParent(IntervalTreeNode parent){
        this.parent = parent;
    }
}
