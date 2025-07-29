package delivery.strategy;

/**
 * Order class that represents a customer order with delivery strategy.
 * Uses the Strategy pattern to calculate delivery costs based on the selected delivery method.
 */
public class Order {
    private final DeliveryPrice deliveryPrice;

    /**
     * Creates a new Order with the specified delivery strategy.
     * 
     * @param deliveryPrice the delivery strategy to use for this order
     * @throws IllegalArgumentException if deliveryPrice is null
     */
    public Order(DeliveryPrice deliveryPrice){
        if (deliveryPrice == null) {
            throw new IllegalArgumentException("DeliveryPrice cannot be null");
        }
        this.deliveryPrice = deliveryPrice;
    }

    /**
     * Gets the delivery price for this order based on the selected delivery strategy.
     * 
     * @return the delivery price in currency units
     */
    public int getDeliveryPrice(){
        return deliveryPrice.calculateDeliveryPrice();
    }
    
    /**
     * Calculates the total cost of the order including product price and delivery cost.
     * 
     * @param productPrice the price of the product in currency units
     * @return the total cost (product price + delivery price)
     * @throws IllegalArgumentException if productPrice is negative
     */
    public int calculateTotal(int productPrice){
        if (productPrice < 0) {
            throw new IllegalArgumentException("Product price cannot be negative");
        }
        return productPrice + getDeliveryPrice();
    }
}