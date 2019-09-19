package com.interfaces;

import java.sql.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.util.DbConnect;
import com.model.*;
import com.service.ProductService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class StockAdd extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	
	private int war_button = JOptionPane.YES_NO_OPTION;
	private int war_result;
	
	ProductService productService = new ProductService();
	Product product = new Product();
	
	private void setStoreText() {
		
		product.setProductId(textField_2.getText());
		product.setProductName(textField_6.getText());
		product.setUnitPrice(Double.parseDouble(textField.getText()));
		product.setCategory(textField_4.getText());
		product.setColour(textField_5.getText());
		product.setStrength(Integer.parseInt(textField_3.getText()));
		product.setShape(textField_1.getText());
		
	}
	
	public boolean validateAddProduct() {
		boolean validate1 = textField_2.getText().matches("^[PR0-9]*$")&&textField_2.getText().length()<=6;
		boolean validate2 = textField_6.getText().matches("^[a-zA-Z]*$")&&textField_6.getText().length()>=1;
		boolean validate3 = textField.getText().matches("^[0-9.]*$")&&textField.getText().length()>=1;
		boolean validate4 = textField_4.getText().matches("^[a-zA-Z]*$")&&textField_4.getText().length()>=1;
		boolean validate5 = textField_5.getText().matches("^[a-zA-Z]*$")&&textField_5.getText().length()>=1;
		boolean validate6 = textField_3.getText().matches("^[0-9]*$")&&textField_3.getText().length()>=1;
		boolean validate7 = textField_1.getText().matches("^[a-zA-Z]*$")&&textField_1.getText().length()>=1;
		
		if (validate1 && validate2 && validate3 && validate4 && validate5 && validate6 && validate7) {
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
					StockAdd frame = new StockAdd();
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
	public StockAdd() {
		setResizable(false);
		setTitle("Add Stock");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1252, 748);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 250, 205));
		panel.setBounds(0, 92, 1245, 619);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Unit Price");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(165, 168, 200, 30);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Category");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(165, 301, 200, 30);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Colour");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setBounds(165, 415, 200, 30);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Product Name");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3.setBounds(165, 49, 200, 30);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Product ID");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_4.setBounds(679, 49, 200, 30);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Strenght");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_5.setBounds(679, 415, 200, 30);
		panel.add(lblNewLabel_5);
		
		textField = new JTextField();
		textField.setBounds(188, 221, 400, 36);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(679, 347, 400, 36);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(679, 103, 400, 36);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				boolean validate6 = textField_3.getText().matches("^[0-9]*$");
				if (validate6) {
					
				} else {
						JOptionPane.showMessageDialog(null, "Please enter number value!");
				}
			}
		});
		textField_3.setBounds(679, 456, 400, 36);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(188, 347, 400, 36);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(188, 456, 400, 36);
		panel.add(textField_5);
		textField_5.setColumns(10);
		
		JButton btnSubmit = new JButton("Update");
		btnSubmit.setBackground(new Color(30, 144, 255));
		btnSubmit.setForeground(new Color(255, 255, 255));
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			war_result = JOptionPane.showConfirmDialog(null, "Do you want to add this product!","warning",war_button);
			if (validateAddProduct() == true) {
				
				if (war_result == JOptionPane.YES_OPTION) {
					
					
					setStoreText();
			
					productService.StockAddInsert(product);
					
					textField.setText(null);
					textField_1.setText(null);
					textField_2.setText(null);
					textField_3.setText(null);
					textField_4.setText(null);
					textField_5.setText(null);
					textField_6.setText(null);
					}
			}
			else {
				JOptionPane.showMessageDialog(null, "Enter Correct Details to the Fields!");
			}
			}
		});
		btnSubmit.setBounds(1063, 543, 104, 36);
		panel.add(btnSubmit);
		
		JButton btnCancle = new JButton("Cancle");
		btnCancle.setBackground(new Color(165, 42, 42));
		btnCancle.setForeground(new Color(255, 255, 255));
		btnCancle.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				StockMain main = new StockMain();
				main.setVisible(true);
				dispose();
				
			}
		});
		btnCancle.setBounds(931, 543, 104, 36);
		panel.add(btnCancle);
		
		textField_6 = new JTextField();
		textField_6.setBounds(188, 103, 400, 36);
		panel.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Shape");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_6.setBounds(679, 301, 200, 30);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Rs/=");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_7.setBounds(617, 224, 60, 33);
		panel.add(lblNewLabel_7);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.textHighlight);
		panel_1.setBounds(0, 13, 1245, 66);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblAddProduct = new JLabel("Add Product");
		lblAddProduct.setBounds(-15, 0, 1248, 66);
		lblAddProduct.setForeground(Color.WHITE);
		panel_1.add(lblAddProduct);
		lblAddProduct.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddProduct.setFont(new Font("Showcard Gothic", Font.BOLD, 30));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 80, 1245, 10);
		contentPane.add(panel_2);
		panel_2.setBackground(new Color(0, 0, 128));
	}
	
	

}
