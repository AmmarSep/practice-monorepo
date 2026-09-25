package mb.com.company;

public class CountWords {
    public static void main(String[] args) {
        String sentence = " A quick brown fox jumps over a lazy dog";
        String sentence2 = sentence.trim().replaceAll(" ", "_");
        String[] wordsWithoutTrim = sentence.split(" ");
        String[] words = sentence.trim().split(" ");
        System.out.println("No.of.words in the sentence is :" +wordsWithoutTrim.length);
        System.out.println("No.of.words in the sentence after trim is : " + words.length);
        System.out.println(sentence2);
    }
}
