package com.service;

import java.sql.ResultSet;

import com.model.User;

public interface IUserService {
	
	//give methods as user management
	public void addUser(User user);
	public void updateUser(String userID, User user);
	public void removeUser(String userID);
	
}
