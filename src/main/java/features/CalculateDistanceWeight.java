package features;

import delivery.DeliveryParameters;

import static delivery.strategy.DeliveryPrices.COURIER_DELIVERY_PRICE;

public class CalculateDistanceWeight {

    public static double calculateDeliveryCost(DeliveryParameters params) {

        return calculateDeliveryCost(params.getDistance(), params.getWeight());
    }

    public static double calculateDeliveryCost(double distance, double weight) {
        double cost = COURIER_DELIVERY_PRICE;

        if (weight > 3) {
            cost *= 1.2;
        }
        if (distance > 100) {
            cost *= 1.3;
        }
        return cost;
    }
}
