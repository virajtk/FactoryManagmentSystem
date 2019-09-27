package com.interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.border.TitledBorder;

import com.model.ShiftingM;
import com.service.ShiftingServices;
import com.util.DbConnect;
import com.util.ID_Generator;

import net.proteanit.sql.DbUtils;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Shifting extends JFrame {

	private JPanel contentPane;
	private JTextField shiftingID;
	private JComboBox cmbSuperID;
	private JComboBox cmbDeliveryIdl; 
	private JComboBox cmbShifter2;
	private JComboBox cmbShifter1;
	
	private String role = "Shifting Supervisour";
	private String role1 = "Shifter";
	
	ShiftingM shiifting;
	ShiftingServices services = new ShiftingServices();

	private PreparedStatement preStatement ;
	private static Connection connection ;
	private JTable table;
	
	
	public boolean validateShiftingFields() {
		boolean validate1 = shiftingID.getText().matches("^[P0-9]*$") && shiftingID.getText().length() == 5;
		boolean validate2 = cmbSuperID.getSelectedIndex() == 1 || cmbSuperID.getSelectedIndex() == 2;
		boolean validate3 = cmbDeliveryIdl.getSelectedIndex() != 0;
		boolean validate4 = cmbShifter1.getSelectedIndex() != 0;
		boolean validate5 = cmbShifter1.getSelectedItem().toString() != cmbShifter2.getSelectedItem().toString();
		
		
		if (validate1  && validate2 && validate3 && validate4 && validate5) {
			return true;
		} else {
			return false;
		}


	}
	
	
	
	

	public void supervicerIdFill() {
		try {
			String selectProductName= "SELECT * FROM unic.user_main where Role like '"+"%"+role+"%"+"'";
			connection = DbConnect.getDBConnection();
			preStatement = connection.prepareStatement(selectProductName);
			ResultSet productSet = preStatement.executeQuery();
			
			while (productSet.next()) {
				cmbSuperID.addItem(productSet.getString("EID"));
	
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void diliveryNoFill() {
		try {
			String diliveryNo= "SELECT * FROM unic.transport";
			connection = DbConnect.getDBConnection();
			preStatement = connection.prepareStatement(diliveryNo);
			ResultSet diliveryNoSet = preStatement.executeQuery();
			
			while (diliveryNoSet.next()) {
				cmbDeliveryIdl.addItem(diliveryNoSet.getString("transportID"));
//				DriverID.setText(diliveryNoSet.getString("driverID"));
//				textFieldVehicleNo.setText(diliveryNoSet.getString("vehicleID"));
//				textOrderID.setText(diliveryNoSet.getString("orderID"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void shiftersFill() {
		try {
			String diliveryNo= "SELECT * FROM unic.user_main WHERE Role = 'Shifter'";
			connection = DbConnect.getDBConnection();
			preStatement = connection.prepareStatement(diliveryNo);
			ResultSet diliveryNoSet = preStatement.executeQuery();
			
			while (diliveryNoSet.next()) {
				cmbShifter1.addItem(diliveryNoSet.getString("EID"));
				cmbShifter2.addItem(diliveryNoSet.getString("EID"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void displayShow() {
		
		try {
			String displayShow = "SELECT * FROM unic.shifting";
			connection = DbConnect.getDBConnection();
			preStatement = connection.prepareStatement(displayShow);
			ResultSet displayTable = preStatement.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(displayTable));
			
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
					Shifting frame = new Shifting();
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
	public Shifting() {
		
		shiifting = new ShiftingM();
		
		setTitle("Shifting");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 717);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 218, 185));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JLabel lblSupervisorid = new JLabel("SupervisorID :");
		lblSupervisorid.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblSupervisorid.setBounds(101, 117, 108, 24);
		contentPane.add(lblSupervisorid);
		
		cmbSuperID = new JComboBox();
		cmbSuperID.setBackground(new Color(255, 255, 255));
		cmbSuperID.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		cmbSuperID.addItem("Please Select");
		cmbSuperID.setBounds(263, 121, 411, 20);
		contentPane.add(cmbSuperID);
		
		JLabel lblDeliveryNo = new JLabel("Delivery No. :");
		lblDeliveryNo.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblDeliveryNo.setBounds(101, 171, 108, 24);
		contentPane.add(lblDeliveryNo);
		
		JLabel lblShifter1 = new JLabel("Shifter 1 :");
		lblShifter1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblShifter1.setBounds(101, 229, 108, 24);
		contentPane.add(lblShifter1);
		
		JLabel lblshiftingID = new JLabel("Shifting ID :");
		lblshiftingID.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblshiftingID.setBounds(101, 66, 108, 24);
		contentPane.add(lblshiftingID);
		
		shiftingID = new JTextField();
		shiftingID.setHorizontalAlignment(SwingConstants.CENTER);
		shiftingID.setEditable(false);
		shiftingID.setBackground(new Color(255, 255, 255));
		shiftingID.setColumns(10);
		shiftingID.setBounds(263, 70, 411, 24);
		contentPane.add(shiftingID);

		
		
		
		JLabel lblShifter2 = new JLabel("Shifter 2 :");
		lblShifter2.setHorizontalAlignment(SwingConstants.LEFT);
		lblShifter2.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblShifter2.setBounds(101, 283, 108, 24);
		contentPane.add(lblShifter2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 784, 44);
		contentPane.add(scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setBackground(new Color(30, 144, 255));
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Shifting");
		lblNewLabel.setBounds(339, 5, 103, 30);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Showcard Gothic", Font.PLAIN, 23));
		panel.add(lblNewLabel);
		
		
		
		cmbDeliveryIdl = new JComboBox();
		cmbDeliveryIdl.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		cmbDeliveryIdl.setModel(new DefaultComboBoxModel(new String[] {"Please select"}));
		cmbDeliveryIdl.setBounds(264, 175, 410, 20);
		contentPane.add(cmbDeliveryIdl);
		
		cmbShifter1 = new JComboBox();
		cmbShifter1.setModel(new DefaultComboBoxModel(new String[] {"Please select"}));
		cmbShifter1.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		cmbShifter1.setBounds(263, 233, 411, 20);
		contentPane.add(cmbShifter1);
		
		cmbShifter2 = new JComboBox();
		cmbShifter2.setModel(new DefaultComboBoxModel(new String[] {"Please select"}));
		cmbShifter2.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		cmbShifter2.setBounds(263, 287, 411, 20);
		contentPane.add(cmbShifter2);
		
		
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(101, 428, 573, 162);
		contentPane.add(scrollPane_1);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int rowNumber = table.getSelectedRow();
				
				shiifting.setShiftingID(table.getValueAt(rowNumber, 0).toString());
				
				
			}
		});
		scrollPane_1.setViewportView(table);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setBackground(new Color(210, 105, 30));
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				shiifting.setShiftingID(shiftingID.getText());
				shiifting.setDiliveryID(cmbDeliveryIdl.getSelectedItem().toString());
				shiifting.setShifter1(cmbShifter1.getSelectedItem().toString());
				shiifting.setShifter2(cmbShifter2.getSelectedItem().toString());
				
				services.AddShifting(shiifting);
				
				displayShow();
				shiftingID.setText(ID_Generator.shifting_Generator(services.shiftingID()));
			}
		});
		btnSubmit.setBounds(316, 349, 114, 37);
		contentPane.add(btnSubmit);
		
		
		JButton btnNewButton = new JButton("Remove");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(178, 34, 34));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					services.DeleteShifting(shiifting.getShiftingID());
					displayShow();
				} catch (InstantiationException | IllegalAccessException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(499, 356, 95, 30);
		contentPane.add(btnNewButton);
		
		
		supervicerIdFill();
		diliveryNoFill();
		shiftersFill();
		displayShow();
		
		
		shiftingID.setText(ID_Generator.shifting_Generator(services.shiftingID()));
	}
}
