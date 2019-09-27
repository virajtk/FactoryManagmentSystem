package com.interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//import com.mysql.cj.xdevapi.DbDoc;
import com.service.VehicleService;
import com.util.DbConnect;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.model.*;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;

public class VehicleManage extends JFrame {

	
	Connection conn= null;
	PreparedStatement psst = null;
	ResultSet rs = null;
	
	
	private JPanel contentPane;
	private JTable table_1;
	
	private Connection connection;
	private PreparedStatement preStatement;
	
	VehicleModel vehicleModel = new VehicleModel();
	
	
	
	
	private boolean clicked = false;
	private JTextField vehiID;
	private JTextField vehiName;
	private JTextField vehiModel;
	private JTextField VehiType;
	private JTextField vehiCapacity;
	private JTextField vehiReq;
	
	private int warnin_message_button = JOptionPane.YES_NO_OPTION;
	private int warning_message_result;
	private JTextField searchId;
	
	
	public void searchShow(String search) {
		try {
			String selectVehicle = "SELECT * FROM unic.vehicle where vehicleID = '"+search+"'";
			connection = DbConnect.getDBConnection();
			preStatement = connection.prepareStatement(selectVehicle);
			ResultSet resultSet = preStatement.executeQuery();
			table_1.setModel(DbUtils.resultSetToTableModel(resultSet));
		} catch (Exception e) {
			
		}
				
	}
	
	
	
	
	private void tableSelectItemOrder() {
		
		int rowNumber = table_1.getSelectedRow();
		
		vehicleModel.setVehicleID(table_1.getValueAt(rowNumber, 0).toString());
		
		clicked = true;
		vehiID.setText(table_1.getValueAt(rowNumber, 0).toString());
		vehiName.setText(table_1.getValueAt(rowNumber, 1).toString());
		vehiModel.setText(table_1.getValueAt(rowNumber, 2).toString());
		VehiType.setText(table_1.getValueAt(rowNumber, 3).toString());
		vehiCapacity.setText(table_1.getValueAt(rowNumber, 4).toString());
		vehiReq.setText(table_1.getValueAt(rowNumber, 5).toString());
		
	}
		public boolean validateVehicleFields() {
		
		boolean validate1 = vehiID.getText().matches("^[A-Z0-9]*$") && vehiID.getText().length() <= 5 ;
		boolean validate2 = vehiName.getText().matches("^[a-zA-Z]*$") && vehiName.getText().length() > 2;
		boolean validate3 = vehiModel.getText().matches("^[a-zA-Z0-9]*$") && vehiModel.getText().length() > 2;
		boolean validate4 = VehiType.getText().matches("^[a-zA-Z0-9]*$") && VehiType.getText().length() < 10;
		boolean validate5 = vehiCapacity.getText().matches("^[0-9]*$") && vehiCapacity.getText().length() > 1;
		boolean validate6 = vehiReq.getText().matches("^[A-Z0-9]*$") && vehiReq.getText().length() <= 7;
		
		
		if (validate1  && validate2 && validate3 && validate4 && validate5 && validate6) {
			return true;
		} else {
			return false;
		}
	}
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VehicleManage frame = new VehicleManage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void tableDisplay() {
		try {
			String selectVehicle = "select * from vehicle";
			connection = DbConnect.getDBConnection();
			preStatement = connection.prepareStatement(selectVehicle);
			ResultSet resultSet = preStatement.executeQuery();
			table_1.setModel(DbUtils.resultSetToTableModel(resultSet));
		} catch (Exception e) {
			
		}
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public VehicleManage() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		setTitle("Vehicles");
		setResizable(false);
		
		conn = DbConnect.getDBConnection();
		VehicleService vehicleService = new VehicleService();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1113, 772);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 222, 173));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setBounds(10, 160, 1085, 221);
		contentPane.add(scrollPane);
		
		table_1 = new JTable();
		table_1.setRowHeight(25);
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				tableSelectItemOrder();
				
				
			}
		});
		scrollPane.setViewportView(table_1);
		table_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JButton btnEdit = new JButton("Update");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				warning_message_result = JOptionPane.showConfirmDialog (null, "Do you want to update","Warning",warnin_message_button);
				if(warning_message_result == JOptionPane.YES_OPTION) {
				
					//System.out.println(clicked);
					if (clicked != true) {
						JOptionPane.showMessageDialog(null, "Please select the record");
						
					} else {
						vehicleModel.setVehicleID(vehiID.getText());
						vehicleModel.setVehicleName(vehiName.getText());
						vehicleModel.setModelName(vehiModel.getText());
						vehicleModel.setVehicleType(VehiType.getText());
						vehicleModel.setVehicleCapacity(vehiCapacity.getText());
						vehicleModel.setRegistrationNumber(vehiReq.getText());
						
						vehicleService.updateVehicle(vehicleModel);
						tableDisplay();
						
						
						clicked = false;
					
					}
				}
					
				
			
			}
		});
		btnEdit.setBackground(new Color(255, 165, 0));
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnEdit.setBounds(929, 494, 137, 54);
		contentPane.add(btnEdit);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBackground(new Color(30, 144, 255));
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				warning_message_result = JOptionPane.showConfirmDialog (null, "Do you want to remove","Warning",warnin_message_button);
				if(warning_message_result == JOptionPane.YES_OPTION) {
				
				
				//System.out.println(clicked);
				if (clicked != true) {
					JOptionPane.showMessageDialog(null, "Please select the record");
					
				} else {
					
					vehicleService.removeVehicle(vehicleModel);
					tableDisplay();
					clicked = false;
					
				}
				}
			}
		});
		btnRemove.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnRemove.setBounds(929, 397, 135, 54);
		contentPane.add(btnRemove);
		
		JPanel panel = new JPanel();
		panel.setToolTipText("");
		panel.setBackground(new Color(0, 102, 204));
		panel.setBounds(0, 0, 1107, 88);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblVehicleDetails = new JLabel("Vehicle Details");
		lblVehicleDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblVehicleDetails.setFont(new Font("Showcard Gothic", Font.BOLD, 30));
		lblVehicleDetails.setForeground(new Color(255, 255, 255));
		lblVehicleDetails.setBounds(12, 13, 1083, 62);
		panel.add(lblVehicleDetails);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 51));
		panel_1.setBounds(0, 88, 1107, 10);
		contentPane.add(panel_1);
		
		JLabel label = new JLabel("Vehicle ID");
		label.setFont(new Font("Tahoma", Font.BOLD, 18));
		label.setBounds(223, 397, 167, 22);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Vehicle Name");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_1.setBounds(223, 454, 188, 16);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Model");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_2.setBounds(223, 508, 137, 26);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("Vehicle Type");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_3.setBounds(223, 560, 137, 26);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("Vehicle Capacity");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_4.setBounds(223, 613, 212, 26);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("Registation Number");
		label_5.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_5.setBounds(223, 670, 212, 26);
		contentPane.add(label_5);
		
		vehiID = new JTextField();
		
		vehiID.setForeground(Color.BLACK);
		vehiID.setFont(new Font("Tahoma", Font.BOLD, 18));
		vehiID.setColumns(10);
		vehiID.setBounds(474, 394, 176, 32);
		contentPane.add(vehiID);
		
		vehiName = new JTextField();
		vehiName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				boolean validate2 = vehiName.getText().matches("^[a-zA-Z]*$") ;
				
				if (validate2) {
					
				} else {
					
					JOptionPane.showMessageDialog(null, "Can't use numbers and special characters");
					
				}
			}
		});
		vehiName.setFont(new Font("Tahoma", Font.BOLD, 18));
		vehiName.setColumns(10);
		vehiName.setBounds(474, 448, 176, 32);
		contentPane.add(vehiName);
		
		vehiModel = new JTextField();
		vehiModel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				boolean validate3 = vehiModel.getText().matches("^[a-zA-Z0-9]*$");
				
				if (validate3) {
					
				} else {
					
					JOptionPane.showMessageDialog(null, "Can't use special characters");
					
				}
			}
		});
		vehiModel.setFont(new Font("Tahoma", Font.BOLD, 18));
		vehiModel.setColumns(10);
		vehiModel.setBounds(474, 502, 176, 32);
		contentPane.add(vehiModel);
		
		VehiType = new JTextField();
		VehiType.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				boolean validate4 = VehiType.getText().matches("^[a-zA-Z0-9]*$");
				
				if (validate4) {
					
				} else {
					
					JOptionPane.showMessageDialog(null, "Can't use special characters");
					
				}
			}
		});
		VehiType.setFont(new Font("Tahoma", Font.BOLD, 18));
		VehiType.setColumns(10);
		VehiType.setBounds(474, 560, 176, 32);
		contentPane.add(VehiType);
		
		vehiCapacity = new JTextField();
		vehiCapacity.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				boolean validate5 = vehiCapacity.getText().matches("^[0-9]*$");
				
				if (validate5) {
					
				} else {
					
					JOptionPane.showMessageDialog(null, "Can't use characters");
					
				}
				
			}
		});
		vehiCapacity.setFont(new Font("Tahoma", Font.BOLD, 18));
		vehiCapacity.setColumns(10);
		vehiCapacity.setBounds(474, 612, 176, 32);
		contentPane.add(vehiCapacity);
		
		vehiReq = new JTextField();
		vehiReq.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				boolean validate6 = vehiReq.getText().matches("^[A-Z0-9]*$");
				
				if (validate6) {
					
				} else {
					
					JOptionPane.showMessageDialog(null, "Can't use simple latters and special characters");
					
				}
			}
		});
		vehiReq.setFont(new Font("Tahoma", Font.BOLD, 18));
		vehiReq.setColumns(10);
		vehiReq.setBounds(474, 667, 176, 32);
		contentPane.add(vehiReq);
		
		JButton btnBack = new JButton("Exit");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				warning_message_result = JOptionPane.showConfirmDialog (null, "Do you want to exit","Warning",warnin_message_button);
				
				if(warning_message_result == JOptionPane.YES_OPTION) {
					
				Vehical vei=new Vehical();
				vei.setVisible(true);
				dispose();
			}
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnBack.setBackground(new Color(204, 51, 0));
		btnBack.setBounds(929, 585, 137, 54);
		contentPane.add(btnBack);
		
		searchId = new JTextField();
		searchId.setFont(new Font("Tahoma", Font.PLAIN, 18));
		searchId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (searchId.getText().length() > 4) {
					searchShow(searchId.getText());
				}else if (searchId.getText().length() == 0) {
					tableDisplay();
				}
			}
		});
		searchId.setBounds(434, 115, 167, 32);
		contentPane.add(searchId);
		searchId.setColumns(10);
		
		JLabel lblVehicleSearch = new JLabel("Vehicle Search");
		lblVehicleSearch.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblVehicleSearch.setBounds(241, 123, 149, 16);
		contentPane.add(lblVehicleSearch);
		
		
		
		tableDisplay();
	}
}
