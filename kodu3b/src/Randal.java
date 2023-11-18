import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class Randal{

    public static void main(String[] args) {
        int n = 30; // 25 EUR in cents

        int ways = countWaysToPay(n);
        System.out.println("Number of ways to pay: " + ways);
    }

    public static int countWaysToPay(int n) {
        int[] coinDenominations = {10, 20, 50, 100, 200};
        int[] coinCounts = {15, 15, 15, 10, 10};

        Deque<Integer> amountStack = new ArrayDeque<>();
        Deque<Integer> coinIndexStack = new ArrayDeque<>();
        int ways = 0;

        amountStack.push(n);
        coinIndexStack.push(0);

        while (!amountStack.isEmpty()) {
            System.out.println(amountStack + " " + coinIndexStack);
            int currentAmount = amountStack.pop();
            int currentIndex = coinIndexStack.pop();

            if (currentAmount == 0) {
                ways++;
                System.out.println(ways);
            } else if (currentIndex < coinDenominations.length) {
                for (int count = 0; count <= coinCounts[currentIndex]; count++) {
                    int remainingAmount = currentAmount - count * coinDenominations[currentIndex];
                    if (remainingAmount >= 0) {
                        amountStack.push(remainingAmount);
                        coinIndexStack.push(currentIndex + 1);
                    }
                }
            }
        }

        return ways;
    }
}
