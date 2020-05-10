// ID 316044809#

/**
 * The class Div.
 */
public class Div extends BinaryExpression {
    /**
     * Instantiates a new Binary expression.
     *
     * @param expression1 the exp 1
     * @param expression2 the exp 2
     */
    public Div(final Expression expression1, final Expression expression2) {
        super(expression1, expression2);
    }

    @Override
    public Expression createNew(final Expression exp1, final Expression exp2) {
        return new Div(exp1, exp2);
    }

    @Override
    protected Expression differentiateLogic(final Expression exp1, final Expression exp2, final String var) {
        return new Div(
                new Minus(
                        new Mult(exp1.differentiate(var), exp2),
                        new Mult(exp1, exp2.differentiate(var))
                ),
                new Pow(exp2, new Num(2))
        );
    }

    @Override
    protected Expression simplifyRules(final Expression exp1, final Expression exp2) {
        if (exp1.equals(exp2)) {
            return new Num(1);
        }
        if (exp1.equals(Const.ZERO)) {
            return new Num(0);
        }
        if (exp1.equals(Const.ONE)) {
            return exp2;
        }
        if (exp2.equals(Const.ONE)) {
            return exp1;
        }
        return new Div(exp1, exp2);
    }

    @Override
    public double operator(final double num1, final double num2) {
        return num1 / num2;
    }

    @Override
    protected String getOperator() {
        return " / ";
    }
}
