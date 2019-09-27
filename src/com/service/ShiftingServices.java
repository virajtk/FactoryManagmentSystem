package com.service;

import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.util.DbConnect;
import com.interfaces.Shifting;
import com.model.ShiftingM;



public class ShiftingServices {
	
	private static Connection connection;
	private PreparedStatement preStatement;
	
	public void AddShifting(ShiftingM shiftingM ) {
		try {
			
			
			connection = DbConnect.getDBConnection();
			String addShifting = "Insert into Shifting(shiftingID,diliveryID,shifter1,shifter2)values(?,?,?,?)";
			
			preStatement = connection.prepareStatement(addShifting);
			
			preStatement.setString(1, shiftingM.getShiftingID());
			preStatement.setString(2, shiftingM.getDiliveryID());
			preStatement.setString(3, shiftingM.getShifter1());
			preStatement.setString(4, shiftingM.getShifter2());
			
			preStatement.executeUpdate();
			JOptionPane.showMessageDialog(null,"Details are sucessfull inserted.");
			
			
		} catch (Exception e) {
			
	}
		
	
}

	public void DeleteShifting(String shiftingID) throws InstantiationException, IllegalAccessException  {
	
		try {
			
			connection = DbConnect.getDBConnection();
			
			String deleteShifting = "DELETE FROM shifting WHERE Shifting.shiftingID = ?";
			
			preStatement = connection.prepareStatement(deleteShifting);
			preStatement.setString(1, shiftingID);
			preStatement.executeUpdate();
			JOptionPane.showMessageDialog(null, shiftingID+"Sucessfully removed");
			
			
		} catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			
		}finally {
			
			try {
				
				
				if(preStatement != null);{
					
					preStatement.close();
					
				}
				
				if(connection != null) {
					
					connection.close();
				}
			
			} catch (Exception e) {
				
			}
		}
	}

	public void viewShifting() {
		
		
		
	
	}

	public void UpdateShifting(ShiftingM Shifting) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
	
		try {
			
			connection = DbConnect.getDBConnection();
			String updateShifting = "UPDATE unic.Shifting SET 'shiftingID = '"+Shifting.getShiftingID()+"'diliveryID = '"+Shifting.getDiliveryID()+"'shifter1 = '"+Shifting.getShifter1()+"'shifter2 '"+Shifting.getShifter2()+"'WHERE (shiftingID ='"+Shifting+"' )";
		} catch (SQLException e) {
			
		}finally {
			try {
				if(preStatement != null) {
					preStatement.close();
					
				}
				
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				
			}
		}
		
		
	}
	
	
	public ArrayList<String> shiftingID(){
		ArrayList<String> shiftingID_List = new ArrayList<String>();
		
		try {
			String shiftingID_query = "SELECT shiftingID FROM unic.shifting;";
			connection = DbConnect.getDBConnection();
			preStatement = connection.prepareStatement(shiftingID_query);
			ResultSet shiftingIDs = preStatement.executeQuery();
			
			while (shiftingIDs.next()) {
				shiftingID_List.add(shiftingIDs.getString(1));	
			}
			
		} catch (Exception e) {
			// TODO: handle exception
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
		
		return shiftingID_List;
		
	
	}

}
