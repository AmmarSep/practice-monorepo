package Amigoscode;

public class EnhancedForLoop {
    public static void main(String[] args) {
        int[] numbers = {2,0,3,5,6,123,65432};
        for(int i = 0; i<numbers.length; i++){
            System.out.println(numbers[i++]);
        }
        System.out.println("Enhanced For Loop");
        for(int num:numbers){
            System.out.println(num);
        }
    }
}
