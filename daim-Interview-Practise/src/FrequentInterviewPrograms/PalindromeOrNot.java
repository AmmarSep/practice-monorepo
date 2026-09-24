package FrequentInterviewPrograms;

import java.util.Scanner;

/**
 * This class checks whether a given number is a palindrome or not.
 */
public class PalindromeOrNot // This is the declaration of the class named 'PalindromeOrNot'
{
    public static void main(String[] args) // This is the main method where the execution of the program starts
    {
        Scanner sc = new Scanner(System.in); // This line creates a Scanner object 'sc' which is used to get the input from the user

        System.out.println("Enter the number:"); // This line prints the message "Enter the number:" to the console

        int num = sc.nextInt(); // This line reads the integer input given by the user and stores it in the variable 'num'

        int org_num = num; // This line stores the original number in the variable 'org_num' for later comparison

        int rev = 0; // This line initializes the variable 'rev' which will be used to store the reversed number

        while(num != 0) // This loop continues until 'num' becomes 0
        {
            rev= rev*10 + num%10; // This line calculates the reverse of the number
            num = num/10; // This line removes the last digit from 'num'

        }
        if(rev==org_num) // This line checks if the reversed number is equal to the original number
        {
            System.out.println("The given number is Palindrome number"); // If the condition is true, it prints that the number is a palindrome
        }
        else
        {
            System.out.println(org_num+" is not a Palindrome number"); // If the condition is false, it prints that the number is not a palindrome
        }
    }
}