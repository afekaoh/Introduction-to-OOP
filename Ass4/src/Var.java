// ID 316044809


import exceptions.AssigmentException;

import java.util.List;
import java.util.Map;

/**
 * The class Var.
 * representing number
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
            // if it if the right var return it's value
            return assignment.get(variable);
        } else {
            // the variable does not exist in the map
            throw new AssigmentException(variable);
        }
    }

    @Override
    public double evaluate() throws Exception {
        // if we got here then the variable is defiantly not assigned
        throw new AssigmentException(variable);
    }

    @Override
    public List<String> getVariables() {
        return List.of(variable);
    }

    @Override
    public Expression assign(final String var, final Expression expression) {
        if (var.equals(variable)) {
            return expression;
        }
        return this;
    }

    @Override
    public Expression differentiate(final String var) {
        // d/dx x = 1 | d/dy x = 0
        return var.equals(variable) ? new Num(1) : new Num(0);
    }

    @Override
    public Expression simplify() {
        // it is the most simplified version already
        return this;
    }

    @Override
    public String toString() {
        return variable;
    }
}
