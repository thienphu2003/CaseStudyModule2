package Order;

import Book.Book;
import Book.BookManagement;
import Customer.Customer;
import Customer.CustomerManagement;
import Worker.Worker;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class OrderManagement {
    private static final String FILE_PATH = "D:\\module2CODEGYM\\MODULE2_CASESTUDY\\MyBookStore\\Order\\order.csv";
    private static OrderManagement orderManagement = new OrderManagement();
    BookManagement bookManagement = BookManagement.getBookManagement();
    CustomerManagement customerManagement = CustomerManagement.getCustomerManagement();
    private static List<Order> orderList = new ArrayList();

    public static List<Order> getOrderList() {
        return orderList;
    }
    private static double num;

    public void count()
    {
        this.readFromFile();
        for(Order order : orderList)
        {
            num+=order.getTotal();
        }
    }

    public static double getNum() {
        return num;
    }

    public static void setNum(double num) {
        OrderManagement.num = num;
    }

    public static OrderManagement getOrderManagement() {
        return orderManagement;
    }
    private OrderManagement() {
    }
    public void add(Order newOrder) {
        Order.quantity++;
        newOrder.setTotal();
        orderList.add(newOrder);
        this.updateQuantity(newOrder);
        this.updateCustomer(newOrder);
        this.saveFile();
    }
    public Order searchByOrderId(String orderId) {
        this.readFromFile();
        Iterator var2 = this.orderList.iterator();

        Order o;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            o = (Order)var2.next();
        } while(!o.getOrderID().equals(orderId));

        return o;
    }
    public List<Order> searchByCustomerName(String customerName) {
        this.readFromFile();
        List<Order> orderArrayList = new ArrayList();
        Iterator var3 = this.orderList.iterator();

        while(var3.hasNext()) {
            Order o = (Order)var3.next();
            if (o.getCustomerName().contains(customerName)) {
                orderArrayList.add(o);
            }
        }

        return orderArrayList;
    }


    public void increaseCarQuantity(Order newOder)
    {
        for(String key : newOder.getHashMap().keySet())
        {
            Book book =this.bookManagement.searchByISBN(key);
            book.setStock(book.getStock()+newOder.getHashMap().get(key));
        }
        bookManagement.writeFile("D:\\module2CODEGYM\\MODULE2_CASESTUDY\\MyBookStore\\Book\\allBook.csv",BookManagement.getAllBookSet());
    }

    public boolean removeByOrderId(String orderId) {
        Order o = this.searchByOrderId(orderId);
        if (o != null) {
            this.orderList.remove(o);
            this.increaseCarQuantity(o);
            this.saveFile();
            return true;
        } else {
            return false;
        }
    }
    public String printOrder() {
        this.readFromFile();
            String string = "";

            Order o;
            for (Iterator var2 = this.orderList.iterator(); var2.hasNext(); string = string + o.toString()) {
                o = (Order) var2.next();
            }

            this.readFromFile();
            return string;
    }
    public void updateQuantity(Order newOrder) {
        HashMap<String, Integer> newHashMap = newOrder.getHashMap();

        Map.Entry e;
        Book var5;
        Iterator var3 = newHashMap.entrySet().iterator();
        while (var3.hasNext())
        {
            e = (Map.Entry)var3.next();
            var5 = this.bookManagement.searchByISBN((String)e.getKey());
            this.processBookQuantity(var5.getIsbn(),(Integer)e.getValue());

        }

    }
    public void updateCustomer(Order newOrder) {
        Customer c = this.customerManagement.searchById(newOrder.getCustomerID());
        if (c != null) {
            c.setCustomerID(newOrder.getCustomerID());
            c.setCustomerName(newOrder.getCustomerName());
            c.setPhoneNumber(newOrder.getPhoneNumber());
            c.setAddress(newOrder.getAddress());
        } else {
            Customer newCustomer = new Customer();
            newCustomer.setCustomerID(newOrder.getCustomerID());
            newCustomer.setCustomerName(newCustomer.getCustomerName());
            newCustomer.setPhoneNumber(newOrder.getPhoneNumber());
            newCustomer.setAddress(newOrder.getAddress());
            System.out.println("Nhập tiền mặt khách hàng ");
            newCustomer.setWallet(new Scanner(System.in).nextDouble());
            System.out.println("Nhập tiền trong thẻ của khách hàng");
            newCustomer.setOnlineWallet(new Scanner(System.in).nextDouble());
            this.customerManagement.add(newCustomer);
        }

        this.customerManagement.saveFile();
    }
    public void processBookQuantity(String bookISBN, int quantity)
    {
        Book book = bookManagement.searchByISBN(bookISBN);
        if(book.getStock()+book.getStockStorage()<quantity)
        {
            System.out.println("Không đủ hàng");
        } else if (book.getStock()<=quantity&&book.getStockStorage()>=quantity) {
            book.setStock(book.getStockStorage()-(book.getStock()-quantity));
            book.setStockStorage(0);
        }else
        {
            book.setStock(book.getStock()-quantity);
        }
        bookManagement.writeFile("D:\\module2CODEGYM\\MODULE2_CASESTUDY\\MyBookStore\\Book\\allBook.csv",BookManagement.getAllBookSet());
    }

    public void saveFile() {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Order o : orderList) {
                bufferedWriter.write(o.toFile());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
        } catch (IOException var5) {
            throw new RuntimeException(var5);
        }
    }
    public void readFromFile() {
        orderList.clear();

        try {
            FileReader fileReader = new FileReader(FILE_PATH);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = "";

            while((line = bufferedReader.readLine()) != null) {
                Order order = this.handleLine(line);
                orderList.add(order);
            }

            bufferedReader.close();
            fileReader.close();
        } catch (IOException var5) {
            throw new RuntimeException(var5);
        }
    }
    public Order handleLine(String line) {
        String[] strings = line.split(",");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate stringsDate = LocalDate.parse(strings[1], formatter);
        Order o = new Order(strings[0], stringsDate, strings[2], strings[3], strings[4], strings[5],Double.parseDouble(strings[6]));

        for(int i = 7; i < strings.length; i += 2) {
            o.addProduct(strings[i], Integer.parseInt(strings[i + 1]));
        }

        return o;
    }
    public void saveFileIfNull() {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write("");

            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException var5) {
            throw new RuntimeException(var5);
        }
    }
}





