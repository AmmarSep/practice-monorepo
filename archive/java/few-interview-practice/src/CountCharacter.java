public class CountCharacter {
    public static void main(String[] args) {
            String input = "iweweh oiwefhoah piwrufjdsfh JHFFGFJK oiawefh";
//        input = input.toLowerCase();
        char search ='f';
        int count =0;
        for(int i =0; i<input.length(); i++){
            if(input.charAt(i) == search){
                count ++;

            }
        }
        System.out.println("The character " + search + " occurs " + count + " times ");
    }
}
