package FrequentInterviewPrograms;

public class PrimeOrNot // This line declares a public class named 'PrimeOrNot'
{
    public static void main(String[] args) // This is the main method where the execution of the program starts
    {
        int numpr = 15; // This line declares an integer variable 'numpr' and initializes it with the value 15

        int count = 0; // This line declares an integer variable 'count' and initializes it with the value 0. This variable will be used to count the number of factors of 'numpr'

        if (numpr > 1) // This line checks if 'numpr' is greater than 1. A prime number is always greater than 1
        {
            for (int i = 1; i <= numpr; i++) // This line starts a for loop that iterates from 1 to 'numpr'
            {
                if (numpr % i == 0) // This line checks if 'numpr' is divisible by 'i'. If it is, 'i' is a factor of 'numpr'
                    count++; // If 'numpr' is divisible by 'i', 'count' is incremented by 1
            }
            if (count == 2) // This line checks if 'count' is equal to 2. A prime number has exactly two distinct positive divisors: 1 and itself
            {
                System.out.println("This number is a prime number"); // If 'count' is equal to 2, it prints that 'numpr' is a prime number
            } 
            else 
            {
                System.out.println("This number is not a prime number"); // If 'count' is not equal to 2, it prints that 'numpr' is not a prime number
            }
        } 
        else 
        {
            System.out.println("Not a prime number"); // If 'numpr' is not greater than 1, it prints that 'numpr' is not a prime number
        }
    }
}

