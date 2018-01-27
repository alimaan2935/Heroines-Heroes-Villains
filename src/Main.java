

public class Main {

    public static void main(String[] args) {

        final String FILENAME = "character-database";

        SuperCharacter ironMan = new SuperCharacter("Iron Man", "A genius billionaire", 9);
        ironMan.addPower("Iron Suit");
        ironMan.addPower("Genius");

        SuperCharacter hulk = new SuperCharacter("Hulk", "The mighty and powerful", 11);
        hulk.addPower("Extreme Strength");
        hulk.addPower("Immortality");

        Character daredevil = new Character("Daredevil", "The devil of Hells Kitchen");

        Character blackWidow = new Character("Black Widow", "World class athlete gymnast acrobat aerialist");

        // Initialize database with filename
        CharacterDatabase database = new CharacterDatabase(FILENAME);

        // Saves the database to the file
        database.load();

        System.out.println(database.search("Hulk"));
    }
}
