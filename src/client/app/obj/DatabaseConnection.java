package client.app.obj;
import client.app.interfaces.ScheduleObject;
import org.w3c.dom.Element;
/*
*Class representing an Internet connection to a remote Database.
*/
public class DatabaseConnection extends ScheduleObject{
    private String IP;
    private int port;

    private boolean validAddress(String ip){
        //STUB
        return true;
    }

    public DatabaseConnection(String ip, int p){
        IP = ip; port = p;
    }
    public DatabaseConnection(Element root){
        load(root);
    }
    public String getIP(){return IP;}
    public int getPort(){return port;}
    public void setIP(String newIP){
        if(validAddress(newIP)) IP = newIP;
    }
    public void setPort(int p){
        if(p<65535) port=p;
    }

    //ScheduleObject methods
    public Element record(){
        return super.record(this);
    }
    public void load(Element e){
        IP=e.getAttribute("ip");
        port=Integer.parseInt(e.getAttribute("port"));
    }
}
