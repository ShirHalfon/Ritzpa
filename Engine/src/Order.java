import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {

    private int price;
    private int amount;
    private String date;
    private OrderType type;
    private OrderDirection direction;
    private String symbol;
    private int cycle;

    public Order(int price, int amount, OrderType type, OrderDirection direction, String symbol) {
        this.price = price;
        this.amount = amount;
        this.date = DateTimeFormatter.ofPattern("HH:mm:ss:SSS").format(LocalDateTime.now());
        this.type = type;
        this.direction = direction;
        this.symbol=symbol;
        this.cycle = price*amount;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public OrderType getType() {
        return type;
    }

    public OrderDirection getDirection() {
        return direction;
    }

    public int getCycle() {
        return cycle;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    public void setDirection(OrderDirection direction) {
        this.direction = direction;
    }

    public void setCycle(int cycle) {
        this.cycle = cycle;
    }

    @Override
    public String toString() {
        String str = new String("Order details:\n" +
                                        "- Price: " + price + "\n" +
                                        "- Amount: " + amount + "\n" +
                                        "- Date: " + date.toString() + "\n" +
                                        "- Type: " + type + "\n" +
                                        "- Direction: " + direction + "\n");

        return str;
    }
}
