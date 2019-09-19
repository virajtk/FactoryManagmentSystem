package com.interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.omg.CORBA.PRIVATE_MEMBER;

import javax.swing.JMenuBar;
import javax.swing.JLabel;
import java.awt.TextField;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Label;
import java.awt.Font;
import java.awt.Color;
//import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.model.RawMaterial;
import com.service.addNewRawMServices;
import com.util.DbConnect;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JDateChooser;
import javax.swing.JScrollPane;




public class AddNewRawM extends JFrame {

	addNewRawMServices addDetails = new addNewRawMServices();
	RawMaterial rawMate = new RawMaterial();
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	private static Connection connection ;
	private PreparedStatement preStatement ;
	
	private JPanel contentPane;
	private JTextField SupplierID;
	private JTextField Quantity;
	private JTextField PricePerUnit;
	private JTextField RawMaterialID;
	private JTable table;
	private JTextField Search;
	private JTextField TotalCost;
	private JDateChooser orderDate;
	private JDateChooser recieveData;

	
	public void tableShow() {
		try {
			String qu = "SELECT * FROM unic.supply";
			connection = DbConnect.getDBConnection();
			preStatement = connection.prepareStatement(qu);
			ResultSet suplyTable = preStatement.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(suplyTable));
		} catch (Exception e) {
			// TODO: handle exception
		
		}
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
					AddNewRawM frame = new AddNewRawM();
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
	public AddNewRawM() {
		setTitle("Supply");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 703, 663);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setForeground(new Color(255, 218, 185));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		SupplierID = new JTextField();
		SupplierID.setBackground(new Color(224, 255, 255));
		SupplierID.setBounds(253, 167, 176, 20);
		contentPane.add(SupplierID);
		SupplierID.setColumns(10);
		
		JLabel lblSupplierName = new JLabel("Price per Unit ");
		lblSupplierName.setFont(new Font("Calibri Light", Font.BOLD, 14));
		lblSupplierName.setBounds(111, 257, 104, 20);
		contentPane.add(lblSupplierName);
		
		JButton btnAddRawMaterial = new JButton("Add Details");
		btnAddRawMaterial.setForeground(new Color(255, 255, 255));
		btnAddRawMaterial.setBackground(SystemColor.textHighlight);
		btnAddRawMaterial.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAddRawMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				rawMate.setRawMaterialId(RawMaterialID.getText());
				rawMate.setSupplierId(SupplierID.getText());
				rawMate.setOrderedDate(simpleDateFormat.format(orderDate.getDate()));
				rawMate.setRecievedDate(simpleDateFormat.format(recieveData.getDate()));
				rawMate.setPricePerUnit(PricePerUnit.getText());
				rawMate.setQuantity(Quantity.getText());
				rawMate.setTotalCost(TotalCost.getText());
				
				addDetails.addNewRawMaterial(rawMate);
				tableShow();
				
				//RawMaterialID.setText(null);
		}
			
		});
		btnAddRawMaterial.setBounds(79, 372, 117, 36);
		contentPane.add(btnAddRawMaterial);
		
		JLabel lblQuantity = new JLabel("Supplier ID");
		lblQuantity.setFont(new Font("Calibri Light", Font.BOLD, 14));
		lblQuantity.setBounds(129, 169, 97, 17);
		contentPane.add(lblQuantity);
		
		JLabel lblQuantity_1 = new JLabel("Quantity ");
		lblQuantity_1.setFont(new Font("Calibri Light", Font.BOLD, 14));
		lblQuantity_1.setBounds(143, 294, 83, 14);
		contentPane.add(lblQuantity_1);
		
		Quantity = new JTextField();
		Quantity.setBackground(new Color(224, 255, 255));
		Quantity.setBounds(253, 291, 176, 17);
		contentPane.add(Quantity);
		Quantity.setColumns(10);
		
		JLabel lblOrderDate = new JLabel("Ordered Date ");
		lblOrderDate.setFont(new Font("Calibri Light", Font.BOLD, 14));
		lblOrderDate.setBounds(111, 201, 98, 14);
		contentPane.add(lblOrderDate);
		
		JLabel lblRecieveDate = new JLabel("Recieved Date ");
		lblRecieveDate.setFont(new Font("Calibri Light", Font.BOLD, 14));
		lblRecieveDate.setBounds(104, 232, 111, 14);
		contentPane.add(lblRecieveDate);
	
		
		PricePerUnit = new JTextField();
		PricePerUnit.setBackground(new Color(224, 255, 255));
		PricePerUnit.setBounds(253, 260, 176, 17);
		contentPane.add(PricePerUnit);
		PricePerUnit.setColumns(10);
		
		JLabel lblTotalCost = new JLabel("Total Cost");
		lblTotalCost.setFont(new Font("Calibri Light", Font.BOLD, 14));
		lblTotalCost.setBounds(135, 325, 80, 14);
		contentPane.add(lblTotalCost);
		
		RawMaterialID = new JTextField();
		RawMaterialID.setBackground(new Color(224, 255, 255));
		RawMaterialID.setBounds(253, 136, 176, 20);
		contentPane.add(RawMaterialID);
		RawMaterialID.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(60, 432, 471, 169);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tableShow();
			}
		});
		btnRefresh.setForeground(new Color(255, 255, 255));
		btnRefresh.setBackground(SystemColor.textHighlight);
		btnRefresh.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRefresh.setBounds(541, 474, 112, 36);
		contentPane.add(btnRefresh);
		
		JButton btnUpdate = new JButton("Update Details");
		btnUpdate.setForeground(new Color(255, 255, 255));
		btnUpdate.setBackground(new Color(210, 105, 30));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				rawMate.setRawMaterialId(RawMaterialID.getText());
				rawMate.setSupplierId(SupplierID.getText());
				rawMate.setOrderedDate(simpleDateFormat.format(orderDate.getDate()));
				rawMate.setRecievedDate(simpleDateFormat.format(recieveData.getDate()));
				rawMate.setPricePerUnit(PricePerUnit.getText());
				rawMate.setQuantity(Quantity.getText());
				rawMate.setTotalCost(TotalCost.getText());
				
				addDetails.updateRawMaterial(rawMate);
				tableShow();
				
				
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnUpdate.setBounds(221, 372, 136, 36);
		contentPane.add(btnUpdate);
		
		JButton btnRemove = new JButton("Remove Details");
		btnRemove.setForeground(new Color(255, 255, 255));
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				rawMate.setSupplierId(SupplierID.getText());	
				 
				addDetails.removeRawMaterial(rawMate);
				tableShow();
			}
		});
		btnRemove.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRemove.setBackground(new Color(178, 34, 34));
		btnRemove.setBounds(377, 372, 136, 36);
		contentPane.add(btnRemove);
		
	
		
		Search = new JTextField();
		Search.setBackground(new Color(224, 255, 255));
		Search.setBounds(546, 105, 117, 17);
		contentPane.add(Search);
		Search.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.textHighlight);
		panel_1.setToolTipText("xbcgh,hdfgdfh");
		panel_1.setBounds(0, 0, 697, 59);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblRawMaterial = new JLabel("Supply");
		lblRawMaterial.setHorizontalAlignment(SwingConstants.CENTER);
		lblRawMaterial.setBounds(12, 5, 673, 54);
		lblRawMaterial.setForeground(new Color(255, 255, 255));
		lblRawMaterial.setFont(new Font("Showcard Gothic", Font.BOLD, 30));
		panel_1.add(lblRawMaterial);
		
		TotalCost = new JTextField();
		TotalCost.setBackground(new Color(224, 255, 255));
		TotalCost.setBounds(253, 322, 176, 20);
		contentPane.add(TotalCost);
		TotalCost.setColumns(10);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setFont(new Font("Calibri Light", Font.BOLD, 14));
		lblSearch.setBounds(490, 108, 46, 14);
		contentPane.add(lblSearch);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 51));
		panel.setBounds(0, 54, 697, 14);
		contentPane.add(panel);
		
		JLabel lblRawMaterialId = new JLabel("Raw material ID");
		lblRawMaterialId.setFont(new Font("Calibri Light", Font.BOLD, 14));
		lblRawMaterialId.setBounds(95, 133, 120, 20);
		contentPane.add(lblRawMaterialId);
		
		JLabel lblRs = new JLabel("Rs:");
		lblRs.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		lblRs.setBounds(225, 263, 33, 14);
		contentPane.add(lblRs);
		
		JLabel lblRs_1 = new JLabel("Rs:");
		lblRs_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblRs_1.setBounds(225, 325, 33, 14);
		contentPane.add(lblRs_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 250, 205));
		panel_2.setBounds(0, 67, 697, 557);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		orderDate = new JDateChooser();
		orderDate.setBounds(255, 125, 174, 20);
		panel_2.add(orderDate);
		
		recieveData = new JDateChooser();
		recieveData.setBounds(253, 156, 176, 20);
		panel_2.add(recieveData);
	}
}
