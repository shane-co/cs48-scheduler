package client.app.obj;

//Imports
import client.app.obj.ScheduleEvent;
import client.app.interfaces.ScheduleObject;
import java.util.ArrayList;
//ScheduleObject imports
import org.w3c.dom.Element;
import org.w3c.dom.Document;

/**
*Class representing a TimeBlock within a schedule. Is the basic unit within a schedule and holds only information regarding the
*block of time, its availability, and a reference to the ScheduleEvent that is occupying it. Takes 24hr time notation to denote
*start of the time block that it represents. Duration of a TimeBlock is 1hr
*/
public class TimeBlock extends ScheduleObject{
    //instance variables
    private int day; //day of the week 1-7
	private int start; // an hour 0-24;

    public TimeBlock(int d,int s){day=d; start=s;}
    public TimeBlock(Element e){
        load(e);
    }

    public int getDay(){return day;}
    public int getStart(){return start;}

    @Override public boolean equals(Object o){
        if(this==o)return true;
        if(o==null)return false;
        if(!(o instanceof TimeBlock)) return false;
        TimeBlock other = (TimeBlock) o;
        return (other.getDay()==day && other.getStart()==start);
    }
    //ScheduleObject Methods
    public Element record(Document doc){
        return super.record(this,doc); //inherited from Superclass
    }
    public void load(Element root){
        if(root!=null){
            day=Integer.parseInt(root.getAttribute("day"));
            start=Integer.parseInt(root.getAttribute("start"));
        }
    }
}
