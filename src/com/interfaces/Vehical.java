package com.interfaces;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;

public class Vehical extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vehical frame = new Vehical();
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
	public Vehical() {
		setTitle("Vehicle");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 959, 578);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 222, 173));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				AddVehicle num = null;
				try {
					num = new AddVehicle();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				num.setVisible(true);
				dispose();
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(367, 193, 251, 59);
		contentPane.add(btnNewButton);
		
		JButton btnManage = new JButton("Manage");
		btnManage.setBackground(new Color(255, 255, 255));
		btnManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					VehicleManage nReUp = new VehicleManage();
					nReUp.setVisible(true);
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
				
			}
		});
		btnManage.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnManage.setBounds(367, 294, 251, 59);
		contentPane.add(btnManage);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 102, 204));
		panel.setBounds(0, 0, 953, 101);
		contentPane.add(panel);
		
		JLabel lblVehicle = new JLabel("VEhicle");
		lblVehicle.setHorizontalAlignment(SwingConstants.CENTER);
		lblVehicle.setForeground(Color.WHITE);
		lblVehicle.setFont(new Font("Showcard Gothic", Font.BOLD, 40));
		lblVehicle.setBounds(12, 13, 929, 75);
		panel.add(lblVehicle);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 51));
		panel_1.setBounds(-1, 102, 953, 10);
		contentPane.add(panel_1);
		
	}
}
