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
        int input;
        boolean inputValidation = false;
        boolean fileLoaded = false;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello and thank you for choosing Ritzpa\n" +
                "Please, select the action you would like to perform:\n" +
                "*To select your choice, please insert the number of the corresponding option (between 1 and " + menusList.size() + ")\n");
        for (MenuItem menuItem:menusList)
        {
            System.out.println(i + "." + menuItem.tabName);
            i++;
        }
        System.out.println("\nPlease insert your choice here: ");
        try {
            while (!inputValidation)
            {
                input = scanner.nextInt();
                if(input < 1 || input > menusList.size()){
                    System.out.println("The input you provided is out of range, please select a number between 1 and " + menusList.size() + "\n");
                }else if(!scanner.hasNextInt()){
                    System.out.println("The input you provided isn't a number, pleaseselect a number between 1 and " + menusList.size() + "\n");
                }else if((input != 1 || input != menusList.size()) && !fileLoaded){
                    System.out.println("There is no data loaded in the system, please select option 1 and load a new file");
                }else {
                    System.out.println("Input was selected successfully\n");
                    inputValidation = true;
                }
            }
        }catch (Exception exception){
            System.out.println("The was an exception:\n" + Arrays.toString(exception.getStackTrace()));
        }

        //get input
        //validate input
        //selected
    }

    private IInputObject getInputObject(InputObjectType inputObjectType){
        InputObjectType inputObjectType1;
        switch (inputObjectType) {
            case FILEREAD: {
                //file path
                //engine
                break;
            }
            case SHOWALLSTOCKS: {
                //stocks array list
                //builder
                //plan
                break;
            }
            case SHOWSINGLESTOCK: {
                //Stock
                //builder
                //plan
                break;
            }
            case ORDERACTION: {
                //Order
                //builder
                //plan
                break;
            }
            case SHOWALLORDERS: {
                //stocks array list
                //builder
                //plan
                break;
            }
            case EXIST:{
                //Input Obeject
            }
        }



        return inputObject;
    }
    private boolean inputValidation(){
        boolean inputValidation = false;


        return inputValidation;
    }
}
