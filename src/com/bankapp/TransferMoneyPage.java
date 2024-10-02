package com.bankapp;

import javax.swing.*;
import java.awt.*;

public class TransferMoneyPage extends JFrame {
    private User user;
    private BankService bankService;

    public TransferMoneyPage(User user, BankService bankService) {
        this.user = user;
        this.bankService = bankService;

        setTitle("Transfer Money");
        setSize(400, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        JLabel titleLabel = new JLabel("Recipient  details", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.PLAIN, 16));  // Set bold font for the title
        add(titleLabel, BorderLayout.NORTH);

        // Create UI elements
        JLabel labelReceiver = new JLabel("Bank account number");
        labelReceiver.setFont(new Font("Serif", Font.PLAIN, 16));
        JLabel labelAmount = new JLabel("Amount to Transfer:");
        labelAmount.setFont(new Font("Serif", Font.PLAIN, 16));
        JTextField textReceiver = new JTextField(15);
        JTextField textAmount = new JTextField(15);
        JButton btnSend = new JButton("Send Money");
        btnSend.setFont(new Font("Serif", Font.PLAIN, 16));

        // Action listener for transferring money
        btnSend.addActionListener(e -> {
            String receiverUsername = textReceiver.getText();
            if (receiverUsername.length()>0) {
                try {
                    double amount = Double.parseDouble(textAmount.getText());

                    // Perform transfer
                    if (bankService.transferMoney(user, receiverUsername, amount)) {
                        JOptionPane.showMessageDialog(this, "Transfer successful!");
                        new Dashboard(user, bankService); // Return to dashboard after transfer
                        this.dispose(); // Close the transfer window
                    } else {
                        JOptionPane.showMessageDialog(this, "Transfer failed! Check account or balance.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid amount!");
                }
            }
            else{
                JOptionPane.showMessageDialog(this, "Please enter receiverUsername");

            }
        });

        // Add components to the panel
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.add(labelReceiver);
        panel.add(textReceiver);
        panel.add(labelAmount);
        panel.add(textAmount);
        panel.add(btnSend);

        // Add the panel to the frame
        add(panel);
        setVisible(true);
        setResizable(false);
    }
}

