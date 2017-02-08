package client.commander;

#Imports
import client.app.Client;
import client.app.ScheduleEvent;
import client.view.UserInterface;

/*
*Class BackGround Commander acting as the Controller in the MVC Design Pattern. Responsible for changing the state of the application
* and coordinating method calls between UserInterface and the rest of the application.
*/
public class BGCommander{
    private Client client;
    private ScheduleGenerator gen;
    private static BGCommander command;
    
    /**
    *Static Factory method to initialize the Commander object. Use this instead of Constructor to ensure that there exists only
    *one BGCommander object.
    *@return BGCommander object
    */
    public static BGCommander getBGCommander(){
        if(command==NULL)
            command = new BGCommander();
        return command;
    }
    
    /**
    *Function to generate a Schedule object to return to UserInterface for display. Uses internal ScheduleGenerator object to
    *perform schedule generation algorithm and uses Client to get Events[] from the current User
    *@return Array containing all Schedule objects obtained by Events subscribed to by the current User
    */
    public Schedule [] genSchedule(){}
    
    public ScheduleEvent [] search(String [] filters){}
}
