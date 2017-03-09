package client.app.obj;
//Local imports
import client.app.interfaces.ScheduleObject;
//ScheduleObject imports
import org.w3c.dom.Element;
import org.w3c.dom.Document;


public class Dependencies extends ScheduleObject{
	//string array store object needed
	//private ArrayList<string> Dependencies = new ArrayList<string>;
	private String prerequiste;
	private String object;

	//constructor
	public Dependencies(String pre, String obj){
	prerequiste = pre;
	object = obj;
	}
	public Dependencies(Element root){
		load(root);
	}

	//set and get functions
	public String get_prerequiste(){
	return prerequiste;
	}

	public String get_object(){
	return object;
	}

	public void set_object(String s){
	this.object = s;
	}

	public void set_prerequiste(String s){
	this.prerequiste = s;
	}

	//ScheduleObject methods
	public Element record(Document doc){
        return super.record(this,doc); //inherited by Superclass
    }
	public void load(Element root){
		if(root!=null){
			prerequiste=root.getFirstChild().getTextContent();
			object=root.getLastChild().getTextContent();
		}
	}
}
