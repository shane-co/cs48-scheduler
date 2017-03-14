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
    private JComboBox schedBox;
    private ScheduleDisplay currSched;
    public SchedSelectionListener(JComboBox box, ScheduleDisplay display){
        schedBox=box;
        currSched=display;
    }
    public void actionPerformed(ActionEvent e){

    }
}
