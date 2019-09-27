package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.model.Attend;
import com.util.DbConnect;

public class AttendService {
	
	private static Connection connection ;
	private PreparedStatement preStatement ;

	public void addAttend(Attend attend) {
		try {
			connection = DbConnect.getDBConnection();
			
			String eid = attend.getEid();
			int existUser = 0;
			
			
			String findEID = "SELECT * FROM attendance";
			PreparedStatement pst = connection.prepareStatement(findEID);
			ResultSet rs = pst.executeQuery();
			
			while ( rs.next() ) {
				
				String str = rs.getString("employeeID");
				
				
				if (eid.equals(str)) {
					existUser = 1;
					String attrendDays = rs.getString("attendDays");
					String otHours = rs.getString("OTHours");
					
					int updatedAttend = Integer.parseInt(attrendDays) + 1;
					double updatedOTHours = Double.parseDouble(otHours) + attend.getOTHours();
					
					String updateAttend = "UPDATE attendance SET attendDays = ? , OTHours = ?  WHERE (employeeID = '"+str+"')";
					PreparedStatement psst = connection.prepareStatement(updateAttend);
					psst.setInt(1, updatedAttend);
					psst.setDouble(2, updatedOTHours);
					
					psst.executeUpdate() ; 			//execute query
					
					System.out.println("Attendance updated Sucessfully!");
					break;
				}
			}
			
			if (existUser == 0 ) {
				
				int att = 1;
				
				String addUserQuery = "INSERT INTO attendance(employeeID,attendDays, OTHours) VALUES (?,?,?)";
				
				preStatement = connection.prepareStatement(addUserQuery);
				preStatement.setString(1, attend.getEid());
				preStatement.setInt(2, att); 
				preStatement.setDouble(3, attend.getOTHours());
				
				
				preStatement.executeUpdate();  //execute query 
				
				System.out.println("Attendance Added Sucessfully!");
			}
			
			
		}catch(SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			
		}finally {
			try {
				if (preStatement != null) {
					preStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			}catch(SQLException e) {
				
			}
			
		}

	}
	
	

}
