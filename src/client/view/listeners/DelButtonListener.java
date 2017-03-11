package client.view.listeners;

import client.view.UserInterface;
import client.commander.BGCommander;
import javax.swing.JList;

public class DelButtonListener extends EventButtonListener{
    public DelButtonListener(JList list){
        super(list);
    }

    @Override protected void editDisplay(String evid){
        BGCommander.getBGCommander().unsubscribe(evid);
        UserInterface.getUserInterface().refreshDisplay();
    }
}
