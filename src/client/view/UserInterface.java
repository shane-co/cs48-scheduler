package client.view;
import client.app.obj.ScheduleEvent;				//this is temporary, should have methods and sub classes to do this

import java.util.ArrayList;
import java.awt.EventQueue;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class UserInterface {

	private JFrame frame;
	private ArrayList<ScheduleEvent> myEvents = new ArrayList<ScheduleEvent>();		//temporary class for demo,account information will replace this, also this is pretty much schedule, i.e.e array list of ScheduleEvents
	private ArrayList<ScheduleEvent> posEvents = new ArrayList<ScheduleEvent>();	//temporary class for demo, will be replaced with functions to search for events and functions to output search results
	private JScrollPane scroll, scroll_2;			//these don't need to be declared here
	private JTable table_1, table_2;
	private JLabel lblPossibleEventsTo;
	private String[] columnNames = {"ID", "DAY", "START DATE", "END DATE"};
	private Object[][] data_1, data_2;
	private DefaultTableModel model_1, model_2;

	/**
	 * Launch the application.
	 */
	//not sure how to get rid of this so that the main function can go elsewhere.
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInterface window = new UserInterface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserInterface() {
		
		initialize();
		//myEvents = (events from account's information), going to be handled by another class
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.test();
		//creates frame, i.e. the window
		frame = new JFrame();
		frame.setBounds(100, 100, 2000, 1500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//creates first label, my events label
		JLabel lblNewLabel = new JLabel("My Events");
		lblNewLabel.setBounds(44, 76, 247, 100);
		frame.getContentPane().add(lblNewLabel);
		
		//second label
		lblPossibleEventsTo = new JLabel("Possible Events to Subscribe to");
		lblPossibleEventsTo.setBounds(1031, 110, 580, 33); 
		frame.getContentPane().add(lblPossibleEventsTo);
		
		//third label
		JLabel lblChooseEventTo = new JLabel("Choose Event to Add");
		lblChooseEventTo.setBounds(1035, 1137, 283, 33);
		frame.getContentPane().add(lblChooseEventTo);
		
		//formats array lists of events to be easily put into tables
		data_1 = eventFormatting(myEvents);
		data_2 = eventFormatting(posEvents);
		
		//creates first table
		model_1 = new DefaultTableModel(data_1, columnNames);
		table_1 = new JTable(model_1);
		table_1.setEnabled(false);
		table_1.setRowHeight(30);
		
		//allows the table to be scrolled down, also allows the columnNames to appear for some reason
		scroll = new JScrollPane(table_1, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setLocation(54, 180);
		scroll.setEnabled(false);
		scroll.setSize(743, 908);
		frame.getContentPane().add(scroll);
		
		//creates second table
		model_2 = new DefaultTableModel(data_2, columnNames);
		table_2 = new JTable(model_2);
		table_2.setEnabled(false);
		table_2.setRowHeight(30);
		
		scroll_2 = new JScrollPane(table_2, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll_2.setLocation(1035, 180);
		scroll_2.setEnabled(false);
		scroll_2.setSize(743, 908);
		frame.getContentPane().add(scroll_2);
		
		
		
		//sets the input string to all the IDs in posEvents
		String[] input = new String[posEvents.size()];
		for(int i = 0; i < posEvents.size(); i++){
			input[i] = posEvents.get(i).get_ID();		
		}
		
		//this will allow a user to choose what event to add
		JComboBox comboBox = new JComboBox(input);
		comboBox.setBounds(1355, 1134, 423, 39);
		
		//listener implementation
		comboBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JComboBox comboBox = (JComboBox) e.getSource();
				int index = comboBox.getSelectedIndex();
				ScheduleEvent transfer = posEvents.get(index);
				String [] insert = {transfer.get_ID(), Integer.toString(transfer.what_day()), 
						Integer.toString(transfer.when_to_start()), Integer.toString(transfer.when_to_end())
				};
				myEvents.add(transfer);
				posEvents.remove(index);
				model_2.removeRow(index);
				model_1.addRow(insert);
				comboBox.removeItemAt(index);
			}
		});
		frame.getContentPane().add(comboBox);
		
	}
	
	//ended up not using Jason's test thing since it didn't work with what we have here
	//so this is test segment
	private void test(){
		//description isn't used at all in this demo iteration, this required changing constructor in ScheduleEvent.javaz to have id included in constructor
		ScheduleEvent a = new ScheduleEvent(1, 2, 3, "wgaf", "ES 132");
		ScheduleEvent b = new ScheduleEvent(2, 3, 4, "wgaf", "CS -8");
		ScheduleEvent c = new ScheduleEvent(3, 4, 5, "wgaf", "MATH 2.3");
		ScheduleEvent d = new ScheduleEvent(4, 5, 6, "wgaf", "TALKING");
		ScheduleEvent e = new ScheduleEvent(5, 6, 7, "wgaf", "PSAT 41");
		ScheduleEvent f = new ScheduleEvent(6, 7, 8, "wgaf", "CS 23232");
		ScheduleEvent g = new ScheduleEvent(7, 8, 9, "wgaf", "MTH 123");
		ScheduleEvent h = new ScheduleEvent(8, 9, 1, "wgaf", "TK 31");
		myEvents.add(a);
		myEvents.add(b);
		myEvents.add(c);
		posEvents.add(d);
		posEvents.add(e);
		posEvents.add(f);
		posEvents.add(g);
		posEvents.add(h);
	}
	
	//takes an ArrayList and outputs object ready to have info stored in table
	public static Object[][] eventFormatting(ArrayList<ScheduleEvent> listEvents){
		Object[][] output = new String[listEvents.size()][4];
		for(int i =0; i < listEvents.size(); i++){
			ScheduleEvent e = listEvents.get(i);
			output[i][0] = (String)e.get_ID();
			output[i][1] = Integer.toString(e.what_day());
			output[i][2] = Integer.toString(e.when_to_start());
			output[i][3] = Integer.toString(e.when_to_end());
			
		}
		return output;
	}

	public JFrame returnFrame() {
		return frame;
	}
}

