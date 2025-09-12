package ru.nsu.kataeva;

/**
 * Parses mathematical expressions from strings.
 */
public class Parser {
    private final String input;
    private int index;

    /**
     * Creates parser for given input string.
     */
    public Parser(String input) {
        this.input = input.replaceAll("\\s+", "");
        this.index = 0;
    }

    /**
     * Starts parsing the expression.
     */
    public Expression parse() {
        return parseExpression();
    }

    /**
     * Parses mathematical expression.
     */
    private Expression parseExpression() {
        if (input.charAt(index) == '(') {
            index++;
            Expression left = parseExpression();
            char operator = input.charAt(index++);
            Expression right = parseExpression();
            index++;

            return switch (operator) {
                case '+' -> new Add(left, right);
                case '-' -> new Sub(left, right);
                case '*' -> new Mul(left, right);
                case '/' -> new Div(left, right);
                default -> throw new IllegalArgumentException("Unknown operator: " + operator);
            };
        } else {
            StringBuilder token = new StringBuilder();
            while (index < input.length() &&
                    (Character.isLetterOrDigit(input.charAt(index)) ||
                            input.charAt(index) == '.' ||
                            input.charAt(index) == '-')) {
                token.append(input.charAt(index));
                index++;
            }

            String tokenStr = token.toString();
            try {
                double value = Double.parseDouble(tokenStr);
                return new Number(value);
            } catch (NumberFormatException e) {
                return new Variable(tokenStr);
            }
        }
    }

    /**
     * Static method to parse string to expression.
     */
    public static Expression parseString(String str) {
        Parser parser = new Parser(str);
        return parser.parse();
    }
}