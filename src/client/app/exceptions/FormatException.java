package client.app.exceptions;

public class FormatException extends Exception{
    private String errMsg;
    public FormatException(String field){
        switch(field){
            case "day": errMsg="Day must be Integer 1-7.";break;
            case "start": errMsg="Start time must be an Integer 0-24.";break;
            case "end": errMsg="End tim emust be an Integer 0-24."; break;
        }
    }
    public String getMsg(){return errMsg;}
}
