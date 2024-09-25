package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import controller.ReviewController;
import controller.ShoppingOrderController;
import model.CartItem;
import model.Customer;
import model.Product;
import model.ShoppingOrder;
import model.UserSession;
import controller.GenerateReceiptController;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class OrderDetailsGui extends JPanel {

    private static final long serialVersionUID = 1L;
    private ShoppingOrderController orderController;
    private JPanel orderDetailsPanel;
    private JPanel itemsPanel;
    private int orderId;
    private JButton btnCompleteOrder;
    private JPanel buttonPanel;
    private OrderHistoryGui parentFrame;
    private String email;
    private String password;

    public OrderDetailsGui(int orderId, OrderHistoryGui parentFrame, String email, String password) {
        this.parentFrame = parentFrame;    	
        this.orderId = orderId;
        this.email = email;
        this.password = password;

        orderController = new ShoppingOrderController();
        orderDetailsPanel = new JPanel(new GridLayout(0, 2, 10, 5));

        itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));

        setLayout(new BorderLayout());

        // Header Panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel storeName = new JLabel("APPLE SAGA STORE");
        storeName.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 24));
        headerPanel.add(storeName, BorderLayout.WEST);

        JButton backBttn = new JButton("BACK");
        backBttn.setFont(new Font("SansSerif", Font.PLAIN, 15));
        backBttn.addActionListener(e -> {
            OrderHistoryGui newOrderHistoryGui = new OrderHistoryGui(null, null, email, password);
            newOrderHistoryGui.setVisible(true);
            SwingUtilities.getWindowAncestor(this).dispose();
        });
        headerPanel.add(backBttn, BorderLayout.EAST);

        add(headerPanel, BorderLayout.NORTH);

        // Main Content Panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        // Fetch order details
        ShoppingOrder shoppingOrder = orderController.getCompleteOrderDetails(orderId);

        // Order Details Panel
        orderDetailsPanel.setBorder(BorderFactory.createTitledBorder("ORDER DETAILS"));
        orderDetailsPanel.add(new JLabel("Order ID: " + String.valueOf(shoppingOrder.getOrderId())));
        orderDetailsPanel.add(new JLabel("Customer Name: " + shoppingOrder.getOrderCustomer().getCustomerName()));
        orderDetailsPanel.add(new JLabel("Contact No: " + shoppingOrder.getOrderCustomer().getCustomerContact()));
        orderDetailsPanel.add(new JLabel("Delivery Address: " + shoppingOrder.getOrderCustomer().getCustomerAddress()));
        orderDetailsPanel.add(new JLabel("Delivery Status: " + (shoppingOrder.isDeliveryStatus() ? "DELIVERED" : "IN-DELIVERY")));

        mainPanel.add(orderDetailsPanel, BorderLayout.NORTH);

        // Order Items Panel
        itemsPanel.setBorder(BorderFactory.createTitledBorder("ORDER ITEMS"));

        for (CartItem item : shoppingOrder.getCartItems()) {
            JPanel itemPanel = new JPanel(new BorderLayout(10, 5));
            itemPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

            // Load and display product image
            String imageUrl = "/com/apple/resources/product_images/" + item.getCartItemProduct().getProductImageURL();
            URL url = getClass().getResource(imageUrl);

            if (url == null) {
                String filePath = System.getProperty("user.dir") + "/src" + imageUrl;
                File file = new File(filePath);
                try {
                    url = file.toURI().toURL();
                } catch (MalformedURLException e) {
                    System.err.println("Error converting file path to URL: " + e.getMessage());
                }
            }
            System.out.println("URL: " + url);
            ImageIcon imageIcon;
            try {
                BufferedImage image = ImageIO.read(url);
                Image scaledImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(scaledImage);
            } catch (Exception e) {
                System.err.println("Error loading image: " + e.getMessage());
                imageIcon = new ImageIcon("path/to/placeholder.png");
            }

            JLabel imageLabel = new JLabel(imageIcon);
            itemPanel.add(imageLabel, BorderLayout.WEST);

            JPanel itemDetailsPanel = new JPanel(new GridLayout(0, 1));
            itemDetailsPanel.add(new JLabel("Name: " + item.getCartItemProduct().getProductName()));
            itemDetailsPanel.add(new JLabel("Price: RM " + item.getCartItemProduct().getProductPrice()));
            itemDetailsPanel.add(new JLabel("Quantity: " + item.getCartItemQuantity()));
            itemPanel.add(itemDetailsPanel, BorderLayout.CENTER);

            itemsPanel.add(itemPanel);
            itemsPanel.add(Box.createVerticalStrut(10));
        }

        // Wrap itemsPanel in a JScrollPane
        JScrollPane itemsScrollPane = new JScrollPane(itemsPanel);
        itemsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        itemsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        mainPanel.add(itemsScrollPane, BorderLayout.CENTER);

        // Total Price Panel
        JPanel totalPricePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel totalPriceLabel = new JLabel("Total Price: RM " + String.format("%.2f", shoppingOrder.getOrderTotal()));
        totalPriceLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        totalPricePanel.add(totalPriceLabel);
        mainPanel.add(totalPricePanel, BorderLayout.SOUTH);

        // Button Panel
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        add(buttonPanel, BorderLayout.SOUTH);
        
        createCompleteOrderButton();
        
        refreshOrderDetails();

        JButton printReceiptButton = new JButton("Print Receipt");
        printReceiptButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
        printReceiptButton.addActionListener(e -> {
            GenerateReceiptController receiptGenerator = new GenerateReceiptController(shoppingOrder);
            String filePath = "order_" + shoppingOrder.getOrderId() + "_receipt.txt";

            try {
                receiptGenerator.generateReceipt(filePath);
                JOptionPane.showMessageDialog(this, "Receipt generated successfully at: " + filePath,
                        "Receipt Generated", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error generating receipt: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        buttonPanel.add(printReceiptButton);
    }

    private void createCompleteOrderButton() {
        btnCompleteOrder = new JButton("Complete Order");
        btnCompleteOrder.setFont(new Font("Times New Roman", Font.BOLD, 15));
        btnCompleteOrder.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to mark this order as delivered?",
                    "Confirm Order Completion",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                updateOrderStatus(orderId);
                refreshOrderDetails();

                // Update OrderHistoryGui
                parentFrame.refreshOrderHistory();

                // Add review buttons
                addReviewButtons();

                // Remove Complete Order button
                buttonPanel.remove(btnCompleteOrder);
                buttonPanel.revalidate();
                buttonPanel.repaint();

                JOptionPane.showMessageDialog(this, "Order marked as delivered. You can now leave reviews for the products.");
            }
        });
        buttonPanel.add(btnCompleteOrder);
    }
    
    private void addReviewButtons() {
        for (Component comp : itemsPanel.getComponents()) {
            if (comp instanceof JPanel) {
                JPanel itemPanel = (JPanel) comp;
                JButton reviewButton = new JButton("Review");
                reviewButton.addActionListener(e -> {
                    Product product = getProductFromPanel(itemPanel);
                    ReviewGui reviewGui = new ReviewGui(product, SwingUtilities.getWindowAncestor(this));
                    reviewGui.setLocationRelativeTo(SwingUtilities.getWindowAncestor(this));
                    reviewGui.setVisible(true);
                    SwingUtilities.getWindowAncestor(this).setVisible(false);
                });
                itemPanel.add(reviewButton, BorderLayout.EAST);
            }
        }
        itemsPanel.revalidate();
        itemsPanel.repaint();
    }

    private void updateOrderStatus(int orderId) {
        orderController.updateOrderStatus(orderId, true);
    }

    private void refreshOrderDetails() {
        ShoppingOrder shoppingOrder = orderController.getCompleteOrderDetails(orderId);

        if (shoppingOrder == null) {
            JOptionPane.showMessageDialog(this, "Order details not found for orderId: " + orderId, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Update the order details panel
        for (Component component : orderDetailsPanel.getComponents()) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                String labelText = label.getText();
                if (labelText.startsWith("Delivery Status:")) {
                    label.setText("Delivery Status: " + (shoppingOrder.isDeliveryStatus() ? "DELIVERED" : "IN-DELIVERY"));
                } else if (labelText.startsWith("Order ID:")) {
                    label.setText("Order ID: " + shoppingOrder.getOrderId());
                } else if (labelText.startsWith("Customer Name:")) {
                    label.setText("Customer Name: " + shoppingOrder.getOrderCustomer().getCustomerName());
                } else if (labelText.startsWith("Contact No:")) {
                    label.setText("Contact No: " + shoppingOrder.getOrderCustomer().getCustomerContact());
                } else if (labelText.startsWith("Delivery Address:")) {
                    label.setText("Delivery Address: " + shoppingOrder.getOrderCustomer().getCustomerAddress());
                }
            }
        }

        // Update the total price
        for (Component component : getComponents()) {
            if (component instanceof JPanel) {
                JPanel panel = (JPanel) component;
                for (Component panelComponent : panel.getComponents()) {
                    if (panelComponent instanceof JLabel) {
                        JLabel label = (JLabel) panelComponent;
                        if (label.getText().startsWith("Total Price:")) {
                            label.setText("Total Price: RM " + String.format("%.2f", shoppingOrder.getOrderTotal()));
                        }
                    }
                }
            }
        }
        
        // Update the visibility of the Complete Order button
        if (shoppingOrder.isDeliveryStatus()) {
            buttonPanel.remove(btnCompleteOrder);
            addReviewButtons();
        } else {
            if (!buttonPanel.isAncestorOf(btnCompleteOrder)) {
                buttonPanel.add(btnCompleteOrder);
            }
        }
        buttonPanel.revalidate();
        buttonPanel.repaint();

        revalidate();
        repaint();
    }

    private Product getProductFromPanel(JPanel itemPanel) {
        JPanel detailsPanel = (JPanel)itemPanel.getComponent(1);
        String productName = ((JLabel)detailsPanel.getComponent(0)).getText().substring(6); // Remove "Name: " prefix
        double productPrice = Double.parseDouble(((JLabel)detailsPanel.getComponent(1)).getText().substring(9)); // Remove "Price: RM " prefix

        Product product = new Product();
        product.setProductName(productName);
        product.setProductPrice(productPrice);

        return product;
    }
}