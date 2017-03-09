package client.app.exceptions;
public class ElementNotFoundException extends Exception{
    protected String errMsg;
    public ElementNotFoundException(){
        errMsg="Element does not exist in Database.";
    }
    public String getMsg(){return errMsg;}
    public void printMsg(){System.out.println(errMsg);}
}
