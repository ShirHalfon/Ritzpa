import java.util.ArrayList;

public class InputObjectFactory {


    IInputObject inputObject;

    public IInputObject getInputObject() {
        return inputObject;
    }

    public void setInputObject(IInputObject inputObject) {
        this.inputObject = inputObject;
    }

    public IInputObject createInputObject(InputObjectType type, Object... objects) {

        switch (type) {
            case FILEREAD: {
                inputObject = new ReadingANewFileInputObejct((String)objects[0], (ConcreteEngine)objects[1]);
                break;
            }
            case SHOWALLSTOCKS: {
                inputObject = new ShowListsFromAllStocksInputObject((ArrayList<Stock>)objects[0], (IDTOBuilder)objects[1], (IDTOPlan)objects[2]);
                break;
            }
            case SHOWSINGLESTOCK: {
                inputObject = new SingleStockInputObject((Stock)objects[0], (IDTOBuilder)objects[1], (IDTOPlan)objects[2]);
                break;
            }
            case ORDERACTION: {
                inputObject = new OrderActionInputObject((Order)objects[0], (ConcreteEngine)objects[1]);
                break;
            }
            case SHOWALLORDERS: {
                inputObject = new ShowListsFromAllStocksInputObject((ArrayList<Stock>)objects[0], (IDTOBuilder)objects[1], (IDTOPlan)objects[2]);
                break;
            }
            case EXIT:{
                System.out.println("[DEBUG] in Factory createInputObject case EXIT");

                inputObject = new ExitInputObject();
            }
        }
        return inputObject;
    }
}