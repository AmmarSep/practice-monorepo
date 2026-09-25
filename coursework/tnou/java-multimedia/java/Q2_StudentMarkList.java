import java.util.Scanner;

public class Q2_StudentMarkList {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter student name: ");
        String name = sc.nextLine();
        System.out.print("Enter marks for 5 subjects: ");
        int total = 0;
        for (int i = 0; i < 5; i++) {
            total += sc.nextInt();
        }
        double avg = total / 5.0;
        System.out.println("\n--- Mark List ---");
        System.out.println("Name: " + name);
        System.out.println("Total: " + total);
        System.out.println("Average: " + avg);
        System.out.println("Grade: " + (avg >= 90 ? "A" : avg >= 75 ? "B" : avg >= 60 ? "C" : avg >= 50 ? "D" : "F"));
        sc.close();
    }
}
