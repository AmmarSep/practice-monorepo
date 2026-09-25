package VariousStreamPrograms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DuplicateElementInArray
{
    public static void main(String[] args)
    {
        List<String> countryNames = new ArrayList<>();
        countryNames.add("India");
        countryNames.add("Malaysia");
        countryNames.add("Singapore");
        countryNames.add("Qatar");
        countryNames.add("Malaysia");
        countryNames.add("Qatar");
        countryNames.add("France");
        countryNames.add("Singapore");
        System.out.println("Duplicate countries are : \n");
        Set<String> duplicateCountry = countryNames.stream().filter(country-> Collections.frequency(countryNames,country)>1).collect(Collectors.toSet());
        duplicateCountry.forEach(System.out::println);
        long count = duplicateCountry.stream().count();
        System.out.println("\nNumber of duplicate countries :\n" + count);
    }
}
