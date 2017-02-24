package client.view;
import client.commander.BGCommander;
import client.app.obj.*;
import client.app.exceptions.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.util.ArrayList;

public class DisplayMyEvents {

	private JPanel finalPanel;
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
		//makes panel and sets up layout
		finalPanel = new JPanel();
		finalPanel.setBounds(100, 100, 500, 600);
		finalPanel.setLayout(new BorderLayout(0, 0));

		//makes a text pane
		JTextPane txtpnMyEvents = new JTextPane();
		txtpnMyEvents.setText("My Events");
		txtpnMyEvents.setEditable(false);
		finalPanel.add(txtpnMyEvents, BorderLayout.NORTH);

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
		finalPanel.add(scroll, BorderLayout.CENTER);
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
		finalPanel.add(scroll, BorderLayout.CENTER);
	}
	public JPanel returnFinalPanel(){
		return finalPanel;
	}

}
