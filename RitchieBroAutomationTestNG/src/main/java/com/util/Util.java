package com.util;

import java.util.Calendar;

public class Util {

	public static int extractIntegerFromString(String extractString) {
		return Integer.parseInt(extractString.replaceAll("[^0-9]", ""));
	}

	public static int getCurrentYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}
	
	
	
	

}
