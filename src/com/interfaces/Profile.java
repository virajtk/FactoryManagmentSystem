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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Event;

import javax.swing.border.CompoundBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.imageio.ImageIO;
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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Profile extends JFrame {

	private static final String img = null;
	private JPanel contentPane;
	private JTextField txtLName;
	private JTextField txtContactNo;
	private JTextField txtEmail;
	private JTextField txtAddress;
	private JTextField txtNIC;
	private JTextField txtUserName;
	private JTextField txtFName;
	private JTextField txtBankNo;
	private JTextField txtRefRole;
	private JTextField txtEID;
	private JTextField txtRole;
	private static Connection connection ;
	private JTextField txtImagePath;
	String ImagePath;
	JLabel lblImage;
	private JLabel lblNotify;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Profile frame = new Profile("E0007");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
	public Profile(String eid) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		setUndecorated(true);
		setResizable(false);
		IUserService iUserService = new UserService();
		connection = DbConnect.getDBConnection();
		User logUser = new User();	
		
		//get data from Db to the object
		try {

			
			String query = "SELECT * FROM user_main WHERE EID = '"+eid+"' ";
			PreparedStatement pst = connection.prepareStatement(query);
			
			ResultSet rs = pst.executeQuery();
		
			while (rs.next()) {
		
			logUser.setEID(rs.getString("EID"));
			logUser.setUsername(rs.getString("username"));
			logUser.setfName(rs.getString("FName"));
			logUser.setlName(rs.getString("LName"));
			logUser.setAddress(rs.getString("Address"));
			logUser.setBankAccNo(rs.getString("BankAccountNo"));
			logUser.setNICNo(rs.getString("NICNo"));
			logUser.setContactNo(rs.getString("ContactNo"));
			logUser.setEmail(rs.getString("Email"));
			logUser.setRole(rs.getString("Role"));
			logUser.setRefferance(rs.getString("Reference"));
			//get Image to the object
//			Blob blob = rs.getBlob("proPicture");
//			InputStream in = blob.getBinaryStream();
//			logUser.setProPicture(in);
			}
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}

		
		//setText();
		
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Profile.class.getResource("/profile-icon.png")));
		setTitle("User Profile");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		setBounds(100, 100, 793, 482);
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
		
		JLabel lblRegistation = new JLabel("  User Profile");
		Image img5 = new ImageIcon(this.getClass().getResource("/profile-icon1.png")).getImage();
		lblRegistation.setIcon(new ImageIcon(img5));
		lblRegistation.setFont(new Font("Showcard Gothic", Font.PLAIN, 30));
		lblRegistation.setForeground(SystemColor.text);
		lblRegistation.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistation.setBounds(10, 11, 773, 65);
		Title.add(lblRegistation);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBackground(new Color(255, 250, 205));
		panel.setBounds(10, 102, 764, 360);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(51, 68, 113, 14);
		panel.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(51, 93, 113, 14);
		panel.add(lblLastName);
		
		JLabel lblUsername = new JLabel("UserName");
		lblUsername.setBounds(51, 40, 113, 14);
		panel.add(lblUsername);
		
		JLabel lblContact = new JLabel("Contact Number");
		lblContact.setBounds(51, 193, 113, 14);
		panel.add(lblContact);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(51, 223, 71, 14);
		panel.add(lblEmail);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(51, 118, 113, 14);
		panel.add(lblAddress);
		
		JLabel lblNicNumber = new JLabel("NIC Number");
		lblNicNumber.setBounds(51, 168, 113, 14);
		panel.add(lblNicNumber);
		
		JLabel lblRole = new JLabel("Position");
		lblRole.setBounds(406, 190, 113, 14);
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
		txtLName.setEditable(false);
		txtLName.setBounds(174, 90, 188, 20);
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
		txtContactNo.setEditable(false);
		txtContactNo.setBounds(174, 189, 188, 20);
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
		txtEmail.setEditable(false);
		txtEmail.setBounds(174, 216, 188, 20);
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
		txtAddress.setEditable(false);
		txtAddress.setBounds(174, 115, 188, 20);
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
		txtNIC.setEditable(false);
		txtNIC.setBounds(174, 165, 188, 20);
		txtNIC.setBackground(new Color(224, 255, 255));
		txtNIC.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtNIC.setToolTipText("970150390V");
		panel.add(txtNIC);
		txtNIC.setColumns(10);
		
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
		txtUserName.setEditable(false);
		txtUserName.setBounds(174, 37, 188, 20);
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
		txtFName.setEditable(false);
		txtFName.setBounds(174, 65, 188, 20);
		txtFName.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtFName.setBackground(new Color(224, 255, 255));
		panel.add(txtFName);
		txtFName.setColumns(10);
		
		JLabel lblBankAccNo = new JLabel("Bank Account No");
		lblBankAccNo.setBounds(51, 143, 113, 14);
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
		txtBankNo.setEditable(false);
		txtBankNo.setBounds(174, 140, 188, 20);
		txtBankNo.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtBankNo.setBackground(new Color(224, 255, 255));
		panel.add(txtBankNo);
		txtBankNo.setColumns(10);
		
		txtRefRole = new JTextField();
		txtRefRole.setEditable(false);
		txtRefRole.setBounds(529, 217, 188, 20);
		txtRefRole.setBackground(new Color(224, 255, 255));
		panel.add(txtRefRole);
		txtRefRole.setColumns(10);
		
		JLabel lblReferanceRole = new JLabel("Referance Role");
		lblReferanceRole.setBounds(406, 217, 92, 14);
		lblReferanceRole.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel.add(lblReferanceRole);
		
		JButton btnBrowse = new JButton("Change");
		btnBrowse.setEnabled(false);
		btnBrowse.addActionListener(new ActionListener() {
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
						
						txtImagePath.setText(ImagePath);
					
						
						lblImage.setIcon(ResizeImage(ImagePath));
						
					}
					else if (result == JFileChooser.CANCEL_OPTION){
						JOptionPane.showMessageDialog(null, "No File Selected");
					}
					
					
					}catch(Exception e1) {
						JOptionPane.showMessageDialog(null, e);
					}
			}
		});
		btnBrowse.setBounds(537, 68, 113, 33);
		Image img4 = new ImageIcon(this.getClass().getResource("/Actions-edit-undo-icon.png")).getImage();
		btnBrowse.setIcon(new ImageIcon(img4));
		btnBrowse.setForeground(new Color(255, 255, 255));
		btnBrowse.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBrowse.setBackground(new Color(30, 144, 255));
		panel.add(btnBrowse);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(406, 15, 100, 125);
		panel_3.setBackground(SystemColor.controlHighlight);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		lblImage = new JLabel("");
		lblImage.setBounds(0, 0, 100, 125);
		panel_3.add(lblImage);
		lblImage.setForeground(SystemColor.textHighlightText);
		lblImage.setBackground(SystemColor.text);
		lblImage.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		Image img = new ImageIcon(this.getClass().getResource("/Places-user-identity-icon.png")).getImage();
		lblImage.setIcon(new ImageIcon(img));
		
		
		JButton btnRegister = new JButton("Update");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (txtUserName.isEditable() == false ) {
					//JOptionPane.showMessageDialog(null, "do that");
					txtUserName.setEditable(true);
					txtFName.setEditable(true);
					txtLName.setEditable(true);
					txtAddress.setEditable(true);
					txtBankNo.setEditable(true);
					txtNIC.setEditable(true);
					txtContactNo.setEditable(true);
					txtEmail.setEditable(true);
					//txtRole.setEditable(true);
					//txtRefRole.setEditable(true);
					btnBrowse.setEnabled(true);
					
				}
				
				else {
					
					try {
					
					logUser.setEID(txtEID.getText());
					logUser.setUsername(txtUserName.getText());
					logUser.setfName(txtFName.getText());
					logUser.setlName(txtLName.getText());
					logUser.setAddress(txtAddress.getText());
					logUser.setBankAccNo(txtBankNo.getText());
					logUser.setNICNo(txtNIC.getText());
					logUser.setContactNo(txtContactNo.getText());
					logUser.setEmail(txtEmail.getText());
					logUser.setRole(txtRole.getText());
					logUser.setRefferance(txtRefRole.getText());
					if (ImagePath == null) {
						// put a default image path if need
					}else {
						InputStream is = new FileInputStream(new File(ImagePath));
						logUser.setProPicture(is);//propicture
					}
					
					
					iUserService.updateUser(logUser.getEID(), logUser);
					
					
					txtUserName.setEditable(false);
					txtFName.setEditable(false);
					txtLName.setEditable(false);
					txtAddress.setEditable(false);
					txtBankNo.setEditable(false);
					txtNIC.setEditable(false);
					txtContactNo.setEditable(false);
					txtEmail.setEditable(false);
					txtRole.setEditable(false);
					txtRefRole.setEditable(false);
					btnBrowse.setEnabled(false);
					
					}catch(Exception e1) {
						JOptionPane.showMessageDialog(null, e1);
					}
					
					
					
				}
				
			}
		});
		btnRegister.setBounds(327, 264, 125, 47);
		Image img1 = new ImageIcon(this.getClass().getResource("/update-icon.png")).getImage();
		btnRegister.setIcon(new ImageIcon(img1));
		btnRegister.setForeground(new Color(255, 255, 255));
		btnRegister.setBackground(new Color(210, 105, 30));
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(btnRegister);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(152, 273, 113, 33);
		Image img2 = new ImageIcon(this.getClass().getResource("/userdelete-icon.png")).getImage();
		btnRemove.setIcon(new ImageIcon(img2));
		btnRemove.setForeground(new Color(255, 255, 255));
		btnRemove.setBackground(new Color(30, 144, 255));
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (JOptionPane.showConfirmDialog(null, "Confirm if you really want to Delete your account", "Delete Account Confirmation" ,
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION ){
					iUserService.removeUser(logUser.getEID());
					dispose();
				}	
			}
		});
		panel.add(btnRemove);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(517, 273, 113, 33);
		Image img3 = new ImageIcon(this.getClass().getResource("/Alarm-Error-icon.png")).getImage();
		btnExit.setIcon(new ImageIcon(img3));
		btnExit.setForeground(new Color(255, 255, 255));
		btnExit.setBackground(new Color(178, 34, 34));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					mainUI home = new mainUI(logUser.getEID());
					home.setVisible(true);
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		panel.add(btnExit);
		
		JLabel lblNewLabel = new JLabel("Employee ID");
		lblNewLabel.setBounds(406, 162, 113, 14);
		panel.add(lblNewLabel);
		
		txtEID = new JTextField();
		txtEID.setEditable(false);
		txtEID.setBounds(529, 159, 188, 20);
		txtEID.setBackground(new Color(224, 255, 255));
		panel.add(txtEID);
		txtEID.setColumns(10);
		
		txtRole = new JTextField();
		txtRole.setBounds(529, 187, 188, 20);
		txtRole.setEditable(false);
		txtRole.setColumns(10);
		txtRole.setBackground(new Color(224, 255, 255));
		panel.add(txtRole);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 128));
		panel_1.setBounds(0, 81, 793, 10);
		contentPane.add(panel_1);
		
		
		txtEID.setText(logUser.getEID());
		txtUserName.setText(logUser.getUsername());
		txtFName.setText(logUser.getfName());
		txtLName.setText(logUser.getlName());
		txtAddress.setText(logUser.getAddress());
		txtBankNo.setText(logUser.getBankAccNo());
		txtNIC.setText(logUser.getNICNo());
		txtContactNo.setText(logUser.getContactNo());
		txtEmail.setText(logUser.getEmail());
		txtRole.setText(logUser.getRole());
		txtRefRole.setText(logUser.getRefferance());
		
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtUserName.setEditable(false);
				txtFName.setEditable(false);
				txtLName.setEditable(false);
				txtAddress.setEditable(false);
				txtBankNo.setEditable(false);
				txtNIC.setEditable(false);
				txtContactNo.setEditable(false);
				txtEmail.setEditable(false);
				txtRole.setEditable(false);
				txtRefRole.setEditable(false);
				
			}
		});
		btnCancel.setBounds(346, 315, 89, 23);
		panel.add(btnCancel);
		
		txtImagePath = new JTextField();
		txtImagePath.setEditable(false);
		txtImagePath.setBounds(537, 115, 180, 20);
		panel.add(txtImagePath);
		txtImagePath.setColumns(10);
		
		lblNotify = new JLabel("...");
		lblNotify.setForeground(new Color(153, 0, 0));
		lblNotify.setBounds(51, 15, 46, 14);
		panel.add(lblNotify);
		
		
		//set Profile Picture
//		try {
//			InputStream in = logUser.getProPicture();
//			BufferedImage image = ImageIO.read(in);
//			//Image profilePicture = new ImageIcon(image).getImage();
//			lblImage.setIcon(new ImageIcon(image));
//		} catch (IOException e1) {
//			JOptionPane.showMessageDialog(null, e1);
//			e1.printStackTrace();
//		}
		try {

			
			String query = "SELECT * FROM user_main WHERE EID = '"+eid+"' ";
			PreparedStatement pst = connection.prepareStatement(query);
			//System.out.println(pst);
			ResultSet rs = pst.executeQuery();
		
			if (rs.next()) {
				
				byte[] imge = rs.getBytes("proPicture");
				
				if (imge != null) {
				ImageIcon image = new ImageIcon(imge);
				Image im = image.getImage();
				Image myImg = im.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
				ImageIcon newImage = new ImageIcon(myImg);
				//System.out.println(newImage);
				lblImage.setIcon(newImage);
				}
				
			}
			else {
				JOptionPane.showMessageDialog(null, "No Data");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
