import java.util.List;
import java.util.Map;

// ID 316044809
public abstract class UnaryExpression extends BaseExpression {

    private final Expression exp;

    public UnaryExpression(final Expression expression) {
        this.exp = expression;
    }

    @Override
    public double evaluate(final Map<String, Double> assignment) throws Exception {
        return operator(exp.evaluate(assignment));
    }

    @Override
    public double evaluate() throws Exception {
        return operator(exp.evaluate());
    }

    @Override
    public List<String> getVariables() {
        return exp.getVariables();
    }

    @Override
    public Expression assign(final String var, final Expression expression) {
        return createNew(exp.assign(var, expression));
    }

    public abstract Expression createNew(Expression expression);

    @Override
    public Expression differentiate(final String var) {
        return differentiateLogic(exp, var);
    }

    protected abstract Expression differentiateLogic(Expression expression, String var);

    @Override
    public Expression simplify() {
        return createNew(exp.simplify());
    }

    public abstract double operator(double num);

    @Override
    public String toString() {
        return getOperator() + exp.toString() + ")";
    }

    public abstract String getOperator();
}
