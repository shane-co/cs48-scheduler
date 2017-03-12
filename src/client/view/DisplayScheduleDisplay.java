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

public class DisplayScheduleDisplay extends JPanel{

	private JComboBox possibleSchedules;
	private BGCommander commander;
	private final String[] columnNames = {"ID", "DAY", "START DATE", "END DATE"};
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

		Object[][] data = {{"blaugh", "a", "b", "c"}};
		JTable schedule = new JTable(data, columnNames);
		JScrollPane scroll = new JScrollPane(schedule);
		this.add(scroll, BorderLayout.CENTER);

		JButton deleteScheduleBtn = new JButton("Delete Schedule");
		this.add(deleteScheduleBtn, BorderLayout.SOUTH);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		possibleSchedules = new JComboBox();
	}

	public void refresh(){}

}
