import java.util.Date;

public class Deal {

    /*
    * 1. Add show method to activate in Client
    * */

    private int m_Cycle;
    private int m_Price;
    private int m_Amount;
    private Date m_Date;

    public int getM_Cycle() {
        return m_Cycle;
    }

    public int getM_Price() {
        return m_Price;
    }

    public int getM_Amount() {
        return m_Amount;
    }

    public Date getM_Date() {
        return m_Date;
    }

    public void setM_Cycle(int m_Cycle) {
        this.m_Cycle = m_Cycle;
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
}


