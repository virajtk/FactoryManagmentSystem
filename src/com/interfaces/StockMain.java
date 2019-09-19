package com.interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.model.Product;
import com.util.DbConnect;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JToolBar;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class StockMain extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField searchTxt;
	private static PreparedStatement preparedStatement;
	private static Connection connection;
	Product selectPoduct;
	
	
	public void tableDisplay() {
		try {
			String selectStore = "select * from unic.product";
			connection = DbConnect.getDBConnection();
			preparedStatement = connection.prepareStatement(selectStore);
			ResultSet resultSet = preparedStatement.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultSet));
		} catch (Exception e) {
			
		}
	}
	
	public void mainSearch(String search) {
		try {
			String selectProduct = "SELECT * FROM unic.product where productID = '"+search+"'";
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
					StockMain frame = new StockMain();
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
	public StockMain() {
		setResizable(false);
		
		
		setTitle("MainStock");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1305, 816);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 312, 1236, 265);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					
				int clickedRow = table.getSelectedRow();
				String value = (String) table.getValueAt(clickedRow, 0);
				String selectStore = "select * from unic.product WHERE productID= '"+value+"'";
				connection = DbConnect.getDBConnection();
				preparedStatement = connection.prepareStatement(selectStore);
				ResultSet resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next()) {
					
				selectPoduct = new Product();
				selectPoduct.setProductId(resultSet.getString("productID"));
			
				}
				
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
				
			}
		});
		scrollPane.setViewportView(table);
		tableDisplay();
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.textHighlight);
		panel_1.setBounds(12, 13, 1263, 91);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		JLabel lblStockManagement = new JLabel("Product Management");
		lblStockManagement.setBounds(0, 0, 1263, 91);
		panel_1.add(lblStockManagement);
		lblStockManagement.setForeground(Color.WHITE);
		lblStockManagement.setHorizontalAlignment(SwingConstants.CENTER);
		lblStockManagement.setFont(new Font("Showcard Gothic", Font.BOLD, 30));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 0, 128));
		panel_2.setBounds(12, 106, 1263, 10);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 250, 205));
		panel_3.setBounds(12, 129, 1263, 547);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Remove");
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(165, 42, 42));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_1.setBounds(995, 483, 119, 35);
		panel_3.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Update");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(210, 105, 30));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(1132, 483, 119, 35);
		panel_3.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Add\r\n");
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setBackground(new Color(30, 144, 255));
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_2.setBounds(854, 483, 119, 35);
		panel_3.add(btnNewButton_2);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 23, 1234, 140);
		panel_3.add(panel);
		panel.setBackground(new Color(253, 245, 230));
		panel.setLayout(null);
		
		searchTxt = new JTextField();
		searchTxt.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyReleased(KeyEvent arg0) {
//				if (searchTxt.getText().length()>5) {
//					mainSearch(searchTxt.getText());
//				} else {
//					tableDisplay();
//				}
//			}
		});
		searchTxt.setBounds(45, 38, 1030, 22);
		panel.add(searchTxt);
		searchTxt.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setForeground(new Color(255, 255, 255));
		btnSearch.setBackground(new Color(30, 144, 255));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (searchTxt.getText().length()>5) {
					mainSearch(searchTxt.getText());
					searchTxt.setText(null);
				} else {
					tableDisplay();
				}
			}
		});
		btnSearch.setBounds(1087, 37, 117, 25);
		panel.add(btnSearch);
		
		JButton btnNewButton_3 = new JButton("Refresh ");
		btnNewButton_3.setBackground(new Color(210, 105, 30));
		btnNewButton_3.setForeground(new Color(255, 255, 255));
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tableDisplay();
			}
		});
		btnNewButton_3.setBounds(965, 89, 239, 38);
		panel.add(btnNewButton_3);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				StockAdd add = new StockAdd();
				add.setVisible(true);
				dispose();
				
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//System.out.println(selectPoduct.getProductId());
				ProductUpdate productUpdate = new ProductUpdate(selectPoduct.getProductId().toString());
				productUpdate.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				StockDelete delete = new StockDelete();
				delete.setVisible(true);
				dispose();
			}
		});
		
		
	}
}
