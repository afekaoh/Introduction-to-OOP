// ID 316044809

/**
 * The class Minus.
 * representing the mathematical operation of subtraction
 */
public class Minus extends BinaryExpression {

    /**
     * Instantiates a new Minus Expression.
     *
     * @param expression1 the first expression
     * @param expression2 the second expression
     */
    public Minus(final Expression expression1, final Expression expression2) {
        super(expression1, expression2);
    }

    @Override
    protected Expression createNew(final Expression exp1, final Expression exp2) {
        return new Minus(exp1, exp2);
    }

    @Override
    protected Expression simplifyRules(final Expression exp1, final Expression exp2) {
        try {
            // 0 - x
            if (doubleEquals(exp1.evaluate(), 0)) {
                return new Neg(exp2);
            }
        } catch (Exception e) {
            try {
                // x - 0
                if (doubleEquals(exp2.evaluate(), 0)) {
                    return exp1;
                }
            } catch (Exception exception) {
                // x - x
                if (exp1.toString().equals(exp2.toString())) {
                    return new Num(0);
                }
            }
        }
        return super.simplifyRules(exp1, exp2);
    }

    @Override
    protected double applyOperator(final double num1, final double num2) {
        return num1 - num2;
    }

    @Override
    protected String getOperator() {
        return " - ";
    }
}
