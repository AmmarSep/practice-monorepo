package Again2.VolTru;

public class PrimeOrNot1 {
    public static void main(String[] args) {
        int num = 1;
        int count = 0;
        if(num>1)
        {
            for(int i =1; i<=num; i++)
            {
                if(num % i == 0)
                {
                    count ++;
                }
            }
            if (count == 2)
            {
                System.out.println("The given number is prime number");
            }
            if (count != 2)
            {
                System.out.println("The given number is not a prime number");
            }
        }
        else
        {
            System.out.println("The number 1 is prime number");
        }
    }
}
