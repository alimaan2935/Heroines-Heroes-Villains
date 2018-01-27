import java.security.InvalidParameterException;
import java.util.HashSet;

/**
 * Class representing a super-powered character. Extends class Character.
 *
 * It throws InvalidParameterException whenever the passed
 * String does not consist of alphabets only. This behavior is suitable
 * because SuperCharacter object should only be used to store data and not handling
 * exceptions. Exceptions must be handles at the time of calling the methods of
 * the SuperCharacter object because there is not a suitable way to handle exceptions
 * inside the object class and inform the user of operation failure except a print statement.
 *
 * @author Ali Nawaz Maan <a.maan@uqconnect.edu.au>
 */
public class SuperCharacter extends Character {

    protected int powerRanking;
    protected final int UNRANKABLE = 11;
    protected final int[] powerRange = {1, 11};



    /**
     * A constructor for SuperCharacter class
     * @param name String name of the super character
     * @param description String description of the super character
     * @require name, description must be string of alphabets
     * @throws InvalidParameterException if n and des strings have other characters than alphabets
     */
    public SuperCharacter(String name, String description) throws InvalidParameterException {
        super(name, description);
    }

    /**
     * Another constructor for SuperCharacter class
     * @param name String name of the super character
     * @param desc String description of the super character
     * @param powerRanking int power ranking of the super character in
     *                      range (1-10), 11 if unrankable
     * @require name, description Strings must consist of alphabets
     * @require powerRanking must be integer value
     * @throws InvalidParameterException if n and des strings have other characters than alphabets
     * @throws IllegalPowerRankingException if power ranking value is out of range or invalid
     */
    public SuperCharacter (String name, String desc, int powerRanking)
            throws IllegalPowerRankingException, InvalidParameterException {
        super(name, desc); // throws InvalidParameterException if strings are other than alphabets only
        if (powerRanking  >= powerRange[0] && powerRanking <= powerRange[1]) {
            this.powerRanking = powerRanking;
        }else {
            throw new IllegalPowerRankingException(" Power ranking should be in the range of 1-10 and 11 if unrankable");
        }
    }

    /**
     * @return string representation for the SuperCharacter object
     */
    public String toString() {
        return super.toString() + " | Power Ranking = " +  (isUnrankable() ? "Unrankable" : getPowerRanking());
    }

    /**
     * @return int power ranking of super character
     * @throws NullPointerException if powerRanking is not initialized
     */
    public int getPowerRanking() throws NullPointerException {
        if (!(powerRanking == 0)) {
            return powerRanking;
        }
        throw new NullPointerException("Super character is not initialized with power ranking.");
    }

    /**
     * Set power ranking of the super character
     * @param powerRanking int power ranking between the range of {@code powerRange}, 11 if unrankable
     * @return boolean true if setting the power ranking is successful
     * @require power ranking must be within the range of 1-10 and 11 if unrankable
     * @throws IllegalPowerRankingException if power ranking is not within @require range
     */
    public boolean setPowerRanking(int powerRanking) throws IllegalPowerRankingException {
        if (powerRange[0] <= powerRanking && powerRanking <= powerRange[1]) {
            this.powerRanking = powerRanking;
            return true;
        }
        throw new IllegalPowerRankingException("Power ranking value is invalid. Please set the power ranking " +
                            "again in the limits of 1 - 10 or 11 for unrankable");

    }

    /**
     * Add super power of a super character
     * @param power String super power trait
     * @return boolean true if adding power is successful,
     *         false otherwise if power already exists in the trait set
     * @require power string must consist of alphabets only
     * @throws InvalidParameterException if power string has other characters than alphabets
     */
    public boolean addPower (String power) throws InvalidParameterException {
        return super.addTrait(power);
    }

    /**
     * Remove method for removing a super power from the super character traits
     * @param power String super power trait to be removed
     * @return boolean true if removal is successful, false if power does not exist or otherwise
     */
    public boolean removePower (String power) {
        return super.removeTrait(power);
    }


    /**
     * Check if a super character is unrankable
     * @return boolean true if super character's power ranking is equal to UNRANKABLE,
     *          false if power ranking is not equal to unrankable or it is not initialized yet
     */
    public boolean isUnrankable() {
        if (powerRanking == 0) { // if power ranking is not initialized
            return false;
        }
        return (powerRanking == UNRANKABLE);
    }

    /**
     * @return a deep copy clone of SuperCharacter object
     */
    @Override
    public Object clone() {
        SuperCharacter clone = new SuperCharacter(super.name, super.description, powerRanking);
        for (String t : super.traits) {
            clone.traits.add(t);
        }
        return clone;
    }
}
