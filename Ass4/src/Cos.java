import static java.lang.Math.cos;

/**
 * The class Cos.
 * representing the mathematical operation of cosine.
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
    protected Expression differentiateLogic(final Expression expression, final String var) {
        // cos(f(x))' = -f'*sin(f)
        return new Neg(
                new Mult(
                        expression.differentiate(var),
                        new Sin(expression)
                )
        );
    }

    @Override
    protected Expression createNew(final Expression expression) {
        return new Cos(expression);
    }

    @Override
    protected double applyOperator(final double num) {
        return cos(Math.toRadians(num));
    }

    @Override
    protected String getOperator() {
        return "cos(";
    }

}
