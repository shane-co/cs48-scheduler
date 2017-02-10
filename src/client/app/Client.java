package client.app;

//Imports
import client.app.obj.User;
import client.app.obj.ScheduleEvent;

/**
*Class representing the Client application. Holds all object data and maintains the state of the Application.
*Interacts with Database object and manages conversions between Java Objects and mySQL database entries.
*/
public class Client{
    //instance variables
    private User currUser; //User currently using the application
    private ArrayList<Database> orgs; //All known REMOTE databases (Called: organizations)
    private ArrayList<ScheduleEvent> myEvents; //List of all ScheduleEvent that the User has subscribed to.
    private ArrayList<ScheduleEvent> myHostedEvents; //list of all ScheduleEvent that the User has created. These are meant for other people to subscribe to.

    /*
    *Function to set the currUser variable. Queries Database associated with orgName at currOrg with login credentials. Throws Exception if
    *no organization associated with orgName exists or if login credentials are invalid.
    */
    public void setCurrUser(String uname, String pw, String orgName){}

    /*
    *Function to set the currOrg variable. Queries orgs ArrayList to find a matching Database object with the same name.
    *Throws Exception if no organization with name orgName exists in orgs.
    *@param orgName String of the organization name to set currOrg to.
    */
    public void setCurrOrg(String orgName){}

    /*
    *Function to add an organization to orgs and allow Client to connect to it. Places newOrg into orgs.
    *@param newOrg a Database object representing the mySQL connection hosted by the organization
    */
    public void registerOrg(Database newOrg){}

    /*
    *Function to add an event to myEvents. Effectively completes the "subscription" process of the currUser to this Event.
    *@param e ScheduleEvent object that the currUser is subcribed to.
    */
    public void addEvent(ScheduleEvent e){}

    /*
    *Function to delete an event to myEvents. Effectively completes the "un-subscription" process of the currUser to this Event.
    *@param e ScheduleEvent object that the currUser is unsubcribed to.
    */
    public void deleteEvent(ScheduleEvent e){}

    /*
    *Function to add a ScheduleEvent to myHostedEvents. Makes a ScheduleEvent available to be subscibed to.
    *@param e ScheduleEvent object that the User has created.
    */
    public void createEvent(ScheduleEvent e){}

    /*
    *Function to retrieve all ScheduleEvent objects from organization associate with orgName that fits the current filters
    *@param orgName Name of organization to query. Will retrieve Database object from orgs associated with orgName
    *@param filters ArrayList<String> of filters to apply to the search results.
    */
    public ArrayList<ScheduleEvent> search(String orgName, ArrayList<String> filters){}
}
