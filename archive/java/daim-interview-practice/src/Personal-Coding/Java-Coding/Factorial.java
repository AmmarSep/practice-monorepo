//Factorial of a number
//Calculate the factorial of a given number using recursion or iteration
//java
//factorial
//recursion
//iteration
//factorial of a number

import java.util.Scanner;

public class Factorial {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number");
        int num = sc.nextInt();
        System.out.println("Factorial of " + num + " is " + factorial(num));
    }

    public static int factorial(int n) {
        if (n == 0) {
            return 1;
        }
        return n * factorial(n - 1);
    }
}