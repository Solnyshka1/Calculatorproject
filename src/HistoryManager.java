import java.util.ArrayList;
import java.util.List;

public class HistoryManager {

    private final List<String> history = new ArrayList<>();

    public void addHistory(String expression, double result) {
        history.add(expression + " = " + result);
    }

    public void showHistory() {
        if (history.isEmpty()) {
            System.out.println("No history available.");
        } else {
            System.out.println("Calculation History:");
            for (String record : history) {
                System.out.println(record);
            }
        }
    }
}
