package FrequentInterviewPrograms;

public class SwapNumbers // This line declares a public class named 'SwapNumbers'
{
    public static void main(String[] args) // This is the main method where the execution of the program starts
    {
        int a = 5; // This line declares an integer variable 'a' and initializes it with the value 5

        int b = 8; // This line declares an integer variable 'b' and initializes it with the value 8

        System.out.println(" Result before swapping :" + a + b); // This line prints the message " Result before swapping :" followed by the values of 'a' and 'b' to the console

        int temp = a; // This line declares an integer variable 'temp' and assigns the value of 'a' to it

        a = b; // This line assigns the value of 'b' to 'a'

        b = temp; // This line assigns the value of 'temp' (which is the original value of 'a') to 'b'

        System.out.println(" Result after swapping :" + a + b); // This line prints the message " Result after swapping :" followed by the new values of 'a' and 'b' to the console
    }
}
