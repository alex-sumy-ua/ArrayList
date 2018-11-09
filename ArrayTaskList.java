package ua.sumdu.j2se.plachkovskyy.tasks;

/**
* Class ArrayTaskList for the list of tasks
*/
public class ArrayTaskList {

    private Task[]  taskList;           // list of tasks
    private int     listLength  = 10;   // array start length
    private int     realSize    = 0;    // array real size

    /**
    *   Constructor of empty list
    */
    public ArrayTaskList () {
        taskList    =   new Task[10];
        listLength  =   taskList.length;
        realSize    =   size();
    }
    
    /**
    * Adding new element (task) into list
    */
    public void add(Task task) {
        if (realSize < listLength) {
            taskList[realSize++]  =   task;     // realSize increased!!!
            // Checking/correction array size
            if ((realSize / listLength) > 0.75) {
                listLength = (int) (listLength * 1.3);
                Task[] tempList = new Task[listLength];
                System.arraycopy(taskList, 0, tempList, 0, realSize);
                taskList = tempList;
            }
        }
    }
    
    /**
    * Removes only first appropriate element from list.
    * Returns true if element was found and removed,
    * returns false if element was not found.
    */
    public boolean remove(Task task) {
        boolean done = false;
        if (task == null) {
            return done;
        }
        for (int i = 0; i < realSize; i++) {
            if (taskList[i].equals(task)) {
                Task[]  tempList = new Task[listLength];
                System.arraycopy(taskList, 0, tempList, 0, i);
                System.arraycopy(taskList, i+1, tempList, i, listLength-i-1);
                taskList = tempList;
                realSize--;                     // realSize decreased!!!
                done = true;
                break;
            }
        }
        // Optimization of the size of array. Important: minimum size is 10
        if (((realSize / listLength) < 0.5) &&
            ((listLength * 0.75) > 10)) {
            listLength = (int) (listLength * 0.75);
            Task[]  tempList = new Task[listLength];
            System.arraycopy(taskList, 0, tempList, 0, realSize);
            taskList = tempList;
        }
        return done;
    }
    
    /**
    * Returns tasks total
    */
    public int size() {
        return realSize;
    }
    
    /**
    * Returns Task by index
    */
    public Task getTask(int index) {
        if ((index >= 0) && (index < listLength)) {
            return taskList[index];
        } else {
            return null;
        }
    }
    
    /**
    * Returns tasks list, which will be planned at least once
    * after "from" and not later than "to"
    */
    public ArrayTaskList incoming(int from, int to) {
        ArrayTaskList arrayTaskList = new ArrayTaskList();
        for (int i = 0; i < realSize; i++) {
            if ((taskList[i].nextTimeAfter(from) >= from) &&
                (taskList[i].nextTimeAfter(from) <= to))
                arrayTaskList.add(taskList[i]);
        }
        return arrayTaskList;
    }
}