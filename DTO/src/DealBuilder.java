public class DealBuilder implements DTOBuilder{

    @Override
    public DTOPlan getDTO(Object object) {
        DealPlan newDTO = new DealPlan((Deal) object);
        return newDTO;
    }
}
