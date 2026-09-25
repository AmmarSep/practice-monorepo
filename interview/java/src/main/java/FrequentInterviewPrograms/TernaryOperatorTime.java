package FrequentInterviewPrograms;

public class TernaryOperatorTime // This line declares a public class named 'TernaryOperatorTime'
{
    public static void main(String[] args) // This is the main method where the execution of the program starts
    {
        int time = 13; // This line declares an integer variable 'time' and initializes it with the value 13

        // This line declares a String variable 'result' and uses a ternary operator to assign a value to it
        // The ternary operator checks if 'time' is less than 12. If it is, "Good Morning" is assigned to 'result'. If it's not, "Good Afternoon" is assigned to 'result'
        String result = time<12 ? "Good Morning" : "Good Afternoon"; 

        System.out.println(result); // This line prints the value of 'result' to the console
    }
}