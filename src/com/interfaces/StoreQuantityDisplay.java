package com.interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.util.DbConnect;

import net.proteanit.sql.DbUtils;

import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

public class StoreQuantityDisplay extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private static PreparedStatement preparedStatement;
	private static Connection connection;
	private JTable table_1;
	private JTable table_2;
	
	public void storeDisplay() {
		try {
			String selectStore = "select * from unic.product_store";
			connection = DbConnect.getDBConnection();
			preparedStatement = connection.prepareStatement(selectStore);
			ResultSet resultSet = preparedStatement.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultSet));
		} catch (Exception e) {
			
		}
	}
	
	public void quantitySearch(String search) {
		try {
			String selectProduct = "SELECT * FROM unic.product_store where itemID = '"+search+"'";
			connection = DbConnect.getDBConnection();
			preparedStatement = connection.prepareStatement(selectProduct);
			ResultSet resultSet = preparedStatement.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultSet));
		} catch (Exception e) {
			
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StoreQuantityDisplay frame = new StoreQuantityDisplay();
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
	public StoreQuantityDisplay() {
		setResizable(false);
		setTitle("Store");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1129, 773);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.textHighlight);
		panel.setBounds(12, 13, 1106, 97);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Store");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Showcard Gothic", Font.BOLD, 30));
		lblNewLabel.setBounds(12, 13, 1082, 71);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 250, 205));
		panel_1.setBounds(12, 139, 1106, 574);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 147, 1047, 175);
		panel_1.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		storeDisplay();
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(28, 13, 1047, 106);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		JButton btnNewButton = new JButton("Update Quantity");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBackground(new Color(210, 105, 30));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StockUpdate update = new StockUpdate();
				update.setVisible(true);
				dispose(); 
			}
		});
		btnNewButton.setBounds(785, 68, 250, 25);
		panel_3.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Add Quantity");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_1.setBackground(new Color(30, 144, 255));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				AddQuantity addQuantity = new AddQuantity();
				addQuantity.setVisible(true);
				dispose(); 
			}
		});
		btnNewButton_1.setBounds(504, 68, 250, 25);
		panel_3.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Search");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textField.getText().length()>5) {
					quantitySearch(textField.getText());
					textField.setText(null);
				} else {
					storeDisplay();
				}
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_2.setBackground(new Color(30, 144, 255));
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setBounds(785, 24, 250, 25);
		panel_3.add(btnNewButton_2);
		
		textField = new JTextField();
		textField.setBounds(12, 24, 742, 25);
		panel_3.add(textField);
		textField.setColumns(10);
		
		table_1 = new JTable();
		table_1.setBounds(46, 371, 18, -16);
		panel_1.add(table_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(28, 430, 1047, 131);
		panel_1.add(scrollPane_1);
		
		table_2 = new JTable();
		scrollPane_1.setViewportView(table_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 0, 51));
		panel_2.setBounds(12, 112, 1106, 14);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		
		try {
			String selectStore = "SELECT rawID,amount FROM unic.supply;";
			connection = DbConnect.getDBConnection();
			preparedStatement = connection.prepareStatement(selectStore);
			ResultSet resultSet = preparedStatement.executeQuery();
			table_2.setModel(DbUtils.resultSetToTableModel(resultSet));
			
			JPanel panel_4 = new JPanel();
			panel_4.setBounds(28, 337, 1047, 70);
			panel_1.add(panel_4);
			panel_4.setLayout(null);
			
			
			JLabel lblRawMaterialsDisplay = new JLabel("Raw Materials Display");
			lblRawMaterialsDisplay.setHorizontalAlignment(SwingConstants.CENTER);
			lblRawMaterialsDisplay.setBounds(0, 0, 280, 70);
			panel_4.add(lblRawMaterialsDisplay);
			lblRawMaterialsDisplay.setForeground(SystemColor.desktop);
			lblRawMaterialsDisplay.setBackground(SystemColor.desktop);
			lblRawMaterialsDisplay.setFont(new Font("Showcard Gothic", Font.BOLD, 18));
		} catch (Exception e) {
			
		}
		
	}
}
