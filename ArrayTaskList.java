package ua.sumdu.j2se.plachkovskyy.tasks;

/**
 * Class ArrayTaskList for the list of tasks
 */
public class ArrayTaskList {

    private int     listLength  = 10;                   // array start length
    private Task[]  taskList    = new Task[listLength]; // list of tasks
    int             i;                                  // array index

    /**
     *   Constructor of empty list
     */
    public ArrayTaskList() {
        for (i = 0; i < listLength; i++) {
            taskList[i] = null;
        }
    }

    /**
     * Adding new element (task) into list
     */
    public void add(Task task) {
        for (i = 0; i < taskList.length; i++) {
            if (taskList[i] == null) {
                taskList[i] =   task;
                break;
            }
        }
        // Checking/correction array size
        if ((size() / taskList.length) > 0.75) {
            listLength   =   (int) (taskList.length * 1.3);
            Task[]  tempList    = new Task[listLength];
            System.arraycopy(taskList, 0, tempList, 0, taskList.length);
            taskList = tempList;
        }
    }

    /**
     * Removes only first appropriate element from list.
     * Returns true if element was found and removed,
     * returns false if element was not found.
     */
    public boolean remove(Task task) {
        boolean done = false;
        for (i = 0; i < listLength; i++) {
            if (taskList[i].equals(task)) {
                taskList[i] = null;
                done = true;
                // Checking/correction array size
                if ((size() / listLength) < 0.5) {
                    listLength   =   (int) (listLength * 0.75);
                    Task[]  tempList    = new Task[listLength];
                    System.arraycopy(taskList, 0, tempList, 0, i);
                    System.arraycopy(taskList, ++i, tempList, i-1, listLength - i);
                    taskList = tempList;
                }
                break;
            }
        }
        return done;
    }

    /**
     * Returns tasks total
     */
    public int size() {
        int realSize = 0;
        for (i = 0; i < listLength; i++) {
            if (taskList[i] != null) {
                realSize += 1;
            }
        }
        return realSize;
    }

    /**
     * Returns Task by index
     */
    public Task getTask(int index) {
        if (index < listLength) {
            return taskList[index];
        } else {
            return null;
        }
    }

    /**
     * Returns tasks list, which will be planned at least once
     * after "from" and not later than "to"
     */
    public Task[] incoming(int from, int to) {
        Task[] incomingList    =   new Task[listLength];
        for (i = 0; i < listLength; i++) {
            if ((taskList[i].nextTimeAfter(from) > from) &&
                    (taskList[i].nextTimeAfter(from) <= to)) {
                incomingList[i] = taskList[i];
            }
        }
        // Optimization of the size of incoming array
        int realSize    =   size();         // real size of taskList
        int index       =   0;              // index for tempList
        Task[] tempList    =   new Task[realSize];
        for (i = 0; i < listLength; i++) {
            if (taskList[i] != null) {
                tempList[index] =   taskList[i];
                index++;
            }
        }
        incomingList = tempList;
        return incomingList;
    }
}
