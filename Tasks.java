package ua.sumdu.j2se.plachkovskyy.tasks;

import ua.sumdu.j2se.plachkovskyy.tasks.exceptions.*;
import java.util.*;

/**
 * Class Tasks for some real task.
 */
public class Tasks {

    /**
     * Returns tasks list, which will be planned at least once
     * after "from" and not later than "to".
     */
    public static Iterable<Task> incoming(Iterable<Task> tasks, Date start, Date end) {
        return new Iterable<Task>() {
            @Override
            public Iterator<Task> iterator() {
                Iterator<Task> iterator = tasks.iterator();
                while (iterator.hasNext()){
                    Task task = iterator.next();
                    if (task.nextTimeAfter(start) == null ||
                        task.nextTimeAfter(start).before(start) ||
                        task.nextTimeAfter(start).after(end))   { iterator.remove();}
                }
                return iterator;
            }
        };
    }

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
