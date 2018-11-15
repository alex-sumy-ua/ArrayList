package ua.sumdu.j2se.plachkovskyy.tasks;

/**
* Class Task for some real task
*/
public class Task {

    private String  title;      // name of task
    private int     time;       // time of task without of repeat
    private int     start;      // start-time of task with repeat
    private int     end;        // end-time of task with repeat
    private int     interval;   // interval-time of task with repeat
    private boolean active;     // the task is active right now
    private boolean repeated;   // the task is repeated

    /**
	* Constructor1: inactive Task with parameters
	*/
	public  Task(String title, int time) {
        this.title      =   title;
		this.time       =   time;
//        this.active     =   false;  // not necessary initialization
//        this.repeated   =   false;  // not necessary initialization
    }
    
	/**
	* Constructor2 of the Task with parameters
	*/
    public  Task(String title, int start, int end, int interval)
            throws Exception {
        this.title      =   title;
        if (start < 0) {
            throw new Exception("Start-time cannot be less then zero!");
        }
		this.start      =   start;
        if (end < start) {
            throw new Exception("End-time cannot be less then start-time!");
        }
        this.end        =   end;
        if (interval <= 0) {
            throw new Exception("Interval must be more then zero!");
        }
        this.interval   =   interval;   
//        this.active     =   false;  // not necessary initialization
        this.repeated   =   true;
     }

    /**
    * get title method
    */
    public String getTitle() {
        return title;
    }
    
    /**
    * set title method
    */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
    * get active method
    */
    public boolean isActive() {
        return active;
    
    }
    
    /**
    * set active method
    */
    public void setActive(boolean active) {
        this.active = active;
    }
    
    /**
    * if repeated, return start
    */
    public int getTime() {
        if (!repeated) {
            return time;
        } else {
            return start;
        }
    }
    
    /**
    * if repeated, make it nonrepeated
    */
    public void setTime(int time) {
        this.time       =   time;
        repeated        =   false;
    }

    /**
    * if nonrepeated, return time
    */
    public int getStartTime() {
        if (repeated) {
            return start;
        } else {
            return time;
        }
    }
    
    /**
    * if nonrepeated, return time
    */
    public int getEndTime() {
        if (repeated) {
            return end;
        } else {
            return time;
        }
    }
    
    /**    
    * if nonrepeated, return 0
    */    
    public int getRepeatInterval() {
        if (repeated) {
            return interval;
        } else {
            return 0;
        }
    }
    
    /**    
    * if nonrepeated, make it repeated
    */    
    public void setTime(int start, int end, int interval) throws Exception {
        if (start < 0) {
            throw new Exception("Start-time cannot be less then zero!");
        }
		this.start      =   start;
        if (end < start) {
            throw new Exception("End-time cannot be less then start-time!");
        }
        this.end        =   end;
        if (interval <= 0) {
            throw new Exception("Interval must be more then zero!");
        }
        this.interval   =   interval;   

        if (!repeated) {
            repeated = true;
        }
    }

    /**
    * if it is repeated
    */
    public boolean isRepeated() {
        return repeated;
    }
    
    /**
    * if possible, return next time or start, or return -1, if impossible
    */
    public int nextTimeAfter(int current) {
        if (!active) {
            return -1;
        } else if (!repeated) {
            if (current >= time) {
                return -1;
            } else {
                return time;
            }
        } else {
            if (current < start) {
                return start;
            } else if (current >= end) {
                return -1;
            } else if ((((end - start) / interval) * interval) <= current) {
                return -1;
            } else {
                return start + (((current - start) / interval) + 1) * interval;
            }
        }
    }
}
