package model;

import java.util.List;

public class ShoppingOrder {
	private int orderId;
	private boolean deliveryStatus;
	private double total;
	private Customer customer;
	private List<CartItem> cartItems;
	
	public ShoppingOrder(int orderId,boolean deliveryStatus, double total, Customer customer, List<CartItem> cartItems) {
	
		this.orderId = orderId;
		this.deliveryStatus = false;
		this.total = total;
		this.customer = customer;
		this.cartItems = cartItems;

	}
	
	public ShoppingOrder() {
		
		this.orderId = 0;
		this.deliveryStatus = false;
		this.total = 0.0;
		this.cartItems = null;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public boolean isDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(boolean deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public double getOrderTotal() {
		return total;
	}

	public void setOrderTotal(double total) {
		this.total = total;
	}

	public Customer getOrderCustomer() {
		return customer;
	}

	public void setOrderCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	
	public void addCartItem(CartItem cartItem) {
	        this.cartItems.add(cartItem);
	}

	public void removeCartItem(CartItem cartItem) {
	        this.cartItems.remove(cartItem);
	}

	public void clearCartItems() {
	        this.cartItems.clear();
	}
	
	
	
}

