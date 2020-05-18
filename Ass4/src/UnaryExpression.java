// ID 316044809

/**
 * The class Unary expression.
 * an abstract class of a single mathematical expression.
 * is used to convert the generalized form of the methods in BaseExpression to specific ones.
 */
public abstract class UnaryExpression extends BaseExpression {

    /**
     * Instantiates a new Unary expression.
     *
     * @param expression the expression
     */
    protected UnaryExpression(final Expression expression) {
        super(expression);
    }

    @Override
    protected double applyOperator(final double... nums) {
        return applyOperator(nums[0]);
    }

    @Override
    protected Expression simplifyRules(final Expression... exps) {
        return createNew(exps[0]);
    }

    @Override
    protected Expression differentiateLogic(final String var, final Expression... exps) {
        return differentiateLogic(exps[0], var);
    }

    @Override
    protected Expression createNew(final Expression... exps) {
        return createNew(exps[0]);
    }

    @Override
    protected String getString(Expression... exps) {
        return getOperator() + exps[0].toString();
    }

    /**
     * Create new expression.
     *
     * @param expression the second expression
     * @return the new expression
     */
    protected abstract Expression createNew(Expression expression);

    /**
     * Differentiate logic expression.
     *
     * @param expression the expression
     * @param var        the variable of which we differentiate upon.
     * @return the derivative expression
     */
    protected Expression differentiateLogic(Expression expression, String var) {
        return createNew(expression.differentiate(var));
    }

    /**
     * Apply operator double.
     *
     * @param num the number to apply the operator on
     * @return the value of the mathematical expression after the application of the operator
     */
    protected abstract double applyOperator(double num);
}
