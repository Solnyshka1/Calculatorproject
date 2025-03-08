public class ExpressionEvaluator {

    public static double evaluateExpression(String expression) throws Exception {
        expression = expression.replaceAll("\\s", "");

        while (expression.contains("abs(")) {
            int start = expression.indexOf("abs(") + 4;
            int end = Utils.findClosingParenthesis(expression, start);
            double value = evaluateExpression(expression.substring(start, end));
            expression = expression.replace("abs(" + expression.substring(start, end) + ")", String.valueOf(Math.abs(value)));
        }

        while (expression.contains("sqrt(")) {
            int start = expression.indexOf("sqrt(") + 5;
            int end = Utils.findClosingParenthesis(expression, start);
            double value = evaluateExpression(expression.substring(start, end));
            if (value < 0) throw new ArithmeticException("Square root of negative number");
            expression = expression.replace("sqrt(" + expression.substring(start, end) + ")", String.valueOf(Math.sqrt(value)));
        }

        while (expression.contains("power(")) {
            int start = expression.indexOf("power(") + 6;
            int end = Utils.findClosingParenthesis(expression, start);
            String[] parts = expression.substring(start, end).split(",");
            if (parts.length != 2) throw new IllegalArgumentException("Invalid power arguments");
            double base = evaluateExpression(parts[0]);
            double exponent = evaluateExpression(parts[1]);
            expression = expression.replace("power(" + expression.substring(start, end) + ")", String.valueOf(Math.pow(base, exponent)));
        }

        while (expression.contains("round(")) {
            int start = expression.indexOf("round(") + 6;
            int end = Utils.findClosingParenthesis(expression, start);
            double value = evaluateExpression(expression.substring(start, end));
            expression = expression.replace("round(" + expression.substring(start, end) + ")", String.valueOf(Math.round(value)));
        }

        return evaluateBasic(expression);
    }

    private static double evaluateBasic(String expression) throws Exception {
        try {
            return new Object() {
                int pos = -1, ch;

                void nextChar() {
                    ch = (++pos < expression.length()) ? expression.charAt(pos) : -1;
                }

                boolean eat(int charToEat) {
                    while (ch == ' ') nextChar();
                    if (ch == charToEat) {
                        nextChar();
                        return true;
                    }
                    return false;
                }

                double parse() {
                    nextChar();
                    double x = parseExpression();
                    if (pos < expression.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                    return x;
                }

                double parseExpression() {
                    double x = parseTerm();
                    for (; ; ) {
                        if (eat('+')) x += parseTerm();
                        else if (eat('-')) x -= parseTerm();
                        else return x;
                    }
                }

                double parseTerm() {
                    double x = parseFactor();
                    for (; ; ) {
                        if (eat('*')) x *= parseFactor();
                        else if (eat('/')) {
                            double divisor = parseFactor();
                            if (divisor == 0) throw new ArithmeticException("Division by zero");
                            x /= divisor;
                        } else if (eat('%')) {
                            double divisor = parseFactor();
                            if (divisor == 0) throw new ArithmeticException("Modulo by zero");
                            x %= divisor;
                        } else return x;
                    }
                }

                double parseFactor() {
                    if (eat('+')) return parseFactor();
                    if (eat('-')) return -parseFactor();

                    double x;
                    int startPos = this.pos;
                    if (eat('(')) {
                        x = parseExpression();
                        eat(')');
                    } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                        while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                        x = Double.parseDouble(expression.substring(startPos, this.pos));
                    } else {
                        throw new RuntimeException("Unexpected: " + (char) ch);
                    }
                    return x;
                }
            }.parse();
        } catch (Exception e) {
            throw new Exception("Invalid expression");
        }
    }
}
