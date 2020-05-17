import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

/**
 * The class Sin.
 * representing the mathematical operation of sine.
 */
public class Sin extends UnaryExpression {

    /**
     * Instantiates a new Sin.
     *
     * @param expression the expression
     */
    public Sin(final Expression expression) {
        super(expression);
    }

    @Override
    protected Expression differentiateLogic(final Expression expression, final String var) {
        // (sin(f(x))' = f' * cos(f)
        return new Mult(
                expression.differentiate(var),
                new Cos(expression)
        );
    }

    @Override
    protected Expression createNew(final Expression expression) {
        return new Sin(expression);
    }

    @Override
    protected double applyOperator(final double num) {
        return sin(toRadians(num));
    }

    @Override
    protected String getOperator() {
        return "sin(";
    }
}
