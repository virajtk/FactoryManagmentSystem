package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.model.*;
import com.service.*;

import javax.swing.JOptionPane;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import com.interfaces.AddS;
import com.model.Supplier;
import com.util.DbConnect;

import net.proteanit.sql.DbUtils;

public class addSServices {
	
	private static Connection connection ;
	private PreparedStatement preStatement ;
	
	public void addSupplier(Supplier supplier) {
		

		
			try {
				connection = DbConnect.getDBConnection();
				String addSupplier ="INSERT INTO unic.supplier (supplierID, FName, LName, location, companyName, email, contactNo, type) VALUES (?,?,?,?,?,?,?,?)"; 
				
				preStatement = connection.prepareStatement(addSupplier);
				
				preStatement.setString(1, supplier.getSupplierId());
				preStatement.setString(2, supplier.getFirstName());
				preStatement.setString(3, supplier.getLastName());
				preStatement.setString(4, supplier.getLocation());
				preStatement.setString(5, supplier.getCompanyName());
				preStatement.setString(6, supplier.getEmail());
				preStatement.setString(7, supplier.getMobileNo());
				preStatement.setString(8, supplier.getRawMaterialType());
				
				System.out.println(preStatement);
				
				
				
				
				
				preStatement.executeUpdate() ;
				JOptionPane.showMessageDialog(null, "Supplier Added Sucessfully....");			
				connection.commit();
				
			} catch(SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {

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
		
	
	

public void updateSupplier(Supplier supplier) {
	try {
		
		connection = DbConnect.getDBConnection();
		String updateSupplier = "UPDATE unic.supplier SET supplierID = '"+supplier.getSupplierId()+"',  FName = '"+supplier.getFirstName()+"', LName = '"+supplier.getLastName()+"', location = '"+supplier.getLocation()+"', companyName = '"+supplier.getCompanyName()+"', email = '"+supplier.getEmail()+"', contactNo = '"+supplier.getMobileNo()+"', type = '"+supplier.getRawMaterialType()+"' WHERE (supplierID = '"+supplier.getSupplierId()+"')";

		
		preStatement = connection.prepareStatement(updateSupplier);
		
		preStatement.executeUpdate() ;
		JOptionPane.showMessageDialog(null, "Supplier Details : "+supplier.getSupplierId()+" Updated Sucessfully....");
		
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



public void removeSupplier(Supplier supplier) {
	try {
		connection = DbConnect.getDBConnection();
		
		String deleteSupplier = "DELETE FROM unic.supplier WHERE supplierID = ?";
		
		preStatement = connection.prepareStatement(deleteSupplier);
		
		preStatement.setString(1, supplier.getSupplierId());
		
		
		preStatement.executeUpdate() ;
		JOptionPane.showMessageDialog(null, "Supplier Removed Sucessfully....");
		
	}catch(Exception e) {
		JOptionPane.showMessageDialog(null, e);

	}
	
}


/*

public ResultSet viewAllSuppliers() {
	try {
		String selectSupplier = "select * from supplier";
		connection = DbConnect.getDBConnection();
		preStatement = connection.prepareStatement(selectSupplier);
		ResultSet resultSet = preStatement.executeQuery();
		return resultSet;
	} catch (Exception e) {
		return null;
	}
}



public ArrayList<String> getSupplierID(){
	ArrayList<String> supplierID_List = new ArrayList<String>();
	
	try {
		String supplierID_query = "select s.supplierID from supplier as s";
		connection = DbConnect.getDBConnection();
		preStatement = connection.prepareStatement(supplierID_query);
		ResultSet clientIDs = preStatement.executeQuery();
		
		while (supplierIDs.next()) {
			supplierID_List.add(clientIDs.getString(1));	
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
	
	return supplierID_List;
	

}



*/
	

}



