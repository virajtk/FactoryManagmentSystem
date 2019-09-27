package com.interfaces;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JFormattedTextField;
import javax.swing.JToggleButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

import com.model.*;
import com.service.TransportService;
import com.service.VehicleService;
import com.toedter.calendar.JDateChooser;
import com.util.DbConnect;
import com.util.Generator;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

public class AddTransport extends JFrame {

	private JPanel contentPane;
	private JTextField textTransportId;
	private JComboBox vehicleBox;
	

	private JDateChooser dateF;
	
	private static PreparedStatement preStatement;
	private Connection connection;
	
	Generator generator = new Generator();
	
	TransportModel transportModel = new TransportModel();
	
	Connection con = null;
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private JComboBox orderBox;
	private JComboBox driverBox;
	
	private int warnin_message_button = JOptionPane.YES_NO_OPTION;
	private int warning_message_result;
	
	public void vehiclIdFill() {
		try {
			String selectVehicleId="select * from unic.vehicle";
			connection = DbConnect.getDBConnection();
			preStatement = connection.prepareStatement(selectVehicleId);
			ResultSet vehicleSet = preStatement.executeQuery();
			
			while(vehicleSet.next()) {
				vehicleBox.addItem(vehicleSet.getString("vehicleID"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void orderIdFill() {
		try {
			
			String selectOrderId= "select * from unic.order WHERE remarks = 'Processing'";
			connection = DbConnect.getDBConnection();
			preStatement = connection.prepareStatement(selectOrderId);
			ResultSet orderSet = preStatement.executeQuery();
			
			while(orderSet.next()) {
				orderBox.addItem(orderSet.getString("orderID"));
			}
			
		} catch (Exception e) {
			e.setStackTrace(null);
		}
	}
	public void driverIdFill() {
		try {
			
			String selectDriverId= "SELECT * FROM user_main WHERE Role='Driver'";
			connection = DbConnect.getDBConnection();
			preStatement = connection.prepareStatement(selectDriverId);
			ResultSet DriverSet = preStatement.executeQuery();
			
			while(DriverSet.next()) {
				driverBox.addItem(DriverSet.getString("EID"));
			}
			
		} catch (Exception e) {
			e.setStackTrace(null);
		}
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddTransport frame = new AddTransport();
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
	public AddTransport() {
		setResizable(false);
		
		
		
		TransportService transportService = new TransportService();
		
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 916, 595);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		
		JLabel lblTransportId = new JLabel("Transport ID");
		lblTransportId.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTransportId.setBounds(256, 138, 166, 22);
		contentPane.add(lblTransportId);
		
		JLabel lblOrderId = new JLabel("Order ID");
		lblOrderId.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblOrderId.setBounds(256, 192, 166, 22);
		contentPane.add(lblOrderId);
		
		JLabel lblVehicaleId = new JLabel("Vehicle ID");
		lblVehicaleId.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblVehicaleId.setBounds(256, 249, 166, 22);
		contentPane.add(lblVehicaleId);
		
		JLabel lblDriverId = new JLabel("Driver ID");
		lblDriverId.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDriverId.setBounds(256, 304, 166, 28);
		contentPane.add(lblDriverId);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDate.setBounds(256, 367, 166, 22);
		contentPane.add(lblDate);
		
		textTransportId = new JTextField();
		textTransportId.setEditable(false);
		textTransportId.setFont(new Font("Tahoma", Font.BOLD, 18));
		textTransportId.setBounds(434, 127, 198, 34);
		contentPane.add(textTransportId);
		textTransportId.setColumns(10);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.setForeground(Color.WHITE);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				transportModel.setTransportID(textTransportId.getText());
				transportModel.setOrderID(orderBox.getSelectedItem().toString());
				transportModel.setDriverID(driverBox.getSelectedItem().toString());
				transportModel.setVehicleID(vehicleBox.getSelectedItem().toString());
				transportModel.setDate(dateFormat.format(dateF.getDate()).toString());	
				
				transportService.addTransport(transportModel);
				
				//resetFields();
				
				textTransportId.setText(generator.transportID_Generator(transportService.transportID()));
				
				
			}
		});
		btnAdd.setBackground(new Color(255, 165, 0));
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnAdd.setBounds(397, 436, 127, 58);
		contentPane.add(btnAdd);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.setForeground(new Color(255, 255, 255));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					
				warning_message_result = JOptionPane.showConfirmDialog (null, "Do you want to exit","Warning",warnin_message_button);
				
				if(warning_message_result == JOptionPane.YES_OPTION){
					
				Transports trans=new Transports();
				trans.setVisible(true);
				dispose();
				}
			}
		});
		btnExit.setBackground(new Color(220, 20, 60));
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnExit.setBounds(583, 444, 107, 43);
		contentPane.add(btnExit);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(SystemColor.textHighlight);
		panel.setBounds(0, 0, 910, 81);
		contentPane.add(panel);
		
		JLabel lblAddTransport = new JLabel("Add Transport");
		lblAddTransport.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddTransport.setForeground(Color.WHITE);
		lblAddTransport.setFont(new Font("Showcard Gothic", Font.BOLD, 30));
		lblAddTransport.setBounds(12, 13, 884, 55);
		panel.add(lblAddTransport);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 51));
		panel_1.setBounds(0, 81, 910, 10);
		contentPane.add(panel_1);
		
		 dateF = new JDateChooser();
		 dateF.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 18));
		dateF.setDateFormatString("yyyy-MM-dd");
		dateF.setBounds(434, 360, 198, 34);
		contentPane.add(dateF);
		
		textTransportId.setText(generator.transportID_Generator(transportService.transportID()));
		
		orderBox = new JComboBox();
		orderBox.setToolTipText("Select");
		orderBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		orderBox.setBounds(434, 185, 198, 34);
		contentPane.add(orderBox);
		
		vehicleBox = new JComboBox();
		vehicleBox.setToolTipText("Select");
		vehicleBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		vehicleBox.setBounds(434, 244, 198, 34);
		contentPane.add(vehicleBox);
		
		driverBox = new JComboBox();
		driverBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		driverBox.setBounds(434, 296, 198, 34);
		contentPane.add(driverBox);
		
		vehiclIdFill();
		orderIdFill();
		driverIdFill();
		
	}
}
