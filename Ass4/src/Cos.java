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
    public double applyOperator(final double num) {
        return cos(Math.toRadians(num));
    }

    @Override
    protected Expression differentiateLogic(final Expression expression, final String var) {
        return new Mult(
                new Neg(expression.differentiate(var)),
                new Sin(expression)
        );
    }

    @Override
    public Expression createNew(final Expression expression) {
        return new Cos(expression);
    }

    @Override
    public String getOperator() {
        return "cos(";
    }

}
