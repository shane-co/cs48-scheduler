package client.view;

import client.app.obj.ScheduleEvent;
import client.commander.BGCommander;
import client.view.*;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class UserInterface {
	private JFrame frame;
	private BGCommander commander;
	private DisplayMyEvents myEventsDisplay;
	private DisplayAddEvent addEventDisplay;
	private DisplayRemoveEvent removeEventDisplay;
	public BGCommander command(){return commander;}
  /**
	 * Launch method.
	 */
	public void launch(){
		try {
			UserInterface uiWindow = new UserInterface();
			uiWindow.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public UserInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the panel.
	 */
	private void initialize() {
		//commander=BGCommander.getBGCommander();
		frame = new JFrame("Del Planner");
		frame.setBounds(100, 100, 1200, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 3));


		JTabbedPane paneLeft = new JTabbedPane(JTabbedPane.TOP);

		//not completely done implementing
		/*DisplayScheduleDisplay k = new DisplayScheduleDisplay();
		JPanel panelScheduleEvents = k.returnFinalPanel();
		paneLeft.addTab("Schedule Display", panelScheduleEvents);*/

		myEventsDisplay = new DisplayMyEvents();
		JPanel panelMyEventsTop = myEventsDisplay.returnFinalPanel();
		removeEventDisplay = new DisplayRemoveEvent(this);
		JPanel panelMyEventsBottomRight = removeEventDisplay.returnFinalPanel();
		addEventDisplay = new DisplayAddEvent(this);
		JSplitPane panelMyEventsBottomLeft = addEventDisplay.returnPanel();
		JSplitPane panelMyEventsBottom = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelMyEventsBottomLeft, panelMyEventsBottomRight);
		panelMyEventsBottom.setResizeWeight(0.5);
		JSplitPane panelMyEventsBoth = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panelMyEventsTop, panelMyEventsBottom);
		panelMyEventsBoth.setResizeWeight(0.5);
		paneLeft.addTab("My Events", panelMyEventsBoth);

		//not completely done implementing
		/*JPanel panelGenerateSchedules = new JPanel();
		paneLeft.addTab("Generate Schedules", panelGenerateSchedules);*/


		JTabbedPane paneRight = new JTabbedPane(JTabbedPane.TOP);
		login loginhandler = new login(this);
		paneRight.addTab("Login",loginhandler.returnFrame().getContentPane());
		JSplitPane splitPaneLR = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, paneLeft, paneRight);

		//not completely done implementing
		/*displaySearchEvent c = new displaySearchEvent();
		JSplitPane panel3 = c.returnFinalPanel();
		paneRight.addTab("Search for Event", panel3);*/

		/*JPanel panelGenerateSchedules2 = new JPanel();
		paneRight.addTab("Generate Schedules", panelGenerateSchedules2);*/

		paneRight.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		paneLeft.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		splitPaneLR.setOneTouchExpandable(true);

		splitPaneLR.setResizeWeight(0.5);
		frame.getContentPane().add(splitPaneLR);
	}

	public void refreshMyEvents(){
		myEventsDisplay.refresh();
		removeEventDisplay.refresh();
		frame.repaint();
	}
}
