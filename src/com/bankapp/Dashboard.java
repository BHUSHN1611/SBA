package com.bankapp;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {
    private User user;
    private BankService bankService;

    public Dashboard(User user, BankService bankService) {
        this.user = user;
        this.bankService = bankService;

        setTitle("Bank Dashboard");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        JLabel titleLabel = new JLabel("Hello " + user.getUsername() +" !");
        titleLabel.setFont(new Font("Serif", Font.PLAIN, 20));  // Set bold font for the title
        add(titleLabel, BorderLayout.NORTH);

        // Create UI elements
        JLabel labelBalance = new JLabel("Your Current Balance: " + user.getBalance() +" Rs");
        labelBalance.setFont(new Font("Serif", Font.PLAIN, 18));
        JTextField textAmount = new JTextField(15);
        textAmount.setFont(new Font("Serif", Font.PLAIN, 16));


        JButton btnDeposit = new JButton("Deposit");
        btnDeposit.setFont(new Font("Serif", Font.PLAIN, 16));

        JButton btnWithdraw = new JButton("Withdraw");
        btnWithdraw.setFont(new Font("Serif", Font.PLAIN, 16));

        JButton btnTransfer = new JButton("Transfer Money");
        btnTransfer.setFont(new Font("Serif", Font.PLAIN, 16));// New button for transferring money

        // Action listener for deposit
        btnDeposit.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(textAmount.getText());
                bankService.deposit(user, amount);
                labelBalance.setText("Current Balance: " + user.getBalance() + " Rs");
                JOptionPane.showMessageDialog(this, "Deposit successful!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid amount!");
            }
        });

        // Action listener for withdraw
        btnWithdraw.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(textAmount.getText());
                if (bankService.withdraw(user, amount)) {
                    labelBalance.setText("Current Balance: $" + user.getBalance());
                    JOptionPane.showMessageDialog(this, "Withdraw successful!");
                } else {
                    JOptionPane.showMessageDialog(this, "Insufficient balance!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid amount!");
            }
        });

        // Action listener for transfer money
        btnTransfer.addActionListener(e -> {
            new TransferMoneyPage(user, bankService); // Open the Transfer Money page
            this.dispose(); // Close the current dashboard
        });

        // Add components to the panel
        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.add(labelBalance);
        panel.add(textAmount);
        panel.add(btnDeposit);
        panel.add(btnWithdraw);
        panel.add(btnTransfer); // Add transfer button to the dashboard

        // Add the panel to the frame
        add(panel);
        setVisible(true);
        setResizable(false);
    }
}
