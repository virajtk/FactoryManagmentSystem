package com.interfaces;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
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
		setBounds(100, 100, 1109, 667);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 222, 173));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTransportId = new JLabel("Transport ID");
		lblTransportId.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTransportId.setBounds(172, 125, 166, 22);
		contentPane.add(lblTransportId);
		
		JLabel lblOrderId = new JLabel("Order ID");
		lblOrderId.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblOrderId.setBounds(172, 179, 166, 22);
		contentPane.add(lblOrderId);
		
		JLabel lblVehicaleId = new JLabel("Vehicle ID");
		lblVehicaleId.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblVehicaleId.setBounds(172, 236, 166, 22);
		contentPane.add(lblVehicaleId);
		
		JLabel lblDriverId = new JLabel("Driver ID");
		lblDriverId.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDriverId.setBounds(172, 291, 166, 28);
		contentPane.add(lblDriverId);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDate.setBounds(172, 354, 166, 22);
		contentPane.add(lblDate);
		
		textTransportId = new JTextField();
		textTransportId.setFont(new Font("Tahoma", Font.BOLD, 18));
		textTransportId.setBounds(407, 121, 198, 34);
		contentPane.add(textTransportId);
		textTransportId.setColumns(10);
		
		JButton btnReset = new JButton("RESET");
		btnReset.setBackground(new Color(255, 255, 255));
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnReset.setBounds(190, 488, 107, 43);
		contentPane.add(btnReset);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				transportModel.setTransportID(textTransportId.getText());
				//transportModel.setOrderID(textOrderID.getText());
				//transportModel.setDriverID(textDriverID.getText());
				//transportModel.setVehicleID(textVehicle.getText());
				transportModel.setDate(dateFormat.format(dateF.getDate()).toString());
				
				
				
				transportService.addTransport(transportModel);
				
				//resetFields();
				
				
				
			}
		});
		btnAdd.setBackground(new Color(255, 255, 255));
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnAdd.setBounds(407, 488, 107, 43);
		contentPane.add(btnAdd);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Transports trans=new Transports();
				trans.setVisible(true);
				dispose();
			}
		});
		btnExit.setBackground(new Color(255, 255, 255));
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnExit.setBounds(615, 488, 107, 43);
		contentPane.add(btnExit);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 102, 204));
		panel.setBounds(0, 0, 1091, 81);
		contentPane.add(panel);
		
		JLabel lblAddTransport = new JLabel("Add Transport");
		lblAddTransport.setForeground(Color.WHITE);
		lblAddTransport.setFont(new Font("Showcard Gothic", Font.BOLD, 30));
		lblAddTransport.setBounds(383, 13, 279, 42);
		panel.add(lblAddTransport);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.BLACK);
		panel_1.setBounds(0, 86, 1091, 10);
		contentPane.add(panel_1);
		
		 dateF = new JDateChooser();
		dateF.setDateFormatString("yyyy-MM-dd");
		dateF.setBounds(407, 354, 198, 34);
		contentPane.add(dateF);
		
		textTransportId.setText(generator.transportID_Generator(transportService.transportID()));
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(407, 179, 198, 34);
		contentPane.add(comboBox);
		
		vehicleBox = new JComboBox();
		vehicleBox.setBounds(407, 238, 198, 34);
		contentPane.add(vehicleBox);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(407, 290, 198, 34);
		contentPane.add(comboBox_2);
		
		vehiclIdFill();
	}
}
