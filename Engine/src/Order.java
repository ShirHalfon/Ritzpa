import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Order {

    private int m_Price;
    private int m_Amount;
    private String m_Date;
    private OrderType m_Type;
    private OrderDirection m_Direction;

    public Order(int m_Price, int m_Amount, OrderType m_Type, OrderDirection m_Direction) {
        this.m_Price = m_Price;
        this.m_Amount = m_Amount;
        this.m_Date = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS").format(LocalDateTime.now());
        this.m_Type = m_Type;
        this.m_Direction = m_Direction;
    }

    public int getM_Price() {
        return m_Price;
    }

    public int getM_Amount() {
        return m_Amount;
    }

    public String getM_Date() {
        return m_Date;
    }

    public OrderType getM_Type() {
        return m_Type;
    }

    public OrderDirection getM_Direction() {
        return m_Direction;
    }

    public void setM_Price(int m_Price) {
        this.m_Price = m_Price;
    }

    public void setM_Amount(int m_Amount) {
        this.m_Amount = m_Amount;
    }

    public void setM_Date(String m_Date) {
        this.m_Date = m_Date;
    }

    public void setM_Type(OrderType m_Type) {
        this.m_Type = m_Type;
    }

    public void setM_Direction(OrderDirection m_Direction) {
        this.m_Direction = m_Direction;
    }

    @Override
    public String toString() {
        String str = new String("Order details:\n" +
                                        "- Price: " + m_Price + "\n" +
                                        "- Amount: " + m_Amount + "\n" +
                                        "- Date: " + m_Date.toString() + "\n" +
                                        "- Type: " + m_Type + "\n" +
                                        "- Direction: " + m_Direction + "\n");

        return str;
    }
}
