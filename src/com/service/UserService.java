package com.service;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.model.User;
import com.util.DbConnect;

public class UserService implements IUserService {
	
	private static Connection connection ;
	private PreparedStatement preStatement ;

	@Override
	public void addUser(User user) {
		try {
			connection = DbConnect.getDBConnection();
			
			String addUserQuery = "INSERT INTO user_main(EID,username,FName,LName,Address,"
					+ "BankAccountNo,NICNo,ContactNo,Role,Password,BasicSalary,DrivingLicenceNo,"
					+ "Email,BDay,Reference,OTRate,age,proPicture,RegDate) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement pst = connection.prepareStatement(addUserQuery);
			//1 - 19
			pst.setString(1, user.getEID());
			pst.setString(2, user.getUsername()); 
			pst.setString(3, user.getfName());
			pst.setString(4, user.getlName());
			pst.setString(5, user.getAddress());
			pst.setString(6, user.getBankAccNo());
			pst.setString(7, user.getNICNo());
			pst.setString(8, user.getContactNo());
			pst.setString(9, user.getRole());
			pst.setString(10, user.getPassword());
			pst.setDouble(11, user.getBasicSalary());
			pst.setString(12, user.getDrivingLicenceNo());
			pst.setString(13, user.getEmail());
			pst.setString(14, user.getbDay());
			pst.setString(15, user.getRefferance());
			pst.setDouble(16, user.getOTRate());
			pst.setInt(17, user.getAge());
			pst.setBlob(18, user.getProPicture());
			pst.setString(19, user.getRegDate());
			
			pst.executeUpdate();  		//execute query 
			
			JOptionPane.showMessageDialog(null, "Account Registed Sucessfully!");
			
			
		}catch(SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			
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

	@Override
	public void updateUser(String EID, User user) {
		try {
			connection = DbConnect.getDBConnection();
		
			
			String updateUserQuery = "UPDATE user_main SET  username = '"+user.getUsername()+"',"
					+ " fname = '"+user.getfName()+"', lname = '"+user.getlName()+"', BasicSalary = '"+user.getBasicSalary()+"',"
							+ " OTRate = '"+user.getOTRate()+"', BankAccountNo = '"+user.getBankAccNo()+"', Role = '"+user.getRole()+"',"
									+ " NICNo = '"+user.getNICNo()+"', ContactNo = '"+user.getContactNo()+"',"
									+ " Email = '"+user.getEmail()+"', Address = '"+user.getAddress()+"', Reference = '"+user.getRefferance()+"', DrivingLicenceNo = '"+user.getDrivingLicenceNo()+"'"
											+ ", proPicture = ?"
											+ " WHERE (EID = '"+EID+"')";
			
			
	
			
			PreparedStatement psst = connection.prepareStatement(updateUserQuery);
			psst.setBlob(1, user.getProPicture());
			
			psst.executeUpdate() ; 			//execute query
			
			JOptionPane.showMessageDialog(null, "Employee : "+EID+"\nProfile Updated Sucessfully!");
			
			
		}catch(SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			
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

	@Override
	public void removeUser(String EID) {
		try {
			connection = DbConnect.getDBConnection();
			
			String deleteUserQuery = "DELETE FROM user_main WHERE user_main.EID = ?";
			
			preStatement = connection.prepareStatement(deleteUserQuery);
			
			preStatement.setString(1, EID);
			preStatement.executeUpdate() ;		//execute query
			JOptionPane.showMessageDialog(null, "Record no : "+EID+" Removed Sucessfully!");
			
		}catch(SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			
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
				JOptionPane.showMessageDialog(null, e);
			}
			
		}

	}

}
