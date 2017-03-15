package client.app;

//Imports
import client.app.obj.User;
import client.app.obj.ScheduleEvent;
import client.app.obj.*;
import client.app.obj.Database;
import client.app.exceptions.*;
//networking
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
//IO
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import javax.xml.transform.TransformerException;
//ArrayList
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;

/**
*Class representing the Client application. Holds all object data and maintains the state of the Application.
*Interacts with Database object and manages conversions between Java Objects and mySQL database entries.
*Serves as applciation server to remote instances of application running.
*/
public class Client{
    //instance variables
    private User currUser; //User currently using the application
    private Database local; //Collection of all users that have used this application and their application data.
    //private ArrayList<User> hostedUsers;
    //networking information
    private static final int port = 7777;
    ServerSocket ss;
    Socket s;
    private boolean pub; //default value false.

    public Client(){
		currUser = null;
        try{ss = new ServerSocket(port);}catch(IOException e){e.printStackTrace();}
        local = new Database();
        setPublic();
    }

    /**
    *Function to determine whether Client has a User logged in yet or not.
    */
    public boolean isActive(){
        return (currUser!=null);
    }
    /**
    *Function to set the currUser variable. Queries LOCAL Database with login credentials. Throws Exception if
    *if login credentials are invalid.
    */
    public void setCurrUser(String uname, String pw) throws ElementNotFoundException, UserLoggedInException, LoginFailedException{
		if(currUser!=null) throw new UserLoggedInException();
		if(local.verifyCredentials(uname, pw)){
			currUser = new User();
			currUser.load(local.findUser("user",uname));
		}
        else throw new LoginFailedException();
	}
    /**
    *Function to setCurrentUser to null. Writes currUser state to Database.
    */
    public void logout() throws TransformerException{
        if(currUser!=null){
            local.writeToFile();
            currUser=null;
        }
    }

	/**
 	*Function to add User object to local Database. Allows user to sign in with registered credentials
	*@param u instantiated User object to be added to Database.
 	*/
	public void addUser(User u){
		local.addUser(u.record(local.getDocument()));
	}



    /**
    *Function to add an event to currUser.myEvents. Queries the Database found at DatabaseConnection db. Effectively completes the "subscription" process of the currUser to this Event.
    *@param e ScheduleEvent object that the currUser is subcribed to.
    */
    public void subscribe(ScheduleEvent e) throws UserNotFoundException, ElementNotFoundException{
        if(currUser==null)throw new UserNotFoundException();
        //update the currUser to include subscription.
		currUser.addToMyEvents(e);
		//update the local database to reflect new change.
		local.modifyUser(currUser.getUsername(), true, "myEvents", e.record(local.getDocument()));
	}

    /**
    *Function to delete an event from currUser.myEvents. Effectively completes the "un-subscription" process of the currUser to this Event.
    *@param e ScheduleEvent object that the currUser is unsubscribed to.
    */
    public void unsubscribe(ScheduleEvent e) throws ElementNotFoundException{
        if(currUser==null)throw new UserNotFoundException();
	//update the currUser to remove subscription
		currUser.removeFromMyEvents(e);
		//update the local database to reflect the change
		local.modifyUser(currUser.getUsername(), false, "myEvents", e.record(local.getDocument()));
	}

    /**
    *Function to add a Schedule to User.mySchedules
    *@param s Schedule object to be added to currUser.
    */
    public void addSchedule(Schedule s)throws ElementNotFoundException{
        if(currUser==null)throw new UserNotFoundException();
        //update the currUser to include schedule.
		currUser.addToMySchedules(s);
		//update the local database to reflect new change.
		local.modifyUser(currUser.getUsername(), true, "mySchedules", s.record(local.getDocument()));
	}
    /**
    *Function to delete a Schedule to User.mySchedules
    *@param s Schedule object to be deleted to currUser.
    */
    public void deleteSchedule(Schedule s)throws ElementNotFoundException{
        if(currUser==null)throw new UserNotFoundException();
        //update the currUser to include schedule.
		currUser.removeFromMySchedules(s);
		//update the local database to reflect new change.
		local.modifyUser(currUser.getUsername(), false, "mySchedules", s.record(local.getDocument()));
	}

    /**
    *Function to add an organization to currUser.myOrgs and allow Client to connect to it. Places newOrg into orgs.
    *@param newOrg a DatabaseConnection object representing the data store connection hosted by the organization
    */
    public void registerOrg(DatabaseConnection newOrg)throws ElementNotFoundException{
        if(currUser==null)throw new UserNotFoundException();
        //update the currUser to include schedule.
		currUser.addDatabaseConnection(newOrg);
		//update the local database to reflect new change.
		local.modifyUser(currUser.getUsername(), true, "myOrgs", newOrg.record(local.getDocument()));
	}

    /**
    *Function to remove an organization from currUser.myOrgs.
    *@param org a DatabaseConnection object representing the data store connection hosted by the organization.
    */
    public void forgetOrg(DatabaseConnection org)throws ElementNotFoundException{
        if(currUser==null)throw new UserNotFoundException();
		//update the currUser to include schedule.
		currUser.removeDatabaseConnection(org);
		//update the local database to reflect new change.
		local.modifyUser(currUser.getUsername(), false, "myOrgs", org.record(local.getDocument()));
    }
    /**
    *Function to add a ScheduleEvent to currUser.myHostedEvents. Makes a ScheduleEvent available to be subscibed to.
    *@param e ScheduleEvent object that the User has created.
    */
    public void createEvent(ScheduleEvent e)throws ElementNotFoundException{
        if(currUser==null)throw new UserNotFoundException();
        //update the currUser to include schedule.
		currUser.addHostedEvent(e);
		//update the local database to reflect new change.
		local.modifyUser(currUser.getUsername(), true, "myHostedEvents", e.record(local.getDocument()));
	}
    /**
    *Function to remove a ScheduleEvent from currUser.myHostedEvents.
    *@param e ScheduleEvent object that the User has created.
    */
    public void deleteEvent(ScheduleEvent e)throws ElementNotFoundException, UserNotFoundException{
		//update the currUser to include schedule.
		currUser.removeHostedEvent(e);
		//update the local database to reflect new change.
		local.modifyUser(currUser.getUsername(), false, "myHostedEvents", e.record(local.getDocument()));
	}


    //currUser accessor functions
    public ArrayList<ScheduleEvent> getUserEvents() throws UserNotFoundException{
        if(currUser!=null)return currUser.getMyEvents();
        else throw new UserNotFoundException();
    }

    public ArrayList<Schedule> getUserSchedules() throws UserNotFoundException{
	 if(currUser != null) return currUser.getMySchedules();
	 else throw new UserNotFoundException();
    }

    public ArrayList<DatabaseConnection> getUserOrgs() throws UserNotFoundException{
        if(currUser != null) return currUser.getMyOrgs();
        else throw new UserNotFoundException();
    }

    public ArrayList<ScheduleEvent> getUserHosted() throws UserNotFoundException{
        if(currUser!=null) return currUser.getMyHostedEvents();
        else throw new UserNotFoundException();
    }

    public String getUserNextAvailableSchedID() throws UserNotFoundException{
        if(currUser != null) return currUser.nextSchedID();
        else throw new UserNotFoundException();
    }

    /*
    *Function to exit application cleanly. Tells Database to write current DOM to file.
    */
    public void exitApp() throws TransformerException{
        local.writeToFile();
    }
    //----------------------------SERVER/CLIENT FUNCTIONALITY---------------------------------------------
    /**
    *Function to set Client public and listen for requests
    */
    public void setPublic(){
        pub = true;
        Listener serve = new Listener();
        new Thread(serve).start();
    }
    /**
    *Function to set Client private and stop listening for requests
    */
    public void setPrivate(){pub=false;}

    public String sendRequest(String orgname){
        try{
            //retrieve connection information to orgname
            ArrayList<DatabaseConnection> orgs = currUser.getMyOrgs();
            DatabaseConnection remote = orgs.get(orgs.indexOf(new DatabaseConnection(orgname,"",0)));
            Socket organization = new Socket(remote.getIP(),remote.getPort());
            //send orgname over the network to remote server
            DataOutputStream out= new DataOutputStream(organization.getOutputStream());
            out.writeUTF(orgname);
            //retrieve server response
            DataInputStream in = new DataInputStream(organization.getInputStream());
            return in.readUTF();
        }catch(IOException i){return "";}
    }
    /**
    *Inner Listener class to listen for requests on port 7777. Handles server functionality.
    */
    class Listener implements Runnable{
        private DataInputStream dis;
        private DataOutputStream dos;
        public void run(){
            while(pub){
                try{
                    s = ss.accept();
                    dis = new DataInputStream(s.getInputStream());
                    String request = dis.readUTF();
                    parseRequest(request);
                }catch(IOException e){ }
            }
        }

        public void parseRequest(String orgName){ //requests come in the form ORGNAME which is equivalent to DatabaseConnection.id and the user being queried
            try{
                dos = new DataOutputStream(s.getOutputStream());
                User queried = new User(local.findUser("user",orgName));
                String result = "";
                for(ScheduleEvent h : queried.getMyHostedEvents()){
                    result+=h.toString()+"%";
                }
                dos.writeUTF(result);
            } catch (ElementNotFoundException e){
                try{dos.writeUTF("NONE");}catch(IOException io){}
            }
            catch (IOException i){}
        }

    }
}
