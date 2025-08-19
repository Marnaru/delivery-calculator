package delivery.strategy;

import delivery.DeliveryPrice;

/**
 * Pick-up delivery strategy implementation.
 * Provides free delivery service when customer picks up the order.
 */
public class PickUpDelivery implements DeliveryPrice {
    @Override
    public int calculateDeliveryPrice() {
        return DeliveryPrices.PICKUP_DELIVERY_PRICE;
    }
}