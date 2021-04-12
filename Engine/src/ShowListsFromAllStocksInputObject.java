import java.util.ArrayList;

public class ShowListsFromAllStocksInputObject implements IInputObject{

    public ArrayList<Stock> stocks;
    public DTOBuilder builderToInit;
    public DTOPlan planToInit;

    public ShowListsFromAllStocksInputObject(ArrayList<Stock> stocks, DTOBuilder builderToInit, DTOPlan planToInit) {
        this.stocks = stocks;
        this.builderToInit = builderToInit;
        this.planToInit = planToInit;
    }

    public ArrayList<Stock> getStocks() {
        return stocks;
    }

    public DTOBuilder getBuilderToInit() {
        return builderToInit;
    }

    public DTOPlan getPlanToInit() {
        return planToInit;
    }

    public void setStocks(ArrayList<Stock> stocks) {
        this.stocks = stocks;
    }

    public void setBuilderToInit(DTOBuilder builderToInit) {
        this.builderToInit = builderToInit;
    }

    public void setPlanToInit(DTOPlan planToInit) {
        this.planToInit = planToInit;
    }
}
