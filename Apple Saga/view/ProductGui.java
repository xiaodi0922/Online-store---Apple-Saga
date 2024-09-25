package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import controller.ProductController;
import model.Product;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class ProductGui extends JFrame {
    private JFrame frame;
    private ProfileGui profileGui;
    private String email;
    private String password;
    private ProductController controller;
    private List<Product> currentProducts;

    public ProductGui(List<Product> products, JFrame existingFrame, ProfileGui profileGui, String email, String password) {
    	this.profileGui = profileGui != null ? profileGui : new ProfileGui(email, password);
    	this.email = email;
        this.password = password;    	
        this.controller = new ProductController();
        controller.connectToDatabase();
        this.currentProducts = products;
    	
        if (existingFrame != null) {
            frame = existingFrame;
            frame.getContentPane().removeAll();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setBounds(100, 100, 1180, 690);
        } else {
            frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setBounds(100, 100, 1180, 690);
        }
        frame.getContentPane().setLayout(null);

        initializeGUI();
    }
        
    private void initializeGUI() {
        frame.getContentPane().removeAll();

        JLabel storeName = new JLabel("APPLE SAGA STORE");
        storeName.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 24));
        storeName.setBounds(71, 39, 239, 45);
        frame.getContentPane().add(storeName);

        JLabel lblProduct = new JLabel("PRODUCTS");
        lblProduct.setHorizontalAlignment(SwingConstants.LEFT);
        lblProduct.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblProduct.setBounds(71, 79, 254, 45);
        frame.getContentPane().add(lblProduct);

        JButton filterBttn = new JButton("FILTER");
        filterBttn.setBounds(909, 95, 85, 21);
        frame.getContentPane().add(filterBttn);

        filterBttn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<String> categories = controller.getAllCategories();
                new FilterGui(ProductGui.this, categories);
            }
        });

        JButton Profile = new JButton("USER PROFILE");
        Profile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                profileGui.setVisible(true);
                frame.dispose();
            }
        });
        Profile.setBounds(844, 54, 150, 21);
        frame.getContentPane().add(Profile);

        displayProducts(currentProducts);
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
    }
    
        public void applyFilter(String category) {
            if (category.equals("All Categories")) {
                currentProducts = controller.getAllProduct();
            } else {
                currentProducts = controller.getAllProductByCategory(category);
            }
            
            initializeGUI();
        }

    private void displayProducts(List<Product> products) {
        int x = 92;
        int y = 161;
        int width = 150;
        int height = 117;
        int horizontalGap = 190;
        int verticalGap = 193;

        for (int i = 0; i < Math.min(products.size(), 10); i++) {
            Product product = products.get(i);

            String imageUrl = "/com/apple/resources/product_images/" + product.getProductImageURL();
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

            System.out.println("URL: " + url); // Print the URL to the console

            if (url != null) {
                try {
                    File file = new File(url.getFile());
                    if (!file.exists()) {
                        System.out.println("File not found: " + file.getAbsolutePath());
                    }
                } catch (Exception e) {
                    System.err.println("Error checking file existence: " + e.getMessage());
                }
            }

            // Load the image using the URL
            ImageIcon imageIcon;
            try {
                BufferedImage image = ImageIO.read(url);
                Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(scaledImage);
            } catch (Exception e) {
                System.err.println("Error loading image: " + e.getMessage());
                imageIcon = new ImageIcon("path/to/placeholder.png");
            }

            JButton productButton = new JButton(imageIcon);
            productButton.setBounds(x, y, width, height);
            frame.getContentPane().add(productButton);
            productButton.setVisible(true); // Make the button visible

            final int productId = product.getProductId();
            productButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    ProductDetailGui detailGui = new ProductDetailGui(product, email, password, productId);
                    detailGui.setVisible(true);
                }
            });

            JLabel nameLabel = new JLabel(product.getProductName());
            nameLabel.setFont(new Font("Serif", Font.PLAIN, 14));
            nameLabel.setBounds(x, y + height + 10, width, 21);
            frame.getContentPane().add(nameLabel);
            nameLabel.setVisible(true); // Make the label visible

            JLabel priceLabel = new JLabel(String.format("RM %.2f", product.getProductPrice()));
            priceLabel.setFont(new Font("Serif", Font.PLAIN, 12));
            priceLabel.setBounds(x, y + height + 33, width, 21);
            frame.getContentPane().add(priceLabel);
            priceLabel.setVisible(true); // Make the label visible

            // Move to the next position
            if (i % 5 == 4) {
                x = 92;
                y += verticalGap;
            } else {
                x += horizontalGap;
            }
        }
        frame.setVisible(true); // Make the frame visible
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                String userEmail = "user@example.com"; // Retrieve this from your login session
                String userPassword = "userPassword"; // Retrieve this from your login session
                ProductController controller = new ProductController();
                controller.connectToDatabase();
                List<Product> products = controller.getAllProduct();
                ProfileGui profileGui = new ProfileGui(userEmail, userPassword);
                new ProductGui(products, null, profileGui, userEmail, userPassword);
            }
        });
    }

}