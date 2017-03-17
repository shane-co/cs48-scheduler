package client.view;
import client.commander.BGCommander;
import client.app.exceptions.ElementNotFoundException;
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
	private ScheduleDisplay display;
	private JButton deleteScheduleBtn;
	public DisplayScheduleDisplay() {
		initialize();
		this.setBounds(100, 100, 500, 300);
		this.setLayout(new BorderLayout(0, 0));
		JPanel topPanel = new JPanel();
		topPanel.setBounds(100, 100, 500, 300);
		topPanel.setLayout(new BorderLayout(0, 0));
		JTextPane chooseSchTxtPn = new JTextPane();
		chooseSchTxtPn.setText("Choose Schedule to Display");
		chooseSchTxtPn.setEditable(false);
		topPanel.add(chooseSchTxtPn, BorderLayout.WEST);
		topPanel.add(possibleSchedules, BorderLayout.CENTER);
		this.add(topPanel, BorderLayout.NORTH);

		display=new ScheduleDisplay();
		this.add(new JScrollPane(display), BorderLayout.CENTER);

		possibleSchedules.setEnabled(false);
		possibleSchedules.addActionListener(new SchedSelectionListener(possibleSchedules, display));

		deleteScheduleBtn = new JButton("Delete Schedule");
		deleteScheduleBtn.setEnabled(false);
		deleteScheduleBtn.addActionListener(new deleteScheduleListener());
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
			if(BGCommander.getBGCommander().getSchedules().size()==0)possibleSchedules.removeAllItems();
			for(String sched:BGCommander.getBGCommander().getSchedules()){
				schedmodel.addElement(sched);
				possibleSchedules.setModel(schedmodel);
			}
		}catch(UserNotFoundException ex){possibleSchedules.removeAllItems();}
		catch(NullPointerException ex){}
	}

	private class deleteScheduleListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String schedid = (String)possibleSchedules.getSelectedItem();
			try{
				BGCommander.getBGCommander().deleteSchedule(schedid);
				UserInterface.getUserInterface().refreshDisplay();
			}catch(ElementNotFoundException ex){}
		}
	}
	public void activateButtons(){
		deleteScheduleBtn.setEnabled(true);
		possibleSchedules.setEnabled(true);
	}
	
	public void unactivateButtons(){
		deleteScheduleBtn.setEnabled(false);
		possibleSchedules.setEnabled(false);
	}
}
