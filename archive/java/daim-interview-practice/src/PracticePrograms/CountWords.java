package PracticePrograms;


public class CountWords {
    public static void main(String[] args) {
        String sentence = "A quick brown fox leaps over a lazy dog";
        String[] words = sentence.trim().split(" ");
        System.out.println("Number of words in the sentence : " +words.length);
        System.out.println("The words in the sentence are :");
        for(int i =0; i< words.length; i++){
            System.out.println(words[i]);
        }
    }
}
