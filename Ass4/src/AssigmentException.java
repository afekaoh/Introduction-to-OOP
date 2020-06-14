// ID 316044809

/**
 * The Exception Assigment exception.
 * thrown if while trying to evaluate an expression, one of the variables was not assigned.
 */
public class AssigmentException extends Exception {
    /**
     * Instantiates a new Assigment exception.
     *
     * @param message the message
     */
    public AssigmentException(final String message) {
        super("the variable " + message + " is not assigned");
    }
}
