// Program 13: Program for checking whether a given number is palindrome or not (Concept: Abstract Class)

import java.util.Scanner;

abstract class NumberChecker {
    abstract boolean isPalindrome(int number);
    abstract void displayResult(int number);
}

class PalindromeChecker extends NumberChecker {
    @Override
    boolean isPalindrome(int number) {
        int original = number;
        int reverse = 0;

        while (number != 0) {
            int digit = number % 10;
            reverse = reverse * 10 + digit;
            number /= 10;
        }

        return original == reverse;
    }

    @Override
    void displayResult(int number) {
        if (isPalindrome(number)) {
            System.out.println(number + " is a palindrome number.");
        } else {
            System.out.println(number + " is not a palindrome number.");
        }
    }
}

public class P13_PalindromeNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int number = scanner.nextInt();

        PalindromeChecker checker = new PalindromeChecker();
        checker.displayResult(number);

        scanner.close();
    }
}
