// ID 316044809

/**
 * The class Log.
 */
public class Log extends BinaryExpression {
    /**
     * Instantiates a new Binary expression.
     *
     * @param expression1 the exp 1
     * @param expression2 the exp 2
     */
    public Log(final Expression expression1, final Expression expression2) {
        super(expression1, expression2);
    }

    @Override
    public double applyOperator(final double num1, final double num2) {
        if (-Const.EPSILON <= num2 && num2 <= Const.EPSILON) {
            throw new DivideByZeroException("cannot compute Log of 0");
        }
        return Math.log(num2) / Math.log(num1);
    }

    @Override
    public Expression createNew(final Expression exp1, final Expression exp2) {
        return new Log(exp1, exp2);
    }

    @Override
    protected Expression differentiateLogic(final Expression exp1, final Expression exp2, final String var) {
        if (exp1.equals(Const.E)) {
            return new Div(exp2.differentiate(var), exp2);
        }
        return toLn(exp1, exp2).differentiate(var);
    }

    @Override
    protected Expression simplifyRules(final Expression exp1, final Expression exp2) {
        if (exp1.equals(exp2)) {
            return new Num(1);
        }
        if (exp2.equals(Const.ONE)) {
            return new Num(0);
        }
        return new Log(exp1, exp2);
    }

    /**
     * To ln expression.
     * converting the log to
     *
     * @param exp1 the exp 1
     * @param exp2 the exp 2
     * @return the expression
     */
    private Expression toLn(final Expression exp1, final Expression exp2) {
        return new Div(
                new Log(Const.E, exp2),
                new Log(Const.E, exp1)
        );
    }

    @Override
    protected String getOperator() {
        return ", ";
    }

    @Override
    public String toString() {
        return "log" + super.toString();
    }
}
