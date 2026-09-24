package Again2.VolTru;

public class FindDupChar1
{
    public static void main(String[] args)
    {
        String allCharacters = "  a quick brown fox leaps over a Lazy Dog g ";
        String allCharactersWithoutSpace = allCharacters.replaceAll("\\s","");
        char[] chars = allCharactersWithoutSpace.toCharArray();
        int count = 0;
        System.out.println("Duplicate characters are : ");
        for(int i = 0; i<allCharactersWithoutSpace.length(); i++)
        {
            for (int j = i+1; j<allCharactersWithoutSpace.length(); j++)
            {
                if(chars[i]==chars[j])
                {
                    System.out.println(chars[j]);
                    count++;
                    break;
                }
            }
        }
        System.out.println(+count);
    }
}
