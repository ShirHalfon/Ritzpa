import java.util.ArrayList;

public class OrdersListPlan implements DTOPlan{

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
        StringBuilder stringToBuild = new StringBuilder("*************\nOrders List\n*************\n");
        int i = 1;
        for(Stock stock:stocksList)
        {
            SingleStockPlan newStock = new SingleStockPlan(stock);
            stringToBuild.append("#").append(i).append(" Stock: ").append(newStock.getCompanyName()).append("\n")
                         .append(newStock.getSellingOrdersList())
                         .append(newStock.getBuyingOrdersList())
                         .append(newStock.getDealsList());
            i++;
        }
        this.data = stringToBuild.toString();
    }

    @Override
    public String toString() {
        return data;
    }
}
