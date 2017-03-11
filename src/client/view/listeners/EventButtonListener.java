package client.view.listeners;

import client.view.UserInterface;
import client.commander.BGCommander;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public abstract class EventButtonListener implements ActionListener{
    private JList eventsList;
    public EventButtonListener(JList list){
        eventsList=list;
    }
    public void actionPerformed(ActionEvent e){
        String eventid = (String)eventsList.getSelectedValue();
        editDisplay(eventid);
    }

    abstract void editDisplay(String evid);
}
