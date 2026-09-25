package PracticePrograms;

// Java Program to Illustrate Getter and Setter

// Importing input output classes

// Class 1
// Helper class
class GetSet {

    // Member variable of this class
    private String name;

    // Method 1 - Getter
    public String getName() { return name; }

    // Method 2 - Setter
    public void setName(String N)
    {

        // This keyword refers to current instance itself
        this.name = N;
    }
}

// Class 2
// Main class
/*class GFG {

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
}*/

