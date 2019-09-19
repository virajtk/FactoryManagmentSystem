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
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TransportManage extends JFrame {

	private JPanel contentPane;
	private JTable table;

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
	public TransportManage() {
		setTitle("Add Transport");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1112, 667);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 103, 873, 492);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		
		JButton btnEdit = new JButton("Update");
		btnEdit.setBackground(new Color(255, 255, 255));
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnEdit.setBounds(922, 103, 145, 55);
		contentPane.add(btnEdit);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBackground(new Color(255, 255, 255));
		btnRemove.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnRemove.setBounds(922, 181, 145, 55);
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
		
		JLabel label = new JLabel("Add Transport");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Showcard Gothic", Font.BOLD, 35));
		label.setBounds(352, 13, 346, 42);
		panel_1.add(label);
		
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
	}
}
