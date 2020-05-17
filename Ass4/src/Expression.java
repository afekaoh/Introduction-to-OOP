// ID 316044809

import java.util.List;
import java.util.Map;

/**
 * The interface Expression.
 * representing a mathematical Expression
 */
public interface Expression {
    /**
     * Evaluate the expression using the variable values provided in the assignment.
     *
     * @param assignment a mapping of the assignment from strings to doubles
     * @return the expression evaluated
     * @throws Exception if the expression contains a variable which is not in the assignment
     *                   or an arithmetic exceptions if a not valid operation happened(divide by zero, log domain etc.)
     */
    double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * Evaluate the expression and return the result.
     * an exception is thrown.
     *
     * @return the expression evaluated
     * @throws Exception if the expression contains a variable which is not in the assignment
     *                   or an arithmetic exceptions if a not valid operation happened(divide by zero, log domain etc.)
     */
    double evaluate() throws Exception;

    /**
     * Get variables.
     *
     * @return Returns an unmodifiable list of the variables in the expression.
     */
    List<String> getVariables();

    /**
     * Returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the
     * current expression).
     *
     * @param var        the variable
     * @param expression the expression to put the variable in
     * @return the expression after the assignment
     */
    Expression assign(String var, Expression expression);

    /**
     * Differentiate expression.
     *
     * @param var the variable of which we derived.
     * @return the expression tree resulting from differentiating
     * the current expression relative to variable `var`.
     */
    Expression differentiate(String var);

    /**
     * Simplify expression.
     *
     * @return a simplified version of the current expression.
     */
    Expression simplify();

    /**
     * To string string.
     *
     * @return Returns a nice string representation of the expression.
     */
    String toString();
}
