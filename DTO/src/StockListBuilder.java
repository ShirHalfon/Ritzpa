import java.util.ArrayList;

public class StockListBuilder implements IDTOBuilder {


    @Override
    public IDTOPlan getDTO(Object object) {
        return new StocksListPlan( (ArrayList<Stock>) object);
    }
}
