package client.app.obj;

//Local Imports
import client.app.obj.Dependencies;
import client.app.obj.TimeBlock;
import client.app.interfaces.ScheduleObject;

//ArrayList
import java.util.ArrayList;
//ScheduleEvent imports
import org.w3c.dom.Element;
import org.w3c.dom.Document;

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
        deps=new ArrayList<Dependencies>();
    	day=d;
    	start=s;
    	end=e;
    	description=dp;
        id=i;
    }
    public ScheduleEvent(Element root){
        load(root);
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

    @Override public boolean equals(Object o){
        if(this==o)return true;
        if(o==null)return false;
        if(!(o instanceof ScheduleEvent)) return false;
        ScheduleEvent other = (ScheduleEvent) o;
        return other.get_ID().equals(id);
    }
    //ScheduleObject methods
    public Element record(Document doc){
        return super.record(this,doc); //inherited by Superclass
    }
    public void load(Element root){
        if(root!=null){
            id=root.getAttribute("id");
            day=Integer.parseInt(root.getAttribute("day"));
            start=Integer.parseInt(root.getAttribute("start"));
            end=Integer.parseInt(root.getAttribute("end"));
            description=root.getFirstChild().getTextContent();
            Element d = (Element)root.getLastChild().getFirstChild();
            while(d!=null){
                deps.add(new Dependencies(d));
                d= (Element)d.getNextSibling();
            }
        }
    }
}
