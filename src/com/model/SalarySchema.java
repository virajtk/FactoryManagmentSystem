package com.model;

import javax.swing.JTextField;

public class SalarySchema {
	
	private String txtSchemaId;
	private int txtpp1;
	private int txtpp2;
	private int txtpp3;
	private int txtot;
	private int txtpp4;
	private int txtpp5;
	private String role;
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSchemaId() {
		return txtSchemaId;
	}
	
	public void setSchemaId (String txtSchemaId) {
		this.txtSchemaId = txtSchemaId;
		
	}
	
	public int getPeacesPrice1() {
		return txtpp1;
	}
	public void setPeacesPrice1(int pp1) {
		this.txtpp1 = pp1;
	}
	
	
	public int getPeacesPrice2() {
		return txtpp2;
	}
	public void setPeacesPrice2(int pp2) {
		this.txtpp2 = pp2;
	}
	
	
	public int getPeacesPrice3() {
		return txtpp3;
	}
	public void setPeacesPrice3(int pp3) {
		this.txtpp3 = pp3;
	}
	
	
	public int getPeacesPrice4() {
		return txtpp4;
	}
	public void setPeacesPrice4(int pp4) {
		this.txtpp4 = pp4;
	}
	
	
	public int getPeacesPrice5() {
		return txtpp5;
	}
	public void setPeacesPrice5(int pp5) {
		this.txtpp5 = pp5;
	}
	
	
	public int getOtRate() {
		return txtot;
	}
	public void setOtRate(int ot) {
		this.txtot = ot;
	}
	

}
