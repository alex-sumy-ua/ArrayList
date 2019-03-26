//package ua.sumdu.j2se.plachkovskyy.tasks;

import /*ua.sumdu.j2se.plachkovskyy.tasks.*/exceptions.*;
import java.util.*;

/**
 * Class Tasks for some real task.
 */
public class Tasks {

    /**
     * Returns tasks list, which will be planned at least once
     * after "from" and not later than "to".
     * Any collection for storing tasks.
     */
    public static Iterable<Task> incoming(Iterable<Task> tasks, Date start, Date end) throws MyException {
        ArrayTaskList collection = new ArrayTaskList();
        for (Task task: tasks) {
            if (task.isActive() &&
                task.nextTimeAfter(start) != null &&
                task.nextTimeAfter(start).after(start) &&
                task.nextTimeAfter(start).compareTo(end) <= 0) {
                collection.add(task);
            }
        }
        return collection;
    }

    /**
     * Task calendar for a given period.
     */
    public static SortedMap<Date, Set<Task>> calendar(Iterable<Task> tasks, Date start, Date end) {
        SortedMap<Date, Set<Task>> sortedMap = new TreeMap<Date, Set<Task>>();
        for (Task task: tasks) {                                // iterator in tasks by task
            Date date = task.nextTimeAfter(start);
            while (date != null && date.compareTo(end) <= 0) {  // work with current task
                if (sortedMap.containsKey(date)) {              // adding into existing set
                    sortedMap.get(date).add(task);
                }
                else {
                    Set<Task> tasksSet = new HashSet<Task>();   // adding into new set
                    tasksSet.add(task);
                    sortedMap.put(date, tasksSet);
                }
                date = task.nextTimeAfter(date);                // iterator by date in current task
            }

        }
        return sortedMap;
    }

}
