import java.util.ArrayList;

public class StockListBuilder implements DTOBuilder{

    @Override
    public DTOPlan getDTO(Object object) {
        StocksListPlan newDTO = new StocksListPlan( (ArrayList<Stock>) object);
        return newDTO;
    }
}
