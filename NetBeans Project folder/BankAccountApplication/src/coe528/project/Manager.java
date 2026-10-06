package coe528.project;

/**
 * Overview: The `Manager` class represents a bank manager with administrative privileges. 
 * It provides methods for logging in and logging out, and allows for the addition and deletion of customer accounts. 
 * The class ensures that only valid operations are performed based on the user role and account details.
 * 
 * @author Akarshan R Singh
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Manager {
    // Boolean flag to indicate the login status of the manager
    protected boolean login;
    
    // User object representing the manager (although not used in the given code)
    protected User user;
    
    /**
     * Constructs a Manager object with the specified username and password.
     * Sets the login flag to true if the username and password are both "admin".
     * @param username The username of the manager.
     * @param password The password of the manager.
     */
    public Manager(String username, String password) {
        // Check if the provided username and password are both "admin"
        login = username.equals("admin") && password.equals("admin");
    }
    
    /**
     * Logs out the manager by setting the login flag to false.
     */
    public void logout() {
        login = false; // Set the login flag to false
    }
    
    /**
     * Gets the login status of the manager.
     * @return true if the manager is logged in, false otherwise.
     */
    public boolean getLogin() {
        return login; // Return the current login status
    }
    
    /**
     * Adds a customer with the specified username and password.
     * Checks if the customer can be added using the RepOk method.
     * @param username The username of the new customer.
     * @param password The password of the new customer.
     * @return true if the customer was added successfully, false otherwise.
     */
    protected boolean addCustomer(String username, String password) {
        // Check if the customer meets the representation invariant
        if (RepOk(100, username, 1) == true) {
            try {
                File file = new File(username + ".txt"); // Create a file object for the customer's file and Manager uses files named after customer usernames to manage customer data
                
                // Check if the customer file already exists
                if (file.exists()) {
                    return false; // Return false if the file already exists
                } else {
                    // Create a new file for the customer
                    file.createNewFile();
                    try (FileWriter Write = new FileWriter(username + ".txt")) {
                        // Write the customer's username to the file
                        Write.write(username);
                        Write.write(System.getProperty("line.separator"));
                        
                        // Write the customer's password to the file
                        Write.write("" + password);
                        Write.write(System.getProperty("line.separator"));
                        
                        // Write the customer type to the file (in this case, "customer")
                        Write.write("customer");
                        Write.write(System.getProperty("line.separator"));
                        
                        // Write the initial balance of 100.0 to the file
                        Write.write("100.0");
                    }
                }
            } catch (IOException e) {
                // Handle any IO exceptions that occur
            }
            return true; // Return true if the customer was added successfully
        } else {
            return false; // Return false if the customer did not meet the representation invariant
        }
    }
    
    /**
     * Deletes a customer with the specified username.
     * Checks if the customer can be deleted using the RepOk method.
     * @param user The username of the customer to be deleted.
     * @return true if the customer was deleted successfully, false otherwise.
     */
    protected boolean deleteCustomer(String user) {
        // Check if the customer meets the representation invariant for deletion
        if (RepOk(0, user, 2)) {
            File file = new File(user + ".txt"); // Create a file object for the customer's file and  Manager uses files named after customer usernames to manage customer data
            
            // Check if the customer file exists
            if (file.exists()) {
                file.delete(); // Delete the customer file
                return true; // Return true if the file was deleted successfully
            } else {
                return false; // Return false if the file does not exist
            }
        } else {
            return false; // Return false if the customer did not meet the representation invariant for deletion
        }
    }
    
    /**
     * Checks the representation invariant for adding and deleting customers.
     * @param balance The balance of the customer (for adding).
     * @param user The username of the customer.
     * @param cases The case to check (1 for adding, 2 for deleting).
     * @return true if the representation invariant is satisfied, false otherwise.
     */
    public boolean RepOk(double balance, String user, int cases) {
        boolean rep = false; // Initialize the representation invariant flag to false
        switch (cases) {
            case 1: // Adding a new customer
                // Check if the balance is at least 100 and the username is not "admin"
                if (balance >= 100 && !user.equals("admin")) {
                    rep = true; // Set the flag to true if the conditions are met
                    break;
                } else {
                    break;
                }
            case 2: // Deleting a customer
                // Check if the username is not "admin"
                if (!user.equals("admin")) {
                    rep = true; // Set the flag to true if the condition is met
                    break;
                } else {
                    break;
                }
        }
        return rep; // Return the representation invariant flag
    }
    
    @Override  
public String toString() { 
    return "Manager{" + "login=" + login + ", user=" + (user != null ? user.toString() : "null") + '}';  
 // Constructs and returns a string that includes the Manager's login status and the details of the User object,
// or "null" if the User is not set.
}
}