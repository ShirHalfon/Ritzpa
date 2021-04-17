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

    public void buyingAction(Order buyingOrder, Boolean isOrderComplete, Stock stockToCommitOrderOn) {

        for(Iterator<Order> iter=stockToCommitOrderOn.getSellingOrders().iterator();iter.hasNext();){
            Order orderToCheck= iter.next();
            if (!isOrderComplete) {
                //go over all the seling order and try to commit
                if (orderToCheck.getPrice() <= buyingOrder.getPrice()) {
                    if (orderToCheck.getAmount() == buyingOrder.getAmount()) {
                        /*add the deal to the DealsList*/
                        isOrderComplete= sellinOrderAndBuyingOrderHaveEqualAmount_BUYING(buyingOrder, stockToCommitOrderOn,iter, orderToCheck.getPrice());
                    } else if (orderToCheck.getAmount() > buyingOrder.getAmount()) {
                        isOrderComplete= buyingOrderAmountBiggerThanGivenOrderAmounnt_BUYING(buyingOrder,orderToCheck,stockToCommitOrderOn);
                    } else {
                        sellingOrderAmountBiggerThanGivenOrderAmounnt_BUYING(buyingOrder,orderToCheck,stockToCommitOrderOn,iter, orderToCheck.getPrice());
                    }
                }
            }
        }
        if (!isOrderComplete) {
            /*add the order to the buyinglist*/
            stockToCommitOrderOn.findPlaceToInsertBuyingOrder(buyingOrder);
        }
    }

    private boolean sellinOrderAndBuyingOrderHaveEqualAmount_BUYING(Order buyingOrder, Stock stockToCommitOrderOn, Iterator<Order> iter, int price){
        Deal deal = new Deal(price, buyingOrder.getAmount());
        stockToCommitOrderOn.getDealsList().add(deal);
        stockToCommitOrderOn.setStockPrice(deal.getPrice());
        iter.remove();
        return true;
    }

    private void sellingOrderAmountBiggerThanGivenOrderAmounnt_BUYING(Order buyingOrder, Order sellingOrder, Stock stockToCommitOrderOn, Iterator<Order> iter, int price) {
        Deal deal = new Deal(price, sellingOrder.getAmount());
        buyingOrder.setAmount(buyingOrder.getAmount() - sellingOrder.getAmount());
        iter.remove();
        stockToCommitOrderOn.getDealsList().add(deal);
        stockToCommitOrderOn.setStockPrice(deal.getPrice());
        /*remove the sell order from the selling order list*/
    }

    private boolean buyingOrderAmountBiggerThanGivenOrderAmounnt_BUYING(Order buyingOrder, Order sellingOrder, Stock stockToCommitOrderOn) {
        sellingOrder.setAmount(sellingOrder.getAmount() - buyingOrder.getAmount());
        Deal deal = new Deal(sellingOrder.getPrice(), buyingOrder.getAmount());
        stockToCommitOrderOn.getDealsList().add(deal);
        stockToCommitOrderOn.setStockPrice(deal.getPrice());
        return true;
    }

    public void sellingAction(Order sellingOrder, Boolean isOrderComplete, Stock stockToCommitOrderOn) {
        for(Iterator<Order> iter=stockToCommitOrderOn.getBuyingOrders().iterator();iter.hasNext();){
            Order orderToCheck=iter.next();
            if (!isOrderComplete) {
                //go over all the seling order and try to commit
                if (orderToCheck.getPrice() >= sellingOrder.getPrice()) {
                    if(orderToCheck.getAmount()== sellingOrder.getAmount()) {
                        isOrderComplete= sellinOrderAndBuyingOrderHaveEqualAmount_SELLING(sellingOrder, stockToCommitOrderOn, iter,orderToCheck.getPrice());
                    }
                    else if(orderToCheck.getAmount()>sellingOrder.getAmount()){
                        isOrderComplete= buyingOrderAmountBiggerThanGivenOrderAmounnt_SELLING(sellingOrder,orderToCheck,stockToCommitOrderOn);
                    }
                    else{
                        sellingOrderAmountBiggerThanGivenOrderAmounnt_SELLING(sellingOrder,orderToCheck,stockToCommitOrderOn,iter);
                    }
                }
            }
        }
        if (!isOrderComplete) {
            /*add the order to the buyinglist*/
            stockToCommitOrderOn.findPlaceToInsertSellingOrder(sellingOrder);
        }
    }

    private boolean sellinOrderAndBuyingOrderHaveEqualAmount_SELLING(Order sellingOrder, Stock stockToCommitOrderOn, Iterator<Order> iter, int price) {
        Deal deal = new Deal(price, sellingOrder.getAmount());
        stockToCommitOrderOn.getDealsList().add(deal);
        iter.remove();
        stockToCommitOrderOn.setStockPrice(deal.getPrice());
        return true;
        /*add the deal to the DealsList*/
    }

    private void sellingOrderAmountBiggerThanGivenOrderAmounnt_SELLING(Order sellingOrder, Order buyingOrder, Stock stockToCommitOrderOn, Iterator<Order> iter){
        Deal deal = new Deal(buyingOrder.getPrice(), buyingOrder.getAmount());
        sellingOrder.setAmount(sellingOrder.getAmount()- buyingOrder.getAmount());
        iter.remove();
        stockToCommitOrderOn.getDealsList().add(deal);
        stockToCommitOrderOn.setStockPrice(deal.getPrice());
    }

    private boolean buyingOrderAmountBiggerThanGivenOrderAmounnt_SELLING(Order sellingOrder, Order buyingOrder, Stock stockToCommitOrderOn) {
        buyingOrder.setAmount(buyingOrder.getAmount()- sellingOrder.getAmount());
        Deal deal = new Deal(buyingOrder.getPrice(), sellingOrder.getAmount());
        stockToCommitOrderOn.getDealsList().add(deal);
        stockToCommitOrderOn.setStockPrice(deal.getPrice());
        return true;
        /*add the deal to the DealsList*/
    }

    public void fileNameCheck(String fileName) throws Exception {
        String fileFormat=".xml";
        int stringLen=fileName.length();
        if(fileName.substring(stringLen-4,stringLen).compareTo(fileFormat)!=0)
            throw new Exception("Incorrect file format, Please insert an XML file only");
    }

    public void stockCheck(ConcreteEngine engine, Stock stockToCheck) throws Exception {
        /*SYMBOL-
        1.English letter only.                      V
        2. not empty                                V
        3. no space                                 V
        4. in upper case                            V
        COMPANY NAME-
        1.
        PRICE-                                      V
        1.possitive number IN stock CTOR            V
        GENERAL-                                    V
        1.for each company name only one symbol     V
         */
        if(stockToCheck.getSymbol().length()==0)
            throw new Exception("The symbol of "+stockToCheck.getCompanyName()+" is an empty string");
        else for(int i=0;i<stockToCheck.getSymbol().length();i++){
            if(!(((stockToCheck.getSymbol().charAt(i)>='A')&&(stockToCheck.getSymbol().charAt(i)<='Z'))||(((stockToCheck.getSymbol().charAt(i)>='a')&&(stockToCheck.getSymbol().charAt(i)<='z')))))
                throw new Exception("There is a non-letter char in the symbol of "+stockToCheck.getCompanyName());
        }
        if((stockToCheck.getSymbol().compareToIgnoreCase(stockToCheck.getSymbol()))!=0){
            throw new Exception("The symbol isnwt in uppercase");
        }
        if(stockToCheck.getStockPrice()<=0)
        {
            throw new Exception("the stock price is a non possitive number.");
        }
        //GENERAL
        Stock stockWithTheSameName=findStockByName(stockToCheck.getCompanyName(),engine);
        if(stockWithTheSameName!=null)
        {//there is a stock with the same name
            //need to check the symbol
            if((stockToCheck.getSymbol().compareTo(stockWithTheSameName.getSymbol()))!=0)
            {
                throw new Exception("The company "+stockToCheck.getCompanyName()+" already exists with different symbol");
            }
        }
    }

    public static Stock findStockByName(String stockName,ConcreteEngine engine) {
        for (Stock stock: engine.getStockList()) {
            if(stockName.compareTo(stock.getCompanyName())==0)
                return stock;
        }
        return null;
    }

}
