package com.model;

import java.sql.Date;

public class ProductionM {
	
	private String productionID;
	private int noOfPices;
	private String productionDate;
	private int mechineNo;
	
	
	public String getProductionID() {
		return productionID;
	}
	public void setProductionID(String productionID) {
		this.productionID = productionID;
	}
	
	
	public int getNoOfPices() {
		return noOfPices;
	}
	public void setNoOfPices(int noOfPices) {
		this.noOfPices = noOfPices;
	}
	
	
	public String getProductionDate() {
		return productionDate;
	}
	public void setProductionDate(String productionDate) {
		this.productionDate = productionDate;
	}
	
	
	public int getMechineNo() {
		return mechineNo;
	}
	public void setMechineNo(int mechineNo) {
		this.mechineNo = mechineNo;
	}
}
