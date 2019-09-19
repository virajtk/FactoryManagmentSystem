package com.service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.util.DbConnect;

public class CommonServices {
	
	private static Connection connection ;
	private PreparedStatement preStatement ;
	
	public ResultSet searchAndSort(String table_name,String colom_name,String inputFieldName) {
		try {
			String selectClient = "SELECT * FROM unic."+table_name+" where "+colom_name+" like '"+"%"+inputFieldName+"%"+"'";
			connection = DbConnect.getDBConnection();
			preStatement = connection.prepareStatement(selectClient);
			ResultSet resultSet = preStatement.executeQuery();
			return resultSet;
		} catch (Exception e) {
			return null;
		}
				
	}

}
