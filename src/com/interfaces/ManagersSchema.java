package com.interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.validation.Schema;

import com.model.SalarySchema;
//import com.mysql.cj.jdbc.StatementWrapper;
//import com.mysql.cj.xdevapi.Statement;
import com.mysql.jdbc.*;
//import com.mysql.jdbc.Statement;
import com.service.SalaryRecordServices;
import com.util.DbConnect;
import com.util.SchemaID_Generator;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

import java.sql.*;
import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class ManagersSchema extends JFrame {

	private JPanel contentPane;
	private JTextField txtSchemaId;
	private JTextField txtpp1;
	private JTextField txtpp2;
	private JTextField txtpp3;
	private JTextField txtot;
	private JTextField txtpp4;
	private JTextField txtSchemaID;
	private JTable table;
	private JTextField txtPP1;
	private JTextField txtPP2;
	private JTextField txtPP3;
	private JTextField txtPP4;
	private JTextField txtOT;
	private JTextField txtpp5;
	
	
	//Other Common variables
	
	private PreparedStatement preStatement ;
	
	private int warnin_message_button = JOptionPane.YES_NO_OPTION;
	private int warning_message_result;
	private int refreshValue;
	private int numOfProducts, userInputQuantity;
	
	private String role = "SUP";
	private String QuickProductSearchID;
	private String QuickProductSearchKeyLock;
	
	private static Connection connection ;
	private static Statement statement ;
	private static boolean textBoxFull = false;
	
	private JComboBox<String> cmbProductType;
	private SalarySchema salary;
	private SalaryRecordServices salaryRecordsServices;
	
	private JTextField txtPP5;
	private JComboBox cmbRole;
	private JTextField txtRole;
	private JLabel lblNotify;
	//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	
	
	

	/**
	 * Launch the application.
	 * @throws SQLException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	
	private void viewAllSalary() {
		try {
			String selectClient = "select * from salaryschema";
			connection = DbConnect.getDBConnection();
			preStatement = connection.prepareStatement(selectClient);
			ResultSet resultSet = preStatement.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultSet));
			
		} catch (Exception e) {
			
		}
	}
	
	
	private void textSetSalarySchema() {

		salary.setSchemaId(txtSchemaId.getText());
		if (txtpp1.getText().isEmpty() == false) {
			salary.setPeacesPrice1(Integer.parseInt(txtpp1.getText()));
		}
		else {
			salary.setPeacesPrice1(0);
		}
		if (txtpp2.getText().isEmpty() == false) {
		salary.setPeacesPrice2(Integer.parseInt(txtpp2.getText()));
		}
		else {
			salary.setPeacesPrice2(0);
		}
		if (txtpp3.getText().isEmpty() == false) {
		salary.setPeacesPrice3(Integer.parseInt(txtpp3.getText()));
		}
		else {
			salary.setPeacesPrice3(0);
		}
		if (txtpp4.getText().isEmpty() == false) {
		salary.setPeacesPrice4(Integer.parseInt(txtpp4.getText()));
		}
		else {
			salary.setPeacesPrice4(0);
		}
		if (txtpp5.getText().isEmpty() == false) {
		salary.setPeacesPrice5(Integer.parseInt(txtpp5.getText()));
		}
		else {
			salary.setPeacesPrice5(0);
		}
		if (txtot.getText().isEmpty() == false) {
		salary.setOtRate(Integer.parseInt(txtot.getText()));
		}
		else {
			salary.setOtRate(0);
		}
		salary.setRole(cmbRole.getSelectedItem().toString());
	}
	
	private void textSetSalarySchema2() {

		salary.setSchemaId(txtSchemaID.getText());
		if (txtPP1.getText().isEmpty() == false) {
			salary.setPeacesPrice1(Integer.parseInt(txtPP1.getText()));
		}
		else {
			salary.setPeacesPrice1(0);
		}
		if (txtPP2.getText().isEmpty() == false) {
		salary.setPeacesPrice2(Integer.parseInt(txtPP2.getText()));
		}
		else {
			salary.setPeacesPrice2(0);
		}
		if (txtPP3.getText().isEmpty() == false) {
		salary.setPeacesPrice3(Integer.parseInt(txtPP3.getText()));
		}
		else {
			salary.setPeacesPrice3(0);
		}
		if (txtPP4.getText().isEmpty() == false) {
		salary.setPeacesPrice4(Integer.parseInt(txtPP4.getText()));
		}
		else {
			salary.setPeacesPrice4(0);
		}
		if (txtPP5.getText().isEmpty() == false) {
		salary.setPeacesPrice5(Integer.parseInt(txtPP5.getText()));
		}
		else {
			salary.setPeacesPrice5(0);
		}
		if (txtOT.getText().isEmpty() == false) {
		salary.setOtRate(Integer.parseInt(txtOT.getText()));
		}
		else {
			salary.setOtRate(0);
		}
		salary.setRole(cmbRole.getSelectedItem().toString());
	}
	
	
	
	private void tableSelectItemSalary() {
		//JOptionPane.showMessageDialog(null, "Schema Only");
		int rowNumber = table.getSelectedRow();
			 txtSchemaID.setText(table.getValueAt(rowNumber, 0).toString());
		     txtPP1.setText(table.getValueAt(rowNumber, 1).toString());
		     txtPP2.setText(table.getValueAt(rowNumber, 2).toString());
		     txtPP3.setText(table.getValueAt(rowNumber, 3).toString());
		     txtPP4.setText(table.getValueAt(rowNumber, 4).toString());
		     txtPP5.setText(table.getValueAt(rowNumber, 5).toString());
		     txtOT.setText(table.getValueAt(rowNumber, 6).toString());
		     txtRole.setText(table.getValueAt(rowNumber, 7).toString());
	}
	
	private Boolean IsSchemaCheckEmpty() {
		return (txtSchemaID.getText().length()==0);
	}
	
	
	
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagersSchema frame = new ManagersSchema();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void resetFields () {
		txtot.setText(null);
		txtpp1.setText(null);
		txtpp2.setText(null);
		txtpp3.setText(null);
		txtpp4.setText(null);
		txtpp5.setText(null);
	}
	
	public void resetFields2() {
		txtOT.setText(null);
		txtPP1.setText(null);
		txtPP2.setText(null);
		txtPP3.setText(null);
		txtPP4.setText(null);
		txtPP5.setText(null);
		txtSchemaID.setText(null);
		txtRole.setText(null);
	}
	
	public void showRoles() {
		try {
			String selectRolesQuery = "SELECT distinct Role FROM unic.user_main;";
					connection = DbConnect.getDBConnection();
					preStatement = connection.prepareStatement(selectRolesQuery);
					ResultSet rs = preStatement.executeQuery();
					
			while (rs.next()) {
					cmbRole.addItem(rs.getString("Role"));
							
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

	
	
	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	
	
	
	
	public ManagersSchema() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
		setTitle("Salary Schema");
		setResizable(false);
		
		connection = DbConnect.getDBConnection();
		SchemaID_Generator generator = new SchemaID_Generator();
		salary = new SalarySchema();
		salaryRecordsServices = new SalaryRecordServices();
//		SalarySchema salary = new SalarySchema();
//		SalaryRecordServices salaryRecordsServices = new SalaryRecordServices();
		
	//	connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/unic", "root" , "07280614");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 951, 605);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(5, 90, 942, 481);
		contentPane.add(tabbedPane);
		
		JPanel addSchema = new JPanel();
		addSchema.setBackground(new Color(255, 250, 205));
		tabbedPane.addTab("Add Schema", null, addSchema, null);
		addSchema.setLayout(null);
		
		JLabel lblschemaId = new JLabel("Schema ID");
		lblschemaId.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblschemaId.setBounds(194, 30, 121, 30);
		addSchema.add(lblschemaId);
		
		txtSchemaId = new JTextField();
		txtSchemaId.setEditable(false);
		txtSchemaId.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtSchemaId.setColumns(10);
		txtSchemaId.setBounds(424, 34, 185, 29);
		addSchema.add(txtSchemaId);
		
		JLabel lblRole = new JLabel("Role");
		lblRole.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblRole.setBounds(273, 87, 88, 30);
		addSchema.add(lblRole);
		
		cmbRole = new JComboBox();
		cmbRole.setFont(new Font("Tahoma", Font.PLAIN, 18));
		//cmbRole.setModel(new DefaultComboBoxModel(new String[] {"Sales Manager", "Branch Manager", "Factory Manager", "Machine Supervisor", "Moled Supervisor", "Shifting Supervisor", "Store Supervisor", "Machine Workers", "Moled Workers", "Driver", "Cleaner", "Shifter"}));
		cmbRole.setBounds(373, 87, 192, 30);
		addSchema.add(cmbRole);
		
		JLabel lblOtRate = new JLabel("OT Rate");
		lblOtRate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOtRate.setBounds(194, 144, 163, 24);
		addSchema.add(lblOtRate);
		
		JLabel lblpp1 = new JLabel("Peace Price(0 - 400)");
		lblpp1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblpp1.setBounds(194, 179, 192, 24);
		addSchema.add(lblpp1);
		
		JLabel lblpp2 = new JLabel("Peace Price(401 - 600)");
		lblpp2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblpp2.setBounds(194, 214, 230, 24);
		addSchema.add(lblpp2);
		
		JLabel lblpp3 = new JLabel("Peace Price(601-1000)");
		lblpp3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblpp3.setBounds(194, 249, 214, 24);
		addSchema.add(lblpp3);
		
		txtpp1 = new JTextField();
		txtpp1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				String pp1 = txtpp1.getText();
				
				if ( !pp1.matches("[0-9 ,]+") ) {
					lblNotify.setText("Please Enter Valide Piece Price");
					txtpp1.setText(null);
					//txtLName.requestFocusInWindow();
				}
				else {
					lblNotify.setText(null);
				}
				
			}
		});
		txtpp1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpp1.setColumns(10);
		txtpp1.setBounds(424, 182, 185, 24);
		addSchema.add(txtpp1);
		
		txtpp2 = new JTextField();
		txtpp2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				String pp2 = txtpp2.getText();
				
				if ( !pp2.matches("[0-9 ,]+") ) {
					lblNotify.setText("Please Enter Valide Piece Price");
					txtpp2.setText(null);
					//txtLName.requestFocusInWindow();
				}
				else {
					lblNotify.setText(null);
				}
			}
		});
		txtpp2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpp2.setColumns(10);
		txtpp2.setBounds(424, 217, 185, 24);
		addSchema.add(txtpp2);
		
		txtpp3 = new JTextField();
		txtpp3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				String pp3 = txtpp3.getText();
				
				if ( !pp3.matches("[0-9 ,]+") ) {
					lblNotify.setText("Please Enter Valide Piece Price");
					txtpp3.setText(null);
					//txtLName.requestFocusInWindow();
				}
				else {
					lblNotify.setText(null);
				}
			}
		});
		txtpp3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpp3.setColumns(10);
		txtpp3.setBounds(424, 252, 185, 24);
		addSchema.add(txtpp3);
		
		JButton btnreset = new JButton("RESET");
		btnreset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				resetFields();
				
			}
		});
		btnreset.setBackground(SystemColor.textHighlight);
		btnreset.setForeground(SystemColor.text);
		btnreset.setFont(UIManager.getFont("Button.font"));
		btnreset.setBounds(111, 384, 140, 31);
		addSchema.add(btnreset);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.setForeground(SystemColor.text);
		btnExit.setBackground(new Color(178, 34, 34));
		btnExit.setFont(UIManager.getFont("Button.font"));
		btnExit.setBounds(577, 384, 140, 31);
		addSchema.add(btnExit);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.setForeground(SystemColor.text);
		btnAdd.setBackground(new Color(210, 105, 30));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshValue = 1;
				textSetSalarySchema();	
				salaryRecordsServices.addSchema(salary);
				
				resetFields ();
				viewAllSalary();
				
				}
		});
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAdd.setBounds(331, 384, 163, 31);
		addSchema.add(btnAdd);
		
		txtot = new JTextField();
		txtot.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				String ot = txtot.getText();
				
				if ( !ot.matches("[0-9 ,]+") ) {
					lblNotify.setText("Please Enter Valide OT Rate");
					txtot.setText(null);
					//txtLName.requestFocusInWindow();
				}
				else {
					lblNotify.setText(null);
				}
				
			}
		});
		txtot.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtot.setColumns(10);
		txtot.setBounds(424, 147, 185, 24);
		addSchema.add(txtot);
		
		JLabel lblpp4 = new JLabel("Peace Price(1001-1500)");
		lblpp4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblpp4.setBounds(194, 284, 230, 24);
		addSchema.add(lblpp4);
		
		txtpp4 = new JTextField();
		txtpp4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String pp4 = txtpp4.getText();
				
				if ( !pp4.matches("[0-9 ,]+") ) {
					lblNotify.setText("Please Enter Valide Piece Price");
					txtpp4.setText(null);
					//txtLName.requestFocusInWindow();
				}
				else {
					lblNotify.setText(null);
				}
			}
		});
		txtpp4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpp4.setColumns(10);
		txtpp4.setBounds(424, 287, 185, 24);
		addSchema.add(txtpp4);
		
		JLabel lbpp5 = new JLabel("Peace Price(1501-2000)");
		lbpp5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbpp5.setBounds(194, 319, 230, 24);
		addSchema.add(lbpp5);
		
		txtpp5 = new JTextField();
		txtpp5.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				String pp5 = txtpp5.getText();
				
				if ( !pp5.matches("[0-9 ,]+") ) {
					lblNotify.setText("Please Enter Valide Piece Price");
					txtpp5.setText(null);
					//txtLName.requestFocusInWindow();
				}
				else {
					lblNotify.setText(null);
				}
			}
		});
		txtpp5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpp5.setColumns(10);
		txtpp5.setBounds(424, 319, 185, 24);
		addSchema.add(txtpp5);
		
		
		
		JPanel editSchema = new JPanel();
		editSchema.setBackground(new Color(255, 250, 205));
		tabbedPane.addTab("Manage Schema", null, editSchema, null);
		editSchema.setLayout(null);
		
		JLabel label = new JLabel("Schema ID");
		label.setFont(new Font("Century", Font.BOLD, 20));
		label.setBounds(20, 40, 121, 30);
		editSchema.add(label);
		
		txtSchemaID = new JTextField();
		txtSchemaID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				try {
					
					
					String key = txtSchemaID.getText();
					
					String query = "SELECT *  FROM salaryschema WHERE schemaID LIKE '"+key+"%'";
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
		txtSchemaID.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtSchemaID.setColumns(10);
		txtSchemaID.setBounds(151, 40, 163, 29);
		editSchema.add(txtSchemaID);
		
		JLabel label_2 = new JLabel("Role");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_2.setBounds(20, 102, 108, 30);
		editSchema.add(label_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setBounds(446, 75, 468, 295);
		editSchema.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				tableSelectItemSalary();
				
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel label_3 = new JLabel("Peace Price(0 - 300)");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_3.setBounds(20, 196, 192, 24);
		editSchema.add(label_3);
		
		JLabel label_4 = new JLabel("Peace Price(401 - 600)");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_4.setBounds(20, 233, 230, 24);
		editSchema.add(label_4);
		
		JLabel label_5 = new JLabel("Peace Price(600-1000)");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_5.setBounds(20, 268, 214, 24);
		editSchema.add(label_5);
		
		JLabel label_6 = new JLabel("Peace Price(1000-1500)");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_6.setBounds(20, 303, 230, 24);
		editSchema.add(label_6);
		
		JLabel label_7 = new JLabel("OT Rate");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_7.setBounds(20, 163, 163, 24);
		editSchema.add(label_7);
		
		txtPP1 = new JTextField();
		txtPP1.setColumns(10);
		txtPP1.setBounds(271, 200, 163, 24);
		editSchema.add(txtPP1);
		
		txtPP2 = new JTextField();
		txtPP2.setColumns(10);
		txtPP2.setBounds(271, 235, 163, 24);
		editSchema.add(txtPP2);
		
		txtPP3 = new JTextField();
		txtPP3.setColumns(10);
		txtPP3.setBounds(271, 270, 163, 24);
		editSchema.add(txtPP3);
		
		txtPP4 = new JTextField();
		txtPP4.setColumns(10);
		txtPP4.setBounds(271, 305, 163, 24);
		editSchema.add(txtPP4);
		
		txtOT = new JTextField();
		txtOT.setColumns(10);
		txtOT.setBounds(271, 165, 163, 24);
		editSchema.add(txtOT);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBackground(new Color(210, 105, 30));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				refreshValue = 1;
				textSetSalarySchema2();	
				salaryRecordsServices.updateSchema(salary);
				
				viewAllSalary();
				
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnUpdate.setBounds(315, 397, 121, 31);
		editSchema.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBackground(new Color(178, 34, 34));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (IsSchemaCheckEmpty() == true) {
					JOptionPane.showMessageDialog(null, "Please Select Schema Details to Verify Before do This Process, Because This Process Can't be Undone..");
				} else {
					warning_message_result = JOptionPane.showConfirmDialog (null, "Do you want to remove this Schema..","Warning",warnin_message_button);
					if(warning_message_result == JOptionPane.YES_OPTION){
						salary.setSchemaId(txtSchemaID.getText());
						refreshValue = 4;
						salaryRecordsServices.removeSchema(salary.getSchemaId());
						viewAllSalary();
					}
					
				}
				
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDelete.setBounds(793, 398, 121, 31);
		editSchema.add(btnDelete);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				resetFields2();
				
			}
		});
		btnReset.setBackground(SystemColor.textHighlight);
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnReset.setBounds(160, 397, 121, 31);
		editSchema.add(btnReset);
		
		JButton button = new JButton("EXIT");
		button.setHorizontalAlignment(SwingConstants.LEADING);
		button.setFont(new Font("Modern No. 20", Font.BOLD, 20));
		button.setBounds(1013, 468, 113, 23);
		editSchema.add(button);
		
		JLabel lblpp5 = new JLabel("Peace Price(1000-1500)");
		lblpp5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblpp5.setBounds(20, 345, 230, 24);
		editSchema.add(lblpp5);
		
		txtPP5 = new JTextField();
		txtPP5.setColumns(10);
		txtPP5.setBounds(271, 346, 163, 24);
		editSchema.add(txtPP5);
		
		txtRole = new JTextField();
		txtRole.setEditable(false);
		txtRole.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtRole.setBounds(150, 102, 163, 28);
		editSchema.add(txtRole);
		txtRole.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setForeground(Color.WHITE);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBackground(new Color(30, 144, 255));
		panel.setBounds(0, 0, 947, 83);
		contentPane.add(panel);
		
		JLabel lblManageSalarySchema = new JLabel("Manage Salary Schema");
		lblManageSalarySchema.setHorizontalAlignment(SwingConstants.CENTER);
		lblManageSalarySchema.setForeground(Color.WHITE);
		lblManageSalarySchema.setFont(new Font("Showcard Gothic", Font.PLAIN, 30));
		lblManageSalarySchema.setBounds(10, 11, 925, 65);
		panel.add(lblManageSalarySchema);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 51));
		panel_1.setBounds(0, 82, 947, 10);
		contentPane.add(panel_1);
		
		showRoles();
		viewAllSalary();
		txtSchemaId.setText(generator.ID_Generator(salaryRecordsServices.getSchemaID()));
		
		lblNotify = new JLabel("");
		lblNotify.setForeground(new Color(139, 0, 0));
		lblNotify.setBounds(424, 5, 293, 16);
		addSchema.add(lblNotify);
		
		JButton btnDemo = new JButton("DEMO");
		btnDemo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				txtot.setText("0");
				txtpp1.setText("10");
				txtpp2.setText("12");
				txtpp3.setText("14");
				txtpp4.setText("16");
				txtpp5.setText("18");
				
				
			}
		});
		btnDemo.setForeground(new Color(178, 34, 34));
		btnDemo.setFont(new Font("! PEPSI !", Font.PLAIN, 13));
		btnDemo.setBounds(828, 13, 97, 25);
		addSchema.add(btnDemo);
	}
}
