// ID 316044809


/**
 * The class Binary expression.
 * an abstract class of a mathematical expression between 2 expressions
 */
public abstract class BinaryExpression extends BaseExpression {

    /**
     * Instantiates a new Binary expression.
     *
     * @param expression1 the first expression
     * @param expression2 the second expression
     */
    public BinaryExpression(final Expression expression1, final Expression expression2) {
        super(expression1, expression2);
    }

    @Override
    public Expression createNew(final Expression... exps) {
        return createNew(exps[0], exps[1]);
    }

    /**
     * Create a new BinaryExpression.
     *
     * @param exp1 the first expression
     * @param exp2 the second expression
     * @return the new expression
     */
    protected abstract Expression createNew(Expression exp1, Expression exp2);

    @Override
    protected Expression differentiateLogic(final String var, final Expression... exps) {
        return differentiateLogic(exps[0], exps[1], var);
    }

    /**
     * Differentiate logic expression.
     *
     * @param exp1 the first expression
     * @param exp2 the second expression
     * @param var  the variable of which we differentiate upon.
     * @return the derivative expression
     */
    protected abstract Expression differentiateLogic(Expression exp1, Expression exp2, String var);

    @Override
    protected Expression simplifyRules(final Expression... exps) {
        final Expression exp1 = exps[0];
        final Expression exp2 = exps[1];
        try {
            return new Num(createNew(exp1, exp2).evaluate());
        } catch (AssigmentException e) {
            return simplifyRules(exp1, exp2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected double applyOperator(final double... nums) {
        final double num1 = nums[0];
        final double num2 = nums[1];
        return applyOperator(num1, num2);
    }

    /**
     * Apply operator double.
     *
     * @param num1 the first number to apply the operator on
     * @param num2 the second number to apply the operator on
     * @return the value of the mathematical expression after the application of the operator
     */
    protected abstract double applyOperator(double num1, double num2);

    @Override
    public String getString(Expression... exps) {
        return "(" + exps[0].toString() + getOperator() + exps[1];
    }

    /**
     * Simplify rules expression.
     *
     * @param exp1 the first expression
     * @param exp2 the second expression
     * @return the simplified expression
     */
    protected abstract Expression simplifyRules(Expression exp1, Expression exp2);
}
