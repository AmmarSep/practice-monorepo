package Again2.VolTru;

public class Palindrome1
{
    public static void main(String[] args)
    {
        int given_number = 349;
        int orginal_number = given_number;
        int reversed_number = 0;
        while(given_number != 0)
        {
            reversed_number = reversed_number * 10 + given_number % 10;
            given_number = given_number/10;
        }
        System.out.println(" The reversed number is : " + reversed_number);
        if(reversed_number==orginal_number)
        {
            System.out.println("The given number is palindrome");
        }
        else
        {
            System.out.println("The given number is not palindrome");
        }
    }

}
