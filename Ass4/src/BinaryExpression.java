// ID 316044809


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * The class Binary expression.
 */
public abstract class BinaryExpression extends BaseExpression {
    private final Expression expression1;
    private final Expression expression2;

    /**
     * Instantiates a new Binary expression.
     *
     * @param expression1 the exp 1
     * @param expression2 the exp 2
     */
    public BinaryExpression(final Expression expression1, final Expression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    @Override
    public double evaluate(final Map<String, Double> assignment) throws Exception {
        return operator(expression1.evaluate(assignment), expression2.evaluate(assignment));
    }

    @Override
    public double evaluate() throws Exception {
        return operator(expression1.evaluate(), expression2.evaluate());
    }

    @Override
    public List<String> getVariables() {
        List<String> variables = new ArrayList<>();
        Stream.of(expression1.getVariables(), expression2.getVariables()).distinct().forEach(variables::addAll);
        return variables;
    }

    @Override
    public Expression assign(final String var, final Expression expression) {
        return createNew(this.expression1.assign(var, expression), expression2.assign(var, expression));
    }

    protected abstract Expression createNew(Expression exp1, Expression exp2);

    @Override
    public Expression differentiate(final String var) {
        return differentiateLogic(expression1, expression2, var);
    }

    protected abstract Expression differentiateLogic(Expression exp1, Expression exp2, String var);

    @Override
    public Expression simplify() {
        return simplifyRules(expression1.simplify(), expression2.simplify());
    }

    protected abstract Expression simplifyRules(Expression exp1, Expression exp2);

    protected abstract double operator(double num1, double num2);

    @Override
    public String toString() {
        return "(" + expression1.toString() + getOperator() + expression2.toString() + ")";
    }

    protected abstract String getOperator();
}
