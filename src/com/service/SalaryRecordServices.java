package com.service;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import com.interfaces.ManagersSchema;

import com.model.SalarySchema;
import com.util.DbConnect;

import net.proteanit.sql.DbUtils;

public class SalaryRecordServices {
	
	private static Connection connection ;
	private PreparedStatement preStatement ;

	
	public void addSchema(SalarySchema schema) {
		try {
			connection = DbConnect.getDBConnection();
			String addSalary ="insert into salaryschema (schemaID,piecePrice1,piecePrice2,piecePrice3,piecePrice4,piecePrice5,OTrate,role) values (?, ? , ? , ? , ? , ?, ?, ?)"; 
			
			preStatement = connection.prepareStatement(addSalary);
			
			preStatement.setString(1, schema.getSchemaId());
			preStatement.setInt(2, schema.getPeacesPrice1());
			preStatement.setInt(3, schema.getPeacesPrice2());
			preStatement.setInt(4, schema.getPeacesPrice3());
			preStatement.setInt(5, schema.getPeacesPrice4());
			preStatement.setInt(6, schema.getPeacesPrice5());
			preStatement.setInt(7, schema.getOtRate());
			preStatement.setString(8, schema.getRole());
			
			preStatement.executeUpdate() ;
			JOptionPane.showMessageDialog(null, "Record Inserted Sucessfully....");			
			//connection.commit();
			
		} catch(SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();

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
	
	
	
	public void updateSchema(SalarySchema schema) {
		try {
			
			connection = DbConnect.getDBConnection();
			String updateSchema = "UPDATE unic.salaryschema SET  piecePrice1 = '"+schema.getPeacesPrice1()+"', piecePrice2 = '"+schema.getPeacesPrice2()+"', piecePrice3 = '"+schema.getPeacesPrice3()+"', piecePrice4 = '"+schema.getPeacesPrice4()+"', piecePrice5 = '"+schema.getPeacesPrice5()+"', OTrate = '"+schema.getOtRate()+"' WHERE schemaID = '"+schema.getSchemaId()+"'";
	
			
			preStatement = connection.prepareStatement(updateSchema);
			
			preStatement.executeUpdate() ;
			JOptionPane.showMessageDialog(null, "Record no: "+schema.getSchemaId()+" Updated Sucessfully....");
			
		}catch(SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}finally {
			try {
				if (preStatement != null) {
					preStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
		}
	}


	
	public void removeSchema(String schemaID) {
		try {
			connection = DbConnect.getDBConnection();
			
			String deleteSchema = "delete  from salaryschema where schemaID = ?";
			
			preStatement = connection.prepareStatement(deleteSchema);
			
			preStatement.setString(1, schemaID);
			
			preStatement.executeUpdate() ;
			
			JOptionPane.showMessageDialog(null, "Record no: "+schemaID+" Removed Sucessfully....");
			
		}catch(SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}finally {
			try {
				if (preStatement != null) {
					preStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	
	public ResultSet searchAndSort(String colom_name,String inputFieldName) {
		try {
			String selectSchema = "SELECT * FROM unic.salaryschema where "+colom_name+" like '"+"%"+inputFieldName+"%"+"'";
			connection = DbConnect.getDBConnection();
			preStatement = connection.prepareStatement(selectSchema);
			ResultSet resultSet = preStatement.executeQuery();
			return resultSet;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
				
	}
	
	
	public ArrayList<String> getSchemaID(){
		ArrayList<String> schemaID_List = new ArrayList<String>();
		
		try {
			String schemaID_query = "select schemaID from salaryschema";
			connection = DbConnect.getDBConnection();
			preStatement = connection.prepareStatement(schemaID_query);
			ResultSet schemaIDs = preStatement.executeQuery();
			
			while (schemaIDs.next()) {
				schemaID_List.add(schemaIDs.getString(1));	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
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
		
		return schemaID_List;
		
	
	}
	
	
	

}
