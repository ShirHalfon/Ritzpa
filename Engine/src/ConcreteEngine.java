import java.util.ArrayList;


public class ConcreteEngine {

    ArrayList<Stock> stocks = new ArrayList<>();
    //need to add show method for the list to activate in Client

    public ArrayList<Stock> getStockList() {
        return this.stocks;
    }

}
