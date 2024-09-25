package view;

import model.Customer;
import controller.CustomerController;
import java.awt.EventQueue;
import javax.swing.JOptionPane;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class RegisterGui extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textFieldEmail;
	private JTextField textFieldPhoneNumber;
	private JTextField textFieldAddress;
	private JPasswordField confirmPasswordField;
	private JPasswordField passwordField;
	private LoginPageGui loginPageGui;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	    EventQueue.invokeLater(new Runnable() {
	        public void run() {
	            try {
	                LoginPageGui loginPageGui = new LoginPageGui();
	                RegisterGui frame = new RegisterGui(loginPageGui);
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

	private boolean isValidPhoneNumber(String phoneNumber) {
	    String phoneRegex = "^(601[02-46-9]\\d{7}|601[1]\\d{8})$";
	    return phoneNumber.matches(phoneRegex);
	}
	
	/**
	 * Create the frame.
	 */
	public RegisterGui(LoginPageGui loginPageGui) {
		
		this.loginPageGui = loginPageGui;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 960, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("APPLE SAGA STORE ");
		lblNewLabel.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 18));
		lblNewLabel.setBounds(47, 36, 185, 22);
		contentPane.add(lblNewLabel);
		
		JButton buttonBack = new JButton("Back");
		buttonBack.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        loginPageGui.setVisible(true);
		        RegisterGui.this.dispose();
		    }
		});
		buttonBack.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		buttonBack.setBounds(45, 64, 85, 34);
		contentPane.add(buttonBack);
		
		JLabel lblNewLabel_1 = new JLabel("Register");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblNewLabel_1.setBounds(421, 31, 155, 39);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Full Name : ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(199, 86, 190, 22);
		contentPane.add(lblNewLabel_2);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(199, 118, 544, 27);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Email : ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(199, 155, 77, 22);
		contentPane.add(lblNewLabel_3);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(199, 187, 544, 30);
		contentPane.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Phone Number : ");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(199, 227, 167, 22);
		contentPane.add(lblNewLabel_4);
		
		textFieldPhoneNumber = new JTextField();
		textFieldPhoneNumber.setBounds(199, 259, 544, 34);
		contentPane.add(textFieldPhoneNumber);
		textFieldPhoneNumber.setColumns(10);	
		
		JLabel lblNewLabel_4_1 = new JLabel("Password : ");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4_1.setBounds(199, 303, 167, 22);
		contentPane.add(lblNewLabel_4_1);
		
		
		JLabel lblNewLabel_4_1_1 = new JLabel("Confirm Password : ");
		lblNewLabel_4_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4_1_1.setBounds(199, 375, 185, 22);
		contentPane.add(lblNewLabel_4_1_1);
		
		textFieldAddress = new JTextField();
		textFieldAddress.setColumns(10);
		textFieldAddress.setBounds(199, 479, 544, 63);
		contentPane.add(textFieldAddress);
		
		JLabel lblNewLabel_4_1_1_1 = new JLabel("Address : ");
		lblNewLabel_4_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4_1_1_1.setBounds(199, 447, 185, 22);
		contentPane.add(lblNewLabel_4_1_1_1);
		
		JButton buttonSignUp = new JButton("Sign Up");
		buttonSignUp.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			
				// Check if any field is empty
		        if (textFieldName.getText().trim().isEmpty() ||
		            textFieldEmail.getText().trim().isEmpty() ||
		            textFieldPhoneNumber.getText().trim().isEmpty() ||
		            passwordField.getPassword().length == 0 ||
		            confirmPasswordField.getPassword().length == 0 ||
		            textFieldAddress.getText().trim().isEmpty()) {
		            
		            JOptionPane.showMessageDialog(RegisterGui.this,
		                "Please fill in all fields.",
		                "Incomplete Information",
		                JOptionPane.WARNING_MESSAGE);
		            return;
		        }
		        
		     // Validate email
		        String email = textFieldEmail.getText().trim();
		        if (!isValidEmail(email)) {
		            JOptionPane.showMessageDialog(RegisterGui.this,
		                "Please enter a valid email address (XX@mail.com). ",
		                "Invalid Email",
		                JOptionPane.WARNING_MESSAGE);
		            return;
		        }

		        // Validate phone number
		        String phoneNumber = textFieldPhoneNumber.getText().trim();
		        if (!isValidPhoneNumber(phoneNumber)) {
		            JOptionPane.showMessageDialog(RegisterGui.this,
		                "Please enter a valid phone number (601XXXXXXXX or 6011XXXXXXXX).",
		                "Invalid Phone Number",
		                JOptionPane.WARNING_MESSAGE);
		            return;
		        }
				
			CustomerController customerController = new CustomerController();
			
			char[] password = passwordField.getPassword();
		    char[] confirmPassword = confirmPasswordField.getPassword();
		 // Check if passwords match
	        if (!Arrays.equals(password, confirmPassword)) {
	            // Passwords don't match, show an error message
	            JOptionPane.showMessageDialog(RegisterGui.this,
	                "Passwords do not match. Please try again.",
	                "Password Mismatch",
	                JOptionPane.ERROR_MESSAGE);
	            return; // Exit the method without registering
	        }
	        
	        // register if match pwd
			Customer newCustomer = new Customer(
				textFieldName.getText().trim(),
				textFieldPhoneNumber.getText().trim(),
				textFieldAddress.getText().trim(),
				textFieldEmail.getText().trim(),
				new String(password));
				
			
			System.out.println(newCustomer);
			customerController.addCustomer(newCustomer);
			
			JOptionPane.showMessageDialog(RegisterGui.this,
		            "Registration successful!",
		            "Success",
		            JOptionPane.INFORMATION_MESSAGE);
		        
			// Close the RegisterGui
	        dispose();
	        
	        EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                loginPageGui.setVisible(true);
	                RegisterGui.this.dispose();
	            }
	        });
	    }
		});
		buttonSignUp.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		buttonSignUp.setBounds(822, 505, 91, 34);
		contentPane.add(buttonSignUp);
		
		confirmPasswordField = new JPasswordField();
		confirmPasswordField.setColumns(10);
		confirmPasswordField.setBounds(199, 407, 544, 30);
		contentPane.add(confirmPasswordField);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setBounds(199, 335, 544, 30);
		contentPane.add(passwordField);
		
		JCheckBox showPasswordCheckBox = new JCheckBox("Show Password");
		showPasswordCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 8));
		showPasswordCheckBox.setBounds(750, 335, 200, 30);
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

        // Show Confirm Password Checkbox
        JCheckBox showConfirmPasswordCheckBox = new JCheckBox("Show Confirm Password");
        showConfirmPasswordCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 8));
        showConfirmPasswordCheckBox.setBounds(750, 407, 200, 30);
        showConfirmPasswordCheckBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    confirmPasswordField.setEchoChar((char) 0);
                } else {
                    confirmPasswordField.setEchoChar('\u2022');
                }
            }
        });
        contentPane.add(showConfirmPasswordCheckBox);
        
        JLabel lblNewLabel_4_2 = new JLabel("[601xxxxxxxx]");
        lblNewLabel_4_2.setFont(new Font("Tahoma", Font.ITALIC, 10));
        lblNewLabel_4_2.setBounds(753, 264, 85, 22);
        contentPane.add(lblNewLabel_4_2);
        
        JLabel lblNewLabel_4_2_1 = new JLabel("[xx@mail.com]");
        lblNewLabel_4_2_1.setFont(new Font("Tahoma", Font.ITALIC, 10));
        lblNewLabel_4_2_1.setBounds(753, 195, 85, 22);
        contentPane.add(lblNewLabel_4_2_1);
	}
}
