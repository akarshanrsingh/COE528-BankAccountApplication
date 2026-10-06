package coe528.project;

import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * Overview: The Bank class is the main interface for the banking application. It provides a graphical user 
 * interface (GUI) using JavaFX to handle user interactions. 
 * The class manages login processes for both customers and managers, and displays different screens based on 
 * user roles. It includes functionality for customers to deposit, withdraw, and make online purchases, and for
 * managers to add or delete customer accounts.
 * 
 * @author Akarshan R Singh
 */

public class Bank extends Application {
    private Customer customer; // Bank aggregates Customer to manage customer interactions and operations
    private Manager manager; // Bank aggregates Manager to handle administrative tasks and operations
    private TextField Value; // TextField for inputting amounts
    private TextField cusUser; // TextField for customer username input
    private TextField cusPass; // TextField for customer password input
    private Button send; // Button to submit actions
    private Label user; // Label for displaying user-related information
    private Label pass; // Label for displaying password-related information
    private Label currentValue; // Label for displaying current value information
    private Label footer; // Label for displaying footer information
    private Scene loginScreen; // Scene for the login screen
    private Scene customerScreen; // Scene for the customer dashboard
    private Scene managerScreen; // Scene for the manager dashboard
    
    @Override
    public void start(Stage primaryStage) {
        // Set the title of the primary stage
        primaryStage.setTitle("Bank Application");
        
        // Create UI components for the login screen
        TextField username = new TextField(); // TextField for username input
        username.setPromptText("Username"); // Set prompt text for username input
        PasswordField password = new PasswordField(); // PasswordField for password input
        password.setPromptText("Password"); // Set prompt text for password input
        Label errorMessage = new Label(""); // Label to display error messages
        Button enter = new Button("Login"); // Button to submit login credentials
        
        // Create and style the layout for the login screen
        VBox layout1 = new VBox(20); // Vertical layout with 20 pixels spacing between elements
        layout1.getChildren().addAll(username, password, enter, errorMessage); // Add UI components to layout
        layout1.setStyle("-fx-background-color: #f0f8ff; -fx-padding: 20px; -fx-alignment: center;"); // Set light blue background and center alignment
        loginScreen = new Scene(layout1, 700, 300); // Create scene with specified width and height
        
        // Event handler for the login button
        enter.setOnAction((ActionEvent action) -> {
            if (username.getText().equals("admin")) { // Check if the username is "admin"
                try {
                    manager = new Manager(username.getText(), password.getText()); // Initialize manager
                } catch(Exception e) {
                    errorMessage.setText("Error: " + e.getMessage()); // Display error message if initialization fails
                }
                if (manager.getLogin() == true) { // Check if manager login is successful
                    // Create UI components for the manager dashboard
                    Button addCus = new Button("Add Customer"); // Button to add a customer
                    Button delCus = new Button("Delete Customer"); // Button to delete a customer
                    Button logout = new Button("Logout"); // Button to logout
                    Label errorMessage2 = new Label(""); // Label to display error messages
                    cusUser = new TextField(); // TextField for customer username input
                    cusPass = new TextField(); // TextField for customer password input
                    send = new Button("Enter"); // Button to submit customer actions
                    user = new Label(""); // Label for user information
                    pass = new Label(""); // Label for password information
                    
                    // Disable and hide customer input fields initially
                    cusUser.setDisable(true); // Disable the username input field
                    cusUser.setVisible(false); // Hide the username input field
                    cusPass.setDisable(true); // Disable the password input field
                    cusPass.setVisible(false); // Hide the password input field
                    send.setDisable(true); // Disable the "Enter" button
                    send.setVisible(false); // Hide the "Enter" button
                    user.setDisable(true); // Disable the user information label
                    user.setVisible(false); // Hide the user information label
                    pass.setDisable(true); // Disable the password information label
                    pass.setVisible(false); // Hide the password information label
                    
                    // Create and style the layout for the manager screen
                    BorderPane border = new BorderPane(); // Create BorderPane layout
                    HBox hbox = new HBox(10, addCus, delCus, logout, errorMessage2); // Horizontal layout for top controls
                    hbox.setStyle("-fx-background-color: #b0e57c; -fx-padding: 10px;"); // Set light green background for the top controls
                    HBox bottom = new HBox(10, cusUser, cusPass, send, user, pass); // Horizontal layout for customer input controls
                    bottom.setStyle("-fx-background-color: #d3f4f6; -fx-padding: 10px;"); // Set light cyan background for input controls
                    border.setTop(hbox); // Set top section of BorderPane
                    border.setLeft(bottom); // Set left section of BorderPane
                    border.setStyle("-fx-background-color: #e6e6fa;"); // Set light lavender background for the manager screen
                    managerScreen = new Scene(border, 700, 300); // Create scene for manager screen
                    primaryStage.setScene(managerScreen); // Set manager screen as the current scene
                    primaryStage.show(); // Show the primary stage

                    // Event handler for Add Customer button
                    addCus.setOnAction(e -> {
                        // Enable and show customer input fields for adding a new customer
                        cusUser.setDisable(false); // Enable the username input field
                        cusUser.setVisible(true); // Show the username input field
                        send.setDisable(false); // Enable the "Enter" button
                        send.setVisible(true); // Show the "Enter" button
                        cusPass.setDisable(false); // Enable the password input field
                        cusPass.setVisible(true); // Show the password input field
                        user.setDisable(true); // Disable the user information label
                        user.setVisible(false); // Hide the user information label
                        pass.setDisable(true); // Disable the password information label
                        pass.setVisible(false); // Hide the password information label
                        cusUser.setPromptText("Enter a Username"); // Set prompt text for username input
                        cusPass.setPromptText("Enter a Password"); // Set prompt text for password input
                        
                        // Event handler for the "Enter" button in add customer mode
                        send.setOnAction(button -> {
                            // Check if adding the customer is successful
                            if (manager.addCustomer(cusUser.getText(), cusPass.getText())) {
                                manager.addCustomer(cusUser.getText(), cusPass.getText()); // Add customer
                            } else {
                                errorMessage2.setText("That user already exists"); // Display error message if user already exists
                            }
                        });
                    });

                    // Event handler for Delete Customer button
                    delCus.setOnAction(e -> {
                        // Enable and show customer username input field for deleting a customer
                        cusUser.setDisable(false); // Enable the username input field
                        cusUser.setVisible(true); // Show the username input field
                        cusPass.setDisable(true); // Disable the password input field
                        cusPass.setVisible(false); // Hide the password input field
                        send.setDisable(false); // Enable the "Enter" button
                        send.setVisible(true); // Show the "Enter" button
                        user.setDisable(true); // Disable the user information label
                        user.setVisible(false); // Hide the user information label
                        pass.setDisable(true); // Disable the password information label
                        pass.setVisible(false); // Hide the password information label
                        cusUser.setPromptText("Enter a Username"); // Set prompt text for username input
                        
                        // Event handler for the "Enter" button in delete customer mode
                        send.setOnAction(button -> {
                            // Check if the customer is successfully deleted
                            if (manager.deleteCustomer(cusUser.getText())) {
                                manager.deleteCustomer(cusUser.getText()); // Delete customer
                            } else {
                                errorMessage2.setText("User doesn't exist!"); // Display error message if user does not exist
                            }
                        });
                    });

                    // Event handler for Logout button
                    logout.setOnAction(e -> {
                        primaryStage.setScene(loginScreen); // Switch back to the login screen
                        manager.logout(); // Logout the manager
                    });  
                } else {
                    errorMessage.setText("Password is incorrect"); // Display error message for incorrect password
                }
            } else {
                try {
                    customer = new Customer(username.getText(), password.getText()); // Initialize customer
                    customer.login(username.getText(), password.getText()); // Attempt to login customer
                } catch(FileNotFoundException e) {
                    errorMessage.setText("Account does not exist"); // Display error message if account does not exist
                }
                // Check if customer login is successful
                if (customer.getLogin() == true) {              
                    // Create UI components for the customer dashboard
                    Button deposit = new Button("Deposit money"); // Button to deposit money
                    Button withdrawMoney = new Button("Withdraw money"); // Button to withdraw money
                    Button purchase = new Button("Online Purchase"); // Button for online purchase
                    Button logout = new Button("Logout"); // Button to logout
                    Label errorMessage2 = new Label(""); // Label to display error messages
                    Value = new TextField(); // TextField for inputting amounts
                    send = new Button("Enter"); // Button to submit actions
                    currentValue = new Label(""); // Label to display current value information
                    footer = new Label("Balance: " + customer.getBalance() + "\nLevel: " + customer.getLevel().getLevelStr()); // Footer displaying balance and level
                    
                    // Disable and hide amount input fields initially
                    Value.setDisable(true); // Disable the amount input field
                    send.setDisable(true); // Disable the "Enter" button
                    Value.setVisible(false); // Hide the amount input field
                    send.setVisible(false); // Hide the "Enter" button
                    currentValue.setDisable(true); // Disable the current value label
                    currentValue.setVisible(false); // Hide the current value label
                    
                    // Create and style the layout for the customer screen
                    BorderPane border = new BorderPane(); // Create BorderPane layout
                    HBox hbox = new HBox(10, deposit, withdrawMoney, purchase, logout, errorMessage2); // Horizontal layout for top controls
                    hbox.setStyle("-fx-background-color: #b0e57c; -fx-padding: 10px;"); // Set light green background for top controls
                    HBox bottom = new HBox(10, Value, send, currentValue); // Horizontal layout for amount controls
                    bottom.setStyle("-fx-background-color: #d3f4f6; -fx-padding: 10px;"); // Set light cyan background for amount controls
                    HBox floor = new HBox(footer); // Horizontal layout for footer
                    floor.setStyle("-fx-background-color: #e0ffff; -fx-padding: 10px;"); // Set light cyan background for footer
                    border.setTop(hbox); // Set top section of BorderPane
                    border.setLeft(bottom); // Set left section of BorderPane
                    border.setBottom(floor); // Set bottom section of BorderPane
                    border.setStyle("-fx-background-color: #d3f4f6;"); // Set light cyan background for the customer screen
                    customerScreen = new Scene(border, 700, 300); // Create scene for customer screen
                    primaryStage.setScene(customerScreen); // Set customer screen as the current scene
                    primaryStage.show(); // Show the primary stage

                    // Event handler for Deposit button
                    deposit.setOnAction(e -> {
                        // Enable and show amount input fields for deposit
                        Value.setDisable(false); // Enable the amount input field
                        send.setDisable(false); // Enable the "Enter" button
                        Value.setVisible(true); // Show the amount input field
                        send.setVisible(true); // Show the "Enter" button
                        currentValue.setDisable(true); // Disable the current value label
                        currentValue.setVisible(false); // Hide the current value label
                        Value.setPromptText("Deposit Amount"); // Set prompt text for deposit amount input
                        
                        // Event handler for the "Enter" button in deposit mode
                        send.setOnAction(button -> {
                            customer.deposit(Double.parseDouble(Value.getText())); // Deposit the amount
                            footer.setText("Balance: " + customer.getBalance() + "\nLevel: " + customer.getLevel().getLevelStr()); // Update footer with new balance and level
                        });
                    });

                    // Event handler for Withdraw button
                    withdrawMoney.setOnAction(e -> {
                        // Enable and show amount input fields for withdrawal
                        Value.setDisable(false); // Enable the amount input field
                        send.setDisable(false); // Enable the "Enter" button
                        Value.setVisible(true); // Show the amount input field
                        send.setVisible(true); // Show the "Enter" button
                        currentValue.setDisable(true); // Disable the current value label
                        currentValue.setVisible(false); // Hide the current value label
                        Value.setPromptText("Withdraw Amount"); // Set prompt text for withdrawal amount input
                        
                        // Event handler for the "Enter" button in withdraw mode
                        send.setOnAction(button -> {
                            // Check if withdrawal amount is less than or equal to balance
                            if (Double.parseDouble(Value.getText()) <= customer.getBalance()) {
                                customer.withdraw(Double.parseDouble(Value.getText())); // Withdraw the amount
                                footer.setText("Balance: " + customer.getBalance() + "\nLevel: " + customer.getLevel().getLevelStr()); // Update footer with new balance and level
                            } else {
                                errorMessage2.setText("You don't have enough Money!"); // Display error message if insufficient funds
                            }
                        });
                    });

                    // Event handler for Online Purchase button
                    purchase.setOnAction(e -> {
                        // Enable and show amount input fields for purchase
                        Value.setDisable(false); // Enable the amount input field
                        send.setDisable(false); // Enable the "Enter" button
                        Value.setVisible(true); // Show the amount input field
                        send.setVisible(true); // Show the "Enter" button
                        currentValue.setDisable(true); // Disable the current value label
                        currentValue.setVisible(false); // Hide the current value label
                        Value.setPromptText("Purchase Cost"); // Set prompt text for purchase amount input

                        // Event handler for the "Enter" button in purchase mode
                        send.setOnAction(button -> {
                            double purchaseAmount = Double.parseDouble(Value.getText()); // Get purchase amount
                            
                            // Check if purchase amount is at least $50
                            if (purchaseAmount < 50) {
                                errorMessage2.setText("The minimum purchase should be $50.00!"); // Warning message if amount is less than $50
                                return; // Exit the method to prevent further processing
                            }
                            
                            double totalCost = purchaseAmount + customer.getLevel().getFee(); // Calculate total cost including fee

                            // Check if total cost is less than or equal to balance
                            if (totalCost <= customer.getBalance()) {
                                customer.onlinePurchase(purchaseAmount); // Process purchase
                                footer.setText("Balance: " + customer.getBalance() + "\nLevel: " + customer.getLevel().getLevelStr()); // Update footer with new balance and level
                            } else {
                                errorMessage2.setText("Insufficient funds for the purchase."); // Display error message if insufficient funds
                            }
                        });
                    });

                    // Event handler for Logout button
                    logout.setOnAction(e -> {
                        primaryStage.setScene(loginScreen); // Switch back to the login screen
                        customer.logout(); // Logout the customer
                    });
                } else {
                    errorMessage.setText("Password or username is incorrect!"); // Display error message for incorrect username or password
                }
            }
        });

        primaryStage.setOnCloseRequest(event -> {
        // Add custom behavior here, e.g., confirmation dialog
        System.out.println("Window close requested");
        
        // To prevent the window from closing based on some condition,
        // you can call event.consume(); otherwise, allow it to close by default.
    });
        
        // Set the initial scene to the login screen
        primaryStage.setScene(loginScreen);
        primaryStage.show(); // Show the primary stage
    }

    public static void main(String[] args) {
        launch(args); // Launch the JavaFX application
    }
}
