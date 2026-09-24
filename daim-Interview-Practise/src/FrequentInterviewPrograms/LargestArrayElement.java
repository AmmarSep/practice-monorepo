package FrequentInterviewPrograms;

// This line declares a public class named LargestArrayElement. 
// In Java, every application must contain a main class that wraps up all the code.
public class LargestArrayElement {

    // This is the main method. This is the entry point for any Java application. 
    // The Java Virtual Machine (JVM) calls the main method when the program starts.
    public static void main(String[] args) {

        // This line declares and initializes an array of integers named arrayEle.
        // The array contains 11 elements.
        int[] arrayEle = new int[]{324,34,324,53,265,2,234,234,52,231,325};

        // This line declares an integer variable named max and initializes it with the first element of the array.
        int max = arrayEle[0];

        // This is a for loop that iterates over each element in the array.
        for( int i =0; i<arrayEle.length; i++){

            // This is an if statement that checks if the current array element is greater than max.
            if(arrayEle[i]> max){

                // If the current array element is greater than max, then max is updated with this element.
                max = arrayEle[i];
            }
        }

        // This line prints the largest element in the array to the console.
        System.out.println("The largest element in a Array is :" +max);
    }
}
