package client.app.obj;

//Imports
import client.app.obj.ScheduleEvent;
import client.app.obj.TimeBlock;
import java.util.ArrayList;
import client.app.interfaces.ScheduleObject;
import org.w3c.dom.Element;
import org.w3c.dom.Document;


public class Schedule extends ScheduleObject{
	private ArrayList<ScheduleEvent> events; //all their timeblocks do not intersect.
	private int month;
	private int day;
	private String id;

	public Schedule(ArrayList<ScheduleEvent> e, String idnum, int m, int d){
		id = idnum;
		month=m;
		day=d;
		events=e;
	}
	public Schedule(Element root){ //Constructor that takes a DOM element and loads it.
		load(root);
	}

	public int numEvents() {return events.size();}
	public int get_month() {return month;}
	public int get_day() {return day;}
	public ArrayList<ScheduleEvent> get_ScheduleEvents() {return events;}
	public String getID(){return id;}

	@Override public boolean equals(Object o){
        if(this==o)return true;
        if(o==null)return false;
        if(!(o instanceof Schedule)) return false;
		Schedule other = (Schedule) o;
		return other.getID().equals(id);
    }

	//ScheduleObject methods
	@Override public Element record(Document doc){
		return super.record(this,doc);
    }
	public void load(Element root){
		if(root!=null){
			id=root.getAttribute("id");
			month=Integer.parseInt(root.getAttribute("month"));
			day=Integer.parseInt(root.getAttribute("day"));
			Element evElement = (Element) root.getFirstChild();
			while(evElement!=null){
				events.add(new ScheduleEvent(evElement));
				evElement=(Element)evElement.getNextSibling();
			}
		}
	}
}
