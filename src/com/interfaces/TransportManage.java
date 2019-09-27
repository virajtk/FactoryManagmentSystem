package com.interfaces;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.border.LineBorder;

import com.model.TransportModel;
import com.model.VehicleModel;
import com.service.TransportService;
import com.util.DbConnect;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TransportManage extends JFrame {

	private JPanel contentPane;
	private JTable table;

	TransportModel transportModel;
	TransportService transportService;
	
	private boolean clicked = false;
	private Connection connection;
	private PreparedStatement preStatement;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TransportManage frame = new TransportManage();
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
	
	public void tableDisplay() {
		try {
			String selectVehicle = "select * from transport";
			connection = DbConnect.getDBConnection();
			preStatement = connection.prepareStatement(selectVehicle);
			ResultSet resultSet = preStatement.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultSet));
		} catch (Exception e) {
			
		}
	}
	
	private void tableSelectItemOrder() {
		
		int rowNumber = table.getSelectedRow();
		
		transportModel.setTransportID(table.getValueAt(rowNumber, 0).toString());
		
		clicked = true;
		
	}
	
	
	
	
	public TransportManage() {
		
		transportModel = new TransportModel();
		transportService = new TransportService();
		
		setTitle("Add Transport");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1112, 667);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 103, 873, 492);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				tableSelectItemOrder();
			}
		});
		table.setRowHeight(25);
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		scrollPane.setViewportView(table);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int rowNumber = table.getSelectedRow();
				
				transportModel.setTransportID(table.getValueAt(rowNumber, 0).toString());
				
				transportService.removeTransport(transportModel);
				tableDisplay();
			}
		});
		btnRemove.setBackground(new Color(255, 255, 255));
		btnRemove.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnRemove.setBounds(922, 103, 145, 55);
		contentPane.add(btnRemove);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 51));
		panel.setBounds(-1, 77, 1107, 10);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(0, 102, 204));
		panel_1.setBounds(0, 0, 1107, 76);
		contentPane.add(panel_1);
		
		JLabel lblTransport = new JLabel("Transport Details");
		lblTransport.setForeground(Color.WHITE);
		lblTransport.setFont(new Font("Showcard Gothic", Font.BOLD, 35));
		lblTransport.setBounds(352, 13, 457, 42);
		panel_1.add(lblTransport);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Transports trans=new Transports();
				trans.setVisible(true);
				dispose();
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnExit.setBackground(Color.WHITE);
		btnExit.setBounds(922, 540, 145, 55);
		contentPane.add(btnExit);
		
		tableDisplay();
	}
}
