package client.view.displayClasses;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;

public class DisplayMyEvents {

	private JPanel finalPanel;

	
	public DisplayMyEvents() {
		initialize();
	}

	/**
	 * Initialize the contents of the panel.
	 */
	private void initialize() {
		//makes panel and sets up layout
		finalPanel = new JPanel();
		finalPanel.setBounds(100, 100, 500, 600);
		finalPanel.setLayout(new BorderLayout(0, 0));
		
		//makes a text pane
		JTextPane txtpnMyEvents = new JTextPane();
		txtpnMyEvents.setText("My Events");
		finalPanel.add(txtpnMyEvents, BorderLayout.NORTH);
		String[] columnNames = {"ID", "DAY", "START DATE", "END DATE"};
		
		//ArrayList<ScheduleEvent> events = client.getUserEvents();
		//String[][] data_1 = eventFormatting(events);
		
		//this is a test line i have for testing without needing the bgcommander
		String[][] data_1 = {{"a", "b", "c", "d"}, {"e", "f", "g", "h"}};
		
		DefaultTableModel model_1 = new DefaultTableModel(data_1, columnNames);
		JTable table_1 = new JTable(model_1);
		table_1.setEnabled(false);
		table_1.setRowHeight(30);
		JScrollPane scroll = new JScrollPane(table_1);
		finalPanel.add(scroll, BorderLayout.CENTER);
	}
	
	//will help make the information be formatted
	/*public static Object[][] eventFormatting(ArrayList<ScheduleEvent> listEvents){
		Object[][] output = new String[listEvents.size()][4];
		for(int i =0; i < listEvents.size(); i++){
			ScheduleEvent e = listEvents.get(i);
			output[i][0] = (String)e.get_ID();
			output[i][1] = Integer.toString(e.what_day());
			output[i][2] = Integer.toString(e.when_to_start());
			output[i][3] = Integer.toString(e.when_to_end());

		}
		return output;
	}*/
	
	public JPanel returnFinalPanel(){
		return finalPanel;
	}

}
