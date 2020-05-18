// ID 316044809

import exceptions.AssigmentException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The class Base expression.
 * <p>
 * representing an abstract mathematical expression with an unknown number of expressions
 */
public abstract class BaseExpression implements Expression {
    public static final double EPSILON = 10e-12;
    /**
     * all The Expressions in the BaseExpression.
     */
    private final List<Expression> expressions;

    /**
     * Instantiates a new Base expression.
     *
     * @param expressions the expressions components
     */
    protected BaseExpression(Expression... expressions) {
        // making the List Unmodifiable
        this.expressions = Stream.of(expressions).collect(Collectors.toUnmodifiableList());
    }

    /**
     * compering 2 doubles using epsilon to increase precision.
     *
     * @param num1 the first number to compare
     * @param num2 the second number to compare
     * @return if both of them are equals up to an epsilon
     */
    protected static boolean doubleEquals(double num1, double num2) {
        return Math.abs(num1 - num2) <= EPSILON;
    }

    /**
     * Get operator.
     *
     * @return the string that representing the operator
     */
    protected abstract String getOperator();

    @Override
    public double evaluate(final Map<String, Double> assignment) throws Exception {
        double[] arr = new double[expressions.size()];
        int count = 0;
        for (Expression e : expressions) {
            arr[count++] = e.evaluate(assignment);
        }
        return round(applyOperator(arr), 3);
    }

    /**
     * Round int.
     *
     * @param toRound   the number to round
     * @param precision the number of digits to round to
     * @return the number rounded to the precision
     */
    protected static double round(double toRound, int precision) {
        final double factor = Math.pow(10, precision);
        return Math.round(toRound * factor) / factor;
    }

    /**
     * applyOperator.
     * <p>
     * apply the Operator on the current Expression
     *
     * @param nums the expressions components after evaluation.
     * @return the value of the expression after the evaluation on itself
     */
    protected abstract double applyOperator(double... nums);

    @Override
    public double evaluate() throws Exception {
        double[] arr = new double[expressions.size()];
        int count = 0;
        for (Expression e : expressions) {
            arr[count++] = e.evaluate();
        }
        return round(applyOperator(arr), 3);
    }

    @Override
    public List<String> getVariables() {
        List<String> vars = new ArrayList<>();
        // unite all the list we got from the expressions
        expressions.forEach(e -> vars.addAll(e.getVariables()));
        // make sure all the variables in the list are unique
        return vars.stream()
                   .distinct()
                   .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Expression assign(final String var, final Expression expression) {
        return createNew(expressions.stream().map(e -> e.assign(var, expression)).toArray(Expression[]::new));
    }

    @Override
    public Expression differentiate(final String var) {
        return differentiateLogic(var, expressions.toArray(Expression[]::new));
    }

    @Override
    public Expression simplify() {
        // getting all the simplified expression to an array
        final Expression[] simplifiedExpressions = expressions.stream()
                                                              .map(Expression::simplify)
                                                              .toArray(Expression[]::new);

        try {
            // checking if the expression can be evaluated to a number
            return new Num(createNew(simplifiedExpressions).evaluate());
        } catch (AssigmentException e) {
            // simplify the expression if cannot be evaluated because assigment problem
            return simplifyRules(simplifiedExpressions);
        } catch (Exception e) {
            // there is an arithmetic reason we cannot evaluated the expression so return the expression as it is
            return createNew(simplifiedExpressions);
        }
    }

    /**
     * Simplify rules expression.
     *
     * @param exps the simplified Expressions components.
     * @return the simplified expression
     */
    protected abstract Expression simplifyRules(Expression... exps);

    /**
     * Create new expression.
     *
     * @param exps the expressions components from which we create a new expression.
     * @return the new expression
     */
    protected abstract Expression createNew(Expression... exps);

    /**
     * Differentiate logic expression.
     *
     * @param var  the variable to differentiate upon.
     * @param exps the expressions components to differentiate.
     * @return the derivative of the expression
     */
    protected abstract Expression differentiateLogic(String var, Expression... exps);

    @Override
    public String toString() {
        return getString(expressions.toArray(Expression[]::new)) + ")";
    }

    /**
     * Get string.
     *
     * @param exps the expressions components
     * @return a string unique to the class we want the string from
     */
    protected abstract String getString(Expression... exps);
}
