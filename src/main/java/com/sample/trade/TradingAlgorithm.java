package com.sample.trade;

import com.sample.trade.def.Price;
import com.sample.trade.def.Trade;

public interface TradingAlgorithm {
	
	public void intialise(String[] products);
	
	/**
	* Builds a trade to be executed based on the supplied prices.
	* @param price data
	* @return trade to execute
	*/
	Trade buildTrades(Price price);

}
