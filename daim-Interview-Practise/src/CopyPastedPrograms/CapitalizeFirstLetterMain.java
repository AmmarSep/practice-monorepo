package CopyPastedPrograms;

public class CapitalizeFirstLetterMain {
    public static void main(String[] args) {

        // create a string
        String name = "amr get ready";
        System.out.println("Original String: " + name);
        // get First letter of the string
        String firstLetStr = name.substring(0,2);
        // Get remaining letter using substring
        String remLetStr = name.substring(2);

        // convert the first letter of String to uppercase
        firstLetStr = firstLetStr.toUpperCase();

        // concantenate the first letter and remaining string
        String firstLetterCapitalizedName = firstLetStr + remLetStr;
        System.out.println("String with first letter as Capital: " + firstLetterCapitalizedName);

    }
}
