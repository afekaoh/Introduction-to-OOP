// ID 316044809
package exceptions;

/**
 * The class Log base exception.
 */
public class LogBaseException extends LogException {
    /**
     * Instantiates a new Log base exception.
     *
     * @param s the s
     */
    public LogBaseException(final double s) {
        super("in a base " + s + ": the base of the log must be grater then 0 and different then 1");
    }
}
