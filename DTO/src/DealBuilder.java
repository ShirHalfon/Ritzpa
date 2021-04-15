public class DealBuilder implements IDTOBuilder {

    @Override
    public IDTOPlan getDTO(Object object) {
        return new DealPlan((Deal) object);
    }
}
