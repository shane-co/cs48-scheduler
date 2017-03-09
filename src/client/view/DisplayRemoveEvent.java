package client.view;
import javax.swing.*;
import client.commander.BGCommander;
import client.app.obj.*;
import client.app.exceptions.ElementNotFoundException;
import java.util.ArrayList;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.*;

public class DisplayRemoveEvent {

	private JPanel finalPanel;
	private BGCommander commander;
	private UserInterface ui;

	public DisplayRemoveEvent(UserInterface parent) {
		ui=parent;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		commander = BGCommander.getBGCommander();
		finalPanel = new JPanel();
		finalPanel.setBounds(100, 100, 500, 600);
		finalPanel.setLayout(new GridLayout(0, 1, 0, 0));

		JTextPane txtpnChooseEventTo = new JTextPane();
		txtpnChooseEventTo.setText("Choose Event to Remove");
		txtpnChooseEventTo.setEditable(false);
		finalPanel.add(txtpnChooseEventTo);

		JPanel panel = new JPanel();
		finalPanel.add(panel);

		ArrayList<ScheduleEvent> myEvents;
		try{myEvents = commander.getScheduleEvents();}
		catch(ElementNotFoundException e){myEvents=new ArrayList<ScheduleEvent>();}
		String[] b = new String[myEvents.size()];
		for(int i = 0; i < myEvents.size(); i++ ){
			b[i] = myEvents.get(i).get_ID();
		}

		//creates combo box
		final JComboBox comboBox_1 = new JComboBox(b);
		panel.add(comboBox_1);

		JButton btnNewButton = new JButton("Remove Event");
		btnNewButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int eventIndex = comboBox_1.getSelectedIndex();
				commander.unsubscribe(b[eventIndex]);
				ui.refreshMyEvents();
			}
		});
		finalPanel.add(btnNewButton);
	}

	public JPanel returnFinalPanel(){
		return finalPanel;
	}

	public void refresh(){
		finalPanel.removeAll();
		JPanel panel = new JPanel();
		finalPanel.add(panel);

		ArrayList<ScheduleEvent> myEvents;
		try{myEvents = commander.getScheduleEvents();}
		catch(ElementNotFoundException e){myEvents=new ArrayList<ScheduleEvent>();}
		String[] b = new String[myEvents.size()];
		for(int i = 0; i < myEvents.size(); i++ ){
			b[i] = myEvents.get(i).get_ID();
		}

		//creates combo box
		final JComboBox comboBox_1 = new JComboBox(b);
		panel.add(comboBox_1);

		JButton btnNewButton = new JButton("Remove Event");
		btnNewButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int eventIndex = comboBox_1.getSelectedIndex();
				commander.unsubscribe(b[eventIndex]);
				ui.refreshMyEvents();
			}
		});
		finalPanel.add(btnNewButton);
	}
}
