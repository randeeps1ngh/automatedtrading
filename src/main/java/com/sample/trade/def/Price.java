package com.sample.trade.def;

/**
 * This class represents price of product and numerical price
 * @author RSingh
 *
 */
public class Price {

	private String productName;
	private double price;

	public Price(String productName, double price) {
		this.productName = productName;
		this.price = price;
	}

	public String getProductName() {
		return productName;
	}

	public double getPrice() {
		return price;
	}	
	
}
