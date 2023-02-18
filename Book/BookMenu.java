package Book;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class BookMenu {
    Scanner scanner =new Scanner(System.in);
    BookManagement bookManagement = BookManagement.getBookManagement();
    public BookMenu()
    {}

    public void displayMenu()
    {
        System.out.println("_________________________________");
        System.out.println("|           BOOK_MENU           |");
        System.out.println("---------------------------------");
        System.out.println("|       Quản lý sách              |");
        System.out.println("|1. Thêm vào sách                 |");
        System.out.println("|2. Xóa sách                      |");
        System.out.println("|3. Tìm theo ISBN của sách        |");
        System.out.println("|4. Tìm theo tên sách             |");
        System.out.println("|5. Tìm theo nhà xuất bản         |");
        System.out.println("|6. Tìm theo tác giả              |");
        System.out.println("|7. Tìm theo thể loại             |");
        System.out.println("|8. Tìm theo ngày xuất bản        |");
        System.out.println("|9. Cập nhật sách                 |");
        System.out.println("|10. Sách hết hàng                |");
        System.out.println("|11. Sách còn hàng                |");
        System.out.println("|12. Hiển thị toàn bộ sách        |");
        System.out.println("|13. Hiển thị theo loại sách      |");
        System.out.println("|14. Hiển thị theo giá sách       |");
        System.out.println("|15. Hiển thị theo thứ tự isbn    |");
        System.out.println("|16. Hiển thị theo thứ tự tên sách|");
        System.out.println("|0. Thoát                         |");
        System.out.println("----------------------------------|");
    }
    public void menu()
    {
        int choice = -1;
        do{
            this.displayMenu();
            System.out.println("Hãy nhập vào lựa chọn của bạn.Nhập 0 để thoát !");
            choice=scanner.nextInt();
            scanner.nextLine();
            switch (choice)
            {
                case 1 ->add();
                case 2->remove();
                case 3->findByISBN();
                case 4->findByTitle();
                case 5->findByPublisher();
                case 6->findByAuthor();
                case 7->findByCategory();
                case 8 ->findByPublishDate();
                case 9 ->updateBookByISBN();
                case 10->showOutOfStockBook();
                case 11->showInStockBook();
                case 12->showAllBook();
                case 13->showBookByCategory();
                case 14->showByPrice();
                case 15->showByISBN();
                case 16->showByTitle();
            }

        }while (choice!=0);

    }
    public void add()
    {
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.println("Hãy nhập số ISBN của sách :");
        String isbn = scanner.nextLine();
        while (bookManagement.checkISBN(isbn))
        {
            System.out.println("Id đã tồn tại vui lòng nhập lại");
            isbn=scanner.nextLine();
        }
        System.out.println("Nhập tiêu đề sách :");
        String title = scanner.nextLine();
        System.out.println("Nhập tác giả :");
        String author =scanner.nextLine();
        System.out.println("Nhập giá sách :");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Nhập loại sách :");
        String category = scanner.nextLine();
        System.out.println("Nhập nhà phát hành :");
        String publisher = scanner.nextLine();
        System.out.println("Nhập ngày xuất bản :");
        LocalDate date = LocalDate.parse(scanner.nextLine(),formatter);
        System.out.println("Nhập số lượng :");
        int stock = scanner.nextInt();
        System.out.println("Nhập số lượng còn trong kho");
        int stockStorage =scanner.nextInt();
        scanner.nextLine();
        BookFactory factory = new BookFactory();
        Book book =factory.createBook(title,author,isbn,price,category,publisher,date,stock,stockStorage);
        bookManagement.add(book);
    }
    public void remove()
    {
        System.out.println("Hãy nhập số isbn của sách cần xóa");
        String isbn = scanner.nextLine();
        if(bookManagement.removeByISBN(isbn))
        {
            System.out.println("Xóa thành công ");
        }else
            System.out.println("Không tìm thấy isbn của sách cần xóa");

    }
    public void findByISBN()
    {
        System.out.println("Hãy nhập số isbn của sách cần tìm ");
        String isbn =scanner.nextLine();
        if(bookManagement.searchByISBN(isbn)==null)
        {
            System.out.println("Không tìm thấy sách ");
        }else {
            System.out.println("Sách đã tìm thấy :");
            System.out.println(bookManagement.searchByISBN(isbn));
        }
    }
    public void findByTitle()
    {
        System.out.println("Hãy nhập tên sách cần tìm ");
        String title =scanner.nextLine();
        List<Book> list = bookManagement.searchByTitle(title);
        if(list.size()==0)
        {
            System.out.println("Không tìm thấy sách");
        }else {
            System.out.println("Đã tìm thấy sách");
            System.out.println(list);
        }
    }
    public void findByPublisher()
    {
        System.out.println("Hãy nhập nhà xuất bản ");
        String publisher = scanner.nextLine();
        List<Book> list =bookManagement.searchByPublisher(publisher);
        if(list.size()==0)
        {
            System.out.println("Không tìm thấy sách");
        }else {
            System.out.println("Đã tìm thấy sách");
            System.out.println(list);
        }
    }
    public void findByAuthor()
    {
        System.out.println("Hãy nhập tên tác giả ");
        String author = scanner.nextLine();
        List<Book> list =bookManagement.searchByAuthor(author);
        if(list.size()==0)
        {
            System.out.println("Không tìm thấy sách");
        }else {
            System.out.println("Đã tìm thấy sách");
            System.out.println(list);
        }
    }
    public void findByCategory()
    {
        System.out.println("Hãy nhập loại sách ");
        String bookType = scanner.nextLine();
        List<Book> list =bookManagement.searchByCategory(bookType);
        if(list.size()==0)
        {
            System.out.println("Không tìm thấy sách");
        }else {
            System.out.println("Đã tìm thấy sách");
            System.out.println(list);
        }
    }
    public void findByPublishDate()
    {
        DateTimeFormatter dateTimeFormatter =DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.println("Hãy nhập ngày xuất bản");
        LocalDate date;
            date = LocalDate.parse(scanner.nextLine(),dateTimeFormatter);
            List<Book> list = bookManagement.searchByPublishDate(date);
            if(list.size()==0)
            {
                System.out.println("Không tìm thấy sách");
            }else
            {
                System.out.println("Đã tìm thấy sách");
                System.out.println(list);
            }
    }
    public void updateBookByISBN()
    {
        System.out.println("Hãy nhập vào ISBN của sách cần cập nhật");
        String isbn =scanner.nextLine();
        Book book =bookManagement.searchByISBN(isbn);
        if(book==null)
        {
            System.out.println("Không tìm thấy sách để cập nhật");
        }else
        {
            DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd-MM-yyyy");
            System.out.println("Nhập tiêu đề sách :");
            String title = scanner.nextLine();
            System.out.println("Nhập tác giả :");
            String author =scanner.nextLine();
            System.out.println("Nhập giá sách :");
            double price = scanner.nextDouble();
            scanner.nextLine();
            System.out.println("Nhập loại sách :");
            String category = scanner.nextLine();
            System.out.println("Nhập nhà phát hành :");
            String publisher = scanner.nextLine();
            System.out.println("Nhập ngày xuất bản :");
            LocalDate date = LocalDate.parse(scanner.nextLine(),formatter);
            System.out.println("Nhập số lượng :");
            int stock = scanner.nextInt();
            System.out.println("Nhập số lượng còn trong kho");
            int stockStorage =scanner.nextInt();
            scanner.nextLine();
            BookFactory factory = new BookFactory();
            Book bookTemp = factory.createBook(title,author,isbn,price,category,publisher,date,stock,stockStorage);
            bookManagement.updateBookByISBN(bookTemp.getIsbn(),bookTemp);
        }
    }
    public void showOutOfStockBook()
    {
        List<Book> outOfStock = bookManagement.outOfStock();
        if(outOfStock.size()!=0)
        {
            System.out.println("Các loại sách đã hết hàng trong kho là ");
            System.out.println(outOfStock);
        }
    }
    public void showInStockBook()
    {
        List<Book> inStock = bookManagement.inStock();
        if(inStock.size()!=0)
        {
            System.out.println("Các loại sách còn hàng trong kho là ");
            System.out.println(inStock);
        }
    }
    public void showAllBook()
    {
        System.out.println("Danh sách toàn bộ sách hiện có trong cửa hàng ");
        List<Book> bookList = bookManagement.displayBook();
        System.out.println(bookList);
    }
    public void showBookByCategory()
    {
        System.out.println("Hãy nhập vào thể loại sách ");
        String type = scanner.nextLine();
        List<Book> bookList= bookManagement.displayCategory(type);
        System.out.println("Các sách thuộc thể loại "+type+" là :"+"\n"+bookList);
    }
    public void showByPrice()
    {
        System.out.println("Hãy nhập vào mức giá mốc");
        double border = scanner.nextDouble();
        String standard;
        scanner.nextLine();
        do {
            System.out.println("Hãy nhập vào chuẩn đo");
            standard = scanner.nextLine();
            if(!(standard.equals(">")||standard.equals("<")||standard.equals("=")))
            {
                System.out.println("Bạn đã nhập sai chuẩn đo.Hãy nhập lại chuẩn đo : ");
            }
        }while (!(standard.equals(">")||standard.equals("<")||standard.equals("=")));
        List<Book> books = bookManagement.displayByPrice(border,standard);
        if(books.size()==0)
        {
            System.out.println("Không có sách nào có giá tiền "+standard+" "+border);
        }
        System.out.println("Các loại sách có giá tiền "+standard+" "+border+" là "+"\n"+books);
    }
    public void showByISBN()
    {
        System.out.println("Danh sách sách sắp xếp theo ISBN là");
        System.out.println(bookManagement.arrangeBookListByISBN());
    }
    public void showByTitle()
    {
        System.out.println("Danh sách sách sắp xếp theo Title là");
        System.out.println(bookManagement.arrangeBookListByTitle());
    }
}
