package client.commander;

//Imports
import client.app.Client;
import client.app.obj.ScheduleEvent;
import client.view.UserInterface;
import client.app.obj.ScheduleGenerator;

/*
*Class BackGround Commander acting as the Controller in the MVC Design Pattern. Responsible for changing the state of the application
* and coordinating method calls between UserInterface and the rest of the application.
*/
public class BGCommander{
    //instance variables
    private Client client;
    private ScheduleGenerator gen;
    private static BGCommander command;

    /*
    *BGCommander Constructor. DO NOT USE TO INSTANTIATE BGCommander. Instead use getBGCommander() function.
    */
    private BGCommander(){
        client = new Client();
        gen = new ScheduleGenerator();
    }

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
    *Function to generate a list of Schedule objects to return to UserInterface for display.
    *Uses internal ScheduleGenerator object to perform schedule generation algorithm and uses
    *Client to get myEvents from the current User
    *@return ArrayList containing all Schedule objects obtained by Events subscribed to by the current User
    */
    public ArrayList<Schedule> genSchedule(){}

    /**
    *Function to generate a list of ScheduleEvent objects to return to UserInterface for display.
    *Uses Client to retrieve ScheduleEvent objects according to search filters, from another Organization
    *@param orgName A string corresponding to the Organization name to search through
    *@param filters A set of filters to apply when searching for a ScheduleEvent
    */
    public ArrayList<ScheduleEvent> search(String orgName, ArrayList<String> filters){}

    /**
    *Function to set the currentUser variable in Client. Uses Client to verify that credentials are present
    *and valid on local database. Modifies Client to update currentUser
    */
    public void login(String username, String password){}
}
