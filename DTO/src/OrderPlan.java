public class OrderPlan implements IDTOPlan {

    private String price;
    private String amount;
    private String date;
    private String cycle;
    private final Order order;

    public OrderPlan(Order orderToShow) {
        this.order = orderToShow;
        this.setData();
    }

    @Override
    public void setData() {
        this.setAmount(order.getAmount());
        this.setPrice(order.getPrice());
        this.setCycle(order.getCycle());
        this.setDate(order.getDate());
    }

    public void setPrice(int price) {
        this.price = "- Price: " + Integer.toString(price) + "\n";
    }

    public void setAmount(int amount) {
        this.amount = "- Amount: " + Integer.toString(amount) + "\n";
    }

    public void setDate(String date) {
        this.date = "- Date: " + date + "\n";
    }

    public void setCycle(int cycle) {
        this.cycle = "- Cycle: " + Integer.toString(cycle) + "\n";
    }

    public String getCycle() {
        return cycle;
    }

    @Override
    public String toString() {
        return "Order:\n" + price + amount + date + cycle;
    }
}
