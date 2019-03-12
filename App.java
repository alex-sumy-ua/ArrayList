import exceptions.MyException;
import java.util.Date;

public class App {
    public static void main(String[] args) throws MyException, CloneNotSupportedException {
        ArrayTaskList atl = new ArrayTaskList();
        LinkedTaskList ltl = new LinkedTaskList();
        Task task1 = new Task("Sport", new Date(119, 05, 10)); // year - 1900, month - 0..11, day - 1..31
        Task task2 = new Task("Work", new Date(119, 05, 11));
        Task task3 = new Task("Sleeping", new Date(119, 05, 12));
        Task task4 = task3.clone();

        System.out.println("task3.getTime() " + task3.getTime());
        task4.setTime(new Date());
        System.out.println("task4.getTime() " + task4.getTime());
        System.out.println("task3.getTime() " + task3.getTime());

        ltl.add(task1);
        atl.add(task1);
        ltl.add(task2);
        atl.add(task2);
        ltl.add(task3);
        atl.add(task3);


        try {
            ArrayTaskList atl2 = atl.clone();
            System.out.println(atl2);
        }
        catch (CloneNotSupportedException e) {
            System.out.println("Finish");
        }

        ltl.remove(task2);
        System.out.println(ltl);

    }
}
