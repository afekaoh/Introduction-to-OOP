// ID 316044809


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The class Var.
 */
public class Var implements Expression {

    private final String variable;

    /**
     * Instantiates a new Var.
     *
     * @param var the var
     */
    public Var(final String var) {
        this.variable = var;
    }

    @Override
    public double evaluate(final Map<String, Double> assignment) throws Exception {
        if (assignment.containsKey(variable)) {
            return assignment.get(variable);
        } else {
            throw new RuntimeException("the variable " + variable + " is not assigned");
        }
    }

    @Override
    public double evaluate() throws Exception {
        throw new RuntimeException("the variable " + variable + " is not assigned");
    }

    @Override
    public List<String> getVariables() {
        List<String> variables = new ArrayList<>();
        variables.add(variable);
        return variables;
    }

    @Override
    public Expression assign(final String var, final Expression expression) {
        if (var.equals(variable)) {
            return expression;
        }
        return new Var(variable);
    }

    @Override
    public Expression differentiate(final String var) {
        return var.equals(variable) ? new Num(1) : new Num(0);
    }

    @Override
    public Expression simplify() {
        return new Var(variable);
    }

    @Override
    public boolean equals(final Expression e) {
        if (e.isVar()) {
            Var other = (Var) e;
            return other.variable.equals(this.variable);
        }
        return false;
    }

    @Override
    public boolean isVar() {
        return true;
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
        return variable;
    }
}
