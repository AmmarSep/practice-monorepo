package PracticePrograms;

public class CountOccurenceChar {
    public static void main(String[] args) {
        String input = "awhfgaksdhbfjdh aiwohdjf weharfadjklsh hjkhrpewy";
        char search = 'f';
        int count = 0;
        for(int i=0; i<input.length(); i++){
            if(input.charAt(i) == search){
                count ++;
            }
        }
        System.out.println(" The character " +search +" occurs " +count + " times ");
        System.out.println();
    }
//    void findOccurrences() {
//        String s = "The quick brown fox jumped over the lazy dog.";
//        Map<String, Integer> occurrences = new LinkedHashMap<String, Integer>();
//
//        for (String ch : s.split("")) {
//            Integer count = occurrences.get(ch);
//            occurrences.put(ch, count == null ? 1 : count + 1);
//        }
//        System.out.println(occurrences);
//    }
}
