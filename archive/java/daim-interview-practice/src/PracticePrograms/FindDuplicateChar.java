package PracticePrograms;

public class FindDuplicateChar {
    public static void main(String[] args) {
        String str = new String(" ammar.S.S");
        int count = 0;
        char[] chars = str.toCharArray();
        System.out.println("Duplicate characters in the string are : ");
        for(int i=0; i<str.length();i++)
        {
            for(int j= i+1; j<str.length();j++)
            {
                if(chars[i] == chars[j])
                {
                    System.out.println(chars[j]);
                    count++;
                    break;
                }
            }
        }
    }
}
