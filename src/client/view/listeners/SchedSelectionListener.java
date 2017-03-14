package client.view.listeners;

import client.view.UserInterface;
import client.commander.BGCommander;
import client.view.ScheduleDisplay;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class SchedSelectionListener implements ActionListener{
    private JComboBox orgBox;
    private DefaultListModel<String> availableOrgsList;
    public SchedSelectionListener(JComboBox box, ListModel list){
        orgBox=box;
        availableOrgsList=(DefaultListModel)list;
    }
    public void actionPerformed(ActionEvent e){
        String orgname = (String)orgBox.getSelectedItem();
        ArrayList<String> available = BGCommander.getBGCommander().getSchedules();
        availableOrgsList.removeAllElements();
        for(String ev:available){
            availableOrgsList.addElement(ev);
        }
        UserInterface.getUserInterface().refreshDisplay();
    }
}
