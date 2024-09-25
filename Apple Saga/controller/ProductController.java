package controller;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.*;

public class ProductController extends Controller {

	@Override
	public void connectToDatabase() {
		try{
            Class.forName("com.mysql.jdbc.Driver");  
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/applesaga", "root", "");
        } catch (ClassNotFoundException | SQLException err)
        {
            System.out.println(err.getMessage());
        }
	}

    public Product getProductDetailsbyName(String productName)
    {
        Product product = new Product();
        try
        {
            String sql = "SELECT * FROM product WHERE productName = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, productName);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                product.setProductId(rs.getInt("productId"));
                product.setProductName(rs.getString("productName"));
                product.setProductPrice(rs.getDouble("productPrice"));
                product.setProductStockQuantity(rs.getInt("stockQuantity"));
                product.setProductColor(rs.getString("productColour"));
                product.setProductImageURL(rs.getString("imageURL"));
                product.setProductCategory(new ProductCategoryController().getProductCategoryById(rs.getInt("categoryId")));
            }
        } catch (SQLException err)
        {
            System.out.println(err.getMessage());
        }
        return product;
    }
	// get 1 product by id
    public Product getProductDetailsbyId(int productId)
    {
        Product product = new Product();
        try
        {
            String sql = "SELECT * FROM product WHERE productId = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                product.setProductId(rs.getInt("productId"));
                product.setProductName(rs.getString("productName"));
                product.setProductPrice(rs.getDouble("productPrice"));
                product.setProductStockQuantity(rs.getInt("stockQuantity"));
                product.setProductColor(rs.getString("productColour"));
                product.setProductImageURL(rs.getString("imageURL"));
                product.setProductCategory(new ProductCategoryController().getProductCategoryById(rs.getInt("categoryId")));
            }
        } catch (SQLException err)
        {
            System.out.println(err.getMessage());
        }
        return product;
    }
	
    // get all product details
    public List<Product> getAllProduct() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product";
        
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("productId"));
                product.setProductName(rs.getString("productName"));
                product.setProductPrice(rs.getDouble("productPrice"));
                product.setProductStockQuantity(rs.getInt("stockQuantity"));
                product.setProductColor(rs.getString("productColour"));
                product.setProductImageURL(rs.getString("imageURL"));
                product.setProductCategory(new ProductCategoryController().getProductCategoryById(rs.getInt("categoryId")));
                
                System.out.println("Product name: " + product.getProductName()); // Add this debug statement
                products.add(product);
            }
        } catch (SQLException err) {
            System.err.println("Error fetching products: " + err.getMessage());
        }
        
        return products;
    }
    
    //get products by category
    public List<Product> getAllProductByCategory(String categoryName)
    {
    	List<Product> products = new ArrayList<>();
    	try {
    		//
    		String sql = "SELECT p.* FROM product p JOIN product_category pc ON p.categoryId = pc.categoryId WHERE pc.categoryName = ?";
        	PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, categoryName);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                Product product = new Product();
                product.setProductId(rs.getInt("productId"));
                product.setProductName(rs.getString("productName"));
                product.setProductPrice(rs.getDouble("productPrice"));
                product.setProductStockQuantity(rs.getInt("stockQuantity"));
                product.setProductColor(rs.getString("productColour"));
                product.setProductImageURL(rs.getString("imageURL"));
                product.setProductCategory(new ProductCategoryController().getProductCategoryById(rs.getInt("p.categoryId")));
                
                products.add(product);
            }
    	}catch (SQLException err)
    	{
            System.out.println(err.getMessage());
        }
        return products;
    	
        
    }
    
    public List<String> getAllCategories() {
        List<String> categories = new ArrayList<>();
        String sql = "SELECT DISTINCT categoryName FROM product_category";
        
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                categories.add(rs.getString("categoryName"));
            }
        } catch (SQLException err) {
            System.err.println("Error fetching categories: " + err.getMessage());
        }
        
        return categories;
    }
    
  //get stock quantity
    public int getProductStockQuantity(int productId)
    {
        int stockQuantity = 0;
        try
        {
            String sql = "SELECT stockQuantity FROM product WHERE productId = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                stockQuantity = rs.getInt("stockQuantity");
            }
        } catch (SQLException err)
        {
            System.out.println(err.getMessage());
        }
        return stockQuantity;
    }
    
}