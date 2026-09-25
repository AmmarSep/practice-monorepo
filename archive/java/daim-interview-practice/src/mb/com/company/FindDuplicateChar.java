package mb.com.company;

public class FindDuplicateChar {
    public static void main(String[] args) {
        String str = " Ba Ba black sheep have you any wool?";
        String str2 = str.replaceAll("\\s","");
        int count = 0;
        char[] chars = str2.toCharArray();
        for ( int i = 0; i<str2.length(); i++){
            for(int j = i+1; j<str2.length(); j++){
                if(chars[i] == chars[j]){
                    System.out.println(chars[j]);
                    count++;
                    break;

                }
            }
        }
        System.out.println(count);
    }
}
