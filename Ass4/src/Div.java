// ID 316044809#

import exceptions.DivideByZeroException;

/**
 * The class Div.
 * representing the mathematical operation of division
 */
public class Div extends BinaryExpression {
    /**
     * Instantiates a new Div expression.
     *
     * @param expression1 the first expression
     * @param expression2 the second expression
     */
    public Div(final Expression expression1, final Expression expression2) {
        super(expression1, expression2);
    }

    @Override
    protected Expression createNew(final Expression exp1, final Expression exp2) {
        return new Div(exp1, exp2);
    }

    @Override
    protected Expression simplifyRules(final Expression exp1, final Expression exp2) {
        try {
            double denominator = exp2.evaluate();
            // x / 1
            if (doubleEquals(denominator, 1)) {
                return exp1;
            }
        } catch (Exception exception) {
            if (exp1.toString().equals(exp2.toString())) {
                return new Num(1);
            }
        }
        return super.simplifyRules(exp1, exp2);
    }

    @Override
    protected Expression differentiateLogic(final Expression exp1, final Expression exp2, final String var) {
        // (f(x)/g(x))' = (f'g - fg')/g^2)
        return new Div(
                new Minus(
                        new Mult(exp1.differentiate(var), exp2),
                        new Mult(exp1, exp2.differentiate(var))
                ),
                new Pow(exp2, new Num(2))
        );
    }

    @Override
    protected double applyOperator(final double num1, final double num2) {
        if (doubleEquals(num2, 0)) {
            throw new DivideByZeroException("cannot divide by 0");
        }
        return num1 / num2;
    }

    @Override
    protected String getOperator() {
        return " / ";
    }
}
