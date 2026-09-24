package javaTechie;

import java.util.ArrayList;
import java.util.List;

public class BooksDAO {
    public List<Book> getBooks(){
        List<Book> books = new ArrayList<>();
        books.add(new Book(1,"Java Programming",453));
        books.add(new Book(2,"C Programming",560));
        books.add(new Book(3,"Python Programming",340));
        books.add(new Book(4,"JavaScript Programming",670));
        return  books;
    }
}
