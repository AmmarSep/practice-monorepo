package Again2.VolTru;

public class FabionacciSer1 {
    public static void main(String[] args) {
        int num1 = 0;
        int num2 = 1;
        int num3;
        int count = 10;
        System.out.println("The Fabionacci series for 10 numbers is : ");
        for(int i=0; i<count; i++ )
        {
            num3 = num1 + num2;
            num1 = num2;
            num2 = num3;
            System.out.println(+num1);
        }
    }
}
