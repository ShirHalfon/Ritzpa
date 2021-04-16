import com.sun.corba.se.pept.encoding.InputObject;
import com.sun.xml.internal.ws.api.pipe.Engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class AppUI {

    private final Client client;
    private IDTOBuilder builder;
    private IDTOPlan plan;
    private String fileName;
    private final ConcreteEngine coreEngine;
    private Order orderToCommit;
    private final ArrayList<MenuItem> menusList;
    private IInputObject inputObject;
    private InputObjectFactory factory;

    public AppUI(ArrayList<MenuItem> commandsList) {
        this.client = new Client();
        this.coreEngine = new ConcreteEngine();
        this.menusList = new ArrayList<>();
        this.inputObject = null;
        this.builder = null;
        this.plan = null;
    }

    private void initCommands(){
        Command command = new Command();
        Command.ReadingANewFileCommand command1 = command.new ReadingANewFileCommand(this.client);
        Command.ShowAllStocksCommand command2 = command.new ShowAllStocksCommand(this.client);
        Command.ShowSingleStockCommand command3 = command.new ShowSingleStockCommand(this.client);
        Command.OrderActionCommand command4 = command.new OrderActionCommand(this.client);
        Command.ShowOrdersForAllStocksCommand command5 = command.new ShowOrdersForAllStocksCommand(this.client);
        Command.ExitCommand command6 = command.new ExitCommand(this.client);

        MenuItem menuItem1 = new MenuItem("Read data from a new file", command1, inputObject);
        MenuItem menuItem2 = new MenuItem("Show all the stocks", command2, inputObject);
        MenuItem menuItem3 = new MenuItem("Show a single stock", command3, inputObject);
        MenuItem menuItem4 = new MenuItem("Sell or Buy stocks", command4, inputObject);
        MenuItem menuItem5 = new MenuItem("Show all the orders of all the stocks", command5, inputObject);
        MenuItem menuItem6 = new MenuItem("Exit the system", command6, inputObject);

        this.menusList.add(menuItem1);
        this.menusList.add(menuItem2);
        this.menusList.add(menuItem3);
        this.menusList.add(menuItem4);
        this.menusList.add(menuItem5);
        this.menusList.add(menuItem6);
    }


    public void run()throws Exception{

        int i = 1;
        int input = 0;
        boolean inputValidation = false;
        boolean fileLoaded = false;
        Scanner scanner = new Scanner(System.in);
        InputObjectType inputObjectType = null;

        System.out.println("Hello and thank you for choosing Ritzpa\n" +
                "Please, select the action you would like to perform:\n" +
                "*To select your choice, please insert the number of the corresponding option (between 1 and " + menusList.size() + ")\n");
        for (MenuItem menuItem:menusList)
        {
            System.out.println(i + "." + menuItem.tabName);
            i++;
        }
        System.out.println("\nPlease insert your choice here: ");
        try{
            while(true){
                while (!inputValidation)
                {
                    input = scanner.nextInt();
                    if(input < 1 || input > menusList.size()){
                        System.out.println("The input you provided is out of range, please select a number between 1 and " + menusList.size());
                    }else if(!scanner.hasNextInt()){
                        System.out.println("The input you provided isn't a number, pleaseselect a number between 1 and " + menusList.size());
                    }else if((input != 1 || input != menusList.size()) && !fileLoaded){
                        System.out.println("There is no data loaded in the system, please select option 1 and load a new file");
                    }else {
                        fileLoaded = true;
                        System.out.println("Input was selected successfully");
                        inputValidation = true;
                    }
                }
                inputObjectType = InputObjectType.values()[input -1];
                getInputObject(inputObjectType);
                selected(this.inputObject);
            }
        }catch (Exception exception){
            System.out.println("The was an exception:\n" + Arrays.toString(exception.getStackTrace()));
        }
    }

    private void getInputObject(InputObjectType inputObjectType){
        switch (inputObjectType) {
            case FILEREAD: {
                getDetailsForFile(inputObjectType);
                break;
            }
            case SHOWALLSTOCKS: {
                getDetailsForShowingAllStocks(inputObjectType);
                break;
            }
            case SHOWSINGLESTOCK: {
                getDetailsForShowingOneStock(inputObjectType);
                break;
            }
            case ORDERACTION: {
                //Order
                //builder
                //plan
                break;
            }
            case SHOWALLORDERS: {
                getDetailsForShowAllOrders(inputObjectType);
                break;
            }
            case EXIST:{
                //Input Obeject
            }
        }
    }

    private void getDetailsForFile(InputObjectType inputObjectType){
        Scanner scanner = new Scanner(System.in);
        boolean inputValidation = false;
        String filePath;
        System.out.println("Please insert input file path and please note it must be an XML file:");
        while (!inputValidation){
            try{
                filePath = scanner.nextLine();
                coreEngine.fileNameCheck(filePath);
                this.inputObject = this.factory.createInputObject(inputObjectType,filePath, coreEngine);
                inputValidation = true;
            }catch (Exception exception){
                System.out.println("Something went wrong with this action:" + exception.getMessage());
            }
        }
    }

    private void getDetailsForShowingAllStocks(InputObjectType inputObjectType){
        this.inputObject = this.factory.createInputObject(inputObjectType,
                this.coreEngine.stocks, this.builder, this.plan);
    }

    private void getDetailsForShowingOneStock(InputObjectType inputObjectType){
        Scanner scanner = new Scanner(System.in);
        String input;
        boolean inputValidation = false;
        System.out.println("Which stock would you like to watch? Please enter name or symbol");
        try{
            while(!inputValidation){
                input = scanner.nextLine();
                if(!findStockByNameOrSymbol(input)){
                    System.out.println("The stock doesn't exist in the system, please select another");
                }else{
                    Stock stock;
                    if ((stock = coreEngine.findStockByName(input)) == null) {
                        stock = coreEngine.findStockBySymbol(input);
                    }
                    this.inputObject = this.factory.createInputObject(inputObjectType, stock, this.builder, this.plan);
                    inputValidation = true;
                }
            }
        }catch (Exception exception){
            System.out.println("Something went wrong with showing a single stock:\n" + exception.getStackTrace());
        }
    }

    private void getDetailsForOrder(InputObjectType inputObjectType){
        //price > 0
        //amount > 0
        //type - lmt hard coded for now
        //direction Selling/Buying
        //symbol -
        boolean inputValidation = false;

    }

    private void getDetailsForShowAllOrders(InputObjectType inputObjectType){
        this.inputObject = this.factory.createInputObject(inputObjectType, this.coreEngine.stocks, this.builder, this.plan);
    }

    private boolean findStockByNameOrSymbol(String input){
        boolean found = false;
        for(Stock stock:this.coreEngine.stocks){
            if((input.compareToIgnoreCase(stock.getCompanyName()) == 0) ||
                    (input.compareToIgnoreCase(stock.getSymbol()) == 0))
                found = true;
        }
        return found;
    }

    private void selected(IInputObject inputObject){

    }
}
