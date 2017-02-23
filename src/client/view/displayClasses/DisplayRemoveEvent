package client.view.displayClasses;
import javax.swing.*;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.*;

public class DisplayRemoveEvent {

	private JPanel finalPanel;

	public DisplayRemoveEvent() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		finalPanel = new JPanel();
		finalPanel.setBounds(100, 100, 500, 600);
		finalPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JTextPane txtpnChooseEventTo = new JTextPane();
		txtpnChooseEventTo.setText("Choose Event to Remove");
		finalPanel.add(txtpnChooseEventTo);
		
		JPanel panel = new JPanel();
		finalPanel.add(panel);
		
		//ArrayList<ScheduleEvent> myEvents = client.getUserEvents();
		//String[] b = new String[myEvents.size(); 
		//for(int i = 0; i < myEvents.size(); i++ ){
		//	b[i] = myEvents[i].get_ID();
		
		//test line
		String[] b = {"Blaugh", "b"};
		
		//creates combo box
		final JComboBox comboBox_1 = new JComboBox(b);
		panel.add(comboBox_1);
		
		JButton btnNewButton = new JButton("Remove Event");
		btnNewButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int eventIndex = comboBox_1.getSelectedIndex();
				// deleteEvent(myEvents[ eventIndex ]);
			}
		});
		finalPanel.add(btnNewButton);
		
		
	}
	
	public JPanel returnPanel(){
		return finalPanel;
	}

}
