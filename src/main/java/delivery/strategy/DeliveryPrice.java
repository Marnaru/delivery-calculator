package delivery.strategy;

/**
 * Strategy interface for calculating delivery prices.
 * Implementations of this interface provide different delivery pricing strategies
 * following the Strategy design pattern.
 */
public interface DeliveryPrice {
    
    /**
     * Calculates the delivery price for this delivery strategy.
     * 
     * @return the delivery price in currency units
     */
    int calculateDeliveryPrice();
}