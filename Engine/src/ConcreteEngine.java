import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;


public class ConcreteEngine {

    ArrayList<Stock> m_Stocks = new ArrayList<>();
    //need to add show method for the list to activate in Client

    public ArrayList<Stock> getStockList() {
        return this.m_Stocks;
    }

}
