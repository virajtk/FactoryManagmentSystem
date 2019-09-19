package com.interfaces;
import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.omg.CORBA.INITIALIZE;

//import com.mysql.cj.xdevapi.Result;
import com.util.DbConnect;
import com.util.Generator;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;


import com.service.*;
import com.model.*;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class AddVehicle extends JFrame {

	
	
	
	
	//PreparedStatement pst = null;
	
	private JPanel contentPane;
	private JTextField textname;
	private JTextField textmodel;
	private JTextField textweight;
	private JTextField textregnum;
	private JTextField textID;
	private JTextField texttype;
	
	Generator generator = new Generator();

	private int warnin_message_button = JOptionPane.YES_NO_OPTION;
	private int warning_message_result;
	
	public void setVehicle() {
		
	}
	public boolean validateVehicleFields() {
		
		boolean validate1 = textID.getText().matches("^[A-Z0-9]*$") && textID.getText().length() <= 5 ;
		boolean validate2 = textname.getText().matches("^[a-zA-Z]*$") && textname.getText().length() > 2;
		boolean validate3 = textmodel.getText().matches("^[a-zA-Z0-9]*$") && textmodel.getText().length() > 2;
		boolean validate4 = texttype.getText().matches("^[a-zA-Z0-9]*$") && texttype.getText().length() < 10;
		boolean validate5 = textweight.getText().matches("^[0-9]*$") && textweight.getText().length() > 1;
		boolean validate6 = textregnum.getText().matches("^[A-Z0-9]*$") && textregnum.getText().length() <= 7;
		
		
		if (validate1  && validate2 && validate3 && validate4 && validate5 && validate6) {
			return true;
		} else {
			return false;
		}
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddVehicle frame = new AddVehicle();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public void resetFields () {
		
		textmodel.setText(null);
		textname.setText(null);
		textregnum.setText(null);
		texttype.setText(null);
		textweight.setText(null);
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	
	
	public AddVehicle() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		setResizable(false);
		
		VehicleModel newVehicle = new VehicleModel();
		
		VehicleService vehicleService = new VehicleService();
		
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1106, 685);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(new Color(255, 222, 173));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Vehicle Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(137, 168, 188, 16);
		contentPane.add(lblNewLabel);
		
		textname = new JTextField();
		textname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				boolean validate2 = textname.getText().matches("^[a-zA-Z]*$") ;
				
				if (validate2) {
					
				} else {
					
					JOptionPane.showMessageDialog(null, "Can't use numbers and special characters");
				}
			}
		});
		textname.setFont(new Font("Tahoma", Font.BOLD, 18));
		textname.setBounds(420, 160, 176, 32);
		contentPane.add(textname);
		textname.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Vehicle Type");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(137, 274, 137, 26);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("RESET");
		btnNewButton.setBackground(new Color(100, 149, 237));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				warning_message_result = JOptionPane.showConfirmDialog (null, "Do you want to reset","Warning",warnin_message_button);
				
				if(warning_message_result == JOptionPane.YES_OPTION){
					
				resetFields();
				
				}
			}
		});
		btnNewButton.setBounds(164, 495, 125, 46);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("ADD");
		btnNewButton_1.setBackground(new Color(255, 215, 0));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (validateVehicleFields() == true) {
					
					newVehicle.setVehicleID(textID.getText());
					
					newVehicle.setVehicleName(textname.getText());
					newVehicle.setModelName(textmodel.getText());
					newVehicle.setVehicleType(texttype.getText());
					newVehicle.setVehicleCapacity(textweight.getText());
					newVehicle.setRegistrationNumber(textregnum.getText());
					
					vehicleService.addVehicle(newVehicle);
					
					resetFields();
					textID.setText(generator.vehicleID_Generator(vehicleService.vehicleID()));
					
				} else {
					
						JOptionPane.showMessageDialog(null, "Check the fields");
				}
				
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_1.setBounds(353, 495, 125, 46);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("EXIT");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				warning_message_result = JOptionPane.showConfirmDialog (null, "Do you want to exit","Warning",warnin_message_button);
				
				if(warning_message_result == JOptionPane.YES_OPTION){
					
				Vehical vehi=new Vehical();
				vehi.setVisible(true);
				dispose();
				
				}
			}
		});
		btnNewButton_2.setBackground(new Color(255, 99, 71));
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_2.setBounds(558, 495, 116, 46);
		contentPane.add(btnNewButton_2);
		
		JLabel lblModelNumber = new JLabel("Model");
		lblModelNumber.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblModelNumber.setBounds(137, 222, 137, 26);
		contentPane.add(lblModelNumber);
		
		JLabel lblVehicleWeight = new JLabel("Vehicle Capacity ");
		lblVehicleWeight.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblVehicleWeight.setBounds(137, 327, 235, 26);
		contentPane.add(lblVehicleWeight);
		
		JLabel lblRegistationNumber = new JLabel("Registation Number");
		lblRegistationNumber.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblRegistationNumber.setBounds(137, 387, 212, 16);
		contentPane.add(lblRegistationNumber);
		
		textmodel = new JTextField();
		textmodel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				boolean validate3 = textmodel.getText().matches("^[a-zA-Z0-9]*$");
				
				if (validate3) {
					
				} else {
					
					JOptionPane.showMessageDialog(null, "Can't use special characters");
					
				}
				
			}
		});
		
		textmodel.setFont(new Font("Tahoma", Font.BOLD, 18));
		textmodel.setBounds(420, 219, 176, 32);
		contentPane.add(textmodel);
		textmodel.setColumns(10);
		
		textweight = new JTextField();
		textweight.setToolTipText("hint:-tonnes");
		textweight.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				boolean validate5 = textweight.getText().matches("^[0-9]*$");
				
				if (validate5) {
					
				} else {
					
					JOptionPane.showMessageDialog(null, "Can't use characters");
					
				}
			}
		});
		
		textweight.setFont(new Font("Tahoma", Font.BOLD, 18));
		textweight.setBounds(420, 324, 176, 32);
		contentPane.add(textweight);
		textweight.setColumns(10);
		
		textregnum = new JTextField();
		textregnum.setToolTipText("hint:-LL*****");
		textregnum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				boolean validate6 = textregnum.getText().matches("^[A-Z0-9]*$");
				
				if (validate6) {
					
				} else {
					
					JOptionPane.showMessageDialog(null, "Can't use simple latters and special characters");
					
				}
				
			}
			
		});
		textregnum.setFont(new Font("Tahoma", Font.BOLD, 18));
		textregnum.setBounds(420, 379, 176, 32);
		contentPane.add(textregnum);
		textregnum.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Vehicle ID");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setBounds(137, 111, 167, 22);
		contentPane.add(lblNewLabel_2);
		
		textID = new JTextField();
		textID.setEditable(false);
		textID.setForeground(new Color(0, 0, 0));
		textID.setFont(new Font("Tahoma", Font.BOLD, 18));
		textID.setBounds(420, 106, 176, 32);
		contentPane.add(textID);
		textID.setColumns(10);
		
		texttype = new JTextField();
		texttype.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				boolean validate4 = texttype.getText().matches("^[a-zA-Z0-9]*$");
				
				if (validate4) {
					
				} else {
					
					JOptionPane.showMessageDialog(null, "Can't use special characters");
					
				}
				
			}
		});
		
		texttype.setFont(new Font("Tahoma", Font.BOLD, 18));
		texttype.setBounds(420, 271, 176, 32);
		contentPane.add(texttype);
		texttype.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 102, 204));
		panel.setBounds(0, 0, 1100, 81);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblAddVehicle = new JLabel("Add Vehicle");
		lblAddVehicle.setForeground(Color.WHITE);
		lblAddVehicle.setFont(new Font("Showcard Gothic", Font.BOLD, 30));
		lblAddVehicle.setBounds(383, 13, 279, 42);
		panel.add(lblAddVehicle);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 51));
		panel_1.setBounds(0, 82, 1100, 10);
		contentPane.add(panel_1);
		
		textID.setText(generator.vehicleID_Generator(vehicleService.vehicleID()));
		
		JLabel labelImg = new JLabel("");
		labelImg.setIcon(new ImageIcon(AddVehicle.class.getResource("/TransportUNIC.jpg")));
		labelImg.setBounds(717, 146, 300, 350);
		contentPane.add(labelImg);
		
	}
}
