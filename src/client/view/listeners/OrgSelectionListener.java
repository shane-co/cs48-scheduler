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
    private DefaultListModel<String> availableEvsList;
    public OrgSelectionListener(JComboBox box, ListModel list){
        orgBox=box;
        availableEvsList=(DefaultListModel)list;
    }
    public void actionPerformed(ActionEvent e){
        try{
            String orgname = (String)orgBox.getSelectedItem();
            if(orgname==null){availableEvsList.removeAllElements();}
            else{
                ArrayList<String> available = BGCommander.getBGCommander().search(orgname);
                availableEvsList.removeAllElements();
                for(String ev:available){
                    availableEvsList.addElement(ev);
                }
            }
                UserInterface.getUserInterface().refreshDisplay();
        }catch(NullPointerException ex){}
        catch(ArrayIndexOutOfBoundsException ex2){}
    }
}
