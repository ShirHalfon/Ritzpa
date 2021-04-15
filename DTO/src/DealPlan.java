public class DealPlan implements IDTOPlan {

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
        this.cycle = "- Cycle: " + Integer.toString(cycle) + "\n";
    }

    public void setPrice(int price) {
        this.price = "- Price: " + Integer.toString(price) + "\n";    }

    public void setAmount(int amount) {
        this.amount = "- Amount: " + Integer.toString(amount) + "\n";
    }

    public void setDate(String date) {
        this.date = "- Date: " + date + "\n";
    }

    @Override
    public String toString() {
        return "Deal:\n" + cycle + price + amount + date + '\n';
    }

}
