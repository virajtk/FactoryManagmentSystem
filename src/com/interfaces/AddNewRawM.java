package com.interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

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
import java.text.ParseException;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;




public class AddNewRawM extends JFrame {

	addNewRawMServices addDetails = new addNewRawMServices();
	RawMaterial rawMate = new RawMaterial();
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	private static Connection connection ;
	private PreparedStatement preStatement ;
	private double pricePerOne,noOfPesic;
	private JPanel contentPane;
	private JTextField SupplierID;
	private JTextField Quantity;
	private JTextField PricePerUnit;
	private JTextField RawMaterialID;
	private JTable table;
	private JTextField TotalCost;
	private JDateChooser orderDate;
	private JDateChooser recieveData;

	public void tableUpdate() throws ParseException {
		int rowNumber = table.getSelectedRow();
		
	RawMaterialID.setText(table.getValueAt(rowNumber, 0).toString());
	SupplierID.setText(table.getValueAt(rowNumber, 1).toString()); 
	orderDate.setDate(simpleDateFormat.parse(table.getValueAt(rowNumber, 3).toString()));
	recieveData.setDate(simpleDateFormat.parse(table.getValueAt(rowNumber, 4).toString()));
	PricePerUnit.setText(table.getValueAt(rowNumber, 5).toString());
	Quantity.setText(table.getValueAt(rowNumber, 6).toString());
	 TotalCost.setText(table.getValueAt(rowNumber, 7).toString()); 
	}
	
	
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
	
	public void resetField() {
		
			
			RawMaterialID.setText(null);
			SupplierID.setText(null);
			orderDate.setDate(null);
			recieveData.setDate(null);
			PricePerUnit.setText(null);
			Quantity.setText(null);
			TotalCost.setText(null);
			table.setModel(new DefaultTableModel());
			
		}
	
	
	//type
	//public boolean validateSupplierFields() {
		
		//boolean sRid = RawMaterialID.getSelectedIndex() == 1 || rMaterial.getSelectedIndex() == 2;
		//boolean validate1 = sID.getText().matches("^[A-Z0-9]*$") && sID.getText().length() <= 5 ;
	//	boolean validate2= fName.getText().matches("^[a-zA-Z]*$") && fName.getText().length() > 2 ;
		//boolean validate3 = lName.getText().matches("^[a-zA-Z]*$") && lName.getText().length() > 2 ;
		//boolean validate4 = cName.getText().matches("^[a-zA-Z]*$") && cName.getText().length() > 2 ;
	//	boolean validate5 = eMail.getText().matches("^[@ . a-zA-Z0-9]*$") &&  eMail.getText().length() > 2 ;
		//boolean validate6 = mNo.getText().matches("^[0-9]*$") && mNo.getText().length() == 10;
		//boolean validate7 = locat.getText().matches("^[a-zA-Z0-9]*$") && locat.getText().length() > 2 ;
		
		//if (validate1 && validate2 && validate3 && validate4 && validate5 && validate6 && validate7 && validate8) {
			
			//return true;
	//	}
		//else {
		//	return false;
			
		//}
	//}
		
	
	
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
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 51));
		panel.setBounds(0, 54, 697, 14);
		contentPane.add(panel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 250, 205));
		panel_2.setBounds(0, 67, 697, 557);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		orderDate = new JDateChooser();
		orderDate.setBounds(253, 118, 242, 20);
		panel_2.add(orderDate);
		
		recieveData = new JDateChooser();
		recieveData.setBounds(253, 167, 242, 20);
		panel_2.add(recieveData);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setBounds(234, 334, 112, 28);
		panel_2.add(btnRefresh);
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tableShow();
			}
		});
		btnRefresh.setForeground(new Color(255, 255, 255));
		btnRefresh.setBackground(SystemColor.textHighlight);
		btnRefresh.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton rstBtn = new JButton("Cancel");
		rstBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				resetField();
			}
		});
		rstBtn.setBackground(Color.RED);
		rstBtn.setForeground(Color.WHITE);
		rstBtn.setBounds(383, 334, 112, 28);
		panel_2.add(rstBtn);
		
		JButton btnRemove = new JButton("Remove Details");
		btnRemove.setBounds(549, 479, 136, 36);
		panel_2.add(btnRemove);
		btnRemove.setForeground(new Color(255, 255, 255));
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				rawMate.setSupplierId(SupplierID.getText());	
				 
				addDetails.removeRawMaterial(rawMate);
				resetField();
				tableShow();
			}
		});
		btnRemove.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRemove.setBackground(new Color(178, 34, 34));
		
		JButton btnUpdate = new JButton("Update Details");
		btnUpdate.setBounds(549, 418, 136, 36);
		panel_2.add(btnUpdate);
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
				resetField();
				tableShow();
				
				
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton btnAddRawMaterial = new JButton("Add Details");
		btnAddRawMaterial.setBounds(549, 351, 136, 36);
		panel_2.add(btnAddRawMaterial);
		btnAddRawMaterial.setForeground(new Color(255, 255, 255));
		btnAddRawMaterial.setBackground(SystemColor.textHighlight);
		btnAddRawMaterial.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		RawMaterialID = new JTextField();
		RawMaterialID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				
				
				
				
				
			}
		});
		RawMaterialID.setBounds(255, 26, 240, 20);
		panel_2.add(RawMaterialID);
		RawMaterialID.setBackground(Color.WHITE);
		RawMaterialID.setColumns(10);
		
		JLabel lblRawMaterialId = new JLabel("Raw material ID");
		lblRawMaterialId.setBounds(110, 27, 120, 20);
		panel_2.add(lblRawMaterialId);
		lblRawMaterialId.setFont(new Font("Calibri Light", Font.BOLD, 14));
		
		SupplierID = new JTextField();
		SupplierID.setBounds(255, 70, 240, 20);
		panel_2.add(SupplierID);
		SupplierID.setBackground(Color.WHITE);
		SupplierID.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Supplier ID");
		lblQuantity.setBounds(146, 73, 97, 17);
		panel_2.add(lblQuantity);
		lblQuantity.setFont(new Font("Calibri Light", Font.BOLD, 14));
		
		JLabel lblOrderDate = new JLabel("Ordered Date ");
		lblOrderDate.setBounds(132, 118, 98, 14);
		panel_2.add(lblOrderDate);
		lblOrderDate.setFont(new Font("Calibri Light", Font.BOLD, 14));
		
		TotalCost = new JTextField();
		TotalCost.setBounds(253, 299, 242, 20);
		panel_2.add(TotalCost);
		TotalCost.setBackground(Color.WHITE);
		TotalCost.setColumns(10);
		
		Quantity = new JTextField();
		Quantity.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				noOfPesic = Double.parseDouble(Quantity.getText().toString());
				TotalCost.setText(String.valueOf(noOfPesic*pricePerOne));
				
			}
		});
		Quantity.setBounds(253, 256, 242, 17);
		panel_2.add(Quantity);
		Quantity.setBackground(Color.WHITE);
		Quantity.setColumns(10);
		
			
			PricePerUnit = new JTextField();
			PricePerUnit.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent arg0) {
					pricePerOne = Double.parseDouble(PricePerUnit.getText().toString());
				}
			});
			PricePerUnit.setBounds(253, 212, 242, 17);
			panel_2.add(PricePerUnit);
			PricePerUnit.setBackground(Color.WHITE);
			PricePerUnit.setColumns(10);
			
			JLabel lblTotalCost = new JLabel("Total Cost");
			lblTotalCost.setBounds(132, 303, 80, 14);
			panel_2.add(lblTotalCost);
			lblTotalCost.setFont(new Font("Calibri Light", Font.BOLD, 14));
			
			JLabel lblRs_1 = new JLabel("Rs:");
			lblRs_1.setBounds(224, 303, 33, 14);
			panel_2.add(lblRs_1);
			lblRs_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
			
			JLabel lblQuantity_1 = new JLabel("Quantity ");
			lblQuantity_1.setBounds(160, 259, 83, 14);
			panel_2.add(lblQuantity_1);
			lblQuantity_1.setFont(new Font("Calibri Light", Font.BOLD, 14));
			
			JLabel lblRs = new JLabel("Rs:");
			lblRs.setBounds(215, 215, 30, 14);
			panel_2.add(lblRs);
			lblRs.setFont(new Font("Calibri Light", Font.PLAIN, 14));
			
			JLabel lblSupplierName = new JLabel("Price per Unit ");
			lblSupplierName.setBounds(108, 212, 104, 20);
			panel_2.add(lblSupplierName);
			lblSupplierName.setFont(new Font("Calibri Light", Font.BOLD, 14));
			
			JLabel lblRecieveDate = new JLabel("Recieved Date ");
			lblRecieveDate.setBounds(130, 173, 111, 14);
			panel_2.add(lblRecieveDate);
			lblRecieveDate.setFont(new Font("Calibri Light", Font.BOLD, 14));
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(61, 375, 471, 169);
			panel_2.add(scrollPane);
			scrollPane.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
				}
			});
			
			table = new JTable();
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					
					
						try {
							tableUpdate();
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				
				}
			});
			scrollPane.setViewportView(table);
			
			JLabel sRid = new JLabel("invalid raw id");
			sRid.setVisible(false);
			sRid.setEnabled(false);
			sRid.setForeground(Color.RED);
			sRid.setBounds(253, 47, 112, 16);
			panel_2.add(sRid);
			
			JLabel sSid = new JLabel("invalid supplier ID");
			sSid.setForeground(Color.RED);
			sSid.setEnabled(false);
			sSid.setBounds(253, 88, 112, 16);
			panel_2.add(sSid);
			
			JLabel sOrder = new JLabel("please select order date");
			sOrder.setEnabled(false);
			sOrder.setForeground(Color.RED);
			sOrder.setBounds(253, 138, 154, 16);
			panel_2.add(sOrder);
			
			JLabel sRecieve = new JLabel("please select recieved date");
			sRecieve.setEnabled(false);
			sRecieve.setForeground(Color.RED);
			sRecieve.setBounds(251, 188, 156, 16);
			panel_2.add(sRecieve);
			
			JLabel sUnitPrice = new JLabel("invalid unit price");
			sUnitPrice.setEnabled(false);
			sUnitPrice.setForeground(Color.RED);
			sUnitPrice.setBounds(253, 227, 128, 16);
			panel_2.add(sUnitPrice);
			
			JLabel sQuantity = new JLabel("invalid quantity");
			sQuantity.setEnabled(false);
			sQuantity.setForeground(Color.RED);
			sQuantity.setBounds(253, 274, 112, 16);
			panel_2.add(sQuantity);
			
			JLabel sTotal = new JLabel("invalid total cost");
			sTotal.setEnabled(false);
			sTotal.setForeground(Color.RED);
			sTotal.setBounds(253, 319, 118, 16);
			panel_2.add(sTotal);
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
				
				RawMaterialID.setText(null); //change
		}
			
		});
	}
}
