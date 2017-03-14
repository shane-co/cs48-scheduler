package client.app.obj;

//Imports
import client.app.obj.ScheduleEvent;
import client.app.obj.Schedule;
import client.app.obj.Database;
import java.util.Iterator;
import client.app.interfaces.ScheduleObject;
import java.util.ArrayList;
import org.w3c.dom.Element;
import org.w3c.dom.Document;

/**
*Class representing a user using the Application. Maintains all data about the user and their
*saved data after using the application. It's state is kept up to date in the LOCAL Database by the
*Client
*/
public class User extends ScheduleObject{
    //instance variables
    private String username;
    private String password;
    private ArrayList<Schedule> mySchedules;
    private ArrayList<ScheduleEvent> myHostedEvents;
    private ArrayList<ScheduleEvent> myEvents;
    private ArrayList<DatabaseConnection> myOrgs;

    public User(){
        mySchedules = new ArrayList<Schedule>();
        myHostedEvents = new ArrayList<ScheduleEvent>();
        myEvents = new ArrayList<ScheduleEvent>();
        myOrgs = new ArrayList<DatabaseConnection>();
    }
    public User(Element e){
        mySchedules = new ArrayList<Schedule>();
        myHostedEvents = new ArrayList<ScheduleEvent>();
        myEvents = new ArrayList<ScheduleEvent>();
        myOrgs = new ArrayList<DatabaseConnection>();
        load(e);
    }
  //Functions to add to instance variables;
    public void setUsername(String s){username=s;}
    public void setPassword(String s){password=s;}
    public void addToMySchedules(Schedule s){
    	this.mySchedules.add(s);
    }
    public void addHostedEvent(ScheduleEvent s){
    	this.myHostedEvents.add(s);
    }
    public void addToMyEvents(ScheduleEvent s){
    	this.myEvents.add(s);
    }
    public void addDatabaseConnection(DatabaseConnection d){
    	this.myOrgs.add(d);
    }

    //Functions to remove from instance variables;
    public String getUsername(){return username;}
    public void removeFromMySchedules(Schedule s){
    	mySchedules.remove(s);
    }
    public void removeFromMyEvents(ScheduleEvent s){
    	myEvents.remove(s);
    }
    public void removeHostedEvent(ScheduleEvent s){
    	myHostedEvents.remove(s);
    }
    public void removeDatabaseConnection(DatabaseConnection d){
    	myOrgs.remove(d);
    }

    //Accessor functions
    public String getPassword(){return password;}
    public String nextSchedID(){
        if(mySchedules.size()==0)return "1";
        else{
            Schedule lastSched = mySchedules.get(mySchedules.size()-1);
            return Integer.toString(Integer.parseInt(lastSched.getID())+1);
        }
    }
    public ArrayList<Schedule> getMySchedules(){return mySchedules;}
    public ArrayList<ScheduleEvent> getMyEvents(){return myEvents;}
    public ArrayList<ScheduleEvent> getMyHostedEvents(){return myHostedEvents;}
    public ArrayList<DatabaseConnection> getMyOrgs(){return myOrgs;}
    @Override public boolean equals(Object o){
        if(this==o)return true;
        if(o==null)return false;
        if(!(o instanceof User)) return false;
        User other = (User) o;
        return other.getUsername().equals(this.getUsername());
    }
    //ScheduleObject methods
    public Element record(Document doc){
        return super.record(this,doc); //inherited by Superclass
    }
    public void load(Element root){ //recieves a "user" DOM Element Node.
        if (root!=null) {
            username=root.getAttribute("id");
            password=root.getAttribute("pw");
            myEvents=new ArrayList<ScheduleEvent>();
            myHostedEvents=new ArrayList<ScheduleEvent>();
            mySchedules=new ArrayList<Schedule>();
            myOrgs= new ArrayList<DatabaseConnection>();
            Element field = (Element)root.getFirstChild();
            while(field!=null){
                //process entries in a field
                loadArrayList(field,field.getNodeName());
                //get next field.
                field=(Element)field.getNextSibling();
            }
        }
    }
    private void loadArrayList(Element field,String tag){ //helper function to load series of DOM Elements
        Element e = (Element)field.getFirstChild();
        while(e!=null){
            switch(tag){
                case("myEvents"): myEvents.add(new ScheduleEvent(e));
                    break;
                case("myHostedEvents"): myHostedEvents.add(new ScheduleEvent(e));
                    break;
                case("mySchedules"): mySchedules.add(new Schedule(e));
                    break;
                case("myOrgs"): myOrgs.add(new DatabaseConnection(e));
                    break;
            }
            e=(Element)e.getNextSibling();
        }
    }

}
