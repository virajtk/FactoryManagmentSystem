package com.interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

//import Updates.UpdateSupplier;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import java.util.function.Supplier;

import java.awt.event.ActionEvent;
import java.awt.TextArea;
import java.awt.TextField;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JEditorPane;
import java.awt.Button;
import java.awt.Insets;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Panel;
import java.awt.Font;
import javax.swing.JScrollBar;
import java.awt.SystemColor;
import javax.swing.JRadioButton;
import java.awt.Checkbox;
import javax.swing.UIManager;
import javax.swing.JCheckBoxMenuItem;
import java.awt.Scrollbar;
import javax.swing.JTable;

import com.util.DbConnect;

import net.proteanit.sql.DbUtils;

import com.service.*;
import com.model.Supplier;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;


public class AddS extends JFrame {
	
	private static Connection connection;
	private PreparedStatement preStatement;
	addSServices supplierSe = new addSServices();
	Supplier smodel = new Supplier();
	
	

	private JPanel contentPane;
	private JTextField FirstName;
	private JTextField CompanyN;
	private JTextField Email;
	private JTextField MobileNo;
	private JTextField LastName;
	private JTextField SupplierID;
	private JTextField Search;
	private JTable table;
	private JComboBox cmbMaterial;
	private JTextArea Location;

	
	public void searchAndSort(String supID) {
		try {
			String qu = "SELECT * FROM unic.supplier where supplierID like '"+"%"+supID+"%"+"'";
			connection = DbConnect.getDBConnection();
			preStatement = connection.prepareStatement(qu);
			ResultSet supTable = preStatement.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(supTable));
		} catch (Exception e) {
			// TODO: handle exception
		
		}
	}
	
	
	
	public void tableShow() {
		try {
			String qu = "SELECT * FROM unic.supplier";
			connection = DbConnect.getDBConnection();
			preStatement = connection.prepareStatement(qu);
			ResultSet supTable = preStatement.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(supTable));
		} catch (Exception e) {
			// TODO: handle exception
		
		}
	}

	
	public boolean validateSupplierFields() {
		
		boolean validate1 = cmbMaterial.getSelectedIndex() == 1 || cmbMaterial.getSelectedIndex() == 2;
		boolean validate2 = SupplierID.getText().matches("^[A-Z0-9]*$") && SupplierID.getText().length() <= 5 ;
		boolean validate3 = FirstName.getText().matches("^[a-zA-Z]*$") && FirstName.getText().length() > 2 ;
		boolean validate4 = LastName.getText().matches("^[a-zA-Z]*$") && LastName.getText().length() > 2 ;
		boolean validate5 = CompanyN.getText().matches("^[a-zA-Z]*$") && CompanyN.getText().length() > 2 ;
		boolean validate6 = Email.getText().matches("^[a-zA-Z0-9]*$") &&  Email.getText().length() > 2 ;
		boolean validate7 = MobileNo.getText().matches("^[0-9]*$") && MobileNo.getText().length() == 10;
		boolean validate8 = Location.getText().matches("^[a-zA-Z0-9]*$") && Location.getText().length() > 2 ;
		
		if (validate1 && validate2 && validate3 && validate4 && validate5 && validate6 && validate7 && validate8) {
			
			return true;
		}else {
			return false;
			
		}
	}
	
	
	public void resetFields () {
		
		cmbMaterial.setSelectedItem(null);
		SupplierID.setText(null);
		FirstName.setText(null);
		LastName.setText(null);
		CompanyN.setText(null);
		Email.setText(null);
		MobileNo.setText(null);
		Location.setText(null);
		table.setModel(new DefaultTableModel());
		
	}
	
	
	
	/**
	 * Launch the application.
	 * @throws SQLException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		
		DbConnect db = new DbConnect();
		//db.getDBConnection();
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddS frame = new AddS();
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
	public AddS() {
		setTitle("Supplier");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 726, 664);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setForeground(new Color(218, 165, 32));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Search = new JTextField();
		Search.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (Search.getText().length() > 4) {
					searchAndSort(Search.getText());
				}
			}
		});
		Search.setBackground(new Color(224, 255, 255));
		Search.setBounds(540, 95, 103, 20);
		contentPane.add(Search);
		Search.setColumns(10);
		
		JLabel lblSearchSupplier = new JLabel("Search");
		lblSearchSupplier.setFont(new Font("Calibri Light", Font.BOLD, 14));
		lblSearchSupplier.setBounds(472, 94, 58, 20);
		contentPane.add(lblSearchSupplier);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(23, 431, 493, 162);
		contentPane.add(scrollPane_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane_1.setViewportView(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
	
					
					int rowNumber = table.getSelectedRow();
					
					     SupplierID.setText(table.getValueAt(rowNumber, 0).toString());
					    FirstName.setText(table.getValueAt(rowNumber, 1).toString());
					     LastName.setText(table.getValueAt(rowNumber, 2).toString());
					  Location.setText(table.getValueAt(rowNumber, 3).toString());
					        CompanyN.setText(table.getValueAt(rowNumber, 4).toString());
					Email.setText(table.getValueAt(rowNumber, 5).toString());
					 MobileNo.setText(table.getValueAt(rowNumber, 6).toString());
					 cmbMaterial.setSelectedItem(table.getValueAt(rowNumber, 7).toString());    
	
			}
		});
		scrollPane.setViewportView(table);
		table.setBackground(new Color(204, 255, 255));
		table.setForeground(new Color(0, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.textHighlight);
		panel.setBounds(0, 0, 710, 68);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblSupplier = new JLabel("Supplier");
		lblSupplier.setBounds(12, 13, 686, 42);
		lblSupplier.setHorizontalAlignment(SwingConstants.CENTER);
		lblSupplier.setForeground(new Color(255, 255, 255));
		panel.add(lblSupplier);
		lblSupplier.setFont(new Font("Showcard Gothic", Font.BOLD, 30));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 250, 205));
		panel_2.setBounds(0, 69, 710, 535);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		cmbMaterial = new JComboBox();
		cmbMaterial.setBounds(206, 263, 245, 24);
		panel_2.add(cmbMaterial);
		cmbMaterial.setFont(new Font("Calibri Light", Font.BOLD, 14));
		cmbMaterial.setBackground(new Color(204, 255, 255));
		cmbMaterial.setModel(new DefaultComboBoxModel(new String[] {"", "Cement", "Chips", "CoriDust", "Pigments", "Metal", "Water"}));
		cmbMaterial.setToolTipText("Raw Matirial Type");
		
		SupplierID = new JTextField();
		SupplierID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				searchAndSort(SupplierID.getText());
			}
		});
		SupplierID.setBounds(206, 32, 246, 20);
		panel_2.add(SupplierID);
		SupplierID.setBackground(new Color(224, 255, 255));
		SupplierID.setColumns(10);
		
		JLabel lblSupplierId = new JLabel("Supplier ID");
		lblSupplierId.setBounds(115, 35, 85, 14);
		panel_2.add(lblSupplierId);
		lblSupplierId.setFont(new Font("Calibri Light", Font.BOLD, 14));
		
		JLabel lblRawMatirialType = new JLabel("Raw Matirial Type ");
		lblRawMatirialType.setBounds(62, 260, 134, 30);
		panel_2.add(lblRawMatirialType);
		lblRawMatirialType.setFont(new Font("Calibri Light", Font.BOLD, 14));
		
		FirstName = new JTextField();
		FirstName.setBounds(206, 63, 245, 20);
		panel_2.add(FirstName);
		FirstName.setBackground(new Color(224, 255, 255));
		FirstName.setColumns(10);
		
		JLabel lblFullName = new JLabel("First Name ");
		lblFullName.setBounds(115, 63, 93, 20);
		panel_2.add(lblFullName);
		lblFullName.setFont(new Font("Calibri Light", Font.BOLD, 14));
		
		LastName = new JTextField();
		LastName.setBounds(207, 93, 245, 20);
		panel_2.add(LastName);
		LastName.setBackground(new Color(224, 255, 255));
		LastName.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(115, 99, 71, 14);
		panel_2.add(lblLastName);
		lblLastName.setFont(new Font("Calibri Light", Font.BOLD, 14));
		
		JLabel lblLocation = new JLabel("Location \r\n\r\n");
		lblLocation.setBounds(125, 128, 67, 14);
		panel_2.add(lblLocation);
		lblLocation.setFont(new Font("Calibri Light", Font.BOLD, 14));
		
		Location = new JTextArea();
		Location.setBounds(206, 123, 245, 36);
		panel_2.add(Location);
		Location.setBackground(new Color(224, 255, 255));
		Location.setForeground(UIManager.getColor("Button.foreground"));
		
		MobileNo = new JTextField();
		MobileNo.setBounds(207, 232, 245, 20);
		panel_2.add(MobileNo);
		MobileNo.setBackground(new Color(224, 255, 255));
		MobileNo.setColumns(10);
		
		JLabel lblMobileNo = new JLabel("Mobile No ");
		lblMobileNo.setBounds(115, 238, 96, 14);
		panel_2.add(lblMobileNo);
		lblMobileNo.setFont(new Font("Calibri Light", Font.BOLD, 14));
		
		Email = new JTextField();
		Email.setBounds(207, 201, 245, 20);
		panel_2.add(Email);
		Email.setBackground(new Color(224, 255, 255));
		Email.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email ");
		lblEmail.setBounds(144, 204, 42, 14);
		panel_2.add(lblEmail);
		lblEmail.setFont(new Font("Calibri Light", Font.BOLD, 14));
		
		CompanyN = new JTextField();
		CompanyN.setBounds(207, 170, 245, 20);
		panel_2.add(CompanyN);
		CompanyN.setBackground(new Color(224, 255, 255));
		CompanyN.setColumns(10);
		
		JLabel lblCompanyName = new JLabel("Company Name ");
		lblCompanyName.setBounds(88, 173, 112, 14);
		panel_2.add(lblCompanyName);
		lblCompanyName.setFont(new Font("Calibri Light", Font.BOLD, 14));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 710, 10);
		panel_2.add(panel_1);
		panel_1.setBackground(new Color(0, 0, 51));
		
		JButton btnDeleteSupplier = new JButton("Remove Supplier");
		btnDeleteSupplier.setBounds(518, 316, 148, 32);
		panel_2.add(btnDeleteSupplier);
		btnDeleteSupplier.setForeground(new Color(255, 255, 255));
		btnDeleteSupplier.setBackground(new Color(204, 0, 0));
		btnDeleteSupplier.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton btnAddsupplier = new JButton("Add ");
		btnAddsupplier.setBounds(88, 316, 148, 32);
		panel_2.add(btnAddsupplier);
		btnAddsupplier.setForeground(new Color(255, 255, 255));
		btnAddsupplier.setBackground(SystemColor.textHighlight);
		btnAddsupplier.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton btnUpdateSupplier = new JButton("Update Supplier");
		btnUpdateSupplier.setBounds(306, 316, 148, 32);
		panel_2.add(btnUpdateSupplier);
		btnUpdateSupplier.setForeground(new Color(255, 255, 255));
		btnUpdateSupplier.setBackground(new Color(210, 105, 30));
		btnUpdateSupplier.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton btnRefresh = new JButton("Cancel");
		btnRefresh.setBounds(549, 413, 130, 30);
		panel_2.add(btnRefresh);
		btnRefresh.setBackground(new Color(178, 34, 34));
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				resetFields ();
				//tableShow();
				
			}
		});
		btnRefresh.setForeground(new Color(255, 255, 255));
		btnRefresh.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnUpdateSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				smodel.setRawMaterialType(cmbMaterial.getSelectedItem().toString());
				smodel.setSupplierId(SupplierID.getText());
				smodel.setFirstName(FirstName.getText());
				smodel.setLastName(LastName.getText());
				smodel.setCompanyName(CompanyN.getText());
				smodel.setEmail(Email.getText());
				smodel.setMobileNo(MobileNo.getText());
				smodel.setLocation(Location.getText());
				
				supplierSe.updateSupplier(smodel);
				tableShow();
				
				
				
				resetFields();
			}
			
		});
		btnAddsupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (validateSupplierFields() == true) {
					
				
				
				smodel.setRawMaterialType(cmbMaterial.getSelectedItem().toString());
				smodel.setSupplierId(SupplierID.getText());
				smodel.setFirstName(FirstName.getText());
				smodel.setLastName(LastName.getText());
				smodel.setCompanyName(CompanyN.getText());
				smodel.setEmail(Email.getText());
				smodel.setMobileNo(MobileNo.getText());
				smodel.setLocation(Location.getText());
				
				supplierSe.addSupplier(smodel);
				tableShow();
				resetFields();
				}
				else {
					JOptionPane.showMessageDialog(null, "Please fill data correctly");
				}
			}

		});
		btnDeleteSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				smodel.setSupplierId(SupplierID.getText());
				
				supplierSe.removeSupplier(smodel);
				tableShow();
			
				resetFields();
			}
		});
		
		//tableShow();
	}
	
}
