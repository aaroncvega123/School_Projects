
/**
 * Custom exception class for LList and its subclasses
 * 
 * @author Aaron Vega
 * @version 1.0
 * @since 2016-11-18
 */
public class LinkedListException extends Exception{
    
    /**
     * Default constructor
     */
    public LinkedListException(){
        super("Something went wrong with the Linked List");
    }
    
    /**
     * @param Takes a custom message to be used in error
     * message when exception is thrown
     */
    public LinkedListException(String msg){
        super(msg);
    }
}