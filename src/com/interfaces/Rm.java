package com.interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.management.remote.rmi.RMIJRMPServerImpl;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;

import com.service.addRmServices;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;


import com.model.RmM;
import com.service.*;
import com.util.DbConnect;

import net.proteanit.sql.DbUtils;
import java.awt.SystemColor;
import javax.swing.JScrollPane;

public class Rm extends JFrame {
	
	private static Connection connection;
	private PreparedStatement preStatement;
	addRmServices rmServices = new addRmServices();
	RmM rmM = new RmM();
	

	private JPanel contentPane;
	private JTextField RawMaterialID;
	private JTextField supplierName;
	private JTable table;
	private JTextField textField;
	private JComboBox materialType;
	
	public void tableShow() {
		
		try {
			String qu = "SELECT * FROM unic.raw_material";
			connection = DbConnect.getDBConnection();
			preStatement = connection.prepareStatement(qu);
			ResultSet rawTable = preStatement.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rawTable));
		} catch (Exception e) {
			// TODO: handle exception
		
		}
	}
	
	public void resetField() {
		
		RawMaterialID.setText(null);
		supplierName.setText(null);
		materialType.setSelectedItem(null);
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
		db.getDBConnection();
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Rm frame = new Rm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
//	public void tableDisplay() {
		
	//	try {
	//		String selectRawMm = "select * from raw_material";
	//		connection = DbConnect.getDBConnection();
	//		preStatement = connection.prepareStatement(selectRawMm);
	//		ResultSet resultSet = preStatement.executeQuery();
	//		table.setModel(DbUtils.resultSetToTableModel(resultSet));
			
	//	}catch (Exception e) {
			// TODO: handle exception
	//	}
		
		
//	}

	/**
	 * Create the frame.
	 */
	public Rm() {
		setTitle("Raw Material");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 717, 589);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.textHighlight);
		panel.setBounds(0, 0, 701, 72);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblRawMaterial = new JLabel("Raw Material");
		lblRawMaterial.setHorizontalAlignment(SwingConstants.CENTER);
		lblRawMaterial.setBounds(10, 11, 691, 50);
		lblRawMaterial.setForeground(Color.WHITE);
		lblRawMaterial.setFont(new Font("Showcard Gothic", Font.BOLD, 30));
		panel.add(lblRawMaterial);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 250, 205));
		panel_1.setBounds(0, 83, 701, 467);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(309, 64, -166, -59);
		panel_1.add(label);
		
		JLabel lblNewLabel = new JLabel("Raw Material ID");
		lblNewLabel.setBounds(42, 70, 111, 19);
		lblNewLabel.setFont(new Font("Calibri Light", Font.BOLD, 14));
		panel_1.add(lblNewLabel);
		
		RawMaterialID = new JTextField();
		RawMaterialID.setBounds(221, 64, 259, 30);
		RawMaterialID.setBackground(new Color(224, 255, 255));
		panel_1.add(RawMaterialID);
		RawMaterialID.setColumns(10);
		
		JLabel lblRawMaterialType = new JLabel("Raw Material Type");
		lblRawMaterialType.setBounds(30, 152, 123, 19);
		lblRawMaterialType.setFont(new Font("Calibri Light", Font.BOLD, 14));
		panel_1.add(lblRawMaterialType);
		
		JComboBox materialType = new JComboBox();
		materialType.setBounds(221, 146, 259, 31);
		materialType.setModel(new DefaultComboBoxModel(new String[] {"", "Cement", "CoriDust", "Chips", "Pigments", "Metal", "Water"}));
		panel_1.add(materialType);
		
		JLabel lblSupplierName = new JLabel("Supplier Name");
		lblSupplierName.setBounds(55, 111, 111, 19);
		lblSupplierName.setFont(new Font("Calibri Light", Font.BOLD, 14));
		panel_1.add(lblSupplierName);
		
		supplierName = new JTextField();
		supplierName.setBounds(221, 105, 259, 30);
		panel_1.add(supplierName);
		supplierName.setColumns(10);
		
		JButton btnAddDetails = new JButton("Add Details");
		btnAddDetails.setBounds(203, 229, 135, 35);
		btnAddDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				rmM.setRawMaterialId(RawMaterialID.getText());	
				rmM.setSupplierName(supplierName.getText());
				rmM.setRawMaterialType(materialType.getSelectedItem().toString());
				
				rmServices.addRmM(rmM);
				tableShow();
				
			}
		});
		btnAddDetails.setBackground(SystemColor.textHighlight);
		btnAddDetails.setForeground(Color.WHITE);
		btnAddDetails.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_1.add(btnAddDetails);
		
		JButton btnUpdateDetails = new JButton("Update Details");
		btnUpdateDetails.setBounds(359, 229, 135, 35);
		btnUpdateDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			//	if (clicked != true) {
					//JOptionPane.showMessageDialog(null, "Please select the record");
					
			//	} else {
					
					rmM.setRawMaterialId(RawMaterialID.getText());	
					rmM.setSupplierName(supplierName.getText());
					rmM.setRawMaterialType(materialType.getSelectedItem().toString());
					
					rmServices.addRmM(rmM);
					tableShow();
					
				//}
				
				
				
				
				
				
			}
		});
		btnUpdateDetails.setBackground(new Color(210, 105, 30));
		btnUpdateDetails.setForeground(Color.WHITE);
		btnUpdateDetails.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_1.add(btnUpdateDetails);
		
		JButton btnRemoveDetails = new JButton("Remove Details");
		btnRemoveDetails.setBounds(526, 229, 135, 35);
		btnRemoveDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				rmM.setRawMaterialId(RawMaterialID.getText());
				
				rmServices.removeRmM(rmM);
				tableShow();
				
			}
		});
		btnRemoveDetails.setBackground(new Color(178, 34, 34));
		btnRemoveDetails.setForeground(Color.WHITE);
		btnRemoveDetails.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_1.add(btnRemoveDetails);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setBounds(559, 302, 102, 30);
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				tableShow();
			}
		});
		btnRefresh.setBackground(SystemColor.textHighlight);
		btnRefresh.setForeground(Color.WHITE);
		btnRefresh.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_1.add(btnRefresh);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 275, 505, 170);
		panel_1.add(scrollPane);
		
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		textField = new JTextField();
		textField.setBounds(506, 28, 111, 19);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setBounds(627, 31, 46, 14);
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_1.add(lblSearch);
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 0, 51));
		panel_2.setBounds(0, 73, 701, 10);
		contentPane.add(panel_2);
		
		
		tableShow();
	}
}
