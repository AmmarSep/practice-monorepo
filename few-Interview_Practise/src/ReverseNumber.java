public class ReverseNumber {
    public static void main(String[] args) {
        int Number = 498;
        int reverse = 0;
        while(Number !=0)
        {
            int remainder = Number%10;
            reverse = reverse *10 + remainder;
            Number = Number/10;
        }
        System.out.println("The reversed number is :" +reverse);

    }
}
