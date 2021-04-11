import java.util.ArrayList;

public class OrderPlan implements DTOPlan{

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
        this.setCycle(order.getAmount()*order.getPrice());
        this.setDate(order.getDate());
        this.setPrice(order.getPrice());
    }

    public void setPrice(int price) {
        this.price = "- Price: " + Integer.toString(price) + "\n";
    }

    public void setAmount(int amount) {
        this.amount = "- Amount: " + Integer.toString(amount) + "\n";
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCycle(int cycle) {
        this.cycle = "- Cycle: " + Integer.toString(cycle) + "\n";
    }

    @Override
    public String toString() {
        return "Order:\n" + price + amount + date + cycle + order;
    }
}
