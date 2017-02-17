package client.app;

//Imports
import client.app.obj.Dependencies;
import client.app.obj.TimeBlock;

/**
*Class representing an event. Basic object that a user will subscribe to. Is created by Organizations and has event dependencies,
*duration of event, and the organization it was created by.
*/
public class ScheduleEvent{

    private ArrayList<Dependencies> deps;
    private int day;
    private int start;
    private int end;
    private String description;
    public ScheduleEvent(ArrayList<Dependencies> dep,int d,int s, int e, String dp)
    {
    	deps=dep;
    	day=d;
    	start=s;
    	end=e;
    	description=dp;
    }
    
    public ScheduleEvent(int d,int s, int e, String dp)
    {
    	day=d;
    	start=s;
    	end=e;
    	description=dp;
    }
    
    public int what_day() {return day;}
    public int when_to_start() {return start; }
    public int when_to_end() {return end; }
    public int duration() {return start-end;}
    public String get_descpt() {return description; }
    public void set_day(int d){day=d;}
    public void set_start(int s) {start=s; }
    public void set_to_end(int e) { end=e; }
    public void set_descpt(String dp) { description=dp; }
}
