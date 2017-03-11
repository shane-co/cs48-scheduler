package client.view;
import client.commander.BGCommander;
import client.app.obj.*;
import client.app.exceptions.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.util.ArrayList;


public class DisplayMyEvents extends JSplitPane{
	private BGCommander commander;
	
	private JSplitPane leftPanel;
	private JPanel leftAdditionalInfoPanel;
	private JPanel myEventsListPanel;
	
	private JSplitPane rightPanel;
	private JPanel rightAdditionalInfoPanel;
	private JPanel availableEventsListPanel;
	private JPanel upperRightPanel;
	
	public DisplayMyEvents() {
		initialize();
	}

	/**
	 * Initialize the contents of the panel.
	 */
	private void initialize() {
		commander = BGCommander.getBGCommander();
		
		//makes myEventsListPanel
		myEventsListPanel = new JPanel();
		myEventsListPanel.setBounds(100, 100, 500, 300);
		myEventsListPanel.setLayout(new BorderLayout(0, 0));
		
		JTextPane myEventsTxtPn = new JTextPane();
		myEventsTxtPn.setText("My Events");
		myEventsTxtPn.setEditable(false);
		myEventsListPanel.add(myEventsTxtPn, BorderLayout.NORTH);

		String[] listOfEvents = {"hey", "hello", "blaugh", "maroon"};
		/*try{
			ArrayList<ScheduleEvent> events = commander.getScheduleEvents();
			listOfEvents = eventFormatting(events);
		}catch(ElementNotFoundException e){
		 	listOfEvents = eventFormatting(new ArrayList<ScheduleEvent>());
		}*/
		JList myEventsList = new JList(listOfEvents);
		myEventsList.addListSelectionListener(new ListSelectionListener(){
    		public void valueChanged(ListSelectionEvent event) {
    			
    		}
		});
		
		JScrollPane scroll = new JScrollPane(myEventsList);
		myEventsListPanel.add(scroll, BorderLayout.CENTER);
		
		//makes additionalInfoPanel
		leftAdditionalInfoPanel = new JPanel();
		leftAdditionalInfoPanel.setBounds(100, 100, 500, 300);
		leftAdditionalInfoPanel.setLayout(new BorderLayout(0, 0));
		
		JTextPane leftAddInfoTxtPn = new JTextPane();
		leftAddInfoTxtPn.setText("Additional Information");
		leftAddInfoTxtPn.setEditable(false);
		leftAdditionalInfoPanel.add(leftAddInfoTxtPn, BorderLayout.NORTH);
		
		JTextField leftAddInfoTxtFld = new JTextField();
		leftAdditionalInfoPanel.add(leftAddInfoTxtFld, BorderLayout.CENTER);
		
		JButton removeEventsBtn = new JButton("Remove Event");
		leftAdditionalInfoPanel.add(removeEventsBtn, BorderLayout.SOUTH);
		
		//makes leftPanel
		leftPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, myEventsListPanel, leftAdditionalInfoPanel);
		leftPanel.setResizeWeight(0.5);
		
		//makes availableEventsListPanel
		availableEventsListPanel = new JPanel();
		availableEventsListPanel.setBounds(100, 100, 500, 300);
		availableEventsListPanel.setLayout(new BorderLayout(0, 0));
		
		String[] listOfAvailableEvents = {"hey", "hello", "blaugh", "maroon"};
		//insert try, catch method to obtain events
		JList availableEventsList = new JList(listOfAvailableEvents);
		availableEventsList.addListSelectionListener(new ListSelectionListener(){
    		public void valueChanged(ListSelectionEvent event) {
    		}
		});
		JScrollPane a = new JScrollPane(availableEventsList);
		availableEventsListPanel.add(a, BorderLayout.CENTER);
		
		//makes upperRightPanel
		upperRightPanel = new JPanel();
		upperRightPanel.setBounds(100, 100, 500, 300);
		upperRightPanel.setLayout(new BorderLayout(0, 0));
		String[] availableOrgsList;
		//insert method to fill availableOrgsList with the names of the list
		JPanel topEventsListPanel = new JPanel();
		topEventsListPanel.setBounds(100, 100, 500, 300);
		topEventsListPanel.setLayout(new BorderLayout(0, 0));
		JTextPane availableEventsTxtPn = new JTextPane();
		availableEventsTxtPn.setText("Available Events");
		availableEventsTxtPn.setEditable(false);

		topEventsListPanel.add(availableEventsTxtPn, BorderLayout.NORTH);
		JComboBox availableOrgs = new JComboBox(/*availableOrgsList*/);
		topEventsListPanel.add(availableOrgs, BorderLayout.CENTER);
		JTextPane availableOrgsTxtPn = new JTextPane();
		availableOrgsTxtPn.setText("Available Orgs.");
		topEventsListPanel.add(availableOrgsTxtPn, BorderLayout.WEST);
		upperRightPanel.add(topEventsListPanel, BorderLayout.NORTH);
		upperRightPanel.add(availableEventsListPanel, BorderLayout.CENTER);
		
		//makes rightAdditionalInfoPanel
		rightAdditionalInfoPanel = new JPanel();
		rightAdditionalInfoPanel.setBounds(100, 100, 500, 300);
		rightAdditionalInfoPanel.setLayout(new BorderLayout(0, 0));
		
		JTextPane rightAddInfoTxtPn = new JTextPane();
		rightAddInfoTxtPn.setText("Additional Information");
		rightAddInfoTxtPn.setEditable(false);
		rightAdditionalInfoPanel.add(rightAddInfoTxtPn, BorderLayout.NORTH);
		
		JTextField rightAddInfoTxtFld = new JTextField();
		rightAdditionalInfoPanel.add(rightAddInfoTxtFld, BorderLayout.CENTER);
		
		JButton addEventsBtn = new JButton("Add Event");
		rightAdditionalInfoPanel.add(addEventsBtn, BorderLayout.SOUTH);
		
		//makes rightPanel
		rightPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, upperRightPanel, rightAdditionalInfoPanel);
		rightPanel.setResizeWeight(0.5);
		
		//makes full panel
		this.setLeftComponent(leftPanel);
		this.setRightComponent(rightPanel);
		this.setResizeWeight(.5);
	}

	//returns a string of the events
	public static String[] eventFormatting(ArrayList<ScheduleEvent> listEvents){
		String[] output = new String[listEvents.size()];
		for(int i =0; i < listEvents.size(); i++){
			ScheduleEvent e = listEvents.get(i);
			output[i] = e.get_ID();
		}
		return output;
	}

	public void refresh(){
		/*finalPanel.removeAll();
		Object[][] data_1;
		try{
			ArrayList<ScheduleEvent> events = commander.getScheduleEvents();
			data_1 = eventFormatting(events);
		}catch(ElementNotFoundException e){
		 	data_1 = eventFormatting(new ArrayList<ScheduleEvent>());
		}

		DefaultTableModel model_1 = new DefaultTableModel(data_1, columnNames);
		JTable table_1 = new JTable(model_1);
		table_1.setEnabled(false);
		table_1.setRowHeight(30);
		JScrollPane scroll = new JScrollPane(table_1);
		finalPanel.add(scroll, BorderLayout.CENTER);*/
	}

}
