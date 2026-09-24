package Fresher;

/**
 * This class demonstrates different ways to generate the Fibonacci series
 * In Fibonacci series, each number is the sum of the two preceding ones
 * Example: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, ...
 */
public class FibonacciSeries {

    // Method 1: Iterative approach using a loop
    public static void printFibonacciIterative(int n) {
        int firstTerm = 0, secondTerm = 1;
        System.out.println("Fibonacci Series (Iterative) up to " + n + " terms:");

        for (int i = 0; i < n; i++) {
            System.out.print(firstTerm + " ");

            int nextTerm = firstTerm + secondTerm;
            firstTerm = secondTerm;
            secondTerm = nextTerm;
        }
        System.out.println();
    }

    // Method 2: Recursive approach
    public static int fibonacciRecursive(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    // Method 3: Using Dynamic Programming for better performance
    public static int fibonacciDP(int n) {
        int[] fib = new int[n + 2];
        fib[0] = 0;
        fib[1] = 1;

        for (int i = 2; i <= n; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }

        return fib[n];
    }

    public static void main(String[] args) {
        int n = 10; // Number of terms to generate

        System.out.println("=== Fibonacci Series Generator ===\n");

        // Using iterative approach
        printFibonacciIterative(n);

        // Using recursive approach
        System.out.println("\nFibonacci Series (Recursive) up to " + n + " terms:");
        for (int i = 0; i < n; i++) {
            System.out.print(fibonacciRecursive(i) + " ");
        }
        System.out.println();

        // Using dynamic programming approach
        System.out.println("\nFibonacci Series (Dynamic Programming) up to " + n + " terms:");
        for (int i = 0; i < n; i++) {
            System.out.print(fibonacciDP(i) + " ");
        }
        System.out.println();

        // Performance comparison
        System.out.println("\n=== Performance Comparison ===");
        int testNumber = 30;

        // Dynamic Programming approach
        long startTime = System.nanoTime();
        int dpResult = fibonacciDP(testNumber);
        long dpTime = System.nanoTime() - startTime;

        // Recursive approach
        startTime = System.nanoTime();
        int recursiveResult = fibonacciRecursive(testNumber);
        long recursiveTime = System.nanoTime() - startTime;

        System.out.println("Fibonacci of " + testNumber);
        System.out.println("Dynamic Programming time: " + dpTime + " ns");
        System.out.println("Recursive time: " + recursiveTime + " ns");
        System.out.println("DP is faster by a factor of: " + (double)recursiveTime/dpTime);
    }
}
