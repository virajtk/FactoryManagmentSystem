package com.interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.model.Product;
import com.service.ProductService;
import com.util.DbConnect;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class StockDelete extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBox_1;

	private static Connection connection;
	private PreparedStatement preparedStatement;
	
	private int war_button = JOptionPane.YES_NO_OPTION;
	private int war_result;
	
	public void showProductID2() {
		try {
			String selectProductID = "SELECT * FROM unic.product";
					connection = DbConnect.getDBConnection();
					preparedStatement = connection.prepareStatement(selectProductID);
					ResultSet productSet = preparedStatement.executeQuery();
					
						while (productSet.next()) {
							comboBox_1.addItem(productSet.getString("productID"));
							
						}
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
					StockDelete frame = new StockDelete();
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
	public StockDelete() {
		setTitle("Delete Product");
		setResizable(false);
		
		Product product = new Product();
		ProductService productService = new ProductService();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1256, 736);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 250, 205));
		panel.setBounds(0, 96, 1248, 602);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Product ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(520, 57, 258, 48);
		panel.add(lblNewLabel);
		
		JLabel lblIfYouDelete = new JLabel("If you delete this product details, It  will delete permanently!");
		lblIfYouDelete.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblIfYouDelete.setHorizontalAlignment(SwingConstants.CENTER);
		lblIfYouDelete.setBounds(433, 260, 460, 48);
		panel.add(lblIfYouDelete);
		
		//Delete_Button
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBackground(new Color(210, 105, 30));
		btnDelete.setForeground(new Color(255, 255, 255));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				war_result = JOptionPane.showConfirmDialog(null, "Do you want to delete this product!","warning",war_button);
				
				if (war_result == JOptionPane.YES_OPTION) {
				
				product.setProductId(comboBox_1.getSelectedItem().toString());
				productService.pDelete(product.getProductId());
				
				}
				
				
				
			}
		});
		btnDelete.setBounds(706, 402, 157, 48);
		panel.add(btnDelete);
		
		//Cancle_Button
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
		btnCancle.setBounds(484, 402, 157, 48);
		panel.add(btnCancle);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(530, 130, 248, 42);
		panel.add(comboBox_1);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.textHighlight);
		panel_1.setBounds(0, 0, 1248, 83);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblDeleteProduct = new JLabel("Delete Product");
		lblDeleteProduct.setBounds(12, 13, 1224, 56);
		panel_1.add(lblDeleteProduct);
		lblDeleteProduct.setForeground(SystemColor.textHighlightText);
		lblDeleteProduct.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeleteProduct.setFont(new Font("Showcard Gothic", Font.BOLD, 30));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 0, 128));
		panel_2.setBounds(0, 82, 1248, 16);
		contentPane.add(panel_2);
		showProductID2();
	}
}
