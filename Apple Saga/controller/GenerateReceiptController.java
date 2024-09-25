package controller;

import model.ShoppingOrder;
import model.CartItem;
import model.Customer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GenerateReceiptController {

    private ShoppingOrder order;

    public GenerateReceiptController(ShoppingOrder order) {
        this.order = order;
    }

    public void generateReceipt(String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("APPLE SAGA STORE");
            writer.newLine();
            writer.write("Receipt");
            writer.newLine();
            writer.newLine();
            writer.write("Order ID: " + order.getOrderId());
            writer.newLine();
            Customer customer = order.getOrderCustomer();
            writer.write("Customer Name: " + customer.getCustomerName());
            writer.newLine();
            writer.write("Contact No: " + customer.getCustomerContact());
            writer.newLine();
            writer.write("Delivery Address: " + customer.getCustomerAddress());
            writer.newLine();
            writer.newLine();
            writer.write("Order Items:");
            writer.newLine();
            writer.write("-------------------------------------------------------------");
            writer.newLine();
            for (CartItem item : order.getCartItems()) {
                writer.write("Product Name: " + item.getCartItemProduct().getProductName());
                writer.newLine();
                writer.write("Quantity: " + item.getCartItemQuantity());
                writer.newLine();
                writer.write("Price: RM " + item.getCartItemProduct().getProductPrice());
                writer.newLine();
                writer.write("-------------------------------------------------------------");
                writer.newLine();
            }
            writer.write("Total Price: RM " + String.format("%.2f", order.getOrderTotal()));
            writer.newLine();
            writer.write("-------------------------------------------------------------");
        } catch (IOException e) {
            throw new IOException("Error generating receipt: " + e.getMessage());
        }
    }
}