package client.view.listeners;

import client.view.UserInterface;
import client.commander.BGCommander;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class CreateOrgListener implements ActionListener{
    private JTextField id,orgip,port;
    public CreateOrgListener(JTextField i, JTextField ip, JTextField p){
        id=i; orgip=ip; port=p;
    }
    public void actionPerformed(ActionEvent e){
        BGCommander.getBGCommander().addOrganization(id.getText(),orgip.getText(),port.getText());
        UserInterface.getUserInterface().refreshDisplay();
    }
}
