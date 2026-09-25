package mb.com.company;

public class ReverseNumber {
    public static void main(String[] args) {
        int initialNumber = 924837;
        int reversedNumber = 0;
        while(initialNumber!=0){
            int remainder = initialNumber%10;
            reversedNumber = reversedNumber*10 + remainder;
            initialNumber = initialNumber/10;


        }
        System.out.println("Reversed Number is : " +reversedNumber);
    }
}
