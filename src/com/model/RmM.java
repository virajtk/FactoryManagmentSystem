package com.model;

import javax.swing.JComboBox;

public class RmM {

	
	private String rawMaterialId;
	private String rawMaterialType;
	private String supplierName;
	
	public String getRawMaterialId() {
		return rawMaterialId;
	}
	public void setRawMaterialId(String rawMaterialId) {
		this.rawMaterialId = rawMaterialId;
	}
	public String getRawMaterialType() {
		return rawMaterialType;
	}
	
	public void setRawMaterialType(String rawMaterialType) {
		this.rawMaterialType = rawMaterialType;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
}
