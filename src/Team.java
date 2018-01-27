import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A very simple class representing a Set of Characters
 *
 * @author Ali Nawaz Maan <a.maan@uqconnect.edu.au>
 */
public class Team {

    private String teamName;
    private HashSet<Character> members = new HashSet<>();

    /**
     * A constructor of Team class
     * @param name String name of the team
     * @require name string must consist of alphabets only
     * @throws InvalidParameterException if name string has other characters than alphabets
     */
    public Team(String name) throws InvalidParameterException {
        if (isValidString(name)) { // test string validity
            teamName = name;
        }else {
            throw new InvalidParameterException("Name string must consist of alphabets only");
        }
    }

    /**
     * Add team member in the team
     * @param character Character object to add in the team
     * @return boolean true if adding the team member to the set is successful, false if it already exists
     */
    public boolean addMember (Character character) {
        return members.add(character);
    }

    /**
     * Remove a team member from the team
     * @param character Character object to remove from the team
     * @return boolean true if removal is successful, false otherwise
     */
    public boolean removeMember (Character character) {
        return members.remove(character);
    }

    /**
     * Get power ranking of the team - only super powered characters are counted for power ranking
     * Only rank-able characters are included in the team power ranking
     * @return int power ranking of the team, 0 if all of the team members are not initialized with power ranking.
     */
    public int powerRanking () {

        int total = 0;
        Iterator i = members.iterator();
        while (i.hasNext()) {
            Object character = i.next();
            if (character instanceof SuperCharacter) {
                if (!((SuperCharacter) character).isUnrankable()) {
                    try {
                        total += ((SuperCharacter) character).getPowerRanking();
                    }catch (NullPointerException e) {
                        System.out.println("Super character is not initialized with power ranking.");
                    }
                }
            }
        }
        return total;
    }

    /**
     * @return string representation of the team
     */
    public String toString() {
        Iterator i = members.iterator();

        StringBuilder stringRepresentation = new StringBuilder();
        stringRepresentation.append("Team name: " +  teamName);
        stringRepresentation.append("\n Team Power Ranking: " + powerRanking());

        while (i.hasNext()) {
            stringRepresentation.append("\n" + ((Character) i.next()).getName());
        }

        return stringRepresentation.toString();
    }

    /**
     * Check if string consists of alphabets and spaces only
     * @param stringToTest String to test the validity
     * @return boolean true if String consists of alphabets and spaces only, false otherwise
     */
    protected boolean isValidString (String stringToTest) {
        Pattern pattern = Pattern.compile("^[ A-Za-z]+$");
        Matcher matcher = pattern.matcher(stringToTest);
        return matcher.matches();
    }
}
