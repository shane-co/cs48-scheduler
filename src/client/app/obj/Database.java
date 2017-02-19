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
import javax.xml.xpath.*;

//ArrayList imports
import java.util.ArrayList;
import java.util.ListIterator;
//local imports
import client.app.exceptions.*;

/**
*Class representing a database store. Keeps all information about User objects and their corresponding Events.
*Allows for program persistence after termination. Contains functions to modify an XML document as a record.
* Used by Client to manipulate program state.
*/
public class Database{
    private static File record;
    private static Document doc;
    private static Element users;
    private static Transformer transformer;
    private int usernum;

    /**
    *Helper functions to locate Elements in the NodeList provided.
    *@return int index of the User; -1 if not found
    */
    private int getIndexOf(String idnum, NodeList list){
        for(int i=0; i<list.getLength(); i++){
            String query = ((Element)list.item(i)).getAttribute("id");
            if(query.equals(idnum)) return i;
        }
        return -1;
    }
    private Element findElement(Element userField, String tag, String elemid) throws ElementNotFoundException{
        NodeList list = userField.getElementsByTagName(tag);
        int index = getIndexOf(elemid, list);
        if(index == -1) throw new ElementNotFoundException();
        Element e = (Element) list.item(index);
        return e;
    }
	public Element findUser(String tag, String elemid) throws ElementNotFoundException{
		return findElement(users, tag, elemid);
	}

    /**Constructor. Checks for existence of appliation record. Loads it if available, makes new one if not.
    */
    public Database(){
        try{
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformer = transformerFactory.newTransformer();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = factory.newDocumentBuilder();
            record = new File("record.xml");
            if(record.createNewFile()){ //create structure for XML document
                System.out.println("New record created.");
                doc = docBuilder.newDocument();
                users = doc.createElement("users");
                doc.appendChild(users);
                usernum=0;
            }
            else{ //read in existing record
                doc = docBuilder.parse(record);
                System.out.println("Record loaded.");
                users = doc.getDocumentElement();
                usernum = users.getElementsByTagName("user").getLength();
            }
        } catch (Exception e){e.printStackTrace();}
    }

    /**
    *Function to add a new User to the data store. Creates XML structure for a new User with no subcscriptions.
    *DOES NOT check for User existence
    *@param u Element object representing the User as an XML record
    */
    public void addUser(Element u){
        users.appendChild(u); //add user to total list
    }

    /**
    *Function to modify the XML record of a specified User by either adding to or deleting from the main XML fields. (e.g. myEvents, myOrgs
    *mySchedules, myHostedEvents)
    *@param uname the username of the User to modify
    *@param add a boolean value either true meaning add, false meaning delete.
    *@param f the main XMl field to alter {myEvents,myHostedEvents,mySchedules,myOrgs}
    *@param newelem a DOM Element Node containing all runtime information about the object to be stored in the main XML file.
    */
    public void modifyUser(String userid, boolean add, String f, Element newelem) throws UserNotFoundException, ElementNotFoundException{
        Element user = findElement(users, "user", userid);
        Element field = doc.createElement("null");
        String tagname = "null";
        switch(f){
            case "myEvents": field = (Element)user.getElementsByTagName("myEvents").item(0);
                tagname="event";
                break;
            case "myHostedEvents": field = (Element)user.getElementsByTagName("myHostedEvents").item(0);
                tagname="event";
                break;
            case "mySchedules": field = (Element)user.getElementsByTagName("mySchedules").item(0);
                tagname="schedule";
                break;
            case "myOrgs": field = (Element)user.getElementsByTagName("myOrgs").item(0);
                tagname="org";
                break;
            default: break;
        }
        if(add) field.appendChild(newelem);
        else{
            //find element in doc and delete it.
            String newelemid = newelem.getAttribute("id");
            Element deleted = findElement(field, tagname, newelemid);
            deleted.getParentNode().removeChild(deleted);
        }
    }

    public String outputSearchResultString(String uname, String filters) throws UserNotFoundException{
            //get element corresponding to tagname and val.
            //get all elements from myHostedEvents and apply filters.
            return ""; //STUB
    }

	/**
 	* Function to check login credentials against a User already stored in the database.
 	* @return bool true if valid credentials; false otw
 	*/

	public boolean verifyCredentials(String uname, String pw) throws ElementNotFoundException{
        try{
            Element u = findElement(users, "user", uname);
    		String recordedPW = u.getElementsByTagName("pw").item(0).getTextContent();
    		return recordedPW.equals(pw);
        }catch(ElementNotFoundException e){throw new UserNotFoundException();}
	}

	/**
 	* Function to write the entire DOM model to local XML stored on the computer.
 	*/
    public static void writeToFile() throws TransformerException{
        transformer.transform(new DOMSource(doc), new StreamResult(record));
    }


}
