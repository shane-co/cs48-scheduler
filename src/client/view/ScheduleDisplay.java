package client.view;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.SwingUtilities;

import com.mindfusion.common.DateTime;
import com.mindfusion.common.Duration;
import com.mindfusion.scheduling.CalendarView;
import com.mindfusion.scheduling.ThemeType;
import com.mindfusion.scheduling.model.Appointment;
import com.mindfusion.common.DateTime;
import com.mindfusion.scheduling.awt.AwtCalendar;
import com.mindfusion.scheduling.model.*;

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
import client.app.obj.Schedule;
import java.util.ArrayList;
import client.app.obj.TimeBlock;

/**
*Class that displays a GUI for a single Schedule object
*/
public class ScheduleDisplay extends JPanel{
	private AwtCalendar calendar;

	public ScheduleDisplay()
	{
		this.setBounds(100, 100, 500, 600);
		this.setLayout(new BorderLayout(0, 0));

		calendar=new AwtCalendar();
		calendar.beginInit();
		calendar.setCurrentView(CalendarView.Timetable);
		calendar.setTheme(ThemeType.Light);
		calendar.getTimetableSettings().setItemOffset(0);
		calendar.getTimetableSettings().setShowItemSpans(null);
		calendar.getTimetableSettings().setSnapInterval(Duration.fromMinutes(1));
		calendar.getTimetableSettings().setStartTime(360);
		calendar.getTimetableSettings().setEndTime(1380);
		calendar.getTimetableSettings().setVisibleColumns(7);
		calendar.endInit();

		calendar.setEnableDragCreate(true);

		this.add(calendar, BorderLayout.CENTER);
	}

	public ScheduleDisplay(Schedule s){
		this();
		this.setSchedule(s);
	}

	public void setSchedule(Schedule s){
		for (int i =1; i<7; i++)
			calendar.getTimetableSettings().getDates().add(new DateTime(2017,s.get_month(),s.get_day()).addDays(i));
		for (int i=0; i<s.get_ScheduleEvents().size();i++)
		{
			for (int j=0; j<s.get_ScheduleEvents().get(i).duration().size(); j++)
			{
				TimeBlock t= s.get_ScheduleEvents().get(i).duration().get(j);
				Appointment app = new Appointment();
				app.setHeaderText(s.get_ScheduleEvents().get(i).get_ID());
				app.setDescriptionText(s.get_ScheduleEvents().get(i).get_descpt());
				app.setStartTime(new DateTime(2017,s.get_month(),s.get_day()).addDays(t.getDay()).addHours(t.getStart()));
				app.setEndTime(new DateTime(2017,s.get_month(),s.get_day()).addDays(t.getDay()).addHours(t.getStart()+1));
				calendar.getSchedule().getItems().add(app);
			}
		}
	}

}
