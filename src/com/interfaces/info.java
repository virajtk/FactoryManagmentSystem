package com.interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.Toolkit;

public class info extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					info frame = new info();
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
	public info() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(info.class.getResource("/UNIClogo.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 742, 615);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(info.class.getResource("/UNIClogo.png")));
		label.setBounds(28, 70, 226, 170);
		contentPane.add(label);
		
		JLabel lblUnicCementProducts = new JLabel("UNIC CEMENT PRODUCTS");
		lblUnicCementProducts.setHorizontalAlignment(SwingConstants.CENTER);
		lblUnicCementProducts.setForeground(SystemColor.textHighlight);
		lblUnicCementProducts.setFont(new Font("Perpetua Titling MT", Font.BOLD, 30));
		lblUnicCementProducts.setBounds(232, 68, 480, 68);
		contentPane.add(lblUnicCementProducts);
		
		JLabel lblHighQualityCement = new JLabel("High Quality Cement Blocks & Paving");
		lblHighQualityCement.setForeground(new Color(128, 0, 0));
		lblHighQualityCement.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblHighQualityCement.setHorizontalAlignment(SwingConstants.CENTER);
		lblHighQualityCement.setBounds(299, 114, 347, 43);
		contentPane.add(lblHighQualityCement);
		
		JLabel lblNewLabel = new JLabel("No ; 451/2, Kumbaloluwa,Veyangoda, Sri Lanka.");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(266, 149, 380, 35);
		contentPane.add(lblNewLabel);
		
		JLabel lblTelfax = new JLabel("Tel/Fax ; 033-2270616 , E-mail : uniccementproduct@gmail.com");
		lblTelfax.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelfax.setBounds(266, 170, 380, 43);
		contentPane.add(lblTelfax);
		
		JLabel lblNewLabel_1 = new JLabel("Registration No. W.M/1483");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(462, 197, 184, 43);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblCompany = new JLabel("Company");
		lblCompany.setFont(new Font("Haettenschweiler", Font.PLAIN, 30));
		lblCompany.setBounds(28, 13, 308, 43);
		contentPane.add(lblCompany);
		
		JLabel lblTeam = new JLabel("Team </Scorpion>");
		lblTeam.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTeam.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeam.setBounds(82, 365, 549, 115);
		contentPane.add(lblTeam);
		
		JLabel lblDevaloper = new JLabel("Devaloper");
		lblDevaloper.setFont(new Font("Haettenschweiler", Font.PLAIN, 30));
		lblDevaloper.setBounds(28, 309, 308, 43);
		contentPane.add(lblDevaloper);
	}
}
