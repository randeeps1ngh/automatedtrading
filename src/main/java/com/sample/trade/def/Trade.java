package com.sample.trade.def;

/**
 * Implementation of Trade Class
 * @author RSingh
 *
 */

public class Trade {

	private String productName;

	private TradeDirection position;

	private double tradePrice;

	private Integer qunatity;

	public Trade(String productName, TradeDirection position, double tradePrice, Integer quantity) {

		this.productName = productName;
		this.position = position;
		this.tradePrice = tradePrice;
		this.qunatity = quantity;

	}

	public String getProductName() {
		return productName;
	}

	public TradeDirection getPosition() {
		return position;
	}

	public double getTradePrice() {
		return tradePrice;
	}

	public Integer getQunatity() {
		return qunatity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
		result = prime * result + ((qunatity == null) ? 0 : qunatity.hashCode());
		long temp;
		temp = Double.doubleToLongBits(tradePrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trade other = (Trade) obj;
		if (position != other.position)
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (qunatity == null) {
			if (other.qunatity != null)
				return false;
		} else if (!qunatity.equals(other.qunatity))
			return false;
		if (Double.doubleToLongBits(tradePrice) != Double.doubleToLongBits(other.tradePrice))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Trade [productName=" + productName + ", position=" + position + ", tradePrice=" + tradePrice + ", qunatity=" + qunatity + "]";
	}

}
