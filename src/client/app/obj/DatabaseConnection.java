package client.app.obj;
import client.app.interfaces.Recordable;
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
    public String setIP(String newIP){
        if(validAddress(newIP)) IP = newIP;
    }
    public String setPort(int p){
        if(p<65535) port=p;
    }
}
