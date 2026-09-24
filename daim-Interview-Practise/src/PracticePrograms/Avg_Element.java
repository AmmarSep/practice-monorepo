package PracticePrograms;
public class Avg_Element {
    public static void main(String[] args) {
        int[] arry = new int[]{243,34,987,839,43,435,355,343,19};
        int max = arry[0];
        int min = arry[0];
        int length = arry.length;
        int sum = 0;
        for (int i =0; i<arry.length; i++)
        {
            if(arry[i]>max)
            max = arry[i];

            if(arry[i]<min)
                min = arry[i];

            sum += arry[i];

        }
        double Average = (sum/length);
        System.out.println("Maximum element in array: "
                +max);
        System.out.println("Minimum element in array: "
                +min);
        System.out.println("Sum of the element is array:"
                +sum);
        System.out.println("Average element in array:"
                +Average);

    }
}
