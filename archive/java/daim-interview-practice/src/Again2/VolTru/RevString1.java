package Again2.VolTru;

public class RevString1 {
    public static void main(String[] args) {
        String initialString = "A Quick Brown Fox leaps over a Lazy Dog";
        String revString = "";
        for(int i = initialString.length()-1; i>=0; i--)
        {
            revString = revString + initialString.charAt(i);
        }
        System.out.println(revString);
    }
}
