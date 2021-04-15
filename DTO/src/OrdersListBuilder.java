import java.util.ArrayList;

public class OrdersListBuilder implements IDTOBuilder {

    @Override
    public IDTOPlan getDTO(Object object) {
        return new OrdersListPlan((ArrayList<Stock>) object);
    }
}
