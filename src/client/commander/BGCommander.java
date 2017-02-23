package client.commander;

//Imports
import client.app.Client;
import client.app.exceptions.ElementNotFoundException;
import client.app.exceptions.UserLoggedInException;
import client.app.exceptions.UserNotFoundException;
import client.app.obj.ScheduleEvent;
import client.app.obj.User;
import client.app.obj.Schedule;
import client.view.UserInterface;
import client.commander.ScheduleGenerator;

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
        gen = new ScheduleGenerator(myEventArray());
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

    //TEMPORARY METHOD TO BE REPLACED
    public ArrayList<ScheduleEvent> demoArray(){
        ArrayList<ScheduleEvent> Events = new ArrayList<ScheduleEvent>();
        Events.add(new ScheduleEvent(2,1700, 2100, "dung", "ID1"));
        Events.add(new ScheduleEvent(12, 800, 11, "A good ol' morning at the beach","ID1"));
        Events.add(new ScheduleEvent(12, 1200, 2, "Beach clean-up","ID1"));
        Events.add(new ScheduleEvent(15, 800, 1100, "dope","ID1"));
        Events.add(new ScheduleEvent(15,1200, 1900, "superdope","ID1"));
        Events.add(new ScheduleEvent(4,1100, 1300, "Koala","ID1"));
        Events.add(new ScheduleEvent(7,500, 700, "biggie","ID1"));
        Events.add(new ScheduleEvent(27,800, 1000, "smalls","ID1"));
        return Events;

    }

    /**
     * calls upon the client to get User's eventArray
     * @return myEvents
     */
    public ArrayList<ScheduleEvent> myEventArray(){
        return client.getUserEvents();
    }



    /**
    *Function to generate a list of Schedule objects to return to UserInterface for display.
    *Uses internal ScheduleGenerator object to perform schedule generation algorithm and uses
    *Client to get myEvents from the current User
    *@return ArrayList containing all Schedule objects obtained by Events subscribed to by the current User
    */
    public ArrayList<Schedule> genSchedule(){
        //STUB
        return new ArrayList<Schedule>();
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
    	String input = String.format("%s;%get", orgName);
    	client.parseRequest(input);
    }

    
    
    /**
    *Function to set the currentUser variable in Client. Uses Client to verify that credentials are present
    *and valid on local database. Modifies Client to update currentUser
     * @throws UserLoggedInException 
     * @throws ElementNotFoundException 
    */
    public void login(String username, String password) throws ElementNotFoundException, UserLoggedInException{
    	client.setCurrUser(username, password);
    }

    
    
    /**
    *Function to add a User to the application.
    *@param uname String representing the User's username
    *@param pword String representing the User's password
     * @throws UserLoggedInException 
    */
    public void addUser(String username, String pword) throws UserLoggedInException{
    	//check to see if username is registered
    	User newUser = new User();
    	newUser.setPassword(pword);
    	newUser.setUsername(username);
    	client.addUser(newUser);
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
    public void subscribeEvent(String id, int day, int starthr, int endhr, String desc) throws UserNotFoundException, ElementNotFoundException{
    	ScheduleEvent event = new ScheduleEvent(day, starthr, endhr, desc, id);
    	client.subscribe(event);
    	
    }

    
    
    /**
    *Function to unsubscribe a logged in User to a ScheduleEvent. Creates a null ScheduleEvent with only id
    *@param id a string identifier equal to the identifier of the Schedule to be deleted.
     * @throws ElementNotFoundException 
     * @throws UserNotFoundException 
    */
    public void unsubscribe(String id) throws UserNotFoundException, ElementNotFoundException{
    	ScheduleEvent event = new ScheduleEvent(0, 0, 0, null, id);
    	client.unsubscribe(event);
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
