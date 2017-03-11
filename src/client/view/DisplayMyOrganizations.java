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
import java.awt.GridLayout;

public class DisplayMyOrganizations extends JSplitPane{
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JPanel rightColumnPanel;
	private String[] columnNames = {"Organization Name", "I.P.", "Port"};
	
	public DisplayMyOrganizations() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		leftPanel = new JPanel();
		leftPanel.setBounds(100, 100, 500, 300);
		leftPanel.setLayout(new BorderLayout(0, 0));
		
		Object[][] data = {{"blaugh", "a", "b"}};
		JTable organizationDisplay = new JTable(data, columnNames);
		JScrollPane scroll = new JScrollPane(organizationDisplay);
		leftPanel.add(scroll, BorderLayout.CENTER);
		
		JButton removeOrgBtn = new JButton("Remove Organization");
		leftPanel.add(removeOrgBtn, BorderLayout.SOUTH);
		
		rightPanel = new JPanel();
		rightPanel.setBounds(100, 100, 500, 300);
		rightPanel.setLayout(new BorderLayout(0, 0));
		rightColumnPanel = new JPanel();
		rightColumnPanel.setBounds(100, 100, 500, 300);
		rightColumnPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JTextPane nameTxtPn = new JTextPane();
		nameTxtPn.setText("Organization Name : ");
		nameTxtPn.setEditable(false);
		rightColumnPanel.add(nameTxtPn);
		
		JTextPane nameInputTxtPn = new JTextPane();
		rightColumnPanel.add(nameInputTxtPn);
		
		JTextPane ipTxtPn = new JTextPane();
		ipTxtPn.setText("I.P. Address : ");
		ipTxtPn.setEditable(false);
		rightColumnPanel.add(ipTxtPn);
		
		JTextPane ipInputTxtPn = new JTextPane();
		rightColumnPanel.add(ipInputTxtPn);
		
		JTextPane portTxtPn = new JTextPane();
		portTxtPn.setText("Port : ");
		portTxtPn.setEditable(false);
		rightColumnPanel.add(portTxtPn);
		
		JTextPane portInputTxtPn = new JTextPane();
		rightColumnPanel.add(portInputTxtPn);
		
		rightPanel.add(rightColumnPanel, BorderLayout.CENTER);
		
		JButton addHostedEventBtn = new JButton("Add to Hosted Events");
		rightPanel.add(addHostedEventBtn, BorderLayout.SOUTH);
		
		//add both leftPanel and rightPanel to this
		this.setLeftComponent(leftPanel);
		this.setRightComponent(rightPanel);
		this.setResizeWeight(0.5);
		
	}
}
