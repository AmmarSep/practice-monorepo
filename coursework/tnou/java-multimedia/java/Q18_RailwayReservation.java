import java.io.*;
import java.util.Scanner;

public class Q18_RailwayReservation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream("reservation.dat"));
            System.out.print("Enter number of passengers: ");
            int n = sc.nextInt();
            for (int i = 0; i < n; i++) {
                System.out.print("Enter PNR: ");
                dos.writeInt(sc.nextInt());
                sc.nextLine();
                System.out.print("Enter Name: ");
                dos.writeUTF(sc.nextLine());
                System.out.print("Enter Age: ");
                dos.writeInt(sc.nextInt());
                sc.nextLine();
                System.out.print("Enter Destination: ");
                dos.writeUTF(sc.nextLine());
            }
            dos.close();
            System.out.println("\nReservation Details:");
            DataInputStream dis = new DataInputStream(new FileInputStream("reservation.dat"));
            for (int i = 0; i < n; i++) {
                System.out.println("PNR: " + dis.readInt());
                System.out.println("Name: " + dis.readUTF());
                System.out.println("Age: " + dis.readInt());
                System.out.println("Destination: " + dis.readUTF());
                System.out.println();
            }
            dis.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        sc.close();
    }
}
