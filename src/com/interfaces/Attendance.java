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
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

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

import net.proteanit.sql.DbUtils;
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
import net.sf.jasperreports.engine.export.tabulator.Row;
import net.sf.jasperreports.view.JasperViewer;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.model.Attend;
import com.service.AttendService;
import com.util.DbConnect;
import javax.swing.ScrollPaneConstants;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.Dimension;

public class Attendance extends JFrame {

	private JPanel contentPane;
	private JTable table;
	Attend attend;
	
	//Connection Object 
	Connection connection = null;

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
	
	public void refreshAttendanceTable() {
		try {
			
			String query = "SElECT * FROM attendance";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			pst.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public Attendance() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		
		attend = new Attend();
		connection = DbConnect.getDBConnection();
		
		setTitle("Daily Attendance");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Attendance.class.getResource("/calendar-tasks-icon.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 862, 606);
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
		panel_2.setBounds(10, 103, 834, 455);
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
						
						FileInputStream fis = new FileInputStream(selectedFile);
						XSSFWorkbook workbook= new XSSFWorkbook(fis);
						
						XSSFSheet sheet = workbook.getSheetAt(0);
						
						//Iterate on rows
						Iterator<org.apache.poi.ss.usermodel.Row> rowIt = sheet.iterator();
						
						while(rowIt.hasNext()) {
							
							
							org.apache.poi.ss.usermodel.Row row = rowIt.next();
							
							//iterate on cells for the current row
							Iterator<Cell> cellIterator = row.cellIterator();
							
							int c=1;
							
							while (cellIterator.hasNext()) {
								Cell cell = cellIterator.next();
								//System.out.print(cell.toString() + ";");
								
								if (c == 1 ) {
									String eid = cell.toString();
									System.out.print("eid = "+eid+";");
									attend.setEid(eid);
									c+=1;
								}
								else if ( c == 2 ) {
									String sTime = cell.toString();
									System.out.print("sTime = "+sTime+";");
									c+=1;
								}
								else if ( c == 3 ) {
									String eTime = cell.toString();
									System.out.print("eTime = "+eTime+";");
									c+=1;
								}
								else if (c == 4 ) {
									String whours = cell.toString();
									System.out.print("whours = "+whours+";");
									c+=1;
								}
								else if (c == 5 ) {
									String otHours = cell.toString();
									System.out.print("otHours = "+otHours+";");
									attend.setOTHours(Double.valueOf(otHours));
									c+=1;
								}
								else if (c == 6 ) {
									String present = cell.toString();
									System.out.print("present = "+present+";");
									c+=1;
								}
								
							}
							System.out.println(attend.getEid()+":"+attend.getOTHours());
							
							AttendService attService = new AttendService();
							attService.addAttend(attend);
							
							
							
							System.out.println("");
							
						}
						
						
					refreshAttendanceTable();
					workbook.close();	
					fis.close();	
					
						
						
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
					JasperReport jasperReport = JasperCompileManager.compileReport("C:\\Users\\VS\\Documents\\Projects\\Management System\\user.jrxml");
					JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null , connection);
					JasperViewer.viewReport(jasperPrint);
					//JasperExportManager.exportReportToPdfFile(jasperPrint ,"C:\\Users\\VS\\Desktop\\monthlyAttendanceReport.pdf" );
					
				} catch (Exception e1) {
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
		scrollPane.setBounds(97, 112, 636, 314);
		panel_2.add(scrollPane);
		
		table = new JTable();
		table.setShowVerticalLines(false);
		table.setRowSelectionAllowed(false);
		table.setRowMargin(3);
		table.setIntercellSpacing(new Dimension(25, 2));
		table.setGridColor(Color.LIGHT_GRAY);
		table.setFocusable(false);
		table.setBounds(new Rectangle(1, 1, 0, 0));
		table.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		table.setAlignmentX(Component.RIGHT_ALIGNMENT);
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		table.setRowHeight(25);
		scrollPane.setViewportView(table);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 87, 810, 14);
		panel_2.add(separator);
		
		
		refreshAttendanceTable();
	}
}
