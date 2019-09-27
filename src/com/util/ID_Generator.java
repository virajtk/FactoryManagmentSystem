package com.util;

import java.util.ArrayList;

public class ID_Generator {
	
	public static String shiftingID;
	public static int shiftingCunt;
	
	public static final String Shifting_ID_Letter= "S";
	public static final String Client_ID_Letter= "C";
	public static final String Order_ID_Letter= "O";
	
	

	public static String clientID;
	public static String orderID;
	
	
	
	public static int clientCnt;
	public static int orderCnt;
	
	public static final String rawMaetID = "R";
	public static String rawMatID;
	public static int rawMatCount;
	
	
	//Start
	
	public static final String Product_ID_Letter= "PR";
	public static String productID;
	public static int productCnt;
	
	
	
	public static String product_ID_Generaor(ArrayList<String> arrayList) {
		
		productCnt = arrayList.size();
		
		productCnt++;
		productID = Product_ID_Letter+ String.format("%04d",productCnt);
		
		if (arrayList.contains(productID)) {
			productCnt++;
			productID = Product_ID_Letter+ String.format("%04d", productCnt);
		}

		return productID;

	}
	//End
	
	public static String clientID_Generator(ArrayList<String> arrayList) {
		
		clientCnt = arrayList.size();
		
		clientCnt++;
		clientID = Client_ID_Letter+ String.format("%04d",clientCnt);
		
		if (arrayList.contains(clientID)) {
			clientCnt++;
			clientID = Client_ID_Letter+ String.format("%04d", clientCnt);
		}

		return clientID;

	}
	
	public static String orderID_Generator(ArrayList<String> arrayList) {
		
		orderCnt = arrayList.size();
		
		orderCnt++;
		orderID = Order_ID_Letter+ String.format("%04d",orderCnt);
		
		if (arrayList.contains(orderID)) {
			orderCnt++;
			orderID = Order_ID_Letter+ String.format("%04d", orderCnt);
		}

		return orderID;
	}
	
	public static String shifting_Generator(ArrayList<String> arrayList) {
		
		shiftingCunt = arrayList.size();
		
		shiftingCunt++;
		shiftingID = Shifting_ID_Letter+ String.format("%04d",shiftingCunt);
		
		if (arrayList.contains(shiftingCunt)) {
			shiftingCunt++;
			shiftingID = Shifting_ID_Letter+ String.format("%04d", shiftingCunt);
		}

		return shiftingID;

	}
	
	public static String raw_Mat_ID(ArrayList<String> arrayList) {
		
		rawMatCount = arrayList.size();
		
		rawMatCount++;
		rawMatID = rawMaetID+ String.format("%04d",rawMatCount);
		
		if (arrayList.contains(rawMatCount)) {
			rawMatCount++;
			rawMatID = rawMaetID+ String.format("%04d", rawMatCount);
		}

		return rawMatID;

	}
	

}
