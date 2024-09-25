package controller;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Review;

public class ReviewController extends Controller {

	@Override
	public void connectToDatabase() {
		
		try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/applesaga", "root", "");
        } catch (ClassNotFoundException | SQLException err){
            System.out.println(err.getMessage());
        }
	}
	
	public List<Review> getAllReview(int productId){
		
		try{
            List<Review> reviews = new ArrayList<Review>();
            String query = "SELECT * FROM review WHERE productId = ?;";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Review review = new Review();
                review.setCaseId(rs.getInt("caseId"));
	        review.setReviewCustomerId(rs.getInt("customerId"));
                review.setReviewProductId(rs.getInt("productId"));
                review.setReviewDescription(rs.getString("reviewDescription"));
           
                reviews.add(review);
            }
            return reviews;
        } catch (SQLException err){
            System.out.println(err.getMessage());
        }

        return null;
	}
	
	public void addReview(int productId, int customerId, String reviewDescription) {
        try {
            String query = "INSERT INTO review (productId, customerId, reviewDescription) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, productId);
            ps.setInt(2, customerId);
            ps.setString(3, reviewDescription);
            ps.executeUpdate();
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
    }
	
}