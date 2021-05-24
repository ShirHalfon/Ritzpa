import Generated.RizpaStockExchangeDescriptor;
import Generated.RseStock;
import Generated2.RseItem;

import javax.annotation.Generated;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Client {
    private final String JAXB_XML_GAME_PACKAGE_NAME = "Generated";

    public void ReadingANewFile(IInputObject inputObject) throws Exception {
        /** Add the reading of the users, item, holdings*/
        IDTOBuilder builder;
        IDTOPlan plan;
        ConcreteEngine engine = new ConcreteEngine();
        ReadingANewFileInputObejct readingANewFileInputObejct = ((ReadingANewFileInputObejct)inputObject);
        engine.fileNameCheck(readingANewFileInputObejct.fileNameToReadFrom);
        File file=new File(readingANewFileInputObejct.fileNameToReadFrom);
        InputStream inputStream = new FileInputStream(readingANewFileInputObejct.fileNameToReadFrom);
        JAXBContext jaxbContext = JAXBContext.newInstance(JAXB_XML_GAME_PACKAGE_NAME);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Generated2.RizpaStockExchangeDescriptor RSEexchangeDescriptor =
                (Generated2.RizpaStockExchangeDescriptor) unmarshaller.unmarshal(file);
        for (Generated2.RseStock stock : RSEexchangeDescriptor.getRseStocks().getRseStock()) {
            Stock stockToAdd = new Stock(stock.getRseCompanyName(), stock.getRseSymbol(), stock.getRsePrice());
            engine.stockCheck(engine,stockToAdd);//if the stock isn't valid an exaption wiil be thrown
            engine.getStockList().add(stockToAdd);
        }
        engine.ConverUsersListFromXMLFile(RSEexchangeDescriptor);
        ((ReadingANewFileInputObejct)inputObject).allStocksInOurDataStructures.stocks = engine.stocks;
    }

    public void ShowAllStocks(IInputObject inputObject){
        ((ShowListsFromAllStocksInputObject)inputObject).builderToInit = new StockListBuilder();
        ((ShowListsFromAllStocksInputObject)inputObject).planToInit =
                ((ShowListsFromAllStocksInputObject)inputObject).builderToInit
                .getDTO(((ShowListsFromAllStocksInputObject)inputObject).stocks);
    }

    public void ShowSingleStock(IInputObject inputObject) {
        ((SingleStockInputObject)inputObject).builderToInit = new SingleStockBuilder();
        ((SingleStockInputObject)inputObject).planToInit = ((SingleStockInputObject)inputObject)
                .builderToInit.getDTO(((SingleStockInputObject)inputObject).stockToShow);

    }

    //public void OrderAction(IInputObject inputObject) throws Exception {
    //    Boolean isOrderComplete = false;
    //    //Validations
    //    Stock stockToCommitOrderOn = ((OrderActionInputObject)inputObject).engine
    //            .findStockBySymbol(((OrderActionInputObject)inputObject).orderToCommit.getSymbol());
    //    if (stockToCommitOrderOn == null) {
    //        throw new Exception("The is no stock with the Symbol " +
    //                ((OrderActionInputObject)inputObject).orderToCommit.getSymbol());
    //    }
    //    stockToCommitOrderOn.setNumberOfNewDeals(0);
    //    if (((OrderActionInputObject)inputObject).orderToCommit.getAmount() <= 0) {
    //        throw new Exception("The amount of the stock is 0");
    //    }
    //    if (((OrderActionInputObject)inputObject).orderToCommit.getPrice() <= 0) {
    //        throw new Exception("The price of the stock is 0");
    //    }
    //    if (((OrderActionInputObject)inputObject).orderToCommit.getDirection() == OrderDirection.BUYING) {
    //        //LMT is the max to spend
    //        //meet with SEELING orders
    //        ((OrderActionInputObject)inputObject).engine
    //                .buyingAction(((OrderActionInputObject)inputObject).orderToCommit,
    //                        isOrderComplete, stockToCommitOrderOn);
    //    } else {
    //        //LMT is the min price
    //        //meet with BUYING orders
    //        ((OrderActionInputObject)inputObject).engine
    //                .sellingAction(((OrderActionInputObject)inputObject).orderToCommit,
    //                        isOrderComplete, stockToCommitOrderOn);
//
    //    }
    //}

    public void OrderAction(IInputObject inputObject) throws Exception {
        OrderActionInputObject orderActionInputObject = (OrderActionInputObject)inputObject;
        if(orderActionInputObject.orderToCommit.getType() == OrderType.LMT) {
            orderActionInputObject.engine.LMTOrder(orderActionInputObject);
        }
        else if(orderActionInputObject.orderToCommit.getType() == OrderType.MKT) {
            orderActionInputObject.engine.MKTOrder(orderActionInputObject);
        }
    }

    public void ShowOrdersForAllStocks(IInputObject inputObject){
        ((ShowListsFromAllStocksInputObject)inputObject).builderToInit = new OrdersListBuilder();
        ((ShowListsFromAllStocksInputObject)inputObject).planToInit =
                ((ShowListsFromAllStocksInputObject)inputObject).builderToInit
                .getDTO(((ShowListsFromAllStocksInputObject)inputObject).stocks);

    }

    public void Exit(IInputObject inputObject) {
        System.out.println("Bye Bye!! :)");
        System.exit(0);
    }
}
