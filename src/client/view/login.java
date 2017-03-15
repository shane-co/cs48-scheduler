package client.view;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import client.app.exceptions.*;
import client.commander.BGCommander;

public class login extends JPanel{

	private JLabel lblUsername;
	private JLabel lblPassword;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JLabel lblprompt;
	private BGCommander command;
	private UserInterface ui;
	private final JPanel panel=this;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public login(UserInterface parent) {
		command = BGCommander.getBGCommander();
		ui=parent;
		initialize();
	}

	/**
	 * Initialize the contents of the panel.
	 */
	private void initialize() {
		this.setLayout(new BorderLayout());

		lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsername.setBounds(71, 67, 88, 27);

		lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(71, 105, 88, 27);

		usernameField = new JTextField();
		usernameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		usernameField.setBounds(161, 72, 153, 22);
		usernameField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.addActionListener(new loginButtonListener());
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordField.setBounds(161, 110, 152, 22);

		lblprompt = new JLabel("Enter username and password", SwingConstants.CENTER);
		lblprompt.setFont(new Font("Sylfaen", Font.PLAIN, 14));
		lblprompt.setBounds(71, 22, 250, 34);

		this.add(lblprompt,BorderLayout.NORTH);
		this.add(lblUsername,BorderLayout.CENTER);
		this.add(usernameField,BorderLayout.CENTER);
		this.add(lblPassword,BorderLayout.CENTER);
		this.add(passwordField,BorderLayout.CENTER);
		this.add(new JPanel(),BorderLayout.CENTER);

		JButton btnlogin = new JButton("Log in");
		btnlogin.addActionListener(new loginButtonListener());
		btnlogin.setForeground(Color.BLUE);
		btnlogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnlogin.setBounds(161, 176, 153, 27);
		JButton btnadd = new JButton("Add User");
		btnadd.addActionListener(new addUserListener());
		btnadd.setForeground(Color.BLUE);
		btnadd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnadd.setBounds(161, 176, 153, 27);
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new BorderLayout());
		btnPanel.add(btnadd, BorderLayout.SOUTH);
		btnPanel.add(btnlogin, BorderLayout.NORTH);
		this.add(btnPanel, BorderLayout.SOUTH);
	}

	private void login()throws ElementNotFoundException, UserLoggedInException, LoginFailedException{
		String Username=usernameField.getText();
		String Password=passwordField.getText();
		command.login(Username, Password);
		this.removeAll();
		lblprompt.setText(Username);
		this.add(lblprompt, BorderLayout.CENTER);
		JLabel successMsg = new JLabel("Log in successfully", SwingConstants.CENTER);
		successMsg.setFont(new Font("Sylfaen", Font.PLAIN, 14));
		successMsg.setBounds(119, 22, 223, 34);
		this.add(successMsg, BorderLayout.CENTER);

		JButton btnlogout = new JButton("Log out");
		btnlogout.addActionListener(new logoutButtonListener());
		btnlogout.setForeground(Color.BLUE);
		btnlogout.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnlogout.setBounds(161, 176, 153, 27);
		this.add(btnlogout, BorderLayout.SOUTH);

		this.repaint();
		this.validate();
		ui.refreshDisplay();
	}

//LISTENER CLASSES
	private class loginButtonListener implements ActionListener, KeyListener{
		public void actionPerformed(ActionEvent e) {

			try{
				login();
			}catch(ElementNotFoundException elem){
				lblprompt.setText(elem.getMsg());
			}
			catch(UserLoggedInException uex){
				lblprompt.setText( uex.getMsg());
			}
			catch(LoginFailedException l){lblprompt.setText(l.getMsg());}
		}
		public void keyPressed(KeyEvent e){
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				try{login();}
				catch(ElementNotFoundException elem){
					lblprompt.setText(elem.getMsg());
				}
				catch(UserLoggedInException uex){
					lblprompt.setText( uex.getMsg());
				}
				catch(LoginFailedException l){lblprompt.setText(l.getMsg());}
			}
		}
		public void keyReleased(KeyEvent e){}
		public void keyTyped(KeyEvent e){}
	}
	private class logoutButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
					command.logout();
					panel.removeAll();
					initialize();
					panel.repaint();
					ui.refreshDisplay();
		}
	}
	private class addUserListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String Username=usernameField.getText();
			String Password=passwordField.getText();
			usernameField.setText(""); passwordField.setText("");
			command.addUser(Username,Password);
			panel.remove(lblprompt);
			lblprompt.setText("User Added Successfully");
			panel.add(lblprompt, BorderLayout.NORTH);
			panel.validate();
			panel.repaint();
		}
	}

}
