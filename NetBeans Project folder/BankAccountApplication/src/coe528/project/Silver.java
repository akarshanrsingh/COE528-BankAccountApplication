package coe528.project;

/**
 * Overview: Represents the Silver level of a customer's account. This level has a fee and a specific level string.
 * This class extends the abstract Level class and provides specific implementations for the Silver level 's fee and
 * level string.
 * 
 * @author Akarshan R Singh
 */
public class Silver extends Level {  // Inherits from the Level class to define Silver-specific behavior

    /**
     * Initializes the Silver level with a specific fee and level string.
     */
    public Silver() {
        levelStr = "Silver"; // Set the level name to Silver
        fee = 20; // Set the fee for Silver level
    }

    /**
     * Returns the name of the Silver level.
     * 
     * @return Level name as a string.
     */
    @Override
    public String getLevelStr() {
        return levelStr; // Return the name of the level
    }

    /**
     * Returns the fee associated with the Silver level.
     * 
     * @return Fee amount.
     */
    @Override
    public int getFee() {
        return fee; // Return the fee for Silver level
    }

    /**
     * Sets the customer's level to Silver.
     * 
     * @param customer The customer to be updated.
     */
    @Override
    public void setLevel(Customer customer) {
        customer.level = new Silver(); // Update customer's level to Silver
    }
}