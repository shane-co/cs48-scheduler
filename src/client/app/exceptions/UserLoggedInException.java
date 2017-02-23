package client.app.exceptions;

public class UserLoggedInException extends Exception{
    private String errMsg;
    public UserLoggedInException(){
        errMsg="User already logged in.";
    }
}
