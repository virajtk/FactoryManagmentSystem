package com.util;

import java.util.ArrayList;

public class Generator {

	public static final String Transport_ID_Letter= "T";
	public static final String Vehicle_ID_Letter="V";
	

	public static String transportID;
	public static String vehicleID;
	
	
	public static int transportCnt;
	public static int vehicleCnt;
	
	
	
	public static String transportID_Generator(ArrayList<String> arrayList) {
		
		transportCnt = arrayList.size();
		
		transportCnt++;
		transportID = Transport_ID_Letter+ String.format("%04d",transportCnt);
		
		if (arrayList.contains(transportID)) {
			transportCnt++;
			transportID = Transport_ID_Letter+ String.format("%04d", transportCnt);
		}

		return transportID;
	}
	
	public static String vehicleID_Generator(ArrayList<String> arrayList) {
		
		vehicleCnt = arrayList.size();
		
		vehicleCnt++;
		vehicleID = Vehicle_ID_Letter+ String.format("%04d",vehicleCnt);
		
		if (arrayList.contains(vehicleID)) {
			vehicleCnt++;
			vehicleID = Vehicle_ID_Letter+ String.format("%04d", vehicleCnt);
		}

		return vehicleID;
	}
}
