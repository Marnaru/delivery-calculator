package delivery.strategy;

import features.CalculateDistanceWeight;


/**
 * Courier delivery strategy implementation.
 * Provides delivery service through courier with dynamic pricing
 * based on distance and weight.
 */
public class CourierDelivery implements DeliveryPrice, DeliveryParameters {
    private final double distance; //km
    private final double weight; //kg

    public CourierDelivery(double distance, double weight) {
        if (distance < 0 || weight <= 0) {
            throw new IllegalArgumentException("Distance must be non-negative and weight must be positive");
        }
        this.distance = distance;
        this.weight = weight;
    }

    @Override
    public int calculateDeliveryPrice() {
        double cost = CalculateDistanceWeight.calculateDeliveryCost(distance, weight);
        return (int) Math.round(cost);
    }
    public double getDistance() {
        return distance;
    }
    public double getWeight() {
        return weight;
    }
}