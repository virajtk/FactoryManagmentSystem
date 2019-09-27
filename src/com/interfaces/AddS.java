package com.interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

//import Updates.UpdateSupplier;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import java.util.function.Supplier;

import java.awt.event.ActionEvent;
import java.awt.TextArea;
import java.awt.TextField;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JEditorPane;
import java.awt.Button;
import java.awt.Insets;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Panel;
import java.awt.Font;
import javax.swing.JScrollBar;
import java.awt.SystemColor;
import javax.swing.JRadioButton;
import java.awt.Checkbox;
import javax.swing.UIManager;
import javax.swing.JCheckBoxMenuItem;
import java.awt.Scrollbar;
import javax.swing.JTable;

import com.util.DbConnect;

import net.proteanit.sql.DbUtils;

import com.service.*;
import com.model.Supplier;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import java.awt.Label;


public class AddS extends JFrame {
	
	private static Connection connection;
	private PreparedStatement preStatement;
	addSServices supplierSe = new addSServices();
	Supplier smodel = new Supplier();
	
	

	private JPanel contentPane;
	private JTextField sID;
	private JTable table;
	private TextArea locat;
	private JTextField fName;
	private JTextField lName;
	private JTextField cName;
	private JTextField eMail;
	private JTextField mNo;
	private JComboBox rMaterial;
	private JLabel validate1,validate2,validate3,validate4,validate5,validate6,validate7,validate8;
	public void searchWithId(String supID) {
		try {
			String qu = "SELECT * FROM unic.supplier where supplierID like '"+"%"+supID+"%"+"'";
			connection = DbConnect.getDBConnection();
			preStatement = connection.prepareStatement(qu);
			ResultSet supTable = preStatement.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(supTable));
		} catch (Exception e) {
			
		
		}
	}
	
	
	
	public void tableShow() {
		try {
			String qu = "SELECT * FROM unic.supplier";
			connection = DbConnect.getDBConnection();
			preStatement = connection.prepareStatement(qu);
			ResultSet supTable = preStatement.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(supTable));
		} catch (Exception e) {
			
		
		}
	}

	
	public boolean validateSupplierFields() {
		
		boolean validate8 = rMaterial.getSelectedIndex() == 1 || rMaterial.getSelectedIndex() == 2;
		boolean validate1 = sID.getText().matches("^[A-Z0-9]*$") && sID.getText().length() <= 5 ;
		boolean validate2= fName.getText().matches("^[a-zA-Z]*$") && fName.getText().length() > 2 ;
		boolean validate3 = lName.getText().matches("^[a-zA-Z]*$") && lName.getText().length() > 2 ;
		boolean validate4 = cName.getText().matches("^[a-zA-Z]*$") && cName.getText().length() > 2 ;
		boolean validate5 = eMail.getText().matches("^[@ . a-zA-Z0-9]*$") &&  eMail.getText().length() > 2 ;
		boolean validate6 = mNo.getText().matches("^[0-9]*$") && mNo.getText().length() == 10;
		boolean validate7 = locat.getText().matches("^[a-zA-Z0-9]*$") && locat.getText().length() > 2 ;
		
		if (validate1 && validate2 && validate3 && validate4 && validate5 && validate6 && validate7 && validate8) {
			
			return true;
		}
		else {
			return false;
			
		}
	}
	
	
	public void resetFields () {
		
		rMaterial.setSelectedItem(null);
		sID.setText(null);
		fName.setText(null);
		lName.setText(null);
		cName.setText(null);
		eMail.setText(null);
		mNo.setText(null);
		locat.setText(null);
		table.setModel(new DefaultTableModel());
		
	}
	
	
	
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		
		DbConnect db = new DbConnect();
		
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddS frame = new AddS();
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
	public AddS() {
		setTitle("Supplier");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 726, 664);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setForeground(new Color(218, 165, 32));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(23, 460, 493, 164);
		contentPane.add(scrollPane_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane_1.setViewportView(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
	
					
					int rowNumber = table.getSelectedRow();
					
					     sID.setText(table.getValueAt(rowNumber, 0).toString());
					    fName.setText(table.getValueAt(rowNumber, 1).toString());
					     lName.setText(table.getValueAt(rowNumber, 2).toString());
					     locat.setText(table.getValueAt(rowNumber, 3).toString());
					        cName.setText(table.getValueAt(rowNumber, 4).toString());
					eMail.setText(table.getValueAt(rowNumber, 5).toString());
					 mNo.setText(table.getValueAt(rowNumber, 6).toString());
					 rMaterial.setSelectedItem(table.getValueAt(rowNumber, 7).toString());    
	
			}
		});
		scrollPane.setViewportView(table);
		table.setBackground(new Color(204, 255, 255));
		table.setForeground(new Color(0, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.textHighlight);
		panel.setBounds(0, 0, 720, 68);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblSupplier = new JLabel("Supplier");
		lblSupplier.setBounds(0, 0, 720, 68);
		lblSupplier.setHorizontalAlignment(SwingConstants.CENTER);
		lblSupplier.setForeground(new Color(255, 255, 255));
		panel.add(lblSupplier);
		lblSupplier.setFont(new Font("Showcard Gothic", Font.BOLD, 30));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 250, 205));
		panel_2.setBounds(0, 69, 720, 566);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		sID = new JTextField();
		sID.setBounds(206, 23, 246, 28);
		sID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				searchWithId(sID.getText());
				
				if(sID.getText().matches("^[A-Z0-9]*$") && sID.getText().length() == 5)
				{
					
					validate1.setEnabled(false);
					validate1.setVisible(false);
					
				}
				else {
					validate1.setEnabled(true);
					validate1.setVisible(true);
					
					
			}
			}
		
		});
		panel_2.add(sID);
		sID.setBackground(Color.WHITE);
		sID.setColumns(10);
		
		JLabel lblSupplierId = new JLabel("Supplier ID");
		lblSupplierId.setBounds(115, 35, 85, 14);
		panel_2.add(lblSupplierId);
		lblSupplierId.setFont(new Font("Calibri Light", Font.BOLD, 14));
		
		JLabel lblRawMatirialType = new JLabel("Raw Matirial Type ");
		lblRawMatirialType.setBounds(62, 341, 134, 30);
		panel_2.add(lblRawMatirialType);
		lblRawMatirialType.setFont(new Font("Calibri Light", Font.BOLD, 14));
		
		JLabel lblFullName = new JLabel("First Name ");
		lblFullName.setBounds(115, 63, 93, 20);
		panel_2.add(lblFullName);
		lblFullName.setFont(new Font("Calibri Light", Font.BOLD, 14));
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(115, 107, 71, 14);
		panel_2.add(lblLastName);
		lblLastName.setFont(new Font("Calibri Light", Font.BOLD, 14));
		
		JLabel Location = new JLabel("Address");
		Location.setBounds(133, 151, 67, 14);
		panel_2.add(Location);
		Location.setFont(new Font("Calibri Light", Font.BOLD, 14));
		
		JLabel lblMobileNo = new JLabel("Mobile No ");
		lblMobileNo.setBounds(115, 294, 96, 14);
		panel_2.add(lblMobileNo);
		lblMobileNo.setFont(new Font("Calibri Light", Font.BOLD, 14));
		
		JLabel lblEmail = new JLabel("Email ");
		lblEmail.setBounds(143, 267, 42, 14);
		panel_2.add(lblEmail);
		lblEmail.setFont(new Font("Calibri Light", Font.BOLD, 14));
		
		JLabel lblCompanyName = new JLabel("Company Name ");
		lblCompanyName.setBounds(76, 222, 112, 14);
		panel_2.add(lblCompanyName);
		lblCompanyName.setFont(new Font("Calibri Light", Font.BOLD, 14));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 720, 10);
		panel_2.add(panel_1);
		panel_1.setBackground(new Color(0, 0, 51));
		
		JButton btnDeleteSupplier = new JButton("Remove Supplier");
		btnDeleteSupplier.setBounds(539, 511, 148, 32);
		panel_2.add(btnDeleteSupplier);
		btnDeleteSupplier.setForeground(new Color(255, 255, 255));
		btnDeleteSupplier.setBackground(new Color(204, 0, 0));
		btnDeleteSupplier.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton btnAddsupplier = new JButton("Add ");
		btnAddsupplier.setBounds(539, 393, 148, 32);
		panel_2.add(btnAddsupplier);
		btnAddsupplier.setForeground(new Color(255, 255, 255));
		btnAddsupplier.setBackground(SystemColor.textHighlight);
		btnAddsupplier.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton btnUpdateSupplier = new JButton("Update Supplier");
		btnUpdateSupplier.setBounds(539, 450, 148, 32);
		panel_2.add(btnUpdateSupplier);
		btnUpdateSupplier.setForeground(new Color(255, 255, 255));
		btnUpdateSupplier.setBackground(new Color(210, 105, 30));
		btnUpdateSupplier.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton ResetFieldBtn = new JButton("Cancel");
		ResetFieldBtn.setBounds(481, 344, 105, 30);
		panel_2.add(ResetFieldBtn);
		ResetFieldBtn.setBackground(new Color(178, 34, 34));
		ResetFieldBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				resetFields ();
				
					}
		});
		ResetFieldBtn.setForeground(new Color(255, 255, 255));
		ResetFieldBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		fName = new JTextField();
		fName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (fName.getText().matches("^[a-zA-Z]*$") && fName.getText().length() > 2) {
			
					validate2.setEnabled(false);
					validate2.setVisible(false);	
					
				} else {
					
					validate2.setEnabled(true);
					validate2.setVisible(true);
					
				}
				
			}
		});
		fName.setBounds(207, 65, 245, 24);
		panel_2.add(fName);
		fName.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(207, 65, 96, -13);
		panel_2.add(lblNewLabel);
		
		validate1 = new JLabel("invalid Supplier Id");
		validate1.setForeground(Color.RED);
		validate1.setEnabled(false);
		validate1.setBounds(207, 50, 130, 16);
		panel_2.add(validate1);
		
		validate2 = new JLabel("Invalid First name");
		validate2.setEnabled(false);
		validate2.setForeground(Color.RED);
		validate2.setBounds(207, 90, 130, 10);
		panel_2.add(validate2);
		
		lName = new JTextField();
		lName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (lName.getText().matches("^[a-zA-Z]*$") && lName.getText().length() > 2) {
					
					validate3.setEnabled(false);
					validate3.setVisible(false);	
					
				} else {
					
					validate3.setEnabled(true);
					validate3.setVisible(true);
					
				}
					
			}
		});
		lName.setBounds(207, 102, 245, 22);
		panel_2.add(lName);
		lName.setColumns(10);
		
		validate3 = new JLabel("invalid Last name");
		validate3.setEnabled(false);
		validate3.setForeground(Color.RED);
		validate3.setBounds(207, 125, 134, 16);
		panel_2.add(validate3);
		
		locat = new TextArea();
		locat.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				if (locat.getText().matches("^[a-zA-Z0-9]*$") && locat.getText().length() > 2) {
					
					validate4.setEnabled(false);
					validate4.setVisible(false);	
					
				} else {
					
					validate4.setEnabled(true);
					validate4.setVisible(true);
					
				
				}
				
				
				
			}
		});
		locat.setBounds(200, 146, 252, 43);
		panel_2.add(locat);
		
		validate4 = new JLabel("Invalid Address");
		validate4.setEnabled(false);
		validate4.setForeground(Color.RED);
		validate4.setBounds(207, 195, 130, 16);
		panel_2.add(validate4);
		
		cName = new JTextField();
		cName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				if (cName.getText().matches("^[a-zA-Z]*$") && cName.getText().length() > 2) {
					
					validate5.setEnabled(false);
					validate5.setVisible(false);	
					
				} else {
					
					validate5.setEnabled(true);
					validate5.setVisible(true);
					
				
				}
				
				
				
			}
		});
		cName.setBounds(207, 217, 245, 22);
		panel_2.add(cName);
		cName.setColumns(10);
		
		validate5 = new JLabel("Invalid company name");
		validate5.setEnabled(false);
		validate5.setForeground(Color.RED);
		validate5.setBounds(207, 235, 130, 16);
		panel_2.add(validate5);
		
		eMail = new JTextField();
		eMail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				if (eMail.getText().matches("^[@ . a-zA-Z0-9]*$") &&  eMail.getText().length() > 2) {
					
					validate6.setEnabled(false);
					validate6.setVisible(false);	
					
				} else {
					
					validate6.setEnabled(true);
					validate6.setVisible(true);
					
				}
				
			}
		});
		eMail.setBounds(207, 259, 245, 22);
		panel_2.add(eMail);
		eMail.setColumns(10);
		
		validate6 = new JLabel("Invalid email format");
		validate6.setEnabled(false);
		validate6.setForeground(Color.RED);
		validate6.setBounds(207, 277, 130, 16);
		panel_2.add(validate6);
		
		mNo = new JTextField();
		mNo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				if (mNo.getText().matches("^[0-9]*$") && mNo.getText().length() == 10) {
					
					validate7.setEnabled(false);
					validate7.setVisible(false);	
					
				} else {
					
					validate7.setEnabled(true);
					validate7.setVisible(true);
					
					
					
				}
				
				
			}
		});
		mNo.setBounds(207, 294, 245, 22);
		panel_2.add(mNo);
		mNo.setColumns(10);
		
		validate7 = new JLabel("Invalid mobile number");
		validate7.setEnabled(false);
		validate7.setForeground(Color.RED);
		validate7.setBounds(207, 321, 130, 16);
		panel_2.add(validate7);
		
		rMaterial = new JComboBox();
		rMaterial.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				if (rMaterial.getSelectedIndex() == 1 || rMaterial.getSelectedIndex() == 2) {
					
					validate8.setEnabled(false);
					validate8.setVisible(false);	
					
				} else {
					
					validate8.setEnabled(true);
					validate8.setVisible(true);
					
				}
				
			}
		});
		rMaterial.setModel(new DefaultComboBoxModel(new String[] {"please select....", "Metal", "Chips", "Pigmants", "CoriDust", "Cement", "Water"}));
		rMaterial.setBounds(206, 344, 246, 22);
		panel_2.add(rMaterial);
		
		validate8 = new JLabel("Select a one type");
		validate8.setEnabled(false);
		validate8.setForeground(Color.RED);
		validate8.setBounds(206, 369, 131, 16);
		panel_2.add(validate8);
		
		//update the supplier
		btnUpdateSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				smodel.setRawMaterialType(rMaterial.getSelectedItem().toString());
				smodel.setSupplierId(sID.getText());
				smodel.setFirstName(fName.getText());
				smodel.setLastName(lName.getText());
				smodel.setCompanyName(cName.getText());
				smodel.setEmail(eMail.getText());
				smodel.setMobileNo(mNo.getText());
				smodel.setLocation(locat.getText());
				
				supplierSe.updateSupplier(smodel);
				
				resetFields();
				tableShow();
			}
			
		});
		//add supplier
		btnAddsupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (validateSupplierFields() == true) {
					
				
				
				smodel.setRawMaterialType(rMaterial.getSelectedItem().toString());
				smodel.setSupplierId(sID.getText());
				smodel.setFirstName(fName.getText());
				smodel.setLastName(lName.getText());
				smodel.setCompanyName(cName.getText());
				smodel.setEmail(eMail.getText());
				smodel.setMobileNo(mNo.getText());
				smodel.setLocation(locat.getText());
				
				supplierSe.addSupplier(smodel);
				resetFields();
				tableShow();
				
				}
				else {
					JOptionPane.showMessageDialog(null, "Please fill data correctly");
				}
			}

		});
		
		//delete supplier
		btnDeleteSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				smodel.setSupplierId(sID.getText());
				
				supplierSe.removeSupplier(smodel);
				tableShow();
			
				resetFields();
			}
		});
		validate1.setVisible(false);
		validate2.setVisible(false);
		validate3.setVisible(false);
		validate4.setVisible(false);
		validate5.setVisible(false);
		validate6.setVisible(false);
		validate7.setVisible(false);
		validate8.setVisible(false);
		
		
	}
	
	
}
