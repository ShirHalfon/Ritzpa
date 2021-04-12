import java.util.ArrayList;

public class OrdersListBuilder implements DTOBuilder{

    @Override
    public DTOPlan getDTO(Object object) {
        return new OrdersListPlan((ArrayList<Stock>) object);
    }
}
