// ID 316044809


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * The class Base expression.
 */
public abstract class BaseExpression implements Expression {
    private final List<Expression> expressions;

    /**
     * Instantiates a new Base expression.
     *
     * @param expressions the expressions
     */
    protected BaseExpression(Expression... expressions) {
        this.expressions = new ArrayList<>();
        this.expressions.addAll(Arrays.asList(expressions));
    }

    /**
     * Get operator.
     *
     * @return the operator
     */
    protected abstract String getOperator();

    @Override
    public double evaluate(final Map<String, Double> assignment) throws Exception {
        double[] arr = new double[2];
        int count = 0;
        for (Expression e : expressions) {
            double evaluate = e.evaluate(assignment);
            arr[count++] = evaluate;
        }
        return applyOperator(arr);
    }

    /**
     * Operator double.
     * apply the applyOperator base on the current Expression
     *
     * @param nums an unknown numbers of numbers to apply the applyOperator on
     * @return the value of the expression after the evaluation
     */
    protected abstract double applyOperator(double... nums) throws Exception;

    @Override
    public double evaluate() throws Exception {
        double[] arr = new double[2];
        int count = 0;
        for (Expression e : expressions) {
            double evaluate = e.evaluate();
            arr[count++] = evaluate;
        }
        return applyOperator(arr);
    }

    @Override
    public List<String> getVariables() {
        List<String> vars = new ArrayList<>();
        expressions.forEach(e -> vars.addAll(e.getVariables()));
        return vars.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public Expression assign(final String var, final Expression expression) {
        return createNew(expressions.stream().map(e -> e.assign(var, expression)).toArray(Expression[]::new));
    }

    /**
     * Create new expression.
     *
     * @param exps the expressions
     * @return the expression
     */
    protected abstract Expression createNew(Expression... exps);

    @Override
    public Expression differentiate(final String var) {
        return differentiateLogic(var, expressions.toArray(Expression[]::new));
    }

    /**
     * Differentiate logic expression.
     *
     * @param var  the var
     * @param exps the exps
     * @return the expression
     */
    protected abstract Expression differentiateLogic(String var, Expression... exps);

    @Override
    public Expression simplify() {
        return simplifyRules(expressions.stream().map(Expression::simplify).toArray(Expression[]::new));
    }

    /**
     * Simplify rules expression.
     *
     * @param exps the exps
     * @return the expression
     */
    protected abstract Expression simplifyRules(Expression... exps);

    @Override
    public boolean equals(Expression e) {
        if (e.isNum() || e.isVar()) {
            return false;
        }
        BaseExpression baseE = (BaseExpression) e;
        if (baseE.expressions.size() != this.expressions.size()) {
            return false;
        }
        final Expression thisExp1 = this.expressions.get(0);
        final Expression otherExp1 = baseE.expressions.get(0);
        if (this.expressions.size() == 1) {
            return thisExp1.equals(otherExp1);
        }
        final Expression thisExp2 = this.expressions.get(1);
        final Expression otherExp2 = baseE.expressions.get(1);
        return (thisExp1.equals(otherExp1) && thisExp2.equals(otherExp2)) || (thisExp1.equals(otherExp2) && thisExp2
                .equals(otherExp1));
    }

    @Override
    public boolean isVar() {
        return false;
    }

    @Override
    public boolean isNum() {
        return false;
    }

    @Override
    public boolean isNeg() {
        return false;
    }

    @Override
    public String toString() {
        return getString(expressions.toArray(Expression[]::new)) + ")";
    }

    /**
     * Get string.
     *
     * @param exps the exps
     * @return the string
     */
    protected abstract String getString(Expression... exps);
}
