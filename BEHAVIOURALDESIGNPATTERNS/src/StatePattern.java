class OrderContext{
    private OrderState currentState;
    public OrderContext() {
        this.currentState = new OrderPlacedState();
    }
    public void setState(OrderState state){
        this.currentState = state;
    }
    public void next(){
        currentState.next(this);
    }
    public void cancel(){
        currentState.cancel(this);
    }
    public String getCurrentState(){
        return currentState.getStateName();
    }
}
interface OrderState{
    void next(OrderContext context);
    void cancel(OrderContext context);
    String getStateName();
}
class OrderPlacedState implements OrderState{
    @Override
    public void next(OrderContext context) {
        context.setState(new PreparingState());
        System.out.println("Order is now being prepared");
    }

    @Override
    public void cancel(OrderContext context) {
        context.setState(new CancelledState());
        System.out.println("Order has been cancelled");
    }

    @Override
    public String getStateName() {
        return "ORDER_PLACED";
    }
}

class PreparingState implements OrderState{
    @Override
    public void next(OrderContext context){
        context.setState(new OutForDeliveryState());
        System.out.println("Order is out for delivery");
    }

    @Override
    public void cancel(OrderContext context) {
        context.setState(new CancelledState());
        System.out.println("Order has been cancelled");
    }

    @Override
    public String getStateName() {
        return "PREPARING";
    }
}
class OutForDeliveryState implements OrderState{
    @Override
    public void next(OrderContext context) {
        context.setState(new DeliveredState());
        System.out.println("Order has been delivered");
    }

    @Override
    public void cancel(OrderContext context) {
        System.out.println("Cannot cancel an order that is out for delivery");
    }

    @Override
    public String getStateName() {
        return "OUT_FOR_DELIVERY";
    }
}
class DeliveredState implements OrderState{
    @Override
    public void next(OrderContext context) {
        System.out.println("Order has already delivered");
    }


    @Override
    public void cancel(OrderContext context) {
        System.out.println("Cannot cancel a order which is already delivered");
    }

    @Override
    public String getStateName() {
        return "DELIVERED";
    }
}
class CancelledState implements OrderState{
    @Override
    public void next(OrderContext context) {
        System.out.println("Order cancelled cannot move to next state");
    }

    @Override
    public void cancel(OrderContext context) {
        System.out.println("Order is already cancelled");
    }

    @Override
    public String getStateName() {
        return "CANCELLED";
    }
}
public class StatePattern {
    static void main(String[] args) {
        OrderContext order = new OrderContext();
        System.out.println("Current State: " + order.getCurrentState());
        order.next();
        order.next();
        order.cancel();
        order.next();
        order.cancel();
    }
}