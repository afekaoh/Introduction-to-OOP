// ID 316044809


/**
 * The class Unary expression.
 * an abstract class of a single mathematical expression
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
    public Expression createNew(final Expression... exps) {
        return createNew(exps[0]);
    }

    @Override
    protected Expression differentiateLogic(final String var, final Expression... exps) {
        final Expression exp = exps[0];
        return differentiateLogic(exp, var);
    }

    @Override
    protected Expression simplifyRules(final Expression... exps) {
        final Expression exp = exps[0];
        try {
            return new Num(createNew(exp).evaluate());
        } catch (AssigmentException e) {
            return createNew(exp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected double applyOperator(final double... nums) throws Exception {
        final double num = nums[0];
        return applyOperator(num);
    }

    @Override
    protected String getString(Expression... exps) {
        return getOperator() + exps[0].toString();
    }

    /**
     * Apply operator double.
     *
     * @param num the number to apply the operator on
     * @return the value of the mathematical expression after the application of the operator
     * @throws Exception an Arithmetic Exception - if it's imposable to compute the value.
     */
    protected abstract double applyOperator(double num) throws Exception;

    /**
     * Differentiate logic expression.
     *
     * @param expression the expression
     * @param var        the variable of which we differentiate upon.
     * @return the derivative expression
     */
    protected abstract Expression differentiateLogic(Expression expression, String var);

    /**
     * Create new expression.
     *
     * @param expression the second expression
     * @return the new expression
     */
    protected abstract Expression createNew(Expression expression);

}
