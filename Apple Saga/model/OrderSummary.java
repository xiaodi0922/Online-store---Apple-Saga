package model;

public class OrderSummary {
    private int orderId;
    private double totalPrice;
    private String deliveryStatus;

    public OrderSummary(int orderId, double totalPrice, String deliveryStatus) {
        this.orderId = orderId;
        this.totalPrice = totalPrice;
        this.deliveryStatus = deliveryStatus;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
}