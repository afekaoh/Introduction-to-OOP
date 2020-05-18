// ID 316044809
package exceptions;


/**
 * The class Log power exception.
 */
public class LogPowerException extends LogException {
    /**
     * Instantiates a new Log exception.
     *
     * @param s the s
     */
    public LogPowerException(final double s) {
        super("of " + s + ": the power of the logarithm must be grater then 0");
    }
}
