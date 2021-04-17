import java.util.ArrayList;

public class StocksListPlan implements IDTOPlan {

    private ArrayList<Stock> stocksList;
    private String data;



    public StocksListPlan(ArrayList<Stock> stockList) {
        this.stocksList = stockList;
        this.setData();
    }

    @Override
    public void setData() {
        this.setDataField(this.stocksList);
    }

    public void setDataField(ArrayList<Stock> stocksList) {
        StringBuilder stringToBuild = new StringBuilder("\n*************\nStocks List\n*************\n");
        int i = 1;
        for(Stock stock:stocksList)
        {
            SingleStockPlan newStock = new SingleStockPlan(stock);
            stringToBuild.append("\n#").append(i).append(" Stock\n");
            stringToBuild.append(newStock.getSymbol())
                         .append(newStock.getCompanyName())
                         .append(newStock.getPrice())
                         .append(newStock.getSumDeals())
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
