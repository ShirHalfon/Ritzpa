import java.util.ArrayList;




public class Stock {

    /*
    * 1. Add method to work with injection points for the way of the sort with what we have learned yesterday
    *
    *
    * */


    private final String companyName; //must have c'tor
    private final String symbol;
    private int stockPrice; //Sha'ar
    private ArrayList<Deal> dealsList;
    private ArrayList<Order> buyingOrders;
    private ArrayList<Order> sellingOrders;

    public Stock(String m_companyName, String m_Symbol, int stockPrice) {
        this.companyName = m_companyName;
        this.symbol = m_Symbol;
        this.stockPrice = stockPrice;
        this.dealsList = new ArrayList<>();
        this.buyingOrders = new ArrayList<>();
        this.sellingOrders = new ArrayList<>();
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

}
