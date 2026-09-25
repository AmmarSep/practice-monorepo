// Program 6: Program for find whether a given number is prime or not

import java.util.Scanner;

public class P6_PrimeNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int number = scanner.nextInt();

        boolean isPrime = true;

        if (number <= 1) {
            isPrime = false;
        } else {
            for (int i = 2; i <number; i++) {
                if (number % i == 0) {
                    isPrime = false;
                    break;
                }
            }
        }

        if (isPrime) {
            System.out.println(number + " is a prime number.");
        } else {
            System.out.println(number + " is not a prime number.");
        }

        scanner.close();
    }
}

//Absolutely! You can check more numbers if the square
//  root concept is confusing. Here are simpler
//  alternatives:
//
//  Option 1: Check up to number / 2
//
//  for (int i = 2; i <= number / 2; i++) {
//      if (number % i == 0) {
//          isPrime = false;
//          break;
//      }
//  }
//  Why this works: No number greater than number/2 can
//  divide into number evenly (except number itself).
//
//  Example: For 17, you check 2 through 8 instead of just 2
//   through 4.
//
//  Option 2: Check up to number - 1 (Most Simple)
//
//  for (int i = 2; i < number; i++) {
//      if (number % i == 0) {
//          isPrime = false;
//          break;
//      }
//  }
//  Why this works: You're literally checking every possible
//   divisor.
//
//  Example: For 17, you check 2, 3, 4, 5, 6... all the way
//  to 16.
//
//  Comparison:
//
//  For number = 100:
//  - With √: Check 2 to 10 (10 checks) ← Fastest
//  - With /2: Check 2 to 50 (49 checks)
//  - With -1: Check 2 to 99 (98 checks) ← Slowest but
//  simplest to understand
//
//  All three are correct! The square root version is just
//  an optimization to make it faster for large numbers. Use
//   whichever makes sense to you!
//
//  Would you like me to modify your code to use one of the
//  simpler approaches?
