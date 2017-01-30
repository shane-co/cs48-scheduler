package client.app;

#Imports
import client.app.Dependencies;
import client.app.TimeBlock;
import client.app.Organization;
import java.util.ArrayList;
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
    private Organization org;
}
