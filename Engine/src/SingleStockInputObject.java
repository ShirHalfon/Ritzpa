public class SingleStockInputObject implements IInputObject{

    public Stock stockToShow;
    public IDTOBuilder builderToInit;
    public IDTOPlan planToInit;

    public SingleStockInputObject(Stock stockToShow, IDTOBuilder builderToInit, IDTOPlan planToInit) {
        this.stockToShow = stockToShow;
        this.builderToInit = builderToInit;
        this.planToInit = planToInit;
    }

    public Stock getStockToShow() {
        return stockToShow;
    }

    public void setStockToShow(Stock stockToShow) {
        this.stockToShow = stockToShow;
    }

    public IDTOBuilder getBuilderToInit() {
        return builderToInit;
    }

    public void setBuilderToInit(IDTOBuilder builderToInit) {
        this.builderToInit = builderToInit;
    }

    public IDTOPlan getPlanToInit() {
        return planToInit;
    }

    public void setPlanToInit(IDTOPlan planToInit) {
        this.planToInit = planToInit;
    }
}
