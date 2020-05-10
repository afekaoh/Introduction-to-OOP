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
    public Expression createNew(final Expression expression) {
        return new Sin(expression);
    }

    @Override
    protected Expression differentiateLogic(final Expression expression, final String var) {
        return new Mult(
                new Cos(expression),
                expression.differentiate(var)
        );
    }

    @Override
    public double operator(final double num) {
        return Math.sin(num);
    }

    @Override
    public String getOperator() {
        return "sin(";
    }
}
