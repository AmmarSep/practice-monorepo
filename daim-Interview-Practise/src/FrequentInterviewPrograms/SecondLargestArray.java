package FrequentInterviewPrograms;

public class SecondLargestArray // This line declares a public class named 'SecondLargestArray'
{
    public static void main(String[] args) // This is the main method where the execution of the program starts
    {
        int arr[] = {14, 46, 47, 86, 92, 52, 48, 36, 66, 85}; // This line declares an integer array 'arr' and initializes it with the given values

        int largest = arr[0]; // This line declares an integer variable 'largest' and initializes it with the first element of the array. This variable will be used to store the largest number in the array

        int secondLargest = arr[0]; // This line declares an integer variable 'secondLargest' and initializes it with the first element of the array. This variable will be used to store the second largest number in the array

        System.out.println("The given array is:"); // This line prints the message "The given array is:" to the console

        for (int i = 0; i < arr.length; i++) // This line starts a for loop that iterates over the elements of the array
        {
            System.out.print(arr[i] + "\t"); // This line prints the current element of the array followed by a tab space
        }

        for (int i = 0; i < arr.length; i++) // This line starts a second for loop that iterates over the elements of the array to find the largest and second largest numbers
        {
            if (arr[i] > largest) // This line checks if the current element of the array is greater than 'largest'
            {
                secondLargest = largest; // If the condition is true, 'largest' is assigned to 'secondLargest'
                largest = arr[i]; // The current element of the array is assigned to 'largest'

            } 
            else if (arr[i] > secondLargest) // This line checks if the current element of the array is greater than 'secondLargest'
            {
                secondLargest = arr[i]; // If the condition is true, the current element of the array is assigned to 'secondLargest'
            }
        }

        System.out.println("\nSecond largest number is:" + secondLargest); // This line prints the message "Second largest number is:" followed by the value of 'secondLargest' to the console
    }
}
