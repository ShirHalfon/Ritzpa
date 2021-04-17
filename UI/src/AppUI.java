import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class AppUI {

    private final Client client;
    private IDTOBuilder builder;
    private final IDTOPlan plan;
    private String fileName;
    private final ConcreteEngine coreEngine;
    private final ArrayList<MenuItem> menusList;
    private IInputObject inputObject;
    private final InputObjectFactory factory;

    public AppUI() {
        this.client = new Client();
        this.coreEngine = new ConcreteEngine();
        this.menusList = new ArrayList<>();
        this.initCommands();
        this.inputObject = null;
        this.builder = null;
        this.plan = null;
        this.factory = new InputObjectFactory();
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


    public void run(){
        int i = 1;
        int input = 0;
        boolean inputValidation = false;
        boolean fileLoaded = false;
        Scanner scanner = new Scanner(System.in);
        InputObjectType inputObjectType = null;

        System.out.println("Hello and thank you for choosing Ritzpa\n" +
                "\nPlease, select the action you would like to perform:\n" +
                "*To select your choice, please insert the number of the corresponding option (between 1 and " + menusList.size() + ")\n");
        printMenu(this.menusList);
        try{
            while(true){
                while (!inputValidation)
                {
                    if(!scanner.hasNextInt()){
                        System.out.println("ERROR: The input you provided isn't a number. Please select a number between 1 and " + menusList.size());
                        scanner.nextLine();
                    }
                    else{
                        input = scanner.nextInt();
                        if(input < 1 || input > menusList.size()){
                            System.out.println("ERROR: The input you provided is out of range. Please select a number between 1 and " + menusList.size());
                        }else if((input != 1 && input != menusList.size()) && !fileLoaded){
                            System.out.println("ERROR: There is no data loaded in the system. Please select option 1 and load a new file");
                        }else {

                            System.out.println("The operation was selected successfully");
                            try{
                                inputObjectType = InputObjectType.values()[input -1];
                                getInputObject(inputObjectType);
                                selected(this.inputObject, input);
                                fileLoaded = true;
                                inputValidation = true;
                            }catch (Exception exception){
                                fileLoaded = false;
                                inputValidation = false;
                                System.out.println("ERROR: The was an exception:\n" + exception.getMessage());
                                System.out.println( "\nPlease, select the action you would like to perform:\n" +
                                        "*To select your choice, please insert the number of the corresponding option (between 1 and " + menusList.size() + ")\n");
                                printMenu(this.menusList);
                            }
                        }
                    }
                }
                System.out.println( "\nPlease, select the action you would like to perform:\n" +
                        "*To select your choice, please insert the number of the corresponding option (between 1 and " + menusList.size() + ")\n");
                printMenu(this.menusList);
                inputValidation = false;
            }
        }catch (Exception exception){
            System.out.println("ERROR: The was an exception:\n" + exception.getMessage());
        }
    }

    private void printMenu(ArrayList<MenuItem> menusList){
        int i = 1;
        for (MenuItem menuItem:menusList)
        {
            System.out.println(i + "." + menuItem.tabName);
            i++;
        }
        System.out.println("\nPlease insert your choice here: ");
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
                getDetailsForOrder(inputObjectType);
                break;
            }
            case SHOWALLORDERS: {
                getDetailsForShowAllOrders(inputObjectType);
                break;
            }
            case EXIT:{
                getDetailsForExit(inputObjectType);
            }
        }
    }

    private void getDetailsForFile(InputObjectType inputObjectType){
        Scanner scanner = new Scanner(System.in);
        boolean inputValidation = false;
        String filePath;
        System.out.println("Please insert input file path and note it must be an XML file:");
        while (!inputValidation){
            try{
                filePath = scanner.nextLine();
                if(filePath.charAt(filePath.length()-1) == '"' || filePath.charAt(0) == '"'){
                    System.out.println("ERROR: Please provide file path without quotation marks");
                }else{
                    coreEngine.fileNameCheck(filePath);
                    this.inputObject = this.factory.createInputObject(inputObjectType,filePath, coreEngine);
                    inputValidation = true;
                }
            }catch (Exception exception){
                System.out.println("ERROR: Something went wrong with this action:" + exception.getMessage());
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
                    System.out.println("ERROR: The stock doesn't exist in the system, please select another");
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
            System.out.println("ERROR: Something went wrong with showing a single stock:\n" + exception.getStackTrace());
        }
    }

    private void getDetailsForOrder(InputObjectType inputObjectType){
        Scanner scanner = new Scanner(System.in);
        boolean inputValidation = false;
        int price = -1, amount = -1;
        OrderType type = OrderType.LMT;
        OrderDirection direction;
        String symbol;
        String tempInput;

        while(!inputValidation){
            System.out.println("Please enter the wanted price (higher than 0):");
            do{
                String tempInputForPrice = scanner.nextLine();
                try{
                    price = Integer.parseInt(tempInputForPrice);
                    if(price<=0){
                        System.out.println("ERROR: Please enter price that is higher than 0:");
                    }
                }catch (NumberFormatException exception){
                    System.out.println("ERROR: Please insert the price in digits only");
                }
            }while (price<=0);
            System.out.println("Please enter the wanted amount (higher than 0):");
            do{
                String tempInputForAmount = scanner.nextLine();
                try{
                    amount = Integer.parseInt(tempInputForAmount);
                    if(amount<=0){
                        System.out.println("ERROR: Please enter amount that is higher than 0:");
                    }
                }catch (NumberFormatException exception){
                    System.out.println("ERROR: Please insert the amount in digits only");
                }
            }while (amount<=0);
            System.out.println("Would you like to sell stocks or buy stocks?(for selling insert s/S and for buying insert b/B)");
            tempInput = scanner.nextLine();
            while (tempInput.compareToIgnoreCase("s") != 0 && tempInput.compareToIgnoreCase("b") != 0) {
                System.out.println("ERROR: The order type you provided is invalid. Please enter s or b only");
                tempInput = scanner.nextLine();
            }
            if (tempInput.compareToIgnoreCase("s") == 0) {
                direction = OrderDirection.SELLING;
            } else {
                direction = OrderDirection.BUYING;
            }
            System.out.println("Please enter symbol:");
            symbol = scanner.nextLine();
            while (coreEngine.findStockBySymbol(symbol) == null) {
                System.out.println("ERROR: This stock doesn't exist in the system. Please enter a valid symbol:");
                symbol = scanner.nextLine();
            }
            Order orderToCommit = new Order(price, amount, type, direction, symbol);
            this.inputObject = this.factory.createInputObject(inputObjectType, orderToCommit, this.coreEngine);
            inputValidation = true;
        }
    }

    private void getDetailsForShowAllOrders(InputObjectType inputObjectType){
        this.inputObject = this.factory.createInputObject(inputObjectType, this.coreEngine.stocks, this.builder, this.plan);
    }

    private void getDetailsForExit(InputObjectType inputObjectType){
        this.inputObject = this.factory.createInputObject(inputObjectType);
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

    private void selected(IInputObject inputObject, int actionInput) throws Exception{
        this.menusList.get(actionInput-1).command.execute(inputObject);
        if(inputObject != null){
            System.out.println(inputObject.toString());
            if(inputObject.getClass().getName().equals(OrderActionInputObject.class.getName())){
                printNewDeals(inputObject);
            }
        }
    }

    private void printNewDeals(IInputObject inputObject){
        Stock stockToShowDeals = coreEngine.findStockBySymbol(((OrderActionInputObject)inputObject).orderToCommit.getSymbol());
        if(stockToShowDeals.getNumberOfNewDeals()>0){
            System.out.println("New deals were made!!!");
            int i = stockToShowDeals.getDealsList().size()-stockToShowDeals.getNumberOfNewDeals();
            int listSize = stockToShowDeals.getDealsList().size();
            while(i<listSize){
                System.out.println(stockToShowDeals.getDealsList().get(i).toString());
                i++;
            }
        }
    }
}
