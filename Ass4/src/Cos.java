import static java.lang.Math.cos;


/**
 * The class Cos.
 */
public class Cos extends UnaryExpression {

    /**
     * Instantiates a new Cos.
     *
     * @param expression the expression
     */
    public Cos(final Expression expression) {
        super(expression);
    }

    @Override
    public Expression createNew(final Expression expression) {
        return new Cos(expression);
    }

    @Override
    protected Expression differentiateLogic(final Expression expression, final String var) {
        return new Mult(
                new Neg(new Sin(expression)),
                expression.differentiate(var)
        );
    }

    @Override
    public double operator(final double num) {
        return cos(num);
    }

    @Override
    public String getOperator() {
        return "cos(";
    }

}
