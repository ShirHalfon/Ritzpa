import Generated.RizpaStockExchangeDescriptor;
import Generated.RseStock;
import Generated.RseStocks;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;

public class Client {
//some changes
    private final static String JAXB_XML_GAME_PACKAGE_NAME = "Generated";

    public static void ReadingANewFile(String i_FileNameToReadFrom,ConcreteEngine allStocksInOurDataStructures) throws Exception {
        /**the UI checks the FILE NAME**/
        /*
         * Need to read the XML     V
         * Validation on Data
         * Copy all the data        V
         * */
        System.out.println("From File to Object");
        FileNameCheck(i_FileNameToReadFrom);
        File file=new File(i_FileNameToReadFrom);
        InputStream inputStream = new FileInputStream(i_FileNameToReadFrom);
        JAXBContext jaxbContext = JAXBContext.newInstance(JAXB_XML_GAME_PACKAGE_NAME);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        RizpaStockExchangeDescriptor RSEexchangeDescriptor = (RizpaStockExchangeDescriptor) unmarshaller.unmarshal(file);

        for (RseStock stock : RSEexchangeDescriptor.getRseStocks().getRseStock()) {
            Stock stockToAdd = new Stock(stock.getRseCompanyName(), stock.getRseSymbol(), stock.getRsePrice());
            StockCheck(allStocksInOurDataStructures,stockToAdd);//if the stock isn't valid an exaption wiil be thrown
            allStocksInOurDataStructures.getStockList().add(stockToAdd);
        }
        System.out.println(allStocksInOurDataStructures.getStockList().toString());


    }

    private static void FileNameCheck(String fileName) throws Exception {
        String fileFormat=".xml";
        int stringLen=fileName.length();

        if(!fileName.subSequence(stringLen-4,stringLen-1).equals(fileFormat))
            throw new Exception("Incorrect file format.");
    }

    private static void StockCheck(ConcreteEngine engine,Stock stockToCheck) throws Exception
    {/*
        SYMBOL-
        1.English letter only.                      V
        2. not empty                                V
        3. no space                                 V
        4. in upper case                            V
        COMPANY NAME-
        1.
        PRICE-                                      V
        1.possitive number IN stock CTOR            V
        GENERAL-                                    V
        1.for each company name only one symbol     V
         */
        if(stockToCheck.getM_Symbol().length()==0)
            throw new Exception("The symbol of "+stockToCheck.getM_companyName()+" is an empty string");
        else for(int i=0;i<stockToCheck.getM_Symbol().length();i++){
            if(!(((c>='A')&&(c<='Z'))||(((c>='a')&&(c<='z')))))
                throw new Exception("There is a non-letter char in the symbol of "+stockToCheck.getM_companyName());
        }
        if((stockToCheck.getM_Symbol().compareTo(stockToCheck.getM_Symbol().toUpperCase()))!=0){
            throw new Exception("The symbol isnwt in uppercase");
        }
        if(stockToCheck.getM_StockPrice()<=0)
        {
            throw new Exception("the stock price is a non possitive number.");
        }
        //GENERAL
        Stock stockWithTheSameName=findStockByName(stockToCheck.getM_companyName(),engine);
        if(stockWithTheSameName!=null)
        {//there is a stock with the same name
            //need to check the symbol
            if((stockToCheck.getM_Symbol().compareTo(stockWithTheSameName.getM_Symbol()))!=0)
            {
                throw new Exception("The company "+stockToCheck.getM_companyName()+" already exists with different symbol");
            }
        }
    }

    public static Stock findStockByName(String stockName,ConcreteEngine engine)
    {
        for (Stock stock: engine.getStockList()) {
            if(stockName.compareTo(stock.getM_companyName())==0)
                return stock;
        }
        return null;
    }

    public void ShowAllStocks() {
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


    }

    public void ShowSingleStock() {
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
    }

    public void OrderAction() {
        /**EACH SHOW ISN'T REALLY SHOW - RETURN AN OBJECT THAT WILL BE SHOWN IN THE UI NO PRINTS FROM HERE (USE BUILDER)
         **/
        /*
         * 1. Input:
         *       a. Type (sell/buy)
         *       b. Symbol (can be in any case, use ignorecase method)
         *       c. Amount
         *       d. Limit - Notice the restrictions in the spec
         * 2. Validations:
         *       a. Symbol - does it exist
         *       b. Amount > 0
         * 3. Add to Order a timestamp
         * 4. Activate action
         *       a. Selling will be encountered with Buying orders
         *       b. Buying will be encountered with Selling orders
         * Note: note all the specifications - This is the heart of the exercise - Think of how and where to actually
         * implement it
         * */
    }

    public void ShowOrdersForAllStocks() {

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
    }

    public void Exit() {

        /*
         * Exit the system
         * If we can make it - add the option to save the system's status - using serialization
         * If we save then we need to add loading the status if it exists
         * */
    }
}
