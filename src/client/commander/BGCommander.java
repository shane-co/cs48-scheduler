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
    private ArrayList<ScheduleEvent> remoteList;
    private static BGCommander command;

    /*
    *BGCommander Constructor. DO NOT USE TO INSTANTIATE BGCommander. Instead use getBGCommander() function.
    *Passes myEvents through BGCommander method myEventArray
    */
    private BGCommander(){
        client = new Client();
        remoteList=new ArrayList<ScheduleEvent>();
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
    public ArrayList<String> getScheduleEvents() throws UserNotFoundException{
        ArrayList<String> display = new ArrayList<String>();
        for(ScheduleEvent e:client.getUserEvents()){
            display.add(e.get_ID());
        }
        return display;
    }

    public String getEventInfo(String evid)throws UserNotFoundException{
        String evInfo ="";
        for(ScheduleEvent e:client.getUserEvents()){
            if(e.get_ID().equals(evid)){
                evInfo="ID: " + evid +"\n"+"Description: " + e.get_descpt() + "\n"+"Times: " + e.getDurationString();
            }
        }
        return evInfo;
    }

    public String getHEventInfo(String evid)throws UserNotFoundException{
        String evInfo ="";
        for(ScheduleEvent e:client.getUserHosted()){
            if(e.get_ID().equals(evid)){
                evInfo="ID: " + evid +"\n"+"Description: " + e.get_descpt() + "\n"+"Times: " + e.getDurationString();
            }
        }
        return evInfo;
    }

    public String getRemoteEventInfo(String evid){
        String evInfo ="";
        for(ScheduleEvent e:this.remoteList){
            if(e.get_ID().equals(evid)){
                evInfo="ID: " + evid +"\n"+"Description: " + e.get_descpt() + "\n"+"Times: " + e.getDurationString();
            }
        }
        return evInfo;
    }
    public ArrayList<String> getHosted() throws UserNotFoundException{
        ArrayList<String> display = new ArrayList<String>();
        for(ScheduleEvent e:client.getUserHosted()){
            display.add(e.get_ID());
        }
        return display;
    }
    //Function to get a list of schedules
    public ArrayList<String> getSchedules() throws UserNotFoundException{
        ArrayList<String> display = new ArrayList<String>();
        for(Schedule s:client.getUserSchedules()){
            display.add(s.getID());
        }
        return display;
    }

    public Schedule getSchedToDisplay(String schedid)throws UserNotFoundException{
        for(Schedule s:client.getUserSchedules()){
            if(s.getID().equals(schedid))return s;
        }
        return null;
    }

    public ArrayList<String> getOrgNames() throws UserNotFoundException{
        ArrayList<String> display = new ArrayList<String>();
        for(DatabaseConnection o:client.getUserOrgs()){
            display.add(o.getID());
        }
        return display;
    }

    public ArrayList<DatabaseConnection> getOrgs() throws UserNotFoundException{
        return client.getUserOrgs();
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
    public ArrayList<String> search(String orgName){
        ArrayList<String> display = new ArrayList<String>();
        String resultString = client.sendRequest(orgName);
        String[] results = resultString.split("%");
        for(String s:results){
            if(!s.equals("")){
                ScheduleEvent e = new ScheduleEvent(s);
                display.add(s.split(":")[1].split(";")[0]);
                remoteList.add(e);
            }
        }
        return display;
    }

    /**
    *Function to set the currentUser variable in Client. Uses Client to verify that credentials are present
    *and valid on local database. Modifies Client to update currentUser
     * @throws UserLoggedInException
     * @throws ElementNotFoundException
    */
    public void login(String username, String password) throws ElementNotFoundException, UserLoggedInException, LoginFailedException{
            client.setCurrUser(username,password);
    }

    /**
    *Function to logout the currentUser in Client and set currentUser to null
    */
    public void logout(){
        try{
            client.logout();
        }catch(TransformerException t){System.out.println("Couldn't write to File");}
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
    public void subscribeEvent(String id)throws ElementNotFoundException{
        ScheduleEvent event = new ScheduleEvent(new ArrayList<Dependencies>(), new ArrayList<TimeBlock>(), "", id);
        if(remoteList.contains(event))client.subscribe(remoteList.get(remoteList.indexOf(event)));
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

    // command.createHostedEvent()
    public void createHostedEvent(ArrayList<ArrayList<Integer>> duration, String id, String desc) throws ElementNotFoundException{
        ArrayList<TimeBlock> evduration = new ArrayList<TimeBlock>();
        for(int i=0; i<7; i++){
            for(int j: duration.get(i)){
                if(j>0){
                    evduration.add(new TimeBlock(i,j));
                }
            }
        }
        ScheduleEvent newev = new ScheduleEvent(new ArrayList<Dependencies>(), evduration, desc, id);
        client.createEvent(newev);
    }

    public void deleteFromField(String id, String field){
        try{
            switch(field){
                case "event": ScheduleEvent event = new ScheduleEvent(new ArrayList<Dependencies>(), new ArrayList<TimeBlock>(), "", id);
                    client.unsubscribe(event);
                    break;
                case "hosted": ScheduleEvent hosted = new ScheduleEvent(new ArrayList<Dependencies>(), new ArrayList<TimeBlock>(), "", id);
                    client.deleteEvent(hosted);
                    break;
                case "org": DatabaseConnection d = new DatabaseConnection(id,"",0);
                    client.forgetOrg(d);
                    break;
            }
        }catch(ElementNotFoundException e){}
    }

    public void addOrganization(String id, String ip){
	DatabaseConnection d = new DatabaseConnection(id,ip,7777);
	try{
	    client.registerOrg(d);
	   }catch(ElementNotFoundException e){}
    }


    public void setNetworkDiscoverable(){client.setPublic();}
    public void unsetNetworkDiscoverable(){client.setPrivate();}

    /**
    *Function to exit the application cleanly. Tells Client to exit application and write application state to file.
    */
    public void exitApp(){
        try{
            client.exitApp();
        }catch(TransformerException t){t.printStackTrace();}
    }
}
