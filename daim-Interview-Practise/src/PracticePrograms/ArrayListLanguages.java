package PracticePrograms;

import java.util.ArrayList;

public class ArrayListLanguages {
    public static void display(ArrayList<String> allLanguages){
        System.out.println("All the computer languages list: ");
        for(String individualLang : allLanguages)
        {
            System.out.println(individualLang + " ");
        }

    }
    public static void main(String[] args) {
        ArrayList<String> allLanguages = new ArrayList<>();
        allLanguages.add("Java");
        allLanguages.add("Kotlin");
        allLanguages.add("Golang");
        display(allLanguages);
    }

}
