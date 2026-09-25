package Again2.VolTru;

public class SumOfArrayEle1 {
    public static void main(String[] args) {
        int[] arrayElements = new int[]{34,859,32,8,89};
        int sumOfArrayElements = 0;
        for (int i=0; i<arrayElements.length; i++)
        {
            sumOfArrayElements = sumOfArrayElements + arrayElements[i];
        }
        System.out.println(sumOfArrayElements);
        System.out.println(arrayElements.length);
    }

}
