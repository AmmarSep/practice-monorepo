package mb.com.company;

public class ReverseString {
    public static void main(String[] args) {
        String originalString = "ubsc io";
        String reverseString = "";
        for(int i = originalString.length()-1; i>=0; i--){
            reverseString = reverseString +originalString.charAt(i);
        }
        System.out.println(reverseString);
    }
}
