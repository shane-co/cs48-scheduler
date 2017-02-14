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
    private ArrayList<TimeBlocks> duration;
    private TimeBlock start;
    private TimeBlock end;
    private String description;
    private int id;
}
