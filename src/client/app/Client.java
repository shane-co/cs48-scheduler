package client.app;

//Imports
import client.app.obj.User;
import client.app.obj.ScheduleEvent;
import client.app.obj.Database;

/**
*Class representing the Client application. Holds all object data and maintains the state of the Application.
*Interacts with Database object and manages conversions between Java Objects and mySQL database entries.
*/
public class Client{
    //instance variables
    private User currUser; //User currently using the application
    private Database local;
    private ArrayList<User> hostedUsers; //Collection of all users that have used this application.

    /*
    *Function to set the currUser variable. Queries LOCAL Database with login credentials. Throws Exception if
    *if login credentials are invalid.
    */
    public void setCurrUser(String uname, String pw){}

    /*
    *Function to add an organization to currUser.myOrgs and allow Client to connect to it. Places newOrg into orgs.
    *@param newOrg a Database object representing the mySQL connection hosted by the organization
    */
    public void registerOrg(Database newOrg){}

    /*
    *Function to add an event to currUser.myEvents. Effectively completes the "subscription" process of the currUser to this Event.
    *@param e ScheduleEvent object that the currUser is subcribed to.
    */
    public void subscribe(ScheduleEvent e){}

    /*
    *Function to delete an event from currUser.myEvents. Effectively completes the "un-subscription" process of the currUser to this Event.
    *@param e ScheduleEvent object that the currUser is unsubcribed to.
    */
    public void unsubscribe(ScheduleEvent e){}

    /*
    *Function to add a ScheduleEvent to currUser.myHostedEvents. Makes a ScheduleEvent available to be subscibed to.
    *@param e ScheduleEvent object that the User has created.
    */
    public void createEvent(ScheduleEvent e){}

    /*
    *Function to remove a ScheduleEvent from currUser.myHostedEvents.
    *@param e ScheduleEvent object that the User has created.
    */
    public void deleteEvent(ScheduleEvent e){}

    /*
    *Function to retrieve all ScheduleEvent objects from organization associate with orgName that fits the current filters
    *Will use currUser to find orgName in currUser.myOrgs and find ScheduleEvents.
    *@param orgName Name of organization to query. Will retrieve Database object from orgs associated with orgName
    *@param filters ArrayList<String> of filters to apply to the search results.
    */
    public ArrayList<ScheduleEvent> search(String orgName, ArrayList<String> filters){}

    /*
    *Function to write the state of the currentUser to Database local.
    */
    public void writeToDB(){}
}
