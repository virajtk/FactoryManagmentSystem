package com.interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.border.BevelBorder;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Event;

import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.model.User;
import com.service.IUserService;
import com.service.UserService;
import com.util.DbConnect;

import net.proteanit.sql.DbUtils;

import java.sql.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ManageUser extends JFrame {

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
	private JTextField txtSearchName;
	private JTextField txtRole;
	private JComboBox comboBoxSelect;
	String ImagePath;
	JLabel lblImage;
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageUser frame = new ManageUser();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//Connection Object 
			Connection connection = null;
			private JTable table;
			private JTextField txtDrivingLicenceNo;
			private JLabel lblNotify;
			
			
	public void refreshTable() {
		try {
			
			String query = "SElECT EID AS EmployeeID,username,ContactNo,Role,Reference  FROM user_main";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			pst.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void resetFields() {
		txtEID.setText(null);
		txtUserName.setText(null);
		txtFName.setText(null);
		txtLName.setText(null);
		txtbasicSalary.setText(null);
		txtOTRate.setText(null);
		txtBankNo.setText(null);
		txtRole.setText(null);
		txtNIC.setText(null);
		txtContactNo.setText(null);
		txtEmail.setText(null);
		txtAddress.setText(null);
		txtRefRole.setText(null);
		txtDrivingLicenceNo.setText(null);
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
	public ManageUser() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		setResizable(false);
		
		connection = DbConnect.getDBConnection();
		User user = new User();
		IUserService iUserService = new UserService();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(ManageUser.class.getResource("/Customer-service-icon.png")));
		setTitle("Manage User");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1028, 758);
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
		Title.setBounds(0, 0, 1022, 83);
		contentPane.add(Title);
		Title.setLayout(null);
		
		JLabel lblRegistation = new JLabel("  Manage User");
		Image img5 = new ImageIcon(this.getClass().getResource("/Admin-icon.png")).getImage();
		lblRegistation.setIcon(new ImageIcon(img5));
		lblRegistation.setFont(new Font("Showcard Gothic", Font.PLAIN, 30));
		lblRegistation.setForeground(SystemColor.text);
		lblRegistation.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistation.setBounds(12, 11, 998, 65);
		Title.add(lblRegistation);
		Image img1 = new ImageIcon(this.getClass().getResource("/update-icon.png")).getImage();
		Image img2 = new ImageIcon(this.getClass().getResource("/userdelete-icon.png")).getImage();
		Image img3 = new ImageIcon(this.getClass().getResource("/Alarm-Error-icon.png")).getImage();
		Image img6 = new ImageIcon(this.getClass().getResource("/reload-icon.png")).getImage();
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 51));
		panel_1.setBounds(0, 81, 1022, 10);
		contentPane.add(panel_1);
		
		JPanel panelManage = new JPanel();
		panelManage.setBounds(0, 0, 1022, 726);
		contentPane.add(panelManage);
		panelManage.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 88, 1022, 636);
		panelManage.add(panel);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBackground(new Color(255, 250, 205));
		panel.setLayout(null);
		
		JButton btnRegister = new JButton("Update");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try {	
				user.setEID(txtEID.getText());
				user.setUsername(txtUserName.getText());
				user.setfName(txtFName.getText());
				user.setlName(txtLName.getText());
				user.setBasicSalary(Double.parseDouble(txtbasicSalary.getText()));
				user.setOTRate(Double.parseDouble(txtOTRate.getText()));
				user.setBankAccNo(txtBankNo.getText());
				user.setRole(txtRole.getText());
				user.setNICNo(txtNIC.getText());
				user.setContactNo(txtContactNo.getText());
				user.setEmail(txtEmail.getText());
				user.setAddress(txtAddress.getText());
				user.setRefferance(txtRefRole.getText());
				user.setDrivingLicenceNo(txtDrivingLicenceNo.getText());
				
				if (ImagePath == null) {
					// put a default image path if need
				}else {
					InputStream is = new FileInputStream(new File(ImagePath));
					user.setProPicture(is);//propicture
				}
				
				
				iUserService.updateUser(user.getEID(), user);
				
			}catch(Exception e1) {
				JOptionPane.showMessageDialog(null, "Please select any Profile to Update!"+"\n"+e1.getMessage(),"WARNING!",JOptionPane.ERROR_MESSAGE);
			}finally {
				//kept this if needed
			}
			
				
			}
		});
		btnRegister.setIcon(new ImageIcon(img1));
		btnRegister.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnRegister.setForeground(new Color(255, 255, 255));
		btnRegister.setBackground(new Color(210, 105, 30));
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnRegister.setBounds(382, 550, 113, 47);
		panel.add(btnRegister);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnRemove.setIcon(new ImageIcon(img2));
		btnRemove.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnRemove.setForeground(new Color(255, 255, 255));
		btnRemove.setBackground(new Color(210, 105, 30));
		btnRemove.setBounds(525, 550, 113, 47);
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
						if (txtEID.getText().isEmpty() == true) {
							JOptionPane.showMessageDialog(null, "Please select any Profile to Remove!"+"\n","WARNING!",JOptionPane.ERROR_MESSAGE);
						}
						else {			
							int action = JOptionPane.showConfirmDialog(null, "Do you really want to Remove ?","Remove Profile",JOptionPane.YES_NO_OPTION);
							if (action==0) {
								user.setEID(txtEID.getText());
								iUserService.removeUser(user.getEID());
							}
						
						}
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage(),"WARNING!",JOptionPane.ERROR_MESSAGE);
					}finally {	
						resetFields();
						refreshTable();	
					}
				
				
			}
		});
		panel.add(btnRemove);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnExit.setIcon(new ImageIcon(img3));
		btnExit.setForeground(new Color(255, 255, 255));
		btnExit.setBackground(new Color(178, 34, 34));
		btnExit.setBounds(732, 558, 113, 33);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		panel.add(btnExit);
		
		txtSearchName = new JTextField();
		txtSearchName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSearchName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					
					String selection = (String)comboBoxSelect.getSelectedItem();
					String key = txtSearchName.getText();
					
					//String query = "SELECT EID AS EmployeeID,username,ContactNo,Role,Reference  FROM user_main WHERE "+selection+" = ?"; //I used this to search whole key
					
					String query = "SELECT EID AS EmployeeID,username,ContactNo,Role,Reference  FROM user_main WHERE "+selection+" LIKE '"+key+"%'";
					PreparedStatement pst = connection.prepareStatement(query);
					
					//pst.setString(1,txtSearchName.getText()); //old query usage
					
					ResultSet rs = pst.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					pst.close();
					rs.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		txtSearchName.setToolTipText("Search");
		txtSearchName.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		txtSearchName.setBounds(262, 38, 265, 26);
		panel.add(txtSearchName);
		txtSearchName.setColumns(10);
		
		JButton btnrelead = new JButton("Reload Users");
		btnrelead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					String query = "SELECT EID AS EmployeeID,username,ContactNo,Role,Reference  FROM user_main";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					pst.close();
					rs.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
			}
			
		});
		btnrelead.setIcon(new ImageIcon(img6));
		btnrelead.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnrelead.setBackground(new Color(30, 144, 255));
		btnrelead.setForeground(SystemColor.text);
		btnrelead.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnrelead.setBounds(539, 31, 122, 38);
		panel.add(btnrelead);
		
		comboBoxSelect = new JComboBox();
		comboBoxSelect.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxSelect.setToolTipText("");
		comboBoxSelect.setModel(new DefaultComboBoxModel(new String[] {"EID", "username", "FName", "LName", "Role", "Reference"}));
		comboBoxSelect.setBackground(SystemColor.text);
		comboBoxSelect.setBorder(UIManager.getBorder("ComboBox.border"));
		comboBoxSelect.setBounds(38, 37, 212, 26);
		panel.add(comboBoxSelect);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(38, 289, 947, 238);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Employee ID");
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNewLabel.setBounds(27, 21, 149, 20);
		panel_2.add(lblNewLabel);
		
		txtEID = new JTextField();
		txtEID.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtEID.setBounds(188, 21, 232, 20);
		panel_2.add(txtEID);
		txtEID.setBackground(new Color(224, 255, 255));
		txtEID.setEditable(false);
		txtEID.setBorder(new LineBorder(Color.BLACK, 1, true));
		txtEID.setColumns(10);
		
		JLabel lblUsername = new JLabel("UserName");
		lblUsername.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblUsername.setBounds(27, 49, 149, 20);
		panel_2.add(lblUsername);
		
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
		txtUserName.setBounds(188, 49, 232, 20);
		panel_2.add(txtUserName);
		txtUserName.setBorder(new LineBorder(new Color(0, 0, 51), 1, true));
		txtUserName.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtUserName.setBackground(new Color(224, 255, 255));
		txtUserName.setColumns(10);
		
		JLabel lblRole = new JLabel("Position");
		lblRole.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblRole.setBounds(452, 23, 131, 17);
		panel_2.add(lblRole);
		
		JLabel lblNicNumber = new JLabel("NIC Number");
		lblNicNumber.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNicNumber.setBounds(452, 49, 131, 19);
		panel_2.add(lblNicNumber);
		
		JLabel lblContact = new JLabel("Contact Number");
		lblContact.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblContact.setBounds(452, 74, 131, 18);
		panel_2.add(lblContact);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblEmail.setBounds(452, 99, 131, 20);
		panel_2.add(lblEmail);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblAddress.setBounds(452, 127, 131, 17);
		panel_2.add(lblAddress);
		
		JLabel lblReferanceRole = new JLabel("Referance Role");
		lblReferanceRole.setBounds(452, 152, 131, 19);
		panel_2.add(lblReferanceRole);
		lblReferanceRole.setFont(new Font("Dialog", Font.PLAIN, 16));
		
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
		txtNIC.setBounds(595, 49, 232, 20);
		panel_2.add(txtNIC);
		txtNIC.setBorder(new LineBorder(new Color(0, 0, 51), 1, true));
		txtNIC.setBackground(new Color(224, 255, 255));
		txtNIC.setFont(new Font("Dialog", Font.PLAIN, 15));
		txtNIC.setToolTipText("");
		txtNIC.setColumns(10);
		
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
		txtContactNo.setBounds(595, 75, 232, 20);
		panel_2.add(txtContactNo);
		txtContactNo.setBorder(new LineBorder(new Color(0, 0, 51), 1, true));
		txtContactNo.setBackground(new Color(224, 255, 255));
		txtContactNo.setSelectionColor(SystemColor.textHighlight);
		txtContactNo.setFont(new Font("Dialog", Font.PLAIN, 15));
		txtContactNo.setToolTipText("");
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
		txtEmail.setBounds(595, 102, 232, 21);
		panel_2.add(txtEmail);
		txtEmail.setBorder(new LineBorder(new Color(0, 0, 51), 1, true));
		txtEmail.setBackground(new Color(224, 255, 255));
		txtEmail.setFont(new Font("Dialog", Font.PLAIN, 15));
		txtEmail.setToolTipText("");
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
		txtAddress.setBounds(595, 128, 232, 19);
		panel_2.add(txtAddress);
		txtAddress.setBorder(new LineBorder(new Color(0, 0, 51), 1, true));
		txtAddress.setBackground(new Color(224, 255, 255));
		txtAddress.setFont(new Font("Dialog", Font.PLAIN, 15));
		txtAddress.setColumns(10);
		
		txtRefRole = new JTextField(); //this should be a drop down with all the EIDs of up to supervisor level
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
		txtRefRole.setFont(new Font("Dialog", Font.PLAIN, 15));
		txtRefRole.setBounds(595, 154, 232, 20);
		panel_2.add(txtRefRole);
		txtRefRole.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		txtRefRole.setBackground(new Color(224, 255, 255));
		txtRefRole.setColumns(10);
		
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
		txtBankNo.setBounds(188, 180, 232, 20);
		panel_2.add(txtBankNo);
		txtBankNo.setBorder(new LineBorder(new Color(0, 0, 51), 1, true));
		txtBankNo.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtBankNo.setBackground(new Color(224, 255, 255));
		txtBankNo.setColumns(10);
		
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
		txtOTRate.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtOTRate.setBounds(188, 155, 232, 20);
		panel_2.add(txtOTRate);
		txtOTRate.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		txtOTRate.setBackground(new Color(224, 255, 255));
		txtOTRate.setColumns(10);
		
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
		txtbasicSalary.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtbasicSalary.setBounds(188, 130, 232, 20);
		panel_2.add(txtbasicSalary);
		txtbasicSalary.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		txtbasicSalary.setBackground(new Color(224, 255, 255));
		txtbasicSalary.setColumns(10);
		
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
		txtLName.setBounds(188, 102, 232, 20);
		panel_2.add(txtLName);
		txtLName.setBorder(new LineBorder(new Color(0, 0, 51), 1, true));
		txtLName.setBackground(new Color(224, 255, 255));
		txtLName.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtLName.setColumns(10);
		
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
		txtFName.setBounds(188, 77, 232, 20);
		panel_2.add(txtFName);
		txtFName.setBorder(new LineBorder(new Color(0, 0, 51), 1, true));
		txtFName.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtFName.setBackground(new Color(224, 255, 255));
		txtFName.setColumns(10);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblFirstName.setBounds(27, 77, 149, 20);
		panel_2.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblLastName.setBounds(27, 102, 149, 19);
		panel_2.add(lblLastName);
		
		JLabel lblBasicSalary = new JLabel("Basic Salary");
		lblBasicSalary.setBounds(27, 130, 149, 20);
		panel_2.add(lblBasicSalary);
		lblBasicSalary.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		JLabel lblOtRate = new JLabel("OT Rate");
		lblOtRate.setBounds(27, 158, 149, 20);
		panel_2.add(lblOtRate);
		lblOtRate.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		JLabel lblBankAccNo = new JLabel("Bank Account No");
		lblBankAccNo.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblBankAccNo.setBounds(27, 180, 149, 17);
		panel_2.add(lblBankAccNo);	
		
		txtRole = new JTextField();
		txtRole.setToolTipText("");
		txtRole.setFont(new Font("Dialog", Font.PLAIN, 15));
		txtRole.setColumns(10);
		txtRole.setBorder(new LineBorder(new Color(0, 0, 51), 1, true));
		txtRole.setBackground(new Color(224, 255, 255));
		txtRole.setBounds(595, 23, 232, 20);
		panel_2.add(txtRole);
		
		JLabel lblDrivingLicence = new JLabel("Driving Licence No");
		lblDrivingLicence.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblDrivingLicence.setBounds(452, 180, 149, 16);
		panel_2.add(lblDrivingLicence);
		
		txtDrivingLicenceNo = new JTextField();
		txtDrivingLicenceNo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				String DrivingLNo = txtDrivingLicenceNo.getText();
				
				if ( !DrivingLNo.matches("[0-9 ,]+") & DrivingLNo.length() != 0 ) {
					lblNotify.setText("Please Enter Valide Driving Licence Number");
					txtDrivingLicenceNo.setText(null);
					//txtDrivingLicenceNo.requestFocusInWindow();
				}
				else {
					lblNotify.setText(null);
				}
				
			}
		});
		txtDrivingLicenceNo.setFont(new Font("Dialog", Font.PLAIN, 15));
		txtDrivingLicenceNo.setColumns(10);
		txtDrivingLicenceNo.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		txtDrivingLicenceNo.setBackground(new Color(224, 255, 255));
		txtDrivingLicenceNo.setBounds(595, 180, 232, 20);
		panel_2.add(txtDrivingLicenceNo);
		
		Image img7 = new ImageIcon(this.getClass().getResource("/person-icon.png")).getImage();
		Image img4 = new ImageIcon(this.getClass().getResource("/Clear-icon.png")).getImage();
		
		lblImage = new JLabel("");
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblImage.setBounds(837, 36, 100, 125);
		lblImage.setIcon(new ImageIcon(img7));
		panel_2.add(lblImage);
		
				
				
				JButton btnChange = new JButton("Change");
				btnChange.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
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
								
								//txtFilePath.setText(ImagePath);
							
								
								lblImage.setIcon(ResizeImage(ImagePath));
								
							}
							else if (result == JFileChooser.CANCEL_OPTION){
								JOptionPane.showMessageDialog(null, "No File Selected");
							}
							
							
							}catch(Exception e1) {
								JOptionPane.showMessageDialog(null, e1);
							}
						
					}
				});
				btnChange.setBounds(837, 177, 100, 23);
				panel_2.add(btnChange);
				
				lblNotify = new JLabel("...");
				lblNotify.setForeground(new Color(153, 0, 0));
				lblNotify.setBounds(27, 213, 393, 14);
				panel_2.add(lblNotify);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBackground(new Color(255, 255, 255));
				scrollPane.setFont(new Font("Tahoma", Font.BOLD, 25));
				scrollPane.setBounds(38, 83, 947, 195);
				panel.add(scrollPane);
				
				table = new JTable();
				table.setSurrendersFocusOnKeystroke(true);
				table.setShowVerticalLines(false);
				table.setFont(new Font("Tahoma", Font.PLAIN, 16));
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						
						try {
							int row = table.getSelectedRow();
							String EID_ = (table.getModel().getValueAt(row, 0)).toString();
							
							String query = "SELECT * FROM user_main WHERE EID = '"+EID_+"' ";
							PreparedStatement pst = connection.prepareStatement(query);
							
							ResultSet rs = pst.executeQuery();
							
							while (rs.next()) {
								
								user.setEID((rs.getString("EID")));
								user.setUsername(rs.getString("username"));
								user.setfName(rs.getString("FName"));
								user.setlName(rs.getString("LName"));
								if (rs.getString("BasicSalary") == null) {
									user.setBasicSalary(0);
								}else {
									user.setBasicSalary(Double.parseDouble(rs.getString("BasicSalary")));
								}
								if (rs.getString("OTRate") == null) {
									user.setOTRate(0);
								}else {
									user.setOTRate(Double.parseDouble(rs.getString("OTRate")));
								}
								user.setBankAccNo(rs.getString("BankAccountNo"));
								user.setRole(rs.getString("Role"));
								user.setNICNo(rs.getString("NICNo"));
								user.setContactNo(rs.getString("ContactNo"));
								user.setEmail(rs.getString("Email"));
								user.setAddress(rs.getString("Address"));
								user.setRefferance(rs.getString("Reference"));
								user.setDrivingLicenceNo(rs.getString("DrivingLicenceNo"));
								
								txtEID.setText(user.getEID());
								txtUserName.setText(user.getUsername());
								txtFName.setText(user.getfName());
								txtLName.setText(user.getlName());
								txtbasicSalary.setText(Double.toString(user.getBasicSalary()));
								txtOTRate.setText(Double.toString(user.getOTRate()));
								txtBankNo.setText(user.getBankAccNo());
								txtRole.setText(user.getRole());
								txtNIC.setText(user.getNICNo());
								txtContactNo.setText(user.getContactNo());
								txtEmail.setText(user.getEmail());
								txtAddress.setText(user.getAddress());
								txtRefRole.setText(user.getRefferance());
								txtDrivingLicenceNo.setText(user.getDrivingLicenceNo());
								
								if (rs.getBytes("proPicture") != null ) {
									
								
								byte[] imge = rs.getBytes("proPicture");
								ImageIcon image = new ImageIcon(imge);
								Image im = image.getImage();
								Image myImg = im.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
								ImageIcon newImage = new ImageIcon(myImg);
								//System.out.println(newImage);
								lblImage.setIcon(newImage);
								
								}
								else {
									Image img7 = new ImageIcon(this.getClass().getResource("/Places-user-identity-icon.png")).getImage();
									lblImage.setIcon(new ImageIcon(img7));
								}
								
							}
							
							pst.close();
							
						}catch(Exception e) {
							JOptionPane.showMessageDialog(null, e);
						}
					}
				});
				table.setBackground(new Color(240, 230, 140));
				scrollPane.setViewportView(table);
				
				JComboBox comboBoxUserLevel = new JComboBox();
				comboBoxUserLevel.setToolTipText("Search by User Levels");
				comboBoxUserLevel.setBorder(UIManager.getBorder("ComboBox.border"));
				comboBoxUserLevel.setModel(new DefaultComboBoxModel(new String[] {"Worker", "Shifter", "Driver", "Kitchen", "Cleaner", "Supervisor", "Manager", "Admin"}));
				comboBoxUserLevel.setFont(new Font("Tahoma", Font.PLAIN, 14));
				comboBoxUserLevel.setBounds(720, 38, 265, 26);
				
				//Implement Action for the User Level Search
				comboBoxUserLevel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						try {
							
							String selection = (String)comboBoxUserLevel.getSelectedItem();
							
							String query = "SELECT EID AS EmployeeID,username,ContactNo,Role,Reference  FROM user_main WHERE Role LIKE '%"+selection+"%'";
							
							PreparedStatement pst = connection.prepareStatement(query);
							
							ResultSet rs = pst.executeQuery();
							
							table.setModel(DbUtils.resultSetToTableModel(rs));
							
							pst.close();
							rs.close();
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				panel.add(comboBoxUserLevel);
				
				JLabel lblSearchBy = new JLabel("Search by");
				lblSearchBy.setForeground(SystemColor.textInactiveText);
				lblSearchBy.setBounds(38, 23, 149, 14);
				panel.add(lblSearchBy);
				
				JLabel lblSearchByUser = new JLabel("Search by User position");
				lblSearchByUser.setForeground(SystemColor.textInactiveText);
				lblSearchByUser.setBounds(720, 22, 179, 14);
				panel.add(lblSearchByUser);
				
				JButton btnReset = new JButton("Reset");
				btnReset.setFont(new Font("Tahoma", Font.PLAIN, 15));
				btnReset.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							resetFields();
						}catch(Exception e) {
							JOptionPane.showMessageDialog(null, e);
						}
					}
				});
				btnReset.setBackground(new Color(30, 144, 255));
				btnReset.setForeground(SystemColor.text);
				btnReset.setBounds(175, 556, 113, 33);
				btnReset.setIcon(new ImageIcon(img4));
				panel.add(btnReset);
		
		
		refreshTable();
	}
}
