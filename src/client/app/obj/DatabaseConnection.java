package client.app.obj;
import client.app.interfaces.Recordable;
//XML DOM imports
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
/*
*Class representing an Internet connection to a remote Database.
*/
public class DatabaseConnection implements Recordable{
    private String IP;
    private int port;

    private boolean validAddress(String ip){
        //STUB
        return true;
    }

    public DatabaseConnection(String ip, int p){
        IP = ip; port = p;
    }

    public String getIP(){return IP;}
    public int getPort(){return port;}
    public void setIP(String newIP){
        if(validAddress(newIP)) IP = newIP;
    }
    public void setPort(int p){
        if(p<65535) port=p;
    }

    //Methods to implement Recordable interface
    public Element record(){
        try{
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            return doc.createElement("STUB");
        }catch(ParserConfigurationException p){}
        return null;
    }
    public void load(Element e){}
}
