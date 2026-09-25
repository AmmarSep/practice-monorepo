package Test;

public class DuplicateChars {
    public static void main(String[] args) {
        String sentence = "a quick brown fox leaps over a Lazy dog";
        char[] chars = sentence.toCharArray();
        System.out.println("Duplicate character in the sentence are below : ");
        for(int i = 0; i<sentence.length(); i++)
        {
            for(int j = i+1; j<sentence.length();j++)
            {
                if (chars[i] == chars[j])
                {
                    System.out.println(chars[j]);
                }
            }
        }
    }
}

