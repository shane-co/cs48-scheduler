package client.view.listeners;

import client.view.UserInterface;
import client.commander.BGCommander;
import javax.swing.JComboBox;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class OrgSelectionListener implements ActionListener{
    private JComboBox orgBox;
    private DefaultListModel<String> availableOrgsList;
    public OrgSelectionListener(JComboBox box, ListModel list){
        orgBox=box;
        availableOrgsList=(DefaultListModel)list;
    }
    public void actionPerformed(ActionEvent e){
        String orgname = (String)orgBox.getSelectedItem();
        ArrayList<String> available = BGCommander.getBGCommander().search(orgname);
        availableOrgsList.removeAllElements();
        for(String ev:available){
            availableOrgsList.addElement(ev);
        }
        System.out.println("refreshing display");
        UserInterface.getUserInterface().refreshDisplay();
    }
}
