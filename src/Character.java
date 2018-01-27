import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A generic class representing a (fictional) character
 * It throws InvalidParameterException whenever the passed
 * String does not consist of alphabets only. This behavior is suitable
 * because Character object should only be used to store data and not handling
 * exceptions. Exceptions must be handles at the time of calling the methods of
 * the Character object because there is not a suitable way to handle exceptions
 * inside the object class and inform the user of operation failure except a print statement.
 *
 * @author Ali Nawaz Maan <a.maan@uqconnect.edu.au>
 */
public class Character implements Serializable {

    protected String name, description;
    protected HashSet<String> traits = new HashSet<>();


    /**
     * A constructor for Character class
     * @param name String name of the character
     * @param description String description of the character
     * @require n, des must be string of alphabets
     * @throws InvalidParameterException if n and des strings have other characters than alphabets
     */
    public Character(String name, String description) throws InvalidParameterException {
        if (isValidString(name) && isValidString(description)) { // check if strings consist of alphabets only
            this.name = name;
            this.description = description;
        }else {
            throw new InvalidParameterException("Name and Description strings must consist of alphabets only");
        }
    }

    /**
     * @return String representation for the Character object
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(" - ");
        sb.append(description).append(" - ").append(" | having traits : ");

        for (Object trait : traits) {
            sb.append(" --> ").append(trait);
        }
        return sb.toString() ;
    }


    /**
     * Determine if 2 characters are equal
     * @param other Object which is to be compared
     * @return boolean true if the objects are equal, false otherwise
     */

    public boolean equals(Object other) {
        try {
            Character c = (Character) other;
            return name.equals(c.getName());
        }
        catch (ClassCastException cce) {
            return false;
        }
    }

    public int hashCode( ) {
        return name.hashCode( );
    }

    /**
     * Add traits to the trait set of the character
     * @param charTrait String character trait
     * @return boolean True if successfully added, false if the trait already exists.
     * @require charTrait String must consist of alphabets only
     * @throws InvalidParameterException if character trait string has other characters than alphabets
     */
    public boolean addTrait (String charTrait) throws InvalidParameterException {
        if (isValidString(charTrait)) {
            return traits.add(charTrait);
        }else {
            throw new InvalidParameterException("Character Trait string must consist of alphabets only");
        }
    }

    /**
     * Remove a certain trait from Character
     * @param charTrait String character trait
     * @return boolean true if removal is successful, false if trait does not exist or otherwise
     */
    public boolean removeTrait (String charTrait) {
        return traits.remove(charTrait);
    }

    /**
     * @return String name of the character
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the character
     * @param name String name of the character
     * @require name must be string of alphabets
     * @throws InvalidParameterException if name string has other characters than alphabets
     */
    public void setName(String name) throws InvalidParameterException {
        if (isValidString(name)) {
            this.name = name;
        }else {
            throw new InvalidParameterException("Name string must consist of alphabets only");
        }
    }
    /**
     * @return String description of the character
     */
    public String getDescription() {
        return description;
    }
    /**
     * Set description of the character
     * @param description String description of the character
     * @require description must be string of alphabets
     * @throws InvalidParameterException if description string has other characters than alphabets
     */
    public void setDescription(String description) throws InvalidParameterException {
        if (isValidString(description)) {
            this.description = description;
        }else {
            throw new InvalidParameterException("Description string must consist of alphabets only");
        }
    }

    /**
     * Check if a particular trait exist in the character's traits
     * @param trait String trait of a character
     * @return boolean true if trait exist in set of character traits, false otherwise
     */
    public boolean hasTrait (String trait) {
        if (traits.contains(trait)) {
            return true;
        }
        return false;
    }

    /**
     * Check if string consists of alphabets and spaces only
     * @param stringToTest String to test the validity
     * @return boolean true if String consists of alphabets and spaces only, false otherwise
     * @throws InvalidParameterException if string is null
     */
    protected boolean isValidString (String stringToTest) {
        try {
            Pattern pattern = Pattern.compile("^[ A-Za-z]+$"); // regex for string pattern matching
            Matcher matcher = pattern.matcher(stringToTest);
            return matcher.matches();
        }catch (NullPointerException e) {
            throw new InvalidParameterException("String cannot be null");
        }
    }

    /**
     * @return a deep copy clone
     */
    public Object clone() {
        Character clone = new Character(name, description);
        for (String t : traits) {
            clone.traits.add(t);
        }
        return clone;
    }

}
