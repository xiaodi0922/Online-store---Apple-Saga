package model;

public class Product {
	
	private int productId;
	private String productName;
	private String productColor;
	private int stockQuantity;
	private double productPrice;
	private ProductCategory productCategory;
	private String imageURL;
	
	public Product() {
		this.productId = 0;
		this.productName = "";
		this.productColor = "";
		this.stockQuantity = 0;
		this.productPrice = 0.0;
		this.productCategory = new ProductCategory();
		this.imageURL = "";
	}

	public Product(int productId, String productName,String productColor, int stockQuantity,double productPrice,ProductCategory productCategory, String imageURL ) {
		this.productId = productId;
		this.productName = productName;
		this.productColor = productColor;
		this.stockQuantity = stockQuantity;
		this.productPrice = productPrice;
		this.productCategory = productCategory;
		this.imageURL = imageURL;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductStockQuantity() {
		return stockQuantity;
	}

	public void setProductStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	public String getProductColor() {
		return productColor;
	}

	public void setProductColor(String productColor) {
		this.productColor = productColor;
	}

	public String getProductImageURL() {
		return imageURL;
	}

	public void setProductImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

}
