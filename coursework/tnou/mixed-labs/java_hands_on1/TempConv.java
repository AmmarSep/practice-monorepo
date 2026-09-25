import java.util.Scanner;

public class TempConv
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter temp in far: ");
        double far = sc.nextDouble();
        double cel = (far - 32)*5/9;
        System.out.println("The temp in celsius is  "+cel);
    }
}