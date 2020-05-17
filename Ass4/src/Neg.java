// ID 316044809


/**
 * The class Neg.
 * representing a negative mathematical expression
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
    protected Expression createNew(Expression exp) {
        return new Neg(exp);
    }

    @Override
    protected double applyOperator(final double num) {
        return -1 * num;
    }

    @Override
    protected String getOperator() {
        return "(-";
    }
}
