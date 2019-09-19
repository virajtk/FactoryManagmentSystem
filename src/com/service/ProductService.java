package com.service;

import java.lang.ref.SoftReference;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.*;
import javax.swing.JOptionPane;
import com.util.DbConnect;


public class ProductService {
	
	private Connection connection;
	private PreparedStatement PreStatement;


	
	public void StockAddInsert(Product product) {
		try {
			
			connection = DbConnect.getDBConnection();
			String StockAddInsert =  "insert into unic.product (productID, productName, unitPrice, category, colour, strength_N, shape) values (?, ?, ?, ?, ?, ?, ?)";
			
			PreStatement = connection.prepareStatement(StockAddInsert);
			
			PreStatement.setString(1, product.getProductId());			
			PreStatement.setString(2, product.getProductName());
			PreStatement.setDouble(3, product.getUnitPrice());
			PreStatement.setString(4, product.getCategory());
			PreStatement.setString(5, product.getColour());
			PreStatement.setInt(6, product.getStrength());
			PreStatement.setString(7, product.getShape());
			System.out.println(PreStatement);
			PreStatement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Record Inserted Successfully.. ");
			connection.commit();
			
			}catch(Exception e1){
			System.out.println(e1);
		}
		
		
	}
	
	public void pDelete(String productID) {
		try {
			
			connection = DbConnect.getDBConnection();
			String deleteProduct = "delete from product where product.productID = ?";
			
			PreStatement = connection.prepareStatement(deleteProduct);
			
			PreStatement.setString(1, productID);
			PreStatement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Product  ID : " + productID + " Record Removed Successfully..");
			
		} catch (Exception e) {
			
		}
	}
	
	public void pUpdate(Product product) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		
		connection = DbConnect.getDBConnection();
		String pUpdate = "UPDATE unic.product SET productID = '"+product.getProductId()+"', productName = '"+product.getProductName()+"', unitPrice = '"+product.getUnitPrice()+"', category = '"+product.getCategory()+"', colour = '"+product.getColour()+"', strength_N = '"+product.getStrength()+"', shape = '"+product.getShape()+"' WHERE (productID = '"+product.getProductId()+"')";
				
		PreStatement = connection.prepareStatement(pUpdate);
		PreStatement.executeUpdate();
		JOptionPane.showMessageDialog(null, "Record Update Successfully...");
		
	}

}
