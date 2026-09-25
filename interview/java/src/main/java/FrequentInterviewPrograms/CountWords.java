package FrequentInterviewPrograms;

/**
 * This class counts the number of words in a given sentence.
 */
public class CountWords {

    /**
     * The main method is the entry point of the program.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        // The sentence to count words from
        String sentence = "A quick brown fox leaps over a lazy dog";

        // Splitting the sentence into words using space as the delimiter
        String[] words = sentence.trim().split(" ");

        // Printing the number of words in the sentence
        System.out.println("No. of words in the sentence: " + words.length);
    }
}

