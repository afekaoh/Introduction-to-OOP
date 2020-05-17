// ID 316044809
package exceptions;


/**
 * The class Log exception.
 */
public class LogException extends ArithmeticException {
    /**
     * Instantiates a new Log exception.
     *
     * @param s the s
     */
    public LogException(final String s) {
        super("cannot compute log " + s);
    }
}
