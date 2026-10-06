package coe528.project;

/**
 * Overview: Represents the Platinum level of a customer's account. This level has a fee and a specific level string.
 * This class extends the abstract Level class and provides specific implementations for the Platinum level 's fee and
 * level string.
 * 
 * @author Akarshan R Singh
 */
public class Platinum extends Level { // Inherits from the Level class to define Platinum-specific behavior

    /**
     * Initializes the Platinum level with a specific fee and level string.
     */
    public Platinum() {
        levelStr = "Platinum"; // Set the level name to Platinum
        fee = 0; // Set the fee for Platinum level
    }

    /**
     * Returns the name of the Platinum level.
     * 
     * @return Level name as a string.
     */
    @Override
    public String getLevelStr() {
        return levelStr; // Return the name of the level
    }

    /**
     * Returns the fee associated with the Platinum level.
     * 
     * @return Fee amount.
     */
    @Override
    public int getFee() {
        return fee; // Return the fee for Platinum level
    }

    /**
     * Sets the customer's level to Platinum.
     * 
     * @param customer The customer to be updated.
     */
    @Override
    public void setLevel(Customer customer) {
        customer.level = new Platinum(); // Update customer's level to Platinum
    }
}