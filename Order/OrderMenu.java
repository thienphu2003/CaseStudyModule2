package Order;


import Customer.*;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import Book.*;

public class OrderMenu {
    OrderManagement orderManagement = OrderManagement.getOrderManagement();
    CustomerManagement customerManagement = CustomerManagement.getCustomerManagement();
    BookManagement bookManagement = BookManagement.getBookManagement();
    BookMenu bookMenu = new BookMenu();

    public OrderMenu() {
    }

    public void displayMenu() {
        System.out.println("_________________________________");
        System.out.println("|               MENU            |");
        System.out.println("---------------------------------");
        System.out.println("|         Quản lý hóa đơn       |");
        System.out.println("|1. Thêm hóa đơn                |");
        System.out.println("|2. Xóa hóa đơn                 |");
        System.out.println("|3. Tìm theo ID hóa đơn         |");
        System.out.println("|4. Tìm theo tên khách hàng     |");
        System.out.println("|5. In hóa đơn                  |");
        System.out.println("|0. Thoát                       |");
        System.out.println("---------------------------------");
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        int choose = -1;

        while(choose != 0) {
            this.displayMenu();
            System.out.println("Nhập số");
            choose = scanner.nextInt();
            scanner.nextLine();
            switch (choose) {
                case 1:
                    this.add();
                    break;
                case 2:
                    this.remove();
                    break;
                case 3:
                    this.searchByOrderId();
                    break;
                case 4:
                    this.searchByCustomerName();
                    break;
                case 5:
                    this.printOrder();
            }
        }

    }

    private void add() {
        Order newOrder;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Danh sách sách còn hàng");
        List<Book> productList = this.bookManagement.inStock();
        if(productList.size()==0)
        {
            System.out.println("Cửa hàng chưa có sách !.Hãy thêm sách");
            bookMenu.menu();
        }else
        {
            Iterator var3 = productList.iterator();

            while(var3.hasNext()) {
                Book p = (Book) var3.next();
                System.out.println(p);
            }
        }

        System.out.println("Nhập id khách hàng");
        String customerId = scanner.nextLine();
        Customer c =customerManagement.searchById(customerId);
        if(c!=null)
        {
            System.out.println("Nhập phương thức thanh toán");
            String paymentMethod =scanner.nextLine();
            newOrder= new Order(c.getCustomerID(),c.getCustomerName(),c.getPhoneNumber(),c.getAddress(),paymentMethod);
        }else
        {
            System.out.println("Tên khách hàng");
            String customerName = scanner.nextLine();
            System.out.println("Nhập số điện thoại khách hàng");
            String phoneNumber = scanner.nextLine();
            System.out.println("Nhập địa chỉ khách hàng");
            String address = scanner.nextLine();
            System.out.println("Nhập phương thức thanh toán");
            String paymentMethod =scanner.nextLine();
//            Customer customer = new Customer.CustomerBuilder().customerID(customerId).customerName(customerName).phoneNumber(phoneNumber).address(address).wallet(wallet).onlineWallet(onlineWallet).build();
//            customerManagement.add(customer);
            newOrder = new Order(customerId, customerName, phoneNumber,address,paymentMethod);
            orderManagement.updateCustomer(newOrder);
        }

        while(true) {
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Dừng thêm sản phẩm");
            int choose = scanner.nextInt();
            scanner.nextLine();
            if (choose != 1) {
                this.orderManagement.add(newOrder);
                return;
            }

            System.out.println("Nhập isbn sách");
            String bookISBN = scanner.nextLine();
            System.out.println("Nhập số lượng mua");
            int quantity = scanner.nextInt();
            scanner.nextLine();
            Book b = this.bookManagement.searchByISBN(bookISBN);
            if (quantity > b.getStock()+b.getStockStorage()) {
                System.out.println("Sách không còn hàng");
                PrintStream var10000 = System.out;
                String var10001 = b.getTitle();
                var10000.println(var10001 + " còn " + b.getStock()+" và trong kho còn "+b.getStockStorage());
                this.orderManagement.removeByOrderId(newOrder.getOrderID());
            } else {
                newOrder.addProduct(bookISBN, quantity);
            }
        }
    }

    private void remove() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập id đơn hàng cần xóa");
        String orderId = scanner.nextLine();
        if (this.orderManagement.removeByOrderId(orderId)) {
            System.out.println("Đã xóa");
        } else {
            System.out.println("Xóa thất bại");
        }

    }

    private void searchByOrderId() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập id đơn hàng");
        String orderId = scanner.nextLine();
        Order searchByOrderId = this.orderManagement.searchByOrderId(orderId);
        if (searchByOrderId != null) {
            System.out.println(searchByOrderId);
        } else {
            System.out.println("Không tìm thấy id hóa đơn");
        }

    }

    private void searchByCustomerName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tên khách hàng");
        String customerName = scanner.nextLine();
        List<Order> searchByCustomerName = this.orderManagement.searchByCustomerName(customerName);
        if (searchByCustomerName.size() != 0) {
            Iterator var4 = searchByCustomerName.iterator();

            while(var4.hasNext()) {
                Order o = (Order)var4.next();
                System.out.println(o);
            }
        } else {
            System.out.println("Không tìm thấy khách hàng");
        }

    }

    private void printOrder() {
        System.out.println(this.orderManagement.printOrder());
    }

}

