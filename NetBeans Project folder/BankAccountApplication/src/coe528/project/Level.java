package coe528.project;

/**
 * Overview: Abstract class representing a level in the customer account system. 
 * Defines the structure for different levels (Silver, Gold, Platinum) with specific fees and level names.
 * 
 * @author Akarshan R Singh
 */
public abstract class Level {
    // The name of the level
    protected String levelStr;
    
    // The fee associated with the level
    protected int fee;

    /**
     * Returns the name of the level.
     * 
     * @return Level name as a string.
     */
    public abstract String getLevelStr();
    
    /**
     * Returns the fee associated with the level.
     * 
     * @return Fee amount.
     */
    public abstract int getFee();
    
    /**
     * Sets the level of the specified customer.
     * 
     * @param customer The customer whose level is to be updated.
     */
    public abstract void setLevel(Customer customer);
}