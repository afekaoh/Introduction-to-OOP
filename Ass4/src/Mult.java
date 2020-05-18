// ID 316044809

/**
 * The class Mult.
 * representing the mathematical operation of multiplication
 */
public class Mult extends BinaryExpression {
    /**
     * Instantiates a new Binary expression.
     *
     * @param expression1 the exp 1
     * @param expression2 the exp 2
     */
    public Mult(final Expression expression1, final Expression expression2) {
        super(expression1, expression2);
    }

    @Override
    protected Expression differentiateLogic(final Expression exp1, final Expression exp2, String var) {
        // (f(x)*g(x))' = f'g + fg'
        return new Plus(
                new Mult(exp1.differentiate(var), exp2),
                new Mult(exp1, exp2.differentiate(var))
        );
    }

    @Override
    protected Expression createNew(final Expression exp1, final Expression exp2) {
        return new Mult(exp1, exp2);
    }

    @Override
    protected Expression simplifyRules(final Expression exp1, final Expression exp2) {
        try {
            final double num1 = exp1.evaluate();
            // 0 * x
            if (doubleEquals(num1, 0)) {
                return new Num(0);
            }
            // 1 * x
            if (doubleEquals(num1, 1)) {
                return exp2;
            }
        } catch (Exception e) {
            // both of the expression cannot be evaluated to a number otherwise this function wouldn't have be called
            try {
                final double num2 = exp2.evaluate();
                // x * 0
                if (doubleEquals(num2, 0)) {
                    return new Num(0);
                }
                // x * 1
                if (doubleEquals(num2, 1)) {
                    return exp1;
                }
            } catch (Exception ignored) {
            }
        }
        return super.simplifyRules(exp1, exp2);
    }

    @Override
    protected double applyOperator(final double num1, final double num2) {
        return num1 * num2;
    }

    @Override
    protected String getOperator() {
        return " * ";
    }
}
