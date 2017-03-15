package client.view.listeners;

import client.view.UserInterface;
import client.commander.BGCommander;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class CreateOrgListener implements ActionListener{
    private JTextField id,orgip,port;
    public CreateOrgListener(JTextField i, JTextField ip){
        id=i; orgip=ip;
    }
    public void actionPerformed(ActionEvent e){
        BGCommander.getBGCommander().addOrganization(id.getText(),orgip.getText());
        UserInterface.getUserInterface().refreshDisplay();
    }
}
