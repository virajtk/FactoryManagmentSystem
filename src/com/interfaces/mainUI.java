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
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.Toolkit;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;

public class mainUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainUI frame = new mainUI();
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
	public mainUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(mainUI.class.getResource("/UNIClogo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1190, 604);
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
		lblCompanyLogo.setIcon(new ImageIcon(mainUI.class.getResource("/UNIClogo.png")));
		lblCompanyLogo.setBounds(12, 13, 250, 130);
		Header.add(lblCompanyLogo);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setFont(new Font("Calibri Light", Font.BOLD, 20));
		btnLogout.setBounds(1053, 99, 107, 40);
		Header.add(btnLogout);
		
		JLabel lblInfo = new JLabel("");
		lblInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				info in = new info();
				in.main(null);
			}
		});
		lblInfo.setIcon(new ImageIcon(mainUI.class.getResource("/javax/swing/plaf/metal/icons/ocean/info.png")));
		lblInfo.setBounds(998, 103, 32, 40);
		Header.add(lblInfo);
		
		JLabel lblUnicFactoryManagment = new JLabel("UNIC Factory Managment System");
		lblUnicFactoryManagment.setHorizontalAlignment(SwingConstants.CENTER);
		lblUnicFactoryManagment.setForeground(Color.WHITE);
		lblUnicFactoryManagment.setFont(new Font("Stencil", Font.PLAIN, 35));
		lblUnicFactoryManagment.setBounds(274, 29, 629, 70);
		Header.add(lblUnicFactoryManagment);
		
		JLabel lblProfilepic = new JLabel("ProfilePic");
		lblProfilepic.setBounds(1053, 13, 107, 73);
		Header.add(lblProfilepic);
		
		JPanel body = new JPanel();
		body.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		body.setBackground(SystemColor.scrollbar);
		body.setBounds(0, 153, 1172, 406);
		contentPane.add(body);
		body.setLayout(null);
		
		JButton btnDashBoard = new JButton("Dash Board");
		btnDashBoard.setSelectedIcon(new ImageIcon(mainUI.class.getResource("/javax/swing/plaf/basic/icons/image-delayed.png")));
		btnDashBoard.setBackground(new Color(0, 0, 51));
		btnDashBoard.setForeground(Color.WHITE);
		btnDashBoard.setFont(new Font("Berlin Sans FB", Font.PLAIN, 25));
		btnDashBoard.setBounds(12, 13, 224, 85);
		body.add(btnDashBoard);
		
		JButton btnOrder = new JButton("Order");
		btnOrder.setSelectedIcon(new ImageIcon(mainUI.class.getResource("/javax/swing/plaf/basic/icons/image-delayed.png")));
		btnOrder.setBackground(new Color(0, 0, 51));
		btnOrder.setForeground(Color.WHITE);
		btnOrder.setFont(new Font("Berlin Sans FB", Font.PLAIN, 25));
		btnOrder.setBounds(12, 111, 224, 85);
		body.add(btnOrder);
		
		JPanel Content = new JPanel();
		Content.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Content.setBounds(248, 13, 676, 379);
		body.add(Content);
		
		JButton btnStore = new JButton("Store");
		btnStore.setSelectedIcon(new ImageIcon(mainUI.class.getResource("/javax/swing/plaf/basic/icons/image-delayed.png")));
		btnStore.setBackground(new Color(0, 0, 51));
		btnStore.setForeground(Color.WHITE);
		btnStore.setFont(new Font("Berlin Sans FB", Font.PLAIN, 25));
		btnStore.setBounds(12, 209, 224, 85);
		body.add(btnStore);
		
		JButton btnSupply = new JButton("Supply");
		btnSupply.setSelectedIcon(new ImageIcon(mainUI.class.getResource("/javax/swing/plaf/basic/icons/image-delayed.png")));
		btnSupply.setBackground(new Color(0, 0, 51));
		btnSupply.setForeground(Color.WHITE);
		btnSupply.setFont(new Font("Berlin Sans FB", Font.PLAIN, 25));
		btnSupply.setBounds(12, 307, 224, 85);
		body.add(btnSupply);
		
		JButton btnEmployee = new JButton("Employee");
		btnEmployee.setIcon(new ImageIcon(mainUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/NewFolder.gif")));
		btnEmployee.setBackground(new Color(0, 0, 51));
		btnEmployee.setForeground(Color.WHITE);
		btnEmployee.setFont(new Font("Berlin Sans FB", Font.PLAIN, 25));
		btnEmployee.setBounds(936, 13, 224, 85);
		body.add(btnEmployee);
		
		JButton btnProduction = new JButton("Production");
		btnProduction.setSelectedIcon(new ImageIcon(mainUI.class.getResource("/javax/swing/plaf/basic/icons/image-delayed.png")));
		btnProduction.setBackground(new Color(0, 0, 51));
		btnProduction.setForeground(Color.WHITE);
		btnProduction.setFont(new Font("Berlin Sans FB", Font.PLAIN, 25));
		btnProduction.setBounds(936, 111, 224, 85);
		body.add(btnProduction);
		
		JButton btnTransport = new JButton("Transport");
		btnTransport.setSelectedIcon(new ImageIcon(mainUI.class.getResource("/javax/swing/plaf/basic/icons/image-delayed.png")));
		btnTransport.setBackground(new Color(0, 0, 51));
		btnTransport.setForeground(Color.WHITE);
		btnTransport.setFont(new Font("Berlin Sans FB", Font.PLAIN, 25));
		btnTransport.setBounds(936, 209, 224, 85);
		body.add(btnTransport);
		
		JButton btnSalary = new JButton("Salary");
		btnSalary.setSelectedIcon(new ImageIcon(mainUI.class.getResource("/javax/swing/plaf/basic/icons/image-delayed.png")));
		btnSalary.setBackground(new Color(0, 0, 51));
		btnSalary.setForeground(Color.WHITE);
		btnSalary.setFont(new Font("Berlin Sans FB", Font.PLAIN, 25));
		btnSalary.setBounds(936, 307, 224, 85);
		body.add(btnSalary);
		
		
	}
}
