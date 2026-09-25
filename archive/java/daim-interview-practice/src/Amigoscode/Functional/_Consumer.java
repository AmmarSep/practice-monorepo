package Amigoscode.Functional;

import java.util.function.Consumer;

//Consumer is  functional interface that takes in one argument and returns nothing.
public class _Consumer {
    public static void main(String[] args) {
        Customer ali = new Customer("Ali",9824793845L);
        greetCustomer(new Customer("Mariam",9283469368L));
        greetCustomerByConsumer.accept(ali);
    }

    static Consumer<Customer> greetCustomerByConsumer = customer ->  System.out.println("Hello "+customer.customerName+", Thanks for registering Phone Number "
            +customer.customerPhoneNumber);

    static void greetCustomer(Customer customer){
        System.out.println("Hello "+customer.customerName+", Thanks for registering Phone Number "
                +customer.customerPhoneNumber);
    }
    static class Customer{
        private final String customerName;
        private final long customerPhoneNumber;

        public Customer(String customerName, long customerPhoneNumber) {
            this.customerName = customerName;
            this.customerPhoneNumber = customerPhoneNumber;
        }
    }
}
