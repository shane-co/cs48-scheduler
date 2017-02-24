package client.view;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.SwingUtilities;

import com.mindfusion.common.DateTime;
import com.mindfusion.common.Duration;
import com.mindfusion.scheduling.CalendarView;
import com.mindfusion.scheduling.ThemeType;
import com.mindfusion.scheduling.awt.AwtCalendar;
import com.mindfusion.scheduling.model.Appointment;
import com.mindfusion.common.DateTime;
import com.mindfusion.common.Duration;
import com.mindfusion.common.MouseButtons;
import com.mindfusion.common.ChangeListener;
import com.mindfusion.drawing.Brushes;
import com.mindfusion.drawing.Colors;
import com.mindfusion.drawing.awt.AwtImage;
import com.mindfusion.scheduling.*;
import com.mindfusion.drawing.Color;
import com.mindfusion.scheduling.awt.AwtCalendar;
import com.mindfusion.scheduling.model.*;
import com.mindfusion.scheduling.model.ItemEvent;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ItemListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.List;
import client.app.obj.*;
import java.util.ArrayList;

public class ScheduleDisplay extends JFrame{
	private AwtCalendar calendar;
	Container container = this.getContentPane();
	public ScheduleDisplay(client.app.obj.Schedule s) 
	{
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("MyScheduel");
		setMinimumSize(new Dimension(800,600));
		
		calendar=new AwtCalendar();
		calendar.beginInit();
		calendar.setCurrentView(CalendarView.Timetable);
		calendar.setTheme(ThemeType.Light);
		for (int i =0; i<7; i++)
			calendar.getTimetableSettings().getDates().add(new DateTime(2017,s.get_month(),s.get_day()).addDays(i));
		
		calendar.getTimetableSettings().setItemOffset(0);
		calendar.getTimetableSettings().setShowItemSpans(null);
		calendar.getTimetableSettings().setSnapInterval(Duration.fromMinutes(1));
		calendar.getTimetableSettings().setStartTime(360);
		calendar.getTimetableSettings().setEndTime(1380);
		calendar.getTimetableSettings().setVisibleColumns(3);
		calendar.endInit();
		
		//calendar.setEnableDragCreate(true);
		
		//create events on schedule
		for (int i=0; i<s.size_of_TimeBlock(); ++i)
		{
			TimeBlock t=s.get_TimeBlock(i);
			if (t.is_occupied()) 
			{
				for (int j=0; j<t.numberOfEvents();++j)
				{
					 Appointment app = new Appointment();
				        app.setHeaderText(t.getEvent(j).get_ID());
				        app.setDescriptionText(t.getEvent(j).get_descpt());
				        app.setStartTime(new DateTime(2017, s.get_month(), s.get_day()+t.getDay()-1, t.getEvent(j).when_to_start()/100, t.getEvent(j).when_to_start()%100,0));
				        app.setEndTime(new DateTime(2017, s.get_month(), s.get_day()+t.getDay()-1, t.getEvent(j).when_to_end()/100, t.getEvent(j).when_to_end()%100,0));
				        calendar.getSchedule().getItems().add(app);
				}
			}
		}
        


		container.add(calendar);
	}
	
	public AwtCalendar returnCalendar(){
		return calendar;
	}
	
	//This main function shows how to display the schedule.
	//I think its useful later when we start implementing BGcommander
	/*public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ScheduleDisplay window = null;
                try {
                    window = new ScheduleDisplay(A Schedule Object);
                    window.setVisible(true);
                }
                catch (Exception exp) {
                }
            }
        });

	}
	*/
}
