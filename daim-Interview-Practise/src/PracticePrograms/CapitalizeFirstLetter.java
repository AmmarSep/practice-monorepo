package PracticePrograms;

import java.util.Locale;

public class CapitalizeFirstLetter {
    public static void main(String[] args) {
        String sen = "amr make something";
        String sen2 = sen.substring(0,1);
        String sen3 = sen.substring(1);
        String sen4 = sen2.toUpperCase(Locale.ROOT);
        System.out.println(sen4+sen3);
    }
}
