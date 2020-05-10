// ID 316044809


/**
 * The class Neg.
 * <p>
 * turn an expresion to it's negative value
 */
public class Neg extends UnaryExpression {

    /**
     * Instantiates a new Neg.
     *
     * @param toNegate the to negate
     */
    public Neg(final Expression toNegate) {
        super(toNegate);
    }

    @Override
    public Expression createNew(Expression exp) {
        return new Neg(exp);
    }

    @Override
    protected Expression differentiateLogic(final Expression expression, final String var) {
        return new Neg(expression.differentiate(var));
    }

    @Override
    public String getOperator() {
        return "(-";
    }

    @Override
    public double operator(final double num, double num2) {
        return -1 * num;
    }
}
