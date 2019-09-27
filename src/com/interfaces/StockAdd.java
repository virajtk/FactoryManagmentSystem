package com.interfaces;

import java.sql.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.util.DbConnect;
import com.util.ID_Generator;
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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class StockAdd extends JFrame {

	private JPanel contentPane;
	private JTextField txtUnitPrice;
	private JTextField txtProductName;
	private JComboBox cmbCategory;
	private JComboBox cmbShape;
	private JComboBox cmbColor;
	private JComboBox cmbStrenght;
	
	
	
	private int war_button = JOptionPane.YES_NO_OPTION;
	private int war_result;
	
	ProductService productService = new ProductService();
	ID_Generator id_Generator = new ID_Generator();
	Product product = new Product();
	private JTextField txtProductID;
	
	private void setStoreText() {
		
		product.setProductId(txtProductID.getText());
		product.setProductName(txtProductName.getText());
		product.setUnitPrice(Double.parseDouble(txtUnitPrice.getText()));
		product.setCategory(cmbCategory.getSelectedItem().toString());
		product.setColour(cmbColor.getSelectedItem().toString());
		product.setStrength(Integer.parseInt(cmbStrenght.getSelectedItem().toString()));  
		product.setShape(cmbShape.getSelectedItem().toString());
		
	}
	
	public boolean validateAddProduct() {
		
		boolean validate2 = txtProductName.getText().matches("^[a-zA-Z]*$")&&txtProductName.getText().length()>=1;
		boolean validate3 = txtUnitPrice.getText().matches("^[0-9.]*$")&&txtUnitPrice.getText().length()>=1;

		
		if (validate2 && validate3 && cmbCategory.getSelectedIndex() != 0 && cmbColor.getSelectedIndex() != 0 &&  cmbStrenght.getSelectedIndex() != 0 && cmbStrenght.getSelectedIndex() != 0) {
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
		
		txtUnitPrice = new JTextField();
		txtUnitPrice.setBounds(188, 221, 400, 50);
		panel.add(txtUnitPrice);
		txtUnitPrice.setColumns(10);
		
		JButton btnSubmit = new JButton("Add");
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
					txtProductID.setText(null);
					txtProductName.setText(null);
					txtUnitPrice.setText(null);
					cmbCategory.setSelectedItem("Please Select...");
					cmbColor.setSelectedItem("Please Select...");
					cmbShape.setSelectedItem("Please Select...");
					cmbStrenght.setSelectedItem("Please Select...");
					
					}
			}
			else {
				JOptionPane.showMessageDialog(null, "Enter Correct Details to the Fields!");
			}
			}
		});
		btnSubmit.setBounds(484, 555, 104, 36);
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
		btnCancle.setBounds(679, 555, 104, 36);
		panel.add(btnCancle);
		
		txtProductName = new JTextField();
		txtProductName.setBounds(188, 103, 400, 50);
		panel.add(txtProductName);
		txtProductName.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Shape");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_6.setBounds(679, 301, 200, 30);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Rs/=");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_7.setBounds(617, 224, 60, 33);
		panel.add(lblNewLabel_7);
		
		cmbCategory = new JComboBox();
		cmbCategory.setModel(new DefaultComboBoxModel(new String[] {"Please Select...", "Mold", "Machine"}));
		cmbCategory.setBounds(188, 344, 400, 50);
		panel.add(cmbCategory);
		
		cmbShape = new JComboBox();
		cmbShape.setModel(new DefaultComboBoxModel(new String[] {"Please select shape", "Block", "Dumble", "Cross Dumble", "Round Dumble", "Square", "Unipaver\t", "Cube", "Fan", "Wave", "Hexagon", "Trihex ", "Trihex Groove", "Mirror"}));
		cmbShape.setBounds(679, 343, 400, 50);
		panel.add(cmbShape);
		
		cmbColor = new JComboBox();
		cmbColor.setModel(new DefaultComboBoxModel(new String[] {"Please select colour", "Natural", "Red", "Black", "Brown", "Yellow", "Blue", "White", "Green"}));
		cmbColor.setBounds(188, 468, 400, 50);
		panel.add(cmbColor);
		
		cmbStrenght = new JComboBox();
		cmbStrenght.setModel(new DefaultComboBoxModel(new String[] {"Please select strenght", "10", "15", "20", "25", "30", "35", "40", "45", "50"}));
		cmbStrenght.setBounds(679, 468, 400, 50);
		panel.add(cmbStrenght);
		
		txtProductID = new JTextField();
		txtProductID.setColumns(10);
		txtProductID.setBounds(689, 103, 400, 50);
		panel.add(txtProductID);
		
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
		
		txtProductID.setText(id_Generator.product_ID_Generaor(productService.getProductID()));
		
	}
}
