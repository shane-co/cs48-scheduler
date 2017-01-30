package client.app;

#Imports
import client.app.Event;

/**
*Class representing a TimeBlock within a schedule. Is the basic unit within a schedule and holds only information regarding the
*block of time, its availability, and a reference to the ScheduleEvent that is occupying it. Takes 24hr time notation to denote 
*start and end of the time block that it represents
*/
public class TimeBlock{

    private String start;
    private String end;
    private ScheduleEvent se;
    private boolean occupied;
  
    /**
    *Default Constructor
    */
    public TimeBlock(String s, String e, ScheduleEvent ev, boolean occ){}
  
    //Getters
    public String getBlock(){}
    public ScheduleEvent getEvent(){}
    public boolean isOccupied(){}
  
    //Setters
    /**
    *Sets the ScheduleEvent that occupies the time block and changes occupied boolean accordingly
    *@param ev ScheduleEvent object that is occupying the time block. If NULL this method sets occupied to false; otherwise sets to true
    */
    public void setEvent(ScheduleEvent ev){}
  
}
