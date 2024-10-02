package com.bankapp;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class BankService {
    private Map<String, User> users = new HashMap<>();
    private static final String FILE_NAME = "users.txt";

    public BankService() {
        loadUsers(); // Load users from file when the service is initialized
    }

    // Create a new user account
    public boolean createUser(String username, String password) {
        if (users.containsKey(username)) {
            return false; // User already exists
        }
        users.put(username, new User(username, password));
        saveUsers(); // Save users to file after creating a new account
        return true;
    }

    // Log in with existing user credentials
    public User loginUser(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user; // Successful login
        }
        return null; // Invalid login
    }

    // Save users to the file
    private void saveUsers() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(users); // Write the users map to the file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load users from the file
    @SuppressWarnings("unchecked")
    private void loadUsers() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
                users = (Map<String, User>) in.readObject(); // Read the users map from the file
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public double checkBalance(User user) {
        return user.getBalance();
    }

    public void deposit(User user, double amount) {
        user.deposit(amount);
        saveUsers(); // Save users to file after depositing money
    }

    public boolean withdraw(User user, double amount) {
        if (user.withdraw(amount)) {
            saveUsers(); // Save users to file after withdrawing money
            return true;
        }
        return false;
    }

    // Transfer money from one user to another
    public boolean transferMoney(User sender, String receiverUsername, double amount) {
        User receiver = users.get(receiverUsername);

        if (sender.getBalance() >= amount) {
            sender.withdraw(amount);
//            receiver.deposit(amount);
            saveUsers(); // Save changes after transferring money
            return true;
        }
        return false; // Transfer failed (either user not found or insufficient funds)
    }
}
