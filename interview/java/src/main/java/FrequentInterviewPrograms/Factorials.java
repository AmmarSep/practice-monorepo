package FrequentInterviewPrograms;

/**
 * The Factorials class calculates the factorial of a given number.
 */
public class Factorials {
    /**
        * This is the main method that calculates the factorial of a given number.
        * It initializes the factorialFor variable to 6 and calculates the factorial using a for loop.
        * The factorial is then printed to the console.
        *
        * @param args the command-line arguments
        */
    /**
        * This method calculates the factorial of a given number.
        * 
        * @param args The command-line arguments.
        */
    /**
     * This program calculates the factorial of a given number.
     */
    
        /**
         * The main method is the entry point of the program.
         * It calculates the factorial of a given number and prints the result.
         *
         * @param args The command-line arguments passed to the program.
         */
        public static void main(String[] args) {
            // Initialize the number for which factorial needs to be calculated
            int factorialFor = 6;

            // Initialize the factorial variable to 1
            int factorial = 1;

            // Calculate the factorial using a for loop
            for(int i=1; i<=factorialFor; i++){
                factorial = factorial * i;
            }

            // Print the factorial
            System.out.println(factorial);
        }
    }

