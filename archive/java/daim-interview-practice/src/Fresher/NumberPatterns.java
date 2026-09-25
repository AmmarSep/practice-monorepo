package Fresher;

/**
 * This class demonstrates various number pattern programs
 * These are commonly asked in fresher interviews
 */
public class NumberPatterns {

    // Pattern 1: Right-angled triangle with numbers
    public static void pattern1(int rows) {
        System.out.println("Pattern 1 - Right Triangle:");
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Pattern 2: Inverted right triangle
    public static void pattern2(int rows) {
        System.out.println("Pattern 2 - Inverted Triangle:");
        for (int i = rows; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Pattern 3: Floyd's triangle
    public static void pattern3(int rows) {
        System.out.println("Pattern 3 - Floyd's Triangle:");
        int num = 1;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(num + " ");
                num++;
            }
            System.out.println();
        }
        System.out.println();
    }

    // Pattern 4: Pyramid pattern
    public static void pattern4(int rows) {
        System.out.println("Pattern 4 - Pyramid:");
        for (int i = 1; i <= rows; i++) {
            // Print spaces
            for (int j = 1; j <= rows - i; j++) {
                System.out.print(" ");
            }
            // Print numbers
            for (int j = 1; j <= i; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Pattern 5: Diamond pattern
    public static void pattern5(int rows) {
        System.out.println("Pattern 5 - Diamond:");
        // Upper half
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= rows - i; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= i; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
        // Lower half
        for (int i = rows - 1; i >= 1; i--) {
            for (int j = 1; j <= rows - i; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= i; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int rows = 5;

        System.out.println("=== Number Pattern Programs ===");
        System.out.println("Number of rows: " + rows + "\n");

        pattern1(rows);
        pattern2(rows);
        pattern3(rows);
        pattern4(rows);
        pattern5(rows);

        // Multiplication table
        System.out.println("Pattern 6 - Multiplication Table:");
        int tableNumber = 5;
        for (int i = 1; i <= 10; i++) {
            System.out.println(tableNumber + " × " + i + " = " + (tableNumber * i));
        }
    }
}