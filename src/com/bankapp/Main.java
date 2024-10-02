package com.bankapp;

public class Main {
    public static void main(String[] args) {
        BankService bankService = new BankService(); // Initialize the BankService
        new LoginPage(bankService); // Launch the login page
    }
}


