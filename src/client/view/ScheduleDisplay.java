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
import client.app.obj.*;
import java.util.ArrayList;

/**
*Class that displays a GUI for a single Schedule object
*/
public class ScheduleDisplay extends JPanel{
	private AwtCalendar calendar;
	public ScheduleDisplay(/*Schedule s*/)
	{
		this.setBounds(100, 100, 500, 600);
		this.setLayout(new BorderLayout(0, 0));

		calendar=new AwtCalendar();
		calendar.beginInit();
		calendar.setCurrentView(CalendarView.Timetable);
		calendar.setTheme(ThemeType.Light);
		for (int i =1; i<7; i++)
			//calendar.getTimetableSettings().getDates().add(new DateTime(2017,s.get_month(),s.get_day()).addDays(i));
			calendar.getTimetableSettings().getDates().add(DateTime.today().addDays(i));
		calendar.getTimetableSettings().setItemOffset(0);
		calendar.getTimetableSettings().setShowItemSpans(null);
		calendar.getTimetableSettings().setSnapInterval(Duration.fromMinutes(1));
		calendar.getTimetableSettings().setStartTime(360);
		calendar.getTimetableSettings().setEndTime(1380);
		calendar.getTimetableSettings().setVisibleColumns(3);
		calendar.endInit();

		calendar.setEnableDragCreate(true);

		Appointment app = new Appointment();
        app.setHeaderText("Test Event1");
        app.setDescriptionText("This event always happens \"today\" from 5:00-6:00 PM");
        app.setStartTime(DateTime.today().addHours(17));
        app.setEndTime(DateTime.today().addHours(19));
        calendar.getSchedule().getItems().add(app);


		this.add(calendar, BorderLayout.CENTER);
	}

}
