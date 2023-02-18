package Customer;

import Book.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Cart {
    private HashMap<String,Integer> books;
    public Cart()
    {
        books=new HashMap<>();
    }
    public void addBookToCart(String isbn,int quantity)
    {
        books.put(isbn,quantity);
    }

    public HashMap<String, Integer> getBooks() {
        return books;
    }
}
