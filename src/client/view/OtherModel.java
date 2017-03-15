package client.view;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class OtherModel extends DefaultTableModel{
	OtherModel(){
		//standard initialization
		super();
	}
	
	//this is literally here to make it so rows are not editable by default
	@Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
