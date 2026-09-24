// Program 18: Program for simple Railway Reservation System (Concept: IO Streams: DataInputStream & DataOutputStream)

import java.io.*;
import java.util.Scanner;

class Passenger {
    String name;
    int age;
    String gender;
    String trainNumber;
    String destination;
    String seatNumber;

    public Passenger(String name, int age, String gender, String trainNumber, String destination, String seatNumber) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.trainNumber = trainNumber;
        this.destination = destination;
        this.seatNumber = seatNumber;
    }
}

public class P18_RailwayReservation {
    private static final String FILENAME = "railway_reservations.dat";

    public static void bookTicket() {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter passenger name: ");
            String name = scanner.nextLine();

            System.out.print("Enter age: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter gender (M/F): ");
            String gender = scanner.nextLine();

            System.out.print("Enter train number: ");
            String trainNumber = scanner.nextLine();

            System.out.print("Enter destination: ");
            String destination = scanner.nextLine();

            String seatNumber = "S" + (int)(Math.random() * 100 + 1);

            FileOutputStream fos = new FileOutputStream(FILENAME, true);
            DataOutputStream dos = new DataOutputStream(fos);

            dos.writeUTF(name);
            dos.writeInt(age);
            dos.writeUTF(gender);
            dos.writeUTF(trainNumber);
            dos.writeUTF(destination);
            dos.writeUTF(seatNumber);

            dos.close();
            fos.close();

            System.out.println("\n===== TICKET BOOKED SUCCESSFULLY =====");
            System.out.println("Passenger: " + name);
            System.out.println("Seat Number: " + seatNumber);
            System.out.println("Train Number: " + trainNumber);
            System.out.println("Destination: " + destination);
            System.out.println("======================================");

        } catch (IOException e) {
            System.out.println("Error booking ticket: " + e.getMessage());
        }
    }

    public static void displayReservations() {
        try {
            File file = new File(FILENAME);
            if (!file.exists()) {
                System.out.println("No reservations found.");
                return;
            }

            FileInputStream fis = new FileInputStream(FILENAME);
            DataInputStream dis = new DataInputStream(fis);

            System.out.println("\n===== ALL RESERVATIONS =====");
            int count = 1;

            try {
                while (true) {
                    String name = dis.readUTF();
                    int age = dis.readInt();
                    String gender = dis.readUTF();
                    String trainNumber = dis.readUTF();
                    String destination = dis.readUTF();
                    String seatNumber = dis.readUTF();

                    System.out.println("\nReservation #" + count++);
                    System.out.println("Name: " + name);
                    System.out.println("Age: " + age);
                    System.out.println("Gender: " + gender);
                    System.out.println("Train: " + trainNumber);
                    System.out.println("Destination: " + destination);
                    System.out.println("Seat: " + seatNumber);
                }
            } catch (EOFException e) {
                // End of file reached
            }

            dis.close();
            fis.close();

            System.out.println("=============================");

        } catch (IOException e) {
            System.out.println("Error reading reservations: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== RAILWAY RESERVATION SYSTEM =====");
            System.out.println("1. Book Ticket");
            System.out.println("2. Display All Reservations");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    bookTicket();
                    break;
                case 2:
                    displayReservations();
                    break;
                case 3:
                    System.out.println("Thank you for using Railway Reservation System!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
