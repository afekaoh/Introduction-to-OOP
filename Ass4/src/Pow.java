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
    public Expression createNew(final Expression exp1, final Expression exp2) {
        return new Pow(exp1, exp2);
    }

    @Override
    protected Expression differentiateLogic(final Expression exp1, final Expression exp2, final String var) {
        return new Mult(
                new Pow(exp1, exp2),
                new Plus(
                        new Mult(
                                exp2.differentiate(var),
                                new Log(new Var("e"), exp1)
                        ),
                        new Mult(
                                exp1.differentiate(var),
                                new Div(exp2, exp1)
                        )
                )
        );
    }

    @Override
    public double applyOperator(final double num1, final double num2) {
        return pow(num1, num2);
    }

    @Override
    protected Expression simplifyRules(final Expression exp1, final Expression exp2) {
        if (exp2.equals(Const.ONE)) {
            return exp1.simplify();
        }
        if (exp1.equals(Const.ONE) || exp2.equals(Const.ZERO)) {
            return new Num(1);
        }
        if (exp1.equals(Const.ZERO)) {
            return new Num(0);
        }
        return new Pow(exp1, exp2);
    }

    @Override
    protected String getOperator() {
        return "^";
    }
}
