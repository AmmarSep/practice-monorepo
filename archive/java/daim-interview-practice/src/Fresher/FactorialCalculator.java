package Fresher;

/**
 * This class demonstrates different ways to calculate factorial
 * Factorial of n (n!) = n × (n-1) × (n-2) × ... × 1
 * Example: 5! = 5 × 4 × 3 × 2 × 1 = 120
 */
public class FactorialCalculator {

    // Iterative approach
    public static long factorialIterative(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers");
        }
        if (n == 0 || n == 1) {
            return 1;
        }

        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    // Recursive approach
    public static long factorialRecursive(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers");
        }
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * factorialRecursive(n - 1);
    }

    public static void main(String[] args) {
        int[] testNumbers = {0, 1, 5, 7, 10};

        System.out.println("=== Factorial Calculator ===");
        System.out.println("Comparing Iterative vs Recursive approaches\n");

        for (int num : testNumbers) {
            long iterativeResult = factorialIterative(num);
            long recursiveResult = factorialRecursive(num);

            System.out.println("Factorial of " + num + ":");
            System.out.println("  Iterative: " + iterativeResult);
            System.out.println("  Recursive: " + recursiveResult);
            System.out.println("  Match: " + (iterativeResult == recursiveResult));
            System.out.println();
        }

        // Performance comparison for larger numbers
        System.out.println("=== Performance Comparison ===");
        int largeNumber = 15;

        long startTime = System.nanoTime();
        long iterativeResult = factorialIterative(largeNumber);
        long iterativeTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        long recursiveResult = factorialRecursive(largeNumber);
        long recursiveTime = System.nanoTime() - startTime;

        System.out.println("Factorial of " + largeNumber + " = " + iterativeResult);
        System.out.println("Iterative time: " + iterativeTime + " ns");
        System.out.println("Recursive time: " + recursiveTime + " ns");
    }
}