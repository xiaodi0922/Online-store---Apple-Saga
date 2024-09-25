package model;

public class Review {
	
	private int caseId;
	private String reviewDescription;
	private int customerId;
	private int productId;
	
	public Review(int caseId,String reviewDescription, int customerId, int productId) {
		
		this.caseId = caseId;
		this.reviewDescription = reviewDescription;
		this.customerId = customerId;
		this.productId = productId;
	}
	
	public Review() {
		this.caseId = 0;
		this.reviewDescription = "";
		this.customerId = 0;
		this.productId = 0;
	}

	public int getCaseId() {
		return caseId;
	}

	public void setCaseId(int caseId) {
		this.caseId = caseId;
	}

	public String getReviewDescription() {
		return reviewDescription;
	}

	public void setReviewDescription(String reviewDescription) {
		this.reviewDescription = reviewDescription;
	}

	public int getReviewCustomerId() {
		return customerId;
	}

	public void setReviewCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getReviewProductId() {
		return productId;
	}

	public void setReviewProductId(int productId) {
		this.productId = productId;
	}
	
}
