package client.view;
import client.commander.BGCommander;
import client.app.obj.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class DisplayScheduleDisplay {

	private JPanel finalPanel;
	private JComboBox comboBox;
	private BGCommander commander;
	//private Container container;
	public DisplayScheduleDisplay() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		finalPanel = new JPanel();
		finalPanel.setBounds(100, 100, 500, 600);
		finalPanel.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		finalPanel.add(splitPane, BorderLayout.NORTH);
		//ArrayList<Schedule> list = commander.getSchedules();
		//String[] listNames = new String[list.size()];
		/*for(int i = 0; i < list.size(); i++ )
			listNames[i] = list.get(i).getID();
			*/
		comboBox = new JComboBox(/*listNames*/);
		splitPane.setRightComponent(comboBox);
		//commander.genSchedule();
		comboBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//int index = comboBox.getSelectedIndex();
				//ScheduleDisplay a = new ScheduleDisplay(list.get(index));
				//container = a.returnContainer();
				JPanel panel = new JPanel(/*container*/);
				finalPanel.add(panel, BorderLayout.CENTER);
			}
		});
		
		JTextPane txtpnChooseScheduleTo = new JTextPane();
		txtpnChooseScheduleTo.setText("Choose Schedule to Display");
		splitPane.setLeftComponent(txtpnChooseScheduleTo);
		txtpnChooseScheduleTo.setEditable(false);
		
		
		JTextPane txtpnChooseScheduleToDelete = new JTextPane();
		txtpnChooseScheduleToDelete.setText("Choose Schedule to Delete");
		txtpnChooseScheduleToDelete.setEditable(false);
		
		JButton buttonDeleteSchedule = new JButton("Delete Selected Schedule");
		JSplitPane bottomPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, txtpnChooseScheduleToDelete, buttonDeleteSchedule);
		buttonDeleteSchedule.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//comboBox.getSelectedIndex();
				//commander.deleteSchedule(list.get(i));
			}
		});
		
		finalPanel.add(bottomPanel, BorderLayout.SOUTH);	
	}
	
	public JPanel returnFinalPanel(){
		return finalPanel;
	}

}
