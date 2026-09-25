package Again2.VolTru;

public class CountWords1
{
    public static void main(String[] args)
    {
        String sentence = "  A quick brown fox leaps over a Lazy Dog ";
        String[] words = sentence.trim().split(" ");
        System.out.println("Number of words in the given sentence is : " +words.length);
    }
}
