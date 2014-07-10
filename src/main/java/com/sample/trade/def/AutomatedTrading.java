package com.sample.trade.def;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.sample.trade.TradingAlgorithm;

public class AutomatedTrading implements TradingAlgorithm {

	private static final Logger logger = Logger.getLogger(AutomatedTrading.class);

	public static int MOVING_AVG_WINDOW = 4;

	private Map<String, MovingAvgWindow> productPrice = new ConcurrentHashMap<String, MovingAvgWindow>();

	public void intialise(String[] products) {

		logger.debug("Intialising Automated Trader");
		for (String product : products) {

			productPrice.put(product, new MovingAvgWindow(MOVING_AVG_WINDOW));
		}
	}

	/**
	 * This method implements buildTrades method.It firstly looks up for Moving Average Window component for
	 * a given Product name.It gets the trade direction according to algorithm defined in the requirements.
	 */
	public Trade buildTrades(Price price) {

		logger.debug("Automated Trader submitted with Price " + price.getProductName() + "  " + price.getPrice());
		MovingAvgWindow window = productPrice.get(price.getProductName());
		if (TradeDirection.BUY == window.getTradeDirection(price.getPrice())) {
			Trade t = new Trade(price.getProductName(), TradeDirection.BUY, price.getPrice(), 1000);
			if (null != t)
				logger.debug("Trade Genrated" + t.toString());
			return t;
		} else {
			return null;
		}
	}
}
