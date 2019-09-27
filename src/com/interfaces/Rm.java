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
import javax.swing.table.DefaultTableModel;

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
import com.util.ID_Generator;

import net.proteanit.sql.DbUtils;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Rm extends JFrame {
	
	private static Connection connection;
	private PreparedStatement preStatement;
	addRmServices rmServices = new addRmServices();
	ID_Generator id_Generator = new ID_Generator();
	
	RmM rmM = new RmM();
	

	private JPanel contentPane;
	private JTextField RawMaterialID;
	private JTextField supplierName;
	private JTable table;
	private JComboBox materialType;
	private JLabel idLable,suNameLable,raw1;  
	
	
	private void tableSelectItemRamm() {
		
		int rowNumber = table.getSelectedRow();
		
		RawMaterialID.setText(table.getValueAt(rowNumber, 0).toString());
		supplierName.setText(table.getValueAt(rowNumber, 1).toString());

		materialType.setSelectedItem(table.getValueAt(rowNumber, 2).toString());
	}
		
		
	
	
	public void tableShow() {
		
		try {
			String qu = "SELECT * FROM unic.raw_material";
			connection = DbConnect.getDBConnection();
			preStatement = connection.prepareStatement(qu);
			ResultSet rawTable = preStatement.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rawTable));
		} catch (Exception e) {
			
		
		}
	}
	
	public boolean validateRawFields () {
		
		boolean  raw1= materialType.getSelectedIndex() == 1 || materialType.getSelectedIndex() == 2;
		boolean suNameLable = supplierName.getText().matches("^[A-Za-z]*$") && supplierName.getText().length() > 2 ;
		boolean idLable= RawMaterialID.getText().matches("^[A-Z0-9]*$") && RawMaterialID.getText().length() <= 5 ;
	
		
		if (raw1 && suNameLable && idLable) {
			
			return true;
			
		}else {
			return false;
		}
	}
	
	public void resetField() {
		
		RawMaterialID.setText(null);
		supplierName.setText(null);
		materialType.setSelectedIndex(0);
		
	}
	
	
	
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
	
	
	public void tableDisplay() {
		
		try {
			String selectRawMm = "select * from raw_material";
			connection = DbConnect.getDBConnection();
		preStatement = connection.prepareStatement(selectRawMm);
			ResultSet resultSet = preStatement.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultSet));
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

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
		lblNewLabel.setBounds(65, 40, 111, 19);
		lblNewLabel.setFont(new Font("Calibri Light", Font.BOLD, 14));
		panel_1.add(lblNewLabel);
		
		RawMaterialID = new JTextField();
		RawMaterialID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				if ( RawMaterialID.getText().matches("^[A-Z0-9]*$") && RawMaterialID.getText().length() <= 5) {
					
					idLable.setEnabled(false);
					idLable.setVisible(false);
					
				}
				else {
					idLable.setEnabled(true);
					idLable.setVisible(true);
					
				}
				
				
			}
		});
		RawMaterialID.setBounds(221, 33, 259, 30);
		RawMaterialID.setBackground(Color.WHITE);
		panel_1.add(RawMaterialID);
		RawMaterialID.setColumns(10);
		
		JLabel lblRawMaterialType = new JLabel("Raw Material Type");
		lblRawMaterialType.setBounds(53, 177, 123, 19);
		lblRawMaterialType.setFont(new Font("Calibri Light", Font.BOLD, 14));
		panel_1.add(lblRawMaterialType);
		
		materialType = new JComboBox();
		materialType.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				if (materialType.getSelectedIndex() == 1 || materialType.getSelectedIndex() == 2) {
					

					raw1.setEnabled(false);
					raw1.setVisible(false);
					
				}
				else {
					raw1.setEnabled(true);
					raw1.setVisible(true);
					
				}
				
				
			}
		});
		materialType.setBounds(221, 170, 259, 31);
		materialType.setModel(new DefaultComboBoxModel(new String[] {"Please select..", "Cement", "CoriDust", "Chips", "Pigments", "Metal", "Water"}));
		panel_1.add(materialType);
		
		JLabel lblSupplierName = new JLabel("Supplier Name");
		lblSupplierName.setBounds(79, 111, 111, 19);
		lblSupplierName.setFont(new Font("Calibri Light", Font.BOLD, 14));
		panel_1.add(lblSupplierName);
		
		supplierName = new JTextField();
		supplierName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				if ( supplierName.getText().matches("^[A-Za-z]*$") && supplierName.getText().length() > 2 ) {
					
					suNameLable.setEnabled(false);
					suNameLable.setVisible(false);
					
				}
				else {
					suNameLable.setEnabled(true);
					suNameLable.setVisible(true);
					
				}
				
				
				
			}
		});
		supplierName.setBounds(221, 104, 259, 30);
		panel_1.add(supplierName);
		supplierName.setColumns(10);
		
		JButton btnAddDetails = new JButton("Add Details");
		btnAddDetails.setBounds(554, 274, 135, 35);
		btnAddDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				rmM.setRawMaterialId(RawMaterialID.getText());	
				rmM.setSupplierName(supplierName.getText());
				rmM.setRawMaterialType(materialType.getSelectedItem().toString());
				
				rmServices.addRmM(rmM);
				tableDisplay();
				
			}
		});
		btnAddDetails.setBackground(SystemColor.textHighlight);
		btnAddDetails.setForeground(Color.WHITE);
		btnAddDetails.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_1.add(btnAddDetails);
		
		JButton btnUpdateDetails = new JButton("Update Details");
		btnUpdateDetails.setBounds(554, 343, 135, 35);
		btnUpdateDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
					
					rmM.setRawMaterialId(RawMaterialID.getText());	
					rmM.setSupplierName(supplierName.getText());
					rmM.setRawMaterialType(materialType.getSelectedItem().toString());
					
					rmServices.updateRmM(rmM);
					
					resetField();
					tableDisplay();
					
					
		}
		});
		btnUpdateDetails.setBackground(new Color(210, 105, 30));
		btnUpdateDetails.setForeground(Color.WHITE);
		btnUpdateDetails.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_1.add(btnUpdateDetails);
		
		JButton btnRemoveDetails = new JButton("Remove Details");
		btnRemoveDetails.setBounds(554, 410, 135, 35);
		btnRemoveDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				rmM.setRawMaterialId(RawMaterialID.getText());
				
				rmServices.removeRmM(rmM);
				tableDisplay();
				
			}
		});
		btnRemoveDetails.setBackground(new Color(178, 34, 34));
		btnRemoveDetails.setForeground(Color.WHITE);
		btnRemoveDetails.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_1.add(btnRemoveDetails);
		
		JButton resetBtn = new JButton("Cancel");
		resetBtn.setBounds(379, 207, 102, 30);
		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				resetField();
				
			}
		});
		resetBtn.setBackground(SystemColor.textHighlight);
		resetBtn.setForeground(Color.WHITE);
		resetBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_1.add(resetBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 242, 505, 212);
		panel_1.add(scrollPane);
		
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				tableSelectItemRamm() ;
			}
		});
		scrollPane.setViewportView(table);
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 0, 51));
		panel_2.setBounds(0, 73, 701, 10);
		contentPane.add(panel_2);
		
		RawMaterialID.setText(id_Generator.raw_Mat_ID(rmServices.getRawMatID()));
		
		idLable = new JLabel("Invalid raw ID");
		idLable.setEnabled(false);
		idLable.setForeground(Color.RED);
		idLable.setBounds(221, 64, 111, 16);
		panel_1.add(idLable);
		
		 suNameLable = new JLabel("Invalid supplier name");
		suNameLable.setForeground(Color.RED);
		suNameLable.setEnabled(false);
		suNameLable.setBounds(221, 141, 135, 16);
		panel_1.add(suNameLable);
		
		raw1 = new JLabel("please select type");
		raw1.setEnabled(false);
		raw1.setForeground(Color.RED);
		raw1.setBounds(215, 214, 141, 16);
		panel_1.add(raw1);
		
		JButton btnNewButton = new JButton("New ID");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				RawMaterialID.setText(id_Generator.raw_Mat_ID(rmServices.getRawMatID()));
					
				
				
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBackground(Color.BLUE);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(564, 36, 97, 25);
		panel_1.add(btnNewButton);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setBackground(SystemColor.textHighlight);
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				tableDisplay();
				
			}
		});
		btnRefresh.setForeground(Color.WHITE);
		btnRefresh.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRefresh.setBounds(564, 173, 97, 25);
		panel_1.add(btnRefresh);
		tableDisplay();
		//table.setModel(new DefaultTableModel());
		idLable.setVisible(false);
		suNameLable.setVisible(false);
		raw1.setVisible(false);
	}
	
	
	
}
