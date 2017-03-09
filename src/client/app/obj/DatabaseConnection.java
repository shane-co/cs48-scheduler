package client.app.obj;
import client.app.interfaces.ScheduleObject;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
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

    @Override public boolean equals(Object o){
        if(this==o)return true;
        if(o==null)return false;
        if(!(o instanceof DatabaseConnection)) return false;
        DatabaseConnection other = (DatabaseConnection) o;
        return other.getIP().equals(IP);
    }
    //ScheduleObject methods
    public Element record(Document doc){
        return super.record(this,doc);
    }
    public void load(Element root){
        if(root!=null){
            IP=root.getAttribute("ip");
            port=Integer.parseInt(root.getAttribute("port"));
        }
    }
}
