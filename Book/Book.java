package Book;

import java.time.LocalDate;
import java.util.Date;

public interface Book {
    String toFile();
    String toString();
    String getCategory();
    String getPublisher();
    String getAuthor();
    double getPrice();
    LocalDate getPublishDate();
    int getStock();
    String getTitle();
    String getIsbn();
    int getStockStorage();
    boolean isStock();
    void setTitle(String title);
    void setAuthor(String author);
    void setCategory(String category);
    void setPrice(double price);
    void setPublisher(String publisher);
    void setPublishDate(LocalDate publishDate);
    void setStock(boolean isStock);
    void setStock (int stock);
    void setStockStorage(int stockStorage);


}
