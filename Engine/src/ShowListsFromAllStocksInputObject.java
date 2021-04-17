import java.util.ArrayList;

public class ShowListsFromAllStocksInputObject implements IInputObject{

    public ArrayList<Stock> stocks;
    public IDTOBuilder builderToInit;
    public IDTOPlan planToInit;

    public ShowListsFromAllStocksInputObject(ArrayList<Stock> stocks, IDTOBuilder builderToInit, IDTOPlan planToInit) {
        this.stocks = stocks;
        this.builderToInit = builderToInit;
        this.planToInit = planToInit;
    }

    public ArrayList<Stock> getStocks() {
        return stocks;
    }

    public IDTOBuilder getBuilderToInit() {
        return builderToInit;
    }

    public IDTOPlan getPlanToInit() {
        return planToInit;
    }

    public void setStocks(ArrayList<Stock> stocks) {
        this.stocks = stocks;
    }

    public void setBuilderToInit(IDTOBuilder builderToInit) {
        this.builderToInit = builderToInit;
    }

    public void setPlanToInit(IDTOPlan planToInit) {
        this.planToInit = planToInit;
    }

    @Override
    public String toString() {
        return  planToInit.toString();
    }
}
