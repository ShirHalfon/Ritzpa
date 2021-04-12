import java.util.ArrayList;

public class StockListBuilder implements DTOBuilder{

    @Override
    public DTOPlan getDTO(Object object) {
        return new StocksListPlan( (ArrayList<Stock>) object);
    }
}
