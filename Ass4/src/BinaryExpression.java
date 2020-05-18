// ID 316044809


/**
 * The class Binary expression.
 * <p>
 * an abstract class of a mathematical expression between 2 expressions
 * <p>
 * is used to convert the generalized form of the methods in BaseExpression to specific ones.
 */
public abstract class BinaryExpression extends BaseExpression {

    /**
     * Instantiates a new Binary expression.
     *
     * @param expression1 the first expression
     * @param expression2 the second expression
     */
    protected BinaryExpression(final Expression expression1, final Expression expression2) {
        super(expression1, expression2);
    }

    @Override
    protected double applyOperator(final double... nums) {
        return applyOperator(nums[0], nums[1]);
    }

    @Override
    protected Expression simplifyRules(final Expression... exps) {
        return simplifyRules(exps[0], exps[1]);
    }

    @Override
    protected Expression differentiateLogic(final String var, final Expression... exps) {
        return differentiateLogic(exps[0], exps[1], var);
    }

    @Override
    protected Expression createNew(final Expression... exps) {
        return createNew(exps[0], exps[1]);
    }

    @Override
    protected String getString(Expression... exps) {
        return "(" + exps[0] + getOperator() + exps[1];
    }

    /**
     * Differentiate logic expression.
     *
     * @param exp1 the first expression component
     * @param exp2 the second expression component
     * @param var  the variable of which we differentiate upon.
     * @return the derivative expression
     */
    protected Expression differentiateLogic(Expression exp1, Expression exp2, String var) {
        return createNew(exp1.differentiate(var), exp2.differentiate(var));
    }

    /**
     * Simplify rules expression.
     *
     * @param exp1 the first simplified component
     * @param exp2 the second simplified component
     * @return the simplified expression
     */
    protected Expression simplifyRules(Expression exp1, Expression exp2) {
        return createNew(exp1, exp2);
    }

    /**
     * Create a new BinaryExpression.
     *
     * @param exp1 the first expression component
     * @param exp2 the second expression component
     * @return the new expression
     */
    protected abstract Expression createNew(Expression exp1, Expression exp2);

    /**
     * Apply operator double.
     *
     * @param num1 the first expression component evaluated
     * @param num2 the second expression component evaluated
     * @return the value of the mathematical expression after the application of the operator
     */
    protected abstract double applyOperator(double num1, double num2);
}
