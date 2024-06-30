public class Customer {
    private String name;
    private Order order;
    private double categoryMultiplier;

    public double getCategoryMultiplier() {
        return categoryMultiplier;
    }

    public void setCategoryMultiplier(double categoryMultiplier) {
        this.categoryMultiplier = categoryMultiplier;
    }

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
