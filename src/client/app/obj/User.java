package client.app.obj;

//Imports
import client.app.obj.ScheduleEvent;
import client.app.obj.Schedule;
import client.app.obj.Database;
import java.util.Iterator;
import client.app.interfaces.Recordable;
import java.util.ArrayList;

/**
*Class representing a user using the Application. Maintains all data about the user and their
*saved data after using the application. It's state is kept up to date in the LOCAL Database by the
*Client
*/
public class User implements Recordable{
    //instance variables
    private ArrayList<Schedule> mySchedules;
    private ArrayList<ScheduleEvent> myHostedEvents;
    private ArrayList<ScheduleEvent> myEvents;
    private ArrayList<DatabaseConnection> myOrgs;
    
    public User(){
        mySchedules = new ArrayList<Schedule>();
      myHostedEvents = new ArrayList<ScheduleEvent>();
      myEvents = new ArrayList<ScheduleEvent>();
      myOrgs = new ArrayList<DatabaseConnection>();
    }
  //Functions to add to instance variables;
    
    public void addSchedule(Schedule s){
    	this.mySchedules.add(s);
    }
    
    public void addHostedEvent(ScheduleEvent s){
    	this.myHostedEvents.add(s);
    }
    
    public void addScheduleEvent(ScheduleEvent s){
    	this.myEvents.add(s);
    }
    
    public void addDatabaseConnection(DatabaseConnection d){
    	this.myOrgs.add(d);
    }
    
    
    //Functions to remove from instance variables;
    public void removemySchedule(Schedule s){
    	Iterator<Schedule> iter = mySchedules.iterator();
    	while (iter.hasNext()){
    		Schedule ss = iter.next();
    		if (s == ss)
    		{
    	    	this.mySchedules.remove(s);

    		}
    	}
    }
    
    public void removeScheduleEvent(ScheduleEvent s){
    	Iterator<ScheduleEvent> iter = myEvents.iterator();
    	while (iter.hasNext()){
    		ScheduleEvent ss = iter.next();
    		if (s == ss)
    		{
    	    	this.myEvents.remove(s);

    		}
    	}
    }
    
    public void removemyHostedEvents(ScheduleEvent s){
    	Iterator<ScheduleEvent> iter = myHostedEvents.iterator();
    	while (iter.hasNext()){
    		ScheduleEvent ss = iter.next();
    		if (s == ss)
    		{
    	    	this.myHostedEvents.remove(s);

    		}
    	}
    }
    
    public void removeDatabaseConnection(DatabaseConnection d){
    	Iterator<DatabaseConnection> iter = myOrgs.iterator();
    	while (iter.hasNext()){
    		DatabaseConnection ss = iter.next();
    		if (d == ss)
    		{
    	    	this.myOrgs.remove(d);

    		}
    	}
    }
  
    
}
