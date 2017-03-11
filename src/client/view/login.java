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

import client.app.exceptions.*;
import client.commander.BGCommander;

public class login extends JPanel{

	private JLabel lblUsername;
	private JLabel lblPassword;
	private JTextField usernameField;
	private JPasswordField passwordField;
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
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordField.setBounds(161, 110, 152, 22);

		JLabel lblprompt = new JLabel("Enter username and password", SwingConstants.CENTER);
		lblprompt.setFont(new Font("Sylfaen", Font.PLAIN, 14));
		lblprompt.setBounds(71, 22, 250, 34);

		this.add(lblprompt,BorderLayout.NORTH);
		this.add(lblUsername,BorderLayout.CENTER);
		this.add(usernameField,BorderLayout.CENTER);
		this.add(lblPassword,BorderLayout.CENTER);
		this.add(passwordField,BorderLayout.CENTER);
		this.add(new JPanel(),BorderLayout.CENTER);

		JButton btnlogin = new JButton("Log in");
		btnlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Username=usernameField.getText();
				String Password=passwordField.getText();
				try{
					command.login(Username, Password);
					panel.removeAll();
					JLabel lblUser = new JLabel(Username, SwingConstants.CENTER);
					lblUser.setFont(new Font("Tahoma", Font.PLAIN, 16));
					lblUser.setBounds(71, 67, 153, 27);
					panel.add(lblUser, BorderLayout.NORTH);
					JLabel lblprompt1 = new JLabel("Log in successfully", SwingConstants.CENTER);
					lblprompt1.setFont(new Font("Sylfaen", Font.PLAIN, 14));
					lblprompt1.setBounds(119, 22, 223, 34);
					panel.add(lblprompt1, BorderLayout.CENTER);

					JButton btnlogout = new JButton("Log out");
					btnlogout.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							command.logout();
							panel.removeAll();
							initialize();
						}
					});
					btnlogout.setForeground(Color.BLUE);
					btnlogout.setFont(new Font("Tahoma", Font.PLAIN, 16));
					btnlogout.setBounds(161, 176, 153, 27);
					panel.add(btnlogout, BorderLayout.SOUTH);
					panel.repaint();
					panel.validate();
					ui.refreshMyEvents();
				}catch(ElementNotFoundException elem){
					lblprompt.setText("Unrecognized username or password O.o? ");
				}
				catch(UserLoggedInException uex){
					System.out.println( uex.getMsg());
				}
			}
		});
		btnlogin.setForeground(Color.BLUE);
		btnlogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnlogin.setBounds(161, 176, 153, 27);
		this.add(btnlogin, BorderLayout.SOUTH);
	}

}
