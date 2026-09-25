package Amigoscode;

import java.time.LocalDate;

public class PassportProgram {
    public static void main(String[] args) {
        Passport indianPassport = new Passport("K3478734","India", LocalDate.of(2025,12,25));
        Passport pakistanPassport = new Passport("I345634","Pakistan", LocalDate.of(2023,8,14));
        System.out.println(pakistanPassport.passportNumber + pakistanPassport.country);
        System.out.println(indianPassport.expiryDate +" "+ indianPassport.country);
    }

    static class Passport{
        String passportNumber;
        String country;
        LocalDate expiryDate;
      Passport(String pNumber, String nation , LocalDate lastDate){
          this.passportNumber= pNumber;
          this.country= nation;
          this.expiryDate = lastDate;
      }
    }
}
