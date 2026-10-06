package coe528.project;

/**
 * Overview: Customer class records the username, password, methods to deposit, withdraw, and purchase, and customer level.
 * The class is mutable.
 * 
 * Abstract Function AF(c) = Customer c such that:
 * c.level = the level of the customer,
 * c.user.username = the username of the customer,
 * c.user.password = the password of the customer,
 * c.balance = the balance of the customer,
 * c.purchase = the purchase amount of the customer.
 * 
 * RepInv RV(c) = true if c.user.username != null,
 *                        c.balance >= 0,
 *                        c.purchase >= 50,
 *                        c.balance - c.purchase - fee >= 0,
 * otherwise false.
 * 
 */
import java.io.*;

public class Customer {
    // Customer's account balance
    protected double balance;
    
    // Boolean flag to indicate the login status of the customer
    protected boolean login;
    
        protected User user;// Aggregated User object representing the customer details.
    
    // Level object representing the customer's account level (Silver, Gold, Platinum)
    protected Level level; // This line shows the aggregation relationship where the Customer class has a Level object as an attribute.
  
    /**
     * Constructs a Customer object with the specified username and password.
     * Initializes the balance and login status, and deposits the initial balance.
     * 
     * @param username The username of the customer.
     * @param password The password of the customer.
     * @throws FileNotFoundException if the user file is not found.
     */
    public Customer(String username, String password) throws FileNotFoundException {
        user = new User(username); // Initialize the user with the given username
        this.balance = 0; // Initialize balance to 0
        deposit(user.getBalance()); // Deposit the initial balance from the user file
        login = false; // Set login status to false
    }
    
    /**
     * Sets the customer's level.
     * 
     * @param level The level to be set for the customer.
     * 
     * Requires: level is a non-null instance of a subclass of Level.
     * Modifies: this.level
     * Effects: Updates the customer's level to the provided level.
     */
    public void setLevel(Level level) {
        this.level = level; // Set the customer's level
    }
    
    /**
     * Gets the customer's level.
     * 
     * @return The current level of the customer.
     * 
     * Requires: nothing
     * Modifies: nothing
     * Effects: Returns the current level of the customer.
     */
    public Level getLevel() {
        return level; // Return the customer's level
    }
    
    /**
     * Gets the user associated with this customer.
     * 
     * @return The user object associated with this customer.
     * 
     * Requires: nothing
     * Modifies: nothing
     * Effects: Returns the User object associated with this customer.
     */
    public User getUser() {
        return user; // Return the user object
    }
    
    /**
     * Sets the customer's balance.
     * 
     * @param balance The balance to be set for the customer.
     * 
     * Requires: balance is a non-negative double.
     * Modifies: this.balance
     * Effects: Updates the customer's balance to the provided value.
     */
    public void setBalance(double balance) {
        this.balance = balance; // Set the customer's balance
    }
    
    /**
     * Gets the customer's balance.
     * 
     * @return The current balance of the customer.
     * 
     * Requires: nothing
     * Modifies: nothing
     * Effects: Returns the balance of the user associated with this customer.
     */
    public double getBalance() {
        return user.getBalance(); // Return the user's balance
    }
    
    /**
     * Gets the login status of the customer.
     * 
     * @return true if the customer is logged in, false otherwise.
     * 
     * Requires: nothing
     * Modifies: nothing
     * Effects: Returns the login status of the customer.
     */
    public boolean getLogin() {
        return this.login; // Return the login status
    }
    
    /**
     * Logs out the customer by setting the login flag to false.
     * 
     * Requires: nothing
     * Modifies: this.login
     * Effects: Logs out the customer by setting the login flag to false.
     */
    public void logout() {
        login = false; // Set the login flag to false
    }
    
    /**
     * Logs in the customer by verifying the username and password.
     * 
     * @param username The username of the customer.
     * @param password The password of the customer.
     * 
     * Requires: username and password are non-null strings.
     * Modifies: this.login
     * Effects: Sets the login flag to true if the username and password match, allowing the user to access the account.
     */
    public void login(String username, String password) {
        /*
            Requires: Username and password input as Strings
            Modifies: nothing
            Effects: Sets the login flag to true if the username and password match, allowing the user to access the account
        */
        if (this.user.getUsername().equals(username) && this.user.getPassword().equals(password)) {
            login = true; // Set login flag to true if username and password match
        }
    }
    
    /**
     * Deposits a specified amount into the customer's account.
     * 
     * @param amount The amount to be deposited.
     * 
     * Requires: amount is a positive double.
     * Modifies: this.balance, user
     * Effects: Adds the deposit amount to the balance, updates the user's balance, and changes the customer's level based on the new balance.
     */
    public void deposit(double amount) {
        /*
            Requires: Deposit amount input from the user as a double
            Modifies: Balance
            Effects: Adds the amount to the balance and updates the account, changing the level based on the new balance
        */
        if (RepOk(amount, 1) == true) {
            this.balance += amount; // Add the deposit amount to the balance
            user.setBalance(this.balance); // Update the user's balance
            user.updateAccount(); // Update the account information in the file
            
            // Update the customer's level based on the new balance
            if (balance < 10000) {
                level = new Silver(); // Set level to Silver
            } else if (balance >= 10000 && balance < 20000) {
                level = new Gold(); // Set level to Gold
            } else if (balance >= 20000) {
                level = new Platinum(); // Set level to Platinum
            }
        }
    }
    
    /**
     * Withdraws a specified amount from the customer's account.
     * 
     * @param amount The amount to be withdrawn.
     * 
     * Requires: amount is a positive double.
     * Modifies: this.balance, user
     * Effects: Subtracts the withdraw amount from the balance, updates the user's balance, and changes the customer's level based on the new balance.
     */
    public void withdraw(double amount) {
        /*
            Requires: Withdraw amount input from the user as a double
            Modifies: Balance
            Effects: Subtracts the withdraw amount from the balance and updates the account, changing the level based on the new balance
        */
        if (RepOk(amount, 2) == true) {
            this.balance -= amount; // Subtract the withdraw amount from the balance
            user.setBalance(this.balance); // Update the user's balance
            user.updateAccount(); // Update the account information in the file
            
            // Update the customer's level based on the new balance
            if (balance < 10000) {
                level = new Silver(); // Set level to Silver
            } else if (balance >= 10000 && balance < 20000) {
                level = new Gold(); // Set level to Gold
            } else if (balance >= 20000) {
                level = new Platinum(); // Set level to Platinum
            }
        }
    }
    
    /**
     * Makes an online purchase, deducting the amount and any applicable fees from the customer's balance.
     * Displays a warning if the purchase amount is less than $50.
     * 
     * @param amount The cost of the purchase.
     * 
     * Requires: amount is a positive double.
     * Modifies: this.balance, user
     * Effects: Subtracts the total cost (including fee) from the balance, updates the user's balance, and changes the customer's level based on the new balance.
     */
    public void onlinePurchase(double amount) {
        /*
            Requires: Cost of the purchase as a positive double
            Modifies: Balance, user
            Effects: Deducts the cost of the purchase and applicable fee from the balance, updating the account and changing the level if necessary
        */
        if (RepOk(amount, 3)) {
            if (amount < 50) {
                System.out.println("Warning: Minimum purchase should be $50.");
                return; // Exit the method if the purchase amount is less than $50
            }
            double totalCost = amount + level.getFee(); // Calculate total cost including fee
            if (totalCost <= balance) {
                balance -= totalCost; // Deduct total cost from balance
                user.setBalance(balance); // Update the user's balance
                user.updateAccount(); // Update the account information in the file
                updateLevel(); // Ensure to update the customer's level after the purchase
            }
        }
    }
    
    /**
     * Updates the customer's level based on the current balance.
     * 
     * Requires: nothing
     * Modifies: this.level
     * Effects: Sets the customer's level to Silver, Gold, or Platinum based on the balance.
     */
    public void updateLevel() {
        if (balance < 10000) {
            level = new Silver(); // Set level to Silver
        } else if (balance >= 10000 && balance < 20000) {
            level = new Gold(); // Set level to Gold
        } else if (balance >= 20000) {
            level = new Platinum(); // Set level to Platinum
        }
    }
    
    /**
     * Checks if the given amount and state of the customer are valid.
     * 
     * @param amount The amount to check.
     * @param cases An integer representing the type of operation (1 for deposit, 2 for withdraw, 3 for purchase).
     * @return true if the operation is valid based on the current state, false otherwise.
     * 
     * Requires: amount is a positive double and cases is an integer between 1 and 3.
     * Modifies: nothing
     * Effects: Returns true if the operation is valid, otherwise false.
     */
    public boolean RepOk(double amount, int cases) {
        boolean rep = false;
        switch (cases) {
            case 1: // Depositing money into the account
                if (amount > 0) {
                    rep = true; // Valid deposit amount
                }
                break;
            case 2: // Withdrawing money from the account
                if (amount > 0 && this.balance - amount >= 0) {
                    rep = true; // Valid withdrawal amount
                }
                break;
            case 3: // Online purchasing
                if (amount > 0 && this.balance - amount - level.getFee() >= 0) {
                    rep = true; // Valid purchase amount including fee
                }
                break;
        }
        return rep; // Return whether the operation is valid
    }
    
    /**
     * Returns a string representation of the Customer object.
     * 
     * @return A string representation of the Customer's level, username, password, and balance.
     * 
     * Requires: nothing
     * Modifies: nothing
     * Effects: Returns a string that includes the customer's level, username, password, and balance.
     */
    @Override
    public String toString() {
        // Implement the abstraction function (AF) in the toString() method
        return "Customer [level=" + level + ", username=" + user.getUsername() + 
               ", password=" + user.getPassword() + ", balance=" + balance + "]";
    }
}