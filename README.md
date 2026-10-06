# Bank Account Management System

A JavaFX-based Bank Account Management System designed and implemented using object-oriented software engineering principles. The project provides separate functionality for customers and managers while supporting account transactions, customer management, and dynamic account levels.

The system was developed as part of COE528 and applies requirements analysis, UML modeling, object-oriented design, and the State design pattern.

## Features

### Customer

Customers can:

- Log in to their account
- View and manage their account balance
- Deposit money
- Withdraw money
- Make online purchases
- Log out securely

The system validates transactions and prevents withdrawals when sufficient funds are not available.

### Manager

Managers can:

- Log in with administrative access
- Add new customers
- Delete existing customers
- Create and manage customer accounts
- Access customer management functionality

## Account Levels

Customer accounts are divided into three levels:

- **Silver**
- **Gold**
- **Platinum**

A customer's level is determined dynamically based on their account balance.

Each level defines its own behavior, including the transaction fee applied to online purchases. When a customer's balance changes, the system can transition the account to the appropriate level.

## State Design Pattern

The project implements the **State design pattern** to manage customer account levels.

The `Customer` class acts as the context and maintains a reference to an abstract `Level` state.

Three concrete states implement the different account levels:

- `Silver`
- `Gold`
- `Platinum`

Each state defines level-specific behavior such as:

- Account level identification
- Transaction fees
- Level transitions

This design allows customer behavior to change dynamically when the account balance causes the customer's level to change.

## System Design

The system was designed using UML before implementation.

The design includes:

- Use Case Diagram
- Use Case Descriptions
- UML Class Diagram
- Object-oriented relationships
- State design pattern

The UML model defines the interactions between managers, customers, accounts, and account levels.

## Core Classes

### `Customer`

Represents a bank customer and manages:

- Account balance
- Login status
- Deposits
- Withdrawals
- Online purchases
- Customer account level

The class also includes an abstraction function and representation invariant to maintain a valid object state.

### `User`

Stores common user information such as:

- Username
- Password
- Role
- Balance

### `Manager`

Extends the user functionality with administrative operations for managing customers.

### `Level`

Abstract representation of a customer's account level.

It defines the common behavior implemented by the concrete account states.

### `Silver`, `Gold`, and `Platinum`

Concrete implementations of `Level` that define the behavior and transaction fees associated with each customer level.

### `Bank`

Coordinates the banking application and manages the customer and manager interfaces.

## Transaction Handling

For operations such as withdrawals, the system:

1. Verifies that the customer is authenticated.
2. Accepts the requested transaction amount.
3. Validates the amount and current balance.
4. Performs the transaction when sufficient funds are available.
5. Updates the customer's balance.
6. Updates the customer's account level when required.
7. Rejects invalid transactions and provides appropriate feedback.

## Object-Oriented Design

The project applies several object-oriented software engineering concepts:

- Encapsulation
- Inheritance
- Abstraction
- Polymorphism
- Aggregation
- Composition
- Representation invariants
- Abstraction functions
- Design patterns

These concepts are used to separate responsibilities and maintain a modular system architecture.

## Technologies

- Java
- JavaFX
- Object-Oriented Programming
- UML
- State Design Pattern
- Software Requirements Analysis
- Software Design and Implementation

## Course

**COE528 – Object-Oriented Engineering Analysis and Design**

**Final Project – Bank Account Management System**

## Author

**Akarshan R Singh**
