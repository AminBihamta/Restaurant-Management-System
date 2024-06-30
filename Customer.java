public class Customer {
    private String name;
    private Order order;

    public Customer(String name) {
        this.name = name;
        this.order = new Order();
    }

    public String getName() {
        return name;
    }

    public Order getOrder() {
        return order;
    }
}
