package ConceptJavaPrograms;

public class Except {
    public static void main(String[] args) {
        try {
            int d =1;
            int n = 20;
            int fraction = n/d;
            System.out.println(fraction);
            int g[] = {1};
            g[20]=100;

        }
        catch(ArithmeticException e)
        {
            System.out.println("In the catch block due to Exception = " +e);
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("In the catch block due to Exception = " +e);
        }
        System.out.println("End of Main");
    }
}
