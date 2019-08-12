package com.interfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.SystemColor;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.JTable;

public class DailyAttendance {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DailyAttendance window = new DailyAttendance();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DailyAttendance() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.text);
		frame.getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setForeground(Color.WHITE);
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBackground(new Color(30, 144, 255));
		panel_1.setBounds(0, 0, 664, 79);
		frame.getContentPane().add(panel_1);
		
		JLabel lblDailyAttendance = new JLabel("Daily Attendance");
		lblDailyAttendance.setBounds(10, 11, 644, 68);
		panel_1.add(lblDailyAttendance);
		lblDailyAttendance.setHorizontalAlignment(SwingConstants.CENTER);
		lblDailyAttendance.setForeground(Color.WHITE);
		lblDailyAttendance.setFont(new Font("Showcard Gothic", Font.PLAIN, 30));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 0, 128));
		panel_2.setBounds(0, 78, 664, 10);
		frame.getContentPane().add(panel_2);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 88, 664, 295);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_3.setBackground(new Color(245, 245, 220));
		panel_3.setBounds(10, 11, 644, 273);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JButton btnImport = new JButton("Import");
		Image img2 = new ImageIcon(this.getClass().getResource("/import-icon.png")).getImage();
		btnImport.setIcon(new ImageIcon(img2));
		btnImport.setForeground(SystemColor.text);
		btnImport.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnImport.setBackground(new Color(210, 105, 30));
		btnImport.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnImport.setBounds(262, 23, 114, 47);
		panel_3.add(btnImport);
		
		JButton btnMonthReport = new JButton("Report");
		btnMonthReport.setFocusPainted(false);
		Image img1 = new ImageIcon(this.getClass().getResource("/report-icon.png")).getImage();
		btnMonthReport.setIcon(new ImageIcon(img1));
		btnMonthReport.setForeground(SystemColor.text);
		btnMonthReport.setBackground(SystemColor.textHighlight);
		btnMonthReport.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnMonthReport.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnMonthReport.setActionCommand("Monthly Report");
		btnMonthReport.setBounds(103, 29, 114, 35);
		panel_3.add(btnMonthReport);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 91, 624, 14);
		panel_3.add(separator);
		
		JButton btnExit = new JButton(" Exit");
		Image img = new ImageIcon(this.getClass().getResource("/Alarm-Error-icon.png")).getImage();
		btnExit.setIcon(new ImageIcon(img));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnExit.setForeground(new Color(224, 255, 255));
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExit.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnExit.setBackground(new Color(178, 34, 34));
		btnExit.setBounds(435, 29, 114, 35);
		panel_3.add(btnExit);
		
		table = new JTable();
		table.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		table.setBounds(103, 118, 455, 131);
		panel_3.add(table);
		frame.setBounds(100, 100, 680, 422);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
