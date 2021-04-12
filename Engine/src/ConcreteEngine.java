import java.util.ArrayList;


public class ConcreteEngine {

    ArrayList<Stock> stocks = new ArrayList<>();
    //need to add show method for the list to activate in Client

    public ArrayList<Stock> getStockList() {
        return this.stocks;
    }

    public Stock findStockByName(String stockName) {
        for (Stock stock : this.getStockList()) {
            if (stockName.compareTo(stock.getCompanyName()) == 0)
                return stock;
        }
        return null;
    }

    public Stock findStockBySymbol(String stockSymbol) {
        for (Stock stock : this.getStockList()) {
            if (stockSymbol.compareToIgnoreCase(stock.getSymbol()) == 0)
                return stock;
        }
        return null;
    }

    public void OrderAction(Order orderToCommit, ConcreteEngine engine) throws Exception {
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

        Boolean isOrderComplete = false;
        //Validations
        Stock stockToCommitOrderOn = findStockBySymbol(orderToCommit.getSymbol());
        if (stockToCommitOrderOn == null) {
            throw new Exception("The is no stock with the Symbol " + orderToCommit.getSymbol());
        }
        if (orderToCommit.getAmount() <= 0) {
            throw new Exception("The amount of the stock is 0");
        }

        if (orderToCommit.getDirection() == OrderDirection.BUYING) {
            //LMT is the max to spend
            //meet with SEELING orders
            BuyingAction(orderToCommit, isOrderComplete, stockToCommitOrderOn);
        } else {
            //LMT is the min price
            //meet with BUYING orders
            SellingAction(orderToCommit, isOrderComplete, stockToCommitOrderOn);

        }

        /**printing to check validation*/
        DTOBuilder builder = new SingleStockBuilder();
        DTOPlan plan = builder.getDTO(stockToCommitOrderOn);
        System.out.println(((SingleStockPlan) plan).getBuyingOrdersList());
        System.out.println(((SingleStockPlan) plan).getSellingOrdersList());
        System.out.println(((SingleStockPlan) plan).getDealsList());
        System.out.println("*************************************");
    }

    public void BuyingAction(Order buyingOrder, Boolean isOrderComplete, Stock stockToCommitOrderOn) {
        for (Order orderToCheck : stockToCommitOrderOn.getSellingOrders()) {
            if (!isOrderComplete) {
                //go over all the seling order and try to commit
                if (orderToCheck.getPrice() <= buyingOrder.getPrice()) {
                    if (orderToCheck.getAmount() == buyingOrder.getAmount()) {
                        /*add the deal to the DealsList*/
                        SellinOrderAndBuyingOrderHaveEqualAmount_BUYING(buyingOrder, stockToCommitOrderOn, isOrderComplete);
                    } else if (orderToCheck.getAmount() > buyingOrder.getAmount()) {
                        BuyingOrderAmountBiggerThanGivenOrderAmounnt_BUYING(buyingOrder,orderToCheck,stockToCommitOrderOn,isOrderComplete);
                    } else {
                        SellingOrderAmountBiggerThanGivenOrderAmounnt_BUYING(buyingOrder,orderToCheck,stockToCommitOrderOn);
                    }
                }
            }
        }
        if (!isOrderComplete) {
            /*add the order to the buyinglist*/
            stockToCommitOrderOn.findPlaceToInsertBuyingOrder(buyingOrder);
        }
    }

    private void SellinOrderAndBuyingOrderHaveEqualAmount_BUYING(Order buyingOrder, Stock stockToCommitOrderOn, Boolean isOrderComplete){
        Deal deal = new Deal(buyingOrder.getPrice(), buyingOrder.getAmount());
        stockToCommitOrderOn.getDealsList().add(deal);
        isOrderComplete = true;
    }

    private void SellingOrderAmountBiggerThanGivenOrderAmounnt_BUYING(Order buyingOrder, Order sellingOrder, Stock stockToCommitOrderOn) {
        Deal deal = new Deal(buyingOrder.getPrice(), sellingOrder.getAmount());
        buyingOrder.setAmount(buyingOrder.getAmount() - sellingOrder.getAmount());
        stockToCommitOrderOn.getSellingOrders().remove(sellingOrder);
        stockToCommitOrderOn.getDealsList().add(deal);
        /*remove the sell order from the selling order list*/
    }

    private void BuyingOrderAmountBiggerThanGivenOrderAmounnt_BUYING(Order buyingOrder, Order sellingOrder, Stock stockToCommitOrderOn, Boolean isOrderComplete) {
        sellingOrder.setAmount(sellingOrder.getAmount() - buyingOrder.getAmount());
        Deal deal = new Deal(buyingOrder.getPrice(), buyingOrder.getAmount());
        isOrderComplete = true;
        stockToCommitOrderOn.getDealsList().add(deal);
        /*add the deal to the DealsList*/
    }

    public void SellingAction(Order sellingOrder, Boolean isOrderComplete, Stock stockToCommitOrderOn) {
        for (Order orderToCheck : stockToCommitOrderOn.getSellingOrders()) {
            if (!isOrderComplete) {
                //go over all the seling order and try to commit
                if (orderToCheck.getPrice() >= sellingOrder.getPrice()) {
                    if(orderToCheck.getAmount()== sellingOrder.getAmount()) {
                        SellinOrderAndBuyingOrderHaveEqualAmount_SELLING(sellingOrder, stockToCommitOrderOn, isOrderComplete);
                    }
                    else if(orderToCheck.getAmount()>sellingOrder.getAmount()){
                        BuyingOrderAmountBiggerThanGivenOrderAmounnt_SELLING(sellingOrder,orderToCheck,stockToCommitOrderOn,isOrderComplete);
                    }
                    else{
                        SellingOrderAmountBiggerThanGivenOrderAmounnt_SELLING(sellingOrder,orderToCheck,stockToCommitOrderOn);
                    }
                }
            }
        }
        if (!isOrderComplete) {
            /*add the order to the buyinglist*/
            stockToCommitOrderOn.findPlaceToInsertSellingOrder(sellingOrder);
        }
    }

    private void SellinOrderAndBuyingOrderHaveEqualAmount_SELLING(Order sellingOrder, Stock stockToCommitOrderOn, Boolean isOrderComplete) {
        Deal deal = new Deal(sellingOrder.getPrice(), sellingOrder.getAmount());
        stockToCommitOrderOn.getDealsList().add(deal);
        isOrderComplete = true;
        /*add the deal to the DealsList*/
    }

    private void SellingOrderAmountBiggerThanGivenOrderAmounnt_SELLING(Order sellingOrder, Order buyingOrder, Stock stockToCommitOrderOn){
        Deal deal = new Deal(sellingOrder.getPrice(), buyingOrder.getAmount());
        sellingOrder.setAmount(sellingOrder.getAmount()- buyingOrder.getAmount());
        stockToCommitOrderOn.getBuyingOrders().remove(buyingOrder);
        stockToCommitOrderOn.getDealsList().add(deal);
    }

    private void BuyingOrderAmountBiggerThanGivenOrderAmounnt_SELLING(Order sellingOrder, Order buyingOrder, Stock stockToCommitOrderOn, Boolean isOrderComplete) {
        buyingOrder.setAmount(buyingOrder.getAmount()- sellingOrder.getAmount());
        Deal deal = new Deal(sellingOrder.getPrice(), sellingOrder.getAmount());
        isOrderComplete = true;
        stockToCommitOrderOn.getDealsList().add(deal);
        /*add the deal to the DealsList*/
    }

}
