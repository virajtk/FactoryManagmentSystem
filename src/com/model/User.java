package com.model;

public class User {
	
	//define variables
	private String EID;
	private String username;
	private String fName;
	private String lName;
	private String address;
	private String bankAccNo;
	private String NICNo;
	private String contactNo;
	private String role;
	private String password;
	private double basicSalary;
	private String drivingLicenceNo;
	private String email;
	private String bDay;
	private String refferance;
	
	//constructor
	public User(String eID, String username, String fName, String lName, String address, String bankAccNo, String nICNo,
			String contactNo, String role, String password, double basicSalary, String drivingLicenceNo, String email,
			String bDay, String refferance) {
		
		EID = eID;
		this.username = username;
		this.fName = fName;
		this.lName = lName;
		this.address = address;
		this.bankAccNo = bankAccNo;
		NICNo = nICNo;
		this.contactNo = contactNo;
		this.role = role;
		this.password = password;
		this.basicSalary = basicSalary;
		this.drivingLicenceNo = drivingLicenceNo;
		this.email = email;
		this.bDay = bDay;
		this.refferance = refferance;
		
	}
	
	

}
