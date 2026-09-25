public class ReverseNumber1 {
    public static void main(String[] args) {
        int orginalNumber = 23948;
        int reversedNumber = 0;
        while(orginalNumber !=0)
        {
            int remainder = orginalNumber%10;
            reversedNumber = reversedNumber*10 + remainder;
            orginalNumber = orginalNumber/10;
        }
        System.out.println("The reversed number is :" + reversedNumber);
    }
}
