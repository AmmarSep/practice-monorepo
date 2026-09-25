package Amigoscode.Optionals;

import java.util.Optional;

public class _Optional {
    public static void main(String[] args) {
        Optional.ofNullable("ammar@gmail.com").ifPresentOrElse(email-> System.out.println("Email has been send to " +email),
                ()-> System.out.println("Cannot send email since email is not specified"));
    }
}
