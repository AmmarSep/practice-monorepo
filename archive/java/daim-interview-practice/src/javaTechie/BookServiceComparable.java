package javaTechie;

import java.util.Collections;
import java.util.List;

public class BookServiceComparable {
    public List<Book> getBooksinSort() {
        List<Book> books = new BooksDAO().getBooks();
//        Collections.sort(books, new MyComparator());
        Collections.sort(books,(o1,o2)->o2.getName().compareTo(o1.getName()));
        return books;
    }

    public static void main(String[] args) {
        System.out.println(new BookServiceComparable().getBooksinSort());
    }
}
/*
class MyComparator implements Comparator<Book> {
        @Override
        public int compare(Book o1, Book o2) {
            return o1.getName().compareTo(o2.getName());
        }
}*/
