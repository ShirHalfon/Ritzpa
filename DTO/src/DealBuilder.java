public class DealBuilder implements DTOBuilder{

    @Override
    public DTOPlan getDTO(Object object) {
        return new DealPlan((Deal) object);
    }
}
