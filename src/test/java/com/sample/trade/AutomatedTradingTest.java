package com.sample.trade;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.sample.trade.TradingAlgorithm;
import com.sample.trade.def.AutomatedTrading;
import com.sample.trade.def.Price;
import com.sample.trade.def.Trade;
import com.sample.trade.def.TradeDirection;

public class AutomatedTradingTest {

	private TradingAlgorithm algoTrading;

	@Before
	public void setUp() {

		algoTrading = new AutomatedTrading();
		String products[] = new String[] { "BP", "RDSA" };
		algoTrading.intialise(products);

	}

	@Test
	public void algoTradeTest() {

		Trade trade = new Trade("BP", TradeDirection.BUY, 7.67, 1000);

		Price price = new Price("BP", 7.61);
		Assert.assertNull(algoTrading.buildTrades(price));
		price = new Price("RDSA", 2201.00);
		Assert.assertNull(algoTrading.buildTrades(price));
		price = new Price("RDSA", 2209.00);
		Assert.assertNull(algoTrading.buildTrades(price));
		price = new Price("BP", 7.66);
		Assert.assertNull(algoTrading.buildTrades(price));
		price = new Price("BP", 7.64);
		Assert.assertNull(algoTrading.buildTrades(price));
		price = new Price("BP", 7.67);
		Assert.assertEquals(trade, algoTrading.buildTrades(price));

	}

	//@Test
	public void concurrentAlgoTradeTest() throws InterruptedException {

		ExecutorService service = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 5; ++i) {
			service.execute(new Runnable() {

				@Override
				public void run() {
					try {
						Price price = new Price("BP", 35.61);
						

						while (true) {
							Trade trade = algoTrading.buildTrades(price);
							double nextPrice = 7.61 * Math.random() * 20;
							price = new Price("BP", nextPrice);
							if (null != trade) {
								System.out.println(trade.toString());
							}
							Thread.sleep(1000);
						}
					} catch (Throwable e) {
						e.printStackTrace();
					}
				}
			});
		}

		service.awaitTermination(5000, TimeUnit.SECONDS);
	}
}
