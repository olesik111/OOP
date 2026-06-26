package ru.nsu.kataeva;

/**
 * Represents a variable in mathematical expression.
 */
public class Variable extends Expression {

    private final String name;

    /**
     * Creates new variable with given name.
     */
    public Variable(String name) {
        this.name = name;
    }

    /**
     * Returns derivative: 1 if same variable, 0 otherwise.
     */
    @Override
    public Expression derivative(String variable) {
        if (name.equals(variable)) {
            return new Number(1);
        } else {
            return new Number(0);
        }
    }

    /**
     * Variable is already simplified.
     */
    @Override
    public Expression doSimple() {
        return this;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Variable v)) {
            return false;
        }
        return this.name.equals(v.name);
    }

    /**
     * Evaluates variable value from given assignments.
     */
    @Override
    public double eval(String variablesString) {
        String[] assignments = variablesString.split(";");
        for (String assignment : assignments) {
            String[] parts = assignment.split("=");
            if (parts.length == 2) {
                String varName = parts[0].trim();
                String valueStr = parts[1].trim();
                if (varName.equals(name)) {
                    try {
                        return Double.parseDouble(valueStr);
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Bad value" + name);
                    }
                }
            }
        }
        throw new IllegalArgumentException("Var " + name + " is not defined");
    }
}