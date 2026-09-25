package FrequentInterviewPrograms;

/**
 * This class generates and prints the Fibonacci series.
 */
public class FabionacciSeries {
    /**
     * The FabionacciSeries class generates and prints the Fibonacci series.
     */


        /**
         * The main method is the entry point of the program.
         * It generates and prints the Fibonacci series.
         *
         * @param args The command-line arguments.
         */
        public static void main(String[] args) {
            // Initialize the first two numbers of the series
            int number1 = 0;
            int number2 = 1;

            // Define the count of numbers to be printed
            int count = 10;

            // Print a message indicating the start of the Fibonacci series
            System.out.println("The Fibonacci series is as follows:");

            // Generate and print the Fibonacci series
            for (int i = 0; i < count; i++) {
                // Calculate the next number in the series
                int number3 = number1 + number2;

                // Update the values of number1 and number2 for the next iteration
                number1 = number2;
                number2 = number3;

                // Print the current number in the series
                System.out.println(number1);
            }
        }
    }
   

