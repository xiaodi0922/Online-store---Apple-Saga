package model;
import java.util.ArrayList;
import java.util.List;
public class Customer {
	private int customerId;;
	private String customerName;
	private String customerContact;
	private String customerAddress;
	private String customerEmail;
	private String customerPassword;
	private ShoppingCart shoppingCart;
	private List<ShoppingOrder> shoppingOrders;

	public Customer() {
		this.customerId = 0;
		this.customerName = "";
		this.customerContact = "";
		this.customerAddress = "";
		this.customerEmail = "";
		this.customerPassword = "";
		this.shoppingCart = new ShoppingCart();
		this.shoppingOrders = new ArrayList<>();
	}
	
	//new
	public Customer( String customerName, String customerContact, String customerAddress,
			String customerEmail, String customerPassword) 
	{
		this.customerName = customerName;
		this.customerContact = customerContact;
		this.customerAddress = customerAddress;
		this.customerEmail = customerEmail;
		this.customerPassword = customerPassword;
	}
	
	public Customer(int customerId, String customerName, String customerContact, String customerAddress,
			String customerEmail, String customerPassword, ShoppingCart shoppingCart,
			List<ShoppingOrder> shoppingOrders) 
	{
		
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerContact = customerContact;
		this.customerAddress = customerAddress;
		this.customerEmail = customerEmail;
		this.customerPassword = customerPassword;
		this.shoppingCart = shoppingCart;
		this.shoppingOrders = shoppingOrders;
	}
	
	public int getCustomerId() {
		return customerId;
	}


	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getCustomerContact() {
		return customerContact;
	}


	public void setCustomerContact(String customerContact) {
		this.customerContact = customerContact;
	}


	public String getCustomerAddress() {
		return customerAddress;
	}


	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}


	public String getCustomerEmail() {
		return customerEmail;
	}


	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}


	public String getCustomerPassword() {
		return customerPassword;
	}


	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}


	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}


	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}


	public List<ShoppingOrder> getShoppingOrders() {
		return shoppingOrders;
	}


	public void setShoppingCarts(List<ShoppingOrder> shoppingOrders) {
		this.shoppingOrders = shoppingOrders;
	}

}

	
