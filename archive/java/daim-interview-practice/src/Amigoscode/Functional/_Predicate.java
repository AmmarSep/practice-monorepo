package Amigoscode.Functional;

import java.util.function.Predicate;

//Predicate Represents a boolean-valued function of one argument.
public class _Predicate {
    public static void main(String[] args) {
        System.out.println("Without Predicate");
        System.out.println(isPhoneNumberValid("9384534779"));
        System.out.println(isPhoneNumberValid("4902358475"));
        System.out.println(isPhoneNumberValid("9485745454"));
        System.out.println(isPhoneNumberValid("9438523657643"));

        System.out.println("\n With Predicate");
        System.out.println(isPhoneNumberValidUsingPredicate.test("8938434343"));
        System.out.println(isPhoneNumberValidUsingPredicate.test("85654364656"));
        System.out.println(isPhoneNumberValidUsingPredicate.test("9646547565"));
        System.out.println(isPhoneNumberValidUsingPredicate.test("8943257454"));

        System.out.println("\n With Predicate Is number has 5");
        System.out.println(isPhoneNumberhave3UsingPredicate.test("8938434343"));
        System.out.println(isPhoneNumberhave3UsingPredicate.test("85654364656"));
        System.out.println(isPhoneNumberhave3UsingPredicate.test("9646547565"));
        System.out.println(isPhoneNumberhave3UsingPredicate.test("8943257454"));


    }

    static Predicate<String> isPhoneNumberValidUsingPredicate = phoneNum -> phoneNum.startsWith("8") && phoneNum.length()==10;
    static Predicate<String> isPhoneNumberhave3UsingPredicate = phoneNum -> phoneNum.contains("5");

    static boolean isPhoneNumberValid(String phoneNumber){
        return phoneNumber.startsWith("9") && phoneNumber.length()==10;
    }
}
