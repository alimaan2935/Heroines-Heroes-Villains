import java.io.*;
import java.util.HashSet;

/**
 * A Character Database class to store a collection of character objects in a file.
 * Have to save the file after creating the object
 *
 *@author Ali Nawaz Maan <a.maan@uqconnect.edu.au>
 */
public class CharacterDatabase {

    protected HashSet<Character> characters;
    protected String filename;


    /**
     * Creates a CharacterDatabase Object with given filename.
     * The database will be saved to this file.
     * The constructor initializes an empty HashSet to store Character objects.
     * Have to save the file after creating the object
     *
     * @param filename String where database is stored
     */
    public CharacterDatabase(String filename) {
        this.filename = filename;
        characters = new HashSet<>();
    }


    /**
     * Loads the database from file into the program
     */
    public void load(){

        try {
            FileInputStream f = new FileInputStream(filename);
            ObjectInputStream charIn = new ObjectInputStream(f);

            characters = (HashSet<Character>)charIn.readObject();

            charIn.close();

        }catch (FileNotFoundException f) {
            f.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException cn) {
            cn.printStackTrace();
        }

    }

    /**
     * Saves the database to the file.
     * Filename was provided in the constructor.
     */
    public void save() {

        try {
            FileOutputStream f = new FileOutputStream(filename);
            ObjectOutputStream charOut = new ObjectOutputStream(f);

            charOut.writeObject(characters);

            charOut.close();

        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Adds character objects to the HashSet database.
     * Caller has to save the file after adding characters to the database.
     *
     * @param character Character object to add to the database
     */
    public void add(Character character) {
        characters.add(character);
    }

    /**
     * Removes a character object from the database.
     * Caller has to save the file after removing characters from the database.
     *
     * @param name Name of character to be removed from database.
     */
    public void remove(String name) {
        characters.remove(search(name));
    }

    /**
     * Searches the database for a character object with given name.
     * Returns the object clone if found.
     * The search method loops around the HashSet object of type Characters and
     * try to match the searched name with each character name in the database.
     * If the searched name matches with any of the character name, returns the clone of character object.
     * @param name String name of the character to search
     * @return Character object if exist in database - null otherwise
     */
    public Character search(String name) {

        for (Character c : characters) {
            if (name.equals(c.getName())) {
                return (Character) c.clone();
            }
        }
        return null;
    }
}
