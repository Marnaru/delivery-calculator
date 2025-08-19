package delivery.strategy;

import delivery.DeliveryPrice;

/**
 * Email delivery strategy implementation.
 * Provides delivery service through email with associated cost.
 */
public class EmailDelivery implements DeliveryPrice {
    @Override
    public int calculateDeliveryPrice() {
        return DeliveryPrices.EMAIL_DELIVERY_PRICE;
    }
}