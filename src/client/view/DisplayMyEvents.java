package client.view;
import client.commander.BGCommander;
import client.app.exceptions.*;
import client.view.listeners.AddButtonListener;
import client.view.listeners.DelButtonListener;
import client.view.listeners.OrgSelectionListener;
import client.view.listeners.GenScheduleListener;
import client.view.listeners.InfoListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.util.ArrayList;


public class DisplayMyEvents extends JSplitPane implements DisplayScheduleComponent{
	private BGCommander commander;
	//display panes
	private JSplitPane leftPanel;
	private JPanel leftAdditionalInfoPanel;
	private JPanel myEventsListPanel;
	private JSplitPane rightPanel;
	private JPanel rightAdditionalInfoPanel;
	private JPanel availableEventsListPanel;
	private JPanel upperRightPanel;
	//mutable content
	private JList myEventsList;
	private JList availableEventsList;
	private JTextPane leftAddInfoTxtFld;
	private JTextPane rightAddInfoTxtFld;
	private JComboBox availableOrgs;
	//buttons
	private JButton genScheduleBtn;
	private JButton removeEventsBtn;
	private JButton addEventsBtn;

	//Constructor to assemble all the display panes
	public DisplayMyEvents() {
		commander = BGCommander.getBGCommander();
		initialize();

		//makes myEventsListPanel
		myEventsListPanel = new JPanel();
			myEventsListPanel.setBounds(100, 100, 500, 300);
			myEventsListPanel.setLayout(new BorderLayout(0, 0));
			JTextPane myEventsTxtPn = new JTextPane();
			myEventsTxtPn.setText("My Events");
			myEventsTxtPn.setEditable(false);
			JScrollPane scroll = new JScrollPane(myEventsList);
		myEventsListPanel.add(scroll, BorderLayout.CENTER);
		myEventsListPanel.add(myEventsTxtPn, BorderLayout.NORTH);

		//makes additionalInfoPanel
		leftAdditionalInfoPanel = new JPanel();
			leftAdditionalInfoPanel.setBounds(100, 100, 500, 300);
			leftAdditionalInfoPanel.setLayout(new BorderLayout(0, 0));
			JTextPane leftAddInfoTxtPn = new JTextPane();
			leftAddInfoTxtPn.setText("Additional Information");
			leftAddInfoTxtPn.setEditable(false);
			JPanel eventActions = new JPanel();
			eventActions.setLayout(new BorderLayout());
			genScheduleBtn = new JButton("Generate Schedule");
			genScheduleBtn.setEnabled(false);
			removeEventsBtn = new JButton("Remove Event");
			removeEventsBtn.setEnabled(false);
			genScheduleBtn.addActionListener(new GenScheduleListener());
			removeEventsBtn.addActionListener(new DelButtonListener(myEventsList,"event"));
			eventActions.add(genScheduleBtn,BorderLayout.NORTH);
			eventActions.add(removeEventsBtn,BorderLayout.SOUTH);
			leftAddInfoTxtFld.setEditable(false);
		leftAdditionalInfoPanel.add(leftAddInfoTxtPn, BorderLayout.NORTH);
		leftAdditionalInfoPanel.add(new JScrollPane(leftAddInfoTxtFld), BorderLayout.CENTER);
		leftAdditionalInfoPanel.add(eventActions, BorderLayout.SOUTH);

		//makes leftPanel
		leftPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, myEventsListPanel, leftAdditionalInfoPanel);
		leftPanel.setResizeWeight(0.5);

		//makes availableEventsListPanel
		availableEventsListPanel = new JPanel();
			availableEventsListPanel.setBounds(100, 100, 500, 300);
			availableEventsListPanel.setLayout(new BorderLayout(0, 0));
			availableEventsList.addListSelectionListener( new InfoListener(availableEventsList, rightAddInfoTxtFld, "local"));
			JScrollPane a = new JScrollPane(availableEventsList);
		availableEventsListPanel.add(a, BorderLayout.CENTER);

		//makes upperRightPanel
		upperRightPanel = new JPanel();
			upperRightPanel.setBounds(100, 100, 500, 300);
			upperRightPanel.setLayout(new BorderLayout(0, 0));
			JPanel topEventsListPanel = new JPanel();
				topEventsListPanel.setBounds(100, 100, 500, 300);
				topEventsListPanel.setLayout(new BorderLayout(0, 0));
				JTextPane availableEventsTxtPn = new JTextPane();
				availableEventsTxtPn.setText("Available Events");
				availableEventsTxtPn.setEditable(false);
				JTextPane availableOrgsTxtPn = new JTextPane();
				availableOrgsTxtPn.setText("Available Orgs.");
				availableOrgsTxtPn.setEditable(false);
			topEventsListPanel.add(availableEventsTxtPn, BorderLayout.NORTH);
			topEventsListPanel.add(availableOrgs, BorderLayout.CENTER);
			topEventsListPanel.add(availableOrgsTxtPn, BorderLayout.WEST);
		upperRightPanel.add(topEventsListPanel, BorderLayout.NORTH);
		upperRightPanel.add(availableEventsListPanel, BorderLayout.CENTER);

		//makes rightAdditionalInfoPanel
		rightAdditionalInfoPanel = new JPanel();
			rightAdditionalInfoPanel.setBounds(100, 100, 500, 300);
			rightAdditionalInfoPanel.setLayout(new BorderLayout(0, 0));
			JTextPane rightAddInfoTxtPn = new JTextPane();
			rightAddInfoTxtPn.setText("Additional Information");
			rightAddInfoTxtPn.setEditable(false);
			rightAddInfoTxtFld.setEditable(false);
			rightAdditionalInfoPanel.add(rightAddInfoTxtFld, BorderLayout.CENTER);
			addEventsBtn = new JButton("Add Event");
			addEventsBtn.setEnabled(false);
			addEventsBtn.addActionListener(new AddButtonListener(availableEventsList));
		rightAdditionalInfoPanel.add(rightAddInfoTxtPn, BorderLayout.NORTH);
		rightAdditionalInfoPanel.add(new JScrollPane(rightAddInfoTxtFld), BorderLayout.CENTER);
		rightAdditionalInfoPanel.add(addEventsBtn, BorderLayout.SOUTH);

		//makes rightPanel
		rightPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, upperRightPanel, rightAdditionalInfoPanel);
		rightPanel.setResizeWeight(0.5);

		//makes full panel
		this.setLeftComponent(leftPanel);
		this.setRightComponent(rightPanel);
		this.setResizeWeight(.5);
	}

	/**
	 * Initialize the mutable contents of the panel.
	 */
	private void initialize() {
		myEventsList = new JList(new DefaultListModel<String>());
		leftAddInfoTxtFld = new JTextPane();
		availableOrgs = new JComboBox(new DefaultComboBoxModel());
		availableEventsList = new JList(new DefaultListModel());
		rightAddInfoTxtFld = new JTextPane();

		availableOrgs.addActionListener(new OrgSelectionListener(availableOrgs,availableEventsList.getModel()));
		availableEventsList.addListSelectionListener(new InfoListener(availableEventsList, rightAddInfoTxtFld, "remote"));
		myEventsList.addListSelectionListener( new InfoListener(myEventsList, leftAddInfoTxtFld, "local"));
	}
	public void refresh(){
		DefaultListModel evmodel = (DefaultListModel)myEventsList.getModel();
		DefaultListModel evmodel2 = (DefaultListModel)availableEventsList.getModel();
		try{
			ArrayList<String> evlist = commander.getScheduleEvents();
			for(String ev:evlist){
				if(!evmodel.contains(ev))evmodel.addElement(ev);
			}
			DefaultComboBoxModel orgmodel = new DefaultComboBoxModel();
			if(availableOrgs.getItemCount() != commander.getOrgNames().size()){
				if(BGCommander.getBGCommander().getOrgNames().size()==0)availableOrgs.removeAllItems();
				for(String org:commander.getOrgNames()){
					orgmodel.addElement(org);
					availableOrgs.setModel(orgmodel);
				}
			}

		}catch(UserNotFoundException e){
			evmodel.clear();
			evmodel2.clear();
			availableOrgs.removeAllItems();
			leftAddInfoTxtFld.setText("");
			rightAddInfoTxtFld.setText("");
		}
	}
	
	public void activateButtons(){
		genScheduleBtn.setEnabled(true);
		removeEventsBtn.setEnabled(true);
		addEventsBtn.setEnabled(true);
	}
	
	public void unactivateButtons(){
		genScheduleBtn.setEnabled(false);
		removeEventsBtn.setEnabled(false);
		addEventsBtn.setEnabled(false);
	}

}
