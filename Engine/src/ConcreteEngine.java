import Generated2.RseItem;

import java.util.*;


public class ConcreteEngine {

    ArrayList<Stock>    stocks = new ArrayList<>();
    Hashtable<String, User> users = new Hashtable<>();

    public ArrayList<Stock> getStockList() {
        return this.stocks;
    }

    public Stock findStockByName(String stockName) {
        for (Stock stock : this.getStockList()) {
            if (stockName.compareToIgnoreCase(stock.getCompanyName()) == 0)
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

        for (Iterator<Order> iter = stockToCommitOrderOn.getSellingOrders().iterator(); iter.hasNext(); ) {
            Order orderToCheck = iter.next();
            if (!isOrderComplete) {
                //go over all the seling order and try to commit
                if (orderToCheck.getPrice() <= buyingOrder.getPrice()) {
                    if (orderToCheck.getAmount() == buyingOrder.getAmount()) {
                        /*add the deal to the DealsList*/
                        isOrderComplete = sellinOrderAndBuyingOrderHaveEqualAmount_BUYING(buyingOrder, stockToCommitOrderOn, iter, orderToCheck.getPrice());
                    } else if (orderToCheck.getAmount() > buyingOrder.getAmount()) {
                        isOrderComplete = buyingOrderAmountBiggerThanGivenOrderAmounnt_BUYING(buyingOrder, orderToCheck, stockToCommitOrderOn);
                    } else {
                        sellingOrderAmountBiggerThanGivenOrderAmounnt_BUYING(buyingOrder, orderToCheck, stockToCommitOrderOn, iter, orderToCheck.getPrice());
                    }
                }
            }
        }
        if (!isOrderComplete) {
            /*add the order to the buyinglist*/
            stockToCommitOrderOn.findPlaceToInsertBuyingOrder(buyingOrder);
        }
    }

    private boolean sellinOrderAndBuyingOrderHaveEqualAmount_BUYING(Order buyingOrder, Stock stockToCommitOrderOn, Iterator<Order> iter, int price) {
        Deal deal = new Deal(price, buyingOrder.getAmount());
        stockToCommitOrderOn.getDealsList().add(deal);
        stockToCommitOrderOn.setStockPrice(deal.getPrice());
        stockToCommitOrderOn.setNumberOfNewDeals(stockToCommitOrderOn.getNumberOfNewDeals() + 1);
        iter.remove();
        return true;
    }

    private void sellingOrderAmountBiggerThanGivenOrderAmounnt_BUYING(Order buyingOrder, Order sellingOrder, Stock stockToCommitOrderOn, Iterator<Order> iter, int price) {
        Deal deal = new Deal(price, sellingOrder.getAmount());
        buyingOrder.setAmount(buyingOrder.getAmount() - sellingOrder.getAmount());
        iter.remove();
        stockToCommitOrderOn.getDealsList().add(deal);
        stockToCommitOrderOn.setStockPrice(deal.getPrice());
        stockToCommitOrderOn.setNumberOfNewDeals(stockToCommitOrderOn.getNumberOfNewDeals() + 1);
        /*remove the sell order from the selling order list*/
    }

    private boolean buyingOrderAmountBiggerThanGivenOrderAmounnt_BUYING(Order buyingOrder, Order sellingOrder, Stock stockToCommitOrderOn) {
        sellingOrder.setAmount(sellingOrder.getAmount() - buyingOrder.getAmount());
        Deal deal = new Deal(sellingOrder.getPrice(), buyingOrder.getAmount());
        stockToCommitOrderOn.getDealsList().add(deal);
        stockToCommitOrderOn.setStockPrice(deal.getPrice());
        stockToCommitOrderOn.setNumberOfNewDeals(stockToCommitOrderOn.getNumberOfNewDeals() + 1);
        return true;
    }

    public void sellingAction(Order sellingOrder, Boolean isOrderComplete, Stock stockToCommitOrderOn) {
        for (Iterator<Order> iter = stockToCommitOrderOn.getBuyingOrders().iterator(); iter.hasNext(); ) {
            Order orderToCheck = iter.next();
            if (!isOrderComplete) {
                //go over all the seling order and try to commit
                if (orderToCheck.getPrice() >= sellingOrder.getPrice()) {
                    if (orderToCheck.getAmount() == sellingOrder.getAmount()) {
                        isOrderComplete = sellinOrderAndBuyingOrderHaveEqualAmount_SELLING(sellingOrder, stockToCommitOrderOn, iter, orderToCheck.getPrice());
                    } else if (orderToCheck.getAmount() > sellingOrder.getAmount()) {
                        isOrderComplete = buyingOrderAmountBiggerThanGivenOrderAmounnt_SELLING(sellingOrder, orderToCheck, stockToCommitOrderOn);
                    } else {
                        sellingOrderAmountBiggerThanGivenOrderAmounnt_SELLING(sellingOrder, orderToCheck, stockToCommitOrderOn, iter);
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
        stockToCommitOrderOn.setNumberOfNewDeals(stockToCommitOrderOn.getNumberOfNewDeals() + 1);
        return true;
        /*add the deal to the DealsList*/
    }

    private void sellingOrderAmountBiggerThanGivenOrderAmounnt_SELLING(Order sellingOrder, Order buyingOrder, Stock stockToCommitOrderOn, Iterator<Order> iter) {
        Deal deal = new Deal(buyingOrder.getPrice(), buyingOrder.getAmount());
        sellingOrder.setAmount(sellingOrder.getAmount() - buyingOrder.getAmount());
        iter.remove();
        stockToCommitOrderOn.getDealsList().add(deal);
        stockToCommitOrderOn.setNumberOfNewDeals(stockToCommitOrderOn.getNumberOfNewDeals() + 1);
        stockToCommitOrderOn.setStockPrice(deal.getPrice());
    }

    private boolean buyingOrderAmountBiggerThanGivenOrderAmounnt_SELLING(Order sellingOrder, Order buyingOrder, Stock stockToCommitOrderOn) {
        buyingOrder.setAmount(buyingOrder.getAmount() - sellingOrder.getAmount());
        Deal deal = new Deal(buyingOrder.getPrice(), sellingOrder.getAmount());
        stockToCommitOrderOn.getDealsList().add(deal);
        stockToCommitOrderOn.setStockPrice(deal.getPrice());
        stockToCommitOrderOn.setNumberOfNewDeals(stockToCommitOrderOn.getNumberOfNewDeals() + 1);
        return true;
        /*add the deal to the DealsList*/
    }

    public void fileNameCheck(String fileName) throws Exception {
        String fileFormat = ".xml";
        int stringLen = fileName.length();
        if (fileName.substring(stringLen - 4, stringLen).compareTo(fileFormat) != 0)
            throw new Exception("Incorrect file format, Please insert an XML file only");
    }

    public void stockCheck(ConcreteEngine engine, Stock stockToCheck) throws Exception {
        if (stockToCheck.getSymbol().length() == 0)
            throw new Exception("The symbol of " + stockToCheck.getCompanyName() + " is an empty string");
        else for (int i = 0; i < stockToCheck.getSymbol().length(); i++) {//check if english letters
            if (!(((stockToCheck.getSymbol().charAt(i) >= 'A') && (stockToCheck.getSymbol().charAt(i) <= 'Z')) || (((stockToCheck.getSymbol().charAt(i) >= 'a') && (stockToCheck.getSymbol().charAt(i) <= 'z')))))
                throw new Exception("There is a non-letter char in the symbol of " + stockToCheck.getCompanyName());
        }
        if (stockToCheck.getStockPrice() <= 0) {
            throw new Exception("The stock price is a non possitive number.");
        }
        //GENERAL
        if (findStockBySymbol(stockToCheck.getSymbol()) != null) {
            throw new Exception("The Symbol " + stockToCheck.getSymbol() + " already exists.");
        }
        Stock stockWithTheSameName = findStockByName(stockToCheck.getCompanyName(), engine);
        if (stockWithTheSameName != null) {//there is a stock with the same name
            //need to check the symbol
            if ((stockToCheck.getSymbol().compareTo(stockWithTheSameName.getSymbol())) != 0) {
                throw new Exception("The company " + stockToCheck.getCompanyName() + " already exists with different symbol");
            }
        }
    }

    public static Stock findStockByName(String stockName, ConcreteEngine engine) {
        for (Stock stock : engine.getStockList()) {
            if (stockName.compareToIgnoreCase(stock.getCompanyName()) == 0)
                return stock;
        }
        return null;
    }


    public void MKTOrder(OrderActionInputObject orderActionInputObject) throws Exception {
        if( orderActionInputObject.orderToCommit.getDirection() == OrderDirection.BUYING){
            MTKOrderBuyingDirection(orderActionInputObject);
        } else{
            MTKOrderSellingDirection(orderActionInputObject);
        }
    }

    private void MTKOrderBuyingDirection(OrderActionInputObject orderActionInputObject){
        boolean isOrderComplete = false;

        Stock stockToCommitOrderOn =
                findStockBySymbol(orderActionInputObject.orderToCommit.getSymbol());
        for (Iterator<Order> iter = stockToCommitOrderOn.getSellingOrders().iterator();
             iter.hasNext() && !isOrderComplete; ) {
            Order orderToCommitActiomOn = iter.next();
            Order buyingOrder = orderActionInputObject.orderToCommit;
            buyingOrder.setPrice(orderToCommitActiomOn.getPrice());
            isOrderComplete = buyingAction_MKT(orderToCommitActiomOn, buyingOrder, stockToCommitOrderOn, iter);
            orderToCommitActiomOn.setAmount(buyingOrder.getAmount());
        }
        if(!isOrderComplete)
        {
            orderActionInputObject.orderToCommit.setPrice(stockToCommitOrderOn.getSellingOrders().get(0).getPrice());
            stockToCommitOrderOn.findPlaceToInsertBuyingOrder(orderActionInputObject.orderToCommit);
        }
    }

    private boolean buyingAction_MKT(Order orderToCommitActiomOn, Order buyingOrder, Stock stockToCommitOrderOn, Iterator<Order> iter){
        boolean isOrderComplete = false;
        if (orderToCommitActiomOn.getAmount() == buyingOrder.getAmount()) {
            /*add the deal to the DealsList*/
            isOrderComplete = sellinOrderAndBuyingOrderHaveEqualAmount_BUYING(buyingOrder, stockToCommitOrderOn, iter, orderToCommitActiomOn.getPrice());
        } else if (orderToCommitActiomOn.getAmount() > buyingOrder.getAmount()) {
            isOrderComplete = buyingOrderAmountBiggerThanGivenOrderAmounnt_BUYING(buyingOrder, orderToCommitActiomOn, stockToCommitOrderOn);
        } else {
            sellingOrderAmountBiggerThanGivenOrderAmounnt_BUYING(buyingOrder, orderToCommitActiomOn, stockToCommitOrderOn, iter, orderToCommitActiomOn.getPrice());
        }

        return isOrderComplete;
    }

    private void MTKOrderSellingDirection(OrderActionInputObject orderActionInputObject){
        boolean isOrderComplete = false;

        Stock stockToCommitOrderOn =
                findStockBySymbol(orderActionInputObject.orderToCommit.getSymbol());
        for (Iterator<Order> iter = stockToCommitOrderOn.getBuyingOrders().iterator();
             iter.hasNext() && !isOrderComplete; ) {
            Order orderToCommitActiomOn = iter.next();
            Order sellingOrder = orderActionInputObject.orderToCommit;
            sellingOrder.setPrice(orderToCommitActiomOn.getPrice());
            isOrderComplete = sellingAction_MKT(orderToCommitActiomOn, sellingOrder, stockToCommitOrderOn, iter);
            orderToCommitActiomOn.setAmount(sellingOrder.getAmount());
        }
        if(!isOrderComplete)
        {
            orderActionInputObject.orderToCommit.setPrice(stockToCommitOrderOn.getSellingOrders().get(0).getPrice());
            stockToCommitOrderOn.findPlaceToInsertBuyingOrder(orderActionInputObject.orderToCommit);
        }
    }

    private boolean sellingAction_MKT(Order orderToCommitActiomOn,Order sellingOrder, Stock stockToCommitOrderOn, Iterator<Order> iter) {
        boolean isOrderComplete = false;

        if (orderToCommitActiomOn.getAmount() == sellingOrder.getAmount()) {
            isOrderComplete = sellinOrderAndBuyingOrderHaveEqualAmount_SELLING(sellingOrder, stockToCommitOrderOn, iter, orderToCommitActiomOn.getPrice());
        } else if (orderToCommitActiomOn.getAmount() > sellingOrder.getAmount()) {
            isOrderComplete = buyingOrderAmountBiggerThanGivenOrderAmounnt_SELLING(sellingOrder, orderToCommitActiomOn, stockToCommitOrderOn);
        } else {
            sellingOrderAmountBiggerThanGivenOrderAmounnt_SELLING(sellingOrder, orderToCommitActiomOn, stockToCommitOrderOn, iter);
        }

        return isOrderComplete;
    }

    public void LMTOrder(OrderActionInputObject orderActionInputObject) throws Exception {
        Boolean isOrderComplete = false;
        //Validations
        Stock stockToCommitOrderOn = orderActionInputObject.engine
                .findStockBySymbol(orderActionInputObject.orderToCommit.getSymbol());
        {
            if (stockToCommitOrderOn == null) {
                throw new Exception("The is no stock with the Symbol " +
                        orderActionInputObject.orderToCommit.getSymbol());
            }
            stockToCommitOrderOn.setNumberOfNewDeals(0);
            if (orderActionInputObject.orderToCommit.getAmount() <= 0) {
                throw new Exception("The amount of the stock is 0");
            }
            if (orderActionInputObject.orderToCommit.getPrice() <= 0) {
                throw new Exception("The price of the stock is 0");
            }
        }
        if (orderActionInputObject.orderToCommit.getDirection() == OrderDirection.BUYING) {
            //LMT is the max to spend
            //meet with SEELING orders
            orderActionInputObject.engine
                    .buyingAction(orderActionInputObject.orderToCommit,
                            isOrderComplete, stockToCommitOrderOn);
        } else {
            //LMT is the min price
            //meet with BUYING orders
            orderActionInputObject.engine
                    .sellingAction(orderActionInputObject.orderToCommit,
                            isOrderComplete, stockToCommitOrderOn);

        }
    }

    private void itemOfUserCheck(UserItem itemToCheck) throws Exception {
        if(findStockBySymbol(itemToCheck.getSymbol()) == null)
            throw new Exception(String.format("There is no stock with the symbol: {0}",itemToCheck.getSymbol()));
        if (itemToCheck.getAmount()>1)
            throw new Exception(String.format("The amount is incorect. The given amount is: {0}",
                    itemToCheck.getAmount()));
    }

    public void ConverUsersListFromXMLFile(Generated2.RizpaStockExchangeDescriptor RSEexchangeDescriptor) throws Exception{

        for(Generated2.RseUser user :  RSEexchangeDescriptor.getRseUsers().getRseUser())
        {
            List<UserItem> userItemList = new ArrayList<>();
            for(RseItem item : user.getRseHoldings().getRseItem())
            {
                UserItem itemToAdd = new UserItem();
                itemToAdd.setAmount(item.getQuantity());
                itemToAdd.setSymbol(item.getSymbol());
                itemOfUserCheck(itemToAdd);
                userItemList.add(itemToAdd);
            }

            User userToAdd = new User(user.getName(), userItemList);
            user.getName();
            if(users.get(user.getName()) == null)//Add if not exists.
                users.put(user.getName(), userToAdd);
            else throw  new Exception(String.format("The user with the name: {0} is allready exists.", user.getName()));
        }

    }
}
