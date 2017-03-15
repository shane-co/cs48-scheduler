package client.view.listeners;

import client.view.UserInterface;
import client.commander.BGCommander;
import client.app.obj.ScheduleEvent;
import client.app.exceptions.UserNotFoundException;
import javax.swing.JList;
import javax.swing.JTextPane;
import client.app.exceptions.ElementNotFoundException;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class InfoListener implements ListSelectionListener{
	JList list;
	JTextPane txtField;
	boolean hosted;
    public InfoListener(JList list, JTextPane txtField, boolean h){
        this.list = list;
        this.txtField = txtField;
		hosted = h;
    }

    public void valueChanged(ListSelectionEvent e) {
    	try{
			String event = (String)list.getSelectedValue();
	    	if(hosted) txtField.setText(BGCommander.getBGCommander().getHEventInfo(event));
			else txtField.setText(BGCommander.getBGCommander().getEventInfo(event));
	    	UserInterface.getUserInterface().refreshDisplay();
		}catch(UserNotFoundException ex){}
    }


}
