package Fresher;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Program to check if a number is prime and find all prime numbers up to a limit
 */
public class PrimeNumberChecker {

    // Method to check if a number is prime using basic approach
    public static boolean isPrime(int number) {
        // Handle edge cases
        if (number <= 1) {
            return false;
        }
        if (number <= 3) {
            return true;
        }
        if (number % 2 == 0 || number % 3 == 0) {
            return false;
        }

        // Check divisibility by all numbers of form 6k ± 1 up to sqrt(number)
        for (int i = 5; i * i <= number; i += 6) {
            if (number % i == 0 || number % (i + 2) == 0) {
                return false;
            }
        }

        return true;
    }

    // Method to find all prime numbers up to a limit using Sieve of Eratosthenes
    public static List<Integer> findPrimesUpTo(int limit) {
        // Create a boolean array for marking composite numbers
        boolean[] isComposite = new boolean[limit + 1];
        List<Integer> primes = new ArrayList<>();

        // Handle edge cases
        if (limit < 2) {
            return primes;
        }

        // 0 and 1 are not prime
        isComposite[0] = isComposite[1] = true;

        // Add 2 as the first prime number
        primes.add(2);

        // Mark all even numbers as composite (except 2)
        for (int i = 4; i <= limit; i += 2) {
            isComposite[i] = true;
        }

        // Check odd numbers starting from 3
        for (int i = 3; i <= limit; i += 2) {
            if (!isComposite[i]) {
                primes.add(i);

                // Mark all multiples of i as composite
                for (int j = i * i; j <= limit && j > 0; j += i * 2) { // j > 0 to prevent overflow
                    isComposite[j] = true;
                }
            }
        }

        return primes;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Prime Number Checker and Generator ===\n");

        // Check if a specific number is prime
        System.out.print("Enter a number to check if it's prime: ");
        int numberToCheck = scanner.nextInt();

        boolean isPrimeResult = isPrime(numberToCheck);
        System.out.println(numberToCheck + " is " + (isPrimeResult ? "prime" : "not prime") + "\n");

        // Find prime numbers up to a limit
        System.out.print("Enter a limit to find all prime numbers up to that limit: ");
        int limit = scanner.nextInt();

        long startTime = System.nanoTime();
        List<Integer> primes = findPrimesUpTo(limit);
        long endTime = System.nanoTime();

        System.out.println("\nFound " + primes.size() + " prime numbers up to " + limit);

        // Print primes in rows of 10
        if (primes.size() <= 100) {
            int count = 0;
            for (int prime : primes) {
                System.out.print(prime + "\t");
                count++;
                if (count % 10 == 0) {
                    System.out.println();
                }
            }
            if (count % 10 != 0) {
                System.out.println();
            }
        } else {
            System.out.println("First 10 primes: " + primes.subList(0, 10) + "...");
            System.out.println("Last 10 primes: ..." + primes.subList(primes.size() - 10, primes.size()));
        }

        System.out.println("\nTime taken to find all primes: " + ((endTime - startTime) / 1_000_000.0) + " ms");

        // Find prime factors of a number
        System.out.print("\nEnter a number to find its prime factors: ");
        int numberToFactorize = scanner.nextInt();
        System.out.println("Prime factors of " + numberToFactorize + ": " + findPrimeFactors(numberToFactorize));

        scanner.close();
    }

    // Method to find prime factors of a number
    public static List<Integer> findPrimeFactors(int number) {
        List<Integer> factors = new ArrayList<>();

        // Handle negative numbers
        if (number < 0) {
            factors.add(-1);
            number = -number;
        }

        // Handle edge cases
        if (number <= 1) {
            factors.add(number);
            return factors;
        }

        // Check divisibility by 2
        while (number % 2 == 0) {
            factors.add(2);
            number /= 2;
        }

        // Check divisibility by odd numbers starting from 3
        for (int i = 3; i <= Math.sqrt(number); i += 2) {
            while (number % i == 0) {
                factors.add(i);
                number /= i;
            }
        }

        // If number is a prime greater than 2
        if (number > 2) {
            factors.add(number);
        }

        return factors;
    }
}
