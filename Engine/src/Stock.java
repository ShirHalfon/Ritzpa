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

    public Stock(String companyName, String m_Symbol, int stockPrice) {
        this.companyName = companyName;
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

    public void findPlaceToInsertBuyingOrder(Order buyingORderToAdd)
    {
        int i=0;
        boolean isAded=false;

        for (Order order:this.buyingOrders) {
            if(!isAded) {
                if (order.getPrice() < buyingORderToAdd.getPrice())
                    isAded=true;
                ++i;
            }
        }
        this.buyingOrders.add(i, buyingORderToAdd);
    }

    public void findPlaceToInsertSellingOrder(Order sellingORderToAdd)
    {
        int i=0;
        boolean isAded=false;
        for (Order order:this.sellingOrders){
            if(!isAded) {
                if (order.getPrice() > sellingORderToAdd.getPrice())
                    isAded=true;
                ++i;
            }
        }
        this.sellingOrders.add(i, sellingORderToAdd);
    }
}
