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
	String source;
    public InfoListener(JList list, JTextPane txtField, String s){
        this.list = list;
        this.txtField = txtField;
		source=s;
    }

    public void valueChanged(ListSelectionEvent e) {
    	try{
			String event = (String)list.getSelectedValue();
		    switch(source){
				case "hosted": txtField.setText(BGCommander.getBGCommander().getHEventInfo(event));break;
				case "local": txtField.setText(BGCommander.getBGCommander().getEventInfo(event));break;
				case "remote": txtField.setText(BGCommander.getBGCommander().getRemoteEventInfo(event));break;
			}
	    	UserInterface.getUserInterface().refreshDisplay();
		}catch(UserNotFoundException ex){}
    }


}
