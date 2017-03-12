package client.view.listeners;

import client.view.UserInterface;
import client.commander.BGCommander;
import javax.swing.JList;
import javax.swing.DefaultListModel;

public class DelButtonListener extends EventButtonListener{
    public DelButtonListener(JList list){
        super(list);
    }

    @Override protected void editDisplay(String evid){
        BGCommander.getBGCommander().unsubscribe(evid);
        DefaultListModel model = (DefaultListModel)super.eventsList.getModel();
        model.remove(model.indexOf(evid));
        UserInterface.getUserInterface().refreshDisplay();
    }
}
