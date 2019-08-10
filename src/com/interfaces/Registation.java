package com.interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.border.CompoundBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.JButton;

public class Registation extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JPasswordField passwordField2;
	private JTextField txtLName;
	private JTextField txtContactNo;
	private JTextField txtEmail;
	private JTextField txtAddress;
	private JTextField txtNIC;
	private JTextField txtUserName;
	private JTextField txtFName;
	private JTextField txtBankNo;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registation frame = new Registation();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Registation() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Registation.class.getResource("/com/sun/java/swing/plaf/windows/icons/File.gif")));
		setTitle("Profile Registation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 809, 526);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel Title = new JPanel();
		Title.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Title.setBackground(new Color(30, 144, 255));
		Title.setForeground(SystemColor.text);
		Title.setBounds(0, 0, 793, 83);
		contentPane.add(Title);
		Title.setLayout(null);
		
		JLabel lblRegistation = new JLabel("Account Registation");
		lblRegistation.setFont(new Font("Showcard Gothic", Font.PLAIN, 30));
		lblRegistation.setForeground(SystemColor.text);
		lblRegistation.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistation.setBounds(10, 11, 773, 65);
		Title.add(lblRegistation);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBackground(new Color(255, 218, 185));
		panel.setBounds(21, 108, 748, 368);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblFirstName = new JLabel("First Name*");
		lblFirstName.setBounds(53, 94, 113, 14);
		panel.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(53, 119, 113, 14);
		panel.add(lblLastName);
		
		JLabel lblUsername = new JLabel("UserName*");
		lblUsername.setBounds(53, 19, 113, 14);
		panel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password*");
		lblPassword.setBounds(53, 44, 113, 14);
		panel.add(lblPassword);
		
		JLabel lblReenterPassword = new JLabel("Confirm Password*");
		lblReenterPassword.setBounds(53, 69, 113, 14);
		panel.add(lblReenterPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBorder(new LineBorder(new Color(0, 0, 51), 1, true));
		passwordField.setBackground(new Color(224, 255, 255));
		passwordField.setToolTipText("Password");
		passwordField.setFont(new Font("Dialog", Font.PLAIN, 13));
		passwordField.setBounds(176, 43, 188, 17);
		panel.add(passwordField);
		
		passwordField2 = new JPasswordField();
		passwordField2.setBorder(new LineBorder(new Color(0, 0, 51), 1, true));
		passwordField2.setBackground(new Color(224, 255, 255));
		passwordField2.setToolTipText("Confirm Password");
		passwordField2.setFont(new Font("Dialog", Font.PLAIN, 13));
		passwordField2.setBounds(176, 68, 188, 17);
		panel.add(passwordField2);
		
		JLabel lblContact = new JLabel("Contact Number");
		lblContact.setBounds(53, 219, 113, 14);
		panel.add(lblContact);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(53, 249, 71, 14);
		panel.add(lblEmail);
		
		JLabel lblAddress = new JLabel("Address*");
		lblAddress.setBounds(53, 144, 113, 14);
		panel.add(lblAddress);
		
		JLabel lblNicNumber = new JLabel("NIC Number*");
		lblNicNumber.setBounds(53, 194, 113, 14);
		panel.add(lblNicNumber);
		
		JLabel lblRole = new JLabel("Role*");
		lblRole.setBounds(53, 274, 113, 14);
		panel.add(lblRole);
		
		txtLName = new JTextField();
		txtLName.setBorder(new LineBorder(new Color(0, 0, 51), 1, true));
		txtLName.setBackground(new Color(224, 255, 255));
		txtLName.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtLName.setBounds(176, 116, 188, 20);
		panel.add(txtLName);
		txtLName.setColumns(10);
		
		txtContactNo = new JTextField();
		txtContactNo.setBorder(new LineBorder(new Color(0, 0, 51), 1, true));
		txtContactNo.setBackground(new Color(224, 255, 255));
		txtContactNo.setSelectionColor(SystemColor.textHighlight);
		txtContactNo.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtContactNo.setToolTipText("Contact Number");
		txtContactNo.setText("07");
		txtContactNo.setBounds(176, 216, 188, 20);
		panel.add(txtContactNo);
		txtContactNo.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBorder(new LineBorder(new Color(0, 0, 51), 1, true));
		txtEmail.setBackground(new Color(224, 255, 255));
		txtEmail.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtEmail.setToolTipText("example@unic.com");
		txtEmail.setBounds(176, 246, 188, 20);
		panel.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtAddress = new JTextField();
		txtAddress.setBorder(new LineBorder(new Color(0, 0, 51), 1, true));
		txtAddress.setBackground(new Color(224, 255, 255));
		txtAddress.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtAddress.setBounds(176, 141, 188, 20);
		panel.add(txtAddress);
		txtAddress.setColumns(10);
		
		txtNIC = new JTextField();
		txtNIC.setBorder(new LineBorder(new Color(0, 0, 51), 1, true));
		txtNIC.setBackground(new Color(224, 255, 255));
		txtNIC.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtNIC.setToolTipText("970150390V");
		txtNIC.setBounds(176, 191, 188, 20);
		panel.add(txtNIC);
		txtNIC.setColumns(10);
		
		JComboBox comboRole = new JComboBox();
		comboRole.setBorder(new LineBorder(new Color(0, 0, 51), 1, true));
		comboRole.setBackground(new Color(224, 255, 255));
		comboRole.setForeground(SystemColor.textText);
		comboRole.setFont(new Font("Dialog", Font.PLAIN, 13));
		comboRole.setModel(new DefaultComboBoxModel(new String[] {"Mechine Supervisour", "Molded Supervisour", "Shifting Supervisour", "Store Supervisour", "Sales Manager", "Factory Manager"}));
		comboRole.setBounds(176, 271, 188, 20);
		panel.add(comboRole);
		
		txtUserName = new JTextField();
		txtUserName.setBorder(new LineBorder(new Color(0, 0, 51), 1, true));
		txtUserName.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtUserName.setBackground(new Color(224, 255, 255));
		txtUserName.setBounds(176, 16, 188, 20);
		panel.add(txtUserName);
		txtUserName.setColumns(10);
		
		txtFName = new JTextField();
		txtFName.setBorder(new LineBorder(new Color(0, 0, 51), 1, true));
		txtFName.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtFName.setBackground(new Color(224, 255, 255));
		txtFName.setBounds(176, 91, 188, 20);
		panel.add(txtFName);
		txtFName.setColumns(10);
		
		JLabel lblBankAccNo = new JLabel("Bank Account Number");
		lblBankAccNo.setBounds(53, 169, 113, 14);
		panel.add(lblBankAccNo);
		
		txtBankNo = new JTextField();
		txtBankNo.setBorder(new LineBorder(new Color(0, 0, 51), 1, true));
		txtBankNo.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtBankNo.setBackground(new Color(224, 255, 255));
		txtBankNo.setBounds(176, 166, 188, 20);
		panel.add(txtBankNo);
		txtBankNo.setColumns(10);
		
		JLabel lblBasicSalary = new JLabel("Basic Salary");
		lblBasicSalary.setBounds(423, 196, 79, 14);
		panel.add(lblBasicSalary);
		
		textField = new JTextField();
		textField.setBounds(504, 193, 188, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblOtRate = new JLabel("OT Rate");
		lblOtRate.setBounds(423, 221, 46, 14);
		panel.add(lblOtRate);
		
		textField_1 = new JTextField();
		textField_1.setBounds(504, 218, 188, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(504, 243, 188, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblReferanceRole = new JLabel("Referance Role");
		lblReferanceRole.setBounds(423, 246, 130, 14);
		panel.add(lblReferanceRole);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_2.setBounds(406, 133, 310, 145);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblFilleWithAuthorized = new JLabel("Fille with Authorized Person");
		lblFilleWithAuthorized.setBounds(23, 24, 205, 14);
		panel_2.add(lblFilleWithAuthorized);
		lblFilleWithAuthorized.setForeground(new Color(0, 0, 102));
		lblFilleWithAuthorized.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setForeground(new Color(255, 255, 255));
		btnBrowse.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBrowse.setBackground(new Color(30, 144, 255));
		btnBrowse.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnBrowse.setBounds(539, 65, 89, 23);
		panel.add(btnBrowse);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_3.setBounds(437, 19, 92, 103);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblImage = new JLabel("");
		lblImage.setBounds(0, 0, 92, 103);
		panel_3.add(lblImage);
		lblImage.setForeground(SystemColor.textHighlightText);
		lblImage.setBackground(SystemColor.window);
		lblImage.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage.setIcon(new ImageIcon("C:\\Users\\VS\\Desktop\\MAD\\Mini Project\\icons\\icons8-user-male-50-2.png"));
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnRegister.setForeground(new Color(255, 255, 255));
		btnRegister.setBackground(new Color(210, 105, 30));
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRegister.setBounds(326, 302, 113, 38);
		panel.add(btnRegister);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnReset.setForeground(new Color(255, 255, 255));
		btnReset.setBackground(new Color(30, 144, 255));
		btnReset.setBounds(140, 310, 89, 23);
		panel.add(btnReset);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnExit.setForeground(new Color(255, 255, 255));
		btnExit.setBackground(new Color(178, 34, 34));
		btnExit.setBounds(539, 310, 89, 23);
		panel.add(btnExit);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 128));
		panel_1.setBounds(0, 81, 793, 10);
		contentPane.add(panel_1);
	}
}
