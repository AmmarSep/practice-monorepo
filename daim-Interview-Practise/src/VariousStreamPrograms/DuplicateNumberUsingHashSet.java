package VariousStreamPrograms;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DuplicateNumberUsingHashSet
{
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(4,76,13,16,98,113,145,80,76,4,98,113,80,4);
        Set<Integer> set = new HashSet<>();
        list.stream().filter(x->!set.add(x)).collect(Collectors.toSet()).forEach(System.out::println);
        System.out.println("\nLimit\n");
        list.stream().limit(3).forEach(System.out::println);
        System.out.println("\nSkip\n");
        list.stream().skip(2).forEach(System.out::println);
    }
}
