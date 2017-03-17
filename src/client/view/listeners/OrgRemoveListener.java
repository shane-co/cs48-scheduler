package client.view.listeners;

import client.view.UserInterface;
import client.commander.BGCommander;
import javax.swing.JTable;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class OrgRemoveListener implements ActionListener{
	JTable table;
	public OrgRemoveListener(JTable table){
		this.table = table;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try{
			String orgToBeDeleted = table.getModel().getValueAt(table.getSelectedRow(),0).toString();
			BGCommander.getBGCommander().deleteFromField(orgToBeDeleted , "org");
			UserInterface.getUserInterface().refreshDisplay();
		}
		catch (ArrayIndexOutOfBoundsException exception){}
	}
}
