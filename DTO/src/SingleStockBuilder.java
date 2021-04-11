public class SingleStockBuilder implements DTOBuilder{

    @Override
    public DTOPlan getDTO(Object object) {
        SingleStockPlan newDTO = new SingleStockPlan( (Stock) object);
        return newDTO;
    }
}
