package ConceptJavaPrograms;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaSortedLang {
    public static void main(String[] args) {
        ArrayList<String> allLang = new ArrayList<>();
        allLang.add("tamil");
        allLang.add("arab");
        allLang.add("marathi");
        allLang.add("malay");
        allLang.add("hindi");
        allLang.add("malayalam");

        System.out.println(allLang);
List<String> sortedLang = allLang.stream().sorted().filter(s -> s.startsWith("m")).collect(Collectors.toList());
        System.out.println(sortedLang);
        allLang.replaceAll(a-> a.toUpperCase());
        System.out.println("Updated Lang :" +allLang);
    }
}
