package Test;

import java.util.ArrayList;
import java.util.List;

public class CountAndPrintRepeating {
    public static void main(String[] args) {
        List<String> fruitNames = new ArrayList<String>();
        fruitNames.add("Apple");
        fruitNames.add("Banana");
        fruitNames.add("Apple");
        fruitNames.add("Mango");
        fruitNames.add("Banana");
        fruitNames.add("Mango");
        fruitNames.add("Banana");
        int count = 0;

//        long count = fruitNames.stream().filter(a->a.contains("Apple")).count();
//        long count2 = fruitNames.stream().filter(a->a.contains("Mango")).count();
//        long count3 = fruitNames.stream().filter(a->a.contains("Banana")).count();
    }
}
