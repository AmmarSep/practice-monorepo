package ConceptJavaPrograms.VariousStreamPrograms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// using Stream.filter() and Collections.frequency() methods
public class FindDuplicatesUsingFilterAndCollectionFrequency {

    public static void main(String[] args) {

        // 1. list of Strings
        List<String> companies = new ArrayList<String>();


        // 1.1 add string elements to List
        companies.add("Meta");
        companies.add("Apple");
        companies.add("Amazon");
        companies.add("Netflix");
        companies.add("Meta"); // duplicate
        companies.add("Google");
        companies.add("Apple"); // duplicate


        // 1.2 print original List to console
        System.out.println("1. Original List with duplicates : \n");
        companies.forEach(System.out::println);


        // 2. get unique elements after removing duplicates
        Set<String> distinctCompanies = companies
                .stream()
                .collect(Collectors.toSet());


        // 2.1 print unique elements
        System.out.println("\n2. Unique elements : \n");
        distinctCompanies.forEach(System.out::println);


        // 3. get duplicate elements
        Set<String> duplicateCompanies = companies
                .stream()
                .filter(company -> Collections.frequency(companies, company) > 1)
                .collect(Collectors.toSet());


        // 3.1 print duplicate elements
        System.out.println("\n3. Duplicate elements : \n");
        duplicateCompanies.forEach(System.out::println);
    }
}
