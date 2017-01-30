package client.app;

#Imports
import client.app.Event;
import client.app.Day;
/**
*Class representing a TimeBlock within a schedule. Is the basic unit within a schedule and holds only information regarding the
*block of time, its availability, and a reference to the ScheduleEvent that is occupying it. Takes 24hr time notation to denote 
*start of the time block that it represents. Duration of a TimeBlock is 1hr
*/
public class TimeBlock{

    private Day;
    private String start;
    private ScheduleEvent se;
    private boolean occupied;
  
    /**
    *Default Constructor
    *@param d the Day event that this TimeBlock belongs to.
    *@param s the time that the TimeBlock starts
    *@param ev the ScheduleEvent that occupies this TimeBlock. NULL if no event associated
    *@param occ boolean indicating availability. Must be True if ev!=NULL
    */
    public TimeBlock(Day d, String s, ScheduleEvent ev, boolean occ){}
  
    /**
    *Function to retrieve time information regarding the TimeBlock
    *@return String of format "yyyy-mm-dd hh:mm" in 24hr time notation
    */
    public String getBlock(){}
    
    /**
    *Function to retrieve ScheduleEvent that is associated with TimeBlock
    *@return ScheduleEvent object
    */
    public ScheduleEvent getEvent(){}
    
    /**
    *Function to determine availability of TimeBlock
    *@return TRUE if se!=NULL; FALSE otherwise
    */
    public boolean isOccupied(){}
  
    /**
    *Sets the ScheduleEvent that occupies the time block and changes occupied boolean accordingly
    *@param ev ScheduleEvent object that is occupying the time block. If NULL this method sets occupied to false; otherwise sets to true
    */
    public void setEvent(ScheduleEvent ev){}
  
}
