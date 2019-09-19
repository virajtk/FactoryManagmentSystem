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
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.chrono.IsoEra;
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

import com.model.User;
import com.service.IUserService;
import com.service.UserService;
import com.util.DbConnect;

import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class AddWorker extends JFrame {

	private JPanel contentPane;
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
	Connection connection = null; 
	private JTextField txtDrivingLNo;
	String ImagePath;
	private JLabel lblImage;
	private JTextField txtFilePath;
	private JDateChooser dateChooserBDay;
	private JDateChooser dateChooserRegDate;
	private JLabel lblNotify;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddWorker frame = new AddWorker();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//do not know is this the best way or not, but i this by my own thinking 
	public String generateEID() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		
		connection = DbConnect.getDBConnection();
		String lastID = null;
		String query = "SELECT EID FROM user_main";
		
		PreparedStatement pst = connection.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
		
		while ( rs.next() ) {
			
			String str = rs.getString("EID");
			String eid[] = str.split("E");
			lastID = eid[1];
		}
		int lastId = Integer.parseInt(lastID);
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
	
	public ImageIcon ResizeImage (String ImagePath) {
		
		ImageIcon imageIcon = new ImageIcon(ImagePath);
		Image img = imageIcon.getImage();
		Image modifiedDabImage = img.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon image = new ImageIcon(modifiedDabImage);
		
		return image;
	}
	
	public void resetFields() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		
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
	
	

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	
	
	public AddWorker() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		setResizable(false);
		User newUser = new User();
		IUserService iUserService = new UserService();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddWorker.class.getResource("/Customer-service-icon.png")));
		setTitle("Add Workers");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
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
		Title.setBounds(0, 0, 793, 83);
		contentPane.add(Title);
		Title.setLayout(null);
		
		JLabel lblRegistation = new JLabel("  Add Workers");
		Image img5 = new ImageIcon(this.getClass().getResource("/user-new-icon.png")).getImage();
		lblRegistation.setIcon(new ImageIcon(img5));
		lblRegistation.setFont(new Font("Showcard Gothic", Font.PLAIN, 30));
		lblRegistation.setForeground(SystemColor.text);
		lblRegistation.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistation.setBounds(10, 11, 773, 65);
		Title.add(lblRegistation);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBackground(new Color(245, 245, 220));
		panel.setBounds(10, 96, 774, 458);
		contentPane.add(panel);
		panel.setLayout(null);
		
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
		dateChooserRegDate.setBounds(514, 291, 178, 22);
		panel.add(dateChooserRegDate);
		dateChooserRegDate.setDateFormatString("yyyy-MM-dd");
		
		JLabel lblRegDate = new JLabel("Registed Date");
		lblRegDate.setBounds(423, 291, 92, 14);
		panel.add(lblRegDate);
		
		JLabel lblFirstName = new JLabel("First Name*");
		lblFirstName.setBounds(53, 50, 113, 14);
		panel.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(53, 75, 113, 14);
		panel.add(lblLastName);
		
		JLabel lblUsername = new JLabel("UserName*");
		lblUsername.setBounds(53, 22, 113, 14);
		panel.add(lblUsername);
		
		JLabel lblContact = new JLabel("Contact Number");
		lblContact.setBounds(53, 214, 113, 14);
		panel.add(lblContact);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(53, 244, 71, 14);
		panel.add(lblEmail);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(53, 100, 113, 14);
		panel.add(lblAddress);
		
		JLabel lblNicNumber = new JLabel("NIC Number");
		lblNicNumber.setBounds(53, 150, 113, 14);
		panel.add(lblNicNumber);
		
		JLabel lblRole = new JLabel("Role*");
		lblRole.setBounds(53, 269, 113, 14);
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
		txtLName.setBounds(176, 72, 188, 20);
		txtLName.setBorder(new LineBorder(new Color(0, 0, 51), 1, true));
		txtLName.setBackground(new Color(224, 255, 255));
		txtLName.setFont(new Font("Dialog", Font.PLAIN, 13));
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
		txtContactNo.setBounds(176, 210, 188, 20);
		txtContactNo.setBorder(new LineBorder(new Color(0, 0, 51), 1, true));
		txtContactNo.setBackground(new Color(224, 255, 255));
		txtContactNo.setSelectionColor(SystemColor.textHighlight);
		txtContactNo.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtContactNo.setToolTipText("Contact Number");
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
		txtEmail.setBounds(176, 237, 188, 20);
		txtEmail.setBorder(new LineBorder(new Color(0, 0, 51), 1, true));
		txtEmail.setBackground(new Color(224, 255, 255));
		txtEmail.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtEmail.setToolTipText("example@unic.com");
		panel.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtAddress = new JTextField();
		txtAddress.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				String Address = txtAddress.getText();
				
				if ( !Address.matches("[a-zA-Z0-9/ ,]+") & Address.length() != 0 ) {
					lblNotify.setText("Please Enter Valide Address");
					txtAddress.setText(null);
					//txtAddress.requestFocusInWindow();
				}
				else {
					lblNotify.setText(null);
				}
				
			}
		});
		txtAddress.setBounds(176, 97, 188, 20);
		txtAddress.setBorder(new LineBorder(new Color(0, 0, 51), 1, true));
		txtAddress.setBackground(new Color(224, 255, 255));
		txtAddress.setFont(new Font("Dialog", Font.PLAIN, 13));
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
		txtNIC.setBounds(176, 147, 188, 20);
		txtNIC.setBorder(new LineBorder(new Color(0, 0, 51), 1, true));
		txtNIC.setBackground(new Color(224, 255, 255));
		txtNIC.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtNIC.setToolTipText("970150390V");
		panel.add(txtNIC);
		txtNIC.setColumns(10);
		
		JComboBox<String> comboRole = new JComboBox<String>();
		comboRole.setBounds(176, 265, 188, 20);
		comboRole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String value = (String)comboRole.getSelectedItem();
				
				if (value == "Driver") {
					txtDrivingLNo.setEditable(true);
				}
				else {
					txtDrivingLNo.setEditable(false);
				}
				
				if (value == "Driver" | value == "Kitchen" | value == "Cleaner" ) {
					txtbasicSalary.setEditable(true);
					txtOTRate.setEditable(true);
				}
				else {
					txtbasicSalary.setEditable(false);
					txtOTRate.setEditable(false);
				}
			}
		});
		comboRole.setBorder(new LineBorder(new Color(0, 0, 51), 1, true));
		comboRole.setBackground(new Color(224, 255, 255));
		comboRole.setForeground(SystemColor.textText);
		comboRole.setFont(new Font("Dialog", Font.PLAIN, 13));
		comboRole.setModel(new DefaultComboBoxModel(new String[] {"Mechine Worker", "Molded Worker", "Shifter", "Driver", "Kitchen", "Cleaner"}));
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
		txtUserName.setBounds(176, 19, 188, 20);
		txtUserName.setBorder(new LineBorder(new Color(0, 0, 51), 1, true));
		txtUserName.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtUserName.setBackground(new Color(224, 255, 255));
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
		txtFName.setBounds(176, 47, 188, 20);
		txtFName.setBorder(new LineBorder(new Color(0, 0, 51), 1, true));
		txtFName.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtFName.setBackground(new Color(224, 255, 255));
		panel.add(txtFName);
		txtFName.setColumns(10);
		
		JLabel lblBankAccNo = new JLabel("Bank Account No");
		lblBankAccNo.setBounds(53, 125, 113, 14);
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
		txtBankNo.setBounds(176, 122, 188, 20);
		txtBankNo.setBorder(new LineBorder(new Color(0, 0, 51), 1, true));
		txtBankNo.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtBankNo.setBackground(new Color(224, 255, 255));
		panel.add(txtBankNo);
		txtBankNo.setColumns(10);
		
		JLabel lblBasicSalary = new JLabel("Basic Salary");
		lblBasicSalary.setBounds(423, 216, 92, 14);
		lblBasicSalary.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel.add(lblBasicSalary);
		
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
		txtbasicSalary.setBounds(514, 213, 178, 20);
		txtbasicSalary.setEditable(false);
		panel.add(txtbasicSalary);
		txtbasicSalary.setColumns(10);
		
		JLabel lblOtRate = new JLabel("OT Rate");
		lblOtRate.setBounds(423, 241, 92, 14);
		lblOtRate.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel.add(lblOtRate);
		
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
		txtOTRate.setBounds(514, 238, 178, 20);
		txtOTRate.setEditable(false);
		panel.add(txtOTRate);
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
		txtRefRole.setBounds(514, 263, 178, 20);
		panel.add(txtRefRole);
		txtRefRole.setColumns(10);
		
		JLabel lblReferanceRole = new JLabel("Referance Role");
		lblReferanceRole.setBounds(423, 266, 92, 14);
		lblReferanceRole.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel.add(lblReferanceRole);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(406, 157, 310, 182);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblFilleWithAuthorized = new JLabel("Fille with Authorized Person");
		lblFilleWithAuthorized.setBounds(23, 24, 205, 14);
		panel_2.add(lblFilleWithAuthorized);
		lblFilleWithAuthorized.setForeground(new Color(0, 0, 102));
		lblFilleWithAuthorized.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setBounds(539, 38, 89, 28);
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
		panel.add(btnBrowse);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(423, 17, 100, 125);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
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
		//lblImage.setIcon(new ImageIcon("/icons8-user-male-50-2.png"));
		
		JButton btnRegister = new JButton("ADD");
		btnRegister.setBounds(330, 371, 113, 47);
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				
				newUser.setEID(txtEID.getText());
				newUser.setUsername(txtUserName.getText());
				newUser.setfName(txtFName.getText());
				newUser.setlName(txtLName.getText());
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
				if (ImagePath == null) {
					// put a default image path if need
				}else {
				InputStream is = null;
				
					is = new FileInputStream(new File(ImagePath));
				
				newUser.setProPicture(is);//propicture
				}
				//regDate
				if (((JTextField)dateChooserRegDate.getDateEditor().getUiComponent()).getText() != null ) {
					newUser.setRegDate(((JTextField)dateChooserRegDate.getDateEditor().getUiComponent()).getText());
				}
				//Birthday
				//Age
				if (((JTextField)dateChooserBDay.getDateEditor().getUiComponent()).getText() != null) {
					newUser.setbDay(((JTextField)dateChooserBDay.getDateEditor().getUiComponent()).getText());
					newUser.setAge(calculateAge(newUser.getbDay())); //age  
				}
				
				
				iUserService.addUser(newUser);
				resetFields();
				
				} catch (Exception e1) {
					e1.printStackTrace();
			}
				
			}
		});
		Image img1 = new ImageIcon(this.getClass().getResource("/add-icon.png")).getImage();
		btnRegister.setIcon(new ImageIcon(img1));
		btnRegister.setForeground(new Color(255, 255, 255));
		btnRegister.setBackground(new Color(210, 105, 30));
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(btnRegister);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(163, 380, 97, 33);
		Image img2 = new ImageIcon(this.getClass().getResource("/Clear-icon.png")).getImage();
		btnReset.setIcon(new ImageIcon(img2));
		btnReset.setForeground(new Color(255, 255, 255));
		btnReset.setBackground(new Color(30, 144, 255));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		
//				txtLName.setText(null);
//				txtContactNo.setText(null);
//				txtEmail.setText(null);
//				txtAddress.setText(null);
//				txtNIC.setText(null);
//				txtUserName.setText(null);
//				txtFName.setText(null);
//				txtBankNo.setText(null);
//				txtbasicSalary.setText(null);
//				txtOTRate.setText(null);
//				txtRefRole.setText(null);
//				txtEID.setText(null);
				
				try {
					resetFields();
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
					e.printStackTrace();
				}
				
			}
		});
		panel.add(btnReset);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(531, 380, 97, 33);
		Image img3 = new ImageIcon(this.getClass().getResource("/Alarm-Error-icon.png")).getImage();
		btnExit.setIcon(new ImageIcon(img3));
		btnExit.setForeground(new Color(255, 255, 255));
		btnExit.setBackground(new Color(178, 34, 34));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		panel.add(btnExit);
		
		JLabel lblNewLabel = new JLabel("Employee ID*");
		lblNewLabel.setBounds(53, 322, 113, 14);
		panel.add(lblNewLabel);
		
		txtEID = new JTextField();
		txtEID.setBounds(176, 319, 188, 20);
		txtEID.setEditable(false);
		txtEID.setBackground(new Color(224, 255, 255));
		panel.add(txtEID);
		txtEID.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 128));
		panel_1.setBounds(0, 81, 793, 10);
		contentPane.add(panel_1);
		
		txtEID.setText(generateEID());
		
		JLabel lblDrivingLiecenceNo = new JLabel("Driving Liecence No");
		lblDrivingLiecenceNo.setBounds(53, 294, 113, 14);
		panel.add(lblDrivingLiecenceNo);
		
		txtDrivingLNo = new JTextField();
		txtDrivingLNo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				String DrivingLNo = txtDrivingLNo.getText();
				
				if ( !DrivingLNo.matches("[0-9 ,]+") & DrivingLNo.length() != 0 ) {
					lblNotify.setText("Please Enter Valide Driving Licence Number");
					txtDrivingLNo.setText(null);
					//txtDrivingLNo.requestFocusInWindow();
				}
				else {
					lblNotify.setText(null);
				}
				
			}
		});
		txtDrivingLNo.setBounds(176, 291, 188, 20);
		txtDrivingLNo.setEditable(false);
		txtDrivingLNo.setToolTipText("example@unic.com");
		txtDrivingLNo.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtDrivingLNo.setColumns(10);
		txtDrivingLNo.setBackground(new Color(224, 255, 255));
		panel.add(txtDrivingLNo);
		
		txtFilePath = new JTextField();
		txtFilePath.setBounds(539, 74, 177, 20);
		txtFilePath.setEditable(false);
		panel.add(txtFilePath);
		txtFilePath.setColumns(10);
		
		dateChooserBDay = new JDateChooser();
		dateChooserBDay.setBounds(176, 175, 188, 22);
		dateChooserBDay.setFont(new Font("Tahoma", Font.PLAIN, 13));
		dateChooserBDay.setDateFormatString("yyyy-MM-dd");
		panel.add(dateChooserBDay);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth*");
		lblDateOfBirth.setBounds(53, 175, 92, 14);
		panel.add(lblDateOfBirth);
		
		lblNotify = new JLabel("...");
		lblNotify.setForeground(new Color(153, 0, 0));
		lblNotify.setHorizontalAlignment(SwingConstants.LEFT);
		lblNotify.setBounds(53, 347, 311, 14);
		panel.add(lblNotify);
	}
}
