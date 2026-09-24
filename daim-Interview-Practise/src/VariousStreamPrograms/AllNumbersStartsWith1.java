package VariousStreamPrograms;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AllNumbersStartsWith1
{
    public static void main(String[] args) {
        List<Integer> allNumbers = Arrays.asList(4,76,13,16,98,113,145);
//         allNumbers.stream().map(s->s+"").filter(s->s.startsWith("1")).forEach(System.out::println);
         List<String> number1= allNumbers.stream().map(s->s+"").filter(s->s.startsWith("1")).collect(Collectors.toList());
        number1.forEach(System.out::println);
    }
}
