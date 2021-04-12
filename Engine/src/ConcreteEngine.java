import java.util.ArrayList;


public class ConcreteEngine {

    ArrayList<Stock> stocks = new ArrayList<>();
    //need to add show method for the list to activate in Client

    public ArrayList<Stock> getStockList() {
        return this.stocks;
    }

    public static Stock findStockByName(String stockName,ConcreteEngine engine)
    {
        for (Stock stock: engine.getStockList()) {
            if(stockName.compareTo(stock.getCompanyName())==0)
                return stock;
        }
        return null;
    }

    public static Stock findStockBySymbol(String stockSymbol,ConcreteEngine engine)
    {
        for (Stock stock: engine.getStockList()) {
            if(stockSymbol.compareToIgnoreCase(stock.getCompanyName())==0)
                return stock;
        }
        return null;
    }

    public static void OrderAction(Order orderToCommit, ConcreteEngine engine) throws Exception {
        /**If the order didn't go throw--> Exception thrown
         * If the order came throw partly--> flase
         * If the order came throw completly--> true*/

        /**EACH SHOW ISN'T REALLY SHOW - RETURN AN OBJECT THAT WILL BE SHOWN IN THE UI NO PRINTS FROM HERE (USE BUILDER)**/
        /* 1. Input:
         *       a. Type (sell/buy)
         *       b. Symbol (can be in any case, use ignorecase method)
         *       c. Amount
         *       d. Limit - Notice the restrictions in the spec
         * 2. Validations:
         *       a. Symbol - does it exist                                  V
         *       b. Amount > 0                                              V
         * 3. Add to Order a timestamp                                      V
         * 4. Activate action
         *       a. Selling will be encountered with Buying orders
         *       b. Buying will be encountered with Selling orders
         * Note: note all the specifications - This is the heart of the exercise - Think of how and where to actually
         * implement it
         * */

        boolean isOrderComplete = false;
        //Validations
        Stock stockToCommitOrderOn = findStockBySymbol(orderToCommit.getSymbol(), engine);
        if (stockToCommitOrderOn == null) {
            throw new Exception("The is no stock with the Symbol " + orderToCommit.getSymbol());
        }
        if (orderToCommit.getAmount() <= 0) {
            throw new Exception("The amount of the stock is 0");
        }

        if (orderToCommit.getDirection() == OrderDirection.BUYING) {
            //LMT is the max to spend
            //meet with SEELING orders
            Order buyingOrder=orderToCommit;
            for (Order orderToCheck : stockToCommitOrderOn.getSellingOrders()) {
                if (!isOrderComplete) {
                    //go over all the seling order and try to commit
                    if (orderToCheck.getPrice() <= buyingOrder.getPrice()) {
                        if (orderToCheck.getAmount() == buyingOrder.getAmount()) {
                            /*add the deal to the DealsList*/
                            Deal deal = new Deal(buyingOrder.getPrice(), buyingOrder.getAmount());
                            stockToCommitOrderOn.getDealsList().add(deal);
                            isOrderComplete = true;
                        } else if (orderToCheck.getAmount() > buyingOrder.getAmount()) {
                            orderToCheck.setAmount(orderToCheck.getAmount() - buyingOrder.getAmount());
                            Deal deal = new Deal(buyingOrder.getPrice(), buyingOrder.getAmount());
                            isOrderComplete = true;
                            stockToCommitOrderOn.getDealsList().add(deal);
                            /*add the deal to the DealsList*/
                        } else {
                            Deal deal = new Deal(buyingOrder.getPrice(), orderToCheck.getAmount());
                            buyingOrder.setAmount(buyingOrder.getAmount() - orderToCheck.getAmount());
                            stockToCommitOrderOn.getSellingOrders().remove(orderToCheck);
                            stockToCommitOrderOn.getDealsList().add(deal);
                            /*remove the sell order from the selling order list*/
                        }
                    }
                }
            }
            if (!isOrderComplete) {
                /*add the order to the buyinglist*/
                stockToCommitOrderOn.findPlaceToInsertBuyingOrder(buyingOrder);
            }
        } else {
            Order sellingOrder=orderToCommit;
            //LMT is the min price
            //meet with BUYING orders
            for (Order orderToCheck : stockToCommitOrderOn.getSellingOrders()) {
                if (!isOrderComplete) {
                    //go over all the seling order and try to commit
                    if (orderToCheck.getPrice() >= sellingOrder.getPrice()) {
                        if(orderToCheck.getAmount()== sellingOrder.getAmount()) {
                            Deal deal = new Deal(sellingOrder.getPrice(), sellingOrder.getAmount());
                            stockToCommitOrderOn.getDealsList().add(deal);
                            isOrderComplete = true;
                            /*add the deal to the DealsList*/
                        }
                        else if(orderToCheck.getAmount()>sellingOrder.getAmount()){
                            orderToCheck.setAmount(orderToCheck.getAmount()- sellingOrder.getAmount());
                            Deal deal = new Deal(sellingOrder.getPrice(), sellingOrder.getAmount());
                            isOrderComplete = true;
                            stockToCommitOrderOn.getDealsList().add(deal);
                            /*add the deal to the DealsList*/
                        }
                        else{
                            Deal deal = new Deal(sellingOrder.getPrice(), orderToCheck.getAmount());
                            sellingOrder.setAmount(sellingOrder.getAmount()- orderToCheck.getAmount());
                            stockToCommitOrderOn.getBuyingOrders().remove(orderToCheck);
                            stockToCommitOrderOn.getDealsList().add(deal);
                        }
                    }
                }
            }
            if (!isOrderComplete) {
                /*add the order to the buyinglist*/
                stockToCommitOrderOn.findPlaceToInsertSellingOrder(sellingOrder);
            }
        }

    }
}
