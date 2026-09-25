package PracticePrograms;

public class ReverseName {
    public static void main(String[] args) {
        String name = "Ammar";
        String reversedName = "";
        for(int i= name.length()-1; i>=0; i--){
            reversedName = reversedName + name.charAt(i);

        }
        System.out.println("Reversed name is :" +reversedName);
    }
}
