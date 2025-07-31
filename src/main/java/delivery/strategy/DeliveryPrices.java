package delivery.strategy;

/**
 * Constants class containing delivery prices for different delivery methods.
 * All prices are in the smallest currency unit (e.g., cents).
 */
public final class DeliveryPrices {
    
    /**
     * Courier delivery price in currency units.
     */
    public static final int COURIER_DELIVERY_PRICE = 500;
    
    /**
     * Email delivery price in currency units.
     */
    public static final int EMAIL_DELIVERY_PRICE = 300;
    
    /**
     * Pick-up delivery price in currency units (free).
     */
    public static final int PICKUP_DELIVERY_PRICE = 0;
    
    // Private constructor to prevent instantiation
    private DeliveryPrices() {
        throw new AssertionError("DeliveryPrices is a utility class and should not be instantiated");
    }
}