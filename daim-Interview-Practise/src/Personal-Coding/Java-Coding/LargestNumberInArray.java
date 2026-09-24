//Find the largest number in an array:
//Iterate through the array and keep track of the maximum value.

/**
 * This class finds the largest number in an array of integers.
 */
public class LargestNumberInArray {
    
    /**
     * The main method of the program.
     * It initializes an array of numbers, finds the largest number, and prints it.
     * 
     * @param args The command-line arguments (not used in this program)
     */
    public static void main(String[] args) {
        int[] numbers = {5, 10, 2, 8, 3};
        int max = numbers[0]; // Assume the first element is the maximum
        
        // Iterate through the array to find the maximum number
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > max) {
                max = numbers[i]; // Update the maximum value
            }
        }
        
        System.out.println("The largest number in the array is: " + max);
    }
}
