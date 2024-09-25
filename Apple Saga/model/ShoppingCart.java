package model;

import java.util.List;

public class ShoppingCart {
	
	private int cartId;
	private List<CartItem> cartItems;

	public ShoppingCart(int cartId,List<CartItem> cartItems ) {
		
		this.cartId = cartId;
		this.cartItems = cartItems;
	}
	
	public ShoppingCart() {
		this.cartId = 0;
		this.cartItems = null;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	
}
