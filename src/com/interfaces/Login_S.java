package com.interfaces;

import java.awt.EventQueue;

import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.util.DbConnect;
import javax.swing.UIManager;
import javax.swing.JPanel;
import javax.swing.JDesktopPane;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login_S {

	private JFrame frmLoginSystem;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	
	private static Connection connection = null; 
	private JLabel lblLblusernoti;

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
	 * @throws SQLException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public Login_S() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		initialize();
		connection = DbConnect.getDBConnection();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLoginSystem = new JFrame();
		frmLoginSystem.setResizable(false);
		frmLoginSystem.setIconImage(Toolkit.getDefaultToolkit().getImage(Login_S.class.getResource("/user-login-icon.png")));
		frmLoginSystem.setTitle("User Login");
		frmLoginSystem.getContentPane().setBackground(SystemColor.textHighlight);
		frmLoginSystem.setBounds(200, 200, 690, 425);
		frmLoginSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLoginSystem.getContentPane().setLayout(null);
		frmLoginSystem.setLocationRelativeTo(null);
		
		JLabel lblUserLogin = new JLabel("   User Login");
		lblUserLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserLogin.setForeground(SystemColor.text);
		lblUserLogin.setFont(new Font("Showcard Gothic", Font.PLAIN, 30));
		lblUserLogin.setBounds(22, 37, 648, 67);
		Image img5 = new ImageIcon(this.getClass().getResource("/Apps-preferences-system-login-icon.png")).getImage();
		lblUserLogin.setIcon(new ImageIcon(img5));
		frmLoginSystem.getContentPane().add(lblUserLogin);
		Image img = new ImageIcon(this.getClass().getResource("/user-icon1.png")).getImage();
		Image img1 = new ImageIcon(this.getClass().getResource("/Register-icon1.png")).getImage();
		
		JButton btnLogin = new JButton(" Login");
		Image img3 = new ImageIcon(this.getClass().getResource("/Alarm-Tick-icon.png")).getImage();
		btnLogin.setIcon(new ImageIcon(img3));
		btnLogin.setForeground(new Color(224, 255, 255));
		btnLogin.setBackground(new Color(210, 105, 30));
		btnLogin.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				try{
					
					
					
					String query = "SELECT * FROM user_main WHERE username = ? AND password = ? "; 
					PreparedStatement pst = connection.prepareStatement(query);
					
					pst.setString(1, txtUsername.getText());
					pst.setString(2, txtPassword.getText());
					
					ResultSet rs = pst.executeQuery();
				
					
					if (txtPassword.getText().isEmpty() == true | txtUsername.getText().isEmpty() == true) {
						JOptionPane.showMessageDialog(null, "Please Fill Login Details", "Login Error", JOptionPane.ERROR_MESSAGE);
						txtUsername.setText(null);
						txtPassword.setText(null);
					}
					else if (rs.next() ) { //&& !Arrays.equals(pass, txtPassword.getPassword())
						txtUsername.setText(null);
						txtPassword.setText(null);
						JOptionPane.showMessageDialog(null, "Login Sucessfully");
					
						frmLoginSystem.dispose();
						mainUI mainui = new mainUI(rs.getString("EID"));
						mainui.setVisible(true);
						
						//connection.close();
						
					}
					else {
						JOptionPane.showMessageDialog(null, "Invalid Login Details", "Login Error", JOptionPane.ERROR_MESSAGE);
						txtUsername.setText(null);
						txtPassword.setText(null);
					}
					
					rs.close();
					pst.close();
					
					
				}catch(Exception e1) {
					System.out.println(e1);
				}
				
			}
		});
		btnLogin.setBounds(285, 298, 144, 54);
		frmLoginSystem.getContentPane().add(btnLogin);
		
		JButton btnReset = new JButton("Reset");
		Image img4 = new ImageIcon(this.getClass().getResource("/Clear-icon.png")).getImage();
		btnReset.setIcon(new ImageIcon(img4));
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
		btnReset.setBounds(114, 310, 120, 35);
		frmLoginSystem.getContentPane().add(btnReset);
		
		JButton btnExit = new JButton(" Exit");
		Image img2 = new ImageIcon(this.getClass().getResource("/Alarm-Error-icon.png")).getImage();
		btnExit.setIcon(new ImageIcon(img2));
		btnExit.setForeground(new Color(224, 255, 255));
		btnExit.setBackground(new Color(139, 0, 0));
		btnExit.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frmLoginSystem = new JFrame("Exit");
				if (JOptionPane.showConfirmDialog(frmLoginSystem, "Confirm if you really want to exit", "Login Exit Confirmation" ,
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION ){
						System.exit(JFrame.EXIT_ON_CLOSE);
				}
				
				
			}
		});
		btnExit.setBounds(474, 310, 120, 35);
		frmLoginSystem.getContentPane().add(btnExit);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(22, 105, 648, 2);
		frmLoginSystem.getContentPane().add(separator_1);
		
		JButton btnCreateAcc = new JButton("Create an Account");
		btnCreateAcc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Registation reg;
				try {
					reg = new Registation();
					reg.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		btnCreateAcc.setForeground(new Color(25, 25, 112));
		btnCreateAcc.setBackground(new Color(240, 230, 140));
		btnCreateAcc.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnCreateAcc.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCreateAcc.setBounds(285, 363, 144, 29);
		frmLoginSystem.getContentPane().add(btnCreateAcc);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(10, 11, 670, 403);
		frmLoginSystem.getContentPane().add(panel);
		panel.setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String userName = txtUsername.getText();
				
				if ( !userName.matches("[a-zA-Z0-9 ,]+") ) {
					lblLblusernoti.setText("Please Enter Valide User Name");
					txtUsername.setText(null);
					//txtLName.requestFocusInWindow();
				}
				else {
					lblLblusernoti.setText(null);
				}
			}
		});
		txtUsername.setBounds(242, 130, 279, 35);
		panel.add(txtUsername);
		txtUsername.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtUsername.setColumns(10);
		
		JLabel lblUsername = new JLabel("");
		lblUsername.setBounds(118, 115, 104, 67);
		panel.add(lblUsername);
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setIcon(new ImageIcon(img));
		
		lblUsername.setForeground(SystemColor.menu);
		lblUsername.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		
		JLabel lblPassword = new JLabel("");
		lblPassword.setBounds(118, 193, 104, 54);
		panel.add(lblPassword);
		lblPassword.setForeground(SystemColor.menu);
		lblPassword.setFont(new Font("Tw Cen MT", Font.PLAIN, 25));
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setIcon(new ImageIcon(img1));
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(240, 201, 281, 32);
		panel.add(txtPassword);
		txtPassword.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		//frmLoginSystem.setUndecorated(true);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 265, 648, 2);
		panel.add(separator);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(Login_S.class.getResource("/brick-dealers-vqxqr.png")));
		lblNewLabel.setForeground(SystemColor.text);
		lblNewLabel.setBounds(0, 0, 670, 403);
		panel.add(lblNewLabel);
		
		lblLblusernoti = new JLabel("");
		lblLblusernoti.setForeground(SystemColor.text);
		lblLblusernoti.setBounds(242, 166, 279, 16);
		panel.add(lblLblusernoti);
		
		frmLoginSystem.setUndecorated(true);
	}
}
