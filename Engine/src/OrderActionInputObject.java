public class OrderActionInputObject implements IInputObject{

    public Order orderToCommit;
    public ConcreteEngine engine;

    public OrderActionInputObject(Order orderToCommit, ConcreteEngine engine) {
        this.orderToCommit = orderToCommit;
        this.engine = engine;
    }

    public Order getOrderToCommit() {
        return orderToCommit;
    }

    public void setOrderToCommit(Order orderToCommit) {
        this.orderToCommit = orderToCommit;
    }

    public ConcreteEngine getEngine() {
        return engine;
    }

    public void setEngine(ConcreteEngine engine) {
        this.engine = engine;
    }

    @Override
    public String toString() {
        return "Order action to commit" + orderToCommit.toString();
    }
}
