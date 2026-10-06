package coe528.project;

/**
 * Overview: Represents the Gold level of a customer's account. This level has a fee and a specific level string.
 *  This class extends the abstract Level class and provides specific implementations for the Gold level 's fee and
 * level string.
 * 
 * @author Akarshan R Singh
 */
public class Gold extends Level { // Inherits from the Level class to define Gold-specific behavior

    /**
     * Initializes the Gold level with a specific fee and level string.
     */
    public Gold() {
        levelStr = "Gold"; // Set the level name to Gold
        fee = 10; // Set the fee for Gold level
    }

    /**
     * Returns the name of the Gold level.
     * 
     * @return Level name as a string.
     */
    @Override
    public String getLevelStr() {
        return levelStr; // Return the name of the level
    }

    /**
     * Returns the fee associated with the Gold level.
     * 
     * @return Fee amount.
     */
    @Override
    public int getFee() {
        return fee; // Return the fee for Gold level
    }

    /**
     * Sets the customer's level to Gold.
     * 
     * @param customer The customer to be updated.
     */
    @Override
    public void setLevel(Customer customer) {
        customer.level = new Gold(); // Update customer's level to Gold
    }
}