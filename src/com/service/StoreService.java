package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.model.Store;
import javax.swing.JOptionPane;

import com.model.Product;
import com.util.DbConnect;

public class StoreService {
	
	private Connection connection;
	private PreparedStatement PreStatement;
	
	public void StockAddQuan(Store store) {
		try {
			
			connection = DbConnect.getDBConnection();
			String StockAddQuan =  "insert into unic.product_store (itemID, quantity) values (?, ?)";
			
			PreStatement = connection.prepareStatement(StockAddQuan);
			
			PreStatement.setString(1, store.getProductId());			
			PreStatement.setInt(2,store.getQuantity());
			
			//System.out.println(PreStatement);
			PreStatement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Record Inserted Successfully..");
			connection.commit();
			
			}catch(Exception e1){
			System.out.println(e1);
		}
		
		
	}
	
	public void qUpdate(Store store) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		
		connection = DbConnect.getDBConnection();
		String qUpdate = "UPDATE product_store SET quantity = '"+store.getQuantity()+"' WHERE itemID = '"+store.getProductId()+"'";
				
		PreStatement = connection.prepareStatement(qUpdate);
		System.out.println(PreStatement);
		PreStatement.executeUpdate();
		JOptionPane.showMessageDialog(null, "Quantity Update Successfully...");
		
	}

}
