package StrategyOfSystem;

public class Order {
    public final DeliveryPrice deliveryPrice;

    public Order(DeliveryPrice deliveryPrice){
        this.deliveryPrice = deliveryPrice;
    }

    public int getDeliveryPrice(){
        return deliveryPrice.calculateDeliveryPrice();
    }
    public int calculateTotal(int productPrice){
        return productPrice + getDeliveryPrice();
    }


}



