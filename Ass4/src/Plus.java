// ID 316044809

/**
 * The class Minus.
 */
public class Plus extends BinaryExpression {

    /**
     * Instantiates a new Minus.
     *
     * @param exp1 the first expression
     * @param exp2 the second expression
     */
    public Plus(final Expression exp1, final Expression exp2) {
        super(exp1, exp2);
    }

    public Expression createNew(Expression exp1, Expression exp2) {
        return new Plus(exp1, exp2);
    }

    @Override
    protected Expression differentiateLogic(final Expression exp1, final Expression exp2, final String var) {
        return new Plus(exp1.differentiate(var), exp2.differentiate(var));
    }

    @Override
    protected Expression simplifyRules(final Expression exp1, final Expression exp2) {
        if (exp1.equals(exp2)) {
            return new Mult(exp1, new Num(2)).simplify();
        }
        if (exp1.equals(Const.ZERO)) {
            return exp2;
        }
        if (exp2.equals(Const.ZERO)) {
            return exp1;
        }
        return new Plus(exp1, exp2);
    }

    @Override
    public double operator(final double num1, final double num2) {
        return num1 + num2;
    }

    @Override
    protected String getOperator() {
        return " + ";
    }
}
