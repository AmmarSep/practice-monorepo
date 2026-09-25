package Again2.VolTru;

public class MaxArrayEle1 {
    public static void main(String[] args)
    {
        int[] arrayElements = new int[]{90,34, 859, 32, 8, 89};
        int maxArrayElement = arrayElements[0];
        for (int i=0; i<arrayElements.length; i++)
        {
            if(arrayElements[i]> maxArrayElement)
            {
                maxArrayElement = arrayElements[i];
            }
            System.out.println("Largest element inside loop " +maxArrayElement);
        }
        System.out.println(maxArrayElement);
    }
}
