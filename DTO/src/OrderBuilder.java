public class OrderBuilder implements DTOBuilder{

    @Override
    public DTOPlan getDTO(Object object) {
        return new OrderPlan((Order) object);
    }
}
