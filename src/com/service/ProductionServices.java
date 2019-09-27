package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.interfaces.Production;
import com.model.ProductionM;
import com.model.ShiftingM;
import com.util.DbConnect;

public class ProductionServices {

	private static Connection connection;
	private PreparedStatement preStatement;
	
	public void AddProduction(ProductionM productionM ) {
		try {
			
			
			connection = DbConnect.getDBConnection();
			String addProduction = "INSERT INTO unic.production (productionID, noOfPices, productionDate, mechineNo) VALUES (?,?,?,?)";
			
			preStatement = connection.prepareStatement(addProduction);
			
			preStatement.setString(1, productionM.getProductionID());
			preStatement.setLong(2, productionM.getNoOfPices());
			preStatement.setString(3, productionM.getProductionDate());
			preStatement.setInt(4, productionM.getMechineNo());
			//System.out.println(preStatement);
			preStatement.executeUpdate();
			JOptionPane.showMessageDialog(null,"Details are sucessfull inserted.");
			
			
		} catch (Exception e) {
			
	}
		
	
}

	public void DeleteProduction(String productionID) throws InstantiationException, IllegalAccessException  {
	
		try {
			
			connection = DbConnect.getDBConnection();
			
			String deleteProduction = "DELETE FROM unic.production WHERE (productionID = '"+productionID+"')";
			
			preStatement = connection.prepareStatement(deleteProduction);
			//System.out.println(preStatement);
			preStatement.executeUpdate();
			JOptionPane.showMessageDialog(null, productionID+"Sucessfully removed");
			
			
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

	public void viewProduction() {
		
		
		
	
	}

	public void UpdateProduction(ProductionM productionM) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
	
		try {
			
			connection = DbConnect.getDBConnection();
			String updateProduction = " UPDATE unic.production SET productionID = '"+productionM.getProductionID()+"', noOfPices = '"+productionM.getNoOfPices()+"', productionDate = '"+productionM.getProductionDate()+"', mechineNo = '"+productionM.getMechineNo()+"' WHERE (productionID = '"+productionM.getProductionID()+"')";			

			
			preStatement = connection.prepareStatement(updateProduction);

			preStatement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Updated Successfully.");
		
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
}
