public class SingleStockInputObject implements IInputObject{

    public Stock stockToShow;
    public DTOBuilder builderToInit;
    public DTOPlan planToInit;

    public SingleStockInputObject(Stock stockToShow, DTOBuilder builderToInit, DTOPlan planToInit) {
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

    public DTOBuilder getBuilderToInit() {
        return builderToInit;
    }

    public void setBuilderToInit(DTOBuilder builderToInit) {
        this.builderToInit = builderToInit;
    }

    public DTOPlan getPlanToInit() {
        return planToInit;
    }

    public void setPlanToInit(DTOPlan planToInit) {
        this.planToInit = planToInit;
    }
}
