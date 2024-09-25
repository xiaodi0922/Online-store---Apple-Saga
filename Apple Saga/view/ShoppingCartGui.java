package view;

import javax.imageio.ImageIO;
import javax.swing.*;

import controller.CustomerController;
import controller.ShoppingCartController;
import model.CartItem;
import model.Customer;
import model.Product;
import model.ShoppingCart;
import model.UserSession;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ShoppingCartGui {
	private JPanel productPanel;
    private JFrame frame;
    private String email;
    private String password;
    private double total = 0.0;
    private JLabel TotalPrice;
    private ShoppingCart shoppingCart;
    private Customer customer;

    public ShoppingCartGui(String email, String password, ShoppingCart shoppingCart, Customer customer) {
    	this.email = email;
        this.password = password;
        this.shoppingCart = shoppingCart;
        CustomerController customerController = new CustomerController();
        int customerId = UserSession.getInstance().getCurrentUserId();
        this.customer = customerController.getCustomerDetailsById(customerId);

        
        productPanel = new JPanel();
        productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));
        
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Make sure to close the application
        frame.setSize(900, 600);  // Set the frame size
        frame.getContentPane().setLayout(null);

        JLabel storeName = new JLabel("APPLE SAGA STORE ");
        storeName.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 24));
        storeName.setBounds(85, 37, 239, 45);
        frame.getContentPane().add(storeName);

        JButton backBttn = new JButton("Back");
        backBttn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                ProfileGui profileFrame = new ProfileGui(email, password);
                profileFrame.setVisible(true);
            }
        });
        backBttn.setFont(new Font("SansSerif", Font.PLAIN, 15));
        backBttn.setBounds(85, 92, 85, 21);
        frame.getContentPane().add(backBttn);

        

        JLabel shoppingCartLabel = new JLabel("Shopping Cart");
        shoppingCartLabel.setHorizontalAlignment(SwingConstants.CENTER);
        shoppingCartLabel.setFont(new Font("Tahoma", Font.BOLD, 20)); // Change to bold font
        shoppingCartLabel.setBounds(317, 78, 254, 45);
        frame.getContentPane().add(shoppingCartLabel);

        TotalPrice = new JLabel();
        TotalPrice.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 12));
        TotalPrice.setBounds(686, 470, 100, 13);
        frame.getContentPane().add(TotalPrice);
        
        // Fetch and display the cart items
        displayCartItems();

        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.setBounds(720, 487, 117, 43);  // Set the position and size
     // Hide the checkout button if the shopping cart is empty
        if (total > 0) {
            frame.getContentPane().add(checkoutButton);
            checkoutButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    frame.setVisible(false);
                    CustomerController customerController = new CustomerController();
                    int customerId = UserSession.getInstance().getCurrentUserId();
                    Customer updatedCustomer = customerController.getCustomerDetailsById(customerId);
                    CheckoutGui checkoutFrame = new CheckoutGui(email, password, shoppingCart, updatedCustomer, total);
                    checkoutFrame.setVisible(true);
                }
            });
        } else {
            checkoutButton.setVisible(false);
        }
        
        JScrollPane scrollPane = new JScrollPane(productPanel);
        scrollPane.setBounds(85, 162, 715, 300);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        frame.getContentPane().add(scrollPane);

        JLabel lblTotalSstIncluded = new JLabel("Total Price :");
        lblTotalSstIncluded.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
        lblTotalSstIncluded.setBounds(510, 470, 167, 13);
        frame.getContentPane().add(lblTotalSstIncluded);


        frame.setVisible(true);  // Make the frame visible
    }

    private void displayCartItems() {
        ShoppingCartController cartController = new ShoppingCartController();
        int customerId = UserSession.getInstance().getCurrentUserId();
        int cartId = cartController.getCartIdbyCustomerId(customerId);
        List<CartItem> cartItems = cartController.getAllCartItembyCartId(cartId);
        
        System.out.println("Displaying items for cart ID " + cartId + ": " + cartItems);

        total = 0.0;
        
        if (cartItems != null && !cartItems.isEmpty()) {
            
            
            for (CartItem cartItem : cartItems) {
            	Product product = cartItem.getCartItemProduct();
                int quantity = cartItem.getCartItemQuantity();
                
                String imageUrl = "/com/apple/resources/product_images/" + product.getProductImageURL();
                URL url = getClass().getResource(imageUrl);

                JPanel itemPanel = new JPanel();
                itemPanel.setLayout(null);
                itemPanel.setPreferredSize(new Dimension(680, 120));
                itemPanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200)),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)
                ));
                
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
                    Image scaledImage = image.getScaledInstance(139, 91, Image.SCALE_SMOOTH);
                    imageIcon = new ImageIcon(scaledImage);
                } catch (Exception e) {
                    System.err.println("Error loading image: " + e.getMessage());
                    imageIcon = new ImageIcon("path/to/placeholder.png");
                }

                JLabel productImage = new JLabel(imageIcon);
                productImage.setBounds(10, 10, 139, 91);
                itemPanel.add(productImage);

                JLabel productName = new JLabel(product.getProductName());
                productName.setFont(new Font("Times New Roman", Font.BOLD, 14));
                productName.setBounds(160, 10, 300, 20);
                itemPanel.add(productName);

                JLabel quantityLabel = new JLabel("Quantity: " + quantity);
                quantityLabel.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 12));
                quantityLabel.setBounds(160, 40, 100, 20);
                itemPanel.add(quantityLabel);
                
                double productPrice = product.getProductPrice();
                double subtotal = productPrice * quantity;

                JLabel productPriceLabel = new JLabel(String.format("Price: RM %.2f", productPrice));
                productPriceLabel.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 12));
                productPriceLabel.setBounds(160, 60, 150, 20);
                itemPanel.add(productPriceLabel);

                JLabel subtotalLabel = new JLabel(String.format("Sub-total Price: RM %.2f", subtotal));
                subtotalLabel.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
                subtotalLabel.setBounds(480, 40, 180, 20);
                itemPanel.add(subtotalLabel);

                JButton decreaseButton = new JButton("Remove One");
                decreaseButton.setBounds(480, 70, 120, 25);
                decreaseButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        cartController.decreaseItemQuantity(cartId, product.getProductId());
                        refreshCart();
                    }
                });
                itemPanel.add(decreaseButton);

                JButton addOneMoreButton = new JButton("Add One More");
                addOneMoreButton.setBounds(590, 70, 120, 25);
                addOneMoreButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        cartController.addItemToCart(cartId, product.getProductId());
                        refreshCart();
                    }
                });
                itemPanel.add(addOneMoreButton);

                productPanel.add(itemPanel);
                productPanel.add(Box.createRigidArea(new Dimension(0, 10)));

                total += subtotal;
                }

                TotalPrice.setText(String.format("RM %.2f", total ));
            
            
        } else {
        	JLabel emptyCartLabel = new JLabel("Your shopping cart is empty", SwingConstants.CENTER);
            emptyCartLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
            emptyCartLabel.setForeground(Color.LIGHT_GRAY);
            emptyCartLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            // Add some vertical spacing
            productPanel.add(Box.createVerticalGlue());
            productPanel.add(emptyCartLabel);
            productPanel.add(Box.createVerticalGlue());
            
            total = 0.0;
        }
        
        TotalPrice.setText(String.format("RM %.2f", total));
        
        productPanel.revalidate();
        productPanel.repaint();
    }
    
    private void refreshCart() {
    	productPanel.removeAll();
        displayCartItems();
        
        // Update the shoppingCart object
        ShoppingCartController cartController = new ShoppingCartController();
        int customerId = UserSession.getInstance().getCurrentUserId();
        int cartId = cartController.getCartIdbyCustomerId(customerId);
        this.shoppingCart.setCartItems(cartController.getAllCartItembyCartId(cartId));
        
        productPanel.revalidate();
        productPanel.repaint();
    }

    public static void main(String[] args) {
    	ShoppingCart shoppingCart = new ShoppingCart(); // Initialize with actual cart items
        Customer customer = new Customer("John Doe", "0123456789", "123 Example St", "john.doe@example.com", "password");
        new ShoppingCartGui("example@email.com", "password", shoppingCart, customer);    }

    public void setVisible(boolean b) {
        frame.setVisible(b);
    }
}