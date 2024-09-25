package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.CustomerController;
import controller.ProductController;
import controller.ShoppingCartController;
import model.CartItem;
import model.Customer;
import model.Product;
import model.ShoppingCart;
import model.UserSession;
import com.mysql.jdbc.Connection;

import database.MyDatabase;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JButton;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class ProfileGui extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel showCustName;
	private JLabel showCustEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProfileGui frame = new ProfileGui("customerEmail", "customerPassword");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void updateCustomerDetails(String email, String password) {
	    CustomerController customerController = new CustomerController();
	    Customer customer = customerController.getCustomerDetailbyUsernamePassword(email, password);

	    if (customer != null) {
	        showCustName.setText("USERNAME: " + customer.getCustomerName());
	        showCustEmail.setText("EMAIL: " + customer.getCustomerEmail());
	    }
	}

	/**
	 * Create the frame.
	 */
	public ProfileGui(String email, String password) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 960, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("APPLE SAGA STORE ");
		lblNewLabel.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 18));
		lblNewLabel.setBounds(34, 31, 185, 22);
		contentPane.add(lblNewLabel);
		
		JButton buttonLogout = new JButton("Logout");
		buttonLogout.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        LoginPageGui loginPage = new LoginPageGui();
		        loginPage.setVisible(true);
		        dispose();
		    }
		});
		buttonLogout.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		buttonLogout.setBounds(34, 57, 101, 34);
		contentPane.add(buttonLogout);
		
		JLabel lblNewLabel_1 = new JLabel("Profile");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblNewLabel_1.setBounds(402, 31, 135, 39);
		contentPane.add(lblNewLabel_1);
		
		JButton buttonOrder = new JButton("Order History ");
		buttonOrder.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 18));
		buttonOrder.setBounds(224, 367, 498, 39);
		contentPane.add(buttonOrder);
		buttonOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 Connection conn;
				try {
					conn = (Connection) MyDatabase.doConnection();
					  // Fetch customer data as needed (example)
	                 Customer customer = new Customer();
					OrderHistoryGui frame = new OrderHistoryGui(conn, customer,email,password);
					frame.setVisible(true);
					dispose();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

               
			}
		});

		
		JButton buttonShopping = new JButton("Shopping Cart");
		buttonShopping.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 18));
		buttonShopping.setBounds(225, 416, 497, 39);
		
		contentPane.add(buttonShopping);
		buttonShopping.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        dispose();
		        ShoppingCartController cartController = new ShoppingCartController();
		        try {
		            cartController.connectToDatabase();
		        } catch (SQLException e1) {
		            e1.printStackTrace();
		        }
		        int customerId = getCurrentCustomerId();
		        ShoppingCart cart = cartController.getShoppingCartDetailbyCustomerId(customerId);
		        List<Product> products = new ArrayList<>();
		        
		        if (cart != null && cart.getCartItems() != null) {
		            for (CartItem item : cart.getCartItems()) {
		                products.add(item.getCartItemProduct());
		            }
		        }
		        
		        view.ShoppingCartGui frame = new view.ShoppingCartGui(email, password, cart, null);
		        frame.setVisible(true);
		    }
		});
		
		showCustName = new JLabel("Username ");
		showCustName.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		showCustName.setBounds(373, 238, 250, 34);
		contentPane.add(showCustName);
		
		showCustEmail = new JLabel(" Email");
		showCustEmail.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		showCustEmail.setBounds(373, 272, 250, 34);
		contentPane.add(showCustEmail);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
        try {
            Image image = ImageIO.read(new File("C:\\Users\\Manni\\Documents\\UNIVERSITY STUDY\\Y2S2 sub\\OOP SANUSI\\PROJECT APPLE\\src\\com\\apple\\resources\\product_images\\profile.png"));
            //*****use your own location*******
            Image scaledImage = image.getScaledInstance(128, 128, Image.SCALE_SMOOTH);
            lblNewLabel_3.setIcon(new ImageIcon(scaledImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
        lblNewLabel_3.setPreferredSize(new Dimension(128, 128));
        lblNewLabel_3.setBounds(402, 90, 128, 128);
        contentPane.add(lblNewLabel_3);
        
        JButton btnShopping = new JButton("Shop Products");
        btnShopping.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ProductController controller = new ProductController();
                List<Product> products = controller.getAllProduct();
                new ProductGui(products, null, ProfileGui.this, email, password); // Pass the email and password
                setVisible(false); 
            }
        });
        btnShopping.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 18));
        btnShopping.setBounds(224, 316, 498, 39);
        contentPane.add(btnShopping);
		
        updateCustomerDetails(email, password);
	}
	
	private int getCurrentCustomerId() {
	    return UserSession.getInstance().getCurrentUserId();
	}
}