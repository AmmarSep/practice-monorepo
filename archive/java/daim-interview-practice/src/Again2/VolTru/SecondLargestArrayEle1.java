package Again2.VolTru;

public class SecondLargestArrayEle1 {
    public static void main(String[] args)
    {
        int[] arrayElements = new int[]{34,90, 859, 32, 8, 89,91};
        int maxArrayElement = arrayElements[0];
        int secondLargestArrayElement = arrayElements[0];
        for (int i = 0; i < arrayElements.length; i++) {
            if (arrayElements[i] > maxArrayElement)
            {
                secondLargestArrayElement = maxArrayElement;
                maxArrayElement = arrayElements[i];
            }
            else if (arrayElements[i]> secondLargestArrayElement)
            {
                secondLargestArrayElement = arrayElements[i];
            }
            System.out.println("Second Largest element inside loop " +secondLargestArrayElement);
            System.out.println("Largest element inside loop " +maxArrayElement);
        }
        System.out.println(secondLargestArrayElement);
    }
}
