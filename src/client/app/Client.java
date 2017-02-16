package client.app;

//Imports
import client.app.obj.User;
import client.app.obj.ScheduleEvent;
import client.app.obj.Database;
//networking
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
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
        ss = new ServerSocket(port);
        local = new Database();
        pub = false;
    }
    /**
    *Function to set the currUser variable. Queries LOCAL Database with login credentials. Throws Exception if
    *if login credentials are invalid.
    */
    public void setCurrUser(String uname, String pw) throws ElementNotFoundException, UserLoggedInException{
		if(currUser!=null) throw new UserLoggedInException();
		if(verifyCredentials(uname, pw)){
			currUser = new User();
			currUser.load(local.findElement("user",uname));
		}
	}
	
	/**
 	*Function to add User object to local Database. Allows user to sign in with registered credentials
	*@param u instantiated User object to be added to Database.
 	*/ 
	public void addUser(User u){
		local.addUser(u.record());
	}

    /**
    *Function to add an event to currUser.myEvents. Queries the Database found at DatabaseConnection db. Effectively completes the "subscription" process of the currUser to this Event.
    *@param e ScheduleEvent object that the currUser is subcribed to.
    */
    public void subscribe(ScheduleEvent e) throws UserNotFoundException, ElementNotFoundException{
		//update the currUser to include subscription.
		currUser.addToMyEvents(e);
		//update the local database to reflect new change.
		local.modifyUser(currUser.getUsername(), true, "myEvents", e.record());	
	}

    /**
    *Function to delete an event from currUser.myEvents. Effectively completes the "un-subscription" process of the currUser to this Event.
    *@param e ScheduleEvent object that the currUser is unsubcribed to.
    */
    public void unsubscribe(ScheduleEvent e) throws UserNotFoundException, ElementNotFoundException{
		//update the currUser to remove subscription
		currUser.removeFromMyEvents(e);
		//update the local database to reflect the change
		local.modifyUser(currUser.getUsername(), false, "myEvents", e.record);
	}

    /**
    *Function to add a Schedule to User.mySchedules
    *@param s Schedule object to be added to currUser.
    */
    public void addSchedule(Schedule s){
		//update the currUser to include schedule.
		currUser.addToMySchedules(s);
		//update the local database to reflect new change.
		local.modifyUser(currUser.getUsername(), true, "mySchedules", s.record());	
	}
    /**
    *Function to delete a Schedule to User.mySchedules
    *@param s Schedule object to be deleted to currUser.
    */
    public void deleteSchedule(Schedule s){
		//update the currUser to include schedule.
		currUser.removeFromMySchedules(s);
		//update the local database to reflect new change.
		local.modifyUser(currUser.getUsername(), false, "mySchedules", s.record());	
	}

    /**
    *Function to add an organization to currUser.myOrgs and allow Client to connect to it. Places newOrg into orgs.
    *@param newOrg a DatabaseConnection object representing the data store connection hosted by the organization
    */
    public void registerOrg(DatabaseConnection newOrg){
		//update the currUser to include schedule.
		currUser.addToMyOrgs(newOrg);
		//update the local database to reflect new change.
		local.modifyUser(currUser.getUsername(), true, "myOrgs", newOrg.record());	
	}

    /**
    *Function to remove an organization from currUser.myOrgs.
    *@param org a DatabaseConnection object representing the data store connection hosted by the organization.
    */
    public void forgetOrg(DatabaseConnection org){
		//update the currUser to include schedule.
		currUser.removeFromMyOrgs(newOrg);
		//update the local database to reflect new change.
		local.modifyUser(currUser.getUsername(), false, "myOrgs", org.record());	
    } 
    /**
    *Function to add a ScheduleEvent to currUser.myHostedEvents. Makes a ScheduleEvent available to be subscibed to.
    *@param e ScheduleEvent object that the User has created.
    */
    public void createEvent(ScheduleEvent e){
		//update the currUser to include schedule.
		currUser.addToMyHostedEvents(e);
		//update the local database to reflect new change.
		local.modifyUser(currUser.getUsername(), true, "myHostedEvents", e.record());	
	}
    /**
    *Function to remove a ScheduleEvent from currUser.myHostedEvents.
    *@param e ScheduleEvent object that the User has created.
    */
    public void deleteEvent(ScheduleEvent e){
		//update the currUser to include schedule.
		currUser.removeFromMyHostedEvents(e);
		//update the local database to reflect new change.
		local.modifyUser(currUser.getUsername(), false, "myHostedEvents", e.record());	
	}



    //----------------------------SERVER FUNCTIONALITY---------------------------------------------
    /**
    *Function to set Client public and listen for requests
    */
    public void setPublic(){
        pub = true;
        try{
                Listener serve = new Listener();
                new Thread(serve).start();
        }catch(IOException e){
            e.printStackTrace();
        }

    }
    /**
    *Function to set Client private and stop listening for requests
    */
    public void setPrivate(){pub=false;}

    /**
    *Inner Listener class to listen for requests on port 7777. Handles server functionality.
    */
    class Listener implements Runnable{
        public void run(){
            while(pub){
                try{
                    s = ss.accept();
                    DataInputStream dis = new DataInputStream(s.getInputStream());
                    String request = dis.readUTF();
                    parseRequest(request);
                }catch(IOException e){ }
            }
        }

        public void parseRequest(String in){ //requests come in the form ORGNAME;%COMMAND;ARG COMMAND={get,search} ARG={filter:}
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            ArrayList<String> req = new ArrayList<String>(Arrays.asList(in.split(";"))); //split request into parseable list.
            ListIterator iter = req.listIterator();
            String result;
            String orgName = iter.next(); //get the name of the organization hosted in local to query.
            String r = iter.next();
            switch(r){
                case "%get": //returns all events from orgName
                    try{
                        result = local.outputSearchResultString(orgName, new ArrayList<String>);
                        dos.writeUTF(result);
                    }catch(UserNotFoundException e){dos.writeUTF("USER NOT FOUND");}
                    break;
                case "%search": //applies search filters.
                    try{
                        result = local.outputSearchResultString(orgName, parseFilters(iter.next()));
                        dos.writeUTF(result);
                    }catch(UserNotFoundException e){dos.writeUTF("USER NOT FOUND");}
                    break;
            }
        }

        public ArrayList<Filters> parseFilters(String in){}
    }
}
