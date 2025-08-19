package delivery.strategy;

import delivery.DeliveryParameters;
import delivery.DeliveryPrice;
import features.CalculateDistanceWeight;

public class CourierDelivery implements DeliveryPrice {
    private final DeliveryParameters params;

    public CourierDelivery(DeliveryParameters params) {
        if (params == null) {
            throw new IllegalArgumentException("Delivery parameters cannot be null");
        }
        this.params = params;
    }

    @Override
    public int calculateDeliveryPrice() {
        double cost = CalculateDistanceWeight.calculateDeliveryCost(params);
        return (int) Math.round(cost);
    }
}