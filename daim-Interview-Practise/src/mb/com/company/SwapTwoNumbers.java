package mb.com.company;

public class SwapTwoNumbers {
    public static void main(String[] args) {
        int a = 35;
        int b = 83;
        System.out.println("Number before swapping " +a +b);
        int temp =  a;
        a=b;
        b=temp;
        System.out.println("Number after swapping " +a +b);
    }
}
