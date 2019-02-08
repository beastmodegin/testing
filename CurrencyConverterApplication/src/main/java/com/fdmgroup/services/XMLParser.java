package com.fdmgroup.services;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import com.fdmgroup.client.client;
import com.fdmgroup.data.CurrencyController;

public class XMLParser {
	
	private final static Logger programmerLog = LogManager.getLogger(XMLParser.class);

	public static void parseXML() {
		
		String url="https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";
      //  CurrencyController currencyController=new CurrencyController();
        
		try {
			
			XMLDownloader.downloadXML(url,"currencylist.xml");
			File inputFile=new File("currencylist.xml");
			SAXBuilder saxBuilder=new SAXBuilder();
			Document document= saxBuilder.build(inputFile);
			Element element=document.getRootElement();
/*			System.out.println(element);
			System.out.println(element.getName());*/
			List<Element> currencyList= element.getChildren();
			
//	        System.out.println("---------------------------------------");
	        List<Element> list=element.getChildren();
	      
            Element cube = list.get(2);
           /* System.out.println("\nCurrent Element :" 
               + cube.getName());*/
	       List<Element> secondList=cube.getChildren();
	       Element cube2=secondList.get(0);
/*	       System.out.println("\nCurrent Element :"+cube2.getName()+"\n"+ 
	    		   "Time : "+ cube2.getAttributeValue("time"));
	       System.out.println("---------------------------------------");*/
	       client.currencyList=cube2.getChildren();

            client.currencyController.storeCurrency();
       //     client.currencyController.printCurrency();
            
		} catch (IOException e) {
			
			programmerLog.fatal("IO operation failed!",e);
			
		} catch (JDOMException e) {
	
			programmerLog.fatal("Error!",e);		}
		
	}
}
