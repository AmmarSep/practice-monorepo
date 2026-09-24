package PracticePrograms;

public class RemoveWhiteSpaces {
    public static void main(String[] args) {

    String str = "Ammar is not so intelligent as we thought";
    String str2 = str.replaceAll("\\s","_");
        System.out.println(str2);
    }
}
