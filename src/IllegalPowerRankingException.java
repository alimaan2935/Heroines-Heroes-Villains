/**
 * An exception class to handle Illegal Power Ranking.
 * This class extends IllegalArgumentException class.
 *
 * @author Ali Nawaz Maan <a.maan@uqconnect.edu.au>
 */
public class IllegalPowerRankingException extends IllegalArgumentException {

    /**
     * Empty constructor for exception class
     */
    public IllegalPowerRankingException() {
        super();
    }

    /**
     * Constructor for exception class to display passed message.
     * @param message String to display when exception is thrown.
     */
    public IllegalPowerRankingException(String message) {
        super(message);
    }
}
