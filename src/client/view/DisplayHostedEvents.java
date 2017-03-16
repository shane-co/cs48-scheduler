package client.view;
import client.commander.BGCommander;
import client.app.obj.*;
import client.app.exceptions.*;
import client.view.listeners.CreateHostedListener;
import client.view.listeners.DelButtonListener;
import client.view.listeners.InfoListener;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.awt.GridLayout;

public class DisplayHostedEvents extends JSplitPane implements DisplayScheduleComponent{
	private JSplitPane leftPanel;
	private JPanel topLeftPanel;
	private JPanel additionalInformationPanel;
	private JPanel rightPanel;
	private JPanel rightColumnPanel;
	//mutable fields
	private JList hostedEventsList;

	//user input fields
	private JTextField idInputTxtPn;
	private JTextField descriptionInputTxtFld;
	private JTextField sunInputTxtPn;
	private	JTextField monInputTxtPn;
	private	JTextField tueInputTxtPn;
	private	JTextField wedInputTxtPn;
	private	JTextField thrInputTxtPn;
	private	JTextField friInputTxtPn;
	private	JTextField satInputTxtPn;
	private JTextPane addInfoTxtFld;

	public DisplayHostedEvents() {
		initialize();

		//make topLeftPanel
		topLeftPanel = new JPanel();
			topLeftPanel.setBounds(100, 100, 500, 300);
			topLeftPanel.setLayout(new BorderLayout(0, 0));
			JTextPane hostedEventsTxtPn = new JTextPane();
			hostedEventsTxtPn.setText("Hosted Events");
			hostedEventsTxtPn.setEditable(false);
			JScrollPane scroll = new JScrollPane(hostedEventsList);
		topLeftPanel.add(scroll, BorderLayout.CENTER);
		topLeftPanel.add(hostedEventsTxtPn, BorderLayout.NORTH);

		//make additionalInformationPanel
		additionalInformationPanel = new JPanel();
			additionalInformationPanel.setBounds(100, 100, 500, 300);
			additionalInformationPanel.setLayout(new BorderLayout(0, 0));
			JTextPane addInfoTxtPn = new JTextPane();
			addInfoTxtPn.setText("Additional Information");
			addInfoTxtPn.setEditable(false);

			addInfoTxtFld = new JTextPane();
			addInfoTxtFld.setEditable(false);
			JButton removeHostedEventBtn = new JButton("Remove Hosted Event");
			removeHostedEventBtn.addActionListener(new DelButtonListener(hostedEventsList,"hosted"));
		additionalInformationPanel.add(removeHostedEventBtn, BorderLayout.SOUTH);
		additionalInformationPanel.add(addInfoTxtPn, BorderLayout.NORTH);
		additionalInformationPanel.add(new JScrollPane(addInfoTxtFld), BorderLayout.CENTER);
		hostedEventsList.addListSelectionListener( new InfoListener(hostedEventsList, addInfoTxtFld, "hosted"));
		//make left panel
		leftPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topLeftPanel, additionalInformationPanel);
		leftPanel.setDividerLocation(.5);
		//make rightPanel
		rightPanel = new JPanel();
		rightPanel.setBounds(100, 100, 500, 300);
		rightPanel.setLayout(new BorderLayout(0, 0));

		JTextPane addHostedEventTxtPn = new JTextPane();
		addHostedEventTxtPn.setText("Add Hosted Event (Duration must be a comma seperated list)");
		addHostedEventTxtPn.setEditable(false);
		rightPanel.add(addHostedEventTxtPn, BorderLayout.NORTH);

		rightColumnPanel = new JPanel();
		rightColumnPanel.setBounds(100, 100, 500, 300);
		rightColumnPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JTextPane idTxtPn = new JTextPane();
		idTxtPn.setText("Input ID : ");
		idTxtPn.setEditable(false);
		rightColumnPanel.add(idTxtPn);

		rightColumnPanel.add(idInputTxtPn);

		JTextPane descriptionTxtFld = new JTextPane();
		descriptionTxtFld.setText("Description : ");
		descriptionTxtFld.setEditable(false);
		rightColumnPanel.add(descriptionTxtFld);

		rightColumnPanel.add(descriptionInputTxtFld);

		JTextPane sunTxtPn = new JTextPane();
		sunTxtPn.setText("Sunday Times : ");
		sunTxtPn.setEditable(false);
		rightColumnPanel.add(sunTxtPn);

		rightColumnPanel.add(sunInputTxtPn);

		JTextPane monTxtPn = new JTextPane();
		monTxtPn.setText("Monday Times : ");
		monTxtPn.setEditable(false);
		rightColumnPanel.add(monTxtPn);

		rightColumnPanel.add(monInputTxtPn);

		JTextPane tueTxtPn = new JTextPane();
		tueTxtPn.setText("Tuesday Times : ");
		tueTxtPn.setEditable(false);
		rightColumnPanel.add(tueTxtPn);

		rightColumnPanel.add(tueInputTxtPn);

		JTextPane wedTxtPn = new JTextPane();
		wedTxtPn.setText("Wednesday Times : ");
		wedTxtPn.setEditable(false);
		rightColumnPanel.add(wedTxtPn);

		rightColumnPanel.add(wedInputTxtPn);

		JTextPane thrTxtPn = new JTextPane();
		thrTxtPn.setText("Thursday Times : ");
		thrTxtPn.setEditable(false);
		rightColumnPanel.add(thrTxtPn);

		rightColumnPanel.add(thrInputTxtPn);

		JTextPane friTxtPn = new JTextPane();
		friTxtPn.setText("Friday Times : ");
		friTxtPn.setEditable(false);
		rightColumnPanel.add(friTxtPn);

		rightColumnPanel.add(friInputTxtPn);

		JTextPane satTxtPn = new JTextPane();
		satTxtPn.setText("Saturday Times : ");
		satTxtPn.setEditable(false);
		rightColumnPanel.add(satTxtPn);

		rightColumnPanel.add(satInputTxtPn);

		rightPanel.add(rightColumnPanel, BorderLayout.CENTER);


		JButton addHostedEventBtn= new JButton("Add Hosted Event");
		addHostedEventBtn.addActionListener(new CreateHostedListener(
			idInputTxtPn, descriptionInputTxtFld, sunInputTxtPn, monInputTxtPn, tueInputTxtPn,
			wedInputTxtPn, thrInputTxtPn, friInputTxtPn, satInputTxtPn, hostedEventsList
		));
		rightPanel.add(addHostedEventBtn, BorderLayout.SOUTH);

		//add them to this

		this.setLeftComponent(leftPanel);
		this.setRightComponent(rightPanel);
		this.setResizeWeight(0.5);
	}
	public void initialize(){
		hostedEventsList = new JList(new DefaultListModel<String>());
		idInputTxtPn = new JTextField();
		descriptionInputTxtFld = new JTextField();
		sunInputTxtPn = new JTextField();
		monInputTxtPn = new JTextField();
		tueInputTxtPn = new JTextField();
		wedInputTxtPn = new JTextField();
		thrInputTxtPn = new JTextField();
		friInputTxtPn = new JTextField();
		satInputTxtPn = new JTextField();
	}
	public void refresh(){
		DefaultListModel hostedModel = (DefaultListModel) hostedEventsList.getModel();
		try{
			for(String ev:BGCommander.getBGCommander().getHosted()){
				if(!hostedModel.contains(ev)) hostedModel.addElement(ev);
			}

		}catch(UserNotFoundException e){
			hostedModel.removeAllElements();
			addInfoTxtFld.setText("");
			idInputTxtPn.setText("");
			descriptionInputTxtFld.setText("");
			sunInputTxtPn.setText("");
			monInputTxtPn.setText("");
			tueInputTxtPn.setText("");
			wedInputTxtPn.setText("");
			thrInputTxtPn.setText("");
			friInputTxtPn.setText("");
			satInputTxtPn.setText("");
		}
	}

	}
