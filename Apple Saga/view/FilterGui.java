package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FilterGui extends JFrame {
    private JComboBox<String> categoryComboBox;
    private JButton applyFilterButton;
    private ProductGui productGui;

    public FilterGui(ProductGui productGui, List<String> categories) {
        this.productGui = productGui;
        setTitle("Filter Products");
        setSize(350, 200);
        setLayout(new BorderLayout());
        
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        
        JLabel titleLabel = new JLabel("Select Category:");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        
        categoryComboBox = new JComboBox<>(categories.toArray(new String[0]));
        categoryComboBox.insertItemAt("All Categories", 0);
        categoryComboBox.setSelectedIndex(0);
        categoryComboBox.setMaximumSize(new Dimension(300, 30));
        categoryComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);

       
        applyFilterButton = new JButton("Apply Filter");
        applyFilterButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        applyFilterButton.setMaximumSize(new Dimension(150, 30));

       
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        mainPanel.add(categoryComboBox);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(applyFilterButton);

        
        add(mainPanel, BorderLayout.CENTER);

        applyFilterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCategory = (String) categoryComboBox.getSelectedItem();
                productGui.applyFilter(selectedCategory);
                dispose();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }
}