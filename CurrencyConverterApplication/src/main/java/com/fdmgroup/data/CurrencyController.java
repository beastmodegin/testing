package com.fdmgroup.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jdom2.Attribute;
import org.jdom2.Element;

import com.fdmgroup.client.client;
import com.fdmgroup.services.XMLParser;

public class CurrencyController {
	private final static Logger programmerLog = LogManager.getLogger(CurrencyController.class);

	List<Currency> currencies=new ArrayList<Currency>();
	Map<String,Double> currencyRates= new HashMap<String, Double>();
	
	public Map<String, Double> getCurrencyRates() {
		return currencyRates;
	}

	public void setCurrencyRates(Map<String, Double> currencyRates) {
		this.currencyRates = currencyRates;
	}

	public void storeCurrency() {
		
	    for (int temp = 0; temp < client.currencyList.size(); temp++) {    
	        Element currency = client.currencyList.get(temp);
	     
	        Attribute attribute =  currency.getAttribute("currency");
	        Attribute attribute2= currency.getAttribute("rate");
	        
	        double rate=new Double(attribute2.getValue());
	        String name=attribute.getValue();
	        
	        currencies.add(new Currency(name,rate));
	        currencyRates.put(name, rate);
/*	        System.out.println("Currency : " 
	           + attribute.getValue() + " Rate : " +attribute2.getValue());*/
	        }
	}
	
	public void printCurrency() {
		
	    for (int temp = 0; temp < currencies.size(); temp++) {
	    	System.out.println("Currency : " 
	 	           +currencies.get(temp).getName() + " Rate : " 
	    			+currencies.get(temp).getRate());
	    }
	}
	
	public double convertEUR(String currency,double amount) {
		
		double amountConverted,convertedRate;

		convertedRate=currencyRates.get(currency);
		//System.out.println(convertedRate);
		amountConverted=convertedRate*amount;
		return amountConverted;		
	}
	public double convertCurrency(String currency,double amount) {
		
		double amountConverted,convertedRate;

		convertedRate=1/currencyRates.get(currency);
		//System.out.println(convertedRate);
		amountConverted=convertedRate*amount;
		return amountConverted;
			
	}

	public double convertCurrencies(String currency, String currency1, double amount) {
		
		double amountConverted,Rate,Rate1,convertedRate;
		
			if (currency.equals("EUR")&&currency1.equals("EUR")) {
				convertedRate=1.0;
				amountConverted=convertedRate*amount;
				return amountConverted;
			}
		
			else if (currency.equals("EUR")) {
				amountConverted=convertEUR(currency1, amount);
				return amountConverted;
			}
			
			else if (currency1.equals("EUR")) {
				amountConverted=convertCurrency(currency, amount);
				return amountConverted;
			}
			
			else {
				Rate=currencyRates.get(currency);
				Rate1=currencyRates.get(currency1);
				convertedRate=Rate1/Rate;
				
				amountConverted=convertedRate*amount;

				return amountConverted;
			}
	}
	
}
