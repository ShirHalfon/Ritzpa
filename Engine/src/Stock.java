import java.lang.reflect.Array;
import java.util.ArrayList;

public class Stock {

    /*
    * 1. Add method to work with injection points for the way of the sort with what we have learned yesterday
    *
    *
    * */


    private final String m_companyName; //must have c'tor
    private final String m_Symbol;
    private int m_StockPrice; //Sha'ar
    private ArrayList<Deal> m_DealsList ;
    private ArrayList<Order> m_BuyingOrders;
    private ArrayList<Order> m_SellingOrders;

    public Stock(String m_companyName, String m_Symbol) {
        this.m_companyName = m_companyName;
        this.m_Symbol = m_Symbol;
    }

    public int getM_StockPrice() {
        return m_StockPrice;
    }

    public ArrayList<Deal> getM_DealsList() {
        return m_DealsList;
    }

    public ArrayList<Order> getM_BuyingOrders() {
        return m_BuyingOrders;
    }

    public ArrayList<Order> getM_SellingOrders() {
        return m_SellingOrders;
    }

    public void setM_StockPrice(int m_StockPrice) {
        this.m_StockPrice = m_StockPrice;
    }

    public void setM_DealsList(ArrayList<Deal> m_DealsList) {
        this.m_DealsList = m_DealsList;
    }

    public void setM_BuyingOrders(ArrayList<Order> m_BuyingOrders) {
        this.m_BuyingOrders = m_BuyingOrders;
    }

    public void setM_SellingOrders(ArrayList<Order> m_SellingOrders) {
        this.m_SellingOrders = m_SellingOrders;
    }

    @Override
    public String toString(){

        //Needs improvement!!!!!
        String str = new String("Stock Name: " + m_companyName + "Stock Symbol: " + m_Symbol + "\n");
        return str;
    }
}
