package client.app.obj;

//Imports
import client.app.obj.ScheduleEvent;
import client.app.obj.TimeBlock;
import java.util.ArrayList;
import client.app.interfaces.ScheduleObject;
import org.w3c.dom.Element;


public class Schedule extends ScheduleObject{
	private ArrayList<TimeBlock> tb;
	private int month;
	private int day;
	private String id;

	public Schedule(ArrayList<ScheduleEvent> events, String idnum, int m, int d)
	{
		id = idnum;
		month=m;
		day=d;
		//create timeblocks for a week
		for (int day=1; day<=7; day++)
			for (int hour=0; hour<24; hour++)
			{
				TimeBlock t=new TimeBlock(day,hour);
				tb.add(t);
			}
		
		//insert events into their corresponding timeblock(s)
		for (int i=0; i<events.size(); i++){
			for (int j=0;j<=(events.get(i).duration()-1)/100;j++){
				tb.get((events.get(i).what_day()-1)*24+events.get(i).when_to_start()+j).addEvent(events.get(i));
			}
		}
	}

	public int size_of_TimeBlock() {return tb.size();}
	public int get_month() {return month;}
	public int get_day() {return day;}
	public TimeBlock get_TimeBlock(int index) {return tb.get(index);}
	public String getID(){return id;}
	//to simplify event modification, I suggest using remove and add function of class
	//timeblock itself. remove the event requiring modification and add the modified one
	//back.

	//ScheduleObject methods
	@Override public Element record(){
		return super.record(this);
    }
	public void load(Element e){}
}
