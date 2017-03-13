package client.view.listeners;

import client.view.UserInterface;
import client.commander.BGCommander;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.util.ArrayList;
public class CreateHostedListener implements ActionListener{
	private String id,description,sunday,monday,tuesday,wednesday,thursday,friday,saturday;
	public CreateHostedListener(JTextField i,
				JTextField desc,
				JTextField sun,
				JTextField mon,
				JTextField tues,
				JTextField wed,
				JTextField thurs,
				JTextField fri,
				JTextField sat){
		id=i.getText();
		description=desc.getText();
		sunday=sun.getText();
		monday=mon.getText();
		tuesday=tues.getText();
		wednesday=wed.getText();
		thursday=thurs.getText();
		friday=fri.getText();
		saturday=sat.getText();
	}

	public void actionPerformed(ActionEvent e){
		try{
		ArrayList<ArrayList<Integer>> duration = new ArrayList<ArrayList<Integer>>();
		for(int i=0; i<7; i++){
			ArrayList<Integer> day=new ArrayList<Integer>();
			switch(i){
				case 0: for(String s: sunday.split(",")){day.add(Integer.parseInt(s));}break;
				case 1:for(String s: monday.split(",")){day.add(Integer.parseInt(s));}break;
				case 2:for(String s: tuesday.split(",")){day.add(Integer.parseInt(s));}break;
				case 3:for(String s: wednesday.split(",")){day.add(Integer.parseInt(s));}break;
				case 4:for(String s: thursday.split(",")){day.add(Integer.parseInt(s));}break;
				case 5:for(String s: friday.split(",")){day.add(Integer.parseInt(s));}break;
				case 6:for(String s: saturday.split(",")){day.add(Integer.parseInt(s));}break;

			}
			duration.add(day);
			BGCommander.getBGCommander().createHostedEvent(duration,id,description);	
		}
		}catch(NumberFormatException ex){}
		catch(NullPointerException n){}

	}
}
