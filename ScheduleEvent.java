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
    private int start;
    private int end;
    private String description;
    public ScheduleEvent(ArrayList<Dependencies> d,int s, int e, String dp)
    {
    	deps=d;
    	start=s;
    	end=e;
    	description=dp;
    }
    
    public int when_to_start() {return start; }
    public int when_to_end() {return end; }
    public String get_descpt() {return description; }
}
