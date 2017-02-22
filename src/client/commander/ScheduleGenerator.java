package client.commander;

import 
public class ScheduleGenerator{
/**
* The class generates a schedule 
*/
	private ArrayList<ScheduleEvent> theEvents;
	private ArrayList<ScheduleEvent> theSortedEvents;
	private ArrayList<ArrayList<ScheduleEvent>> listOLists = new ArrayList<ArrayList<ScheduleEvent>>();
	private myEventsSize;
	
	public ScheduleGenerator(){
		theEvents = client.getUserEvents();
		myEventsSize = theEvents.size()
	}
	
	private boolean collision(ScheduleEvent ev1, ScheduleEvent ev2){
		if(ev1.whenToEnd() <= ev2.)
			return false;
		else
			return true;
	}
	
	
	for (int j = 0; j < myEventsSize; j++) {
		int shortestStart = -1;
		int shortestIndex;
		for(int i = 0; i<myEventsSize; i++){
			if(theEvents.whenToStart() > shortestStart){
				shortestStart = theEvents.whenToStart();
			}
		}
	}
	

}
