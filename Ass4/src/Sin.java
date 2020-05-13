import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

/**
 * The class Sin.
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
    protected Expression simplifyRules(final Expression exp) {
        return new Sin(exp);
    }

    @Override
    protected Expression differentiateLogic(final Expression expression, final String var) {
        return new Mult(
                new Cos(expression),
                expression.differentiate(var)
        );
    }

    @Override
    public Expression createNew(final Expression expression) {
        return new Sin(expression);
    }

    @Override
    public double applyOperator(final double num) {
        return sin(toRadians(num));
    }

    @Override
    public String getOperator() {
        return "sin(";
    }
}