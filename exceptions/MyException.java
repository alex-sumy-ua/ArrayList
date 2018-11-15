package ua.sumdu.j2se.plachkovskyy.tasks.exceptions;

/**
 * Description of ListException - expansion of the class Exception.
 * Index out of range of the list.
 */
public class MyException extends Exception {

    public MyException() {
        super("Index out of range of the list");
    }

    public MyException(String descry) {
        super(descry);
    }
    
    public MyException(Throwable cause) {
        super(cause);
    }
    
    public MyException(String descry, Throwable cause) {
        super(descry, cause);
    }

}
