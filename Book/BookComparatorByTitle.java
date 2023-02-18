package Book;

import java.util.Comparator;

public class BookComparatorByTitle implements Comparator<Book> {
    @Override
    public int compare(Book book , Book newBook)
    {
        return book.getTitle().compareTo(newBook.getTitle());
    }
}
