package client.view;
import client.commander.BGCommander;
import client.app.obj.*;
import client.app.exceptions.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisplayMyEvents {

	private JPanel topPanel;
	private JSplitPane finalPanel;
	private JSplitPane bottomPanel;
	private JPanel columnPanel;
	private JPanel bottomRightPanel;
	private JPanel bottomLeftPanel;
	private JTable table_1;
	private JTable table_2;
	private ArrayList<ScheduleEvent> events_1;
	private ArrayList<ScheduleEvent> events_2;
	private BGCommander commander;
	private final String[] columnNames = {"ID", "DAY", "START DATE", "END DATE"};


	public DisplayMyEvents() {
		initialize();
	}

	/**
	 * Initialize the contents of the panel.
	 */
	private void initialize() {
		commander = BGCommander.getBGCommander();
		//makes panel and sets up layout of TopPanel
		topPanel = new JPanel();
		topPanel.setBounds(100, 100, 500, 300);
		topPanel.setLayout(new BorderLayout(0, 0));
		
		//makes a text pane
		JTextPane txtpnMyEvents = new JTextPane();
		txtpnMyEvents.setText("My Events");
		txtpnMyEvents.setEditable(false);
		topPanel.add(txtpnMyEvents, BorderLayout.NORTH);

		Object[][] data_1={{}};
		/*try{
			ArrayList<ScheduleEvent> events = commander.getScheduleEvents();
			data_1 = eventFormatting(events);
		}catch(ElementNotFoundException e){
		 	data_1 = eventFormatting(new ArrayList<ScheduleEvent>());
		}
		*/
		DefaultTableModel model_1 = new DefaultTableModel(data_1, columnNames);
		JTable table_1 = new JTable(model_1);
		table_1.setEnabled(false);
		table_1.setRowHeight(30);
		//ListSelectionModel listSelectionModel = table_1.getSelectionModel();
        /*table_1.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event) {
				System.out.println(table_1.getValueAt(table_1.getSelectedRow(), 0).toString());
			}
		});*/
		JScrollPane scroll = new JScrollPane(table_1);
		topPanel.add(scroll, BorderLayout.CENTER);
		
		//makes bottom left panel
		bottomLeftPanel = new JPanel();
		bottomLeftPanel.setBounds(100, 100, 250, 300);
		bottomLeftPanel.setLayout(new BorderLayout(0, 0));
		
		JTextPane txtpnEventsToAdd = new JTextPane();
		txtpnEventsToAdd.setText("Events to Add");
		txtpnEventsToAdd.setEditable(false);
		bottomLeftPanel.add(txtpnEventsToAdd, BorderLayout.NORTH);

		Object[][] data_2 = {{}};
		/*try{
			//replace with method to get another orgs/students events
			ArrayList<ScheduleEvent> events = commander.getScheduleEvents();
			data_2 = eventFormatting(events);
		}catch(ElementNotFoundException e){
		 	data_2 = eventFormatting(new ArrayList<ScheduleEvent>());
		}*/
		
		DefaultTableModel model_2 = new DefaultTableModel(data_2, columnNames);
		JTable table_2 = new JTable(model_2);
		table_2.setEnabled(false);
		table_2.setRowHeight(30);
		JScrollPane scrollBottomLeftTable = new JScrollPane(table_2);
		bottomLeftPanel.add(scrollBottomLeftTable, BorderLayout.CENTER);
		
		//makes bottom right panel
		bottomRightPanel = new JPanel();
		JTextPane txtpnAdditionalInfo = new JTextPane();
		txtpnAdditionalInfo.setText("Additional Info");
		bottomRightPanel.setBounds(100, 100, 250, 300);
		bottomRightPanel.setLayout(new BorderLayout(0, 0));
		bottomRightPanel.add(txtpnAdditionalInfo, BorderLayout.NORTH);
		
		//completes panel
		bottomPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, bottomLeftPanel, bottomRightPanel);
		bottomPanel.setBounds(100, 100, 500, 600);
		bottomPanel.setResizeWeight(0.5);
		finalPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topPanel, bottomPanel);
		finalPanel.setBounds(100, 100, 500, 600);
		finalPanel.setResizeWeight(0.5);
	}

	//will help make the information be formatted
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

	public void refresh(){
		finalPanel.removeAll();
		
		
		//makes bottom right panel
		JTextPane txtpnAdditionalInfo = new JTextPane();
		txtpnAdditionalInfo.setText("Additional Info");
		bottomRightPanel.add(txtpnAdditionalInfo, BorderLayout.NORTH);
		
		//make top panel
		
		Object[][] data_1;
		try{
			ArrayList<ScheduleEvent> events_1 = commander.getScheduleEvents();
			data_1 = eventFormatting(events_1);
		}catch(ElementNotFoundException e){
		 	data_1 = eventFormatting(new ArrayList<ScheduleEvent>());
		}

		table_1 = new JTable();
		table_1.setModel(new OtherModel(data_1, columnNames));
        /*table_1.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event) {
				bottomRightPanel.removeAll();
				final int i = table_1.getSelectedRow();
				JTextField textFieldAdditionalInfo = new JTextField();
				textFieldAdditionalInfo.setText("/nI.D. : " + events_1.get(i).get_ID() + "/nDay : "
						+ events_1.get(i).what_day() + "/nStart Time : " + events_1.get(i).when_to_start() + "/nEnd Time : " 
						+ events_1.get(i).when_to_end() + "/nDescription : " + events_1.get(i).get_descpt());
				JTextPane txtpnAdditionalInfo = new JTextPane();
				txtpnAdditionalInfo.setText("Additional Info");
				bottomRightPanel.add(txtpnAdditionalInfo, BorderLayout.NORTH);
				bottomRightPanel.add(textFieldAdditionalInfo, BorderLayout.CENTER);
				JButton removeEventBtn = new JButton("Remove Event");
				removeEventBtn.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						//code to remove events
					}
				});
				bottomRightPanel.add(removeEventBtn, BorderLayout.SOUTH);
				bottomRightPanel.repaint();
			}
		});*/
		table_1.setRowHeight(30);
		JScrollPane scroll = new JScrollPane(table_1);
		topPanel.add(scroll, BorderLayout.CENTER);
		JTextPane txtpnMyEvents = new JTextPane();
		txtpnMyEvents.setText("My Events");
		txtpnMyEvents.setEditable(false);
		topPanel.add(txtpnMyEvents, BorderLayout.NORTH);
		
		//make bottom left panel
		Object[][] data_2;
		try{
			//replace with command to get events list from an organization
			ArrayList<ScheduleEvent> events_2 = commander.getScheduleEvents();
			data_2 = eventFormatting(events_2);
		}catch(ElementNotFoundException e){
		 	data_2 = eventFormatting(new ArrayList<ScheduleEvent>());
		}
		table_2 = new JTable();
		table_2.setModel(new OtherModel(data_2, columnNames));
        /*table_2.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event) {
				bottomRightPanel.removeAll();
				final int i = table_2.getSelectedRow();
				JTextField textFieldAdditionalInfo = new JTextField();
				textFieldAdditionalInfo.setText("/nI.D. : " + events_2.get(i).get_ID() + "/nDay : "
						+ events_2.get(i).what_day() + "/nStart Time : " + events_2.get(i).when_to_start() + "/nEnd Time : " 
						+ events_2.get(i).when_to_end() + "/n Description : " + events_2.get(i).get_descpt());
				JTextPane txtpnAdditionalInfo = new JTextPane();
				txtpnAdditionalInfo.setText("Additional Info");
				bottomRightPanel.add(txtpnAdditionalInfo, BorderLayout.NORTH);
				bottomRightPanel.add(textFieldAdditionalInfo, BorderLayout.CENTER);
				JButton addEventBtn = new JButton("Add Event");
				addEventBtn.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						//code to remove events
					}
				});
				bottomRightPanel.add(addEventBtn, BorderLayout.SOUTH);
				bottomRightPanel.repaint();
			}
		});*/
        table_2.setRowHeight(30);
		JScrollPane scroll_2 = new JScrollPane(table_2);
		topPanel.add(scroll_2, BorderLayout.CENTER);
		JTextPane txtpnEventsToAdd = new JTextPane();
		txtpnEventsToAdd.setText("Events to Add");
		txtpnEventsToAdd.setEditable(false);
		bottomLeftPanel.add(txtpnEventsToAdd, BorderLayout.NORTH);
		
		
		//completes the final panel
		bottomPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, bottomLeftPanel, bottomRightPanel);
		bottomPanel.setBounds(100, 100, 500, 600);
		bottomPanel.setResizeWeight(0.5);
		finalPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topPanel, bottomPanel);
		finalPanel.setBounds(100, 100, 500, 600);
		finalPanel.setResizeWeight(0.5);
	}
	public JSplitPane returnFinalPanel(){
		return finalPanel;
	}

}
