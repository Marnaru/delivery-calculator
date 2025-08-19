package ListOfProducts;

import delivery.DeliveryParameters;
import features.CalculateDistanceWeight;

public class Products implements DeliveryParameters {
    private final String id;
    private final String name;
    private final double weight; // in kg
    private final double price;

    public Products(String id, String name, double weight, double price) {
        if (weight <= 0 || price < 0) {
            throw new IllegalArgumentException("Weight must be positive and price cannot be negative");
        }
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.price = price;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    @Override
    public double getDistance() {
        throw new UnsupportedOperationException("Distance is not applicable for individual products");
    }

    @Override
    public double getWeight() {
        return weight;
    }

    public double calculateDeliveryCost(double distance) {
        return CalculateDistanceWeight.calculateDeliveryCost(distance, this.weight);
    }
}