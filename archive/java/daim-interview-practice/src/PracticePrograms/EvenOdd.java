package PracticePrograms;

public class EvenOdd {
    public static void main(String[] args) {
        int[] arreo =  new int[]{7,3,6,334,75,78};
        System.out.println("Even number in a array: ");
        for(int i=0; i<arreo.length-1; i++)
        {
            if(arreo[i]%2==0)
                System.out.println(arreo[i]);
        }
        System.out.println("Odd number in a array: ");
        for(int i=0; i< arreo.length-1; i++)
        {
            if(arreo[i]%2!=0)
                System.out.println(arreo[i]);
        }
    }
}
