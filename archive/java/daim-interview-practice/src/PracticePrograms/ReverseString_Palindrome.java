package PracticePrograms;


public class ReverseString_Palindrome
{
    public static void main(String[] args) {
        String string = "Dreambig";
        //Stores the reverse of given string
        String reversedStr = "";

        //Iterate through the string from last and add each character to variable reversedStr
        for(int i = string.length()-1; i >= 0; i--){
            reversedStr = reversedStr + string.charAt(i);
        }

        System.out.println("Original string: " + string);
        //Displays the reverse of given string
        System.out.println("Reverse of given string: " + reversedStr);
    }
}


/*public class ReverseString_Palindrome {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your String: ");
        String stri = sc.next();
        String str = "ABCBD";
        String rev = "";
        String revi = "";
        String org_stri = stri;
        int len = str.length();
        int leni = stri.length();
        for(int i=len-1;i>=0;i--)
        {
            rev = rev + str.charAt(i);
        }
        for(int i=leni-1;i>=0;i--)
        {
            revi = revi + stri.charAt(i);
        }
        if (org_stri.equals(revi))
        {
            System.out.println("The typed string is palindrome");
        }
        else
        {
            System.out.println(org_stri+ " is not a palindrome");
        }
        System.out.println("Reversed string : " +rev);
        System.out.println("Reversed text of entered String is :" +revi);
    }

}*/

//public class ReverseNumber
//{
//    public static void main(String[] args) {
//        int num = 23947;
//        int rev  = 0;
//        while(num>0)
//        {
//            rev =rev*10 + num%10;
//            num = num/10;
//        }
//        System.out.println("Reversed Number is : " +rev);
//    }
//}
