package PracticePrograms;

public class StringCount {
    public static void main(String[] args) {
        String sampleWord = "How many letters in this sentence";
        int count = 0;

        for(int i=0; i<sampleWord.length(); i++){
            if(sampleWord.charAt(i)!= ' ')
                count ++;
        }
        System.out.println("Total number of character in string: "+count);

    }
}
