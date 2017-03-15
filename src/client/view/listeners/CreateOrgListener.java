package client.view.listeners;

import client.view.UserInterface;
import client.commander.BGCommander;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTable;

public class CreateOrgListener implements ActionListener{
    private JTextField id,orgip,port;
    private JTable table;
    public CreateOrgListener(JTextField i, JTextField ip, JTable table){
        id=i; orgip=ip; this.table = table;
    }
    public void actionPerformed(ActionEvent e){
    	//if(!existsInTable()){
        BGCommander.getBGCommander().addOrganization(id.getText(),orgip.getText());
        UserInterface.getUserInterface().refreshDisplay();
    	//}
    }
    /*public boolean existsInTable(){
    	int rowCount=table.getRowCount();
    	for (int i=0; i<rowCount; i++){
    		if (id.getText().equals((String)table.getValueAt(i,0).toString())) return true;
    	}
    	return false;
    }*/
}
