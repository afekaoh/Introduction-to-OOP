// ID 316044809

import static java.lang.Math.pow;

/**
 * The class Pow.
 * representing the mathematical operation of power
 */
public class Pow extends BinaryExpression {

    /**
     * Instantiates a new Pow.
     *
     * @param expression1 the first expression
     * @param expression2 the second expression
     */
    public Pow(final Expression expression1, final Expression expression2) {
        super(expression1, expression2);
    }

    @Override
    protected Expression createNew(final Expression exp1, final Expression exp2) {
        return new Pow(exp1, exp2);
    }

    @Override
    protected Expression differentiateLogic(final Expression exp1, final Expression exp2, final String var) {
        // (f(x)^g(x)) = (e^(f*ln(g)))' = f^g * (g*ln(f))'
        return new Mult(
                new Pow(exp1, exp2),
                new Mult(exp2, new Log(new Var("e"), exp1)).differentiate(var)
        );
    }

    @Override
    protected double applyOperator(final double num1, final double num2) {
        return pow(num1, num2);
    }

    @Override
    protected String getOperator() {
        return "^";
    }
}
