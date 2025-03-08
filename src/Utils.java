public class Utils {

    public static int findClosingParenthesis(String expression, int start) {
        int count = 1;
        for (int i = start; i < expression.length(); i++) {
            if (expression.charAt(i) == '(') count++;
            if (expression.charAt(i) == ')') count--;
            if (count == 0) return i;
        }
        throw new IllegalArgumentException("Mismatched parentheses");
    }
}
