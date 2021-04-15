import Generated.RizpaStockExchangeDescriptor;
import Generated.RseStock;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class Client {
//some changes
    private final String JAXB_XML_GAME_PACKAGE_NAME = "Generated";

    public void ReadingANewFile(IInputObject inputObject,ConcreteEngine engine) throws Exception {
        /**the UI checks the FILE NAME
         * This shoule not be STATIC!!!**/
        /*
         * Need to read the XML     V
         * Validation on Data       V
         * Copy all the data        V
         * */
        IDTOBuilder builder;
        IDTOPlan plan;
        System.out.println("From File to Object");
        engine.FileNameCheck(((ReadingANewFileInputObejct)inputObject).i_FileNameToReadFrom);
        File file=new File(((ReadingANewFileInputObejct)inputObject).i_FileNameToReadFrom);
        InputStream inputStream = new FileInputStream(((ReadingANewFileInputObejct)inputObject).i_FileNameToReadFrom);
        JAXBContext jaxbContext = JAXBContext.newInstance(JAXB_XML_GAME_PACKAGE_NAME);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        RizpaStockExchangeDescriptor RSEexchangeDescriptor = (RizpaStockExchangeDescriptor) unmarshaller.unmarshal(file);
        for (RseStock stock : RSEexchangeDescriptor.getRseStocks().getRseStock()) {
            Stock stockToAdd = new Stock(stock.getRseCompanyName(), stock.getRseSymbol(), stock.getRsePrice());
            engine.StockCheck(((ReadingANewFileInputObejct)inputObject).allStocksInOurDataStructures,stockToAdd);//if the stock isn't valid an exaption wiil be thrown
            ((ReadingANewFileInputObejct)inputObject).allStocksInOurDataStructures.getStockList().add(stockToAdd);
        }

    }

    public void ShowAllStocks(IInputObject inputObject) throws Exception{
        /**EACH SHOW ISN'T REALLY SHOW - RETURN AN OBJECT THAT WILL BE SHOWN IN THE UI NO PRINTS FROM HERE (USE BUILDER)
         **/
        /*
         * For each stock show:
         * 1. Symbol
         * 2. Company Name
         * 3. Price
         * 4. number of Deals made so far
         * 5. Sum of all deals made so far (can use the Stream Method)
         * Note: This can't be chosen without a file in database
         * */
        try {
            ((ShowListsFromAllStocksInputObject)inputObject).builderToInit = new StockListBuilder();
            ((ShowListsFromAllStocksInputObject)inputObject).planToInit =
                    ((ShowListsFromAllStocksInputObject)inputObject).builderToInit
                    .getDTO(((ShowListsFromAllStocksInputObject)inputObject).stocks);
            }
        catch (Exception exception) {
            throw exception;
        }
    }

    public void ShowSingleStock(IInputObject inputObject) {
        /**EACH SHOW ISN'T REALLY SHOW - RETURN AN OBJECT THAT WILL BE SHOWN IN THE UI NO PRINTS FROM HERE (USE BUILDER)
         **/
        /*
         * 1. Symbol
         * 2. Company Name
         * 3. Price
         * 4. number of Deals made so far
         * 5. Sum of all deals made so far (can use the Stream Method)
         * 6. Show all deals
         *      a. Copy the list
         *      b. Sort by latest to earliest
         *      c. For each deal show:
         *          - Date (with the specified format)
         *          - Amount of stocks
         *          - Price
         *          - Total worth
         * */
        try {
            ((SingleStockInputObject)inputObject).builderToInit = new SingleStockBuilder();
            ((SingleStockInputObject)inputObject).planToInit = ((SingleStockInputObject)inputObject)
                    .builderToInit.getDTO(((SingleStockInputObject)inputObject).stockToShow);
        }
        catch (Exception exception) {
            throw exception;
        }
    }

    public void OrderAction(IInputObject inputObject) throws Exception {
        /**EACH SHOW ISN'T REALLY SHOW - RETURN AN OBJECT THAT WILL BE SHOWN IN THE UI NO PRINTS FROM HERE (USE BUILDER)**/
        /* 1. Input:
         *       a. Type (sell/buy)
         *       b. Symbol (can be in any case, use ignorecase method)
         *       c. Amount
         *       d. Limit - Notice the restrictions in the spec
         * 2. Validations:
         *       a. Symbol - does it exist                                  V
         *       b. Amount > 0                                              V
         * 3. Add to Order a timestamp                                      V
         * 4. Activate action
         *       a. Selling will be encountered with Buying orders
         *       b. Buying will be encountered with Selling orders
         * Note: note all the specifications - This is the heart of the exercise - Think of how and where to actually
         * implement it
         * */

        Boolean isOrderComplete = false;
        //Validations
        Stock stockToCommitOrderOn = ((OrderActionInputObject)inputObject).engine
                .findStockBySymbol(((OrderActionInputObject)inputObject).orderToCommit.getSymbol());
        if (stockToCommitOrderOn == null) {
            throw new Exception("The is no stock with the Symbol " +
                    ((OrderActionInputObject)inputObject).orderToCommit.getSymbol());
        }
        if (((OrderActionInputObject)inputObject).orderToCommit.getAmount() <= 0) {
            throw new Exception("The amount of the stock is 0");
        }
        if (((OrderActionInputObject)inputObject).orderToCommit.getDirection() == OrderDirection.BUYING) {
            //LMT is the max to spend
            //meet with SEELING orders
            ((OrderActionInputObject)inputObject).engine
                    .BuyingAction(((OrderActionInputObject)inputObject).orderToCommit,
                            isOrderComplete, stockToCommitOrderOn);
        } else {
            //LMT is the min price
            //meet with BUYING orders
            ((OrderActionInputObject)inputObject).engine
                    .SellingAction(((OrderActionInputObject)inputObject).orderToCommit,
                            isOrderComplete, stockToCommitOrderOn);

        }
    }

    public void ShowOrdersForAllStocks(IInputObject inputObject) throws Exception {

        /**EACH SHOW ISN'T REALLY SHOW - RETURN AN OBJECT THAT WILL BE SHOWN IN THE UI NO PRINTS FROM HERE (USE BUILDER)
         **/
        /*
         * 1. For each Stock(!!) show the following lists:
         *       a. Buying Orders
         *       b. Selling Orders
         *       c. Deals Made
         * 2. For each Order show the following:
         *       a. Date
         *       b. Amount
         *       c. Price
         *       d. Cycle
         * 3. Sum of the Cycle for each list
         * */
        try {
            ((ShowListsFromAllStocksInputObject)inputObject).builderToInit = new OrdersListBuilder();
            ((ShowListsFromAllStocksInputObject)inputObject).planToInit =
                    ((ShowListsFromAllStocksInputObject)inputObject).builderToInit
                    .getDTO(((ShowListsFromAllStocksInputObject)inputObject).stocks);
        }
        catch (Exception exception) {
            throw exception;
        }
    }

    public void Exit(IInputObject inputObject) {

        /*
         * Exit the system
         * If we can make it - add the option to save the system's status - using serialization
         * If we save then we need to add loading the status if it exists
         * */

        System.exit(0);
    }
}
