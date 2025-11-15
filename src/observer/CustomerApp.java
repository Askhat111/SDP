package observer;

public class CustomerApp implements OrderObserver {
    private final String customerName;

    public CustomerApp(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public void update(Order order, OrderStatus status) {
        String message = getStatusMessage(status);
        System.out.println(customerName + ": " + message);
    }

    private String getStatusMessage(OrderStatus status) {
        return switch (status) {
            case CONFIRMED -> "Order confirmed";
            case PREPARING -> "Preparing your order";
            case READY -> "Order ready for pickup";
            case COMPLETED -> "Order completed";
            case CANCELLED -> "Order cancelled";
            default -> "Order status updated";
        };
    }
}