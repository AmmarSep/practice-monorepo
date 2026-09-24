package mb.com.company;

public class CountDigits {
    public static void main(String[] args) {
        int count = 0;
        int number = 98234;
        while(number>0){
            number = number/10;
//            count++;
            count = count +1;
        }
        System.out.println(count);
    }
}
