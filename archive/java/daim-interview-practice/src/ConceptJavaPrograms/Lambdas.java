package ConceptJavaPrograms;

public class Lambdas {
    public static void main(String[] args) {
LambdaCat myLambdaCat = new LambdaCat();
printThing(myLambdaCat);

    }
    static void printThing(LambdaPrintable thing){
        thing.print();

    }
}
