import java.math.BigInteger;

public class BinaryHeapCount {

    // Function to calculate the factorial of a number
    private static BigInteger factorial(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    // Function to calculate the number of binary heaps on n elements
    private static BigInteger countBinaryHeaps(int n) {
        // Calculate (2n)!
        BigInteger numerator = factorial(2 * n);

        // Calculate (n+1)! * n!
        BigInteger denominator = factorial(n + 1).multiply(factorial(n));

        // Divide (2n)! by (n+1)! * n!
        return numerator.divide(denominator);
    }

    public static void main(String[] args) {
        // Specify the value of n
        int n = 3; // Change this to the desired value

        // Calculate and print the number of binary heaps on n elements
        BigInteger result = countBinaryHeaps(n);
        System.out.println("Number of binary heaps on " + n + " elements: " + result);
    }
}
