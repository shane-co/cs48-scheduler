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

public class DisplayHostedEvents extends JSplitPane{
	private JSplitPane leftPanel;
	private JPanel topLeftPanel;
	private JPanel hostedEventsPanel;
	private JPanel additionalInformationPanel;
	private JPanel rightPanel;
	private JPanel rightColumnPanel;
	public DisplayHostedEvents() {
		initialize();
	}
	public void initialize(){
		
		//make topLeftPanel
		topLeftPanel = new JPanel();
		topLeftPanel.setBounds(100, 100, 500, 300);
		topLeftPanel.setLayout(new BorderLayout(0, 0));
		JTextPane hostedEventsTxtPn = new JTextPane();
		hostedEventsTxtPn.setText("Hosted Events");
		topLeftPanel.add(hostedEventsTxtPn, BorderLayout.NORTH);
		
		String[] hostedEvents = {"a", "b", "c"};
		JList hostedEventsList = new JList(hostedEvents);
		JScrollPane scroll = new JScrollPane(hostedEventsList);
		topLeftPanel.add(hostedEventsList, BorderLayout.CENTER);
		
		//make additionalInformationPanel
		additionalInformationPanel = new JPanel();
		additionalInformationPanel.setBounds(100, 100, 500, 300);
		additionalInformationPanel.setLayout(new BorderLayout(0, 0));
		
		JTextPane addInfoTxtPn = new JTextPane();
		addInfoTxtPn.setText("Additional Information");
		additionalInformationPanel.add(addInfoTxtPn, BorderLayout.NORTH);
		
		JTextField addInfoTxtFld = new JTextField();
		additionalInformationPanel.add(addInfoTxtFld, BorderLayout.CENTER);
		
		JButton removeHostedEventBtn = new JButton("Remove Hosted Event");
		additionalInformationPanel.add(removeHostedEventBtn, BorderLayout.SOUTH);
		
		//make left panel
		leftPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topLeftPanel, additionalInformationPanel);
		
		//make rightPanel
		rightPanel = new JPanel();
		rightPanel.setBounds(100, 100, 500, 300);
		rightPanel.setLayout(new BorderLayout(0, 0));
		
		JTextPane addHostedEventTxtPn = new JTextPane();
		addHostedEventTxtPn.setText("Add Hosted Event");
		rightPanel.add(addHostedEventTxtPn, BorderLayout.NORTH);
		
		rightColumnPanel = new JPanel();
		rightColumnPanel.setBounds(100, 100, 500, 300);
		rightColumnPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JTextPane idTxtPn = new JTextPane();
		idTxtPn.setText("Input ID : ");
		idTxtPn.setEditable(false);
		rightColumnPanel.add(idTxtPn);
		
		JTextPane idInputTxtPn = new JTextPane();
		rightColumnPanel.add(idInputTxtPn);
		
		JTextField descriptionTxtFld = new JTextField();
		descriptionTxtFld.setText("Description : ");
		descriptionTxtFld.setEditable(false);
		rightColumnPanel.add(descriptionTxtFld);
		
		JTextField descriptionInputTxtFld = new JTextField();
		rightColumnPanel.add(descriptionInputTxtFld);
		
		JTextPane monTxtPn = new JTextPane();
		monTxtPn.setText("Monday Times : ");
		monTxtPn.setEditable(false);
		rightColumnPanel.add(monTxtPn);
		
		JTextPane monInputTxtPn = new JTextPane();
		rightColumnPanel.add(monInputTxtPn);
		
		JTextPane tueTxtPn = new JTextPane();
		tueTxtPn.setText("Tuesday Times : ");
		tueTxtPn.setEditable(false);
		rightColumnPanel.add(tueTxtPn);
		
		JTextPane tueInputTxtPn = new JTextPane();
		rightColumnPanel.add(tueInputTxtPn);
		
		JTextPane wedTxtPn = new JTextPane();
		wedTxtPn.setText("Wednesday Times : ");
		wedTxtPn.setEditable(false);
		rightColumnPanel.add(wedTxtPn);
		
		JTextPane wedInputTxtPn = new JTextPane();
		rightColumnPanel.add(wedInputTxtPn);
		
		JTextPane thrTxtPn = new JTextPane();
		thrTxtPn.setText("Thursday Times : ");
		thrTxtPn.setEditable(false);
		rightColumnPanel.add(thrTxtPn);
		
		JTextPane thrInputTxtPn = new JTextPane();
		rightColumnPanel.add(thrInputTxtPn);
		
		JTextPane friTxtPn = new JTextPane();
		friTxtPn.setText("Friday Times : ");
		friTxtPn.setEditable(false);
		rightColumnPanel.add(friTxtPn);
		
		JTextPane friInputTxtPn = new JTextPane();
		rightColumnPanel.add(friInputTxtPn);
		
		JTextPane satTxtPn = new JTextPane();
		satTxtPn.setText("Saturday Times : ");
		satTxtPn.setEditable(false);
		rightColumnPanel.add(satTxtPn);
		
		JTextPane satInputTxtPn = new JTextPane();
		rightColumnPanel.add(satInputTxtPn);
		
		JTextPane sunTxtPn = new JTextPane();
		sunTxtPn.setText("Sunday Times : ");
		sunTxtPn.setEditable(false);
		rightColumnPanel.add(sunTxtPn);
		
		JTextPane sunInputTxtPn = new JTextPane();
		rightColumnPanel.add(sunInputTxtPn);
		
		rightPanel.add(rightColumnPanel, BorderLayout.CENTER);
		
		
		JButton addHostedEventBtn= new JButton("Add Hosted Event");
		rightPanel.add(addHostedEventBtn, BorderLayout.SOUTH);
		
		//add them to this
		
		this.setLeftComponent(leftPanel);
		this.setRightComponent(rightPanel);
		this.setResizeWeight(0.5);
	}
}
