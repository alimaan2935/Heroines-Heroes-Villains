import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;


public class TestTeam {
    private SuperCharacter ironMan;
    private SuperCharacter hulk;
    private Character daredevil;
    private Team defenders;

    @Before
    public void createSuperCharacters() {
        ironMan = new SuperCharacter("Iron Man", "A genius billionaire", 9);
        ironMan.addPower("Iron Suit");
        ironMan.addPower("Genius");

        hulk = new SuperCharacter("Hulk", "The mighty and powerful", 11);
        hulk.addPower("Extreme Strength");
        hulk.addPower("Immortality");

        daredevil = new Character("Daredevil", "The devil of Hells Kitchen");

        defenders = new Team("Defenders");
        defenders.addMember(ironMan);
        defenders.addMember(daredevil);
        defenders.addMember(hulk);
    }

    @Test
    public void teamPowerRanking() {
        assertThat("Defenders team power ranking is not equal to 9", defenders.powerRanking(), is(equalTo(9)));

        hulk.setPowerRanking(10);
        assertThat("Defenders team power ranking is not equal to 19", defenders.powerRanking(), is(equalTo(19)));

        SuperCharacter invalidTest = new SuperCharacter("Name", "Description");
        defenders.addMember(invalidTest);
        assertThat("Defenders team power ranking is not equal to 19", defenders.powerRanking(), is(equalTo(19)));

    }
}
