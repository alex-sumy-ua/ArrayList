package ua.sumdu.j2se.plachkovskyy.tasks;

import ua.sumdu.j2se.plachkovskyy.tasks.exceptions.*;

/**
* Class LinkedTaskList for the list of tasks.
*/
public class LinkedTaskList extends TaskList {

    private Node    first;          // first element of the List
    private int     length = 0;     // size of List
    
     /**
	 * The node of the list - internal class.
	 */
	private class Node {
        
        private Task task;     // current Node content - Task
        private Node next;     // pointer for next Node
        
        /**
        * Constructor1 of the object of the class Node.
        */
        public Node() {
            next = null;
        }
        
        /**
        * Constructor2 of the object of the class Node.
        */
        public Node(Task task) {
            this.task   = task;
            next        = null;
        }
        
        /**
         * Set content of the next node.
         */
        public void setNext(Node node) {
            next = node;
        }
        
        /**
         * Move the pointer to the next node.
         */
        public Node getNext() {
            return next;
        }
        
        /**
         * Set content of the current node.
         */
        public void setContent(Task task) {
            this.task = task;
        }
        
        /**
         * Get content of the current node.
         */
        public Task getContent() {
            return task;
        }
    }

    /**
     * Constructor1 of the class LinkedTaskList.
     */
    public LinkedTaskList() {
        Node first  = new Node();
        length      = 0;
    }

    /**
     * Add element to the end of List.
     */
     @Override
    public void add(Task task) throws MyException {
        Node end = new Node(task);
        try {
            if (length > 0) {
                Node tmp = findElement(length - 1);
                tmp.setNext(end);
            }
            else {
                first = end;
            }
        } catch (MyException e) {
            throw new MyException(e);
        }
        length++;
    }
 
    /**
     * Remove element of List by index.
     */
    @Override
    public boolean remove(Task task) throws MyException {
        boolean done = false;
        if (task == null) {
            return done;
        }
        
        Node tmp = first;
        for (int i = 0; i < length; i++) {
            if (tmp.getContent() == task) {
                if (i == 0) {
                    first = first.getNext();
                    length--;
                    done = true;
                    return done;
                } else {
                    Node tmp1 = first;
                    Node tmp2 = first;
                    tmp1 = findElement(i - 1);
                    tmp2 = tmp1.getNext();
                    tmp1.setNext(tmp2.getNext());
                    length--;
                    done = true;
                    break;
                }
            }
            tmp = tmp.getNext();
        }
        return done;
    }

    /**
     * Get length of the List.
     */
     @Override
    public int size() {
        return length;
    }
    
    /**
     * Get content of the element with index
     */
    @Override
    public Task getTask(int index) {
        if (index < 0 || index >= length) {
            return null;
        }
        Node tmp = first;
        for (int i = 0; i < index; i++) {
            tmp = tmp.getNext();
        }
        return tmp.getContent();
    }
   
    /**
     * Find element of VectorList by index.
     */
    private Node findElement(int index) throws MyException {
        if (index > this.length || index < 0) {
            throw new MyException("index out of bounds");
        }
        Node tmp = first;
        for (int i = 0; i < index; i++) {
            tmp = tmp.getNext();
        }
        return tmp;
    }
    
    /**
    * Returns tasks list, which will be planned at least once
    * after "from" and not later than "to".
    */
    @Override
    public LinkedTaskList incoming(int from, int to) throws MyException {
        if (from < 0) {
            throw new MyException("From-time cannot be less then zero!");
        }
        if (to <= from) {
            throw new MyException("To-time must be more then from-time!");
        }
        LinkedTaskList linkedTaskList = new LinkedTaskList();
        for (int i = 0; i < length; i++) {
            if ((getTask(i).nextTimeAfter(from) >= from) &&
                (getTask(i).nextTimeAfter(from) <= to))
                linkedTaskList.add(getTask(i));
        }
        return linkedTaskList;
    }
}
