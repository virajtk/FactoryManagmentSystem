package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.model.TransportModel;
import com.model.VehicleModel;
//import com.mysql.cj.xdevapi.Client;
import com.util.DbConnect;

public class VehicleService {

	private static PreparedStatement preStatement;
	private Connection connection;
	
	public void addVehicle(VehicleModel vehicleModel) {
		try {
			connection  = DbConnect.getDBConnection();
			
			String addVehicle = "insert into vehicle(vehicleID, name, model, type, capacity_tonnes, RegistationNo) values(?,?,?,?,?,?)";
			
			preStatement = connection.prepareStatement(addVehicle);
			
			preStatement.setString(1, vehicleModel.getVehicleID());
			preStatement.setString(2, vehicleModel.getVehicleName());
			preStatement.setString(3, vehicleModel.getModelName());
			preStatement.setString(4, vehicleModel.getVehicleType());
			preStatement.setString(5, vehicleModel.getVehicleCapacity());
			preStatement.setString(6, vehicleModel.getRegistrationNumber());
			
			preStatement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Vehical Inserted Succesfully!");
			
			//connection.commit();
			
			
			
			
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	public void removeVehicle(VehicleModel vehicleModel) {
		try {
			connection  = DbConnect.getDBConnection();
			
			String removeVehicle = "DELETE FROM unic.vehicle WHERE vehicleID = ? ";
			
			preStatement = connection.prepareStatement(removeVehicle);
			
			preStatement.setString(1, vehicleModel.getVehicleID());
			
			preStatement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Vehical Deleted Succesfully!");
			
			//connection.commit();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
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
	public void updateVehicle(VehicleModel vehicleModel) {
		try {
			
			connection = DbConnect.getDBConnection();
			String updateVehicle = "UPDATE unic.vehicle SET  vehicleID = '"+vehicleModel.getVehicleID()+"', name = '"+vehicleModel.getVehicleName()+"', model = '"+vehicleModel.getModelName()+"', type = '"+vehicleModel.getVehicleType()+"', capacity_tonnes = '"+vehicleModel.getVehicleCapacity()+"', RegistationNo = '"+vehicleModel.getRegistrationNumber()+"' WHERE (vehicleID = '"+vehicleModel.getVehicleID()+"')";
	
			
			preStatement = connection.prepareStatement(updateVehicle);
			
			preStatement.executeUpdate() ;
			JOptionPane.showMessageDialog(null, "Record no: "+vehicleModel.getVehicleID()+" Updated Sucessfully....");
			
		}catch(SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			
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
