package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.print.attribute.standard.RequestingUserName;
import javax.swing.JOptionPane;
import com.model.*;
import com.util.DbConnect;

import net.proteanit.sql.DbUtils;


public class OrderRecordsServices {
	
	private static Connection connection ;
	private PreparedStatement preStatement ;
	
	private int prodctAvailableCount;
	
	public ResultSet produtTypeFill() {
		try {
			String selectProductName= "select distinct productName from unic.product";
			connection = DbConnect.getDBConnection();
			preStatement = connection.prepareStatement(selectProductName);
			ResultSet productSet = preStatement.executeQuery();
			
			return productSet;
			
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);
			return null;
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
	
	public int chekAvailability(String productID) {
		try {
			connection=DbConnect.getDBConnection();
			String chekAvailability = "SELECT quantity FROM unic.product_store WHERE itemID = '"+productID+"'";
			preStatement = connection.prepareStatement(chekAvailability);
			ResultSet chekAvailabilitySet = preStatement.executeQuery();
			
			while (chekAvailabilitySet.next()) {
				prodctAvailableCount = chekAvailabilitySet.getInt("quantity");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return prodctAvailableCount;
		
	}
	
	
	public void addOrder(Order order, Client client) {
		try {
			connection = DbConnect.getDBConnection();
			String addClient ="insert into unic.order (orderID, clientID, productID, orderDate, dayOfNeed, dayOfComplete, quantity, superviser, transportType, location, remarks) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
			//String addClient ="insert into orders (clientID, orderID, productType, orderDate, dayOfNeed, quantity, superviserID, color, transportType, location, remark) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
			
			preStatement = connection.prepareStatement(addClient);
			System.out.println(order.getOrderDate());
			preStatement.setString(1, order.getOrderID());
			preStatement.setString(2, client.getClientId());
			preStatement.setString(3, order.getProductType());
			preStatement.setString(4, order.getOrderDate());
			preStatement.setString(5, order.getDayOfNeed());
			preStatement.setString(6, order.getDayOfComplete());
			preStatement.setString(7, order.getQuantity());
			preStatement.setString(8, order.getSuperviserID());
			preStatement.setString(9, order.getTransportType());
			preStatement.setString(10, order.getLocation());
			preStatement.setString(11, order.getRemark());
			
	
			
			preStatement.executeUpdate() ;
			JOptionPane.showMessageDialog(null, "Order Placed Sucessfully....");
			connection.commit();
			
		} catch(SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			//JOptionPane.showMessageDialog(null, "Error....");
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
	
	public  void updateOrder(Order order, Client client) {
		try {
			connection = DbConnect.getDBConnection();
			String updateOrder = "UPDATE unic.order SET orderID = '"+order.getOrderID()+"', clientID = '"+client.getClientId()+"', productID = '"+order.getProductType()+"', orderDate = '"+order.getOrderDate()+"', dayOfNeed = '"+order.getDayOfNeed()+"', dayOfComplete = '"+order.getDayOfComplete()+"', quantity = '"+order.getQuantity()+"', superviser = '"+order.getSuperviserID()+"', transportType = '"+order.getTransportType()+"', location = '"+order.getLocation()+"', remarks = '"+order.getRemark()+"' WHERE (orderID = '"+order.getOrderID()+"')";
			preStatement = connection.prepareStatement(updateOrder);
			System.out.println(preStatement);
			preStatement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Record no: "+order.getOrderID()+" Updated Sucessfully....");
			
			
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
		
	}
	
	public void removeOrder(String orderID) {
		try {
			connection = DbConnect.getDBConnection();
			
			String deleteOrder = "delete from unic.order where order.orderID = ?";
			preStatement = connection.prepareStatement(deleteOrder);
			preStatement.setString(1, orderID);
			preStatement.executeUpdate() ;
			JOptionPane.showMessageDialog(null, "Record no: "+orderID+" Removed Sucessfully....");
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	
	public ResultSet viewAllOrders() {
		try {
			String selectClient = "SELECT * FROM unic.order";
			connection = DbConnect.getDBConnection();
			preStatement = connection.prepareStatement(selectClient);
			ResultSet resultSet = preStatement.executeQuery();
			return resultSet;
		} catch (Exception e) {
			return null;
		}
	}
	
	
	
	
	public String superviceIDView(String supervicerName) {
		String supervicerID = null ;
		try {
			String SupervicerID_query = "SELECT EID FROM (SELECT EID, CONCAT(FName,' ', LName) AS fullName FROM unic.user_main) result WHERE result.fullName = '"+supervicerName+"'";
			connection = DbConnect.getDBConnection();
			preStatement = connection.prepareStatement(SupervicerID_query);
			ResultSet supervicerResult = preStatement.executeQuery();
			while (supervicerResult.next()) {
				supervicerID = supervicerResult.getString("EID");	
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		//return productID;
		return supervicerID;
	}
	
	
	public ArrayList<String> getOrderID(){
		ArrayList<String> orderID_List = new ArrayList<String>();
		
		try {
			String OrderID_query = "select orderID from unic.order";
			connection = DbConnect.getDBConnection();
			preStatement = connection.prepareStatement(OrderID_query);
			ResultSet orderIDs = preStatement.executeQuery();
			
			while (orderIDs.next()) {
				orderID_List.add(orderIDs.getString(1));	
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
		
		return orderID_List;
	}
	
	
	public void updateProductQuantity(String productID, int remainingAmount) {
		try {
			connection = DbConnect.getDBConnection();
			String updateQuantity = "UPDATE unic.product_store SET quantity = '"+remainingAmount+"' WHERE (itemID = '"+productID+"') ";
			preStatement = connection.prepareStatement(updateQuantity);
			preStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	

}
