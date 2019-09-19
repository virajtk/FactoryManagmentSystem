package com.interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Point;
import java.awt.Cursor;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JProgressBar;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Toolkit;

public class Splash extends JFrame {

	private JPanel contentPane;
	private static JProgressBar progressBar;
	private static JLabel lblProgress;
	private JLabel lblNewLabel;
	private JLabel lblUnicFactoryManagment;
	private JLabel lblImg;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
				Splash splash = new Splash();
				splash.setVisible(true);
				try {
					
					
					for (int i = 0; i <= 100; i++) {
						Thread.sleep(50);
						
						lblProgress.setText(i+"%");
						progressBar.setValue(i);
						if (i==100) {
							Login_S login = new Login_S();
							splash.setVisible(false);
							login.main(null);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			
	}

	/**
	 * Create the frame.
	 */
	public Splash() {
		setUndecorated(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Splash.class.getResource("/UNIC logo.png")));
		setResizable(false);
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 599);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 800, 600);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblProgress = new JLabel("0%");
		lblProgress.setForeground(SystemColor.text);
		lblProgress.setHorizontalAlignment(SwingConstants.CENTER);
		lblProgress.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblProgress.setBounds(12, 437, 776, 46);
		panel.add(lblProgress);
		
		progressBar = new JProgressBar();
		progressBar.setBorder(new LineBorder(new Color(0, 0, 102), 3, true));
		progressBar.setForeground(new Color(255, 153, 0));
		progressBar.setBounds(29, 486, 742, 29);
		panel.add(progressBar);
		Image img = new ImageIcon(this.getClass().getResource("/5.gif")).getImage();
		
		lblUnicFactoryManagment = new JLabel("Factory Managment System");
		lblUnicFactoryManagment.setHorizontalAlignment(SwingConstants.CENTER);
		lblUnicFactoryManagment.setForeground(SystemColor.text);
		lblUnicFactoryManagment.setFont(new Font("SF Old Republic SC", Font.BOLD, 45));
		lblUnicFactoryManagment.setBounds(29, 128, 742, 88);
		panel.add(lblUnicFactoryManagment);
		
		lblImg = new JLabel("");
		lblImg.setHorizontalAlignment(SwingConstants.CENTER);
		lblImg.setForeground(SystemColor.text);
		lblImg.setBounds(297, 13, 200, 131);
		Image img1 = new ImageIcon(this.getClass().getResource("/UNIC logo.png")).getImage();
		lblImg.setIcon(new ImageIcon(img1));
		panel.add(lblImg);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setBounds(0, 0, 800, 600);
		lblNewLabel.setIcon(new ImageIcon(img));
		panel.add(lblNewLabel);
		
	}
}
