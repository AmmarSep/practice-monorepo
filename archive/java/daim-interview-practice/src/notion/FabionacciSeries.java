package notion;

//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;

public class FabionacciSeries {
    public static void main(String[] args) {
        int number1 = 0;
        int number2= 1;
        int count = 10;
        for(int i=0; i<count; i++){
            int number3 = number1 + number2;
            number1 = number2;
            number2 = number3;
            System.out.println(number1);

        }
    }
//    @Test
//    public void test_JUnit_FabionacciSeries() {
//        System.out.println("This is the testcase in this class");
//        String str1="This is the testcase in this class";
//        assertEquals("This is the testcase in this class", str1);
//    }
}
