package client.commander;

//Imports
import client.app.Client;

import client.app.obj.*;
import client.view.UserInterface;
import client.commander.ScheduleGenerator;
import client.app.exceptions.*;

import java.util.ArrayList;
import javax.xml.transform.TransformerException;


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
    *Passes myEvents through BGCommander method myEventArray
    */
    private BGCommander(){
        client = new Client();
    }

    /**
    *Static Factory method to initialize the Commander object. Use this instead of Constructor to ensure that there exists only
    *one BGCommander object.
    *@return BGCommander object
    */
    public static BGCommander getBGCommander(){
        if(command==null)
            command = new BGCommander();
        return command;
    }

    /**
    *Function to generate a list of Schedule objects to return to UserInterface for display.
    *Uses internal ScheduleGenerator object to perform schedule generation algorithm and uses
    *Client to get myEvents from the current User
    *@return ArrayList containing all Schedule objects obtained by Events subscribed to by the current User
    */
    public Schedule genSchedule(int month, int day) throws UserNotFoundException{
        gen = new ScheduleGenerator(client.getUserEvents());
        String id = client.getUserNextAvailableSchedID();
        return gen.getSchedule(id, month, day);
    }

    //Function to get a list of ScheduleEvents objects from current users return to UserInterface for display
    public ArrayList<ScheduleEvent> getScheduleEvents() throws UserNotFoundException{
        return client.getUserEvents();
    }

    //Function to get a list of schedules
    public ArrayList<Schedule> getSchedules() throws UserNotFoundException{
	 return client.getUserSchedules();
    }

    //Function to add a schdule to array
    public void addSchedule(Schedule s) throws ElementNotFoundException{
	  client.addSchedule(s);
    }
    //Function to delete a schedule from user array
    public void deleteSchedule(Schedule s)throws ElementNotFoundException{
	    client.deleteSchedule(s);
    }

    /**
    *Function to generate a list of ScheduleEvent objects to return to UserInterface for display.
    *Uses Client to retrieve ScheduleEvent objects according to search filters, from another Organization
    *@param orgName A string corresponding to the Organization name to search through
    *@param -- Lets leave this for the second iteration, for now just return all the events --
    *filters A set of filters to apply when searching for a ScheduleEvent
    */
    public ArrayList<ScheduleEvent> search(String orgName/*, ArrayList<String> filters*/){
    	//CALL client parseRequest, still needs implementation of database.outputSearchResultString ignore filter
    	//String input = String.format("%s;%get", orgName);
    	//client.parseRequest(input);
        return new ArrayList<ScheduleEvent>();
        //remoteApplication.retrieveAllHostedEvents();
    }

    /**
    *Function to set the currentUser variable in Client. Uses Client to verify that credentials are present
    *and valid on local database. Modifies Client to update currentUser
     * @throws UserLoggedInException
     * @throws ElementNotFoundException
    */
    public void login(String username, String password) throws ElementNotFoundException, UserLoggedInException{
            client.setCurrUser(username,password);
    }

    /**
    *Function to add a User to the application.
    *@param uname String representing the User's username
    *@param pword String representing the User's password
     * @throws UserLoggedInException
    */
    public void addUser(String username, String pword){
        User new_user = new User();
		new_user.setUsername(username);
		new_user.setPassword(pword);
        client.addUser(new_user);
    }

    /**
    *Function to subscribe a logged in User to a ScheduleEvent. Creates a ScheduleEvent object and passes that
    *to Client for further processing.
    *@param id A string that identifies this ScheduleEvent. Could be a id number, a name, or any other identifying information
    *@param day An integer ranging from 1-7 representing a day of the week
    *@param starthr, integer ranging from 0 - 2400 representing the start time of the event
    *@param endhr An integer ranging from 0 - 2400 representing the end time of an event
    * @throws ElementNotFoundException
    * @throws UserNotFoundException
    */
    public void subscribeEvent(String id, String day, String starthr, String endhr, String desc)throws FormatException,ElementNotFoundException{
        int d = Integer.parseInt(day); int s = Integer.parseInt(starthr); int e =Integer.parseInt(endhr);
        if(d>7||d<1)throw new FormatException("day");
        else if(s>24||s<0) throw new FormatException("start");
        else if(e>24||e<0) throw new FormatException("end");
        ScheduleEvent event = new ScheduleEvent(new ArrayList<Dependencies>(), new ArrayList<TimeBlock>(), "", id);
        client.subscribe(event);
    }

    /**
    *Function to unsubscribe a logged in User to a ScheduleEvent. Creates a null ScheduleEvent with only id
    *@param id a string identifier equal to the identifier of the Schedule to be deleted.
     * @throws ElementNotFoundException
     * @throws UserNotFoundException
    */
    public void unsubscribe(String id){
       ScheduleEvent event = new ScheduleEvent(new ArrayList<Dependencies>(), new ArrayList<TimeBlock>(), "", id);
       try{
           client.unsubscribe(event);
       }catch(ElementNotFoundException e){}
    }

    /**
    *Function to exit the application cleanly. Tells Client to exit application and write application state to file.
    */
    public void exitApp(){
        try{
            client.exitApp();
        }catch(TransformerException t){t.printStackTrace();}
    }
}
