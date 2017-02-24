package client.commander;

import client.app.obj.ScheduleEvent;
import client.app.obj.Schedule;
import client.app.Client;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import client.app.Client;
import client.commander.BGCommander;
public class ScheduleGenerator{
/**
* The class generates a schedule from client's getUserEvents, using (for now, seeing if it will work)
* a variation of the greedy algorithm in which the earliest scheduleEvent start time is used, will
* return an ArrayList of all possible combinations of events
  */
	private ArrayList<ScheduleEvent> theEvents;
	private ArrayList<Schedule> theSchedules;
	private int myEventsSize;

	//constructor
	public ScheduleGenerator(ArrayList<ScheduleEvent> events){
		theEvents = events;
		myEventsSize = theEvents.size();
		theSchedules = new ArrayList<Schedule>();
	}

	// checks if two ScheduleEvent's times collide
	private boolean collision(ScheduleEvent ev1, ScheduleEvent ev2){
		if(ev1.when_to_end() < ev2.when_to_start())
			return false;
		return true;
	}

	//Arranges theEvents in order of their finish time
	private void Sort(){
		ArrayList<ScheduleEvent> theSortedEvents = new ArrayList<ScheduleEvent>();
	for (int j = 0; j < myEventsSize; j++) {
		int earliestFinish = 2500;
		int earliestIndex;
		for(int i = 0; i< myEventsSize; i++){
			if(earliestFinish > theEvents.get(i).when_to_end() ){
				earliestFinish = theEvents.get(i).when_to_end();
				earliestIndex = i;
			}
			}
		theSortedEvents.add(theEvents.get(earliestIndex));
		theEvents.get(earliestIndex).set_start(2500);
		}
	theEvents.clear();
	for(ScheduleEvent temp : theSortedEvents)
		theEvents.add(temp);
	}

	/**
	 * start with the candidates, choose the earliest interval, then remove the earliest interval
	 * along with any other interval that collides with chosen interval. Repeat until candidates is empty.
	 * produces an ArrayList<Schedule> theSchedules
	 */

	private void Greedy(){
		ArrayList<ScheduleEvent> candidates =new ArrayList<ScheduleEvent>();
		ArrayList<ScheduleEvent> possibleSchedule = new ArrayList<ScheduleEvent>();
		for( int i = 0; i < myEventsSize; i++){
			candidates.add(theEvents.get(i));
		}


		while(candidates.size() != 0){
			ScheduleEvent interval = candidates.get(0);
			candidates.remove(0);
			while(candidates.size() != 0){
				if(collision(interval, candidates.get(0)) == true)
					candidates.remove(0);
				else
					break;
			}
			possibleSchedule.add(interval);
			}
		theSchedules.add(new Schedule(possibleSchedule, "identifier",1,1));


	}

	/**
	 * You will only call this method in BGCommander to return the arraylist of schedules
	 * @return the optimal schedule of events,
	 */
	public ArrayList<Schedule> getSchedules(){
		sort();
		greedy();
		return theSchedules;
	}

}
