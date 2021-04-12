import java.util.ArrayList;
import java.util.Iterator;


public class ConcreteEngine {

    ArrayList<Stock> stocks = new ArrayList<>();
    /**need to add show method for the list to activate in Client*/

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
    }

    public void BuyingAction(Order buyingOrder, Boolean isOrderComplete, Stock stockToCommitOrderOn) {

        for(Iterator<Order> iter=stockToCommitOrderOn.getSellingOrders().iterator();iter.hasNext();){
            Order orderToCheck= iter.next();
            if (!isOrderComplete) {
                //go over all the seling order and try to commit
                if (orderToCheck.getPrice() <= buyingOrder.getPrice()) {
                    if (orderToCheck.getAmount() == buyingOrder.getAmount()) {
                        /*add the deal to the DealsList*/
                        isOrderComplete=SellinOrderAndBuyingOrderHaveEqualAmount_BUYING(buyingOrder, stockToCommitOrderOn,iter, orderToCheck.getPrice());
                    } else if (orderToCheck.getAmount() > buyingOrder.getAmount()) {
                        isOrderComplete=BuyingOrderAmountBiggerThanGivenOrderAmounnt_BUYING(buyingOrder,orderToCheck,stockToCommitOrderOn);
                    } else {
                        SellingOrderAmountBiggerThanGivenOrderAmounnt_BUYING(buyingOrder,orderToCheck,stockToCommitOrderOn,iter, orderToCheck.getPrice());
                    }
                }
            }
        }
        if (!isOrderComplete) {
            /*add the order to the buyinglist*/
            stockToCommitOrderOn.findPlaceToInsertBuyingOrder(buyingOrder);
        }
    }

    private boolean SellinOrderAndBuyingOrderHaveEqualAmount_BUYING(Order buyingOrder, Stock stockToCommitOrderOn, Iterator<Order> iter,int price){
        Deal deal = new Deal(price, buyingOrder.getAmount());
        stockToCommitOrderOn.getDealsList().add(deal);
        iter.remove();
        return true;
    }

    private void SellingOrderAmountBiggerThanGivenOrderAmounnt_BUYING(Order buyingOrder, Order sellingOrder, Stock stockToCommitOrderOn, Iterator<Order> iter,int price) {
        Deal deal = new Deal(price, sellingOrder.getAmount());
        buyingOrder.setAmount(buyingOrder.getAmount() - sellingOrder.getAmount());
        iter.remove();
        stockToCommitOrderOn.getDealsList().add(deal);
        /*remove the sell order from the selling order list*/
    }

    private boolean BuyingOrderAmountBiggerThanGivenOrderAmounnt_BUYING(Order buyingOrder, Order sellingOrder, Stock stockToCommitOrderOn) {
        sellingOrder.setAmount(sellingOrder.getAmount() - buyingOrder.getAmount());
        Deal deal = new Deal(sellingOrder.getPrice(), buyingOrder.getAmount());
        stockToCommitOrderOn.getDealsList().add(deal);
    return true;
    }

    public void SellingAction(Order sellingOrder, Boolean isOrderComplete, Stock stockToCommitOrderOn) {
        for(Iterator<Order> iter=stockToCommitOrderOn.getBuyingOrders().iterator();iter.hasNext();){
            Order orderToCheck=iter.next();
            if (!isOrderComplete) {
                //go over all the seling order and try to commit
                if (orderToCheck.getPrice() >= sellingOrder.getPrice()) {
                    if(orderToCheck.getAmount()== sellingOrder.getAmount()) {
                        isOrderComplete=SellinOrderAndBuyingOrderHaveEqualAmount_SELLING(sellingOrder, stockToCommitOrderOn, iter,orderToCheck.getPrice());
                    }
                    else if(orderToCheck.getAmount()>sellingOrder.getAmount()){
                        isOrderComplete=BuyingOrderAmountBiggerThanGivenOrderAmounnt_SELLING(sellingOrder,orderToCheck,stockToCommitOrderOn);
                    }
                    else{
                        SellingOrderAmountBiggerThanGivenOrderAmounnt_SELLING(sellingOrder,orderToCheck,stockToCommitOrderOn,iter);
                    }
                }
            }
        }
        if (!isOrderComplete) {
            /*add the order to the buyinglist*/
            stockToCommitOrderOn.findPlaceToInsertSellingOrder(sellingOrder);
        }
    }

    private boolean SellinOrderAndBuyingOrderHaveEqualAmount_SELLING(Order sellingOrder, Stock stockToCommitOrderOn, Iterator<Order> iter,int price) {
        Deal deal = new Deal(price, sellingOrder.getAmount());
        stockToCommitOrderOn.getDealsList().add(deal);
        iter.remove();
        return true;
        /*add the deal to the DealsList*/
    }

    private void SellingOrderAmountBiggerThanGivenOrderAmounnt_SELLING(Order sellingOrder, Order buyingOrder, Stock stockToCommitOrderOn,Iterator<Order> iter){
        Deal deal = new Deal(buyingOrder.getPrice(), buyingOrder.getAmount());
        sellingOrder.setAmount(sellingOrder.getAmount()- buyingOrder.getAmount());
        iter.remove();
        stockToCommitOrderOn.getDealsList().add(deal);
    }

    private boolean BuyingOrderAmountBiggerThanGivenOrderAmounnt_SELLING(Order sellingOrder, Order buyingOrder, Stock stockToCommitOrderOn) {
        buyingOrder.setAmount(buyingOrder.getAmount()- sellingOrder.getAmount());
        Deal deal = new Deal(buyingOrder.getPrice(), sellingOrder.getAmount());
        stockToCommitOrderOn.getDealsList().add(deal);
        return true;
        /*add the deal to the DealsList*/
    }

}
