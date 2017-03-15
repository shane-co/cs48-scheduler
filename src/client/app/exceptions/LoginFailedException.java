package client.app.exceptions;
public class LoginFailedException extends Exception{
    protected String errMsg;
    public LoginFailedException(){
        errMsg="Login Failed";
    }
    public String getMsg(){return errMsg;}
    public void printMsg(){System.out.println(errMsg);}
}
