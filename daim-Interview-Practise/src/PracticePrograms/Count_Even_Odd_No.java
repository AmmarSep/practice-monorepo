package PracticePrograms;

public class Count_Even_Odd_No {
    public static void main(String[] args) {
        int numb = 23478243;
        int even_coun =  0;
        int odd_coun =  0;
        while(numb>0)
        {
            int remain = numb%10;
            if ( remain%2 == 0)
            {
                even_coun++;
            }
            else
            {
                odd_coun++;
            }
            numb = numb/10;
        }
        System.out.println("Number of even number in the digit:"+even_coun);
        System.out.println("NUmber of odd number in the digit:" +odd_coun);
    }
}
