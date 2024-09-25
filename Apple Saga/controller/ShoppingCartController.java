package controller;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import model.*;

public class ShoppingCartController extends Controller{

    public void connectToDatabase() throws SQLException
    {
    	conn.setAutoCommit(false);
    	try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/applesaga", "root", "");
        } catch (ClassNotFoundException | SQLException err)
        {
            System.out.println(err.getMessage());
        }
    	conn.commit();
    }

    public ShoppingCart getShoppingCartDetailbyCustomerId(int customerId)
    {
    	
        ShoppingCart shoppingCart = new ShoppingCart();
        try{
            String query = "SELECT id FROM cart WHERE customerId = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                shoppingCart.setCartId(resultSet.getInt("id"));
            } else if (!resultSet.next())
            {
                String query2 = "INSERT INTO cart (customerId) VALUES (?)";
                PreparedStatement preparedStatement2 = conn.prepareStatement(query2);
                preparedStatement2.setInt(1, customerId);
                preparedStatement2.executeUpdate();
                String query3 = "SELECT * FROM cart WHERE customerId = ?";
                PreparedStatement preparedStatement3 = conn.prepareStatement(query3);
                preparedStatement3.setInt(1, customerId);
                ResultSet resultSet2 = preparedStatement3.executeQuery();
                if (resultSet2.next())
                {
                    shoppingCart.setCartId(resultSet2.getInt("id"));
                }
            }
            shoppingCart.setCartItems(getAllCartItembyCartId(shoppingCart.getCartId()));
        } catch (SQLException err)
        {
            System.out.println(err.getMessage());
        }
        return shoppingCart;
    }

    public int getCartIdbyCustomerId(int customerId) {
        int cartId = -1;
        try {
            String query = "SELECT id FROM cart WHERE customerId = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                cartId = resultSet.getInt("id");
            }
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        System.out.println("Cart ID for customer " + customerId + ": " + cartId);
        return cartId;
    }
    
    public List<CartItem> getAllCartItembyCartId(int cartId) {
        List<CartItem> cartItems = new ArrayList<>();
        try {
            String query = "SELECT productId, quantity FROM cart_item WHERE cartId = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, cartId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CartItem cartItem = new CartItem();
                cartItem.setCartItemQuantity(resultSet.getInt("quantity"));
                cartItem.setCartItemProduct(new ProductController().getProductDetailsbyId(resultSet.getInt("productId")));
                cartItems.add(cartItem);
            }
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        System.out.println("Cart items for cart ID " + cartId + ": " + cartItems);
        return cartItems;
    }

    public void addItemToCart(int cartId, int productId) {
        try {
            String query = "UPDATE cart_item SET quantity = quantity + 1 WHERE cartId = ? AND productId = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, cartId);
            preparedStatement.setInt(2, productId);
            int rowsAffected = preparedStatement.executeUpdate();
            
            if (rowsAffected == 0) {
                // If the item doesn't exist in the cart, insert it
                String insertQuery = "INSERT INTO cart_item (cartId, productId, quantity) VALUES (?, ?, 1)";
                PreparedStatement insertStatement = conn.prepareStatement(insertQuery);
                insertStatement.setInt(1, cartId);
                insertStatement.setInt(2, productId);
                insertStatement.executeUpdate();
            }
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
    }

    public void removeItemFromCart(int cartId, int productId) {
        try {
            String query = "DELETE FROM cart_item WHERE cartId = ? AND productId = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, cartId);
            preparedStatement.setInt(2, productId);
            preparedStatement.executeUpdate();
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
    }
    
    public void decreaseItemQuantity(int cartId, int productId) {
        try {
            // First, get the current quantity
            String getQuantityQuery = "SELECT quantity FROM cart_item WHERE cartId = ? AND productId = ?";
            PreparedStatement getQuantityStmt = conn.prepareStatement(getQuantityQuery);
            getQuantityStmt.setInt(1, cartId);
            getQuantityStmt.setInt(2, productId);
            ResultSet rs = getQuantityStmt.executeQuery();

            if (rs.next()) {
                int currentQuantity = rs.getInt("quantity");
                if (currentQuantity > 1) {
                    // If quantity > 1, decrease by 1
                    String updateQuery = "UPDATE cart_item SET quantity = quantity - 1 WHERE cartId = ? AND productId = ?";
                    PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
                    updateStmt.setInt(1, cartId);
                    updateStmt.setInt(2, productId);
                    updateStmt.executeUpdate();
                } else {
                    // If quantity is 1, remove the item
                    removeItemFromCart(cartId, productId);
                }
            }
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
    }
    
    public void clearCart(int customerId) {
        try {
            int cartId = getCartIdbyCustomerId(customerId);
            String query = "DELETE FROM cart_item WHERE cartId = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, cartId);
            preparedStatement.executeUpdate();
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
    }
    
}