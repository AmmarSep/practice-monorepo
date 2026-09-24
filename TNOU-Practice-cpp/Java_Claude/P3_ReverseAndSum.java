// Program 3: Program for reverse and finding sum of individual digits of a given number

import java.util.Scanner;

public class P3_ReverseAndSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int number = scanner.nextInt();

        int original = number;
        int reverse = 0;
        int sum = 0;

        while (number != 0) {
            int digit = number % 10;
            reverse = reverse * 10 + digit;
            sum += digit;
            number /= 10;
        }

        System.out.println("Original number: " + original);
        System.out.println("Reversed number: " + reverse);
        System.out.println("Sum of digits: " + sum);

        scanner.close();
    }
}
