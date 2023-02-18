package Book;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BookManagement  {
    private static final List<Book> allBookSet = new ArrayList<>() ;
    private static final String FILE_PATH_4 ="D:\\module2CODEGYM\\MODULE2_CASESTUDY\\MyBookStore\\Book\\allBook.csv";
    private static final BookManagement bookManagement = new BookManagement();
    private BookManagement()
    {
        try
        {
            this.readFromFile();
        }catch (Exception e)
        {
            this.saveFileIfNull();
        }
    }

    public static BookManagement getBookManagement()
    {
        return bookManagement;
    }

    public static List<Book> getAllBookSet() {
        return allBookSet;
    }



    public void add(Book newBook)
    {
        allBookSet.add(newBook);
        this.writeFile(FILE_PATH_4,allBookSet);
    }
    public Book searchByISBN(String isbn)
    {

        Iterator<Book> it = allBookSet.iterator();
        Book book ;
        do {
            if(!it.hasNext())
                return null;
            book = (Book)it.next();
        }while (!book.getIsbn().equals(isbn));
        return book;
    }
    public boolean removeByISBN(String isbn)
    {
        Book book = this.searchByISBN(isbn);
        if(book!=null)
        {
            allBookSet.remove(book);
            writeFile(FILE_PATH_4,allBookSet);
            return true;
        }
        return false;
    }
     public List<Book> searchByTitle(String title)
    {
        Iterator<Book> it = allBookSet.iterator();
       List<Book> bookByTitle = new ArrayList<>();
        while(it.hasNext())
        {
            Book book = (Book) it.next();
            if(book.getTitle().contains(title))
            {
                bookByTitle.add(book);
            }
        }
        return bookByTitle;
    }
    public List<Book> searchByAuthor(String author)
    {
        Iterator<Book> it = allBookSet.iterator();
        List<Book> bookByAuthor = new ArrayList<>();
        while(it.hasNext())
        {
            Book book = (Book) it.next();
            if(book.getAuthor().contains(author))

            {
                bookByAuthor.add(book);
            }
        }
        return bookByAuthor;
    }
    public List<Book> searchByPublisher(String publisher)
    {
        Iterator<Book> it = allBookSet.iterator();
        List<Book> bookByPublisher = new ArrayList<>();
        while(it.hasNext())
        {
            Book book = (Book) it.next();
            if(book.getPublisher().contains(publisher))

            {
                bookByPublisher.add(book);
            }
        }
        return bookByPublisher;
    }
    public List<Book> searchByCategory(String category)
    {
        Iterator<Book> it = allBookSet.iterator();
        List<Book> bookByCategory = new ArrayList<>();
        while(it.hasNext())
        {
            Book book = (Book) it.next();
            if(book.getCategory().contains(category))

            {
                bookByCategory.add(book);
            }
        }
        return bookByCategory;
    }
    public List<Book> searchByPublishDate(LocalDate publishDate)
    {
        Iterator<Book> it = allBookSet.iterator();
        List<Book> bookByPublishDate = new ArrayList<>();
        while(it.hasNext())
        {
            Book book = (Book) it.next();
            LocalDate date = book.getPublishDate();
            if(date.equals(publishDate))
            {
                bookByPublishDate.add(book);
            }
        }
        return bookByPublishDate;
    }
    public List<Book> displayBook()
    {
        return new ArrayList<>(allBookSet);
    }
    public List<Book> displayCategory(String category)
    {
        List<Book> books =  this.searchByCategory(category);
        return books;
    }
    public List<Book> displayByPrice(double limitPrice,String standard)
    {

       List<Book> bookByPrice = new ArrayList<>();
       if(standard.equals(">"))
       {
           for(Book book :allBookSet)
           {
               if(book.getPrice()>limitPrice)
                   bookByPrice.add(book);
           }
       }else if(standard.equals("<"))
       {
           for(Book book :allBookSet)
           {
               if(book.getPrice()<limitPrice)
                   bookByPrice.add(book);
           }
       }else
       {
           for(Book book :allBookSet)
           {
               if(book.getPrice()==limitPrice)
                   bookByPrice.add(book);
           }
       }
       return bookByPrice;
    }
    public List<Book> arrangeBookListByISBN()
    {
        allBookSet.sort(new BookComparatorByISBN());
        return new ArrayList<>(allBookSet);
    }
    public List<Book> arrangeBookListByTitle()
    {
        allBookSet.sort(new BookComparatorByTitle());
        return new ArrayList<>(allBookSet);
    }

    public void updateBookByISBN(String isbn , Book newBook)
    {
        Book book = this.searchByISBN(isbn);
        if(book!=null) {
            book.setTitle(newBook.getTitle());
            book.setAuthor(newBook.getAuthor());
            book.setPublisher(newBook.getPublisher());
            book.setCategory(newBook.getCategory());
            book.setPublishDate(newBook.getPublishDate());
            book.setStockStorage(newBook.getStockStorage());
            book.setStock(newBook.getStock());
            book.setPrice(newBook.getPrice());
        }
        this.writeFile(FILE_PATH_4,allBookSet);
    }
    public List<Book> outOfStock() {
        this.readFromFile();
        List<Book> outOfStock = new ArrayList<>();

        for (Book b : allBookSet) {
            if (!b.isStock()) {
                outOfStock.add(b);
            }
        }
        return outOfStock;
    }
    public List<Book> inStock() {
        this.readFromFile();
        List<Book> inStock = new ArrayList<>();

        for (Book b : allBookSet) {
            if (b.isStock()) {
                inStock.add(b);
            }
        }
        return inStock;
    }


    public boolean checkISBN(String newISBN) {
        Iterator<Book> it= allBookSet.iterator();

        Book book;
        do {
            if (!it.hasNext()) {
                return false;
            }

            book = (Book)it.next();
        } while(!book.getIsbn().equals(newISBN));

        return true;
    }
    public void writeFile(String path,List<Book> books)
    {
        try {
            FileWriter engine = new FileWriter(path);
            BufferedWriter bf = new BufferedWriter(engine);
            for (Book book : books) {
                bf.write(book.toFile());
                bf.newLine();
            }
            bf.close();
            engine.close();
        }catch (IOException e)
        {
            throw new IllegalArgumentException("Không thể ghi ra File");
        }
    }


    public void readFromFile()
    {
        allBookSet.clear();
        try {
            FileReader engine = new FileReader(FILE_PATH_4);
            BufferedReader bf = new BufferedReader(engine);
            String line = "";
            while((line=bf.readLine())!=null)
            {
                Book book = this.handleLine(line);
                allBookSet.add(book);
            }
            bf.close();
            engine.close();
        }catch (IOException e)
        {
            throw new IllegalArgumentException("Không thể đọc từ File");
        }
    }
    public Book handleLine(String line)
    {
        DateTimeFormatter dateTimeFormatter =DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date;
        String[] strings = line.split(",");
        date = LocalDate.parse(strings[6],dateTimeFormatter);
        if(Objects.equals(strings[4], "Novel"))
        {
            return new Novel.BookBuilder().Title(strings[0]).Author(strings[1]).ISBN(strings[2]).price(Double.parseDouble(strings[3])).category(strings[4]).publisher(strings[5]).publishDate(date).isStock(Boolean.parseBoolean(strings[7])).stock(Integer.parseInt(strings[8])).stockStorage(Integer.parseInt(strings[9])).build();
        }else if(Objects.equals(strings[4], "LoveStory"))
        {
            return new LoveStory.BookBuilder().Title(strings[0]).Author(strings[1]).ISBN(strings[2]).price(Double.parseDouble(strings[3])).category(strings[4]).publisher(strings[5]).publishDate(date).isStock(Boolean.parseBoolean(strings[7])).stock(Integer.parseInt(strings[8])).stockStorage(Integer.parseInt(strings[9])).build();
        }else
            return new ComicsBook.BookBuilder().Title(strings[0]).Author(strings[1]).ISBN(strings[2]).price(Double.parseDouble(strings[3])).category(strings[4]).publisher(strings[5]).publishDate(date).isStock(Boolean.parseBoolean(strings[7])).stock(Integer.parseInt(strings[8])).stockStorage(Integer.parseInt(strings[9])).build();
    }
    public void saveFileIfNull() {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH_4);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write("");

            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException var5) {
            throw new RuntimeException(var5);
        }
    }
}