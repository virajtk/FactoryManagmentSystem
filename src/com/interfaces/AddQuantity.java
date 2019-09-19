package com.interfaces;

import java.awt.BorderLayout;
import com.*;
import com.model.*;
import com.service.ProductService;
import com.service.StoreService;
import com.util.DbConnect;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class AddQuantity extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBox;
	private JTextField textField_1;
	private static Connection connection;
	private PreparedStatement preparedStatement;
	Store store = new Store();
	StoreService storeService = new StoreService();
	
	private int war_button = JOptionPane.YES_NO_OPTION;
	private int war_result;
	
	
	public void setText() {
		store.setProductId(comboBox.getSelectedItem().toString());
		store.setQuantity(Integer.parseInt(textField_1.getText()));
	}
	
	public void showProductID() {
		try {
			String selectProductID = "SELECT * FROM unic.product";
					connection = DbConnect.getDBConnection();
					preparedStatement = connection.prepareStatement(selectProductID);
					ResultSet productSet = preparedStatement.executeQuery();
					
						while (productSet.next()) {
							comboBox.addItem(productSet.getString("productID"));
							
						}
		} catch (Exception e) {
			// TODO: handle exception
		} 
		
	}
	
	public boolean validateAddQuantity() {
		
		boolean validate1 = textField_1.getText().matches("[0-9 ,]+") && textField_1.getText().length()>=1;
		
		if (validate1) {
			return true;
		}
		else {
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
					AddQuantity frame = new AddQuantity();
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
	public AddQuantity() {
		setTitle("AddQuantity");
		setResizable(false);
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1088, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.textHighlight);
		panel.setBounds(12, 13, 1046, 122);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Add Product Quantity");
		lblNewLabel.setForeground(new Color(240, 248, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Showcard Gothic", Font.BOLD, 30));
		lblNewLabel.setBounds(0, 0, 1046, 122);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 128));
		panel_1.setBounds(12, 137, 1046, 10);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 250, 205));
		panel_2.setBounds(12, 164, 1046, 533);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Product ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(299, 161, 153, 19);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Quantity");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setBounds(299, 256, 153, 19);
		panel_2.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		
		textField_1.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				boolean validate = textField_1.getText().matches("[0-9 ,]+") && textField_1.getText().length()>=1;
				if (validate) {
					
				} else {
						JOptionPane.showMessageDialog(null, "Please enter number value!");
				}
			}
		});
		textField_1.setBounds(524, 253, 229, 36);
		panel_2.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.setBackground(new Color(165, 42, 42));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StoreQuantityDisplay storeQuantityDisplay = new StoreQuantityDisplay();
				storeQuantityDisplay.setVisible(true);
				dispose(); 
			}
		});
		btnNewButton.setBounds(519, 438, 107, 36);
		panel_2.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Add");
		btnNewButton_1.setBackground(new Color(30, 144, 255));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				war_result = JOptionPane.showConfirmDialog(null, "Do you want to add this product!","warning",war_button);
				if (validateAddQuantity() == true)  {
					if (war_result == JOptionPane.YES_OPTION)  {
						setText();
						
						storeService.StockAddQuan(store);
						
						comboBox.setSelectedItem(null);
						textField_1.setText(null);
					}
				}
				else {
	
					JOptionPane.showMessageDialog(null, "Enter Correct Details to the Fields!");
					
				}
				
				
			}
		});
		btnNewButton_1.setBounds(664, 438, 120, 36);
		panel_2.add(btnNewButton_1);
		
		 comboBox = new JComboBox();
		comboBox.setBounds(524, 161, 229, 36);
		panel_2.add(comboBox);
		showProductID();
	}
}
