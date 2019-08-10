package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DbConnect {
	public static Connection getDBConnection() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		Connection connection;
		String dbURL = "jdbc:mysql://localhost:3306/ordermanagement" ;
		String dbDriver = "com.mysql.jdbc.Driver";
		String dbUsername = "root" ;
		String dbPassword = "tharindu123";
		
		Class.forName(dbDriver).newInstance();
		connection = DriverManager.getConnection(dbURL,dbUsername , dbPassword);

		if (connection == null || connection.isClosed() ) {
			JOptionPane.showMessageDialog(null, "Error Connection");
		}
		
		//JOptionPane.showMessageDialog(null, "Connected");

		
		return connection ;
	}
	

}

