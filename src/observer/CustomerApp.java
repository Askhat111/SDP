package observer;

public class CustomerApp implements OrderObserver {
    public void update(Order order) {
        System.out.println("CUSTOMER: Your order " + order.getOrderId() + " is " + order.getStatus());
    }
}