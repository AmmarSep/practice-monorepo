package Amigoscode.Functional;

import java.util.List;
import java.util.function.Supplier;

//Supplier<T> represents a supplier of results.
public class _Supplier {
    public static void main(String[] args) {
        System.out.println(returnUrl());
        System.out.println(returnUrlUsingSupplier.get());

    }

    static Supplier<List<String>>  returnUrlUsingSupplier = ()-> List.of("jdbc://localhost:7384/supplier",
            "jdbc://localhost:7384/consumer");

    static String returnUrl(){
        return "jdbc:/getUrl/user";
    }

}
