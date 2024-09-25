package view;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class SuccessfulPlaceOrder {
	private JFrame frame;
    private JTextField txtSearch;
    
    public SuccessfulPlaceOrder() {
   	 frame = new JFrame();
   	frame.getContentPane().setForeground(new Color(0, 0, 0));
    frame.setBounds(100, 100, 449, 239);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(null);
    
    JLabel lblNewLabel = new JLabel("The Order is placed successfully.");
    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
    lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
    lblNewLabel.setBounds(96, 88, 239, 13);
    frame.getContentPane().add(lblNewLabel);
}
}