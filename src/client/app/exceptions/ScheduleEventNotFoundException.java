package client.app.exceptions;
public class ScheduleEventNotFoundException extends ElementNotFoundException{
    public ScheduleEventNotFoundException(){
        errMsg="Schedule does not exist in Database.";
    }
}
