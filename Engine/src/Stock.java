import com.sun.xml.internal.txw2.annotation.XmlElement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
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

    public Stock(String m_companyName, String m_Symbol, int stockPrice) {
        this.m_companyName = m_companyName;
        this.m_Symbol = m_Symbol;
        this.m_StockPrice = stockPrice;
        this.m_DealsList = new ArrayList<>();
        this.m_BuyingOrders = new ArrayList<>();
        this.m_SellingOrders = new ArrayList<>();
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

        //Needs improvement!!!!! The way the lists are printed
        String str = new String("Stock Details: " + "\n" +
                                        "- Company Name: " + m_companyName.toString() + "\n"+
                                        "- Symbol: " + m_Symbol.toUpperCase() + "\n" +
                                        "- Price: " + m_StockPrice + "\n" +
                                        "- Deals:\n" + m_DealsList.toString() + "\n" +
                                        "- Buying Orders:\n" + m_BuyingOrders.toString() + "\n" +
                                        "- Selling Orders:\n" + m_SellingOrders.toString() + "\n");
        return str;
    }
}
