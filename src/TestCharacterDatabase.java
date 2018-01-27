import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Unit test for the {@link CharacterDatabase} class.
 */
public class TestCharacterDatabase {

    /** Instances of objects to test */
    private SuperCharacter ironMan;
    private SuperCharacter hulk;
    private Character daredevil;
    private Character blackWidow;
    private CharacterDatabase database;
    private final String FILENAME = "character-database";

    /**
     * This method is run by JUnit before each test.
     * Create character objects to add into database.
     */
    @Before
    public void createDatabase() {
        ironMan = new SuperCharacter("Iron Man", "A genius billionaire", 9);
        ironMan.addPower("Iron Suit");
        ironMan.addPower("Genius");

        hulk = new SuperCharacter("Hulk", "The mighty and powerful", 11);
        hulk.addPower("Extreme Strength");
        hulk.addPower("Immortality");

        daredevil = new Character("Daredevil", "The devil of Hells Kitchen");

        blackWidow = new Character("Black Widow", "World class athlete gymnast acrobat aerialist");

        // Initialize database with filename
        database = new CharacterDatabase(FILENAME);

        // Saves the database to the file
        database.save();
    }

    /**
     * Test the correctness of the search method of Character Database.
     */
    @Test
    public void testSearchDatabase() {

        // Loads the database
        database.load();

        // Search on empty database
        assertThat("Searching the database did not returned " +
                "null for Spider man when searched on empty database" , database.search("Spider man"), is(equalTo(null)));

        // Search on database containing one character object
        database.add(ironMan);
        assertThat("Searching the database for Iron Man did not returned" +
                " cloned character object IronMan", database.search("Iron Man"), is(equalTo(ironMan.clone())));

        database.add(hulk);
        database.add(daredevil);
        database.add(blackWidow);
        database.add(ironMan);

        // Search on database with more than one character objects
        assertThat("Searching the database for Iron Man did not returned" +
                " cloned character object Daredevil", database.search("Daredevil"), is(equalTo(daredevil.clone())));

        assertThat("Searching the database did not returned " +
                "null for Wolverine when searched on populated database" , database.search("Wolverine"), is(equalTo(null)));


        // Save the database file
        database.save();

        database.remove("Daredevil");

        assertThat("Searching the database for Daredevil " +
                "did not returned null after removal", database.search("Daredevil"), is(equalTo(null)));

    }

}
