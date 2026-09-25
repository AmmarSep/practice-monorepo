package javaTechie;

import static java.lang.System.*;

interface Calculator {
//    void swithOn();
//    void sum(int input);
    int subtract(int a, int b);
}

 class CalculatorImpl{
    public static void main(String[] args) {
//        Calculator calculator= ()-> out.println("Calculator will switch on");
//        calculator.swithOn();
//        Calculator calculator=(input)-> out.println("Sum is :"+input);
//        calculator.sum(34);
        Calculator calculator = (a,b)-> {
            if (a < b) {
                throw new RuntimeException("The value of a should not be less than b");
            }
            else
            {
                return  a - b;
            }
        };
       out.println(calculator.subtract(1,3));
    }
}
