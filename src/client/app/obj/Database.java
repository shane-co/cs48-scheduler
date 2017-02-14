package client.app.obj;

//Imports
//Filesystem imports
import java.io.File;
//DOM XML imports
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
//ArrayList imports
import java.util.ArrayList;
import java.util.ListIterator;
//local imports
import client.app.exceptions.UserNotFoundException;

/*
*Class representing a database store. Keeps all information about User objects and their corresponding Events.
*Allows for program persistence after termination. Contains functions to modify an XML document as a record.
* Used by Client to manipulate program state.
*/
public class Database{
    private static File record;
    private static Document doc;
    private static Element users;

    /*
    *Helper function to locate a User in the NodeList provided.
    *@return int index of the User; -1 if not found
    */
    private int getIndexOf(String uname, NodeList list){
        for(int i=0; i<list.getLength(); i++){
            String query = list.item(i).getFirstChild().getTextContent();
            if(query.equals(uname)) return i;
        }
        return -1;
    }
    private Element getUser(String uname) throws UserNotFoundException{
        NodeList list = users.getElementsByTagName("user");
        int index = getIndexOf(uname, list);
        if(index == -1) throw new UserNotFoundException(); //if user exists, get its myEvents list
        Element user = (Element) list.item(index);
        return user;
    }
    //Constructor. Checks for existence of appliation record. Loads it if available, makes new one if not.
    public Database(){
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = factory.newDocumentBuilder();
            record = new File("record.xml");
            if(record.createNewFile()){ //create structure for XML document
                System.out.println("New record created.");
                doc = docBuilder.newDocument();
                users = doc.createElement("users");
                doc.appendChild(users);
            }
            else{ //read in existing record
                doc = docBuilder.parse(record);
                System.out.println("Record loaded.");
                users = doc.getDocumentElement();
            }
        } catch (Exception e){e.printStackTrace();}
    }

    /*
    *Function to add a User to the data store. Creates XML structure for a new User with no subcscriptions.
    */
    public void addUser(String username, String password){
        //create user with all fields needed.
        Element user = doc.createElement("user");
        Element uname = doc.createElement("username");
            uname.appendChild(doc.createTextNode(username));
            user.appendChild(uname);
        Element pw = doc.createElement("pw");
            pw.appendChild(doc.createTextNode(password));
            user.appendChild(pw);
        Element myEvents = doc.createElement("myEvents");
        user.appendChild(myEvents);
        Element myHostedEvents = doc.createElement("myHostedEvents");
        user.appendChild(myHostedEvents);
        Element mySchedules = doc.createElement("mySchedules");
        user.appendChild(mySchedules);
        Element myOrgs = doc.createElement("myOrgs");
        user.appendChild(myOrgs);

        users.appendChild(user); //add user to total list
    }

    /*
    *Function to add a ScheduleEvent to a User.
    *@param uname the username of the User to add the Event to.
    *@param eventDetails an ArrayList containing Strings containing {start;end;desc;block;dep} and data.
    */
    public void subscribe(String uname, String id, ArrayList<String> eventDetails) throws UserNotFoundException {
        Element user = getUser(uname);
        Element userEvents = (Element)user.getElementsByTagName("myEvents").item(0);
        //create the event Element to be added based on the information provided in the eventDetails
        Element newev = doc.createElement("event");
        Attr idnum = doc.createAttribute("id");
        Element timeblocks = doc.createElement("blocks");
        Element dependencies = doc.createElement("deps");
        ListIterator iter=eventDetails.listIterator();
        String d=(String)iter.next();
        while(iter.hasNext()){
            switch(d){
                case "start": Element start = doc.createElement("start");
                    d=(String)iter.next(); start.appendChild(doc.createTextNode(d));
                    newev.appendChild(start);
                    break;
                case "end": Element end = doc.createElement("end");
                    d=(String)iter.next(); end.appendChild(doc.createTextNode(d));
                    newev.appendChild(end);
                    break;
                case "desc": Element desc = doc.createElement("desc");
                    d=(String)iter.next(); desc.appendChild(doc.createTextNode(d));
                    newev.appendChild(desc);
                    break;
                case "block": Element tb = doc.createElement("tb");
                    d=(String)iter.next(); tb.appendChild(doc.createTextNode(d));
                    timeblocks.appendChild(tb);
                    break;
                case "dep": Element dep = doc.createElement("dep");
                    d=(String)iter.next(); dep.appendChild(doc.createTextNode(d));
                    dependencies.appendChild(dep);
                    break;
            }
            if(iter.hasNext()) d=(String)iter.next();
        }
        idnum.setValue(id);
        newev.appendChild(timeblocks);
        newev.appendChild(dependencies);
        newev.setAttributeNode(idnum);
        userEvents.appendChild(newev);
    }

    public void unsubscribe(String uname, int id){

    }

    public static void writeToFile() throws TransformerException{
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.transform(new DOMSource(doc), new StreamResult(record));
    }

    public static void main(String argv[]){
        Database tester = new Database();
        ArrayList<String> list = new ArrayList<String>();
        list.add("start");
        list.add("Apr 7 1995");
        list.add("end");
        list.add("Feb 2 2017");
        list.add("desc");
        list.add("STUB");
        list.add("block");
        list.add("STUB");
        list.add("dep");
        list.add("STUB");

        tester.addUser("Jared","asdf");
        System.out.println("added user");
        try{
            writeToFile();
            tester.subscribe("Jared", "1", list);
        //System.out.println("added event");
        writeToFile();
        }
        catch(Exception e){e.printStackTrace();}
    }
}
