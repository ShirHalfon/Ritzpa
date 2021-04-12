import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deal {

    /*
    * 1. Add show method to activate in Client
    * */

    private int cycle;
    private int price;
    private int amount;
    private String date;

    public Deal(int price, int amount) {
        this.cycle = price*amount;
        this.price = price;
        this.amount = amount;
        this.date = DateTimeFormatter.ofPattern("HH:mm:ss:SSS").format(LocalDateTime.now());
    }

    public int getCycle() {
        return cycle;
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

    public void setCycle(int cycle) {
        //need to calculate
        this.cycle = cycle;}

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString(){
        String str = new String("Deal details:\n" +
                                                    "- Cycle: " + cycle + "\n" +
                                                    "- Price: " + price + "\n" +
                                                    "- Amount: " + amount + "\n" +
                                                    "- Date: " + date.toString() + "\n");

        return str;
    }
}


