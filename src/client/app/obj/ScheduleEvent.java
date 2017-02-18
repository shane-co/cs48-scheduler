package client.app.obj;

//Local Imports
import client.app.obj.Dependencies;
import client.app.obj.TimeBlock;
import client.app.interfaces.ScheduleObject;

//ArrayList
import java.util.ArrayList;
//ScheduleEvent imports
import org.w3c.dom.Element;

/**
*Class representing an event. Basic object that a user will subscribe to. Is created by Organizations and has event dependencies,
*duration of event, and the organization it was created by.
*/
public class ScheduleEvent extends ScheduleObject{

    private ArrayList<Dependencies> deps;
    private int day;
    private int start;
    private int end;
    private String description;
    private String id;
    public ScheduleEvent(ArrayList<Dependencies> dep,int d,int s, int e, String dp,String i)
    {
    	deps=dep;
    	day=d;
    	start=s;
    	end=e;
    	description=dp;
        id=i;
    }

    public ScheduleEvent(int d,int s, int e, String dp,String i)
    {
    	day=d;
    	start=s;
    	end=e;
    	description=dp;
        id=i;
    }

    public int what_day() {return day;}
    public int when_to_start() {return start; }
    public int when_to_end() {return end; }
    public int duration() {return start-end;}
    public String get_descpt() {return description; }
    public String get_ID() {return id; }
    public void set_day(int d){day=d;}
    public void set_start(int s) {start=s; }
    public void set_to_end(int e) { end=e; }
    public void set_descpt(String dp) { description=dp; }
    public void set_id(String i){id=i;}
    public int num_deps(){return deps.size();}
    public Dependencies getDependency(int index){return deps.get(index);}

    //ScheduleObject methods
    public Element record(){
        return super.record(this); //inherited by Superclass
    }
    public void load(Element root){

    }
}
