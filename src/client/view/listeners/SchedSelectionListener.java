package client.view.listeners;

import client.view.UserInterface;
import client.commander.BGCommander;
import client.view.ScheduleDisplay;
import client.app.exceptions.UserNotFoundException;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class SchedSelectionListener implements ActionListener{
    private JComboBox schedBox;
    private ScheduleDisplay currDisplay;
    public SchedSelectionListener(JComboBox box, ScheduleDisplay display){
        schedBox=box;
        currDisplay=display;
    }
    public void actionPerformed(ActionEvent e){
        String schedName = (String)schedBox.getSelectedItem();
        try{
            currDisplay.setSchedule(BGCommander.getBGCommander().getSchedToDisplay(schedName));
        }catch(UserNotFoundException ex){}
    }
}
