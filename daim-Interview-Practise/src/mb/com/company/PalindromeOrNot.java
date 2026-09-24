package mb.com.company;

public class PalindromeOrNot {
    public static void main(String[] args) {
        int givenNumber = 3443;
        int originalNumber = givenNumber;
        int reversedNumber = 0;
        while(givenNumber != 0){
            int remainder = givenNumber%10;
            reversedNumber = reversedNumber*10 + remainder;
            givenNumber = givenNumber/10;
        }
        if(reversedNumber == originalNumber){
            System.out.println("The given number is Palindrome");
        }
        else
        {
            System.out.println("The given number is not a Palindrome Number");
        }
    }
}
