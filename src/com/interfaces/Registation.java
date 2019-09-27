package com.interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Date;

import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Event;

import javax.swing.border.CompoundBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.util.DbConnect;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;

import com.model.User;
import com.service.IUserService;
import com.service.UserService;
import com.toedter.calendar.JDateChooser;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

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
	private JTextField txtbasicSalary;
	private JTextField txtOTRate;
	private JTextField txtRefRole;
	private JTextField txtEID;
	private JDateChooser dateChooserBDay;
	private User newUser;
	JLabel lblImage;
	String ImagePath;

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
	
	//
	Connection connection = null;
	private JTextField txtFilePath;
	private JDateChooser dateChooserRegDate;
	private JLabel lblNotify;
	private JButton btnDemo;
	
	public void resetFields() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		passwordField.setText(null);
		passwordField2.setText(null);
		txtLName.setText(null);
		txtContactNo.setText(null);
		txtEmail.setText(null);
		txtAddress.setText(null);
		txtNIC.setText(null);
		txtUserName.setText(null);
		txtFName.setText(null);
		txtBankNo.setText(null);
		txtbasicSalary.setText(null);
		txtOTRate.setText(null);
		txtRefRole.setText(null);
		txtEID.setText(null);
		Image img = new ImageIcon(this.getClass().getResource("/browser-girl-firefox-icon.png")).getImage();
		lblImage.setIcon(new ImageIcon(img));
		
		ImagePath = null;
		txtFilePath.setText(null);
		
		txtEID.setText(generateEID());
		dateChooserBDay.setDate(null);
		
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = new Date(System.currentTimeMillis());
		//System.out.println(formatter.format(date1));
		String date = formatter.format(date1);
		java.util.Date date2 = null;
		try {
			date2 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		dateChooserRegDate.setDate(date2);
		
		
		
	}
	
	public int calculateAge(String date) {
		
		String dob[] = date.split("-");
		int year = Integer.parseInt(dob[0]);
		int month = Integer.parseInt(dob[1]);
		int day = Integer.parseInt(dob[2]);
		LocalDate selectDate = LocalDate.of(year,month,day);
		LocalDate currentDate = LocalDate.now();
		
		int age = Period.between(selectDate, currentDate).getYears();
		
		return age;
	}
	
	//do not know is this the best way or not, but i this by my own thinking 
	public String generateEID() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		
		connection = DbConnect.getDBConnection();
		String lastID = null;
		int lastId;
		String query = "SELECT EID FROM user_main";
		
		PreparedStatement pst = connection.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
		
		while ( rs.next() ) {
			
			String str = rs.getString("EID");
			String eid[] = str.split("E");
			lastID = eid[1];
		}
		if (lastID == null) {
			lastId = 0;
		}
		else {
			lastId = Integer.parseInt(lastID);
			
			
		}
		lastId++;
		String result = Integer.toString(lastId);
		
		if (result.length()==1) {
			result = "E000"+lastId;
		}
		else if (result.length()==2) {
			result = "E00"+lastId;
		}
		else if (result.length()==3) {
			result = "E0"+lastId;
		}
		else {
			result = "E"+lastId;
		}
		
		return result;
	}
	
	public boolean validateRegistration() {
			
			if( newUser.getUsername().isEmpty() == true | passwordField.getText().isEmpty() == true 
					| newUser.getfName().isEmpty() == true | passwordField2.getText().isEmpty() == true ) {
				JOptionPane.showMessageDialog(	null ,"Please Fill mandatory Fields!","Problem!",JOptionPane.ERROR_MESSAGE);
				passwordField.setText(null);
				passwordField2.setText(null);
				return false;
			}
			else if (passwordField.getText().toString().length() < 3 ) {
				JOptionPane.showMessageDialog(	null ,"Password should contain minimum 3 characters","Problem!",JOptionPane.ERROR_MESSAGE);
				passwordField.setText(null);
				passwordField2.setText(null);
				return false;
			}
			else if (!Arrays.equals(passwordField.getPassword(), passwordField2.getPassword())) {
				JOptionPane.showMessageDialog(null, "Passwords do not match.", "Woops", JOptionPane.ERROR_MESSAGE);
				passwordField.setText(null);
				passwordField2.setText(null);   
				return false;
				}
			else {
				return true;
			}
		
	}
	
	public ImageIcon ResizeImage (String ImagePath) {
		
		ImageIcon imageIcon = new ImageIcon(ImagePath);
		Image img = imageIcon.getImage();
		Image modifiedDabImage = img.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon image = new ImageIcon(modifiedDabImage);
		
		return image;
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public Registation() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		
		//connection = DbConnect.getDBConnection();
		IUserService iUserService = new UserService();
		newUser = new User();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Registation.class.getResource("/new-icon.png")));
		setTitle("Profile Registation");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 873, 669);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); 
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JPanel Title = new JPanel();
		Title.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Title.setBackground(new Color(30, 144, 255));
		Title.setForeground(SystemColor.text);
		Title.setBounds(0, 0, 855, 83);
		contentPane.add(Title);
		Title.setLayout(null);
		
		JLabel lblRegistation = new JLabel("  Account Registation");
		Image img5 = new ImageIcon(this.getClass().getResource("/user-new-icon.png")).getImage();
		lblRegistation.setIcon(new ImageIcon(img5));
		lblRegistation.setFont(new Font("Showcard Gothic", Font.PLAIN, 30));
		lblRegistation.setForeground(SystemColor.text);
		lblRegistation.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistation.setBounds(10, 11, 833, 65);
		Title.add(lblRegistation);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBackground(new Color(245, 245, 220));
		panel.setBounds(10, 102, 833, 520);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblNotify = new JLabel("...");
		lblNotify.setHorizontalAlignment(SwingConstants.LEFT);
		lblNotify.setForeground(new Color(153, 0, 0));
		lblNotify.setBounds(77, 11, 311, 14);
		panel.add(lblNotify);
		
		JLabel lblFirstName = new JLabel("First Name*");
		lblFirstName.setBounds(77, 111, 113, 14);
		panel.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(77, 136, 113, 14);
		panel.add(lblLastName);
		
		JLabel lblUsername = new JLabel("UserName*");
		lblUsername.setBounds(77, 36, 113, 14);
		panel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password*");
		lblPassword.setBounds(77, 61, 113, 14);
		panel.add(lblPassword);
		
		btnDemo = new JButton("DEMO");
		btnDemo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				txtUserName.setText("nuwan");
				passwordField.setText("12345");
				passwordField2.setText("12345");
				txtFName.setText("Nuwan");
				txtLName.setText("Kodagoda");
				txtAddress.setText("Malabe");
				txtBankNo.setText("1008789658245");
				txtNIC.setText("801205390V");
				txtContactNo.setText("0711070226");
				txtEmail.setText("nuwan@unic.lk");
				txtbasicSalary.setText("100000");
				txtOTRate.setText("100");
				txtRefRole.setText("E0002");	
				
			}
		});
		btnDemo.setForeground(new Color(178, 34, 34));
		btnDemo.setFont(new Font("! PEPSI !", Font.BOLD, 12));
		btnDemo.setBounds(721, 11, 100, 33);
		panel.add(btnDemo);
		
		JLabel lblReenterPassword = new JLabel("Confirm Password*");
		lblReenterPassword.setBounds(77, 86, 113, 14);
		panel.add(lblReenterPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBorder(new LineBorder(new Color(0, 0, 51), 1, true));
		passwordField.setBackground(new Color(224, 255, 255));
		passwordField.setToolTipText("Password");
		passwordField.setFont(new Font("Dialog", Font.PLAIN, 13));
		passwordField.setBounds(200, 60, 188, 17);
		panel.add(passwordField);
		
		passwordField2 = new JPasswordField();
		passwordField2.setBorder(new LineBorder(new Color(0, 0, 51), 1, true));
		passwordField2.setBackground(new Color(224, 255, 255));
		passwordField2.setToolTipText("Confirm Password");
		passwordField2.setFont(new Font("Dialog", Font.PLAIN, 13));
		passwordField2.setBounds(200, 85, 188, 17);
		panel.add(passwordField2);
		
		JLabel lblContact = new JLabel("Contact Number");
		lblContact.setBounds(77, 277, 113, 14);
		panel.add(lblContact);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(77, 307, 71, 14);
		panel.add(lblEmail);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(77, 202, 113, 14);
		panel.add(lblAddress);
		
		JLabel lblNicNumber = new JLabel("NIC Number");
		lblNicNumber.setBounds(77, 252, 113, 14);
		panel.add(lblNicNumber);
		
		JLabel lblRole = new JLabel("Position*");
		lblRole.setBounds(77, 332, 113, 14);
		panel.add(lblRole);
		
		txtLName = new JTextField();
		txtLName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String LastName = txtLName.getText();
				
				if ( !LastName.matches("[a-zA-Z ,]+") & LastName.length() != 0 ) {
					lblNotify.setText("Please Enter Valide Name");
					txtLName.setText(null);
					//txtLName.requestFocusInWindow();
				}
				else {
					lblNotify.setText(null);
				}
			}
		});
		txtLName.setBorder(new LineBorder(new Color(0, 0, 51), 1, true));
		txtLName.setBackground(new Color(224, 255, 255));
		txtLName.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtLName.setBounds(202, 136, 188, 20);
		panel.add(txtLName);
		txtLName.setColumns(10);
		
		txtContactNo = new JTextField();
		txtContactNo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				String ContactNo = txtContactNo.getText();
				
				if ( !ContactNo.matches("[0-9 ,]+") & ContactNo.length() != 0) {
					lblNotify.setText("Please Enter Valide Contact Number");
					txtContactNo.setText(null);
					//txtContactNo.requestFocusInWindow();
				}
				else if (ContactNo.length() != 10) {
					if (ContactNo.length() == 0) {
						lblNotify.setText(null);
					}else {
						lblNotify.setText("Please Enter Valide Contact Number");
						txtContactNo.setText(null);
						//txtContactNo.requestFocusInWindow();
					}
					
				}
				else {
					lblNotify.setText(null);
				}
				
			}
		});
		txtContactNo.setBorder(new LineBorder(new Color(0, 0, 51), 1, true));
		txtContactNo.setBackground(new Color(224, 255, 255));
		txtContactNo.setSelectionColor(SystemColor.textHighlight);
		txtContactNo.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtContactNo.setToolTipText("Contact Number");
		txtContactNo.setBounds(200, 273, 188, 20);
		panel.add(txtContactNo);
		txtContactNo.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				String email = txtEmail.getText();
				
				if ( !email.matches("[a-zA-Z0-9._@ ,]+") & email.length() != 0 ) {
					lblNotify.setText("Please Enter Valide E-mail");
					txtEmail.setText(null);
					//txtEmail.requestFocusInWindow();
				}
				else {
					lblNotify.setText(null);
				}
				
			}
		});
		txtEmail.setBorder(new LineBorder(new Color(0, 0, 51), 1, true));
		txtEmail.setBackground(new Color(224, 255, 255));
		txtEmail.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtEmail.setToolTipText("example@unic.com");
		txtEmail.setBounds(200, 300, 188, 20);
		panel.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtAddress = new JTextField();
		txtAddress.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String Address = txtAddress.getText();
				
				if ( !Address.matches("[a-zA-Z1-9/ ,]+") & Address.length() != 0 ) {
					lblNotify.setText("Please Enter Valide Address");
					txtAddress.setText(null);
					//txtAddress.requestFocusInWindow();
				}
				else {
					lblNotify.setText(null);
				}
				
			}
		});
		txtAddress.setBorder(new LineBorder(new Color(0, 0, 51), 1, true));
		txtAddress.setBackground(new Color(224, 255, 255));
		txtAddress.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtAddress.setBounds(200, 199, 188, 20);
		panel.add(txtAddress);
		txtAddress.setColumns(10);
		
		txtNIC = new JTextField();
		txtNIC.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				String NIC = txtNIC.getText();
				
				if ( !NIC.matches("[0-9vV ,]+") & NIC.length() != 0 ) {
					lblNotify.setText("Please Enter Valide NIC Number");
					txtNIC.setText(null);
					//txtNIC.requestFocusInWindow();
				}
				else {
					lblNotify.setText(null);
				}
				
			}
		});
		txtNIC.setBorder(new LineBorder(new Color(0, 0, 51), 1, true));
		txtNIC.setBackground(new Color(224, 255, 255));
		txtNIC.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtNIC.setToolTipText("970150390V");
		txtNIC.setBounds(200, 249, 188, 20);
		panel.add(txtNIC);
		txtNIC.setColumns(10);
		
		JComboBox<String> comboRole = new JComboBox<String>();
		comboRole.setBorder(new LineBorder(new Color(0, 0, 51), 1, true));
		comboRole.setBackground(new Color(224, 255, 255));
		comboRole.setForeground(SystemColor.textText);
		comboRole.setFont(new Font("Dialog", Font.PLAIN, 13));
		comboRole.setModel(new DefaultComboBoxModel<String>(new String[] {"Mechine Supervisour", "Molded Supervisour", "Shifting Supervisour", "Store Supervisour", "Branch Manager", "Sales Manager", "Factory Manager"}));
		comboRole.setBounds(200, 328, 188, 20);
		panel.add(comboRole);
		
		txtUserName = new JTextField();
		txtUserName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				String userName = txtUserName.getText();
				
				if ( !userName.matches("[a-zA-Z1-9 ,]+") ) {
					//JOptionPane.showMessageDialog(null, "Please Enter Valide UserName");
					lblNotify.setText("Please Enter Valide UserName");
					txtUserName.setText(null);
					//txtUserName.requestFocusInWindow();
					
				}
				else {
					lblNotify.setText(null);
				}
			}
		});
		txtUserName.setBorder(new LineBorder(new Color(0, 0, 51), 1, true));
		txtUserName.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtUserName.setBackground(new Color(224, 255, 255));
		txtUserName.setBounds(200, 33, 188, 20);
		panel.add(txtUserName);
		txtUserName.setColumns(10);
		
		txtFName = new JTextField();
		txtFName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				String FirstName = txtFName.getText();
				
				if ( !FirstName.matches("[a-zA-Z ,]+") ) {
					lblNotify.setText("Please Enter Valide Name");
					txtFName.setText(null);
					//txtFName.requestFocusInWindow();
					
				}
				else {
					lblNotify.setText(null);
				}
				
			}
		});
		txtFName.setBorder(new LineBorder(new Color(0, 0, 51), 1, true));
		txtFName.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtFName.setBackground(new Color(224, 255, 255));
		txtFName.setBounds(200, 108, 188, 20);
		panel.add(txtFName);
		txtFName.setColumns(10);
		
		JLabel lblBankAccNo = new JLabel("Bank Account No");
		lblBankAccNo.setBounds(77, 227, 113, 14);
		panel.add(lblBankAccNo);
		
		txtBankNo = new JTextField();
		txtBankNo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String BankNo = txtBankNo.getText();
				
				if ( !BankNo.matches("[0-9 ,]+") & BankNo.length() != 0 ) {
					lblNotify.setText("Please Enter Valide Bank Account Number");
					txtBankNo.setText(null);
					//txtBankNo.requestFocusInWindow();
				}
				else {
					lblNotify.setText(null);
				}
			}
		});
		txtBankNo.setBorder(new LineBorder(new Color(0, 0, 51), 1, true));
		txtBankNo.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtBankNo.setBackground(new Color(224, 255, 255));
		txtBankNo.setBounds(200, 224, 188, 20);
		panel.add(txtBankNo);
		txtBankNo.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_2.setBounds(451, 169, 310, 206);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblFilleWithAuthorized = new JLabel("Fille with Authorized Person");
		lblFilleWithAuthorized.setBounds(23, 24, 205, 14);
		panel_2.add(lblFilleWithAuthorized);
		lblFilleWithAuthorized.setForeground(new Color(0, 0, 102));
		lblFilleWithAuthorized.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtbasicSalary = new JTextField();
		txtbasicSalary.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String BasicSalary = txtbasicSalary.getText();
				
				if ( !BasicSalary.matches("[0-9. ,]+") & BasicSalary.length() != 0 ) {
					lblNotify.setText("Please Enter Valide Amount for Basic Salary");
					txtbasicSalary.setText(null);
					//txtbasicSalary.requestFocusInWindow();
				}
				else {
					lblNotify.setText(null);
				}
			}
		});
		txtbasicSalary.setBounds(114, 59, 178, 20);
		panel_2.add(txtbasicSalary);
		txtbasicSalary.setColumns(10);
		
		txtOTRate = new JTextField();
		txtOTRate.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				String OTRate = txtOTRate.getText();
				
				if ( !OTRate.matches("[0-9. ,]+") & OTRate.length() != 0 ) {
					lblNotify.setText("Please Enter Valide Amount for OT Rate");
					txtOTRate.setText(null);
					//txtOTRate.requestFocusInWindow();
				}
				else {
					lblNotify.setText(null);
				}
			}
		});
		txtOTRate.setBounds(114, 84, 178, 20);
		panel_2.add(txtOTRate);
		txtOTRate.setColumns(10);
		
		txtRefRole = new JTextField();
		txtRefRole.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				String refRole = txtRefRole.getText();
				
				if ( !refRole.matches("[E0-9 ,]+") & refRole.length() != 0 ) {
					lblNotify.setText("Please Enter Valide Refferance Role EID");
					txtRefRole.setText(null);
					//txtRefRole.requestFocusInWindow();
				}
				else if (refRole.length() != 5) {
					if (refRole.length() == 0) {
						lblNotify.setText(null);
					}
					else {
						lblNotify.setText("Please Enter Valide Refferance Role EID");
						txtRefRole.setText(null);
					}
				}
				else {
					lblNotify.setText(null);
				}
				
			}
		});
		txtRefRole.setBounds(114, 109, 178, 20);
		panel_2.add(txtRefRole);
		txtRefRole.setColumns(10);
		
		JLabel lblReferanceRole = new JLabel("Referance Role");
		lblReferanceRole.setBounds(23, 112, 92, 14);
		panel_2.add(lblReferanceRole);
		lblReferanceRole.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JLabel lblOtRate = new JLabel("OT Rate");
		lblOtRate.setBounds(23, 87, 92, 14);
		panel_2.add(lblOtRate);
		lblOtRate.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JLabel lblBasicSalary = new JLabel("Basic Salary");
		lblBasicSalary.setBounds(23, 62, 92, 14);
		panel_2.add(lblBasicSalary);
		lblBasicSalary.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JLabel lblRegistedDate = new JLabel("Registed Date");
		lblRegistedDate.setBounds(23, 139, 92, 16);
		panel_2.add(lblRegistedDate);
		
		dateChooserRegDate = new JDateChooser();
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = new Date(System.currentTimeMillis());
		//System.out.println(formatter.format(date1));
		String date = formatter.format(date1);
		java.util.Date date2 = null;
		try {
			date2 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		dateChooserRegDate.setDate(date2);
		dateChooserRegDate.setDateFormatString("yyyy-MM-dd");
		dateChooserRegDate.setBounds(114, 142, 178, 22);
		panel_2.add(dateChooserRegDate);
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					
				JFileChooser file = new JFileChooser();
				file.setCurrentDirectory(new File(System.getProperty("user.home")+"/Desktop"));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg" , "png" , "jpeg" , "gif");
				file.addChoosableFileFilter(filter);
				file.setAcceptAllFileFilterUsed(true);
				int result = file.showSaveDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					
					File selectedFile = file.getSelectedFile();
					String path = selectedFile.getAbsolutePath();
					ImagePath = path;
					
					txtFilePath.setText(ImagePath);
				
					
					lblImage.setIcon(ResizeImage(ImagePath));
					
				}
				else if (result == JFileChooser.CANCEL_OPTION){
					JOptionPane.showMessageDialog(null, "No File Selected");
				}
				
				
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
				
			}
		});
		Image img4 = new ImageIcon(this.getClass().getResource("/Preview-2-icon.png")).getImage();
		btnBrowse.setIcon(new ImageIcon(img4));
		btnBrowse.setForeground(new Color(255, 255, 255));
		btnBrowse.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBrowse.setBackground(new Color(30, 144, 255));
		btnBrowse.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnBrowse.setBounds(562, 36, 100, 39);
		panel.add(btnBrowse);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_3.setBounds(451, 25, 100, 125);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		lblImage = new JLabel("");
		lblImage.setBounds(0, 0, 100, 125);
		panel_3.add(lblImage);
		lblImage.setForeground(SystemColor.textHighlightText);
		lblImage.setBackground(SystemColor.window);
		lblImage.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		Image img = new ImageIcon(this.getClass().getResource("/browser-girl-firefox-icon.png")).getImage();
		lblImage.setIcon(new ImageIcon(img));
		
		
		
		JButton btnReset = new JButton("Reset");
		Image img2 = new ImageIcon(this.getClass().getResource("/Clear-icon.png")).getImage();
		btnReset.setIcon(new ImageIcon(img2));
		btnReset.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnReset.setForeground(new Color(255, 255, 255));
		btnReset.setBackground(new Color(30, 144, 255));
		btnReset.setBounds(176, 423, 89, 33);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					resetFields();
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
					
					e.printStackTrace();
				}			
			}
		});
		panel.add(btnReset);
		Image imgDemo = new ImageIcon(this.getClass().getResource("/add-icon.png")).getImage();
		
		JButton btnExit = new JButton("Exit");
		Image img3 = new ImageIcon(this.getClass().getResource("/Alarm-Error-icon.png")).getImage();
		btnExit.setIcon(new ImageIcon(img3));
		btnExit.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnExit.setForeground(new Color(255, 255, 255));
		btnExit.setBackground(new Color(178, 34, 34));
		btnExit.setBounds(562, 423, 89, 33);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		panel.add(btnExit);
		
		JLabel lblNewLabel = new JLabel("Employee ID*");
		lblNewLabel.setBounds(77, 357, 113, 14);
		panel.add(lblNewLabel);
		
		txtEID = new JTextField();
		txtEID.setEditable(false);
		txtEID.setBackground(new Color(224, 255, 255));
		txtEID.setBorder(new LineBorder(Color.BLACK, 1, true));
		txtEID.setBounds(200, 354, 188, 20);
		panel.add(txtEID);
		txtEID.setColumns(10);
		
		JLabel lblBirthday = new JLabel("Date of Birth");
		lblBirthday.setBounds(77, 169, 113, 17);
		panel.add(lblBirthday);
		
		dateChooserBDay = new JDateChooser();
		dateChooserBDay.setDateFormatString("yyyy-MM-dd");
		dateChooserBDay.setFont(new Font("Tahoma", Font.PLAIN, 13));
		dateChooserBDay.setBounds(200, 166, 188, 22);
		panel.add(dateChooserBDay);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 51));
		panel_1.setBounds(0, 81, 855, 10);
		contentPane.add(panel_1);
		
		txtEID.setText(generateEID());
		
		txtFilePath = new JTextField();
		txtFilePath.setEditable(false);
		txtFilePath.setBounds(562, 83, 199, 20);
		panel.add(txtFilePath);
		txtFilePath.setColumns(10);
		
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					newUser.setEID(txtEID.getText());
					newUser.setUsername(txtUserName.getText());
					newUser.setPassword(passwordField.getText());
					newUser.setfName(txtFName.getText());
					newUser.setlName(txtLName.getText());
					if (dateChooserBDay.getDate() != null) {
						newUser.setbDay(((JTextField)dateChooserBDay.getDateEditor().getUiComponent()).getText());
						newUser.setAge(calculateAge(newUser.getbDay())); //age  
					}
					newUser.setAddress(txtAddress.getText());
					newUser.setBankAccNo(txtBankNo.getText());
					newUser.setNICNo(txtNIC.getText());
					newUser.setContactNo(txtContactNo.getText());
					newUser.setEmail(txtEmail.getText());
					newUser.setRole((String)comboRole.getSelectedItem()); 
					if (txtbasicSalary.getText().isEmpty() == true) {
						newUser.setBasicSalary(0);
					}else {
						newUser.setBasicSalary(Double.parseDouble(txtbasicSalary.getText()));
					}
					if (txtOTRate.getText().isEmpty() == true) {
						newUser.setOTRate(0);
					}else {
						newUser.setOTRate(Double.parseDouble(txtOTRate.getText()));
					}
					newUser.setRefferance(txtRefRole.getText());
					
					if (dateChooserRegDate.getDate() != null ) {
						newUser.setRegDate(((JTextField)dateChooserRegDate.getDateEditor().getUiComponent()).getText());
					}
					
					
					//drivingLicenceNo
					
					if (ImagePath == null) {
						// put a default image path if need
					}else {
						InputStream is = new FileInputStream(new File(ImagePath));
						newUser.setProPicture(is);//propicture
					}
					
					//Registration data validation
					if (validateRegistration()) {
						
					
							iUserService.addUser(newUser);
							resetFields();
							dispose();
							
					}
				
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage() );
				}
			}
		});
		Image img1 = new ImageIcon(this.getClass().getResource("/add-icon.png")).getImage();
		btnRegister.setIcon(new ImageIcon(img1));
		btnRegister.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnRegister.setForeground(new Color(255, 255, 255));
		btnRegister.setBackground(new Color(210, 105, 30));
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnRegister.setBounds(354, 414, 113, 47);
		panel.add(btnRegister);
		
		
		
	}
}
