package com.sample.trade.def;

import java.util.LinkedList;
import java.util.Queue;

import org.apache.log4j.Logger;

/**
 * This class contains logic for implementing moving average window.
 * 
 * @author RSingh
 * 
 */
public class MovingAvgWindow {

	private int movingWindowSize;
	private Queue<Double> prices = new LinkedList<Double>();
	private double sum;
	private static final Logger logger = Logger.getLogger(MovingAvgWindow.class);

	public MovingAvgWindow(int movingWindowSize) {
		this.movingWindowSize = movingWindowSize;
	}

	private void newPrice(double price) {
		logger.debug("Adding New Price " + price);
		sum += price;
		prices.add(price);
		if (prices.size() > movingWindowSize) {
			sum -= prices.remove();
		}
	}

	/**
	 * Get Avg for current Moving Window prices.
	 * 
	 * @return
	 */
	private double getAvg() {

		if (prices.isEmpty() || prices.size() < movingWindowSize)
			return 0.0;
		return sum / prices.size();
	}

	/**
	 * This method returns trade direction depending on the algo applied in the
	 * code base.You can apply different logic and derive trade direction
	 * 
	 * @param price
	 * @return
	 */
	public synchronized TradeDirection getTradeDirection(double price) {
		logger.debug("Get Trade Direction Invoked ");
		
		newPrice(price);

		if (!prices.isEmpty() && prices.size() == movingWindowSize) {
			logger.debug("Moving Avg " + getAvg() + "  price " + prices.peek() + " prices size " + prices.size());
			return getAvg() > prices.peek() ? TradeDirection.BUY : TradeDirection.NONE;
		} else
			return TradeDirection.NONE;

	}
}
