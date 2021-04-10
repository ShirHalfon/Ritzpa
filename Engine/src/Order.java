import java.util.Date;

public class Order {

    private int m_Price;
    private int m_Amount;
    private Date m_Date;
    private OrderType m_Type;
    private OrderDirection m_Direction;

    public int getM_Price() {
        return m_Price;
    }

    public int getM_Amount() {
        return m_Amount;
    }

    public Date getM_Date() {
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

    public void setM_Date(Date m_Date) {
        this.m_Date = m_Date;
    }

    public void setM_Type(OrderType m_Type) {
        this.m_Type = m_Type;
    }

    public void setM_Direction(OrderDirection m_Direction) {
        this.m_Direction = m_Direction;
    }
}
