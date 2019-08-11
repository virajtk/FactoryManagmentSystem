package com.interfaces;

import java.awt.EventQueue;

import java.sql.*;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.util.DbConnect;

public class Login_S {

	private JFrame frmLoginSystem;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	
	private static Connection connection ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login_S window = new Login_S();
					window.frmLoginSystem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login_S() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLoginSystem = new JFrame();
		frmLoginSystem.setIconImage(Toolkit.getDefaultToolkit().getImage(Login_S.class.getResource("/com/sun/java/swing/plaf/motif/icons/DesktopIcon.gif")));
		frmLoginSystem.setTitle("User Login");
		frmLoginSystem.getContentPane().setBackground(SystemColor.textHighlight);
		frmLoginSystem.setBounds(200, 200, 690, 425);
		frmLoginSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLoginSystem.getContentPane().setLayout(null);
		
		JLabel lblUserLogin = new JLabel("User Login");
		lblUserLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserLogin.setForeground(SystemColor.text);
		lblUserLogin.setFont(new Font("Showcard Gothic", Font.PLAIN, 30));
		lblUserLogin.setBounds(12, 16, 648, 67);
		frmLoginSystem.getContentPane().add(lblUserLogin);
		
		JLabel lblUsername = new JLabel("User Name");
		lblUsername.setForeground(SystemColor.menu);
		lblUsername.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		lblUsername.setBounds(137, 125, 130, 25);
		frmLoginSystem.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(SystemColor.menu);
		lblPassword.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		lblPassword.setBounds(137, 185, 120, 23);
		frmLoginSystem.getContentPane().add(lblPassword);
		
		txtUsername = new JTextField();
		txtUsername.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		txtUsername.setFont(new Font("BoomerangItalic", Font.BOLD, 18));
		txtUsername.setBounds(291, 122, 265, 35);
		frmLoginSystem.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		txtPassword.setFont(new Font("BoomerangItalic", Font.BOLD, 18));
		txtPassword.setBounds(291, 182, 265, 32);
		frmLoginSystem.getContentPane().add(txtPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setForeground(new Color(224, 255, 255));
		btnLogin.setBackground(new Color(210, 105, 30));
		btnLogin.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				try{
					//Class.forName("com.mysql.jdbc.Driver");
					//Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/unic", "root", "root");
					connection = DbConnect.getDBConnection();
					
					Statement stmt = connection.createStatement();
					
					String sql = "SELECT * FROM user WHERE username = '" + txtUsername.getText() + "' AND password = '" + txtPassword.getText().toString() + "'";
					ResultSet rs = stmt.executeQuery(sql);
					if (rs.next()) {
						txtUsername.setText(null);
						txtPassword.setText(null);
						JOptionPane.showMessageDialog(null, "Login Sucessfully");
						
						mainUI mn = new mainUI();
						mn.main(null);
						
						//User user = new User();
						//User.main(null);
						//user.main(null);
					}
					else {
						JOptionPane.showMessageDialog(null, "Invalid Login Details", "Login Error", JOptionPane.ERROR_MESSAGE);
						txtUsername.setText(null);
						txtPassword.setText(null);
					}
					connection.close();
				}catch(Exception e1) {
					System.out.println(e1);
				}
				//String password = txtPassword.getText();
				//String username = txtUsername.getText();
				
				//if (password.contains("abc") && username.contains("viraj")) {
					//txtUsername.setText(null);
					//txtPassword.setText(null);
					
					//User user = new User();
					//User.main(null);
					//user.main(null);
				//}
				//else {
					//JOptionPane.showMessageDialog(null, "Invalid Login Details", "Login Error", JOptionPane.ERROR_MESSAGE);
					//txtUsername.setText(null);
					//txtPassword.setText(null);
				//}
			}
		});
		btnLogin.setBounds(275, 285, 144, 52);
		frmLoginSystem.getContentPane().add(btnLogin);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setForeground(new Color(224, 255, 255));
		btnReset.setBackground(new Color(30, 144, 255));
		btnReset.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				txtUsername.setText(null);
				txtPassword.setText(null);
				
			}
		});
		btnReset.setBounds(103, 296, 120, 35);
		frmLoginSystem.getContentPane().add(btnReset);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setForeground(new Color(224, 255, 255));
		btnExit.setBackground(new Color(139, 0, 0));
		btnExit.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frmLoginSystem = new JFrame("Exit");
				if (JOptionPane.showConfirmDialog(frmLoginSystem, "Confirm if you really want to exit", "Login Exit Confirmation" ,
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION ){
						System.exit(0);
				}
				
				
			}
		});
		btnExit.setBounds(464, 296, 120, 35);
		frmLoginSystem.getContentPane().add(btnExit);
		//frmLoginSystem.setUndecorated(true);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 264, 648, 2);
		frmLoginSystem.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(12, 84, 648, 2);
		frmLoginSystem.getContentPane().add(separator_1);
		
		//frmLoginSystem.setUndecorated(true);
	}
}
