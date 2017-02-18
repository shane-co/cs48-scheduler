package client.app.obj;

//Imports
import client.app.obj.ScheduleEvent;
import java.util.ArrayList;
/**
*Class representing a TimeBlock within a schedule. Is the basic unit within a schedule and holds only information regarding the
*block of time, its availability, and a reference to the ScheduleEvent that is occupying it. Takes 24hr time notation to denote
*start of the time block that it represents. Duration of a TimeBlock is 1hr
*/
public class TimeBlock{
    //instance variables
    private int day;
	private int start;
    private ArrayList<ScheduleEvent> se;


    public TimeBlock(int d,int s){day=d; start=s;}

    public void addEvent(ScheduleEvent ev){se.add(ev);}
    public void removeEvent(int position){se.remove(position);}
    public int getDay(){return day;}
    public int getStart(){return start;}
    public int numberOfEvents(){return se.size();}
    public ScheduleEvent getEvent(int index){return se.get(index);}
}
