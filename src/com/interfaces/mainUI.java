package com.interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.model.User;
import com.util.DbConnect;

import net.proteanit.sql.DbUtils;

import javax.swing.border.SoftBevelBorder;
import java.awt.Toolkit;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class mainUI extends JFrame {

	private JPanel contentPane;
	JLabel lblDay;
	JLabel lblTime;
	JLabel lblImage;
	private JPanel panelOrder;
	private JPanel panelDashBoard;
	private JPanel panelEmployee;
	private JButton btnAddWorker;
	private JButton btnManageUser;
	private JButton btnAttendance;
	private JPanel panelTransport;
	private JPanel panelStore;
	private JPanel panelProduction;
	private JPanel panelSupply;
	private JPanel panelSalary;
	private JTable tableOrders;
	Connection connection = null;
	private JScrollPane scrollPane;
	private JButton btnDashBoard;
	private JButton btnOrder;
	private JButton btnStore;
	private JButton btnSupply;
	private JButton btnEmployee;
	private JButton btnProduction;
	private JButton btnTransport;
	private JButton btnSalary;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainUI frame = new mainUI("E0008");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace(); 
				}
			}
		});
	}
	
	public void clock() {
		
		Thread threadClock = new Thread() {
			
			public void run(){
				try {
					while(true) {
						Calendar calender = new GregorianCalendar();	
						
					int day = calender.get(Calendar.DAY_OF_MONTH);
					int month = calender.get(Calendar.MONTH);
					int year = calender.get(Calendar.YEAR);
					
					int second = calender.get(Calendar.SECOND);
					int minute = calender.get(Calendar.MINUTE);
					int hour = calender.get(Calendar.HOUR);
					
					lblDay.setText(" "+day+" , "+(month+1)+" , "+year);
					lblTime.setText(" "+hour+" : "+minute+" : "+second);
					
					
					sleep(1000);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		};
		
		threadClock.start();
		
	}
	
	public void showTableOrders() {
		
		try {
			
			String query = "SELECT orderID,productID,dayOfNeed,quantity,remarks FROM unic.order WHERE remarks = 'Processing'";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			tableOrders.setModel(DbUtils.resultSetToTableModel(rs));
			
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
	public mainUI(String EID_) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		
		
		connection = DbConnect.getDBConnection();
		User logUser = new User();
		
try {
			
			String query = "SELECT * FROM user_main WHERE EID = '"+EID_+"' ";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				logUser.setEID(rs.getString("EID"));
				logUser.setUsername(rs.getString("username"));
				logUser.setRole(rs.getString("Role"));
				
			}
			
			pst.close();
			rs.close();
			
		}catch (Exception e  ) {
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		
		
		
		setResizable(false);
		//String eid = "E0001";
		//String logUserName = "viraj";
		//String logUserRole = "Admin";
		setTitle("Main Menu");
		setIconImage(Toolkit.getDefaultToolkit().getImage(mainUI.class.getResource("/icons8-home-50.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1176, 613);
		this.setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(new Color(0, 0, 102));
		menuBar.setBackground(new Color(255, 239, 213));
		menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		setJMenuBar(menuBar);
		
		JMenu mnUser = new JMenu("User");
		mnUser.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(mnUser);
		
		JMenuItem mntmProfile = new JMenuItem("Profile");
		mntmProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Profile profile = null;
				try {
					profile = new Profile(logUser.getEID());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e1) {
					
					e1.printStackTrace();
				}
				profile.setVisible(true);
				
			}
		});
		mntmProfile.setFont(new Font("Segoe UI", Font.PLAIN, 19));
		mnUser.add(mntmProfile);
		
		JSeparator separator_2 = new JSeparator();
		mnUser.add(separator_2);
		
		JMenuItem mntmAddWorker = new JMenuItem("Add Worker");
		mntmAddWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AddWorker ad = new AddWorker();
					ad.setVisible(true);
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		mntmAddWorker.setFont(new Font("Segoe UI", Font.PLAIN, 19));
		mnUser.add(mntmAddWorker);
		
		JMenuItem mntmManageEmployee = new JMenuItem("Manage Employee");
		mntmManageEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ManageUser mu = new ManageUser();
					mu.setVisible(true);
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		mntmManageEmployee.setFont(new Font("Segoe UI", Font.PLAIN, 19));
		mnUser.add(mntmManageEmployee);
		
		JSeparator separator = new JSeparator();
		mnUser.add(separator);
		
		JMenuItem mntmLogOut = new JMenuItem("Log Out");
		mntmLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					Login_S login = new Login_S();
					login.main(null);;
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		mntmLogOut.setFont(new Font("Segoe UI", Font.PLAIN, 19));
		mnUser.add(mntmLogOut);
		
		JSeparator separator_1 = new JSeparator();
		mnUser.add(separator_1);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(JFrame.EXIT_ON_CLOSE);
			}
		});
		mntmExit.setFont(new Font("Segoe UI", Font.PLAIN, 19));
		mnUser.add(mntmExit);
		
		JMenu mnOrder = new JMenu("Order");
		mnOrder.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(mnOrder);
		
		JMenuItem mntmManageOrders = new JMenuItem("Manage Orders");
		mntmManageOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainOrderInterface order = new MainOrderInterface();
				order.setVisible(true);
			}
		});
		mntmManageOrders.setFont(new Font("Segoe UI", Font.PLAIN, 19));
		mnOrder.add(mntmManageOrders);
		
		JMenu mnProduction = new JMenu("Production");
		mnProduction.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(mnProduction);
		
		JMenuItem mntmProduction = new JMenuItem("Production");
		mntmProduction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Production production = new Production();
					production.setVisible(true);
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		mnProduction.add(mntmProduction);
		
		JMenuItem mntmShifting = new JMenuItem("Shifting");
		mnProduction.add(mntmShifting);
		
		JMenu mnStore = new JMenu("Store");
		mnStore.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(mnStore);
		
		JMenuItem mntmProducts = new JMenuItem("Products");
		mntmProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StockMain product = new StockMain();
				product.setVisible(true);
			}
		});
		mnStore.add(mntmProducts);
		
		JMenuItem mntmStore = new JMenuItem("Store");
		mntmStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StoreQuantityDisplay store = new StoreQuantityDisplay();
				store.setVisible(true);
			}
		});
		mnStore.add(mntmStore);
		
		JMenu mnSupply = new JMenu("Supply");
		mnSupply.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(mnSupply);
		
		JMenuItem mntmSupplier = new JMenuItem("Supplier");
		mntmSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddS se = new AddS();
				se.setVisible(true);
			}
		});
		mnSupply.add(mntmSupplier);
		
		JMenuItem mntmRawMaterial = new JMenuItem("Raw Material");
		mntmRawMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Rm r = new Rm();
				r.setVisible(true);
			}
		});
		mnSupply.add(mntmRawMaterial);
		
		JMenuItem mntmSupply = new JMenuItem("Supply");
		mntmSupply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddNewRawM supply = new AddNewRawM();
				supply.setVisible(true);
			}
		});
		mnSupply.add(mntmSupply);
		
		JMenu mnTransport = new JMenu("Transport");
		mnTransport.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(mnTransport);
		
		JMenuItem mntmVehicle = new JMenuItem("Vehicle");
		mntmVehicle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vehical ve=new Vehical();
				ve.setVisible(true);
			}
		});
		mnTransport.add(mntmVehicle);
		
		JMenuItem mntmTransport = new JMenuItem("Transport");
		mntmTransport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Transports trns=new Transports();
				trns.setVisible(true);
			}
		});
		mnTransport.add(mntmTransport);
		
		JMenuItem mntmGenerateDailyRouts = new JMenuItem("Generate Daily Routs");
		mnTransport.add(mntmGenerateDailyRouts);
		
		JMenu mnSalary = new JMenu("Salary");
		mnSalary.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(mnSalary);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Manage Schema");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ManagersSchema manageSchema = new ManagersSchema();
					manageSchema.setVisible(true);
					}catch (Exception e1) {
						e1.printStackTrace();
					}
			}
		});
		mnSalary.add(mntmNewMenuItem);
		
		JMenuItem mntmGenerateReports = new JMenuItem("Generate Reports");
		mntmGenerateReports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		mnSalary.add(mntmGenerateReports);
		
		JMenu mnAbout = new JMenu("About");
		mnAbout.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(mnAbout);
		
		JMenuItem mntmSystemInfo = new JMenuItem("System Info");
		mntmSystemInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				info in = new info();
				in.main(null);
			}
		});
		mntmSystemInfo.setFont(new Font("Segoe UI", Font.PLAIN, 19));
		mnAbout.add(mntmSystemInfo);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel Header = new JPanel();
		Header.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Header.setBackground(SystemColor.textHighlight);
		Header.setBounds(0, 0, 1172, 152);
		contentPane.add(Header);
		Header.setLayout(null);
		
		JLabel lblCompanyLogo = new JLabel("");
		lblCompanyLogo.setBounds(12, 13, 207, 130);
		lblCompanyLogo.setIcon(new ImageIcon(mainUI.class.getResource("/UNIC logo.png")));
		Header.add(lblCompanyLogo);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					Login_S login = new Login_S();
					login.main(null);;
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
				
				
			}
		});
		btnLogout.setBounds(946, 104, 107, 34);
		btnLogout.setFocusPainted(false);
		btnLogout.setForeground(SystemColor.text);
		btnLogout.setBackground(new Color(153, 0, 0));
		btnLogout.setFont(new Font("Calibri Light", Font.BOLD, 20));
		Header.add(btnLogout);
		
		JLabel lblInfo = new JLabel("");
		lblInfo.setBounds(865, 13, 50, 40);
		lblInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				info in = new info();
				in.main(null);
			}
		});
		lblInfo.setIcon(new ImageIcon(mainUI.class.getResource("/icons8-about-50.png")));
		Header.add(lblInfo);
		
		JLabel lblUnicFactoryManagment = new JLabel("UNIC Factory Managment System");
		lblUnicFactoryManagment.setBounds(232, 11, 639, 132);
		lblUnicFactoryManagment.setHorizontalAlignment(SwingConstants.CENTER);
		lblUnicFactoryManagment.setForeground(Color.WHITE);
		lblUnicFactoryManagment.setFont(new Font("Stencil", Font.PLAIN, 35));
		Header.add(lblUnicFactoryManagment);
		
		JButton btnLogUser = new JButton(logUser.getUsername());
		btnLogUser.setBounds(923, 13, 130, 34);
		btnLogUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Profile profile = null;
				dispose();
				try {
					profile = new Profile(logUser.getEID());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e1) {
					
					e1.printStackTrace();
				}
				profile.setVisible(true);
			}
		});
		btnLogUser.setForeground(Color.WHITE);
		btnLogUser.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnLogUser.setFocusPainted(false);
		btnLogUser.setBackground(new Color(210, 105, 30));
		Header.add(btnLogUser);
		
		JLabel lblUserRole = new JLabel(logUser.getRole());
		lblUserRole.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUserRole.setBounds(923, 53, 130, 24);
		lblUserRole.setForeground(new Color(255, 255, 255));
		lblUserRole.setBackground(new Color(255, 165, 0));
		lblUserRole.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserRole.setBorder(new LineBorder(new Color(0, 0, 0)));
		Header.add(lblUserRole);
		
		JPanel panel = new JPanel();
		panel.setBounds(1063, 13, 99, 125);
		Header.add(panel);
		panel.setLayout(null);
		
		lblImage = new JLabel("");
		lblImage.setBounds(0, 0, 100, 125);
		panel.add(lblImage);
		lblImage.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		Image img7 = new ImageIcon(this.getClass().getResource("/Places-user-identity-icon.png")).getImage();
		lblImage.setIcon(new ImageIcon(img7));
		
		JPanel body = new JPanel();
		body.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		body.setBackground(new Color(255, 250, 205));
		body.setBounds(0, 153, 1172, 415);
		contentPane.add(body);
		body.setLayout(null);
		
		btnDashBoard = new JButton(" DashBoard");
		btnDashBoard.setEnabled(false);
		btnDashBoard.setIcon(new ImageIcon(mainUI.class.getResource("/Home-icon.png")));
		
		btnDashBoard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				panelDashBoard.setVisible(true);
				panelOrder.setVisible(false);
				panelEmployee.setVisible(false);
				panelStore.setVisible(false);
				panelTransport.setVisible(false);
				panelProduction.setVisible(false);
				panelSupply.setVisible(false);
				panelSalary.setVisible(false);
				
				btnOrder.setEnabled(true);
				btnDashBoard.setEnabled(false);
				btnEmployee.setEnabled(true);
				btnProduction.setEnabled(true);
				btnTransport.setEnabled(true);
				btnSupply.setEnabled(true);
				btnStore.setEnabled(true);
				btnSalary.setEnabled(true);
				
				showTableOrders();
				
			}
		});
		btnDashBoard.setSelectedIcon(new ImageIcon(mainUI.class.getResource("/javax/swing/plaf/basic/icons/image-delayed.png")));
		btnDashBoard.setBackground(new Color(0, 0, 51));
		btnDashBoard.setForeground(Color.WHITE);
		btnDashBoard.setFont(new Font("Berlin Sans FB", Font.PLAIN, 25));
		btnDashBoard.setBounds(0, 0, 248, 100);
		body.add(btnDashBoard);
		
		btnOrder = new JButton(" Order");
		btnOrder.setIcon(new ImageIcon(mainUI.class.getResource("/Order-history-icon.png")));
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelOrder.setVisible(true);
				panelDashBoard.setVisible(false);
				panelEmployee.setVisible(false);
				panelStore.setVisible(false);
				panelProduction.setVisible(false);
				panelTransport.setVisible(false);
				panelSupply.setVisible(false);
				panelSalary.setVisible(false);
				
				btnOrder.setEnabled(false);
				btnDashBoard.setEnabled(true);
				btnEmployee.setEnabled(true);
				btnProduction.setEnabled(true);
				btnTransport.setEnabled(true);
				btnSupply.setEnabled(true);
				btnStore.setEnabled(true);
				btnSalary.setEnabled(true);
			}
		});
		
		
		btnOrder.setSelectedIcon(new ImageIcon(mainUI.class.getResource("/javax/swing/plaf/basic/icons/image-delayed.png")));
		btnOrder.setBackground(new Color(0, 0, 51));
		btnOrder.setForeground(Color.WHITE);
		btnOrder.setFont(new Font("Berlin Sans FB", Font.PLAIN, 25));
		btnOrder.setBounds(0, 98, 248, 100);
		body.add(btnOrder);
		
		btnStore = new JButton(" Store");
		btnStore.setIcon(new ImageIcon(mainUI.class.getResource("/Shop-icon.png")));
		btnStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				panelStore.setVisible(true);
				panelOrder.setVisible(false);
				panelDashBoard.setVisible(false);
				panelEmployee.setVisible(false);
				panelProduction.setVisible(false);
				panelTransport.setVisible(false);
				panelSupply.setVisible(false);
				panelSalary.setVisible(false);
				
				
				btnOrder.setEnabled(true);
				btnDashBoard.setEnabled(true);
				btnEmployee.setEnabled(true);
				btnProduction.setEnabled(true);
				btnTransport.setEnabled(true);
				btnSupply.setEnabled(true);
				btnStore.setEnabled(false);
				btnSalary.setEnabled(true);
			}
		});
		btnStore.setSelectedIcon(new ImageIcon(mainUI.class.getResource("/javax/swing/plaf/basic/icons/image-delayed.png")));
		btnStore.setBackground(new Color(0, 0, 51));
		btnStore.setForeground(Color.WHITE);
		btnStore.setFont(new Font("Berlin Sans FB", Font.PLAIN, 25));
		btnStore.setBounds(0, 196, 248, 100);
		body.add(btnStore);
		
		btnSupply = new JButton("Supply");
		btnSupply.setIcon(new ImageIcon(mainUI.class.getResource("/User-Group-icon.png")));
		btnSupply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panelSupply.setVisible(true);
				panelEmployee.setVisible(false);
				panelDashBoard.setVisible(false);
				panelOrder.setVisible(false);
				panelStore.setVisible(false);
				panelProduction.setVisible(false);
				panelTransport.setVisible(false);
				panelSalary.setVisible(false);
				
				
				btnOrder.setEnabled(true);
				btnDashBoard.setEnabled(true);
				btnEmployee.setEnabled(true);
				btnProduction.setEnabled(true);
				btnTransport.setEnabled(true);
				btnSupply.setEnabled(false);
				btnStore.setEnabled(true);
				btnSalary.setEnabled(true);
				
			}
		});
		btnSupply.setSelectedIcon(new ImageIcon(mainUI.class.getResource("/javax/swing/plaf/basic/icons/image-delayed.png")));
		btnSupply.setBackground(new Color(0, 0, 51));
		btnSupply.setForeground(Color.WHITE);
		btnSupply.setFont(new Font("Berlin Sans FB", Font.PLAIN, 25));
		btnSupply.setBounds(0, 296, 248, 100);
		body.add(btnSupply);
		
		btnEmployee = new JButton(" Employee");
		
		btnEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				panelEmployee.setVisible(true);
				panelDashBoard.setVisible(false);
				panelOrder.setVisible(false);
				panelStore.setVisible(false);
				panelProduction.setVisible(false);
				panelTransport.setVisible(false);
				panelSupply.setVisible(false);
				panelSalary.setVisible(false);
				
				btnOrder.setEnabled(true);
				btnDashBoard.setEnabled(true);
				btnEmployee.setEnabled(false);
				btnProduction.setEnabled(true);
				btnTransport.setEnabled(true);
				btnSupply.setEnabled(true);
				btnStore.setEnabled(true);
				btnSalary.setEnabled(true);
			}
		});
		btnEmployee.setIcon(null);
		btnEmployee.setBackground(new Color(0, 0, 51));
		btnEmployee.setForeground(Color.WHITE);
		btnEmployee.setFont(new Font("Berlin Sans FB", Font.PLAIN, 25));
		btnEmployee.setBounds(924, 0, 248, 100);
		btnEmployee.setIcon(new ImageIcon(mainUI.class.getResource("/user-settings-icon.png")));
		body.add(btnEmployee);
		
		btnProduction = new JButton(" Production");
		btnProduction.setIcon(new ImageIcon(mainUI.class.getResource("/coal-power-plant-icon.png")));
		btnProduction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				panelProduction.setVisible(true);
				panelEmployee.setVisible(false);
				panelDashBoard.setVisible(false);
				panelOrder.setVisible(false);
				panelStore.setVisible(false);
				panelTransport.setVisible(false);
				panelSupply.setVisible(false);
				panelSalary.setVisible(false);
				
				btnOrder.setEnabled(true);
				btnDashBoard.setEnabled(true);
				btnEmployee.setEnabled(true);
				btnProduction.setEnabled(false);
				btnTransport.setEnabled(true);
				btnSupply.setEnabled(true);
				btnStore.setEnabled(true);
				btnSalary.setEnabled(true);
				
			}
		});
		btnProduction.setSelectedIcon(new ImageIcon(mainUI.class.getResource("/javax/swing/plaf/basic/icons/image-delayed.png")));
		btnProduction.setBackground(new Color(0, 0, 51));
		btnProduction.setForeground(Color.WHITE);
		btnProduction.setFont(new Font("Berlin Sans FB", Font.PLAIN, 25));
		btnProduction.setBounds(924, 98, 248, 100);
		body.add(btnProduction);
		
		btnTransport = new JButton(" Transport");
		btnTransport.setIcon(new ImageIcon(mainUI.class.getResource("/data-transport-icon.png")));
		btnTransport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				panelTransport.setVisible(true);
				panelDashBoard.setVisible(false);
				panelEmployee.setVisible(false);
				panelOrder.setVisible(false);
				panelProduction.setVisible(false);
				panelStore.setVisible(false);
				panelSupply.setVisible(false);
				panelSalary.setVisible(false);
				
				btnOrder.setEnabled(true);
				btnDashBoard.setEnabled(true);
				btnEmployee.setEnabled(true);
				btnProduction.setEnabled(true);
				btnTransport.setEnabled(false);
				btnSupply.setEnabled(true);
				btnStore.setEnabled(true);
				btnSalary.setEnabled(true);
				
			}
		});
		btnTransport.setSelectedIcon(new ImageIcon(mainUI.class.getResource("/javax/swing/plaf/basic/icons/image-delayed.png")));
		btnTransport.setBackground(new Color(0, 0, 51));
		btnTransport.setForeground(Color.WHITE);
		btnTransport.setFont(new Font("Berlin Sans FB", Font.PLAIN, 25));
		btnTransport.setBounds(924, 196, 248, 100);
		body.add(btnTransport);
		
		btnSalary = new JButton(" Salary");
		btnSalary.setIcon(new ImageIcon(mainUI.class.getResource("/payment-icon.png")));
		btnSalary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panelSalary.setVisible(true);
				panelTransport.setVisible(false);
				panelDashBoard.setVisible(false);
				panelEmployee.setVisible(false);
				panelOrder.setVisible(false);
				panelProduction.setVisible(false);
				panelStore.setVisible(false);
				panelSupply.setVisible(false);
				
				btnOrder.setEnabled(true);
				btnDashBoard.setEnabled(true);
				btnEmployee.setEnabled(true);
				btnProduction.setEnabled(true);
				btnTransport.setEnabled(true);
				btnSupply.setEnabled(true);
				btnStore.setEnabled(true);
				btnSalary.setEnabled(false);
				
			}
		});
		btnSalary.setSelectedIcon(new ImageIcon(mainUI.class.getResource("/javax/swing/plaf/basic/icons/image-delayed.png")));
		btnSalary.setBackground(new Color(0, 0, 51));
		btnSalary.setForeground(Color.WHITE);
		btnSalary.setFont(new Font("Berlin Sans FB", Font.PLAIN, 25));
		btnSalary.setBounds(924, 295, 248, 100);
		body.add(btnSalary);
		
		JPanel Content = new JPanel();
		Content.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Content.setBounds(248, 0, 676, 396);
		body.add(Content);
		Content.setLayout(null);
		
		panelDashBoard = new JPanel();
		panelDashBoard.setBackground(new Color(255, 250, 205));
		panelDashBoard.setBounds(0, 0, 676, 396);
		Content.add(panelDashBoard);
		panelDashBoard.setLayout(null);
		
		lblDay = new JLabel("");
		lblDay.setBounds(73, 40, 275, 48);
		lblDay.setHorizontalAlignment(SwingConstants.LEFT);
		lblDay.setFont(new Font("Sui Generis", Font.PLAIN, 25));
		panelDashBoard.add(lblDay);
		
		lblTime = new JLabel("");
		lblTime.setBounds(360, 40, 275, 48);
		lblTime.setHorizontalAlignment(SwingConstants.LEFT);
		lblTime.setFont(new Font("Sui Generis", Font.PLAIN, 25));
		panelDashBoard.add(lblTime);
		
		JLabel lblDate1 = new JLabel("Date");
		lblDate1.setForeground(new Color(210, 105, 30));
		lblDate1.setFont(new Font("Sui Generis", Font.PLAIN, 18));
		lblDate1.setBounds(73, 13, 145, 28);
		panelDashBoard.add(lblDate1);
		
		JLabel lblTime_1 = new JLabel("Time");
		lblTime_1.setForeground(new Color(210, 105, 30));
		lblTime_1.setFont(new Font("Sui Generis", Font.PLAIN, 18));
		lblTime_1.setBounds(360, 13, 145, 28);
		panelDashBoard.add(lblTime_1);
		
		scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Tahoma", Font.BOLD, 18));
		scrollPane.setBounds(74, 110, 540, 240);
		panelDashBoard.add(scrollPane);
		
		tableOrders = new JTable();
		tableOrders.setRowHeight(25);
		tableOrders.setRowSelectionAllowed(false);
		tableOrders.setFocusable(false);
		tableOrders.setFont(new Font("Tahoma", Font.PLAIN, 16));
		scrollPane.setViewportView(tableOrders);
		
		panelEmployee = new JPanel();
		panelEmployee.setBackground(new Color(255, 250, 205));
		panelEmployee.setVisible(false);
		
		panelOrder = new JPanel();
		panelOrder.setBackground(new Color(255, 250, 205));
		panelOrder.setVisible(false);
		panelOrder.setBounds(0, 0, 676, 396);
		Content.add(panelOrder);
		panelOrder.setLayout(null);
		
		JButton btnOrderManagment = new JButton("Order Managment");
		btnOrderManagment.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnOrderManagment.setForeground(SystemColor.text);
		btnOrderManagment.setBackground(new Color(210, 105, 30));
		btnOrderManagment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				MainOrderInterface order = new MainOrderInterface();
				order.setVisible(true);
				//dispose();
				
			}
		});
		btnOrderManagment.setBounds(219, 155, 230, 47);
		panelOrder.add(btnOrderManagment);
		panelEmployee.setBounds(0, 0, 676, 396);
		Content.add(panelEmployee);
		panelEmployee.setLayout(null);
		
		btnAddWorker = new JButton("Add Worker");
		btnAddWorker.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnAddWorker.setForeground(SystemColor.text);
		btnAddWorker.setBackground(new Color(210, 105, 30));
		btnAddWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					//dispose();
					AddWorker addWorker = new AddWorker();
					addWorker.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		btnAddWorker.setBounds(240, 95, 201, 43);
		panelEmployee.add(btnAddWorker);
		
		btnManageUser = new JButton("Manage User");
		btnManageUser.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnManageUser.setForeground(SystemColor.text);
		btnManageUser.setBackground(new Color(210, 105, 30));
		btnManageUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				ManageUser manageUser = new ManageUser();
				manageUser.setVisible(true);
				//dispose();
				}catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		btnManageUser.setBounds(240, 173, 201, 43);
		panelEmployee.add(btnManageUser);
		
		btnAttendance = new JButton("Attendance");
		btnAttendance.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnAttendance.setForeground(SystemColor.text);
		btnAttendance.setBackground(new Color(210, 105, 30));
		btnAttendance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Attendance attendance;
				try {
					attendance = new Attendance();
					attendance.setVisible(true);
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnAttendance.setBounds(240, 253, 201, 43);
		panelEmployee.add(btnAttendance);
		
		panelTransport = new JPanel();
		panelTransport.setBackground(new Color(255, 250, 205));
		panelTransport.setVisible(false);
		panelTransport.setLayout(null);
		panelTransport.setBounds(0, 0, 676, 396);
		Content.add(panelTransport);
		
		JButton buttonVehicle = new JButton("Vehicle");
		buttonVehicle.setForeground(SystemColor.text);
		buttonVehicle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Vehical ve=new Vehical();
				ve.setVisible(true);
				//dispose();
			}
		});
		buttonVehicle.setFont(new Font("Tahoma", Font.BOLD, 18));
		buttonVehicle.setBackground(new Color(210, 105, 30));
		buttonVehicle.setBounds(220, 62, 245, 45);
		panelTransport.add(buttonVehicle);
		
		JButton buttonTransport = new JButton("Transport");
		buttonTransport.setForeground(SystemColor.text);
		buttonTransport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Transports trns=new Transports();
				trns.setVisible(true);
				//dispose();
				
			}
		});
		buttonTransport.setFont(new Font("Tahoma", Font.BOLD, 18));
		buttonTransport.setBackground(new Color(210, 105, 30));
		buttonTransport.setBounds(220, 170, 245, 45);
		panelTransport.add(buttonTransport);
		
		JButton buttonGenerateReport = new JButton("Generate Daily Routs");
		buttonGenerateReport.setForeground(SystemColor.text);
		buttonGenerateReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		buttonGenerateReport.setFont(new Font("Tahoma", Font.BOLD, 18));
		buttonGenerateReport.setBackground(new Color(210, 105, 30));
		buttonGenerateReport.setBounds(220, 280, 245, 45);
		panelTransport.add(buttonGenerateReport);
		
		panelStore = new JPanel();
		panelStore.setBackground(new Color(255, 250, 205));
		panelStore.setVisible(false);
		panelStore.setBounds(0, 0, 676, 396);
		Content.add(panelStore);
		panelStore.setLayout(null);
		
		JButton btnProduct = new JButton("Products");
		btnProduct.setBackground(new Color(210, 105, 30));
		btnProduct.setForeground(SystemColor.text);
		btnProduct.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				StockMain product = new StockMain();
				product.setVisible(true);
				
			}
		});
		btnProduct.setBounds(232, 123, 220, 39);
		panelStore.add(btnProduct);
		
		JButton btnStore_1 = new JButton("Store");
		btnStore_1.setBackground(new Color(210, 105, 30));
		btnStore_1.setForeground(SystemColor.text);
		btnStore_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnStore_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				StoreQuantityDisplay store = new StoreQuantityDisplay();
				store.setVisible(true);
				
			}
		});
		btnStore_1.setBounds(232, 193, 220, 36);
		panelStore.add(btnStore_1);
		
		panelProduction = new JPanel();
		panelProduction.setBackground(new Color(255, 250, 205));
		panelProduction.setVisible(false);
		panelProduction.setBounds(0, 0, 676, 396);
		Content.add(panelProduction);
		panelProduction.setLayout(null);
		
		JButton btnProductionMng = new JButton("Production");
		btnProductionMng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Production production = new Production();
					production.setVisible(true);
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
					
					e.printStackTrace();
				}
				
				
			}
		});
		btnProductionMng.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnProductionMng.setBackground(new Color(210, 105, 30));
		btnProductionMng.setForeground(SystemColor.text);
		btnProductionMng.setBounds(236, 108, 229, 38);
		panelProduction.add(btnProductionMng);
		
		JButton btnShifting = new JButton("Shifting");
		btnShifting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Shifting shifting = new Shifting();
				shifting.setVisible(true);
				
			}
		});
		btnShifting.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnShifting.setBackground(new Color(210, 105, 30));
		btnShifting.setForeground(SystemColor.text);
		btnShifting.setBounds(236, 180, 229, 40);
		panelProduction.add(btnShifting);
		
		panelSupply = new JPanel();
		panelSupply.setBackground(new Color(255, 250, 205));
		panelSupply.setVisible(false);
		panelSupply.setBounds(0, 0, 676, 396);
		Content.add(panelSupply);
		panelSupply.setLayout(null);
		
		JButton btnSupplier = new JButton("Supplier");
		btnSupplier.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSupplier.setBackground(new Color(210, 105, 30));
		btnSupplier.setForeground(SystemColor.text);
		btnSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AddS se = new AddS();
				se.setVisible(true);
				
			}
		});
		btnSupplier.setBounds(233, 178, 219, 41);
		panelSupply.add(btnSupplier);
		
		JButton btnRawMaterial = new JButton("Raw Material");
		btnRawMaterial.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnRawMaterial.setBackground(new Color(210, 105, 30));
		btnRawMaterial.setForeground(SystemColor.text);
		btnRawMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Rm r = new Rm();
				r.setVisible(true);
				
			}
		});
		btnRawMaterial.setBounds(233, 98, 219, 41);
		panelSupply.add(btnRawMaterial);
		
		JButton btnSupply_1 = new JButton("Supply");
		btnSupply_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSupply_1.setBackground(new Color(210, 105, 30));
		btnSupply_1.setForeground(SystemColor.text);
		btnSupply_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AddNewRawM supply = new AddNewRawM();
				supply.setVisible(true);
				
			}
		});
		btnSupply_1.setBounds(233, 260, 219, 41);
		panelSupply.add(btnSupply_1);
		
		panelSalary = new JPanel();
		panelSalary.setBackground(new Color(255, 250, 205));
		panelSalary.setVisible(false);
		panelSalary.setBounds(0, 0, 676, 396);
		Content.add(panelSalary);
		panelSalary.setLayout(null);
		
		JButton btnManageSchema = new JButton("Manage Schema");
		btnManageSchema.setForeground(SystemColor.text);
		btnManageSchema.setBackground(new Color(210, 105, 30));
		btnManageSchema.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnManageSchema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				ManagersSchema manageSchema = new ManagersSchema();
				manageSchema.setVisible(true);
				}catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnManageSchema.setBounds(235, 138, 214, 41);
		panelSalary.add(btnManageSchema);
		
		JButton btnGenerateReports = new JButton("Generate Reports");
		btnGenerateReports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					String query = "SELECT distinct EID,OTHours,OTRate,BasicSalary,BankAccountNo,FName,LName FROM user_main u, attendance a WHERE u.EID = a.employeeID";
					PreparedStatement pst;
					pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					
					System.out.println("EID\t\tName\t\tOT Hours\tOT Rate\t\tBasic Salary\tGross\t\tEPFE\t\tDeduction\tBankAcc");
					
					while (rs.next()) {
						String Eid = rs.getString("EID");
						String name = rs.getString("FName");
						Double othours = rs.getDouble("OTHours");
						Double otrate = rs.getDouble("OTRate");
						Double basicSalary = rs.getDouble("BasicSalary");
						
						Double grossPay = basicSalary + (otrate*othours);
						Double EPFE = grossPay * 0.08;
						Double deduction = grossPay - EPFE;
						String bankAcc = rs.getString("BankAccountNo");
						
						String insertQuery = "INSERT INTO unic.schema_employee(eID,grossPay,deduction,EPFE)VALUES(?,?,?,?)";
						PreparedStatement psst = connection.prepareStatement(insertQuery);
						psst.setString(1, Eid);
						psst.setDouble(2, grossPay);
						psst.setDouble(3, deduction);
						psst.setDouble(4, EPFE);
						psst.executeUpdate();
						
						
						System.out.println(Eid +"\t\t"+name+"\t\t"+ othours+"\t\t"+otrate+"\t\t"+basicSalary+"\t\t"+grossPay+"\t\t"+EPFE+"\t\t"+deduction+"\t\t"+bankAcc);
						
						
					}
					
					JOptionPane.showMessageDialog(null, "Salary Calculated and Inserted temp Table Succesfully!!!");
					
				
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				
				
				
				
			}
		});
		btnGenerateReports.setForeground(SystemColor.text);
		btnGenerateReports.setBackground(new Color(210, 105, 30));
		btnGenerateReports.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnGenerateReports.setBounds(235, 208, 214, 41);
		panelSalary.add(btnGenerateReports);
		
		clock();
		
		
		try {
			
			String query = "SELECT * FROM user_main WHERE EID = '"+EID_+"' ";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				
				
				if (rs.getBytes("proPicture") != null ) {
					
					
					byte[] imge = rs.getBytes("proPicture");
					ImageIcon image = new ImageIcon(imge);
					Image im = image.getImage();
					Image myImg = im.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
					ImageIcon newImage = new ImageIcon(myImg);
					//System.out.println(newImage);
					lblImage.setIcon(newImage);
					
					}
					else {
						//Image img7 = new ImageIcon(this.getClass().getResource("/Places-user-identity-icon.png")).getImage();
						//lblImage.setIcon(new ImageIcon(img7));
					}
				
			}
			
			pst.close();
			rs.close();
			
		}catch (Exception e  ) {
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		
		
		showTableOrders();
		
	}
}
