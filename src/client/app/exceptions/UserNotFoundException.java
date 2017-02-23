package client.app.exceptions;

public class UserNotFoundException extends ElementNotFoundException{
    public UserNotFoundException(){
        errMsg="User does not exist in the Database.";
    }
}
