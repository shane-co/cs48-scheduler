package client.view.listeners;

import client.view.UserInterface;
import client.commander.BGCommander;
import javax.swing.JList;
import javax.swing.DefaultListModel;

public class DelButtonListener extends EventButtonListener{
    private String field;
    public DelButtonListener(JList list, String f){
        super(list);
        field=f;
    }

    @Override protected void editDisplay(String evid){
        BGCommander.getBGCommander().deleteFromField(evid,field);
        DefaultListModel model = (DefaultListModel)super.eventsList.getModel();
        model.remove(model.indexOf(evid));
        UserInterface.getUserInterface().refreshDisplay();
    }
}
