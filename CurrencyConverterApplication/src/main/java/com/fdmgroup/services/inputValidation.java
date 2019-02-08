package com.fdmgroup.services;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.client.client;

public class inputValidation {
	private final static Logger programmerLog = LogManager.getLogger(inputValidation.class);

	public static boolean isNumeric(String strNum) {
	    try {
	    	
	        double d = Double.parseDouble(strNum);
	        int i=Integer.parseInt(strNum);
	        
	    } catch (NumberFormatException e) {
	    	
		//	programmerLog.fatal("Not an integer");
			
	        return false;
	    }
	    return true;
	}
	
	public static boolean isUpperCase(String str) {
		
		return str==str.toUpperCase();
	}
	
	public static boolean checkIfCurrencyInList(String currency) {
		
		if (currency.equals("EUR")) {
			return true;
		}
		else {
			Map<String, Double> currencyList=client.currencyController.getCurrencyRates();
			return currencyList.containsKey(currency);
		}

	}
	
}
