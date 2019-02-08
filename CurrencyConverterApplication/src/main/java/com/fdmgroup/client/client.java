package com.fdmgroup.client;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channel;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.io.*;
import java.util.*;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;

import com.fdmgroup.data.Currency;
import com.fdmgroup.data.CurrencyController;
import com.fdmgroup.services.XMLDownloader;
import com.fdmgroup.services.XMLParser;
import com.fdmgroup.services.inputValidation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class client {
	
	private final static Logger programmerLog = LogManager.getLogger(client.class);
	public static List<Element> currencyList;
    public static CurrencyController currencyController=new CurrencyController();

	public static void main(String[] args) throws JDOMException {
		
		String currency,currency1;
		double amount;

		XMLParser.parseXML();

        System.out.println("           Currency Converter Menu");
        System.out.println("-------------------------------------------");
        System.out.println("1 - EUR To Other Currencies");
        System.out.println("2 - Other Currencies To EUR");
        System.out.println("3 - Currency To Currency");
        System.out.println("-------------------------------------------\n");
		
		
		System.out.println("Please Select An Option: ");

		Scanner scanner=new Scanner(System.in);
		int choice=scanner.nextInt();
		Scanner scanner1=new Scanner(System.in);
		
		switch(choice)
	    {
			case 1:
				boolean input;

				do {
					input=true;
					System.out.println("Currency to be converted to: ");
					currency=scanner1.nextLine();
					
					if (inputValidation.isNumeric(currency)) {
						System.out.println("Please only enter currency names in the following format: XXX\n");
						input=false;
					}
					
					if (!inputValidation.isUpperCase(currency)) {
						currency=currency.toUpperCase();
					}
				
					if (!inputValidation.checkIfCurrencyInList(currency)) {
						System.out.println("Currency entered not in the list!\n"
								+ "Please Try Again\n");
						input=false;
					}

				} while (input==false);
				System.out.println("Amount to be converted: ");
				amount=scanner1.nextDouble();
				try {
					System.out.println("---------------------------------------------------");
					System.out.println(amount+" EUR equals to "+
							currencyController.convertEUR(currency,amount)+" "+currency);
					
				} catch (NullPointerException e) {
					
					programmerLog.fatal("Currency entered not in the list!\n",e);
		
				}		
				
				break;
				
			case 2:
				
				do {
					input=true;
					System.out.println("Currency to be converted to EUR: ");
					currency=scanner1.nextLine();
					
					if (inputValidation.isNumeric(currency)) {
						System.out.println("Please only enter currency names in the following format: XXX\n");
						input=false;
					}
					
					if (!inputValidation.isUpperCase(currency)) {
						currency=currency.toUpperCase();
					}
				
					if (!inputValidation.checkIfCurrencyInList(currency)) {
						System.out.println("Currency entered not in the list!\n"
								+ "Please Try Again\n");
						input=false;
					}

				} while (input==false);
				
				System.out.println("Amount to be converted: ");
				amount=scanner1.nextDouble();
				try {
					
					System.out.println("---------------------------------------------------");
					System.out.println(amount+" "+currency+" equals to "+
							currencyController.convertCurrency(currency,amount)+" EUR");
					
				} catch (NullPointerException e) {
					
					programmerLog.fatal("Currency entered not in the list!\n",e);
				}				
				
				break;
				
			case 3:

				do {
					input=true;
					System.out.println("Currency to be converted: ");
					currency=scanner1.nextLine();

					if (inputValidation.isNumeric(currency)) {
						System.out.println("Please only enter currency names in the following format: XXX\n");
						input=false;
					}
					
					if (!inputValidation.isUpperCase(currency)) {
						currency=currency.toUpperCase();
					}
				
					if (!inputValidation.checkIfCurrencyInList(currency)) {
						System.out.println("Currency entered not in the list!\n"
								+ "Please Try Again\n");
						input=false;
					}

				} while (input==false);
				
				do {
					input=true;
					System.out.println("Currency to be converted into: ");
					currency1=scanner1.nextLine();
		
					if (inputValidation.isNumeric(currency1)) {
						System.out.println("Please only enter currency names in the following format: XXX\n");
						input=false;
					}
					
					if (!inputValidation.isUpperCase(currency1)) {
						currency1=currency1.toUpperCase();
					}
					
					if (!inputValidation.checkIfCurrencyInList(currency1)) {
						System.out.println("Currency entered not in the list!\n"
								+ "Please Try Again\n");
						input=false;
					}
				} while (input==false);
				
				System.out.println("Amount to be converted: ");
				amount=scanner.nextDouble();
				
				try {
					System.out.println("---------------------------------------------------");
					System.out.println(amount+" "+currency+" equals to "+
					currencyController.convertCurrencies(currency,currency1,amount)+" "+
							currency1);
					
				} catch (NullPointerException e) {
					
					programmerLog.fatal("Currency entered not in the list!\n",e);
				}
				
				break;

			default:
				System.out.println("Please Enter 1 To 3 Only");
	    }
		scanner.close();
		scanner1.close();
	}

	


}
