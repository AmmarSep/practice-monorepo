package FrequentInterviewPrograms;

public class SumOfArrayEle // This line declares a public class named 'SumOfArrayEle'
{
    public static void main(String[] args) // This is the main method where the execution of the program starts
    {
        int[] arr = new int[]{43,53,90,79,89}; // This line declares an integer array 'arr' and initializes it with the given values

        int sum = 0; // This line declares an integer variable 'sum' and initializes it with 0. This variable will be used to store the sum of the elements of the array

        for(int i = 0; i<= arr.length-1; i++) // This line starts a for loop that iterates over the elements of the array
        {
            sum = sum +arr[i]; // This line adds the current element of the array to 'sum'
        }

        System.out.println("Sum of the array element: "+sum); // This line prints the message "Sum of the array element: " followed by the value of 'sum' to the console
    }
}
