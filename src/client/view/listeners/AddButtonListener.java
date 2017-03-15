package client.view.listeners;

import client.view.UserInterface;
import client.commander.BGCommander;
import javax.swing.JList;
import client.app.exceptions.ElementNotFoundException;


public class AddButtonListener extends EventButtonListener{
    public AddButtonListener(JList list){
        super(list);
    }

    @Override protected void editDisplay(String evid){
    	try{
    		BGCommander.getBGCommander().subscribeEvent(evid);
     		UserInterface.getUserInterface().refreshDisplay();
    	}
    	catch  (ElementNotFoundException e){}
    }
}
