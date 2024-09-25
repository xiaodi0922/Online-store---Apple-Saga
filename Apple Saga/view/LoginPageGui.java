package view;

import controller.CustomerController;
import model.Customer;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.Color;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;

public class LoginPageGui extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldEmail;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPageGui frame = new LoginPageGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private boolean isValidEmail(String email) {
	    String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
	    return email.matches(emailRegex);
	}

	/**
	 * Create the frame.
	 */
	public LoginPageGui() {
		
		CustomerController customerController = new CustomerController();
		customerController.connectToDatabase();
		
		//frame setting
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 960, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblNewLabel.setBounds(427, 55, 132, 39);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Welcome to APPLE SAGA STORE");
		lblNewLabel_1.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 24));
		lblNewLabel_1.setBounds(286, 117, 386, 24);
		contentPane.add(lblNewLabel_1);
		
		//email label
		JLabel lblNewLabel_2 = new JLabel("Email : ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(174, 193, 105, 24);
		contentPane.add(lblNewLabel_2);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(174, 227, 556, 33);
		contentPane.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		//password label
		JLabel lblNewLabel_3 = new JLabel("Password : ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(174, 283, 105, 33);
		contentPane.add(lblNewLabel_3);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(174, 319, 556, 33);
		contentPane.add(passwordField);
	
		
		JLabel lblNewLabel_4 = new JLabel("Don't have an account? ");
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(174, 359, 167, 22);
		contentPane.add(lblNewLabel_4);
		
		JCheckBox showPasswordCheckBox = new JCheckBox("Show Password");
		showPasswordCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 8));
		showPasswordCheckBox.setBackground(new Color(192, 192, 192));
        showPasswordCheckBox.setBounds(736, 319, 120, 33);
        showPasswordCheckBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    passwordField.setEchoChar((char) 0);
                } else {
                    passwordField.setEchoChar('\u2022');
                }
            }
        });
        contentPane.add(showPasswordCheckBox);
    
		
		JToggleButton buttonSignUp = new JToggleButton("Sign up here");
		buttonSignUp.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        RegisterGui registerGui = new RegisterGui(LoginPageGui.this);
		        registerGui.setVisible(true);
		        LoginPageGui.this.setVisible(false);
		    }
		});
		buttonSignUp.setBackground(UIManager.getColor("Button.darkShadow"));
		buttonSignUp.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		buttonSignUp.setBounds(340, 362, 132, 21);
		contentPane.add(buttonSignUp);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String customerEmail = textFieldEmail.getText().trim();
				String customerPassword = new String(passwordField.getPassword()).trim();
				
				 // Input validation
		        if (customerEmail.isEmpty() || customerPassword.isEmpty()) {
		            JOptionPane.showMessageDialog(LoginPageGui.this, 
		                "Please enter both email and password.", 
		                "Input Error", 
		                JOptionPane.WARNING_MESSAGE);
		            return;
		        }

		        // Basic email format validation
		        if (!isValidEmail(customerEmail)) {
		            JOptionPane.showMessageDialog(LoginPageGui.this, 
		                "Please enter a valid email address (XX@mail.com). ", 
		                "Invalid Email", 
		                JOptionPane.WARNING_MESSAGE);
		            return;
		        }

		        CustomerController customerController = new CustomerController();

		        try {
		            Customer loggedInCustomer = customerController.getCustomerDetailbyUsernamePassword(customerEmail, customerPassword);

		            if (loggedInCustomer != null) {
		                // Successful login
		                JOptionPane.showMessageDialog(LoginPageGui.this, "Login successful!");
		                ProfileGui frame = new ProfileGui(customerEmail, customerPassword);
		                frame.setVisible(true);
		                dispose();
		            } else {
		                // Failed login
		                JOptionPane.showMessageDialog(LoginPageGui.this, 
		                    "Invalid email or password. Please try again.", 
		                    "Login Failed", 
		                    JOptionPane.ERROR_MESSAGE);
		            }
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(LoginPageGui.this, 
		                "An error occurred: " + ex.getMessage(), 
		                "Error", 
		                JOptionPane.ERROR_MESSAGE);
		            ex.printStackTrace();
		        }
		    }
		});
		
		btnNewButton.setBackground(UIManager.getColor("Button.darkShadow"));
		btnNewButton.setFont(new Font("Microsoft New Tai Lue", Font.PLAIN, 20));
		btnNewButton.setBounds(415, 434, 132, 33);
		contentPane.add(btnNewButton);
	}
}
		
		
