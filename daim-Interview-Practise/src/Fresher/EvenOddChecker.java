package Fresher;

/**
 * This class demonstrates different ways to check if a number is even or odd
 */
public class EvenOddChecker {

    // Method 1: Using modulus operator
    public static boolean isEvenUsingModulus(int number) {
        return number % 2 == 0;
    }

    // Method 2: Using bitwise AND operator (more efficient)
    public static boolean isEvenUsingBitwise(int number) {
        return (number & 1) == 0;
    }

    // Method 3: Using division and multiplication
    public static boolean isEvenUsingDivision(int number) {
        return (number / 2) * 2 == number;
    }

    public static void main(String[] args) {
        int[] testNumbers = {0, 1, 2, 15, 20, 100, 101, -5, -8};

        System.out.println("=== Even/Odd Number Checker ===");
        System.out.println("Testing different approaches:\n");

        for (int num : testNumbers) {
            boolean modulus = isEvenUsingModulus(num);
            boolean bitwise = isEvenUsingBitwise(num);
            boolean division = isEvenUsingDivision(num);

            System.out.println("Number: " + num);
            System.out.println("  Modulus method: " + (modulus ? "Even" : "Odd"));
            System.out.println("  Bitwise method: " + (bitwise ? "Even" : "Odd"));
            System.out.println("  Division method: " + (division ? "Even" : "Odd"));
            System.out.println("  All methods agree: " + (modulus == bitwise && bitwise == division));
            System.out.println();
        }

        // Performance comparison
        System.out.println("=== Performance Comparison ===");
        int iterations = 1000000;
        int testNumber = 12345;

        // Modulus approach
        long startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            isEvenUsingModulus(testNumber);
        }
        long modulusTime = System.nanoTime() - startTime;

        // Bitwise approach
        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            isEvenUsingBitwise(testNumber);
        }
        long bitwiseTime = System.nanoTime() - startTime;

        System.out.println("Modulus approach: " + modulusTime + " ns");
        System.out.println("Bitwise approach: " + bitwiseTime + " ns");
        System.out.println("Bitwise is " + (double)modulusTime/bitwiseTime + "x faster");
    }
}