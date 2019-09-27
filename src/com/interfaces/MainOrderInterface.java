package com.interfaces;

import com.model.*;
import com.service.*;
import com.util.*;

import net.proteanit.sql.DbUtils;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;

import java.awt.Color;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import java.awt.SystemColor;

public class MainOrderInterface extends JFrame {


	private JPanel contentPane;
	private JTextField address;
	private JTextField emailAddress;
	private JTextField contactNumber;
	private JTextField nicNo;
	private JTextField companyName;
	private JTextField lastName;
	private JTextField firstName;
	private JTextField clientID;
	private JTextField txtOrderID;
	private JTextField productID;
	private JTextField quantity1;
	private JTextField supervicerID;
	private JTextField Location;
	
	private JButton placeOrderButton;
	private JButton ClearOrderButton;
	private JButton UpdateOrderButton;
	private JButton RemoveOrderButton;
	private JButton clientRemoveButton;
	private JButton clientUpdateButton;
	private JButton clientClearButton;
	
	private JTable table;

	private JComboBox<String> cmbProductType;
	private JComboBox<String> cmbSuperID;
	private JComboBox<String> cmpTransport;
	private JComboBox<String> cmbRemark;
	
	private JDateChooser dayOfNeed;
	private JDateChooser orderDate;
	private JDateChooser dayOfComplete;
	

	//Other Common variables
	
		private PreparedStatement preStatement ;
		
		private int warnin_message_button = JOptionPane.YES_NO_OPTION;
		private int warning_message_result;
		private int refreshValue = 0;
		private int newOrderValue = 0;
		private static int numOfProducts, userInputQuantity;
		
		private String role = "SUP";
		private String QuickProductSearchID;
		private String QuickProductSearchKeyLock;
		
		private static Connection connection ;
		private static Statement statement ;
		private static boolean isNewOrder = false;
		private static boolean textBoxFull = false;
		private static boolean processingRemoved = false;
		private static boolean orderRetriview = false;
		
		//Other Common variables
		
		
		//Object Declaration
		
		Client client = new Client();
		Order order = new Order();
		ClientRecordsServices clientRecordsServices = new ClientRecordsServices();
		OrderRecordsServices orderRecordsServices = new OrderRecordsServices();
		CommonServices commonServices = new CommonServices();
		ID_Generator id_Generator = new ID_Generator();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		private JTextField Colorinput;
		private JScrollPane scrollPane_1;
		private JTextPane txtpnUseThisFields_1;
		
		
		//Object Declaration

/*---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

		//Button SetEnable True
		
		public void set_client_button_enable() {
			clientUpdateButton.setEnabled(true);
			clientRemoveButton.setEnabled(true);	
		}
		
		public void set_order_button_enable() {
			placeOrderButton.setEnabled(true);
			UpdateOrderButton.setEnabled(true);
			RemoveOrderButton.setEnabled(true);
		}
		
		
		//Button SetEnable True
		
/*---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
		
		//Form Load Button Disable
		public void form_Load_Client_Disable() {
			clientUpdateButton.setEnabled(false);
			clientRemoveButton.setEnabled(false);			
		}
		
		public void form_Load_Order_Disable() {
			placeOrderButton.setEnabled(false);
			UpdateOrderButton.setEnabled(false);
			RemoveOrderButton.setEnabled(false);
		}
		//Form Load Button Disable
/*---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
		//Check Fields Empty Or Full
		public boolean isCustomerFieldsEmpty() {
			if(clientID.getText().toString().equals("") &
		    firstName.getText().toString().equals("") &
		     lastName.getText().toString().equals("") &
		  companyName.getText().toString().equals("None") &
		        nicNo.getText().toString().equals("") &
		contactNumber.getText().toString().equals("") &
		 emailAddress.getText().toString().equals("") &
		      address.getText().toString().equals("")) {
				
				return true;
				
			}else {
				return false;
			}
			
			
		}
		
		
		//Check Fields Empty Or Full		
/*---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
		
		//New Order Button DIsable
		public void NewOrder_Client_Disable() {
			clientUpdateButton.setEnabled(false);
			clientRemoveButton.setEnabled(false);
		}
		
		
		public void NewOrder_Order_Disable() {
			UpdateOrderButton.setEnabled(false);
			RemoveOrderButton.setEnabled(false);
			placeOrderButton.setEnabled(false);
			if (processingRemoved == true) {
				cmbRemark.addItem("Processing");
				cmbRemark.setSelectedItem("Processing");
				cmbRemark.setEnabled(false);
				processingRemoved = false;
			} else {
				cmbRemark.setSelectedItem("Processing");
				cmbRemark.setEnabled(false);
			}
		}
		
		//New Order Button DIsable
		
/*---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	
		
		//All Order Fields Disable
		
		public void orderDisable() {
			txtOrderID.setEditable(false);
			cmbProductType.setEnabled(false);
			orderDate.setEnabled(false);
			dayOfComplete.setEnabled(false);
			dayOfNeed.setEnabled(false);
			productID.setEditable(false);
			quantity1.setEditable(false);
			cmbSuperID.setEnabled(false);
			supervicerID.setEditable(false);
			cmpTransport.setEnabled(false);
			Colorinput.setEditable(false);
			Location.setEditable(false);
			cmbRemark.setEnabled(false);
			
		}
		//All Order Fields Disable
		
		
		//All Order Fields Enable
		
		public void orderEnableable() {
			cmbProductType.setEnabled(true);
			orderDate.setEnabled(true);
			dayOfComplete.setEnabled(true);
			dayOfNeed.setEnabled(true);
			quantity1.setEditable(true);
			cmbSuperID.setEnabled(true);
			cmpTransport.setEnabled(true);
			cmbRemark.setEnabled(true);
			Location.setEditable(true);

			if (processingRemoved == true) {
				cmbRemark.addItem("Processing");
				processingRemoved = false;
			}
			
		}
		
		//All Order Fields Enable
		
		
		//SetValues to the model Class
		
		private void textSetClient() {
			client.setClientId(clientID.getText());
			client.setFirstName(firstName.getText());
			client.setLastName(lastName.getText());
			client.setCompanyName(companyName.getText());
			client.setNicNo(nicNo.getText());
			client.setContactNo(contactNumber.getText());
			client.setEmailAddress(emailAddress.getText());
			client.setClientAddress(address.getText());
		}

		private void textSetOrder() {
			order.setOrderID(txtOrderID.getText());
			order.setProductType(productID.getText());
			order.setOrderDate(dateFormat.format(orderDate.getDate()));
			order.setDayOfNeed(dateFormat.format(dayOfNeed.getDate()));
			order.setDayOfComplete(dateFormat.format(dayOfComplete.getDate()));
			order.setQuantity(quantity1.getText());
			order.setSuperviserID(supervicerID.getText());
			order.setTransportType(cmpTransport.getSelectedItem().toString());
			order.setColor(Colorinput.getText());
			order.setLocation(Location.getText());
			order.setRemark(cmbRemark.getSelectedItem().toString());
		}
		
		//SetValues to the model Class
		
		
		//Client Field Validation
		public boolean validateClientFields() {
			
			boolean validate1 = clientID.getText().matches("^[a-zA-Z0-9]*$") && clientID.getText().length() == 5 ;
			boolean validate2 = firstName.getText().matches("^[a-zA-Z]*$") && firstName.getText().length() > 2;
			boolean validate3 = lastName.getText().matches("^[a-zA-Z]*$") && lastName.getText().length() > 2;
			boolean validate4 = companyName.getText().matches("^[a-zA-Z0-9]*$") && companyName.getText().length() >= 4;
			boolean validate5 = nicNo.getText().matches("^[V0-9]*$") && nicNo.getText().length() == 10;
			boolean validate6 = contactNumber.getText().matches("^[0-9]*$") && contactNumber.getText().length() == 10;
			boolean validate7 = emailAddress.getText().matches("^[1-9a-zA-Z@.]*$") && emailAddress.getText().length() > 5;
			boolean validate8 = address.getText().matches("^[0-9a-zA-Z/,.]*$") && address.getText().length() > 2;
			
			if (validate1  && validate2 && validate3 && validate4 && validate5 && validate6 && validate7 && validate8 ) {
				return true;
			} else {
				return false;
			}
		}
		
		
		public boolean validateOrderFields() {
			boolean validate1 = txtOrderID.getText().matches("^[O0-9]*$") && txtOrderID.getText().length() == 5 ;
			boolean validate2 = productID.getText().matches("^[PR0-9]*$") && productID.getText().length() == 6 && cmbProductType.getSelectedIndex() != 0 ;
			boolean validate3 = (orderDate.getDate() != null);
			boolean validate4 = (dayOfNeed.getDate() != null);
			boolean validate5 = (dayOfComplete.getDate() != null);
			boolean validate6 = quantity1.getText().matches("^[0-9]*$") && quantity1.getText().length() >=2 ;
			boolean validate7 = supervicerID.getText().matches("^[E0-9]*$") && supervicerID.getText().length() >=2 && (cmbSuperID.getSelectedIndex() != 0) ;
			boolean validate8 = cmpTransport.getSelectedItem().equals("Company") || cmpTransport.getSelectedItem().equals("Private");
			boolean validate9 = Colorinput.getText().length() > 2;
			boolean validate10 = Location.getText().matches("^[0-9A-Za-z,./-]*$") &&Location.getText().length() > 2;
			boolean validate11 = cmbRemark.getSelectedIndex() == 1 || cmbRemark.getSelectedIndex() == 2;

			
			if (validate1  && validate2 && validate3 && validate4 && validate5 && validate6 && validate7 && validate8 && validate9 && validate10 && validate11 ) {
				return true;
			} else {
				return false;
			}


		}
		
		//Client Field Validation
		public void produtTypeFill() {
			try {
				String selectProductName= "select distinct productName from unic.product";
				connection = DbConnect.getDBConnection();
				preStatement = connection.prepareStatement(selectProductName);
				ResultSet productSet = preStatement.executeQuery();
				
				while (productSet.next()) {
					cmbProductType.addItem(productSet.getString("productName"));

				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		

		public void productIDview(String nameProduct) {
			String proName = nameProduct;
			
			try {
				String OrderID_query = "select * From unic.product p Where productName = '"+proName+"'";
				connection = DbConnect.getDBConnection();
				preStatement = connection.prepareStatement(OrderID_query);
				ResultSet resultSet = preStatement.executeQuery();
				while (resultSet.next()) {
					
						QuickProductSearchID = (resultSet.getString("productID"));
						cmbProductType.setSelectedItem(resultSet.getString("productName"));
				
					productID.setText(resultSet.getString("productID"));
					Colorinput.setText(resultSet.getString("colour"));
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
					
		}
		
		public void superviceNameID(String role) {
			
			
			try {
				String selectSupervicer = "SELECT DISTINCT FName,LName FROM user_main WHERE Role like '"+"%"+role+"%"+"'";
				connection = DbConnect.getDBConnection();
				preStatement = connection.prepareStatement(selectSupervicer);
				ResultSet productSet = preStatement.executeQuery();
				
				while (productSet.next()) {
					cmbSuperID.addItem(productSet.getString("FName")+" "+productSet.getString("LName"));
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		
		private boolean viewAllSupervicers(String supervicerName) {
			String superName = supervicerName;
			
			try {
				String selectClient = "SELECT EID,CONCAT(FName,' ', LName) AS Full_Name,NICNo,Role FROM (SELECT EID,FName,LName,NICNo,Role, CONCAT(FName,' ', LName) AS fullName FROM unic.user_main) result WHERE result.fullName = '"+superName+"'";
				connection = DbConnect.getDBConnection();
				preStatement = connection.prepareStatement(selectClient);
				ResultSet resultSet = preStatement.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(resultSet));
				int cnt = table.getRowCount();
				if (cnt >= 2) {
					supervicerID.setText(null);
					JOptionPane.showMessageDialog(null, "select Correct Supervisor..");
					return true;
				} else {
					table.setModel(new DefaultTableModel());
					return false;
				}
				
			} catch (Exception e) {
				return false;
			}
		}
		
		
		private boolean viewAllProducts(String productName) {
			int cnt = 0;
			String nameProduct = productName;
			table.setModel(new DefaultTableModel());
			try {
				String selectClient = "select * from product where productName = '"+nameProduct+"'";
				connection = DbConnect.getDBConnection();
				preStatement = connection.prepareStatement(selectClient);
				ResultSet resultSet = preStatement.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(resultSet));
				
				cnt = table.getRowCount();
				
				if (cnt >= 2) {
					productID.setText(null);
					JOptionPane.showMessageDialog(null, "Select Correct Product..");
					return true;
				} else {
					
					table.setModel(new DefaultTableModel());
					cnt = 0;
					return false;
				}
				
			} catch (Exception e) {
				return false;
			}
		}
		
		
		
		
		
		
		
		private void tableSelectItemClient() {
			
			int rowNumber = table.getSelectedRow();
			     clientID.setText(table.getValueAt(rowNumber, 0).toString());
			    firstName.setText(table.getValueAt(rowNumber, 1).toString());
			     lastName.setText(table.getValueAt(rowNumber, 2).toString());
			  companyName.setText(table.getValueAt(rowNumber, 3).toString());
			        nicNo.setText(table.getValueAt(rowNumber, 4).toString());
			contactNumber.setText(table.getValueAt(rowNumber, 5).toString());
			 emailAddress.setText(table.getValueAt(rowNumber, 6).toString());
			      address.setText(table.getValueAt(rowNumber, 7).toString());
		}
		

		
		private void allOrderItems() throws ParseException {

			int rowNumber = table.getSelectedRow();
			   
			txtOrderID.setText(table.getValueAt(rowNumber, 0).toString());
			clientID.setText(table.getValueAt(rowNumber, 1).toString());
			productID.setText(table.getValueAt(rowNumber, 2).toString());
			orderDate.setDate(dateFormat.parse(table.getValueAt(rowNumber, 3).toString()));
			dayOfNeed.setDate(dateFormat.parse(table.getValueAt(rowNumber, 4).toString()));
			dayOfComplete.setDate(dateFormat.parse(table.getValueAt(rowNumber, 5).toString()));
			quantity1.setText(table.getValueAt(rowNumber, 6).toString());
			supervicerID.setText(table.getValueAt(rowNumber, 7).toString());
			cmpTransport.setSelectedItem(table.getValueAt(rowNumber, 8).toString());
			Location.setText(table.getValueAt(rowNumber, 9).toString());
			
			if (table.getValueAt(rowNumber, 10).toString().equals("Processing")) {
				cmbRemark.setEnabled(true);
				cmbRemark.removeItem("Processing");
				processingRemoved = true;
				cmbRemark.setEnabled(true);;
				cmbRemark.setSelectedIndex(0);
				placeOrderButton.setEnabled(false);
				UpdateOrderButton.setEnabled(true);
			} else {
				if (processingRemoved == true) {
					cmbRemark.addItem("Processing");
					processingRemoved = false;
				}
				cmbRemark.setSelectedItem("Complete");
				cmbRemark.setEnabled(false);
				placeOrderButton.setEnabled(false);
				UpdateOrderButton.setEnabled(false);
				
			}
			
			//Remark.setText(table.getValueAt(rowNumber, 10).toString());
			  
		}
		
		private void clearCustomerFields(){
			clientID.setText(null);
			firstName.setText(null);
			lastName.setText(null);	
			companyName.setText("None");	
			nicNo.setText(null);
			contactNumber.setText(null);				
			emailAddress.setText(null);
			address.setText(null);					
		}
		
		private void clearOrderFields() {
			txtOrderID.setText(null);
			txtOrderID.setEditable(true);
			cmbProductType.setSelectedIndex(0);
			orderDate.setDate(null);
			dayOfComplete.setDate(null);
			dayOfNeed.setDate(null);
			productID.setText(null);
			quantity1.setText(null);
			cmbSuperID.setSelectedIndex(0);
			supervicerID.setText(null);
			cmpTransport.setSelectedIndex(0);
			Colorinput.setText(null);
			Location.setText(null);
			cmbRemark.setSelectedIndex(0);
			
		}
		
		
		private void allProductItems() {
			int rowNumber = table.getSelectedRow();
			cmbProductType.setSelectedItem(table.getValueAt(rowNumber, 1).toString());
			productID.setText(table.getValueAt(rowNumber, 0).toString());
			Colorinput.setText(table.getValueAt(rowNumber, 4).toString());
			QuickProductSearchID = (table.getValueAt(rowNumber, 0).toString());

		}
		
		
		private void supervicerID() {
			int rowNumber = table.getSelectedRow();
			supervicerID.setText(table.getValueAt(rowNumber, 0).toString());
		
		}
		
		private Boolean IsClientCheckEmpty() {
			return ((clientID.getText().length()==0 || firstName.getText().length()==0) || lastName.getText().length()==0 );
		}
		
		private boolean IsOrderCheckEmpty() {
			return ((txtOrderID.getText().length() == 0 || productID.getText().length() == 0 ));
		}
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainOrderInterface frame = new MainOrderInterface();
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
	public MainOrderInterface() {
		setTitle("Orders");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(-3, 20, 1071, 972);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JLabel label_1 = new JLabel("Client ID");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_1.setBounds(23, 186, 86, 14);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("First Name");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_2.setBounds(23, 223, 86, 14);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("Last Name");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_3.setBounds(23, 263, 86, 14);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("Company Name");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_4.setBounds(23, 300, 130, 14);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("NIC No");
		label_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_5.setBounds(23, 339, 71, 14);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("Contact No");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_6.setBounds(23, 377, 94, 14);
		contentPane.add(label_6);
		
		JLabel label_7 = new JLabel("Email");
		label_7.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_7.setBounds(23, 415, 86, 14);
		contentPane.add(label_7);
		
		JLabel label_8 = new JLabel("Address");
		label_8.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_8.setBounds(23, 452, 86, 14);
		contentPane.add(label_8);
		
		JLabel label_12 = new JLabel("Product Type");
		label_12.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_12.setBounds(532, 223, 130, 14);
		contentPane.add(label_12);
		
		JLabel label_13 = new JLabel("Order Date");
		label_13.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_13.setBounds(532, 263, 86, 14);
		contentPane.add(label_13);
		
		JLabel label_14 = new JLabel("Day Of Need");
		label_14.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_14.setBounds(532, 339, 113, 14);
		contentPane.add(label_14);
	
		JLabel label_15 = new JLabel("Remark");
		label_15.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_15.setBounds(532, 564, 113, 14);
		contentPane.add(label_15);
		
		JLabel label_16 = new JLabel("Location");
		label_16.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_16.setBounds(532, 533, 113, 14);
		contentPane.add(label_16);
		
		JLabel lblProductColour = new JLabel("Product Colour");
		lblProductColour.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblProductColour.setBounds(532, 490, 130, 14);
		contentPane.add(lblProductColour);
		
		JLabel label_18 = new JLabel("Transport Type");
		label_18.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_18.setBounds(532, 452, 130, 14);
		contentPane.add(label_18);
		
		JLabel label_19 = new JLabel("Superviser ID");
		label_19.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_19.setBounds(532, 415, 113, 14);
		contentPane.add(label_19);
		
		JLabel label_20 = new JLabel("Quantity");
		label_20.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_20.setBounds(532, 377, 113, 14);
		contentPane.add(label_20);
		
		JLabel label_21 = new JLabel("Day Of Complete");
		label_21.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_21.setBounds(532, 300, 144, 14);
		contentPane.add(label_21);

		JLabel label_11 = new JLabel("Order ID");
		label_11.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_11.setBounds(532, 186, 86, 14);
		contentPane.add(label_11);
		
		
		
		
		JButton newOrderButton = new JButton("New Order");
		newOrderButton.setBackground(new Color(210, 105, 30));
		newOrderButton.setForeground(SystemColor.text);
		newOrderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				isNewOrder = true;
				
				clearCustomerFields();
				clearOrderFields();

				NewOrder_Order_Disable();
				NewOrder_Client_Disable();
				

				
				warning_message_result = JOptionPane.showConfirmDialog (null, "Already a member of UNIC..","Warning",warnin_message_button);
				
				if(warning_message_result == JOptionPane.YES_OPTION){
					if (processingRemoved == true) {
						cmbRemark.addItem("Processing");
						processingRemoved = false;
					}
					newOrderValue = 1;
					//orderDisable();
					JOptionPane.showMessageDialog(null, "Please Select that client from given list or search the client..");
					table.setModel(DbUtils.resultSetToTableModel(clientRecordsServices.viewAllClients()));
					txtOrderID.setText(ID_Generator.orderID_Generator(orderRecordsServices.getOrderID()));
					txtOrderID.setEditable(false);
					clientID.setEditable(false);
					refreshValue = 1;
					
					
				}else {
					if (processingRemoved == true) {
						cmbRemark.addItem("Processing");
						processingRemoved = false;
					}
					newOrderValue = 1;
					orderDisable();
					txtOrderID.setEditable(false);
					clientID.setText(ID_Generator.clientID_Generator(clientRecordsServices.getClientID()));
					txtOrderID.setText(ID_Generator.orderID_Generator(orderRecordsServices.getOrderID()));
					clientID.setEditable(false);
					table.setModel(new DefaultTableModel());
					clientUpdateButton.setEnabled(true);
					clientRemoveButton.setEnabled(true);
					
				}
				
			}
		});
		newOrderButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		newOrderButton.setBounds(22, 520, 420, 68);
		contentPane.add(newOrderButton);
	
		clientClearButton = new JButton("Cancel");
		clientClearButton.setForeground(SystemColor.text);
		clientClearButton.setBackground(new Color(178, 34, 34));
		clientClearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newOrderValue = 0;
				clearCustomerFields();
				if (isNewOrder == true) {
					clearOrderFields();
				}
				
				if (isCustomerFieldsEmpty() == true) {
					System.out.println(isCustomerFieldsEmpty());
					form_Load_Client_Disable();
					if (isNewOrder == true) {
						clearOrderFields();
						orderEnableable();
						isNewOrder =false;
					}
					
				}else {
					
					set_client_button_enable();
				}
				
					clientID.setEditable(true);
					
				
				if (orderRetriview == false) {
					table.setModel(new DefaultTableModel());
				}
				isNewOrder = false;
			}
		});
		clientClearButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		clientClearButton.setBounds(221, 602, 221, 40);
		contentPane.add(clientClearButton);
		
		clientUpdateButton = new JButton("Update Client");
		clientUpdateButton.setForeground(SystemColor.text);
		clientUpdateButton.setBackground(SystemColor.textHighlight);
		clientUpdateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean clientValidate = validateClientFields();
			
				if (clientValidate) {
					refreshValue = 1;
					textSetClient();
					clientRecordsServices.updateClient(client);
					table.setModel(DbUtils.resultSetToTableModel(clientRecordsServices.viewAllClients()));
					clearCustomerFields();
					form_Load_Client_Disable();
					form_Load_Order_Disable();
					table.setModel(new DefaultTableModel());
					clientID.setEditable(true);
				} else {
					JOptionPane.showMessageDialog(null, "Please Check  the Fields Again Carefully");
				}	
			}
		});
		clientUpdateButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		clientUpdateButton.setBounds(23, 651, 193, 40);
		contentPane.add(clientUpdateButton);
		
		clientRemoveButton = new JButton("Remove Client");
		clientRemoveButton.setForeground(SystemColor.text);
		clientRemoveButton.setBackground(new Color(178, 34, 34));
		clientRemoveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				if (IsClientCheckEmpty() != false) {
					JOptionPane.showMessageDialog(null, "Please Search Select your Client Details to Verify Before Done This Process, Because This Process Can't be Undone..");
				} else {
					warning_message_result = JOptionPane.showConfirmDialog (null, "Do you want to remove this Client..","Warning",warnin_message_button);
					if(warning_message_result == JOptionPane.YES_OPTION){

						clientRecordsServices.removeCLient(clientID.getText());
						table.setModel(DbUtils.resultSetToTableModel(clientRecordsServices.viewAllClients()));
						clearCustomerFields();
						form_Load_Client_Disable();
						form_Load_Order_Disable();
						table.setModel(new DefaultTableModel());
						
						clientID.setEditable(true);
					}
					
				}
				
			}
				
		});
		
		clientRemoveButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		clientRemoveButton.setBounds(223, 651, 219, 40);
		contentPane.add(clientRemoveButton);
		
		ClearOrderButton = new JButton("Cancel");
		ClearOrderButton.setForeground(SystemColor.text);
		ClearOrderButton.setBackground(new Color(178, 34, 34));
		ClearOrderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				placeOrderButton.setEnabled(true);
				RemoveOrderButton.setEnabled(true);
				UpdateOrderButton.setEnabled(true);
				if (newOrderValue == 0) {
					orderEnableable();
				}
				
				cmbRemark.setEnabled(true);
				clearOrderFields();
				if (processingRemoved == true) {
					cmbRemark.addItem("Processing");
				}
				//refreshValue = 5;
			}
		});
		ClearOrderButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		ClearOrderButton.setBounds(881, 606, 150, 41);
		contentPane.add(ClearOrderButton);
		
		RemoveOrderButton = new JButton("Remove Order");
		RemoveOrderButton.setForeground(SystemColor.text);
		RemoveOrderButton.setBackground(new Color(178, 34, 34));
		RemoveOrderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (IsOrderCheckEmpty() != false) {
					JOptionPane.showMessageDialog(null, "Please Search Select your Order Details to Verify Before Done This Process, Because This Process Can't be Undone..");
				} else {
					warning_message_result = JOptionPane.showConfirmDialog (null, "Do you want to remove this Order..","Warning",warnin_message_button);
					if(warning_message_result == JOptionPane.YES_OPTION){
						orderRetriview = true;
						orderRecordsServices.removeOrder(txtOrderID.getText());
						clearOrderFields();
						clearCustomerFields();
						form_Load_Client_Disable();
						form_Load_Order_Disable();
						table.setModel(DbUtils.resultSetToTableModel(orderRecordsServices.viewAllOrders()));
						
						if (processingRemoved == true) {
							cmbRemark.addItem("Processing");
							processingRemoved = false;
						}
						orderEnableable();
						
					}
					
				}
				
				
				
				
			}
		});
		RemoveOrderButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		RemoveOrderButton.setBounds(881, 654, 150, 40);
		contentPane.add(RemoveOrderButton);
		
		UpdateOrderButton = new JButton("Update Order");
		UpdateOrderButton.setForeground(SystemColor.text);
		UpdateOrderButton.setBackground(SystemColor.textHighlight);
		UpdateOrderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validateOrderFields()) {
					//refreshValue = 5;
					textSetClient();
					textSetOrder();
					orderRecordsServices.updateOrder(order, client);
					table.setModel(DbUtils.resultSetToTableModel(orderRecordsServices.viewAllOrders()));
					clearOrderFields();
					clearCustomerFields();
					form_Load_Client_Disable();
					form_Load_Order_Disable();
					if (processingRemoved == true) {
						cmbRemark.addItem("Processing");
						processingRemoved = false;
					}
				} else {
					JOptionPane.showMessageDialog(null, "Check Again, There Are Some Mistakes In your Order Fields");
				}

			}
		});
		UpdateOrderButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		UpdateOrderButton.setBounds(532, 654, 337, 40);
		contentPane.add(UpdateOrderButton);
		
		placeOrderButton = new JButton("Place Order");
		placeOrderButton.setForeground(SystemColor.text);
		placeOrderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isNewOrder = false;
				if (rootPaneCheckingEnabled) {
					if (validateClientFields()) {
						if (validateOrderFields()) {
							//if (numOfProducts > userInputQuantity) {
								numOfProducts = orderRecordsServices.chekAvailability(productID.getText());
								userInputQuantity = Integer.parseInt(quantity1.getText());
								//refreshValue = 5;
								textSetClient();
								textSetOrder();
								clientRecordsServices.addClient(client);
								orderRecordsServices.addOrder(order, client);
								orderRecordsServices.updateProductQuantity(productID.getText(), numOfProducts - userInputQuantity);
								table.setModel(DbUtils.resultSetToTableModel(orderRecordsServices.viewAllOrders()));
								clearCustomerFields();
								clearOrderFields();
								cmbRemark.setEnabled(true);
								UpdateOrderButton.setEnabled(true);
								RemoveOrderButton.setEnabled(true);
								clientRemoveButton.setEnabled(true);
								clientUpdateButton.setEnabled(true);
								clientID.setEditable(true);
								clearOrderFields();
								clearCustomerFields();
								form_Load_Client_Disable();
								form_Load_Order_Disable();
								refreshValue = 2;
								table.setModel(DbUtils.resultSetToTableModel(orderRecordsServices.viewAllOrders()));
								
							//} else {
								//JOptionPane.showMessageDialog(null,"Insuficent Amount To Proceed\nOnly Have "+numOfProducts+" Products Only");
							//}
							
							
						} else {
							JOptionPane.showMessageDialog(null, "Please Check Order Fields Again Carefully");
						}
						
					} else  {
						JOptionPane.showMessageDialog(null, "Please Check Client Fields Again Carefully");
					}
					
					
				} else {

				}
				

			}
		});
		placeOrderButton.setFont(new Font("Tahoma", Font.BOLD, 17));
		placeOrderButton.setBackground(new Color(210, 105, 30));
		placeOrderButton.setBounds(532, 606, 337, 40);
		contentPane.add(placeOrderButton);
		
		JButton button_11 = new JButton("All Orders");
		button_11.setForeground(SystemColor.text);
		button_11.setBackground(SystemColor.textHighlight);
		button_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshValue = 2;
				table.setModel(DbUtils.resultSetToTableModel(orderRecordsServices.viewAllOrders()));
				clearCustomerFields();
			}
		});
		button_11.setFont(new Font("Tahoma", Font.BOLD, 13));
		button_11.setBounds(880, 714, 151, 40);
		contentPane.add(button_11);
		
		JButton button_13 = new JButton("All Clients");
		button_13.setForeground(SystemColor.text);
		button_13.setBackground(SystemColor.textHighlight);
		button_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearOrderFields();
				refreshValue = 1;
				table.setModel(DbUtils.resultSetToTableModel(clientRecordsServices.viewAllClients()));
				
			}
		});
		button_13.setFont(new Font("Tahoma", Font.BOLD, 13));
		button_13.setBounds(881, 767, 150, 42);
		contentPane.add(button_13);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(23, 714, 846, 210);
		contentPane.add(scrollPane_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane_1.setViewportView(scrollPane);
		
		
		
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if (refreshValue == 1) {
					tableSelectItemClient();
					clientUpdateButton.setEnabled(true);
					clientRemoveButton.setEnabled(true);
					if (validateClientFields() == true) {
						orderEnableable();
						placeOrderButton.setEnabled(true);
					}else {
						orderDisable();
					}
					
				} else if (refreshValue == 2) {
					placeOrderButton.setEnabled(false);
					UpdateOrderButton.setEnabled(true);
					RemoveOrderButton.setEnabled(true);
					try {
						
						allOrderItems();
						orderRetriview = true;
						ResultSet productName = commonServices.searchAndSort("product", "productID", productID.getText().toString());
						ResultSet customerAll = commonServices.searchAndSort("customer", "clientID", clientID.getText().toString());
						ResultSet supervicerName = commonServices.searchAndSort("user_main", "EID", supervicerID.getText().toString());
						while (productName.next()) {
							cmbProductType.setSelectedItem(productName.getString("productName").toString());
							Colorinput.setText(productName.getString("colour").toString());
						}
						while (supervicerName.next()) {
							cmbSuperID.setSelectedItem(supervicerName.getString("FName").toString()+" "+supervicerName.getString("LName").toString());
							
						}
						
						while (customerAll.next()) {
							clientID.setText(customerAll.getString("clientID").toString());
						    firstName.setText(customerAll.getString("FName").toString());
						     lastName.setText(customerAll.getString("LName").toString());
						  companyName.setText(customerAll.getString("companyName").toString());
						        nicNo.setText(customerAll.getString("NICNo").toString());
						contactNumber.setText(customerAll.getString("ContactNo").toString());
						 emailAddress.setText(customerAll.getString("Email").toString());
						      address.setText(customerAll.getString("Address").toString());
							
						}
						orderRetriview = false;
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if (refreshValue == 3) {
					allProductItems();
				}else if (refreshValue == 4) {
					supervicerID();
				}
				
				
			}
		});
		
		
		
		
		
		
		
		address = new JTextField();
		address.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				boolean validate8 = address.getText().matches("^[0-9a-zA-Z/,.]*$");
				if (validate8 != true) {
					JOptionPane.showMessageDialog(null, "Special Charaters are not allowed except / , ");
					address.setText(null);
				}
				
				if (validateClientFields() == true) {
					orderEnableable();
					placeOrderButton.setEnabled(true);
				}else {
					if (newOrderValue == 1) {
						orderDisable();	
						placeOrderButton.setEnabled(false);
					}
				}
			}
		});
		address.setFont(new Font("Dialog", Font.PLAIN, 15));
		address.setColumns(10);
		address.setBounds(165, 445, 277, 30);
		contentPane.add(address);
		
		emailAddress = new JTextField();
		emailAddress.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				boolean validate7 = emailAddress.getText().matches("^[1-9a-zA-Z@.]*$");
				if (validate7 != true) {
					JOptionPane.showMessageDialog(null, "Special Charaters are not allowed except @");
					emailAddress.setText(null);
				}
				
				if (validateClientFields() == true) {
					orderEnableable();
					placeOrderButton.setEnabled(true);
				}else {
					if (newOrderValue == 1) {
						orderDisable();	
						placeOrderButton.setEnabled(false);
					}
				}
			}
		});
		emailAddress.setFont(new Font("Dialog", Font.PLAIN, 15));
		emailAddress.setColumns(10);
		emailAddress.setBounds(165, 408, 277, 30);
		contentPane.add(emailAddress);
		
		contactNumber = new JTextField();
		contactNumber.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				boolean validate6 = contactNumber.getText().matches("^[0-9]*$");
				if (validate6 != true) {
					JOptionPane.showMessageDialog(null, "Special Charaters are not allowed");
					contactNumber.setText(null);
				}
				
				if (validateClientFields() == true) {
					orderEnableable();
					placeOrderButton.setEnabled(true);
				}else {
					if (newOrderValue == 1) {
						orderDisable();	
						placeOrderButton.setEnabled(false);
					}
				}
			}
		});
		contactNumber.setFont(new Font("Dialog", Font.PLAIN, 15));
		contactNumber.setColumns(10);
		contactNumber.setBounds(165, 370, 277, 30);
		contentPane.add(contactNumber);
		
		nicNo = new JTextField();
		nicNo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				boolean validate5 = nicNo.getText().matches("^[V0-9]*$");
				if (validate5 != true) {
					JOptionPane.showMessageDialog(null, "Use Only UPPER CASE 'V'");
					nicNo.setText(null);
				}
				
				if (validateClientFields() == true) {
					orderEnableable();
					placeOrderButton.setEnabled(true);
				}else {
					if (newOrderValue == 1) {
						orderDisable();	
						placeOrderButton.setEnabled(false);
					}
				}
			}
		});
		nicNo.setFont(new Font("Dialog", Font.PLAIN, 15));
		nicNo.setColumns(10);
		nicNo.setBounds(165, 332, 277, 30);
		contentPane.add(nicNo);
		
		companyName = new JTextField();
		companyName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				boolean validate4 = companyName.getText().matches("^[a-zA-Z0-9]*$");
				if (validate4 != true) {
					JOptionPane.showMessageDialog(null, "Special Charaters are not allowed");
					companyName.setText("None");
				}
				
				if (validateClientFields() == true) {
					orderEnableable();
					placeOrderButton.setEnabled(true);
				}else {
					if (newOrderValue == 1) {
						orderDisable();	
						placeOrderButton.setEnabled(false);
					}
				}
			}
		});
		companyName.setFont(new Font("Dialog", Font.PLAIN, 15));
		companyName.setText("None");
		companyName.setColumns(10);
		companyName.setBounds(165, 295, 277, 30);
		contentPane.add(companyName);
		
		lastName = new JTextField();
		lastName.setFont(new Font("Dialog", Font.PLAIN, 15));
		lastName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				boolean validate3 = lastName.getText().matches("^[a-zA-Z]*$");
				if (validate3 != true) {
					JOptionPane.showMessageDialog(null, "Special Charaters are not allowed");
					lastName.setText(null);
				}
				
				if (validateClientFields() == true) {
					orderEnableable();
					placeOrderButton.setEnabled(true);
				}else {
					if (newOrderValue == 1) {
						orderDisable();	
						placeOrderButton.setEnabled(false);
					}
				}
				
				if (lastName.getText().equals("") != true) {
					
					if (clientID.getText().equals("") == true) {
						textBoxFull = false;
					}
					refreshValue = 1;
					table.setModel(DbUtils.resultSetToTableModel(commonServices.searchAndSort("customer","LName",lastName.getText())));
					textBoxFull = true;
				} else {
					if (textBoxFull != true) {
						table.setModel(new DefaultTableModel());
						textBoxFull = false;
					}
				}
				
			}
		});
		lastName.setColumns(10);
		lastName.setBounds(165, 256, 277, 30);
		contentPane.add(lastName);
		
		firstName = new JTextField();
		firstName.setFont(new Font("Dialog", Font.PLAIN, 15));
		firstName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				boolean validate2 = firstName.getText().matches("^[a-zA-Z]*$");
				if (validate2 != true) {
					JOptionPane.showMessageDialog(null, "Special Charaters are not allowed");
					firstName.setText(null);
				}
				
				if (validateClientFields() == true) {
					orderEnableable();
					placeOrderButton.setEnabled(true);
				}else {
					if (newOrderValue == 1) {
						orderDisable();	
						placeOrderButton.setEnabled(false);
					}
				}
				
				if (firstName.getText().equals("") != true) {
					
					if (clientID.getText().equals("") == true) {
						textBoxFull = false;
					}
					refreshValue = 1;
					table.setModel(DbUtils.resultSetToTableModel(commonServices.searchAndSort("customer","FName",firstName.getText())));
					textBoxFull = true;
				} else {
					if (textBoxFull != true) {
						table.setModel(new DefaultTableModel());
						textBoxFull = false;
					}
				}
				
			}
		});
		firstName.setColumns(10);
		firstName.setBounds(165, 216, 277, 30);
		contentPane.add(firstName);
		
		clientID = new JTextField();
		clientID.setFont(new Font("Dialog", Font.PLAIN, 15));
		clientID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (validateClientFields() == true) {
					orderEnableable();
					placeOrderButton.setEnabled(true);
				}else {
					if (newOrderValue == 1) {
						orderDisable();	
						placeOrderButton.setEnabled(false);
					}
					
				}
				
				boolean validate1 = clientID.getText().matches("^[C0-9]*$");
				String clientIdUserInput = clientID.getText();
				
				if (validate1 != true) {
					JOptionPane.showMessageDialog(null, "No Special Charcters, Only letter 'C' in UPERCASE.. \n'! @ # $ % ^ & * ( ) _ + : c '");
					clientID.setText(null);
					
				}else {
					if(clientIdUserInput.equals("") != true) {
						if (clientIdUserInput.equals("") == true) {
							textBoxFull = false;
						}
						refreshValue = 1;
						table.setModel(DbUtils.resultSetToTableModel(commonServices.searchAndSort("customer","clientID",clientIdUserInput)));
						textBoxFull = true;
					} else {
						if (textBoxFull != true) {
							table.setModel(new DefaultTableModel());
							textBoxFull = false;
						}
						
					}
				}
				
				
				
			}
		});
		clientID.setColumns(10);
		clientID.setBounds(165, 178, 277, 30);
		contentPane.add(clientID);
		
		txtOrderID = new JTextField();
		txtOrderID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtOrderID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String orderIdUserInput = txtOrderID.getText();
				
				boolean validate1 = txtOrderID.getText().matches("^[O0-9]*$") && txtOrderID.getText().length() <= 5;
				if (validate1) {
					if(orderIdUserInput.equals("") != true) {
						if (orderIdUserInput.equals("") == true) {
							textBoxFull = false;
						}
						cmbRemark.setEnabled(true);
						refreshValue = 2;
						table.setModel(DbUtils.resultSetToTableModel(commonServices.searchAndSort("order","orderID",orderIdUserInput)));
						textBoxFull = true;
					} else {
						if (textBoxFull != true) {
							table.setModel(new DefaultTableModel());
							textBoxFull = false;
						}
						
					}
				}else {
					table.setModel(new DefaultTableModel());
					txtOrderID.setText(null);
					JOptionPane.showMessageDialog(null, "Specail Characters and Any LOWERCASE Letters Are Not Valid\nUse This Correct Format 'Eg:-O0001' ");
					
				}
				
				
				
			}
		});
		txtOrderID.setColumns(10);
		txtOrderID.setBounds(729, 178, 305, 30);
		contentPane.add(txtOrderID);
	
		cmbProductType = new JComboBox();
		cmbProductType.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cmbProductType.addItem("Please Select");
		cmbProductType.setToolTipText("Select Product Name");
		cmbProductType.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				boolean orderIDNull;
				if (orderRetriview == false) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						if (cmbProductType.getSelectedItem().equals("Please Select")) {
							productID.setText(null);						
						}
						QuickProductSearchKeyLock = "lock";
						orderIDNull=viewAllProducts(e.getItem().toString());
						refreshValue = 3;
						if (orderIDNull == true) {
							productID.setText(null);
						}else {
							productIDview(e.getItem().toString());
						}
						numOfProducts = orderRecordsServices.chekAvailability(String.valueOf(productID.getText()));
				    }
				}	
				
			}
		});
		cmbProductType.setBounds(729, 216, 199, 30);
		contentPane.add(cmbProductType);
		
		productID = new JTextField();
		productID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		productID.setEditable(false);
		productID.setColumns(10);
		productID.setBounds(940, 216, 94, 30);
		contentPane.add(productID);
		
		orderDate = new JDateChooser();
		orderDate.setDateFormatString("yyyy-MM-dd");
		orderDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		orderDate.setBounds(730, 256, 304, 30);
		contentPane.add(orderDate);
		
		dayOfNeed = new JDateChooser();
		dayOfNeed.setDateFormatString("yyyy-MM-dd");
		dayOfNeed.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dayOfNeed.setBounds(729, 332, 305, 30);
		contentPane.add(dayOfNeed);
		
		dayOfComplete = new JDateChooser();
		dayOfComplete.setDateFormatString("yyyy-MM-dd");
		dayOfComplete.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dayOfComplete.setBounds(730, 295, 304, 30);
		contentPane.add(dayOfComplete);
		
		quantity1 = new JTextField();
		quantity1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		quantity1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				boolean validate6 = quantity1.getText().matches("^[0-9]*$");
				if (validate6) {
					
				} else {
					JOptionPane.showMessageDialog(null, "Use Only No of Products\nNo Special Charcters are allowed....!");
					quantity1.setText(null);
				}
				
				
			}
		});
		quantity1.setColumns(10);
		quantity1.setBounds(729, 370, 305, 30);
		contentPane.add(quantity1);
		
		cmbSuperID = new JComboBox();
		cmbSuperID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cmbSuperID.addItem("Please Select");
		cmbSuperID.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				boolean supervicerSetNull;
				String supervicerIDresult;
				if (orderRetriview == false) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					
						refreshValue = 4;
						supervicerSetNull = viewAllSupervicers(e.getItem().toString());
						
						if (supervicerSetNull == true) {
							supervicerID.setText(null); 
						} else {
							supervicerIDresult = orderRecordsServices.superviceIDView(e.getItem().toString());
							supervicerID.setText(supervicerIDresult);
						}
						 
				    }
				}
				
			}
		});
		cmbSuperID.setBounds(729, 408, 199, 30);
		contentPane.add(cmbSuperID);
		
		supervicerID = new JTextField();
		supervicerID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		supervicerID.setEditable(false);
		supervicerID.setColumns(10);
		supervicerID.setBounds(940, 408, 94, 30);
		contentPane.add(supervicerID);
		
		cmpTransport = new JComboBox();
		cmpTransport.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cmpTransport.addItem("Select Transport Type");
		cmpTransport.addItem("Company");
		cmpTransport.addItem("Private");
		cmpTransport.setBounds(729, 445, 305, 30);
		contentPane.add(cmpTransport);
		
		Location = new JTextField();
		Location.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Location.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				boolean validate10 = Location.getText().matches("^[0-9A-Za-z,./-]*$");
				
				if (validate10) {
					
				} else {
					JOptionPane.showMessageDialog(null, "Use Only Dilevery Location Address\nNo Special Charcters are allowed....!");
					Location.setText(null);
				}
				
			}
		});
		Location.setColumns(10);
		Location.setBounds(729, 520, 305, 30);
		contentPane.add(Location);
		
		Colorinput = new JTextField();
		Colorinput.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Colorinput.setEditable(false);
		Colorinput.setBounds(729, 483, 305, 30);
		contentPane.add(Colorinput);
		Colorinput.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.textHighlight);
		panel.setBounds(0, 0, 1065, 94);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblOrderManagement = new JLabel("ORDER MANAGEMENT");
		lblOrderManagement.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrderManagement.setForeground(Color.WHITE);
		lblOrderManagement.setFont(new Font("Showcard Gothic", Font.BOLD, 30));
		lblOrderManagement.setBounds(12, 13, 1041, 68);
		panel.add(lblOrderManagement);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 51));
		panel_1.setBounds(0, 95, 1065, 14);
		contentPane.add(panel_1);

		JTextPane txtpnUseThisFields = new JTextPane();
		txtpnUseThisFields.setEditable(false);
		txtpnUseThisFields.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnUseThisFields.setText("Use This Fields to Search Clients, Add Clients,\r\nUpdate Client Details, Remove Clients");
		txtpnUseThisFields.setBounds(82, 127, 304, 40);
		contentPane.add(txtpnUseThisFields);
		
		txtpnUseThisFields_1 = new JTextPane();
		txtpnUseThisFields_1.setText("Use This Fields to Search Orders, Add Orders, \r\nUpdate Order Details, Remove Orders");
		txtpnUseThisFields_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnUseThisFields_1.setBounds(637, 127, 344, 37);
		contentPane.add(txtpnUseThisFields_1);
		
		
		
		cmbRemark = new JComboBox();
		cmbRemark.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cmbRemark.addItem("Please Select");
		cmbRemark.addItem("Processing");
		cmbRemark.addItem("Complete");
		cmbRemark.setBounds(729, 557, 305, 30);
		contentPane.add(cmbRemark);
		
		JButton btnDemoUser = new JButton("Demo User");
		btnDemoUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				firstName.setText("Ruwan");
				lastName.setText("Gamage");	
				companyName.setText("None");	
				nicNo.setText("981591143V");
				contactNumber.setText("0716330481");				
				emailAddress.setText("wictd@gmail.com");
				address.setText("12/24,Colombo");
				
			}
		});
		btnDemoUser.setBounds(32, 610, 97, 25);
		contentPane.add(btnDemoUser);
		
		produtTypeFill();
		superviceNameID(role);
		
		form_Load_Client_Disable();
		form_Load_Order_Disable();
		
		clearCustomerFields();
		clearOrderFields();

	}
}
