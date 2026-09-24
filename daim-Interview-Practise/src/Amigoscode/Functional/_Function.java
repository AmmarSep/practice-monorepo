package Amigoscode.Functional;

import java.util.function.Function;

public class _Function {
    public static void main(String[] args) {
        int increasedNumber = incrementByOne(5);
        System.out.println(increasedNumber);

        int increasedNumberbyFunction = incrementByOneFunction.apply(7);
        System.out.println(increasedNumberbyFunction);

        int addedAndMultipliedNumber = addOneAndMultiply10.apply(6);
        System.out.println(addedAndMultipliedNumber);
    }
    static Function<Integer,Integer> incrementByOneFunction = num -> num +1;
    static Function<Integer,Integer> mulitpyByTenFunction = num -> num * 10;
    static Function<Integer,Integer> addOneAndMultiply10 = incrementByOneFunction.andThen(mulitpyByTenFunction);
    static int incrementByOne(int number){
        return number+1;
    }
}
