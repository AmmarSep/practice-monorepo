package PracticePrograms;
public class RemoveSpecialChar
{
    public static void main(String[] args)
    {
        String wordWithSpecialChar = "Amar2CG@Feb#2022";
        int count = 0;
        String specialCharRemoved = "";

        for(int i =0; i<wordWithSpecialChar.length(); i++)
        {
            if (
                    !Character.isDigit(wordWithSpecialChar.charAt(i))
            && !Character.isLetter(wordWithSpecialChar.charAt(i))
            && !Character.isWhitespace(wordWithSpecialChar.charAt(i))
            )
            {
                count ++;
            }
            else
            {
                specialCharRemoved = specialCharRemoved + wordWithSpecialChar.charAt(i);
            }
        }
        if (count == 0)
        {
            System.out.println("There are no special characters in String");
        }
        else
        {
            System.out.println("Special Characters found: " + count);
        }
        System.out.println("Word with Special Character Removed " + specialCharRemoved);
    }
}
