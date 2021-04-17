import java.util.ArrayList;

public class OrdersListPlan implements IDTOPlan {

    private final ArrayList<Stock> stocksList;
    private String data;

    public OrdersListPlan(ArrayList<Stock> stockList) {
        this.stocksList = stockList;
        this.setData();
    }

    @Override
    public void setData() {
        this.setDataField(this.stocksList);
    }

    public void setDataField(ArrayList<Stock> stocksList) {
        StringBuilder stringToBuild = new StringBuilder("\n*************\nOrders List\n*************\n");
        int i = 1;
        for(Stock stock:stocksList)
        {
            SingleStockPlan newStock = new SingleStockPlan(stock);
            stringToBuild.append("\n#").append(i).append(" Stock: ").append(newStock.getCompanyName())
                         .append(newStock.getSellingOrdersList())
                         .append(newStock.getSumSellingOrders())
                         .append(newStock.getBuyingOrdersList())
                         .append(newStock.getSumBuyingOrders())
                         .append(newStock.getDealsList())
                         .append(newStock.getSumCycle());
            i++;
        }
        this.data = stringToBuild.toString();
    }

    @Override
    public String toString() {
        return data;
    }
}
