// ID 316044809

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * The class Num.
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
    public double evaluate(final Map<String, Double> assignment) throws Exception {
        return num;
    }

    @Override
    public double evaluate() throws Exception {
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
    public int hashCode() {
        return Objects.hash(num);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Num num1 = (Num) o;
        return Double.compare(num1.num, num) == 0;
    }

    @Override
    public String toString() {
        return String.valueOf(num);
    }
}
