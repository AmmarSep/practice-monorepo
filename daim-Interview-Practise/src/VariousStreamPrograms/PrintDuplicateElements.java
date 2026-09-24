package VariousStreamPrograms;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PrintDuplicateElements {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(34,53,34,34,23,34,65,32,64,23,34,64,62);
        Set<Integer> set = new HashSet<>();
        list.stream().filter(x-> !set.add(x)).collect(Collectors.toSet()).forEach(x-> System.out.println(x));
    }
}
