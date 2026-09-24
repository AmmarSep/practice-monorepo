package Again2.VolTru;

public class SwapNum1
{
    public static void main(String[] args)
    {
        int a= 8;
        int b= 2;
        System.out.println("Before swapping :" +a +b);
        int temporary = a;
        a=b;
        b=temporary;
        System.out.println("After swapping : " +a +b);
    }
}
