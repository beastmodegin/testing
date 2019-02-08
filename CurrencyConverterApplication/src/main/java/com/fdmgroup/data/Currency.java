package com.fdmgroup.data;

import java.util.HashMap;
import java.util.Map;

import org.jdom2.Attribute;
import org.jdom2.Element;

import com.fdmgroup.client.client;

public class Currency {

	String name;
	double rate;
	
	public Currency(String name, double rate) {
		super();
		this.name = name;
		this.rate = rate;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	


}
