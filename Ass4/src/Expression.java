// ID 316044809

import java.util.List;
import java.util.Map;

/**
 * The interface Expression.
 */
public interface Expression {
    /**
     * Evaluate the expression using the variable values provided
     * in the assignment, and return the result.  If the expression
     * contains a variable which is not in the assignment, an exception
     * is thrown.
     *
     * @param assignment the assignment
     * @return the expression evaluated
     * @throws Exception the exception
     */
    double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * Evaluate the expression and return the result.
     * If the expression contains a variable which is not in the assignment,
     * an exception is thrown.
     *
     * @return the expression evaluated
     * @throws Exception the exception
     */
    double evaluate() throws Exception;


    /**
     * Get variables.
     *
     * @return Returns a list of the variables in the expression.
     */
    List<String> getVariables();

    /**
     * To string string.
     *
     * @return Returns a nice string representation of the expression.
     */
    String toString();


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
     * @param var the var
     * @return the expression tree resulting from differentiating
     * * the current expression relative to variable `var`.
     */
    Expression differentiate(String var);

    /**
     * Simplify expression.
     *
     * @return a simplified version of the current expression.
     */
    Expression simplify();

    /**
     * Equals boolean.
     *
     * @param e the e
     * @return the boolean
     */
    boolean equals(Expression e);

    /**
     * Is var boolean.
     *
     * @return true if the Expression is Var
     */
    boolean isVar();

    /**
     * Is num boolean.
     *
     * @return true if the Expression is Num
     */
    boolean isNum();

    /**
     * Is neg boolean.
     *
     * @return true if the Expression is Neg
     */
    boolean isNeg();
}
