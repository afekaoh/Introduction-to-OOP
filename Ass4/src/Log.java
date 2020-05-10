// ID 316044809

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

    @Override
    public double operator(final double num1, final double num2) {
        return Math.log(num2) / Math.log(num1);
    }

    @Override
    public String toString() {
        return "log" + super.toString();
    }

    @Override
    protected String getOperator() {
        return ", ";
    }

    private Expression toLn(final Expression exp1, final Expression exp2) {
        return new Div(
                new Log(Const.E, exp2),
                new Log(Const.E, exp1)
        );
    }
}
