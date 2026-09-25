package FrequentInterviewPrograms;

public class ReverseString // This line declares a public class named 'ReverseString'
{
    public static void main(String[] args) // This is the main method where the execution of the program starts
    {
        String str1 = "Google vs Amazon"; // This line declares a String variable 'str1' and initializes it with the value "Google vs Amazon"

        String revStr = " "; // This line declares a String variable 'revStr' and initializes it with a single space. This variable will be used to store the reversed string

        for(int i=str1.length()-1; i>=0; i--) // This line starts a for loop that iterates from the last character of 'str1' to the first character
        {
            revStr = revStr + str1.charAt(i); // This line appends the character at index 'i' of 'str1' to 'revStr'
        }

        System.out.println(revStr); // This line prints the reversed string to the console
    }
}
