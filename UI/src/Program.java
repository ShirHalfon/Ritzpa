/*import com.sun.deploy.util.SessionState;
*import com.sun.security.ntlm.Client;
*import com.sun.xml.internal.ws.api.pipe.Engine;
*import com.sun.xml.internal.ws.api.pipe.Engine.*;
*/
/*import com.sun.security.ntlm.Client;*/
import com.sun.xml.internal.ws.api.pipe.Engine;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;


public class Program {

    public static void main(String[] args) {
  String fileName= new String ("Engine/src/Sources/ex1-small.xml");
    try {
        ConcreteEngine engine=new ConcreteEngine();
        Client.ReadingANewFile(fileName,engine);
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (JAXBException e) {
        e.printStackTrace();
    } catch (Exception e) {
        e.printStackTrace();
    }

        System.out.println("all is good");

   /*ConcreteEngine newEngine = new ConcreteEngine();
    Stock stock1 = new Stock("Google", "GoGl", 50);
    Stock stock2 = new Stock("Microsoft", "MCRSFT", 60);


    Deal newDeal = new Deal(90, 20);
    Deal newDeal1 = new Deal( 90, 20);
    Deal newDeal2 = new Deal(90, 20);
    Order newOrder3 = new Order(70, 30, OrderType.LMT, OrderDirection.BUYING);
    Order newOrder4 = new Order(90, 10, OrderType.LMT, OrderDirection.BUYING);
    Order newOrder5 = new Order(110, 20, OrderType.LMT, OrderDirection.SELLING);
    Order newOrder6 = new Order(80, 10, OrderType.LMT, OrderDirection.SELLING);
    stock1.getM_BuyingOrders().add(newOrder3);
    stock1.getM_BuyingOrders().add(newOrder4);
    stock1.getM_SellingOrders().add(newOrder5);
    stock1.getM_SellingOrders().add(newOrder6);
    stock1.getM_DealsList().add(newDeal);
    stock1.getM_DealsList().add(newDeal1);
    stock1.getM_DealsList().add(newDeal2);

    Deal newDeal3 = new Deal(90, 20);
    Deal newDeal4 = new Deal(90, 20);
    Deal newDeal5 = new Deal(90, 20);
    Order newOrder = new Order(90, 1, OrderType.LMT, OrderDirection.BUYING);
    Order newOrder7 = new Order(80, 10, OrderType.LMT, OrderDirection.BUYING);
    Order newOrder1 = new Order(110, 20, OrderType.LMT, OrderDirection.SELLING);
    Order newOrder2 = new Order(120, 10, OrderType.LMT, OrderDirection.SELLING);
    stock2.getM_BuyingOrders().add(newOrder);
    stock2.getM_BuyingOrders().add(newOrder7);
    stock2.getM_SellingOrders().add(newOrder1);
    stock2.getM_SellingOrders().add(newOrder2);
    stock2.getM_DealsList().add(newDeal3);
    stock2.getM_DealsList().add(newDeal4);
    stock2.getM_DealsList().add(newDeal5);

    newEngine.m_Stocks.add(stock1);
    newEngine.m_Stocks.add(stock2);

    //System.out.println(newEngine.m_Stocks.toString());
    //System.out.println(newDeal.toString());
    //System.out.println(newOrder.toString());
    //System.out.println(stock1);
    //System.out.println(stock2);
     stock1.toString();
*/

    }
}
