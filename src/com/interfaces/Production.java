package com.interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JSplitPane;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import com.model.ProductionM;
import com.model.ShiftingM;
//import com.mysql.cj.xdevapi.PreparableStatement;
import com.service.ProductionServices;
import com.service.ShiftingServices;
import com.util.DbConnect;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Production extends JFrame {

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	private static Connection connection;
	private PreparedStatement preStatement;	
	
	
	ProductionM production = new ProductionM();
	ProductionServices Pservices = new ProductionServices();
	
	
	private JPanel contentPane;
	private JTextField txtNoOfPieces;
	private JTable table;
	private JComboBox cmbMachine;
	private JDateChooser dateChooser;
	private JTextField productionID;
	private JTextField serachID;
	
	
	private int refreshValue = 1;
	
	DbConnect dbConnect = new DbConnect();
	
	public void setProduction() {
		
	}
	
	
	public void displayShow() {
		
		
		try {
			String displayShow = "SELECT * FROM unic.production";
			connection = DbConnect.getDBConnection();
			preStatement = connection.prepareStatement(displayShow);
			ResultSet displayTable = preStatement.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(displayTable));
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	public void displayShowProduction() {
		
		
		try {
			String displayShow = "SELECT * FROM unic.production";
			connection = DbConnect.getDBConnection();
			preStatement = connection.prepareStatement(displayShow);
			ResultSet displayTable = preStatement.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(displayTable));
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void displayShowProduction(String ProductionID) {
		
		
		try {
			String displayShow = "SELECT * FROM unic.production where productionID = '"+ProductionID+"'";
			connection = DbConnect.getDBConnection();
			preStatement = connection.prepareStatement(displayShow);
			ResultSet displayTable = preStatement.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(displayTable));
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	private void eidSelect() {
		
		int rowNumber = table.getSelectedRow();
		productionID.setText(table.getValueAt(rowNumber, 0).toString());
	
	}
	
	
	private void productionSelect() throws ParseException {
		
		int rowNumber = table.getSelectedRow();
		
		productionID.setText(table.getValueAt(rowNumber, 0).toString());
		cmbMachine.setSelectedItem(table.getValueAt(rowNumber, 3).toString());
		txtNoOfPieces.setText(table.getValueAt(rowNumber,1 ).toString());
		dateChooser.setDate(format.parse((table.getValueAt(rowNumber, 2).toString())));
	
	}
	
	/*private void viewAllOrders() {
		try {
			String selectClient = "SELECT EID, FName, LName FROM unic.user_main; ";
			connection = DbConnect.getDBConnection();
			preStatement = connection.prepareStatement(selectClient);
			ResultSet resultSet = preStatement.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultSet));
		} catch (Exception e) {
			
		}

	}*/
	
	

	/**
	 * Launch the application.
	 */
	
	public boolean validateProductionFields() {
		boolean validate1 = productionID.getText().matches("^[P0-9]*$") && productionID.getText().length() == 5;
		boolean validate2 = txtNoOfPieces.getText().matches("^[0-9]*$") && txtNoOfPieces.getText().length() <= 5;
		boolean validate3 = dateChooser.getDate() != null;
		boolean validate4 = cmbMachine.getSelectedIndex() == 1 || cmbMachine.getSelectedIndex() == 2;
		
		if (validate1  && validate2 && validate3 && validate4) {
			return true;
		} else {
			return false;
		}


	}
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Production frame = new Production();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public Production() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		setResizable(false);
		//dbConnect.getDBConnection();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 855, 623);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 218, 185));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JLabel lblNoOfPieces = new JLabel("No. of pieces :");
		lblNoOfPieces.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNoOfPieces.setBounds(123, 406, 123, 19);
		contentPane.add(lblNoOfPieces);
		
		txtNoOfPieces = new JTextField();
		txtNoOfPieces.setColumns(10);
		txtNoOfPieces.setBounds(256, 407, 396, 20);
		contentPane.add(txtNoOfPieces);
		
		JLabel lblDate = new JLabel("Date :");
		lblDate.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblDate.setBounds(123, 450, 123, 19);
		contentPane.add(lblDate);
		
		JLabel lblMachineNo = new JLabel("Machine No. :");
		lblMachineNo.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblMachineNo.setBounds(123, 493, 123, 19);
		contentPane.add(lblMachineNo);
		
		cmbMachine = new JComboBox();
		cmbMachine.setModel(new DefaultComboBoxModel(new String[] {"Please Select","1", "2"}));
		cmbMachine.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		cmbMachine.setBounds(256, 494, 396, 20);
		contentPane.add(cmbMachine);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setBackground(new Color(210, 105, 30));
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (validateProductionFields() == true) {
					table.setModel(new DefaultTableModel());
				production.setProductionID(productionID.getText().toString());
				production.setProductionDate(format.format(dateChooser.getDate()));
				production.setNoOfPices(Integer.parseInt(txtNoOfPieces.getText()));
				production.setMechineNo(Integer.parseInt(cmbMachine.getSelectedItem().toString()));
				
				Pservices.AddProduction(production);
				productionID.setText(null);
				table.setModel(new DefaultTableModel());	
				txtNoOfPieces.setText(null);
				dateChooser.setDate(null);
				cmbMachine.setSelectedIndex(0);
				
				displayShowProduction();
				} else {
						JOptionPane.showMessageDialog(null, "Inserted details are wrong.");
				}
				
				
			}
		});
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(122, 108, 530, 218);
		contentPane.add(scrollPane_2);
		
		table = new JTable();
		scrollPane_2.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if (refreshValue == 1) {
					eidSelect();
				} else if (refreshValue == 2)  {
					try {
						productionSelect();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
				
			}
		});
		//table.getColumnModel().getColumn(1).setPreferredWidth(141);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) SystemColor.textHighlight));
		btnSubmit.setBounds(256, 534, 89, 23);
		contentPane.add(btnSubmit);
		
		JButton btnReset = new JButton("Cancel");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshValue = 1;
		
				table.setModel(new DefaultTableModel());	
				txtNoOfPieces.setText(null);
				dateChooser.setDate(null);
				productionID.setText(null);
				cmbMachine.setSelectedIndex(0);
				displayShow();
			}
		});
		btnReset.setBounds(563, 534, 89, 23);
		contentPane.add(btnReset);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(652, 207, -9, -159);
		contentPane.add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(651, 46, -9, 164);
		contentPane.add(scrollPane_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(30, 144, 255));
		panel.setBounds(0, 0, 851, 35);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("Production");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Showcard Gothic", Font.PLAIN, 23));
		panel.add(lblNewLabel);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(256, 449, 396, 19);
		contentPane.add(dateChooser);
		
		JLabel lblProductionId = new JLabel("Production ID");
		lblProductionId.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblProductionId.setBounds(123, 358, 123, 19);
		contentPane.add(lblProductionId);
		
		productionID = new JTextField();
		productionID.setColumns(10);
		productionID.setBounds(256, 358, 396, 20);
		contentPane.add(productionID);
		
		JButton btnNewButton = new JButton("Update");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(SystemColor.textHighlight);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				production.setProductionID(productionID.getText().toString());
				production.setProductionDate(format.format(dateChooser.getDate()));
				production.setNoOfPices(Integer.parseInt(txtNoOfPieces.getText()));
				production.setMechineNo(Integer.parseInt(cmbMachine.getSelectedItem().toString()));
				
				try {
					Pservices.UpdateProduction(production);
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				
				
			}
		});
		btnNewButton.setBounds(358, 534, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Remove");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(new Color(178, 34, 34));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Pservices.DeleteProduction(productionID.getText());
				} catch (InstantiationException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				displayShowProduction();
			}
		});
		btnNewButton_1.setBounds(457, 534, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblS = new JLabel("Search Productions");
		lblS.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblS.setBounds(112, 76, 134, 19);
		contentPane.add(lblS);
		
		serachID = new JTextField();
		serachID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (serachID.getText().length() > 3) {
					displayShowProduction(serachID.getText().toString());
					refreshValue = 2;
					}
				
			}
		});
		serachID.setColumns(10);
		serachID.setBounds(256, 77, 396, 20);
		contentPane.add(serachID);
		
		JButton btnDemo = new JButton("DEMO");
		btnDemo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				productionID.setText("P0004");
				txtNoOfPieces.setText("350");
				
				
			}
		});
		btnDemo.setForeground(new Color(178, 34, 34));
		btnDemo.setFont(new Font("! PEPSI !", Font.PLAIN, 13));
		btnDemo.setBounds(740, 46, 97, 25);
		contentPane.add(btnDemo);
		
		
		displayShow();
		refreshValue = 1;
	}
}
