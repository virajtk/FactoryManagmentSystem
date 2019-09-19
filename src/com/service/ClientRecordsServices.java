package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import com.interfaces.MainOrderInterface;
import com.model.Client;
import com.util.DbConnect;

import net.proteanit.sql.DbUtils;

public class ClientRecordsServices {
	
	private static Connection connection ;
	private PreparedStatement preStatement ;


	
	public void addClient(Client client) {
		try {
			connection = DbConnect.getDBConnection();
			String addClient ="insert into customer (clientID,FName,LName,companyName,NICNo,ContactNo,Email,Address) values (?,?,?, ?, ?, ?, ?, ?)"; 
			
			preStatement = connection.prepareStatement(addClient);
			
			preStatement.setString(1, client.getClientId());
			preStatement.setString(2, client.getFirstName());
			preStatement.setString(3, client.getLastName());
			preStatement.setString(4, client.getCompanyName());
			preStatement.setString(5, client.getNicNo());
			preStatement.setString(6, client.getContactNo());
			preStatement.setString(7, client.getEmailAddress());
			preStatement.setString(8, client.getClientAddress());
			
			preStatement.executeUpdate() ;
			JOptionPane.showMessageDialog(null, "Record Inserted Sucessfully....");			
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

	
	
	public void updateClient(Client client) {
		try {
			
			connection = DbConnect.getDBConnection();
			String updateClient = "UPDATE unic.customer SET  FName = '"+client.getFirstName()+"', LName = '"+client.getLastName()+"', companyName = '"+client.getCompanyName()+"', NICNo = '"+client.getNicNo()+"', ContactNo = '"+client.getContactNo()+"', Email = '"+client.getEmailAddress()+"', Address = '"+client.getClientAddress()+"' WHERE (clientID = '"+client.getClientId()+"')";
	
			
			preStatement = connection.prepareStatement(updateClient);
			
			preStatement.executeUpdate() ;
			JOptionPane.showMessageDialog(null, "Record no: "+client.getClientId()+" Updated Sucessfully....");
			
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


	
	public void removeCLient(String clientID) {
		try {
			connection = DbConnect.getDBConnection();
			
			String deleteClient = "DELETE FROM `unic`.`customer` WHERE (`clientID` = '"+clientID+"');";

			preStatement = connection.prepareStatement(deleteClient);

			System.out.println(preStatement);
			preStatement.executeUpdate() ;
			JOptionPane.showMessageDialog(null, "Record no: "+clientID+" Removed Sucessfully....");
			
		}catch(SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			JOptionPane.showMessageDialog(null, "This client have order...");
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
		
	}
	
	
	public ResultSet viewAllClients() {
		try {
			String selectClient = "select * from customer";
			connection = DbConnect.getDBConnection();
			preStatement = connection.prepareStatement(selectClient);
			ResultSet resultSet = preStatement.executeQuery();
			return resultSet;
		} catch (Exception e) {
			return null;
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
	

	
	public ArrayList<String> getClientID(){
		ArrayList<String> clientID_List = new ArrayList<String>();
		
		try {
			String clientID_query = "select c.clientID from customer as c";
			connection = DbConnect.getDBConnection();
			preStatement = connection.prepareStatement(clientID_query);
			ResultSet clientIDs = preStatement.executeQuery();
			
			while (clientIDs.next()) {
				clientID_List.add(clientIDs.getString(1));	
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
		
		return clientID_List;
		
	
	}
	
	
	
	
		
	
}
