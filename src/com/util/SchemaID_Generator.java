package com.util;

import java.util.ArrayList;

public class SchemaID_Generator {
	
	public static final String Schema_ID_Letter = "SC";

	public static String SchemaID;

	public static int SchemaScnt;

	public static String ID_Generator(ArrayList<String> arrayList) {

		SchemaScnt = arrayList.size();

		SchemaScnt++;
		SchemaID = Schema_ID_Letter + String.format("%04d", SchemaScnt);

		if (arrayList.contains(SchemaID)) {
			SchemaScnt++;
			SchemaID = Schema_ID_Letter + String.format("%04d", SchemaScnt);
		}

		return SchemaID;

	}

}
