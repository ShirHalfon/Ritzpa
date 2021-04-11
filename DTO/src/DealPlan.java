public class DealPlan implements DTOPlan{

    private String cycle;
    private String price;
    private String amount;
    private String date;
    private final Deal dealToExtractData;

    public DealPlan(Deal dealToExtractData) {
        this.dealToExtractData = dealToExtractData;
        this.setData();
    }

    @Override
    public void setData() {
        this.setAmount(dealToExtractData.getAmount());
        this.setCycle(dealToExtractData.getCycle());
        this.setDate(dealToExtractData.getDate());
        this.setPrice(dealToExtractData.getPrice());
    }

    public void setCycle(int cycle) {
        Integer cycleToExtract = cycle;
        this.cycle = "- Cycle: " + cycleToExtract.toString() + "\n";
    }

    public void setPrice(int price) {
        Integer priceToExtract = price;
        this.price = "- Price: " + priceToExtract.toString() + "\n";    }

    public void setAmount(int amount) {
        Integer amountToExtract = amount;
        this.amount = "- Amount: " +  amountToExtract.toString() + "\n";
    }

    public void setDate(String date) {
        this.date = "- Date: " + date + "\n";
    }

    @Override
    public String toString() {
        return "Deal:\n" + cycle + price + amount + date + '\n';
    }

}
