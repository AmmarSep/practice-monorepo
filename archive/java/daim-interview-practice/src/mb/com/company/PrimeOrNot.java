package mb.com.company;

public class PrimeOrNot {
    public static void main(String[] args) {
        int numberTobeChecked = 19;
        int count =0;
        if(numberTobeChecked>1){
            for(int i = 1; i<=numberTobeChecked; i++){
                if(numberTobeChecked%i == 0)
                    count++;
            }
            if(count ==2){
                System.out.println("This is a prime number");
            }else
            {
                System.out.println("This is not a prime number as factor is more than 2");
            }
        }
        else
            System.out.println("Not a prime number since less than 1 or 1");
    }
}
