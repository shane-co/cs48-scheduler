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
    private ArrayList<TimeBlock> duration;
    private String description;
    private String id;
    public ScheduleEvent(ArrayList<Dependencies> dep, ArrayList<TimeBlock> dur, String dp,String i)
    {
    	deps=dep;
    	duration=dur;
    	description=dp;
        id=i;
    }

    public ScheduleEvent(Element root){
        deps = new ArrayList<Dependencies>();
        duration = new ArrayList<TimeBlock>();
        load(root);
    }

    public ScheduleEvent(String networkDesc){
        deps = new ArrayList<Dependencies>();
        duration = new ArrayList<TimeBlock>();
        load(networkDesc);
    }

    public ArrayList<TimeBlock> duration(){return duration;}
    public String get_descpt() {return description; }
    public String get_ID() {return id; }
    public ArrayList<Dependencies> getDependencies(){return deps;}

    public boolean conflicts(ScheduleEvent compare){
        for(TimeBlock t : compare.duration()){
            if(duration.contains(t)) return true;
        }
        return false;
    }

    @Override public boolean equals(Object o){
        if(this==o)return true;
        if(o==null)return false;
        if(!(o instanceof ScheduleEvent)) return false;
        ScheduleEvent other = (ScheduleEvent) o;
        return other.get_ID().equals(id);
    }

    @Override public String toString(){
        String durationString = "";
        String dependencyString = "";
        for(TimeBlock t: duration){
            durationString+= t.toString()+"|";
        }
        for(Dependencies d: deps){
            dependencyString+=d.toString();
        }
        String result = "id:"+id+";description:"+description+";duration:"+durationString+";dependencies:"+dependencyString;
        //id:JARED;description:SOMETEXT;duration:((0-7),(0-2400)|)*;dependencies:?
        return result;
    }
    //ScheduleObject methods
    public Element record(Document doc){
        return super.record(this,doc); //inherited by Superclass
    }
    public void load(Element root){
        if(root!=null){
            id=root.getAttribute("id");
            description=root.getFirstChild().getTextContent();
            Element depelem = (Element)root.getFirstChild().getNextSibling();
            Element dpnd = (Element)depelem.getFirstChild();
            while(dpnd!=null){
                deps.add(new Dependencies(dpnd));
                dpnd= (Element)dpnd.getNextSibling();
            }
            Element drtn = (Element)depelem.getNextSibling().getFirstChild();
            while(drtn!=null){
                 duration.add(new TimeBlock(drtn));
                 drtn=(Element)drtn.getNextSibling();
            }
        }
    }

    public void load(String recv){
        String[] fields = recv.split(";");
        id=fields[0].split(":")[1];
        description=fields[1].split(":")[1];
        loadArray(fields[2]);
        loadArray(fields[3]);
    }
    private void loadArray(String data){
        String[] parts = data.split(":");
        switch(parts[0]){
            case "duration":
                for(String dur:parts[1].split("|")){
                    duration.add(new TimeBlock(dur));
                }
            break;
            case "dependencies":
                //STUB Class Dependencies has no load(String) function and no Dependencies(String) constructor.
            break;
        }
    }
}
