package delivery.strategy;

/**
 * Courier delivery strategy implementation.
 * Provides delivery service through courier with associated cost.
 */
public class CourierDelivery implements DeliveryPrice {
    @Override
    public int calculateDeliveryPrice(){
        return DeliveryPrices.COURIER_DELIVERY_PRICE;
    }
}