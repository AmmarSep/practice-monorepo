package Again2.VolTru;

public class EvenOddOfArr1
{
    public static void main(String[] args) {
        int[] arrayElements = new int[]{34,90, 859, 32, 8, 89,91};
        System.out.println("Even numbers in array : ");
        for (int i = 0; i < arrayElements.length; i++)
        {
            if(arrayElements[i]%2 == 0){
                System.out.println(arrayElements[i]);
            }
        }
        System.out.println("Odd numbers in array : ");
        for (int i = 0; i < arrayElements.length; i++)
        {
            if(arrayElements[i]%2 != 0){
                System.out.println(arrayElements[i]);
            }
        }
    }
}
