package client.commander;
import client.app.obj.ScheduleEvent;
import client.app.obj.Schedule;
import client.app.obj.User;

import java.util.Random;
import java.util.ArrayList;
import java.util.Iterator;

public class ScheduleGenerator{
	private ArrayList<ScheduleEvent> events; //input array
	private ArrayList<ScheduleEvent> output;
	private int number_of_events; //number of different events (same event with different duration count as one event)

	public ScheduleGenerator(ArrayList<ScheduleEvent> e){
	events = e;
	output = new ArrayList<ScheduleEvent>();
	number_of_events=events.size();
	}
	private boolean isEligible(ScheduleEvent candidate){
	  for(ScheduleEvent s: output){
		  if(s.equals(candidate) || s.conflicts(candidate)) return false;
	  }
	  return true;
	}
	public Schedule getSchedule(String id, int month, int day){
		for(int i=0; i<number_of_events; i++){
			Random random = new Random();
			int index = random.nextInt(events.size());
			ScheduleEvent randomEvent = events.get(index);
			if(isEligible(randomEvent)) output.add(randomEvent);
		}
		return new Schedule(output, id, month, day);
	}
}
