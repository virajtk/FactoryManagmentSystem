package com.model;

import java.io.InputStream;

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
	private double OTRate;
	private int age;
	private String regDate;
	private InputStream ProPicture;

	
	
	//getter and setters
	public String getEID() {
		return EID;
	}

	public void setEID(String eID) {
		EID = eID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBankAccNo() {
		return bankAccNo;
	}

	public void setBankAccNo(String bankAccNo) {
		this.bankAccNo = bankAccNo;
	}

	public String getNICNo() {
		return NICNo;
	}

	public void setNICNo(String nICNo) {
		NICNo = nICNo;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(double basicSalary) {
		this.basicSalary = basicSalary;
	}

	public String getDrivingLicenceNo() {
		return drivingLicenceNo;
	}

	public void setDrivingLicenceNo(String drivingLicenceNo) {
		this.drivingLicenceNo = drivingLicenceNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getbDay() {
		return bDay;
	}

	public void setbDay(String bDay) {
		this.bDay = bDay;
	}

	public String getRefferance() {
		return refferance;
	}

	public void setRefferance(String refferance) {
		this.refferance = refferance;
	}


	public double getOTRate() {
		return OTRate;
	}


	public void setOTRate(double oTRate) {
		OTRate = oTRate;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public InputStream getProPicture() {
		return ProPicture;
	}

	public void setProPicture(InputStream inputStream) {
		ProPicture = inputStream;
	}
	
	

}
