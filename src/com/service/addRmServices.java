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
			
			String deleteSupplier = "DELETE FROM unic.supplier WHERE supplierID = ?";
			
			preStatement = connection.prepareStatement(deleteSupplier);
			
			preStatement.setString(1, rmM.getRawMaterialId());
			
			
			preStatement.executeUpdate() ;
			JOptionPane.showMessageDialog(null, "Supplier Removed Sucessfully....");
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);

		}
		
	}		
		
		
		
}
	


