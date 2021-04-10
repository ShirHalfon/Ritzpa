import com.sun.xml.internal.ws.api.pipe.Engine.*;

public class Program {

    public static void main(String[] args) {

    ConcreteEngine newEngine = new ConcreteEngine();
    Stock stock1 = new Stock("Goggle", "GOGL");
    Stock stock2 = new Stock("Microsoft", "MCRSFT");
    newEngine.m_Stocks.add(stock1);
    newEngine.m_Stocks.add(stock2);
    Deal newDeal = new Deal(1, 90, 20);

    System.out.println(newEngine.m_Stocks.toString());
    System.out.println(newDeal.toString());

    }
}
