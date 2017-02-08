/**
 * This class uses many functions from LList, but as a Queue,
 * meaning items added (enqueue()) go on top, and items removed
 * (dequeue()) come from the bottom (First in, First Out)
 * 
 * @author Aaron Vega
 * @version 1.0
 * @since 2016-11-18
 */
public class Queue extends LList{
    
    /**
     * @param Takes in String as "data". Adds it to
     * head of list using super.insert().
     */
    public void enqueue(String next){
        super.insert(next);
    }
    
    /**
     * Removes the tail node of the list.
     * 
     * @param Nothing
     * @return String of removed node
     */
    public String dequeue(){
        if(super.numNodes() == 0){
            return null;
        }
        String ret = super.getTail().data;
        
        try{
            super.remove(super.getTail());
         
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
     * @return String of data from tail of list
     */
    public String peek(){
        if(empty()){
            return null;
        }
        else{
            String ret = new String(super.getTail().data);
            return ret;
        }
    }
    }
