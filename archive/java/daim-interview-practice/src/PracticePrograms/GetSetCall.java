package PracticePrograms;

public class GetSetCall {

    // Main driver method
    public static void main(String[] args)
    {
        // Creating an object of class 1 in main() method
        GetSet obj = new GetSet();

        // Setting the name by calling setter method
        obj.setName("Geeks for Geeks");
        // Getting the name by calling geter method
        System.out.println(obj.getName());
    }
}

