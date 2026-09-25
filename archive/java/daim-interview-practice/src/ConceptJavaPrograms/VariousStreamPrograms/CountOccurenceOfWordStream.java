package ConceptJavaPrograms.VariousStreamPrograms;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CountOccurenceOfWordStream
{
    public static void main(String[] args) {
        String str = "welcome to code decode and code decode welcome you";
        List<String> listStr = Arrays.asList(str.split(" "));
        System.out.println(listStr);
        Map<String, Long> map = listStr.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        System.out.println(map);
    }
}
