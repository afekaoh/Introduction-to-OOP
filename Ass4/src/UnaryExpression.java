// ID 316044809


/**
 * The class Unary expression.
 */
public abstract class UnaryExpression extends BaseExpression {

    /**
     * Instantiates a new Unary expression.
     *
     * @param expression the expression
     */
    public UnaryExpression(final Expression expression) {
        super(expression);
    }

    @Override
    protected double applyOperator(final double... nums) {
        return applyOperator(nums[0]);
    }

    @Override
    public Expression createNew(final Expression... exps) {
        return createNew(exps[0]);
    }

    @Override
    protected Expression differentiateLogic(final String var, final Expression... exps) {
        return differentiateLogic(exps[0], var);
    }

    @Override
    protected Expression simplifyRules(final Expression... exps) {
        return simplifyRules(exps[0]);
    }

    /**
     * Simplify rules expression.
     *
     * @param exp the exp
     * @return the expression
     */
    protected abstract Expression simplifyRules(Expression exp);

    @Override
    protected String getString(Expression... exps) {
        return getOperator() + exps[0].toString();
    }

    /**
     * Differentiate logic expression.
     *
     * @param expression the expression
     * @param var        the var
     * @return the expression
     */
    protected abstract Expression differentiateLogic(Expression expression, String var);

    /**
     * Create new expression.
     *
     * @param expression the expression
     * @return the expression
     */
    protected abstract Expression createNew(Expression expression);

    /**
     * Apply operator double.
     *
     * @param num the num
     * @return the double
     */
    protected abstract double applyOperator(double num);
}
