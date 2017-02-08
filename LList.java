
/**
 * This class creates a list-like data structure that is dynamic
 * in memory unlike an array, but unlike an ArrayList, LList does
 * not have a seperate memory bank to hold the indexes of each
 * item.  Instead, items are found by how far they are from the
 * head node. The second node is 1 step away from the head node, so
 * it has an index of 1.
 * 
 * @author Aaron Vega
 * @version 1.0
 * @since 2016-11-18
 */
public class LList{
    private Node head = null;
    private Node tail = null;

    /**
     * Input data is turned into a node and inserted atthe head
     * of the LList.
     * 
     * @param input String "data" will become the head node.
     * @return Nothing
     */
    public void insert(String data){
        Node newNode = new Node();
        if(head == null){
            newNode.data = data;
            this.head = newNode;
            this.tail = newNode;
        }
        else{
            newNode.data = data;
            this.head.pPrev = newNode;
            newNode.pNext = this.head;
            this.head = newNode;
        }

        Node current = this.head;
        while(true){
            if(current.pNext == null){
                tail = current;
                break;
            }
            else{
                current = current.pNext;
            }
        }
    }

    /**
     * Input data is turned into a node and inserted at the tail
     * of the LList.
     * 
     * @param input String "data" will become the tail node.
     * @return Nothing
     */
    public void addTail(String data){
        Node newNode = new Node();
        if(tail == null){
            newNode.data = data;
            this.tail = newNode;
            this.head = newNode;
        }
        else{
            newNode.data = data;
            this.tail.pNext = newNode;
            newNode.pPrev = this.tail;
            this.tail = newNode;
        }

        Node current = this.tail;
        while(true){
            if(current.pPrev == null){
                head = current;
                break;
            }
            else{
                current = current.pPrev;
            }
        }
    }

    /**
     * Takes an integer as an index of the list. Finds corresponding
     * item and removes it by reconnecting pointers to nodes around it.
     * Java's garbage collection process deletes node completely when
     * nothing is pointing to it.
     * 
     * @param input String "data" will become the head node.
     * @return Nothing
     */

    public void remove(int index){
        Node current = this.head;
        int currentIndex = 0;
        while(currentIndex < index){
            try{
                current = current.pNext;
            }
            catch(Exception e){
                return;
            }

            if(current.pNext == null){
                break;
            }
            currentIndex++;
        }

        if(current != null){
            Node up = current.pPrev;
            Node down = current.pNext;
            if(up != null){
                up.pNext = down;
                current.pPrev = null;
            }
            if(down != null){
                down.pPrev = up;
                current.pNext = null;
            }
            if(down == null && up == null){
                this.head = null;
                this.tail = null;
            }
        }

        current = this.head;
        while(true){
            if(current == null){
                break;
            }
            if(current.pNext == null){
                this.tail = current;
                break;
            }
            else{
                current = current.pNext;
            }
        }

    }

    /**
     * Uses similar process as when remove() takes in an integer,
     * except this time, we use a loop to find if any of the list's
     * items match the input node. If found, it will use the same
     * process of deletion that remove(int) does.
     * 
     * @param dead is a node to be removed
     * @return nothing
     * @exception throws LinkedListException if input node is null
     * or node is not on list
     */
    public void remove(Node dead) throws LinkedListException{
        Node current = dead;
        if(current == null){
            throw new LinkedListException();
        }
        if(current.pPrev == null && current.pNext == null && this.head == null && this.tail == null){
            return;
        }
        Node checker = this.head;
        while(true){
            if(checker == current){
                break;
            }
            else if(checker.pNext == null & checker != current){
                throw new LinkedListException();
            }
            else if(checker == null){
                throw new LinkedListException();
            }
            checker = checker.pNext;
        }

        if(current != null){
            Node up = current.pPrev;
            Node down = current.pNext;
            if(up != null){
                up.pNext = down;
                current.pPrev = null;
            }
            else if(up == null && down != null){
                this.head = down;
            }
            if(down != null){
                down.pPrev = up;
                current.pNext = null;
            }
            else if(down == null && up != null){
                this.tail = up;
            }
            if(down == null && up == null){
                this.head = null;
                this.tail = null;
            }
            current.pPrev = null;
            current.pNext = null;
        }

        current = this.head;
        while(true){
            if(current == null){
                break;
            }
            if(current.pNext == null){
                this.tail = current;
                break;
            }
            else{
                current = current.pNext;
            }
        }
    }

    /**
     * Takes a string of data and turns it into a node. Also takes a
     * node existing on the list and adds the new node right after it.
     * Connections pointer of priorNode to new node.
     * 
     * @param data String becomes a new node
     * @param priorNode is node that exists on list
     * @return nothing
     * @exception Will throw LinekdListException if input
     * node is null
     */
    public void addAfter(String data, Node priorNode) throws LinkedListException{
        if(priorNode == null){
            throw new LinkedListException();
        }

        Node newNode = new Node();
        newNode.data = data;
        Node up = priorNode;
        Node down = priorNode.pNext;
        up.pNext = newNode;
        newNode.pNext = down;
        newNode.pPrev = up;
        if(down != null){
            down.pPrev = newNode;
        }
    }

    /**
     * Function finds out how many nodes there are on list
     * 
     * @param Nothing
     * @return number of nodes as an int
     * 
     */
    public int numNodes(){
        int count = 0;
        Node current = this.head;
        while(current != null){
            count++;
            current = current.pNext;
        }
        return count;
    }

    /**
     * Finds node on list according to index and returns it
     * 
     * @param int index
     * @return node on list if it exists
     * 
     */
    public Node getNode(int index){
        Node current = this.head;
        int currentIndex = 0;
        if(current == null || index < 0){
            return null;
        }
        while(currentIndex < index){
            current = current.pNext;
            if(current == null){
                break;
            }
            currentIndex++;
        }

        return current;
    }

    /**
     * Function takes all nodes of list and puts their data
     * together in a String array
     * 
     * @param nothing
     * @return String array of all nodes' data
     */
    public String[] asArray(){
        int size = numNodes();
        String[] ret = new String[size];
        Node current = this.head;
        for(int i = 0; i < size; i++){
            ret[i] = current.data;
            current = current.pNext;
        }
        return ret;
    }

    /**
     * @return head of list
     */
    public Node getHead(){
        return this.head;
    }

    /**
     * @return tail of list
     */
    public Node getTail(){
        return this.tail;
    }
    
    /**
     * @param Takes in Node as node
     * @return Returns the node right after it
     * @exception Will throw LinkedListException if node is null,
     * or item is not found in list.
     */

    public Node nextNode(Node node) throws LinkedListException{
        if(node == null){
            throw new LinkedListException();
        }
        Node current = node;
        Node checker = this.head;
        while(true){
            if(checker == current){
                break;
            }
            else if(checker.pNext == null & checker != current){
                throw new LinkedListException();
            }
            else if(checker == null){
                throw new LinkedListException();
            }
            checker = checker.pNext;
        }

        return node.pNext;
    }

    /**
     * Inner class to be used by LList. 
     */
    public class Node{
        String data;
        Node pNext;
        Node pPrev;
        
        /**
         * @return returns data as a String
         */
        public String getData(){
            String d = new String(this.data);
            return d;
        }
    }
}