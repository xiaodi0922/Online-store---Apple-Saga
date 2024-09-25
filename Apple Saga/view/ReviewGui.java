package view;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.CustomerController;
import controller.ReviewController;
import controller.ProductController;
import model.Customer;
import model.Product;
import model.UserSession;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.awt.event.ActionEvent;

public class ReviewGui extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
    private Customer customer;
    private CustomerController customerController;
    private Product product1;
    private ProductController productController;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Product sampleProduct = new Product();
                    sampleProduct.setProductId(1);
                    sampleProduct.setProductName("Sample Product");
                    sampleProduct.setProductPrice(999.99);
                    sampleProduct.setProductColor("Black");
                    sampleProduct.setProductStockQuantity(10);
                    sampleProduct.setProductImageURL("path/to/sample/image.jpg");

                    String dummyEmail = "test@example.com";
                    String dummyPassword = "testpassword";

                    ProductDetailGui frame = new ProductDetailGui(sampleProduct, dummyEmail, dummyPassword,0);
                    frame.setVisible(true);
					
					//ReviewGui frame = new ReviewGui();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public ReviewGui(Product product, Window parentWindow) {
	    this.customerController = new CustomerController();
	    int currentUserId = UserSession.getInstance().getCurrentUserId();
	    this.customer = customerController.getCustomerDetailsById(currentUserId);
	    this.productController = new ProductController();
            this.product1 = productController.getProductDetailsbyName(product.getProductName());

        String customerEmail = customer.getCustomerEmail();
        String customerPassword = customer.getCustomerPassword();
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 960, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("APPLE SAGA STORE ");
		lblNewLabel.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 18));
		lblNewLabel.setBounds(44, 38, 185, 22);
		contentPane.add(lblNewLabel);
		
		JButton backButton = new JButton("Back");
	    backButton.addActionListener(e -> {
	        parentWindow.setVisible(true);
	        dispose();
	    });
	    contentPane.add(backButton);
		
		JLabel lblNewLabel_1_1 = new JLabel("Feedback");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblNewLabel_1_1.setBounds(419, 38, 252, 39);
		contentPane.add(lblNewLabel_1_1);
		
		String imageUrl = "/com/apple/resources/product_images/" + product1.getProductImageURL();
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
            Image scaledImage = image.getScaledInstance(252, 153, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(scaledImage);
        } catch (Exception e) {
            System.err.println("Error loading image: " + e.getMessage());
            imageIcon = new ImageIcon("path/to/placeholder.png");
        }

        JButton productImage1 = new JButton(imageIcon);
        productImage1.setBounds(78, 141, 252, 153);
        contentPane.add(productImage1);
		JLabel lblNewLabel_2 = new JLabel(product.getProductName());
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_2.setBounds(393, 152, 137, 22);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("RM " + String.format("%.2f", product.getProductPrice()));
		lblNewLabel_2_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(393, 177, 137, 22);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel(product.getProductColor());
		lblNewLabel_2_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_2_2.setBounds(393, 198, 137, 22);
		contentPane.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_4 = new JLabel("Write your feedback : ");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(61, 360, 202, 22);
		contentPane.add(lblNewLabel_4);
		
		textField = new JTextField();
		textField.setBounds(61, 392, 680, 34);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton buttonSubmit = new JButton("Submit");
		buttonSubmit.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String review = textField.getText();
		        if (!review.trim().isEmpty()) {
		            int currentUserId = UserSession.getInstance().getCurrentUserId();
		            ReviewController reviewController = new ReviewController();
		            reviewController.addReview(product1.getProductId(), currentUserId, review);
		            JOptionPane.showMessageDialog(ReviewGui.this, "Review submitted successfully!");
		            ProductDetailGui productDetailGui = new ProductDetailGui(product1, customerEmail, customerPassword, 0);
		            productDetailGui.setVisible(true);
		            dispose();
		        } else {
		            JOptionPane.showMessageDialog(ReviewGui.this, "Please enter a review before submitting.");
		        }
		    }
		});
		buttonSubmit.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		buttonSubmit.setBounds(802, 479, 110, 34);
		contentPane.add(buttonSubmit);
	}

}