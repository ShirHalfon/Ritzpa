public class SingleStockBuilder implements IDTOBuilder {

    @Override
    public IDTOPlan getDTO(Object object) {
        return new SingleStockPlan( (Stock) object);
    }
}
