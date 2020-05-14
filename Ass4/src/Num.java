// ID 316044809

import java.util.ArrayList;
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
        return new ArrayList<>();
    }

    @Override
    public Expression assign(final String var, final Expression expression) {
        return new Num(num);
    }

    @Override
    public Expression differentiate(final String var) {
        return new Num(0);
    }

    @Override
    public Expression simplify() {
        return new Num(num);
    }

    @Override
    public boolean equals(final Expression e) {
        if (e.isNum()) {
            Num other = (Num) e;
            return Const.doubleEquals(num, other.num);
        }
        return false;
    }

    @Override
    public boolean isVar() {
        return false;
    }

    @Override
    public boolean isNum() {
        return true;
    }

    @Override
    public boolean isNeg() {
        return false;
    }

    @Override
    public String toString() {
        return String.valueOf(num);
    }
}
