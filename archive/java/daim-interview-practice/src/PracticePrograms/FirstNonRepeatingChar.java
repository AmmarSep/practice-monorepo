package PracticePrograms;

public class FirstNonRepeatingChar {
    public static void main(String[] args) {
        String inputSample = "abcdcaf";
        for(char i : inputSample.toCharArray()){
            if(inputSample.indexOf(i)==inputSample.lastIndexOf(i)){
                System.out.println("The first non-repeating char is : " +i);
                System.out.println("The index of the first non-repeating char : " +inputSample.indexOf(i));
                break;

            }
        }
    }

}
