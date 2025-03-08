import java.util.Scanner;

public class Calculator {

    private static final HistoryManager historyManager = new HistoryManager();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Calculator!");

        while (true) {
            try {
                System.out.print("Please enter your arithmetic expression: ");
                String input = scanner.nextLine().trim();

                if (input.equalsIgnoreCase("history")) {
                    historyManager.showHistory();
                    continue;
                }

                double result = ExpressionEvaluator.evaluateExpression(input);
                System.out.println("Result: " + result);
                historyManager.addHistory(input, result);

                System.out.print("Do you want to continue? (y/n): ");
                if (scanner.nextLine().equalsIgnoreCase("n")) {
                    System.out.println("Thank you for using the Calculator!");
                    break;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }
}
