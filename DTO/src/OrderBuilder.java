public class OrderBuilder implements IDTOBuilder {

    @Override
    public IDTOPlan getDTO(Object object) {
        return new OrderPlan((Order) object);
    }
}
