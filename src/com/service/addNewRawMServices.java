package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import com.interfaces.AddNewRawM;
import com.model.RawMaterial;
import com.util.DbConnect;

import net.proteanit.sql.DbUtils;

public class addNewRawMServices {
	
	

	
	private static Connection connection ;
	private PreparedStatement preStatement ;


	public void addNewRawMaterial(RawMaterial rawM) {
		try {
			connection = DbConnect.getDBConnection();
		//
			String addNewRawMaterial ="INSERT INTO unic.supply (rawID, supplierID, pricePerUnit, orderDate, recievedDate, totalCost, amount) VALUES (?,?,?,?,?,?,?)"; 
			
			preStatement = connection.prepareStatement(addNewRawMaterial);
			
			preStatement.setString(1, rawM.getRawMaterialId());
			preStatement.setString(2, rawM.getSupplierId());
			preStatement.setString(3, rawM.getPricePerUnit());
			preStatement.setString(4, rawM.getOrderedDate());
			preStatement.setString(5, rawM.getRecievedDate());
			preStatement.setString(6, rawM.getTotalCost());
			preStatement.setString(7, rawM.getQuantity());
			
			System.out.println(preStatement);
			
			preStatement.executeUpdate() ;
			
			JOptionPane.showMessageDialog(null, "supply details Inserted Sucessfully....");			
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
	


public void updateRawMaterial(RawMaterial rawM) {
	try {
		
		connection = DbConnect.getDBConnection();
		String updateRawMaterial = "UPDATE unic.supply SET   = '"+rawM.getRawMaterialId()+"', supplierID = '"+rawM.getSupplierId()+"', pricePerUnit = '"+rawM.getPricePerUnit()+"', orderDate = '"+rawM.getOrderedDate()+"', recievedDate = '"+rawM.getRecievedDate()+"', totalCost = '"+rawM.getTotalCost()+"', amount = '"+rawM.getQuantity()+"' WHERE (rawID = '"+rawM.getRawMaterialId()+"')";

		
		preStatement = connection.prepareStatement(updateRawMaterial);
		
		preStatement.executeUpdate() ;
		JOptionPane.showMessageDialog(null, "rawID: "+rawM.getRawMaterialId()+" Updated Sucessfully....");
		
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


public void removeRawMaterial(RawMaterial rawM) {
	
	try {
		
		connection = DbConnect.getDBConnection();
		String deleteRawMaterial = "DELETE unic.supply WHERE  supplierID = ?";

		
		preStatement = connection.prepareStatement(deleteRawMaterial);
		
		preStatement.setString(1, rawM.getSupplierId());
	
		
		preStatement.executeUpdate() ;
		JOptionPane.showMessageDialog(null, "supply details Removed Successfuly....");
	
	}catch(Exception e) {
		JOptionPane.showMessageDialog(null, e);
	}
}
}











