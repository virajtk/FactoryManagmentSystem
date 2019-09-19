package com.interfaces;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.util.DbConnect;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import com.model.*;
import com.service.*;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class StockUpdate extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JComboBox idComboBox ;
	
	private static Connection connection;
	private PreparedStatement preparedStatement;
	
	StoreService storeService = new StoreService();
	Store store = new Store();
	
	private int war_button = JOptionPane.YES_NO_OPTION;
	private int war_result;

	private void updateQuantityText() {
		
		store.setProductId(idComboBox.getSelectedItem().toString());
		
		store.setQuantity(Integer.parseInt(textField_2.getText()));
		
		
	}
	
	public void showProductID3() {
		try {
			String selectProductID = "SELECT * FROM unic.product_store";
					connection = DbConnect.getDBConnection();
					preparedStatement = connection.prepareStatement(selectProductID);
					ResultSet productSet = preparedStatement.executeQuery();
					
						while (productSet.next()) {
							idComboBox.addItem(productSet.getString("itemID"));
							
						}
		} catch (Exception e) {
			// TODO: handle exception
		} 
		
	}
	
public boolean validateupdateStore() {
		
		boolean validate1 = textField_2.getText().matches("^[0-9]*$") && textField_2.getText().length()>=1;
		
	
		if (validate1 ) {
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
					StockUpdate frame = new StockUpdate();
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
	public StockUpdate() {
		setTitle("UpdateQuantity");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1087, 648);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.textHighlight);
		panel_1.setBounds(12, 13, 1045, 84);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblUpdateQuantity = new JLabel("Update Quantity");
		lblUpdateQuantity.setForeground(new Color(255, 255, 255));
		lblUpdateQuantity.setBackground(new Color(255, 255, 255));
		lblUpdateQuantity.setBounds(0, 0, 1045, 84);
		panel_1.add(lblUpdateQuantity);
		lblUpdateQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdateQuantity.setFont(new Font("Showcard Gothic", Font.BOLD, 30));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 0, 128));
		panel_2.setBounds(12, 101, 1045, 10);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(22, 110, 1035, 491);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Raw Material", null, panel, null);
		panel.setLayout(null);
		panel.setBackground(new Color(255, 222, 173));
		
		JLabel lblRawMaterialId = new JLabel("Raw Material ID");
		lblRawMaterialId.setHorizontalAlignment(SwingConstants.CENTER);
		lblRawMaterialId.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblRawMaterialId.setBounds(234, 86, 169, 31);
		panel.add(lblRawMaterialId);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(540, 85, 203, 36);
		panel.add(textField);
		
		JLabel lblDoYouSure = new JLabel("Do you sure update this quantities!");
		lblDoYouSure.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDoYouSure.setHorizontalAlignment(SwingConstants.CENTER);
		lblDoYouSure.setBounds(282, 252, 461, 43);
		panel.add(lblDoYouSure);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBackground(new Color(30, 144, 255));
		btnUpdate.setForeground(new Color(255, 255, 255));
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnUpdate.setBounds(634, 364, 122, 36);
		panel.add(btnUpdate);
		
		JButton button_1 = new JButton("Cancle");
		button_1.setForeground(new Color(255, 255, 255));
		button_1.setBackground(new Color(165, 42, 42));
		button_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				StoreQuantityDisplay storeQuantityDisplay = new StoreQuantityDisplay();
				storeQuantityDisplay.setVisible(true);
				dispose();
				
			}
		});
		button_1.setBounds(488, 366, 122, 36);
		panel.add(button_1);
		
		JLabel lblNewLabel = new JLabel("Quantity");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(234, 156, 179, 31);
		panel.add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setBounds(540, 155, 203, 36);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 250, 205));
		tabbedPane.addTab("Product", null, panel_3, null);
		panel_3.setLayout(null);
		
		JLabel label_1 = new JLabel("Product ID");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_1.setBounds(234, 64, 169, 31);
		panel_3.add(label_1);
		
		JLabel label_2 = new JLabel("Quantity");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_2.setBounds(224, 134, 179, 31);
		panel_3.add(label_2);
		
		textField_2 = new JTextField();
		textField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				boolean validate = textField_2.getText().matches("[0-9 ,]+") && textField_2.getText().length()>=1;
				if (validate) {
					
				} else {
						JOptionPane.showMessageDialog(null, "Please enter number value!");
				}
			}
		});
		textField_2.setColumns(10);
		textField_2.setBounds(530, 140, 169, 22);
		panel_3.add(textField_2);
		
		JLabel label_3 = new JLabel("Do you sure update this quantities!");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_3.setBounds(272, 237, 461, 43);
		panel_3.add(label_3);
		
		JButton button = new JButton("Cancle");
		button.setFont(new Font("Tahoma", Font.BOLD, 18));
		button.setBackground(new Color(165, 42, 42));
		button.setForeground(new Color(255, 255, 255));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				StoreQuantityDisplay storeQuantityDisplay = new StoreQuantityDisplay();
				storeQuantityDisplay.setVisible(true);
				dispose();
			}
		});
		button.setBounds(481, 354, 109, 31);
		panel_3.add(button);
		
		JButton button_2 = new JButton("Update");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				war_result = JOptionPane.showConfirmDialog(null, "Do you want to Update this product!","warning",war_button);
				if (war_result == JOptionPane.YES_OPTION) {
					
					updateQuantityText();
					try {
						storeService.qUpdate(store);
					} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
							| SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}
				
			}
		});
		button_2.setBackground(new Color(30, 144, 255));
		button_2.setForeground(new Color(255, 255, 255));
		button_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		button_2.setBounds(624, 354, 109, 31);
		panel_3.add(button_2);
		
		idComboBox = new JComboBox();
		idComboBox.setBounds(530, 70, 169, 22);
		panel_3.add(idComboBox);
		showProductID3();
	}
}
