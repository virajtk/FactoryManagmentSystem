package com.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;

public class Attendance extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Attendance frame = new Attendance();
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
	public Attendance() {
		setTitle("Daily Attendance");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Attendance.class.getResource("/calendar-tasks-icon.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 862, 518);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		Image img3 = new ImageIcon(this.getClass().getResource("/mark-task-icon.png")).getImage();
		contentPane.setLayout(null);
		Image img2 = new ImageIcon(this.getClass().getResource("/import-icon.png")).getImage();
		Image img1 = new ImageIcon(this.getClass().getResource("/report-icon.png")).getImage();
		Image img = new ImageIcon(this.getClass().getResource("/Alarm-Error-icon.png")).getImage();
		contentPane.setBounds(100, 100, 680, 480);
		this.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setForeground(Color.WHITE);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBackground(new Color(30, 144, 255));
		panel.setBounds(0, 0, 856, 79);
		contentPane.add(panel);
		
		JLabel labelTitle = new JLabel("  Daily Attendance");
		Image img4 = new ImageIcon(this.getClass().getResource("/mark-task-icon.png")).getImage();
		labelTitle.setIcon(new ImageIcon(img4));
		labelTitle.setBounds(12, 13, 832, 53);
		panel.add(labelTitle);
		labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitle.setForeground(Color.WHITE);
		labelTitle.setFont(new Font("Showcard Gothic", Font.PLAIN, 30));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 51));
		panel_1.setBounds(0, 80, 856, 10);
		contentPane.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 250, 205));
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(10, 103, 834, 367);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		
		JButton btnImport = new JButton("Import");
		btnImport.setBounds(345, 23, 128, 47);
		panel_2.add(btnImport);
		btnImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					
					JFileChooser file = new JFileChooser();
					file.setCurrentDirectory(new File(System.getProperty("user.home")+"/Desktop"));
					FileNameExtensionFilter filter = new FileNameExtensionFilter("*.docs", "HTM" , "ODT" , "PDF" , "XLS", "XLSX", "TXT");
					file.addChoosableFileFilter(filter);
					file.setAcceptAllFileFilterUsed(true);
					int result = file.showSaveDialog(null);
					if (result == JFileChooser.APPROVE_OPTION) {
						
						File selectedFile = file.getSelectedFile();
						String path = selectedFile.getAbsolutePath();
						String FilePath = path;
						
						//txtFilePath.setText(FilePath);
					
						
						
					}
					else if (result == JFileChooser.CANCEL_OPTION){
						JOptionPane.showMessageDialog(null, "No File Selected");
					}
					
					
					}catch(Exception e1) {
						JOptionPane.showMessageDialog(null, e1);
					}
				
			}
		});
		btnImport.setIcon(new ImageIcon(img2));
		btnImport.setForeground(SystemColor.text);
		btnImport.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnImport.setBackground(new Color(210, 105, 30));
		
		JButton btnExit = new JButton(" Exit");
		btnExit.setBounds(572, 29, 114, 35);
		panel_2.add(btnExit);
		btnExit.setIcon(new ImageIcon(img));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnExit.setForeground(new Color(224, 255, 255));
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExit.setBackground(new Color(178, 34, 34));
		
		JButton btnMonthReport = new JButton("Report");
		btnMonthReport.setBounds(134, 29, 114, 35);
		panel_2.add(btnMonthReport);
		btnMonthReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					JasperReport jasperReport = JasperCompileManager.compileReport("C:\\Users\\VS\\Documents\\Projects\\Management System\\src\\com\\util\\attendanceReport.jrxml");
					
					JRDataSource jrDataSource = new JREmptyDataSource();
					
					JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null , jrDataSource);
					
					JasperExportManager.exportReportToPdfFile(jasperPrint ,"C:\\Users\\VS\\Desktop\\monthly_Attendance_Report.pdf" );
					
				} catch (JRException e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
				
			}
		});
		btnMonthReport.setFocusPainted(false);
		btnMonthReport.setIcon(new ImageIcon(img1));
		btnMonthReport.setForeground(SystemColor.text);
		btnMonthReport.setBackground(SystemColor.textHighlight);
		btnMonthReport.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnMonthReport.setActionCommand("Monthly Report");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(97, 112, 636, 218);
		panel_2.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 87, 810, 14);
		panel_2.add(separator);
	}
}
