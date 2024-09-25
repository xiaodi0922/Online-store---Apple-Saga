package controller;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.*;

public class ProductCategoryController extends Controller {
	
	public void connectToDatabase() {
		
		try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/applesaga", "root", "");
        } catch (ClassNotFoundException | SQLException err)
        {
            System.out.println(err.getMessage());
        }
		
	}
	
	// get product category by id
	public ProductCategory getProductCategoryById(int categoryId) {
		
		ProductCategory productCategory = new ProductCategory();
		try
        {
            String sql = "SELECT * FROM product_category WHERE categoryId = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, categoryId);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                productCategory.setCategoryId(rs.getInt("categoryId"));
                productCategory.setCategoryName(rs.getString("categoryName"));
            }
        } catch (SQLException err)
        {
            System.out.println(err.getMessage());
        }
		
		return productCategory;
		
	}
	
	 // Get all product category
    public List<ProductCategory> getAllProductCategory()
    {
        List<ProductCategory> productCategories = new ArrayList<>();
        try
        {
            String sql = "SELECT * FROM product_category";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                ProductCategory productCategory = new ProductCategory();
                productCategory.setCategoryId(rs.getInt("categoryId"));
                productCategory.setCategoryName(rs.getString("categoryName"));
                productCategories.add(productCategory);
            }
        } catch (SQLException err)
        {
            System.out.println(err.getMessage());
        }

        return productCategories;
    }

}
