package client.view;
import client.commander.BGCommander;
import client.app.obj.*;
import client.app.exceptions.UserNotFoundException;
import client.view.listeners.SchedSelectionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class DisplayScheduleDisplay extends JPanel implements DisplayScheduleComponent{

	private JComboBox possibleSchedules;
	private BGCommander commander;
	public DisplayScheduleDisplay() {
		initialize();
		this.setBounds(100, 100, 500, 300);
		this.setLayout(new BorderLayout(0, 0));
		JPanel topPanel = new JPanel();
		topPanel.setBounds(100, 100, 500, 300);
		topPanel.setLayout(new BorderLayout(0, 0));
		JTextPane chooseSchTxtPn = new JTextPane();
		chooseSchTxtPn.setText("Choose Schedule to Display");
		topPanel.add(chooseSchTxtPn, BorderLayout.WEST);
		topPanel.add(possibleSchedules, BorderLayout.CENTER);
		this.add(topPanel, BorderLayout.NORTH);

		ScheduleDisplay display=new ScheduleDisplay();
		JScrollPane scroll = new JScrollPane(display);
		this.add(scroll, BorderLayout.CENTER);

		possibleSchedules.addActionListener(new SchedSelectionListener(possibleSchedules, display));

		JButton deleteScheduleBtn = new JButton("Delete Schedule");
		this.add(deleteScheduleBtn, BorderLayout.SOUTH);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		possibleSchedules = new JComboBox();
	}

	public void refresh(){
		DefaultComboBoxModel schedmodel = new DefaultComboBoxModel();
		try{
			for(String org:commander.getSchedules()){
				schedmodel.addElement(org);
				possibleSchedules.setModel(schedmodel);
			}
		}catch(UserNotFoundException ex){possibleSchedules.removeAllItems();}
		catch(NullPointerException ex){}
	}

}
