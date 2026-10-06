package coe528.project;

/**
 * Overview: The `User` class represents a user within the banking system.
 * It manages user information such as username, password, role, and account balance.
 * This class provides functionality for reading user data from a file, updating user details, 
 * and handling account balance.
 * 
 * @author Akarshan R Singh
 */

import java.io.*;
import java.util.Scanner;

public class User {
    // Username of the user
    private String username;
    
    // Password of the user
    private String password;
    
    // Role of the user (e.g., customer, admin)
    private String role;
    
    // Balance of the user
    private double balance;

    /**
     * Constructs a User object by reading user information from a file.
     * 
     * @param username The username of the user whose information is to be read from a file.
     * @throws FileNotFoundException if the file for the user is not found.
     */
    public User(String username) throws FileNotFoundException {
        // Create a File object representing the user's file
        File file = new File(username + ".txt");
        
        // Open the file and read user information
        try (Scanner reader = new Scanner(file)) {
            // Read username, password, role, and balance from the file
            this.username = reader.nextLine(); // Read the username
            this.password = reader.nextLine(); // Read the password
            this.role = reader.nextLine(); // Read the role
            this.balance = Double.parseDouble(reader.nextLine()); // Read and parse the balance
        }
    }
    
    /**
     * Gets the username of the user.
     * 
     * @return The username of the user.
     */
    public String getUsername() {
        return username; // Return the username
    }

    /**
     * Gets the password of the user.
     * 
     * @return The password of the user.
     */
    public String getPassword() {
        return password; // Return the password
    }

    /**
     * Gets the role of the user.
     * 
     * @return The role of the user.
     */
    public String getRole() {
        return role; // Return the role
    }
    
    /**
     * Sets the balance of the user.
     * 
     * @param accountBalance The balance to be set for the user.
     */
    public void setBalance(double accountBalance) {
        this.balance = accountBalance; // Update the user's balance
    }
    
    /**
     * Gets the balance of the user.
     * 
     * @return The current balance of the user.
     */
    public double getBalance() {
        return balance; // Return the balance
    }

    /**
     * Updates the user's account information in the file.
     * 
     * @throws IOException if an I/O error occurs while writing to the file.
     */
    public void updateAccount() {
        // Create a File object representing the user's file
        File file = new File(username + ".txt");
        
        try {
            // Create a FileWriter to write user information to the file
            FileWriter Write = new FileWriter(username + ".txt");
            
            // Write the username, password, role, and balance to the file
            Write.write(this.username); // Write the username
            Write.write(System.getProperty("line.separator")); // Write a newline
            Write.write(this.password); // Write the password
            Write.write(System.getProperty("line.separator")); // Write a newline
            Write.write("customer"); // Write the role (hardcoded as "customer")
            Write.write(System.getProperty("line.separator")); // Write a newline
            Write.write("" + this.balance); // Write the balance
            Write.close(); // Close the file writer
        } catch (IOException e) {
            // Handle potential I/O errors
            e.printStackTrace(); // Print stack trace for debugging
        }
    }
}