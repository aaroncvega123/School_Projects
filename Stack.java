
/**
 * This class uses many functions from LList, but as a Stack,
 * meaning items added (push()) go on top, and items removed
 * (pop()) come from the top (Last in, First Out)
 * 
 * @author Aaron Vega
 * @version 1.0
 * @since 2016-11-18
 */
public class Stack extends LList{
    
    /**
     * @param Takes in String as "data". Adds it to
     * head of list using super.insert().
     * @return Nothing
     */
    public void push(String data){
        super.insert(data);
    }
    
    /**
     * Removes the head node of the list.
     * 
     * @param Nothing
     * @return String of removed node
     */
    public String pop(){
        if(super.numNodes() == 0){
            return null;
        }
        String ret = super.getHead().data;
        
        try{
            super.remove(super.getHead());
         
        }
        catch(Exception e){
            
        }
        return ret;
        
    }
    
    /**
     * Checks if a list is empty or not
     * 
     * @param Nothing
     * @return true or false
     */
    public boolean empty(){
        if(super.numNodes() == 0){
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * @return String of data from head of list
     */
    public String peek(){
        if(empty()){
            return null;
        }
        else{
            String ret = new String(super.getHead().data);
            return ret;
        }
    }
}