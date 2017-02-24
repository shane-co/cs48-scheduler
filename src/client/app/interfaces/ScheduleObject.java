package client.app.interfaces;

//XML DOM imports
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.util.ArrayList;
//Local imports
import client.app.obj.Schedule;
import client.app.obj.ScheduleEvent;
import client.app.obj.User;
import client.app.obj.Dependencies;
import client.app.obj.TimeBlock;
import client.app.obj.DatabaseConnection;

/*
*Superclass to be extended by any class that has persistence in the scheduler application. Implements record(doc) function to convert it's running state
*into XML format for recording. Implements load() to read XML format and initialize all run time variables.
*/
public abstract class ScheduleObject{
    /**
    *Helper function to convert the object into XML representation readable by the application
    *@param o Object to be converted into XML representation
    *@return Element DOM object to be stored and recorded by Database
    */
    protected Element record(Object o, Document doc){
        if(o instanceof Schedule){
            Schedule s = (Schedule) o;
            Element sched = doc.createElement("sched");
            Attr schedid = doc.createAttribute("id");
            for(int i=0;i<s.size_of_TimeBlock();i++){
                TimeBlock t = s.get_TimeBlock(i);
                sched.appendChild(t.record(doc));
            }
            sched.setAttributeNode(schedid);
        }
        else if(o instanceof TimeBlock){
            TimeBlock t = (TimeBlock) o;
            Element tb = doc.createElement("tb");
            Attr tbday = doc.createAttribute("day");
            Attr tbstart = doc.createAttribute("start");
            Element events = doc.createElement("events");
            tbday.setValue(Integer.toString(t.getDay()));
            tbstart.setValue(Integer.toString(t.getStart()));
            for(int j=0;j<t.numberOfEvents();j++){
                events.appendChild(t.getEvent(j).record(doc));
            }
            tb.setAttributeNode(tbday);
            tb.setAttributeNode(tbstart);
            tb.appendChild(events);
            return tb;
        }
        else if(o instanceof ScheduleEvent){
            ScheduleEvent e = (ScheduleEvent) o;
            Element ev = doc.createElement("event");
            Attr idnum = doc.createAttribute("id");
            Attr day = doc.createAttribute("day");
            Attr start = doc.createAttribute("start");
            Attr end = doc.createAttribute("end");
            Element desc = doc.createElement("desc");
            Element deps = doc.createElement("deps");

            idnum.setValue(e.get_ID());
            day.setValue(Integer.toString(e.what_day()));
            start.setValue(Integer.toString(e.when_to_start()));
            end.setValue(Integer.toString(e.when_to_end()));
            desc.appendChild(doc.createTextNode(e.get_descpt()));
            for(int i=0;i<e.num_deps();i++){
                deps.appendChild(e.getDependency(i).record(doc));
            }

            ev.setAttributeNode(idnum);
            ev.setAttributeNode(day);
            ev.setAttributeNode(start);
            ev.setAttributeNode(end);
            ev.appendChild(desc);
            ev.appendChild(deps);
            return ev;
        }

        else if(o instanceof Dependencies){
            Dependencies d = (Dependencies) o;
            Element dep = doc.createElement("dep");
            Element prereq = doc.createElement("pr");
            Element obj = doc.createElement("obj");
            prereq.appendChild(doc.createTextNode(d.get_prerequiste()));
            obj.appendChild(doc.createTextNode(d.get_object()));
            dep.appendChild(prereq);
            dep.appendChild(obj);
            return dep;
        }
        else if(o instanceof DatabaseConnection){
            DatabaseConnection d = (DatabaseConnection) o;
            Element dc = doc.createElement("org");
            Attr ip = doc.createAttribute("ip");
            Attr port = doc.createAttribute("port");
            ip.setValue(d.getIP());
            port.setValue(Integer.toString(d.getPort()));
            dc.setAttributeNode(ip);
            dc.setAttributeNode(port);
            return dc;
        }
        else if(o instanceof User){
            User u = (User) o;
            Element user = doc.createElement("user");
            Attr uname = doc.createAttribute("id");
            Attr pword = doc.createAttribute("pw");
            Element myEvents = doc.createElement("myEvents");
            Element myHostedEvents = doc.createElement("myHostedEvents");
            Element mySchedules = doc.createElement("mySchedules");
            Element myOrgs = doc.createElement("myOrgs");
            ArrayList<ScheduleEvent> evs = u.getMyEvents();
            ArrayList<ScheduleEvent> hevs = u.getMyHostedEvents();
            ArrayList<Schedule> scheds = u.getMySchedules();
            ArrayList<DatabaseConnection> orgs = u.getMyOrgs();
            uname.setValue(u.getUsername());
            pword.setValue(u.getPassword());
            for(int i=0;i<evs.size();i++){
                myEvents.appendChild(evs.get(i).record(doc));
            }
            for(int i=0;i<hevs.size();i++){
                myHostedEvents.appendChild(hevs.get(i).record(doc));
            }
            for(int i=0;i<scheds.size();i++){
                mySchedules.appendChild(scheds.get(i).record(doc));
            }
            for(int i=0;i<orgs.size();i++){
                myOrgs.appendChild(orgs.get(i).record(doc));
            }

            user.setAttributeNode(uname);
            user.setAttributeNode(pword);
            user.appendChild(myEvents);
            user.appendChild(myHostedEvents);
            user.appendChild(mySchedules);
            user.appendChild(myOrgs);
            return user;
        }
        return doc.createElement("null");
    }


    /**
    *Function to load and instantiate objects instance variables given XML representation. To be overriden by subclasses.
    */
    public abstract void load(Element root); //loads runtime information given an XML DOM Element Node.

    /**
    *Function to convert the object into XML representation readable by application
    */
    public abstract Element record(Document doc);
}
