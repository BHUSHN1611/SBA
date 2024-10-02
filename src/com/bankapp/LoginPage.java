package com.bankapp;

import javax.swing.*;
import java.awt.*;

public class LoginPage extends JFrame {
    private BankService bankService;

    public LoginPage(BankService bankService) {
        this.bankService = bankService;

        // Set Nimbus Look and Feel for a more modern appearance
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, default to basic look and feel
        }

        setTitle("Bank Login");
        setSize(400, 230);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Create UI elements with modern adjustments
        JLabel titleLabel = new JLabel("Bank Login", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));  // Set bold font for the title
        add(titleLabel, BorderLayout.NORTH);

        JLabel labelUsername = new JLabel("Username:");
        JLabel labelPassword = new JLabel("Password:");
        JTextField textUsername = new JTextField(15);
        JPasswordField textPassword = new JPasswordField(15);
        JButton btnLogin = new JButton("Login");
        JButton btnCreateAccount = new JButton("Create Account");

        // Modern font styling
        Font labelFont = new Font("Serif", Font.PLAIN, 16);
        labelUsername.setFont(labelFont);
        labelPassword.setFont(labelFont);
        textUsername.setFont(new Font("Serif", Font.PLAIN, 16));
        textPassword.setFont(new Font("Serif", Font.PLAIN, 16));
        btnCreateAccount.setFont(new Font("Serif", Font.PLAIN, 16));
        btnLogin.setFont(new Font("Serif", Font.PLAIN, 16));

        // Add some padding around components
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setLayout(new BorderLayout(10, 10));

        // Panel for fields (username/password)
        JPanel fieldPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        fieldPanel.add(labelUsername);
        fieldPanel.add(textUsername);
        fieldPanel.add(labelPassword);
        fieldPanel.add(textPassword);


        // Panel for buttons (login/create account)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(btnLogin);
        buttonPanel.add(btnCreateAccount);

        // Add tooltips to enhance usability
        textUsername.setToolTipText("Enter your username");
        textPassword.setToolTipText("Enter your password");
        btnLogin.setToolTipText("Click to login");
        btnCreateAccount.setToolTipText("Click to create a new account");

        // Add panels to the frame
        panel.add(fieldPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners (same logic)
        btnLogin.addActionListener(e -> {
            String username = textUsername.getText();
            String password = new String(textPassword.getPassword());

            if (username.length() > 0){

            User user = bankService.loginUser(username, password);

            if (user != null ) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                new Dashboard(user, bankService); // Open Dashboard on successful login
                this.dispose(); // Close the login window
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials!");
            }}
            else{
                JOptionPane.showMessageDialog(this, "Please enter the username and password");
            }
        });

        btnCreateAccount.addActionListener(e -> {
            new CreateAccountPage(bankService);
            this.dispose();
        });

        // Set frame content and visibility
        add(panel);
        setVisible(true);
        setResizable(false);
    }
    // Custom JPanel class with gradient background
    private class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            // Define colors for the gradient
            Color color1 = new Color(70, 130, 180); // Steel blue
            Color color2 = new Color(100, 149, 237); // Cornflower blue

            // Create GradientPaint for the background
            int width = getWidth();
            int height = getHeight();
            GradientPaint gradientPaint = new GradientPaint(0, 0, color1, width, height, color2);

            // Set the paint and fill the background
            g2d.setPaint(gradientPaint);
            g2d.fillRect(0, 0, width, height);
        }
    }

}


