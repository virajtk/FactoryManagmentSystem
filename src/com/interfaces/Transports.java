package com.interfaces;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class Transports extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Transports frame = new Transports();
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
	public Transports() {
		setTitle("Transport");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 963, 583);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 222, 173));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddTransport trans=new AddTransport();
				trans.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(338, 205, 225, 60);
		contentPane.add(btnNewButton);
		
		JButton btnManage = new JButton("Manage");
		btnManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TransportManage reup=new TransportManage();
				reup.setVisible(true);
				dispose();
			}
		});
		btnManage.setBackground(new Color(255, 255, 255));
		btnManage.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnManage.setBounds(338, 324, 225, 68);
		contentPane.add(btnManage);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 102, 204));
		panel.setBounds(0, 0, 945, 101);
		contentPane.add(panel);
		
		JLabel lblTransport = new JLabel("Transport");
		lblTransport.setHorizontalAlignment(SwingConstants.CENTER);
		lblTransport.setForeground(Color.WHITE);
		lblTransport.setFont(new Font("Showcard Gothic", Font.BOLD, 40));
		lblTransport.setBounds(12, 13, 921, 75);
		panel.add(lblTransport);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 51));
		panel_1.setBounds(-1, 102, 945, 10);
		contentPane.add(panel_1);
	}
}
