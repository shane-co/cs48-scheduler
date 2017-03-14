package client.view.listeners;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import client.commander.BGCommander;
import client.view.ScheduleDisplay;
import client.app.exceptions.ElementNotFoundException;
import client.app.obj.Schedule;

public class GenScheduleListener implements ActionListener{
    private JFrame generatorWindow;
    private JFrame displayWindow;
    private JTextField month;
    private JTextField day;
    public GenScheduleListener(){
        generatorWindow = new JFrame("Generating a Schedule");
        generatorWindow.setBounds(100, 100, 500, 100);
		generatorWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		generatorWindow.getContentPane().setLayout(new GridLayout(3,2));
        JTextPane monthPrompt = new JTextPane();
        monthPrompt.setText("Month this schedule begins");
        monthPrompt.setEditable(false);
        JTextPane dayPrompt = new JTextPane();
        dayPrompt.setText("Day of the month this schedule begins");
        dayPrompt.setEditable(false);
        month=new JTextField();
        day=new JTextField();
        JButton gen = new JButton("Generate");
        gen.addActionListener(new generateListener());
        generatorWindow.add(monthPrompt);
        generatorWindow.add(month);
        generatorWindow.add(dayPrompt);
        generatorWindow.add(day);
        generatorWindow.add(gen);
    }
    public void actionPerformed(ActionEvent e){
        generatorWindow.setVisible(true);
    }

    private class generateListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(!month.getText().equals("") && !day.getText().equals("")){
                int m=Integer.parseInt(month.getText());
                int d=Integer.parseInt(day.getText());
                try{
                    Schedule generated = BGCommander.getBGCommander().genSchedule(m,d);
                    ScheduleDisplay display = new ScheduleDisplay(generated);
                    JButton addSched = new JButton("Add this schedule");
                    addSched.addActionListener(new addListener(generated));
                    generatorWindow.dispose();
                    displayWindow = new JFrame("Schedule");
                    displayWindow.setBounds(100,100,1000,1000);
                    displayWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    displayWindow.getContentPane().setLayout(new BorderLayout());
                    displayWindow.add(display, BorderLayout.CENTER);
                    displayWindow.add(addSched, BorderLayout.SOUTH);
                    displayWindow.setVisible(true);
                }catch(ElementNotFoundException ex){
                    System.out.println("login first");
                }
            }
        }
    }

    private class addListener implements ActionListener{
        private Schedule candidate;
        public addListener(Schedule e){candidate=e;}
        public void actionPerformed(ActionEvent e){
            try{
                BGCommander.getBGCommander().addSchedule(candidate);
                displayWindow.dispose();
            }
            catch(ElementNotFoundException ex){System.out.println("login please");}
        }
    }
}
