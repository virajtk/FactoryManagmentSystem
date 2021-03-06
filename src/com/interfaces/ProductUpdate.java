package com.interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.model.Product;
import com.service.ProductService;
import com.util.DbConnect;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;



public class ProductUpdate extends JFrame {

	private JPanel contentPane;
	private JTextField txtUnitPrice;
	private JTextField txtProductName;
	private JTextField txtProductID;
	private JComboBox cmbCategory;
	private JComboBox cmbShape;
	private JComboBox cmbColour;
	private JComboBox cmbStrenght;
	
	private static Connection connection;
	private PreparedStatement preparedStatement;
	
	ProductService productService = new ProductService();
	Product product = new Product();
	
	
	private int war_button = JOptionPane.YES_NO_OPTION;
	private int war_result;
	
//	public void showProductID1() {
//		try {
//			String selectProductID = "SELECT * FROM unic.product";
//					connection = DbConnect.getDBConnection();
//					preparedStatement = connection.prepareStatement(selectProductID);
//					ResultSet productSet = preparedStatement.executeQuery();
//					
//						while (productSet.next()) {
//							comboBox1.addItem(productSet.getString("productID"));
//							
//						}
//		} catch (Exception e) {
//			// TODO: handle exception
//		} 
//		
//	}
	
	private void updateProductText() {
		
		product.setProductId(txtProductID.getText());
		product.setProductName(txtProductName.getText());
		product.setUnitPrice(Double.parseDouble(txtUnitPrice.getText()));
		product.setCategory(cmbCategory.getSelectedItem().toString());
		product.setColour(cmbColour.getSelectedItem().toString());
		product.setStrength(Integer.parseInt(cmbStrenght.getSelectedItem().toString()));
		product.setShape(cmbShape.getSelectedItem().toString());
		
	}

	public boolean validateUpdateProduct() {
	
		boolean validate1 = txtProductID.getText().matches("^[PR0-9]*$")&&txtProductID.getText().length() == 6;
		boolean validate2 = txtProductName.getText().matches("^[a-zA-Z]*$")&&txtProductName.getText().length()>=1;
		boolean validate3 = txtUnitPrice.getText().matches("^[0-9.]*$")&&txtUnitPrice.getText().length()>=1;

		if (validate1 && validate2 && validate3 && cmbCategory.getSelectedIndex() != 0 && cmbColour.getSelectedIndex() != 0 &&  cmbStrenght.getSelectedIndex() != 0 && cmbStrenght.getSelectedIndex() != 0 ) {
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
					ProductUpdate frame = new ProductUpdate("PR0001");
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
	public ProductUpdate(String productID) {
		setTitle("Update Product");
		setResizable(false);
		
		//System.out.println(productID);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1214, 759);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBounds(0, 0, 1210, 724);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(255, 250, 205));
		panel_1.setBounds(-11, 100, 1219, 624);
		panel.add(panel_1);
		
		JLabel label_7 = new JLabel("Rs/=");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setFont(new Font("Sylfaen", Font.PLAIN, 15));
		label_7.setBounds(525, 219, 58, 33);
		panel_1.add(label_7);
		
		JLabel label = new JLabel("Unit Price");
		label.setFont(new Font("Tahoma", Font.BOLD, 18));
		label.setBounds(160, 163, 200, 26);
		panel_1.add(label);
		
		JLabel label_1 = new JLabel("Category");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_1.setBounds(160, 296, 200, 26);
		panel_1.add(label_1);
		
		JLabel label_2 = new JLabel("Colour");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_2.setBounds(160, 410, 200, 26);
		panel_1.add(label_2);
		
		JLabel label_3 = new JLabel("Product Name");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_3.setBounds(160, 44, 200, 26);
		panel_1.add(label_3);
		
		JLabel label_4 = new JLabel("Product ID");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_4.setBounds(674, 44, 200, 26);
		panel_1.add(label_4);
		
		JLabel label_5 = new JLabel("Strenght");
		label_5.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_5.setBounds(674, 410, 200, 26);
		panel_1.add(label_5);
		
		txtUnitPrice = new JTextField();
		txtUnitPrice.setColumns(10);
		txtUnitPrice.setBounds(183, 216, 400, 50);
		panel_1.add(txtUnitPrice);
		
		txtProductName = new JTextField();
		txtProductName.setColumns(10);
		txtProductName.setBounds(183, 100, 400, 50);
		panel_1.add(txtProductName);
		
		JButton button = new JButton("Submit");
		button.setBackground(new Color(30, 144, 255));
		button.setForeground(new Color(255, 255, 255));
		button.setFont(new Font("Tahoma", Font.BOLD, 18));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				war_result = JOptionPane.showConfirmDialog(null, "Do you want to update this product!","warning",war_button);
				
				if (validateUpdateProduct() == true) {
				
					if (war_result == JOptionPane.YES_OPTION) {
				
						updateProductText();
				
						try {
							productService.pUpdate(product);
						} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				
					}
				
				}
				else {
					
					JOptionPane.showMessageDialog(null, "Enter Correct Details to the Fields!");
				
				}
			}
		});
		button.setBounds(456, 536, 135, 47);
		panel_1.add(button);
		
		JButton button_1 = new JButton("Cancle");
		button_1.setBackground(new Color(165, 42, 42));
		button_1.setForeground(new Color(255, 255, 255));
		button_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StockMain main = new StockMain();
				main.setVisible(true);
				dispose();
			}
		});
		button_1.setBounds(682, 536, 135, 47);
		panel_1.add(button_1);
		
		JLabel label_6 = new JLabel("Shape");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_6.setBounds(674, 296, 200, 26);
		panel_1.add(label_6);
		
		txtProductID = new JTextField();
		txtProductID.setBounds(674, 100, 400, 50);
		panel_1.add(txtProductID);
		txtProductID.setColumns(10);
		
		cmbCategory = new JComboBox();
		cmbCategory.setModel(new DefaultComboBoxModel(new String[] {"Please Select...", "Mold", "Machine"}));
		cmbCategory.setBounds(183, 349, 400, 50);
		panel_1.add(cmbCategory);
		
		cmbShape = new JComboBox();
		cmbShape.setModel(new DefaultComboBoxModel(new String[] {"Please select shape", "Block", "Dumble", "Cross Dumble", "Round Dumble", "Square", "Unipaver\t", "Cube", "Fan", "Wave", "Hexagon", "Trihex ", "Trihex Groove", "Mirror"}));
		cmbShape.setBounds(674, 351, 400, 50);
		panel_1.add(cmbShape);
		
		cmbStrenght = new JComboBox();
		cmbStrenght.setModel(new DefaultComboBoxModel(new String[] {"Please select strenght", "10", "15", "20", "25", "30", "35", "40", "45", "50"}));
		cmbStrenght.setBounds(674, 449, 400, 50);
		panel_1.add(cmbStrenght);
		
		cmbColour = new JComboBox();
		cmbColour.setModel(new DefaultComboBoxModel(new String[] {"Please select colour", "Natural", "Red", "Black", "Brown", "Yellow", "Blue", "White", "Green"}));
		cmbColour.setBounds(183, 449, 400, 50);
		panel_1.add(cmbColour);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.textHighlight);
		panel_2.setBounds(-11, -12, 1219, 101);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblUpdateProduct = new JLabel("Update Product");
		lblUpdateProduct.setBounds(23, 13, 1185, 88);
		panel_2.add(lblUpdateProduct);
		lblUpdateProduct.setForeground(Color.WHITE);
		lblUpdateProduct.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdateProduct.setFont(new Font("Showcard Gothic", Font.BOLD, 30));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(0, 0, 128));
		panel_3.setBounds(-11, 90, 1219, 10);
		panel.add(panel_3);
		//showProductID1() ;
		
		try {
		String selectStore = "select * from unic.product WHERE productID= '"+productID+"'";
		connection = DbConnect.getDBConnection();
		preparedStatement = connection.prepareStatement(selectStore);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			
			txtProductID.setText(resultSet.getString("productID"));
			txtProductName.setText(resultSet.getString("productName"));
			txtUnitPrice.setText(resultSet.getString("unitPrice"));
			cmbCategory.setSelectedItem(resultSet.getString("category"));
			cmbColour.setSelectedItem(resultSet.getString("colour"));
			cmbStrenght.setSelectedItem(resultSet.getString("strength_N"));
			cmbShape.setSelectedItem(resultSet.getString("shape"));
			
			
	
		}
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
