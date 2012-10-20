package evaluation;

import java.util.Arrays;

/**
 * Class which implements and stores the variable inside a regression. Basically makes a name for the variable, and stores its data. 
 */
public class RegressionVariable {
    
    private final String name;
    private Arrays data;
    private int dataColumn;
    
    public RegressionVariable(String name, Arrays data, int dataColumn){
        this.name = name;
        this.data = data;
        this.dataColumn = dataColumn;
    }
    
    public String getName(){
        return this.name;
    }
    
    public Arrays getData(){
        return this.data;
    }
    
    public int getDataColumn(){
        return this.dataColumn;
    }
    

}
