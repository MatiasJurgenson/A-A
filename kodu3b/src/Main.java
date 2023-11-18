import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) {
        int[] denominations = {10, 20, 50, 100, 200};
        int[] counts = {15, 15, 15, 10, 10};
        int targetAmount = 500; // The amount you want to pay

        Deque<Integer> currentCombination = new ArrayDeque<>();
        findCombinations(denominations, counts, targetAmount, 0, currentCombination);
    }

    static void findCombinations(int[] denominations, int[] counts, int targetAmount, int index, Deque<Integer> currentCombination) {
        if (targetAmount == 0) {
            // Print the current combination
            System.out.println(currentCombination);
            return;
        }

        if (targetAmount < 0 || index == denominations.length) {
            return;
        }

        for (int i = 0; i <= counts[index]; i++) {
            for (int j = 0; j < i; j++) {
                currentCombination.addLast(denominations[index]);
            }

            findCombinations(denominations, counts, targetAmount - i * denominations[index], index + 1, currentCombination);

            for (int j = 0; j < i; j++) {
                currentCombination.removeLast();
            }
        }
    }
}