package Amigoscode;

public class IfStatement {
    public static void main(String[] args) {
        int age = 19;
        if(!(age<=30)){
            System.out.println("You are an adult");
        }
        else if(age<=19 && age>=13){
            System.out.println("Hooray!, You are teen");
        }
        else if(age<=12) {
            System.out.println("Hey Lad, You are a kid");
        }
        else{
            System.out.println("You are young");
        }
    }
}
