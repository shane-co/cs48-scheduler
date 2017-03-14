package client.view.listeners;

import client.view.UserInterface;
import client.commander.BGCommander;
import client.app.obj.ScheduleEvent;
import javax.swing.JList;
import javax.swing.JTextField;
import client.app.exceptions.ElementNotFoundException;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class InfoListener implements ListSelectionListener{
	JList list;
	JTextField txtField;
    public InfoListener(JList list, JTextField txtField){
        this.list = list;
        this.txtField = txtField;
    }

    public void valueChanged(ListSelectionEvent e) {
    	String event = (String)list.getSelectedValue();
    	txtField.setText("I.D. : " + event);
    	UserInterface.getUserInterface().refreshDisplay();
    }
    

}
