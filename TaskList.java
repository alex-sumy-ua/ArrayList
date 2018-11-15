package ua.sumdu.j2se.plachkovskyy.tasks;

import ua.sumdu.j2se.plachkovskyy.tasks.exceptions.*;

/**
* Class TaskList for the list of tasks.
*/
abstract class TaskList {
    
    /**
    *   Constructor of empty list.
    */
    public TaskList () {}

    /**
    * Adding new element (task) into list.
    */
    public abstract void add(Task task) throws MyException;
    
    /**
    * Removes only first appropriate element from list.
    * Returns true if element was found and removed,
    * returns false if element was not found.
    */
    public abstract boolean remove(Task task) throws MyException;
    
    /**
    * Returns tasks total.
    */
    public abstract int size();
    
    /**
    * Returns Task by index.
    */
    public abstract Task getTask(int index);
    
    /**
    * Returns tasks list, which will be planned at least once
    * after "from" and not later than "to".
    */
    public abstract TaskList incoming(int from, int to) throws MyException;/* {
        if (from < 0) {
            throw new MyException("From-time cannot be less then zero!");
        }
        if (to <= from) {
            throw new MyException("To-time must be more then from-time!");
        }
        TaskList taskList = new TaskList();
        for (int i = 0; i < size(); i++) {
            if ((getTask(i).nextTimeAfter(from) >= from) &&
                (getTask(i).nextTimeAfter(from) <= to))
                taskList.add(getTask(i));
        }
        return taskList;
    }*/
}
