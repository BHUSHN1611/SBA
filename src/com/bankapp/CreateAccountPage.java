package com.bankapp;

import javax.swing.*;
import java.awt.*;

public class CreateAccountPage extends JFrame {
    private BankService bankService;

    public CreateAccountPage(BankService bankService) {
        this.bankService = bankService;

        setTitle("Create Account");
        setSize(400, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        JLabel titleLabel = new JLabel("Create Account", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));  // Set bold font for the title
        add(titleLabel, BorderLayout.NORTH);

        // Create UI elements
        JLabel labelUsername = new JLabel("Username:");
        labelUsername.setFont(new Font("Serif", Font.PLAIN, 16));
        JLabel labelPassword = new JLabel("Password:");
        labelPassword.setFont(new Font("Serif", Font.PLAIN, 16));
        JTextField textUsername = new JTextField(15);
        JPasswordField textPassword = new JPasswordField(16);
        JButton btnCreate = new JButton("Create Account");
        btnCreate.setFont(new Font("Serif", Font.PLAIN, 16));

        // Add components to a panel
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 15));
        panel.add(labelUsername);
        panel.add(textUsername);
        panel.add(labelPassword);
        panel.add(textPassword);
        panel.add(btnCreate);

        // Action listener to create a new account
        btnCreate.addActionListener(e -> {
            String username = textUsername.getText();
            String password = new String(textPassword.getPassword());

            if (username.length()>0){

            if (bankService.createUser(username, password)) {
                JOptionPane.showMessageDialog(this, "Account created successfully!");
                new LoginPage(bankService);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Username already exists!");
            }}
            else{
                JOptionPane.showMessageDialog(this, "Please enter the username and password");
            }

        });

        // Add the panel to the frame
        add(panel);
        setVisible(true);
        setResizable(false);
    }
}
