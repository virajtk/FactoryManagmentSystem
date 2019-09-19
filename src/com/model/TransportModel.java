package com.model;

import javax.lang.model.type.PrimitiveType;

public class TransportModel {
	private String transportID;
	private String orderID;
	private String vehicleID;
	private String driverID;
	private String date;
	
	
	public String getTransportID() {
		return transportID;
	}
	public void setTransportID(String transportID) {
		this.transportID = transportID;
	}
	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	public String getVehicleID() {
		return vehicleID;
	}
	public void setVehicleID(String vehicleID) {
		this.vehicleID = vehicleID;
	}
	public String getDriverID() {
		return driverID;
	}
	public void setDriverID(String driverID) {
		this.driverID = driverID;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

}
