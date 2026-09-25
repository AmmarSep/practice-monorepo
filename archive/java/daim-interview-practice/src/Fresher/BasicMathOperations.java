package Fresher;

/**
 * This class demonstrates basic mathematical operations in Java
 */
public class BasicMathOperations {
    public static void main(String[] args) {
        int a = 25;
        int b = 10;

        System.out.println("=== Basic Math Operations ===");
        System.out.println("Number 1: " + a);
        System.out.println("Number 2: " + b);
        System.out.println();

        // Basic arithmetic operations
        System.out.println("Addition: " + a + " + " + b + " = " + (a + b));
        System.out.println("Subtraction: " + a + " - " + b + " = " + (a - b));
        System.out.println("Multiplication: " + a + " * " + b + " = " + (a * b));
        System.out.println("Division: " + a + " / " + b + " = " + (a / b));
        System.out.println("Modulus: " + a + " % " + b + " = " + (a % b));

        // Demonstrating integer vs float division
        System.out.println("\n=== Division Examples ===");
        System.out.println("Integer division: " + a + " / " + b + " = " + (a / b));
        System.out.println("Float division: " + a + " / " + b + " = " + ((float)a / b));

        // Using Math class methods
        System.out.println("\n=== Math Class Methods ===");
        System.out.println("Square root of " + a + " = " + Math.sqrt(a));
        System.out.println("Power: " + a + "^2 = " + Math.pow(a, 2));
        System.out.println("Absolute value of -15 = " + Math.abs(-15));
        System.out.println("Maximum of " + a + " and " + b + " = " + Math.max(a, b));
        System.out.println("Minimum of " + a + " and " + b + " = " + Math.min(a, b));
    }
}