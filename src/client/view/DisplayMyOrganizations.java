package client.view;
import client.commander.BGCommander;
import client.app.obj.*;
import client.app.exceptions.*;
import client.view.listeners.CreateOrgListener;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.awt.GridLayout;

public class DisplayMyOrganizations extends JSplitPane implements DisplayScheduleComponent{
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JPanel rightColumnPanel;
	private String[] columnNames = {"Organization Name", "I.P.", "Port"};
	private JTable organizationDisplay;

	public DisplayMyOrganizations() {
		leftPanel = new JPanel();
		leftPanel.setBounds(100, 100, 500, 300);
		leftPanel.setLayout(new BorderLayout(0, 0));

		organizationDisplay = new JTable();
		DefaultTableModel model =(DefaultTableModel)organizationDisplay.getModel();
		for(int i=0; i<3; i++){model.addColumn(columnNames[i]);}
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

		JTextField nameInputTxtPn = new JTextField();
		rightColumnPanel.add(nameInputTxtPn);

		JTextPane ipTxtPn = new JTextPane();
		ipTxtPn.setText("I.P. Address : ");
		ipTxtPn.setEditable(false);
		rightColumnPanel.add(ipTxtPn);

		JTextField ipInputTxtPn = new JTextField();
		rightColumnPanel.add(ipInputTxtPn);

		rightPanel.add(rightColumnPanel, BorderLayout.CENTER);

		JButton addHostedEventBtn = new JButton("Add to Hosted Events");
		addHostedEventBtn.addActionListener(new CreateOrgListener(nameInputTxtPn,ipInputTxtPn));
		rightPanel.add(addHostedEventBtn, BorderLayout.SOUTH);

		//add both leftPanel and rightPanel to this
		this.setLeftComponent(leftPanel);
		this.setRightComponent(rightPanel);
		this.setResizeWeight(0.5);
	}

	public void refresh(){
		DefaultTableModel model = (DefaultTableModel) organizationDisplay.getModel();
		//initialize display columnNames
		try{
			for(DatabaseConnection d:BGCommander.getBGCommander().getOrgs()){
				String [] data = {d.getID(), d.getIP(), Integer.toString(d.getPort())};
				model.addRow(data);
			}
		}catch(UserNotFoundException e){}
	}
}
