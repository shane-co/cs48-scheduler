package client.app.obj;

//Imports
import client.app.obj.ScheduleEvent;
import client.app.obj.Schedule;
import client.app.obj.Database;
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

    //Functions to retrieve from instance variables;

    //Functions to modify instsance variables;
}
