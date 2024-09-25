package model;

public class Payment {
	private int paymentId;
	private String paymentMethod;
	private int orderId;
	
	public Payment() {
		this.paymentId = 0;
		this.orderId = 0;
		this.paymentMethod = "";
	}
	
	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = 0;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = "";
	}

	public int orderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
}
