package FrequentInterviewPrograms;

/**
 * The EvenOdd class prints the even and odd numbers from an array.
 */
/**
 * The EvenOdd class prints the even and odd numbers from an array.
 */
public class EvenOdd {
    /**
     * This program prints the even and odd numbers from an array.
     */
  
        /**
         * The main method is the entry point of the program.
         * It initializes an array of integers and prints the even and odd numbers from the array.
         *
         * @param args The command-line arguments passed to the program.
         */
        public static void main(String[] args) {
            // Initialize the array
            int[] arreo = new int[]{7, 3, 6, 334, 75, 78};

            // Print even numbers in the array
            System.out.println("Even numbers in the array: ");
            for (int i = 0; i < arreo.length - 1; i++) {
                if (arreo[i] % 2 == 0) {
                    System.out.println(arreo[i]);
                }
            }

            // Print odd numbers in the array
            System.out.println("Odd numbers in the array: ");
            for (int i = 0; i < arreo.length - 1; i++) {
                if (arreo[i] % 2 != 0) {
                    System.out.println(arreo[i]);
                }
            }
        }
    }
   

