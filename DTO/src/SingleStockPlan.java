import java.util.ArrayList;

public class SingleStockPlan implements DTOPlan{

    private String symbol;
    private String companyName;
    private String price;
    private String sumDeals;
    private String sumCycle;
    private String dealsList;
    private final Stock stockToExtractData;

    public SingleStockPlan(Stock inputStockToExtractData) {
        this.stockToExtractData = inputStockToExtractData;
        this.setData();
    }

    @Override
    public void setData() {
        this.setCompanyName(stockToExtractData.getCompanyName());
        this.setDealsList(stockToExtractData.getDealsList());
        this.setPrice(stockToExtractData.getStockPrice());
        this.setSumCycle(stockToExtractData.getDealsList());
        this.setSumDeals(stockToExtractData.getDealsList());
        this.setSymbol(stockToExtractData.getSymbol());
    }

    public String getSymbol() {
        return symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getPrice() {
        return price;
    }

    public String getSumDeals() {
        return sumDeals;
    }

    public String getSumCycle() {
        return sumCycle;
    }

    public void setSymbol(String symbol) {
        this.symbol = "- Symbol: " + symbol.toUpperCase() + "\n";
    }

    public void setCompanyName(String companyName) {
        this.companyName = "- Company name: " + companyName + "\n";
    }

    public void setPrice(int price) {
        this.price = "- Price: " + Integer.toString(price) + "\n";
    }

    public void setSumDeals(ArrayList<Deal> dealsList) {
        this.sumDeals = "- Num of deals: " + Long.toString((long) dealsList.size()) + "\n";
    }

    public void setSumCycle(ArrayList<Deal> dealsList) {
        this.sumCycle = "- Sum of all cycles: " + Integer.toString(dealsList.stream().mapToInt(Deal::getM_Cycle).sum()) + "\n";
    }

    public void setDealsList(ArrayList<Deal> dealsList) {
        StringBuilder stringToBuild = new StringBuilder("- Deals List:\n");
        int i = 1;
        for(Deal deal:dealsList)
        {
            DealPlan newDeal = new DealPlan(deal);
            stringToBuild.append("#").append(i).append(" ");
            stringToBuild.append(newDeal.toString());
            i++;
        }
         this.dealsList = stringToBuild.toString();
    }

    @Override
    public String toString() {
        return "Stock:\n" + symbol + companyName +  price + sumDeals + sumCycle + dealsList + stockToExtractData;
    }
}
