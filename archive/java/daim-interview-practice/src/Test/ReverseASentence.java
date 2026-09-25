package Test;

import static PracticePrograms.ReverseSentence.reverse;

public class ReverseASentence
{

    public static void main(String[] args) {

        String s = "I am in bangalore";
        String reversedSentence =reverse(s);
        System.out.println("The reverse sentence is "+reversedSentence);
    }


    public static String reverse(String s)
    {
        if(s.isEmpty())
            return s;


        return reverse(s.substring(1))+s.charAt(0);
    }
}

