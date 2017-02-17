package client.app.obj;

//Local Imports
import client.app.obj.Dependencies;
import client.app.obj.TimeBlock;
import client.app.interfaces.Recordable;
//XML DOM imports
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
//ArrayList
import java.util.ArrayList;
/**
*Class representing an event. Basic object that a user will subscribe to. Is created by Organizations and has event dependencies,
*duration of event, and the organization it was created by.
*/
public class ScheduleEvent implements Recordable{

    private ArrayList<Dependencies> deps;
    private int day;
    private int start;
    private int end;
    private String description;
    private String id;
    public ScheduleEvent(ArrayList<Dependencies> dep,int d,int s, int e, String dp)
    {
    	deps=dep;
    	day=d;
    	start=s;
    	end=e;
    	description=dp;
    }

    public ScheduleEvent(int d,int s, int e, String dp)
    {
    	day=d;
    	start=s;
    	end=e;
    	description=dp;
    }

    public int what_day() {return day;}
    public int when_to_start() {return start; }
    public int when_to_end() {return end; }
    public int duration() {return start-end;}
    public String get_descpt() {return description; }
    public void set_day(int d){day=d;}
    public void set_start(int s) {start=s; }
    public void set_to_end(int e) { end=e; }
    public void set_descpt(String dp) { description=dp; }
    public void set_id(String i){id=i;}

    //Methods to implement Recordable interface
    public Element record(){
        try{
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element ev = doc.createElement("event");
            Attr idnum = doc.createAttribute("id");
            Element d = doc.createElement("day");
            Attr dnum = doc.createAttribute("val");
            Element s = doc.createElement("start");
            Attr startTime = doc.createAttribute("val");
            Element e = doc.createElement("end");
            Attr endTime = doc.createAttribute("val");
            Element desc = doc.createElement("desc");
            Element dep =  doc.createElement("deps");

            idnum.setValue(id);
            dnum.setValue(Integer.toString(day));
            startTime.setValue(Integer.toString(start));
            endTime.setValue(Integer.toString(end));
            ev.setAttributeNode(idnum);
            d.setAttributeNode(dnum);
            s.setAttributeNode(startTime);
            e.setAttributeNode(endTime);
            desc.appendChild(doc.createTextNode(description));
            for(int i=0; i<deps.size(); i++){
                dep.appendChild(deps.get(i).record());
            }
            ev.appendChild(d);
            ev.appendChild(s);
            ev.appendChild(e);
            ev.appendChild(desc);
            ev.appendChild(dep);

            return ev;
        }catch(ParserConfigurationException p){}
            return null;
    }

    public void load(Element root){

    }
}
