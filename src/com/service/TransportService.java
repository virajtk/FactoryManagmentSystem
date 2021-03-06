package com.service;
import java.security.KeyStore.PrivateKeyEntry;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.model.*;
import com.util.*;

public class TransportService{
	
	private static PreparedStatement preStatement;
	private Connection connection;
	
	public void addTransport(TransportModel transportModel) {
		try {
			connection  = DbConnect.getDBConnection();
			
			String addTransport = "insert into transport(transportID, orderID, vehicleID, driverID, date) values(?,?,?,?,?)";
			//System.out.println(addTransport);
			preStatement = connection.prepareStatement(addTransport);
			
			preStatement.setString(1, transportModel.getTransportID());
			preStatement.setString(2, transportModel.getOrderID());
			preStatement.setString(3, transportModel.getVehicleID());
			preStatement.setString(4, transportModel.getDriverID());
			preStatement.setString(5, transportModel.getDate());
			
			//System.out.println(preStatement);
			preStatement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Transport Inserted Succesfully!");
			
			//connection.commit();
			
			
			
			
			
		} catch (Exception e) {
			//JOptionPane.showMessageDialog(null, e);
		}
	}
	public ArrayList<String> transportID(){
		ArrayList<String> transportID_List = new ArrayList<String>();
		
		try {
			String transportID_query = "select transportID from unic.transport";
			connection = DbConnect.getDBConnection();
			preStatement = connection.prepareStatement(transportID_query);
			ResultSet transportIDs = preStatement.executeQuery();
			
			while (transportIDs.next()) {
				transportID_List.add(transportIDs.getString(1));	
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
		
		return transportID_List;
	}
	public ArrayList<String> vehicleID(){
		ArrayList<String> vehicleID_List = new ArrayList<String>();
		
		try {
			String vehicleID_query = "select vehicleID from unic.vehicle";
			connection = DbConnect.getDBConnection();
			preStatement = connection.prepareStatement(vehicleID_query);
			ResultSet vehicleIDs = preStatement.executeQuery();
			
			while (vehicleIDs.next()) {
				vehicleID_List.add(vehicleIDs.getString(1));	
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
		
		return vehicleID_List;
	}
	public void removeTransport(TransportModel transportModel) {
		try {
			connection  = DbConnect.getDBConnection();
			
			String removeTransport = "DELETE FROM unic.transport WHERE transportID = ? ";
			
			preStatement = connection.prepareStatement(removeTransport);
			
			preStatement.setString(1, transportModel.getTransportID());
			
			preStatement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Transport Deleted Succesfully!");
			
			//connection.commit();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	

}
