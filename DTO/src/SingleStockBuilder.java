public class SingleStockBuilder implements DTOBuilder{

    @Override
    public DTOPlan getDTO(Object object) {
        return new SingleStockPlan( (Stock) object);
    }
}
