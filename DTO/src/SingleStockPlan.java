import java.util.ArrayList;

public class SingleStockPlan implements IDTOPlan {

    private String symbol;
    private String companyName;
    private String price;
    private String sumDeals;
    private String sumCycle;
    private String dealsList;
    private String sellingOrdersList;
    private String buyingOrdersList;
    private String sumSellingOrders;
    private String sumBuyingOrders;
    private final Stock stockToExtractData;

    public SingleStockPlan(Stock inputStockToExtractData) {
        this.stockToExtractData = inputStockToExtractData;
        this.setData();
    }

    @Override
    public void setData() {
        this.setCompanyName(stockToExtractData.getCompanyName());
        this.setDealsList(stockToExtractData.getDealsList());
        this.setPrice(stockToExtractData.getStockPrice());
        this.setSumCycle(stockToExtractData.getDealsList());
        this.setSumDeals(stockToExtractData.getDealsList());
        this.setSymbol(stockToExtractData.getSymbol());
        this.setBuyingOrdersList(stockToExtractData.getBuyingOrders());
        this.setSellingOrdersList(stockToExtractData.getSellingOrders());
        this.setSumSellingOrders(stockToExtractData.getSellingOrders());
        this.setSumBuyingOrders(stockToExtractData.getBuyingOrders());
    }

    public String getSymbol() {
        return symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getPrice() {
        return price;
    }

    public String getSumDeals() {
        return sumDeals;
    }

    public String getSumCycle() {
        return sumCycle;
    }

    public String getDealsList() {
        return dealsList;
    }

    public String getSumSellingOrders() {
        return sumSellingOrders;
    }

    public String getSumBuyingOrders() {
        return sumBuyingOrders;
    }

    public String getSellingOrdersList() {
        return sellingOrdersList;
    }

    public String getBuyingOrdersList() {
        return buyingOrdersList;
    }

    public void setSymbol(String symbol) {
        this.symbol = "- Symbol: " + symbol.toUpperCase() + "\n";
    }

    public void setCompanyName(String companyName) {
        this.companyName = "- Company name: " + companyName + "\n";
    }

    public void setPrice(int price) {
        this.price = "- Price: " + Integer.toString(price) + "\n";
    }

    public void setSumDeals(ArrayList<Deal> dealsList) {
        this.sumDeals = "- Num of deals: " + Long.toString((long) dealsList.size()) + "\n";
    }

    public void setSumCycle(ArrayList<Deal> dealsList) {
        this.sumCycle = "- Sum of all cycles: " + Integer.toString(dealsList.stream().mapToInt(Deal::getCycle).sum()) + "\n";
    }

    public void setSumSellingOrders(ArrayList<Order> sellingOrders) {
        this.sumSellingOrders = "- Sum of all selling orders: " + Integer.toString(sellingOrders.stream().mapToInt(Order::getCycle).sum()) + "\n";
    }

    public void setSumBuyingOrders(ArrayList<Order> buyingOrders) {
        this.sumBuyingOrders = "- Sum of all buying orders: " + Integer.toString(buyingOrders.stream().mapToInt(Order::getCycle).sum()) + "\n";
    }

    public void setDealsList(ArrayList<Deal> dealsList) {
        StringBuilder stringToBuild = new StringBuilder("\n- Deals List:\n");
        if(stockToExtractData.getDealsList().size() == 0)
        {
            stringToBuild.append("\tThere are no deals to present\n");
        }else{
            int i = 1;
            for(Deal deal:dealsList)
            {
                DealPlan newDeal = new DealPlan(deal);
                stringToBuild.append("#").append(i).append(" ");
                stringToBuild.append(newDeal.toString());
                i++;
            }
        }
         this.dealsList = stringToBuild.toString();
    }

    public void setSellingOrdersList(ArrayList<Order> sellingOrdersList) {
        StringBuilder stringToBuild = new StringBuilder("\n- Selling Orders List:\n");
        if(stockToExtractData.getSellingOrders().size() == 0)
        {
            stringToBuild.append("\tThere are no selling orders to present\n");
        }else{
            int i = 1;
            for(Order order:sellingOrdersList)
            {
                OrderPlan newOrder = new OrderPlan(order);
                stringToBuild.append("#").append(i).append(" ");
                stringToBuild.append(newOrder.toString());
                i++;
            }
        }
        this.sellingOrdersList = stringToBuild.toString();
    }

    public void setBuyingOrdersList(ArrayList<Order> buyingOrdersList) {
        StringBuilder stringToBuild = new StringBuilder("\n- Buying Orders List:\n");
        if(stockToExtractData.getBuyingOrders().size() == 0)
        {
            stringToBuild.append("\tThere are no buying orders to present\n");
        }else{
            int i = 1;
            for(Order order:buyingOrdersList)
            {
                OrderPlan newOrder = new OrderPlan(order);
                stringToBuild.append("#").append(i).append(" ");
                stringToBuild.append(newOrder.toString());
                i++;
            }
        }
        this.buyingOrdersList = stringToBuild.toString();
    }

    @Override
    public String toString() {
        return "Stock:\n" + symbol + companyName +  price + sumDeals + sumCycle + dealsList;
    }
}
