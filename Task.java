package ua.sumdu.j2se.plachkovskyy.tasks;

import ua.sumdu.j2se.plachkovskyy.tasks.exceptions.*;

/**
* Class Task for some real task.
*/
public class Task implements Cloneable {

    private String  title;      // name of task
    private int     time;       // time of task without of repeat
    private int     start;      // start-time of task with repeat
    private int     end;        // end-time of task with repeat
    private int     interval;   // interval-time of task with repeat
    private boolean active;     // the task is active right now
    private boolean repeated;   // the task is repeated

    /**
	* Constructor1: inactive Task with parameters.
	*/
	public  Task(String title, int time) {
        this.title      =   title;
		this.time       =   time;
//        this.active     =   false;  // not necessary initialization
//        this.repeated   =   false;  // not necessary initialization
    }
    
	/**
	* Constructor2 of the Task with parameters.
	*/
    public  Task(String title, int start, int end, int interval)
            throws Exception {
        this.title      =   title;
        if (start < 0)
            throw new Exception("Start-time cannot be less then zero!");
		this.start      =   start;
        if (end < start)
            throw new Exception("End-time cannot be less then start-time!");
        this.end        =   end;
        if (interval <= 0)
            throw new Exception("Interval must be more then zero!");
        this.interval   =   interval;   
//        this.active     =   false;  // not necessary initialization
        this.repeated   =   true;
     }

    /**
    * Get title method.
    */
    public String getTitle() {
        return title;
    }
    
    /**
    * Set title method.
    */
    public void setTitle(String title) throws MyException {
        if ((title != null) && (!title.equals("")))
            this.title = title;
        else throw new MyException("Title mustn't be empty!");
    }
    
    /**
    * Get active method.
    */
    public boolean isActive() {
        return active;
    
    }
    
    /**
    * Set active method.
    */
    public void setActive(boolean active) {
        this.active = active;
    }
    
    /**
    * If repeated, return start-time.
    */
    public int getTime() {
        if (!repeated) return time;
        else return start;
    }
    
    /**
    * If repeated, make it nonrepeated.
    */
    public void setTime(int time) {
        repeated        =   false;
        this.time       =   time;
    }

    /**
    * If nonrepeated, return time.
    */
    public int getStartTime() {
        if (repeated) return start;
        else return time;
    }
    
    /**
    * If nonrepeated, return time.
    */
    public int getEndTime() {
        if (repeated) return end;
        else return time;
    }
    
    /**    
    * If nonrepeated, return 0.
    */    
    public int getRepeatInterval() {
        if (repeated) return interval;
        else return 0;
    }
    
    /**    
    * If nonrepeated, make it repeated.
    */    
    public void setTime(int start, int end, int interval) throws Exception {
        if (start < 0)
            throw new Exception("Start-time cannot be less then zero!");
		this.start      =   start;
        if (end < start)
            throw new Exception("End-time cannot be less then start-time!");
        this.end        =   end;
        if (interval <= 0)
            throw new Exception("Interval must be more then zero!");
        this.interval   =   interval;   
        if (!repeated) repeated = true;
    }

    /**
    * If it is repeated.
    */
    public boolean isRepeated() {
        return repeated;
    }
    
    /**
    * If possible, return next time or start, or return -1, if impossible.
    */
    public int nextTimeAfter(int current) {
        if (!active) return -1;
        else if (!repeated) {
            if (current >= time) return -1;
            else return time;
        } else {
            if (current < start) return start;
            else if (current >= end) return -1;
            else if ((((end - start) / interval) * interval) <= current) return -1;
            else return start + (((current - start) / interval) + 1) * interval;
        }
    }
    
    /*
    * Redefining of method equals().
    */
    @Override
    public boolean equals(Object otherObject) {
        if(this == otherObject) return true;
        if(otherObject == null ||
           this.getClass() != otherObject.getClass()) return false;
        Task other = (Task) otherObject;
        return this.title.equals(other.title) &&
 //              this.time     == other.time &&
 //              this.start    == other.start &&
 //              this.end      == other.end &&
 //              this.interval == other.interval &&
               this.active   == other.active;// &&
//               this.repeated == other.repeated;
    }

    /*
    * Redefining of method hashCode().
    */
    @Override
    public int hashCode() {
        final int prime = 1113;
        int result = 1;
        result = prime * result + ((this.title == null) ? 0 : this.title.hashCode());
//        result = prime * result + this.time;
//        result = prime * result + this.start;
//        result = prime * result + this.end;
//        result = prime * result + this.interval;
        result = prime * result + (this.active ? 0 : prime);
//        result = prime * result + (this.repeated ? 0 : prime);
        return result;
    }

    /*
    * Redefining of method toString().
    */
    @Override
    public String toString() {
        StringBuffer strbuf = new StringBuffer("Task{ title: ");
        strbuf.append(this.title);
        strbuf.append(", time: ");
        strbuf.append(this.time);
        strbuf.append(", start: ");
        strbuf.append(this.start);
        strbuf.append(", end: ");
        strbuf.append(this.end);
        strbuf.append(", interval: ");
        strbuf.append(this.interval);
        strbuf.append(", active: ");
        strbuf.append(this.active);
        strbuf.append(", repeated: ");
        strbuf.append(this.repeated);
        strbuf.append('}');
        return strbuf.toString();
    }

    /*
    * Redefining of method clone().
    */
    @Override
    public Task clone() throws CloneNotSupportedException {  
        return (Task)super.clone(); 
    }

}
