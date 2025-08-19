package Order;

import ListOfProducts.Products;
import delivery.DeliveryParameters;
import delivery.DeliveryPrice;

import java.util.List;

public class Order implements DeliveryParameters {
    private final DeliveryPrice deliveryPrice;
    private final String orderId;
    private final List<Products> products;
    private final double distance;

    public Order(DeliveryPrice deliveryPrice, String orderId,
                 List<Products> products, double distance) {
        if (deliveryPrice == null) {
            throw new IllegalArgumentException("DeliveryPrice cannot be null");
        }
        if (orderId == null || orderId.trim().isEmpty()) {
            throw new IllegalArgumentException("Order ID cannot be null or empty");
        }
        if (products == null || products.isEmpty()) {
            throw new IllegalArgumentException("Products list cannot be null or empty");
        }
        if (distance < 0) {
            throw new IllegalArgumentException("Distance cannot be negative");
        }

        this.deliveryPrice = deliveryPrice;
        this.orderId = orderId.trim();
        this.products = List.copyOf(products);
        this.distance = distance;
    }

    public double getTotalWeight() {
        return products.stream()
                .mapToDouble(Products::getWeight)
                .sum();
    }

    public double getProductsTotalPrice() {
        return products.stream()
                .mapToDouble(Products::getPrice)
                .sum();
    }

    public int getDeliveryPrice() {
        return deliveryPrice.calculateDeliveryPrice();
    }

    public double calculateTotal() {
        return getProductsTotalPrice() + getDeliveryPrice();
    }


    public String getOrderId() {
        return orderId;
    }

    public List<Products> getProducts() {
        return List.copyOf(products);
    }

    @Override
    public double getDistance() {
        return distance;
    }

    @Override
    public double getWeight() {
        return getTotalWeight();
    }
}