package Book;

import java.util.Comparator;

public class BookComparatorByISBN implements Comparator<Book> {
    @Override
    public int compare(Book book, Book newBook)
    {
        return book.getIsbn().compareTo(newBook.getIsbn());
    }
}
