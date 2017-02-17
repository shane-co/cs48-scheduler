package client.app.obj;
//Local imports
import client.app.interfaces.Recordable;
//XML DOM imports
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Dependencies implements Recordable{
	//string array store object needed
	//private ArrayList<string> Dependencies = new ArrayList<string>;
	private String prerequiste;
	private String object;

	//constructor
	public Dependencies(String pre, String obj){
	prerequiste = pre;
	object = obj;
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

	public Element record(){
		try{
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            return doc.createElement("STUB");
        }catch(ParserConfigurationException p){}
			return null;
	}

	public void load(Element e){}
}
