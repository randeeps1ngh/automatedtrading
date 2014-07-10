This solution consists of maven project.In order to run it simply import it to eclipse or use maven install from command prompt.
I have used Apache Log4j for logging statements.

Automated Trading implements TradingAlgorithm interface.It intialises concurrent hashmap of product name and MovingAvgWindow.
MovingAvgWindow uses queue based approach to maintain moving prices and average calculations.I have synchronized getTradeDirection
method to make it thread safe.I am doing average calculations and I prefer to lock whole method.
I am using TradeDirection enum type to represent Buy , Sell or None directions.

I have used double instead of BigDecimal for product prices as for average caculation its fair to use double and we are comparing it to 
first product price value.There could be other way to solve this problem by using producer consumer pattern on the prices queue but that 
will require more time to implement and test.

Price and Trade class represents simple implementation of given requirements.

I have added AutomatedTradingTest for testing the functionality. First test algoTradeTest contains desired output and I expect system to return 
null if there is no trade to be made for given product and price.When trade algo is satisfied then we get a valid trade of product,direction,price
and quantity.

I have written concurrentAlgoTradeTest for testing AutomatedTrading with multiple threads.I am using ExecutorService to run the system using multiple threads.
This test is not complete but it helped me to fix some boundary conditions check to be added for the implementation and get some idea how it will behave for multiple 
threads.





