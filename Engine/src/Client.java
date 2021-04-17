import Generated.RizpaStockExchangeDescriptor;
import Generated.RseStock;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class Client {
    private final String JAXB_XML_GAME_PACKAGE_NAME = "Generated";

    public void ReadingANewFile(IInputObject inputObject) throws Exception {

        IDTOBuilder builder;
        IDTOPlan plan;
        ConcreteEngine engine = new ConcreteEngine();
        engine.fileNameCheck(((ReadingANewFileInputObejct)inputObject).fileNameToReadFrom);
        File file=new File(((ReadingANewFileInputObejct)inputObject).fileNameToReadFrom);
        InputStream inputStream = new FileInputStream(((ReadingANewFileInputObejct)inputObject).fileNameToReadFrom);
        JAXBContext jaxbContext = JAXBContext.newInstance(JAXB_XML_GAME_PACKAGE_NAME);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        RizpaStockExchangeDescriptor RSEexchangeDescriptor = (RizpaStockExchangeDescriptor) unmarshaller.unmarshal(file);
        for (RseStock stock : RSEexchangeDescriptor.getRseStocks().getRseStock()) {
            Stock stockToAdd = new Stock(stock.getRseCompanyName(), stock.getRseSymbol(), stock.getRsePrice());
            engine.stockCheck(engine,stockToAdd);//if the stock isn't valid an exaption wiil be thrown
            engine.getStockList().add(stockToAdd);
        }
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

    public void OrderAction(IInputObject inputObject) throws Exception {
        Boolean isOrderComplete = false;
        //Validations
        Stock stockToCommitOrderOn = ((OrderActionInputObject)inputObject).engine
                .findStockBySymbol(((OrderActionInputObject)inputObject).orderToCommit.getSymbol());
        if (stockToCommitOrderOn == null) {
            throw new Exception("The is no stock with the Symbol " +
                    ((OrderActionInputObject)inputObject).orderToCommit.getSymbol());
        }
        stockToCommitOrderOn.setNumberOfNewDeals(0);
        if (((OrderActionInputObject)inputObject).orderToCommit.getAmount() <= 0) {
            throw new Exception("The amount of the stock is 0");
        }
        if (((OrderActionInputObject)inputObject).orderToCommit.getPrice() <= 0) {
            throw new Exception("The price of the stock is 0");
        }
        if (((OrderActionInputObject)inputObject).orderToCommit.getDirection() == OrderDirection.BUYING) {
            //LMT is the max to spend
            //meet with SEELING orders
            ((OrderActionInputObject)inputObject).engine
                    .buyingAction(((OrderActionInputObject)inputObject).orderToCommit,
                            isOrderComplete, stockToCommitOrderOn);
        } else {
            //LMT is the min price
            //meet with BUYING orders
            ((OrderActionInputObject)inputObject).engine
                    .sellingAction(((OrderActionInputObject)inputObject).orderToCommit,
                            isOrderComplete, stockToCommitOrderOn);

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
