// ID 316044809

/**
 * The class Log.
 * representing the mathematical operation of logarithm.
 */
public class Log extends BinaryExpression {

    /**
     * Instantiates a new Log Expression.
     *
     * @param expression1 the first expression
     * @param expression2 the second expression
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
    public double applyOperator(final double num1, final double num2) throws Exception {
        if (Const.doubleEquals(num2, 0)) {
            throw new DivideByZeroException("cannot compute Log of 0");
        }
        if (Const.doubleEquals(num1, 1) || num1 <= 0) {
            throw new LogBaseException("the base of the log must be grater then 0 and different then 1");
        }
        return Math.log(num2) / Math.log(num1);
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
     * converting the log to be ln based
     *
     * @param exp1 the first expression
     * @param exp2 the second expression
     * @return the ln based expression
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
