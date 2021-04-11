import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {

    private int price;
    private int amount;
    private String date;
    private OrderType type;
    private OrderDirection direction;

    public Order(int m_Price, int m_Amount, OrderType m_Type, OrderDirection m_Direction) {
        this.price = m_Price;
        this.amount = m_Amount;
        this.date = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS").format(LocalDateTime.now());
        this.type = m_Type;
        this.direction = m_Direction;
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
