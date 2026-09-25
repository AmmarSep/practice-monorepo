public class ReverseString {
    public static void main(String[] args) {
        String originalString  = "oliver reddy khan";
        String reversedString = "";

        for (int i= originalString.length()-1; i>=0; i-- )
        {
            reversedString= reversedString + originalString.charAt(i);
        }
        System.out.println("The reversed String is : " + reversedString);
    }
}
