// ID 316044809

import exceptions.AssigmentException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * The class Base expression.
 * representing an abstract mathematical expression with an unknown number of expressions
 */
public abstract class BaseExpression implements Expression {
    public static final double EPSILON = 10e-12;
    public static final int MAX_NUM_OF_EXPRESSIONS = 2;
    /**
     * all The Expressions in the BaseExpression.
     */
    private final List<Expression> expressions;

    /**
     * Instantiates a new Base expression.
     *
     * @param expressions the expressions
     */
    protected BaseExpression(Expression... expressions) {
        // making the List Unmodifiable
        this.expressions = Stream.of(expressions).collect(Collectors.toUnmodifiableList());
    }

    /**
     * compering 2 doubles using epsilon to increase precision.
     *
     * @param num1 the first num to compare
     * @param num2 the second num to compare
     * @return if both of them are equals up to an epsilon
     */
    protected static boolean doubleEquals(double num1, double num2) {
        return Math.abs(num1 - num2) <= EPSILON;
    }

    /**
     * Get operator.
     *
     * @return the string that representing operator
     */
    protected abstract String getOperator();

    @Override
    public double evaluate(final Map<String, Double> assignment) throws Exception {
        double[] arr = new double[MAX_NUM_OF_EXPRESSIONS];
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
     * apply the Operator on the current Expression
     *
     * @param nums an unknown numbers of numbers to apply the applyOperator on
     * @return the value of the expression after the evaluation
     */
    protected abstract double applyOperator(double... nums);

    @Override
    public double evaluate() throws Exception {
        double[] arr = new double[MAX_NUM_OF_EXPRESSIONS];
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
        return createNew(expressions.stream()
                                    .map(e -> e.assign(var, expression))
                                    .toArray(Expression[]::new));
    }

    /**
     * Create new expression.
     *
     * @param exps the expressions from which we create a new one
     * @return the new expression
     */
    protected abstract Expression createNew(Expression... exps);

    @Override
    public Expression differentiate(final String var) {
        return differentiateLogic(var, expressions.toArray(Expression[]::new));
    }

    /**
     * Differentiate logic expression.
     *
     * @param var  the variable to differentiate upon.
     * @param exps the expressions to differentiate.
     * @return the derivative expression
     */
    protected abstract Expression differentiateLogic(String var, Expression... exps);

    @Override
    public Expression simplify() {
        // getting all the simplified expression to an array
        final Expression[] simplifiedExpressions = this.expressions.stream()
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
     * @param exps the Expressions which need to be simplified
     * @return the simplified expression
     */
    protected abstract Expression simplifyRules(Expression... exps);

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
