package Amigoscode.Functional;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.*;


public class Coursera_Functional {

    public static void main(String[] args) {
        //Predicate- takes in one argument and returns a result which is boolean
        Predicate<Integer> posNum = (num) -> num>=0;

        //Function - takes in one argument and return a result
        //Function example, uses apply method
        Function<Integer, String> converter = (num) -> Integer.toString(num);
        System.out.println("Length of 235915483 is: " + converter.apply(235915483));

        //Consumer - takes in a single argument, cosumes it and return nothing.
        //Consumer example, uses accept method
        Consumer<String> consumerTest = (s) -> System.out.println(s + " - received argument");
        consumerTest.accept("Testing consumer interface");

        //Supplier - takes no argument, return some result.
        //Supplier example, uses get method
        Supplier<String> sup = () -> "Exploring Function Interface";
        System.out.println(sup.get());
    }
}
