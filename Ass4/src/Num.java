// ID 316044809

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * The class Num.
 * representing number
 */
public class Num implements Expression {

    private final double num;

    /**
     * Instantiates a new Num.
     *
     * @param num the num
     */
    public Num(final double num) {
        this.num = num;
    }

    @Override
    public double evaluate(final Map<String, Double> assignment) {
        return num;
    }

    @Override
    public double evaluate() {
        return num;
    }

    @Override
    public List<String> getVariables() {
        // return empty list
        return Collections.emptyList();
    }

    @Override
    public Expression assign(final String var, final Expression expression) {
        return this;
    }

    @Override
    public Expression differentiate(final String var) {
        // c' = 0
        return new Num(0);
    }

    @Override
    public Expression simplify() {
        // it is the most simplified version already
        return this;
    }

    @Override
    public String toString() {
        return String.valueOf(num);
    }
}
