import java.util.ArrayList;
public class Stock {

    private final String companyName; //must have c'tor
    private final String symbol;
    private int stockPrice; //Sha'ar
    private ArrayList<Deal> dealsList;
    private ArrayList<Order> buyingOrders;
    private ArrayList<Order> sellingOrders;
    private int numberOfNewDeals;

    public Stock(String companyName, String m_Symbol, int stockPrice) {
        this.companyName = companyName;
        this.symbol = m_Symbol;
        this.stockPrice = stockPrice;
        this.dealsList = new ArrayList<>();
        this.buyingOrders = new ArrayList<>();
        this.sellingOrders = new ArrayList<>();
        this.numberOfNewDeals = 0;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getStockPrice() {
        return stockPrice;
    }

    public ArrayList<Deal> getDealsList() {
        return dealsList;
    }

    public ArrayList<Order> getBuyingOrders() {
        return buyingOrders;
    }

    public ArrayList<Order> getSellingOrders() {
        return sellingOrders;
    }

    public int getNumberOfNewDeals() {
        return numberOfNewDeals;
    }

    public void setStockPrice(int stockPrice) {
        this.stockPrice = stockPrice;
    }

    public void setDealsList(ArrayList<Deal> dealsList) {
        this.dealsList = dealsList;
    }

    public void setBuyingOrders(ArrayList<Order> buyingOrders) {
        this.buyingOrders = buyingOrders;
    }

    public void setSellingOrders(ArrayList<Order> sellingOrders) {
        this.sellingOrders = sellingOrders;
    }

    public void setNumberOfNewDeals(int numberOfNewDeals) {
        this.numberOfNewDeals = numberOfNewDeals;
    }

    public void findPlaceToInsertBuyingOrder(Order buyingORderToAdd) {
        int i=0;
        boolean isAded=false;

        for (Order order:this.buyingOrders) {
            if(!isAded) {
                if (order.getPrice() < buyingORderToAdd.getPrice())
                    isAded=true;
                else{
                ++i;
                }
            }
        }
        this.buyingOrders.add(i, buyingORderToAdd);
    }

    public void findPlaceToInsertSellingOrder(Order sellingORderToAdd) {
        int i=0;
        boolean isAded=false;
        for (Order order:this.sellingOrders){
            if(!isAded) {
                if (order.getPrice() > sellingORderToAdd.getPrice())
                    isAded=true;
                else{
                ++i;
                }
            }
        }
        this.sellingOrders.add(i, sellingORderToAdd);
    }
}
