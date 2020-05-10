// ID 316044809

/**
 * The class Mult.
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
    protected Expression createNew(final Expression exp1, final Expression exp2) {
        return new Mult(exp1, exp2);
    }

    @Override
    protected Expression differentiateLogic(final Expression exp1, final Expression exp2, String var) {
        return new Plus(
                new Mult(exp1.differentiate(var), exp2),
                new Mult(exp1, exp2.differentiate(var))
        );
    }

    @Override
    protected Expression simplifyRules(final Expression exp1, final Expression exp2) {

        if (exp1.equals(Const.ZERO) || exp2.equals(Const.ZERO)) {
            return new Num(0);
        }
        if (exp1.equals(Const.ONE)) {
            return exp2;
        }
        if (exp2.equals(Const.ONE)) {
            return exp1;
        }
        return new Mult(exp1, exp2);
    }

    @Override
    protected double operator(final double num1, final double num2) {
        return num1 * num2;
    }

    @Override
    protected String getOperator() {
        return " * ";
    }
}
