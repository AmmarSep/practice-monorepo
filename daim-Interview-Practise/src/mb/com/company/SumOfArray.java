package mb.com.company;

public class SumOfArray {
    public static void main(String[] args) {
        int[] arrayElements = new int[]{38,83,92,97,93};
        int sum = 0;
        for (int i = 0; i < arrayElements.length; i++)
        {
            sum = sum + arrayElements[i];
        }
        System.out.println(sum);

    }
}
