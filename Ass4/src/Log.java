// ID 316044809

import exceptions.LogBaseException;
import exceptions.LogPowerException;

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
    protected Expression createNew(final Expression exp1, final Expression exp2) {
        return new Log(exp1, exp2);
    }

    @Override
    protected Expression simplifyRules(final Expression exp1, final Expression exp2) {
        // log(x, x)
        if (exp1.toString().equals(exp2.toString())) {
            return new Num(1);
        }
        return super.simplifyRules(exp1, exp2);
    }

    @Override
    protected Expression differentiateLogic(final Expression exp1, final Expression exp2, final String var) {
        // we know how to differentiate ln easily
        if (exp1.toString().equals("e")) {
            // (ln(f(x)))' = f'/f
            return new Div(exp2.differentiate(var), exp2);
        }
        // converting the base to ln and then differentiate with the easier base
        return toLn(exp1, exp2).differentiate(var);
    }

    @Override
    protected double applyOperator(final double num1, final double num2) {
        if (num2 <= EPSILON) {
            throw new LogPowerException(num2);
        }
        if (doubleEquals(num1, 1) || num1 <= EPSILON) {
            throw new LogBaseException(num1);
        }
        return Math.log(num2) / Math.log(num1);
    }

    /**
     * To ln expression.
     * <p>
     * converting the log to be ln based
     *
     * @param exp1 the first expression
     * @param exp2 the second expression
     * @return the ln based expression
     */
    private Expression toLn(final Expression exp1, final Expression exp2) {
        final Var e = new Var("e");
        // log(a, b) = ln(b)/ln(a)
        return new Div(
                new Log(e, exp2),
                new Log(e, exp1)
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
