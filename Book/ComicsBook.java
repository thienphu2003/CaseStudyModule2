package Book;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ComicsBook implements Book  {
    static  SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    private String title;
    private String author;
    private String isbn;
    private double price;
    private String category="Comics";
    private String publisher;
    private LocalDate publishDate;
    private boolean isStock  ;
    private int stock;
    private int stockStorage;

    public ComicsBook(BookBuilder builder) {
        this.title = builder.title;
        this.author = builder.author;
        this.isbn = builder.isbn;
        this.price = builder.price;
        this.publisher = builder.publisher;
        this.publishDate=builder.publishDate;
        this.stock=builder.stock;
        this.stockStorage=builder.stockStorage;
        this.isStock=false;
        this.category=builder.category;
        if(this.stockStorage>0||this.stock>0)
        {
            this.isStock=true;
        }
    }



    @Override
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public double getPrice() {
        return price;
    }


    public String getCategory() {
        return category;
    }

    public String getPublisher() {
        return publisher;
    }

    public LocalDate getPublishDate() {
        return this.publishDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int getStockStorage() {
        return stockStorage;
    }

    public void setStockStorage(int stockStorage) {
        this.stockStorage = stockStorage;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public boolean isStock() {
        return isStock;
    }

    public void setStock(boolean stock) {
        isStock = stock;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }



    @Override
    public String toFile()
    {
        return this.title+","+this.author+","+this.isbn+","+this.price+","+this.category+","
                +this.publisher+","+this.publishDate+","+this.isStock+","+this.stock+","+this.stockStorage;
    }

    @Override
    public String toString()
    {
        return "Title : "+this.title+", "+"Author : "+this.author+", "+"ISBN : "+this.isbn+", "
                +"Price : "+this.price+", "+"Category : "+this.category+", "+"Publisher : "+this.publisher
                +", "+"PublishDate : "+this.publishDate+", "+"C??n h??ng ? "+this.isStock+", "+"S??? l?????ng : "+this.stock+", "+"S??? l?????ng trong kho : "+this.stockStorage+"\n";
    }
    public static class BookBuilder{
        private String title;
        private String author;
        private String isbn ;
        private double price ;
        private String category;
        private String publisher;
        private  LocalDate publishDate;
        private int stock;
        private boolean isStock;
        private int stockStorage;

        public BookBuilder()
        {}


        public BookBuilder Title(String title)
        {
            this.title=title;
            return this;
        }
        public BookBuilder Author(String author)
        {
            this.author=author;
            return this;
        }
        public BookBuilder ISBN(String isbn)
        {
            this.isbn=isbn;
            return this;
        }
        public BookBuilder price(double price)
        {
            this.price=price;
            return this;
        }
        public BookBuilder category(String category)
        {
            this.category=category;
            return this;
        }
        public BookBuilder publisher(String publisher)
        {
            this.publisher = publisher;
            return this;
        }
        public BookBuilder publishDate (LocalDate publishDate)
        {
            this.publishDate=publishDate;
            return this;
        }
        public BookBuilder stock(int stock)
        {
            this.stock =stock;
            return this;
        }
        public BookBuilder isStock(boolean isStock)
        {
            this.isStock=isStock;
            return this;
        }
        public BookBuilder stockStorage(int stockStorage)
        {
            this.stockStorage=stockStorage;
            return this;
        }
        public ComicsBook build()
        {
            return new ComicsBook(this);
        }

    }
}
