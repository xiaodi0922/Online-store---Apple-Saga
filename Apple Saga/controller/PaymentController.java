package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.MyDatabase;
import model.Payment;

public class PaymentController {
   
	public void setPayment (int paymentId, String paymentMethod, int orderId) throws ClassNotFoundException, SQLException
    {
		Connection conn = MyDatabase.doConnection();
        try{
            String query = "INSERT INTO payment (paymentId, paymentMethod, orderId) VALUES (?, ?, ?);";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, paymentId);
            preparedStatement.setString(2, paymentMethod);
            preparedStatement.setInt(3, orderId);
            preparedStatement.executeUpdate();
        } catch (SQLException err)
        {
        	conn.close();
            System.out.println(err.getMessage());
        }
    }
	
    public Payment getPayment (int orderId) throws ClassNotFoundException, SQLException
    {
    	Connection conn = MyDatabase.doConnection();
        Payment payment = new Payment();
        try{
            String query = "SELECT * FROM payment WHERE orderId = ?;"; //payment got no order id, database got
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, orderId);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next())
            {
                payment.setPaymentId(rs.getInt("paymentId"));
                payment.setPaymentMethod(rs.getString("paymentMethod")); //database don't have amount
                payment.setOrderId(rs.getInt("orderId"));
            }
        } catch (SQLException err)
        {
            System.out.println(err.getMessage());
        }
        conn.close();
        return payment;
    }
}
