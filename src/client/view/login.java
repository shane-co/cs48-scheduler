package client.view;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import client.app.exceptions.*;

public class login {

	private JFrame frame;
	private JTextField textFieldUN;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(71, 67, 88, 27);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(71, 105, 88, 27);
		frame.getContentPane().add(lblNewLabel_1);

		textFieldUN = new JTextField();
		textFieldUN.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldUN.setBounds(161, 72, 153, 22);
		frame.getContentPane().add(textFieldUN);
		textFieldUN.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordField.setBounds(161, 110, 152, 22);
		frame.getContentPane().add(passwordField);

		JButton btnlogin = new JButton("Log in");
		btnlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserInterface main = new UserInterface();
				String Username=textFieldUN.getText();
				String Password=passwordField.getText();
				try{
					main.command().login(Username, Password);
					JOptionPane.showMessageDialog(null, "Correct Username and Password!");
					frame.dispose();
					main.launch();
				}catch(ElementNotFoundException elem){
					JOptionPane.showMessageDialog(null, "Incorrect Username or Password. Please Try Again!");
				}
				/**
				if (main.command().login(Username, Password))//login (as well as logout) function requires implementation.
				{
					//Here I just realized that maybe blending entire login into Userinterface would make more sense.
					//Otherwise, I have to create a class of userinterface here which might cause conflicts against RunApp().
					JOptionPane.showMessageDialog(null, "Correct Username and Password!");
					frame.dispose();
					main.launch();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Incorrect Username or Password. Please Try Again!");
				}
				*/
			}
		});
		btnlogin.setForeground(Color.BLUE);
		btnlogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnlogin.setBounds(161, 176, 153, 27);
		frame.getContentPane().add(btnlogin);
	}

}
