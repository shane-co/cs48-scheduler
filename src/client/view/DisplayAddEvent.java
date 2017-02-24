package client.view;
import client.commander.BGCommander;
import client.app.obj.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import java.awt.*;
import java.util.ArrayList;

public class DisplayAddEvent {

	private JSplitPane finalPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextArea textArea;
	private BGCommander commander;

	public DisplayAddEvent() {
		initialize();
	}

	/**
	 * Initialize the contents of the panel.
	 */
	private void initialize() {
		commander=BGCommander.getBGCommander();
		JPanel topPanel = new JPanel();
		topPanel.setBounds(100, 100, 250, 600);
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBounds(100, 100, 250, 600);
		bottomPanel.setLayout(new BorderLayout(0, 0));
	  
		//creates button to display event
		JButton btnAddEvent = new JButton("Add Event");
		bottomPanel.add(btnAddEvent);
		finalPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topPanel, bottomPanel);
		topPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		final JTextPane txtpnEventId = new JTextPane();
		txtpnEventId.setText("Event I.D. :");
		txtpnEventId.setEditable(false);
		topPanel.add(txtpnEventId);
		
		textField = new JTextField();
		topPanel.add(textField);
		textField.setColumns(10);
		
		JTextPane txtpnDay = new JTextPane();
		txtpnDay.setText("Day :");
		txtpnDay.setEditable(false);
		topPanel.add(txtpnDay);
		
		textField_1 = new JTextField();
		topPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JTextPane txtpnStartTime = new JTextPane();
		txtpnStartTime.setText("Start Time :");
		txtpnStartTime.setEditable(false);
		topPanel.add(txtpnStartTime);
		
		textField_2 = new JTextField();
		topPanel.add(textField_2);
		textField_2.setColumns(10);
		
		JTextPane txtpnEndTime = new JTextPane();
		txtpnEndTime.setText("End Time :");
		txtpnEndTime.setEditable(false);
		topPanel.add(txtpnEndTime);
		
		textField_3 = new JTextField();
		topPanel.add(textField_3);
		textField_3.setColumns(10);
		
		JTextPane txtpnDescription = new JTextPane();
		txtpnDescription.setText("Description :");
		txtpnDescription.setEditable(false);
		topPanel.add(txtpnDescription);
		
		textArea = new JTextArea();
		topPanel.add(textArea);
		
		btnAddEvent.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String eventID = textField.getText();
				String day = textField_1.getText();
				int intDay = Integer.parseInt(day);
				String startTime = textField_2.getText();
				int intStartTime = Integer.parseInt(day);
				String endTime = textField_3.getText();
				int intEndTime = Integer.parseInt(day);
				String description = textArea.getText();
				ScheduleEvent event = new ScheduleEvent(intDay, intStartTime, intEndTime, eventID, description);
				commander.subscribeEvent(eventID, day, startTime, endTime, description);
			}
		});
		finalPane.setBounds(100, 100, 500, 600);
		finalPane.setResizeWeight(0.8);
	}
	
	public JSplitPane returnPanel(){
  
		return finalPane;
    
	}

}
