package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import com.interfaces.*;
import com.util.DbConnect;
import com.model.RmM;
import com.model.Supplier;

import net.proteanit.sql.DbUtils;

public class addRmServices {
	
	private static Connection connection ;
	private PreparedStatement preStatement ;

	
	public void addRmM(RmM rmM) {
		
		try {
			
			connection = DbConnect.getDBConnection();
			
			
			String addRmM = "INSERT INTO unic.raw_material (rawID, name, category) VALUES (?, ?, ?)"; 
			
preStatement = connection.prepareStatement(addRmM);
			
			preStatement.setString(1, rmM.getRawMaterialId());
			preStatement.setString(2, rmM.getSupplierName());
			preStatement.setString(3, rmM.getRawMaterialType());
			
			System.out.println(preStatement);
			
		
			
			preStatement.executeUpdate() ;
			
			JOptionPane.showMessageDialog(null, "RAW Material Details Inserted Sucessfully....");			
			connection.commit();
			
		} catch(SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				JOptionPane.showMessageDialog(null, e);
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
	
	public void updateRmM(RmM rmM) {
		try {
			
			connection = DbConnect.getDBConnection();
			String updateRmM = "UPDATE unic.raw_material SET   rawID = '"+rmM.getRawMaterialId()+"', name = '"+rmM.getSupplierName()+"', category = '"+rmM.getRawMaterialType()+"' WHERE (rawID = '"+rmM.getRawMaterialId()+"')";

			
			preStatement = connection.prepareStatement(updateRmM);
			
			preStatement.executeUpdate() ;
			JOptionPane.showMessageDialog(null, "rawID: "+rmM.getRawMaterialId()+" Updated Sucessfully....");
			
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
		
	public void removeRmM(RmM rmM) {
		try {
			connection = DbConnect.getDBConnection();
			
			String deleteSupplier = "DELETE FROM unic.raw_material WHERE rawID = ?";
			
			preStatement = connection.prepareStatement(deleteSupplier);
			
			preStatement.setString(1, rmM.getRawMaterialId());
			
			
			preStatement.executeUpdate() ;
			JOptionPane.showMessageDialog(null, "Raw Material Removed Sucessfully....");
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);

		}
		
	}		
		
	
	
	public ArrayList<String> getRawMatID(){
		ArrayList<String>rawMatID_List = new ArrayList<String>();
		
		try {
			String rawMatQuary = "SELECT rawID FROM unic.raw_material";
			connection = DbConnect.getDBConnection();
			preStatement = connection.prepareStatement(rawMatQuary);
			ResultSet rawMatIDs = preStatement.executeQuery();
			
			while (rawMatIDs.next()) {
				rawMatID_List.add(rawMatIDs.getString(1));	
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
		
		return rawMatID_List;
	}
	
	

		
}
	


