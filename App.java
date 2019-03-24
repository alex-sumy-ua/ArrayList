import exceptions.MyException;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

public class App {
    public static void main(String[] args) throws MyException, CloneNotSupportedException, IOException, ParseException {
        ArrayTaskList atl = new ArrayTaskList();
        LinkedTaskList ltl = new LinkedTaskList();
        Task task1 = new Task("Sport", new Date(119, 05, 10),
                                            new Date(119, 07, 10),
                                            10000); // year - 1900, month - 0..11, day - 1..31
        task1.setActive(true);
        Task task2 = new Task("Work", new Date(119, 05, 11));
        task2.setActive(true);
        Task task3 = new Task("Sleeping", new Date(119, 05, 12));
        Task task4 = task3.clone();

//        System.out.println("task3.getTime() " + task3.getTime());
        task4.setTime(new Date());
//        System.out.println("task4.getTime() " + task4.getTime());
//        System.out.println("task3.getTime() " + task3.getTime());

        ltl.add(task1);
        atl.add(task1);
        ltl.add(task2);
        atl.add(task2);
        ltl.add(task3);
        atl.add(task3);


//        try {
//            ArrayTaskList atl2 = atl.clone();
//            System.out.println(atl2);
//        } catch (CloneNotSupportedException e) {
//            System.out.println("Finish");
//        }

//        ltl.remove(task2);
//        System.out.println(ltl);

        System.out.println(atl + "\n\n Compare to:\n");
        File file = new File("D:\\TextTasks.txt");
        TaskIO.writeText(atl, file);
        ArrayTaskList tmp = new ArrayTaskList();
        TaskIO.readText(tmp,  file);
        System.out.println(tmp);


    }
}
