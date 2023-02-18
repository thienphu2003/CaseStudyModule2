package Book;

import java.time.LocalDate;
import java.util.Date;

public class BookFactory {
    public Book createBook(String Title, String Author, String ISBN, double price, String category, String publisher, LocalDate publishDate, int stock, int stockStorage)
    {
        Book book = switch (category) {
            case "Comics" ->
                    new ComicsBook.BookBuilder().Title(Title).Author(Author).ISBN(ISBN).price(price).category(category).publisher(publisher).publishDate(publishDate).stock(stock).stockStorage(stockStorage).build();
            case "LoveStory" ->
                    new LoveStory.BookBuilder().Title(Title).Author(Author).ISBN(ISBN).price(price).category(category).publisher(publisher).publishDate(publishDate).stock(stock).stockStorage(stockStorage).build();
            case "Novel" ->
                    new Novel.BookBuilder().Title(Title).Author(Author).ISBN(ISBN).price(price).category(category).publisher(publisher).publishDate(publishDate).stock(stock).stockStorage(stockStorage).build();
            default -> null;
        };
        return book;
    }
}
