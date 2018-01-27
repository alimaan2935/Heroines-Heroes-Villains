import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import java.security.InvalidParameterException;


public class TestSuperCharacter {
    private SuperCharacter ironMan;
    private SuperCharacter daredevil;
    private SuperCharacter hulk;

    @Before
    public void createSuperCharacters() {

        ironMan = new SuperCharacter("Iron Man", "A genius billionaire");

        daredevil = new SuperCharacter("Daredevil", "The devil of hells kitchen", 7);

        daredevil.addPower("Resilience");
        daredevil.addPower("Sensory Vision");

        hulk = new SuperCharacter("Hulk", "The mighty and powerful");
        hulk.setPowerRanking(11);
        hulk.addPower("Super Strength");
        hulk.addPower("Immortality");
    }

    @Test (expected = IllegalArgumentException.class)
    public void invalidConstruction1() {
        ironMan = new SuperCharacter("Iron Man", "A genius billionaire", 12);
    }

    @Test (expected = IllegalArgumentException.class)
    public void invalidConstruction2() {
        ironMan = new SuperCharacter("Iron Man", "A genius billionaire", -1);
    }

    @Test (expected = InvalidParameterException.class)
    public void invalidConstruction3() {
        ironMan = new SuperCharacter(null, "A genius billionaire", 5);
    }

    @Test (expected = InvalidParameterException.class)
    public void invalidConstruction4() {
        ironMan = new SuperCharacter("Iron Man", null, 5);
    }

    @Test (expected = InvalidParameterException.class)
    public void invalidConstruction5() {
        ironMan = new SuperCharacter("Iron Man1", "A genius billionaire", 11);
    }

    @Test (expected = InvalidParameterException.class)
    public void invalidConstruction6() {
        ironMan = new SuperCharacter("Iron Man", "A genius billionaire2", 2);
    }

    @Test (expected = InvalidParameterException.class)
    public void invalidConstruction7() {
        ironMan = new SuperCharacter("IronMan>", "A-genius-billionaire", 2);
    }

    @Test (expected = InvalidParameterException.class)
    public void invalidPowerRanking1() {
        assertThat("Iron Man's set power ranking was valid", ironMan.setPowerRanking(12), is(equalTo(false)));

    }

    @Test (expected = InvalidParameterException.class)
    public void invalidPowerRanking2() {
        assertThat("Daredevil's set power ranking was valid", daredevil.setPowerRanking(0), is(equalTo(false)));

    }

    @Test (expected = InvalidParameterException.class)
    public void invalidPowerRanking3() {
        assertThat("Hulk's set power ranking was valid", hulk.setPowerRanking(-1), is(equalTo(false)));

    }

    @Test
    public void nameAndDescription() {
        String notDaredevil = "Not Daredevil";
        daredevil.setName(notDaredevil);
        assertThat("Daredevil's name has not been changed to (Not Daredevil)", daredevil.getName(), is(equalTo(notDaredevil)));

        String ironManDescription = "Spoiled Child";
        ironMan.setDescription(ironManDescription);
        assertThat("Iron Man's description has not been changed to (Spoiled Child)", ironMan.getDescription(), is(equalTo(ironManDescription)));

    }

    @Test
    public void powerRanking() {
        assertThat("Power ranking of Daredevil is not 7", daredevil.getPowerRanking(), is(equalTo(7)));
        assertThat("Daredevil is unrankable", daredevil.isUnrankable(), is(equalTo(false)));

        assertThat("Power ranking of hulk is not 11", hulk.getPowerRanking(), is(equalTo(11)));
        assertThat("Hulk is not unrankable", hulk.isUnrankable(), is(equalTo(true)));
    }

    @Test
    public void setPowerRanking() {

        assertThat("Iron Man's power ranking was not set", ironMan.setPowerRanking(8), is(equalTo(true)));
        assertThat("Iron Man's set power ranking was not 8", ironMan.getPowerRanking(), is(equalTo(8)));

        assertThat("Daredevil's power ranking was not set", daredevil.setPowerRanking(6), is(equalTo(true)));
        assertThat("Daredevil's set power ranking was not 6", daredevil.getPowerRanking(), is(equalTo(6)));

    }

    @Test
    public void superPowers() {

        assertThat("Iron Man's superpower was not added", ironMan.addPower("Iron Suit"), is(equalTo(true)));
        assertThat("Iron Man's superpower (Iron Suit) was not into the trait set", ironMan.hasTrait("Iron Suit"), is(equalTo(true)));

        assertThat("Daredevil's superpower was not added", daredevil.addPower("Agility"), is(equalTo(true)));
        assertThat("Daredevil's superpower (Agility) was not into the trait set", daredevil.hasTrait("Agility"), is(equalTo(true)));

        assertThat("Iron Man's superpower was not removed", ironMan.removePower("Iron Suit"), is(equalTo(true)));
        assertThat("Iron Man's superpower (Iron Suit) was into the trait set", ironMan.hasTrait("Iron Suit"), is(equalTo(false)));

        assertThat("Daredevil's superpower was not removed", daredevil.removePower("Agility"), is(equalTo(true)));
        assertThat("Daredevil's superpower (Agility) was into the trait set", daredevil.hasTrait("Agility"), is(equalTo(false)));

    }

    @Test (expected = InvalidParameterException.class)
    public void invalidSuperPowers1() {
        daredevil.addPower("Strong1");
    }

    @Test (expected = InvalidParameterException.class)
    public void invalidSuperPowers2() {
        ironMan.addPower("Iron-Suit");
    }

    @Test (expected = InvalidParameterException.class)
    public void invalidSuperPowers3() {
        hulk.addPower("Greenish 124");
    }

    @Test
    public void equalityTest() {

        SuperCharacter s1 = new SuperCharacter("Thor", "Immortal God", 9);
        SuperCharacter s2 = new SuperCharacter("Thor", "Immortal God", 10);
        SuperCharacter s3 = new SuperCharacter("Loki", "Immortal God", 10);
        Character s4 = new Character("Loki", "Immortal God");

        assertThat("s1 was not equal to s2", s1.equals(s2), is(equalTo(true)));
        assertThat("s2 was equal to s3", s2.equals(s3), is(equalTo(false)));
        assertThat("s3 was not equal to s4", s3.equals(s4), is(equalTo(true)));

    }
}
