package mb.com.company;

public class RemoveWhiteSpaces {
    public static void main(String[] args) {
        String str1 = "The quick brown fox jumps over a lazy dog";
        String str2 = str1.replaceAll("\\s", "_");
        System.out.println(str2);
    }
}
