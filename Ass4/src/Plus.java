// ID 316044809

/**
 * The class Plus.
 * representing the mathematical operation of plus
 */
public class Plus extends BinaryExpression {

    /**
     * Instantiates a new Plus.
     *
     * @param exp1 the first expression
     * @param exp2 the second expression
     */
    public Plus(final Expression exp1, final Expression exp2) {
        super(exp1, exp2);
    }

    @Override
    protected Expression simplifyRules(final Expression exp1, final Expression exp2) {
        try {
            final double num1 = exp1.evaluate();
            // 0 + x
            if (doubleEquals(num1, 0)) {
                return exp2;
            }
        } catch (Exception e) {
            // both of the expression cannot be evaluated to a number otherwise this function wouldn't have be called
            try {
                // x + 0
                final double num2 = exp2.evaluate();
                if (doubleEquals(num2, 0)) {
                    return exp1;
                }
            } catch (Exception ignored) {
                boolean ignoreThis;
            }
        }
        return super.simplifyRules(exp1, exp2);
    }

    @Override
    protected Expression createNew(Expression exp1, Expression exp2) {
        return new Plus(exp1, exp2);
    }

    @Override
    protected double applyOperator(final double num1, final double num2) {
        return num1 + num2;
    }

    @Override
    protected String getOperator() {
        return " + ";
    }
}
