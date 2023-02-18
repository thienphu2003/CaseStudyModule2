package Order;
import Book.*;
import Customer.*;

import java.time.LocalDate;
import java.util.*;

public class Order {
    private String orderID;
    private LocalDate purchaseDate;
    private String customerName;
    private String customerID;
    private String phoneNumber;
    private String address;
    public static int quantity = 0;
    private double subTotal;
    private double total;
    private int discount;
    private String paymentMethod;
    private HashMap<String, Integer> hashMap;
    BookManagement bookManagement = BookManagement.getBookManagement();
    CustomerManagement customerManagement = CustomerManagement.getCustomerManagement();


    public Order() {
    }
    public Order(String customerId, String customerName, String phoneNumber,String address,String paymentMethod) {
        this.orderID =String.valueOf (new Random().nextInt(100)+1);
        this.purchaseDate = LocalDate.now();
        this.customerID = customerId;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.address=address;
        this.hashMap = new HashMap();
        this.paymentMethod=paymentMethod;
    }
    public Order(String orderId, LocalDate purchaseDate, String customerId, String customerName,String phoneNumber,String address, double total) {
        this.orderID = orderId;
        this.purchaseDate = purchaseDate;
        this.customerID = customerId;
        this.customerName = customerName;
        this.total = total;
        this.phoneNumber = phoneNumber;
        this.address=address;
        this.hashMap = new HashMap();
    }
    public void addProduct(String bookISBN, int quantity) {
        Customer customer = customerManagement.searchById(this.customerID);
        Cart cart = new Cart();
        cart.addBookToCart(bookISBN,quantity);
        customer.setCart(cart.getBooks());
        this.hashMap.put(bookISBN,quantity);
    }

    public double getSubTotal(String bookISBN, int quantity) {
        double sub = 0.0;
        Book p = this.bookManagement.searchByISBN(bookISBN);
        if(quantity>=2&&quantity<4)
        {
            this.discount=Discount.Twenty.getValue();
            sub = p.getPrice() * (double)quantity;
            sub=sub-(sub/100)*discount;
        } else if (quantity>4&&quantity<8) {
            this.discount=Discount.Forty.getValue();
            sub = p.getPrice() * (double)quantity;
            sub=sub-(sub/100)*discount;
        } else if (quantity>8) {
            this.discount=Discount.Eighty.getValue();
            sub = p.getPrice() * (double)quantity;
            sub=sub-(sub/100)*discount;
        }else
        {
            sub = p.getPrice() * (double)quantity;
            this.subTotal = sub;
        }
        return sub;
    }
    public double getTotal() {
        return this.total;
    }
    public HashMap<String, Integer> getHashMap() {
        return this.hashMap;
    }
    public void setTotal() {
        double total = 0.0;

        String key;
        for(Iterator var3 = this.hashMap.keySet().iterator(); var3.hasNext(); total += this.getSubTotal(key, (Integer)this.hashMap.get(key))) {
            key = (String)var3.next();
        }

        this.total = total;
        processCustomerMoney(this);

    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setHashMap(HashMap<String, Integer> hashMap) {
        this.hashMap = hashMap;
    }

    @Override
    public String toString() {
        String out ="";
        Iterator<Map.Entry<String, Integer>> var2 = this.getHashMap().entrySet().iterator();
        Map.Entry entry;
        Book book;
        while (var2.hasNext())
        {
            entry = var2.next();
            book = this.bookManagement.searchByISBN((String)entry.getKey());
            out = out + " " +entry.getKey() + "\t\t\t\t" + book.getTitle() + "\t\t\t\t\t" + book.getPrice() + "\t\t\t" + entry.getValue() + "\t\t\t\t" + this.getSubTotal((String)entry.getKey(), (Integer)entry.getValue()) + "\n";
        }
        return "------------------------------------------------------------------------------------------------------\n|                                                  Hóa đơn                                          |\n------------------------------------------------------------------------------------------------------\nID hóa đơn: " + this.orderID + "\nNgày mua: " + this.purchaseDate + "\nTên khách hàng: " + this.customerName + "\nSĐT khách hàng: " + this.phoneNumber + "\nĐịa chỉ khách hàng: " + this.address+
                "\n------------------------------------------------------------------------------------------------------\n|ISBN sách \t\t\t Tên sách \t\t\t\t\t\tGiá \t\t\tSố lượng \t\t\tThành tiền\t\t|\n------------------------------------------------------------------------------------------------------\n" + out + "\nTổng tiền: " + this.getTotal() + "\n                                          -----Cảm ơn quý khách-----                                  \n\n";


    }
    public String toFile() {
        String out = "";
        out = out + this.orderID + "," + this.purchaseDate + "," + this.customerID + "," + this.customerName + "," + this.phoneNumber + "," + this.address +","+ this.total;

        String bookISBN;
        for(Iterator var2 = this.hashMap.keySet().iterator(); var2.hasNext(); out = out + "," + bookISBN + "," + this.hashMap.get(bookISBN)) {
            bookISBN = (String)var2.next();
        }

        return out;
    }
    public void processCustomerMoney(Order order)
    {
        Customer customer = customerManagement.searchById(order.getCustomerID());
        if(order.getPaymentMethod().equals("Online"))
        {
            customer.setOnlineWallet(customer.getOnlineWallet()-order.getTotal());
        }else
        {
            customer.setWallet(customer.getWallet()-order.getTotal());
        }
        if(customer.getOnlineWallet()<0||customer.getWallet()<0)
        {
            System.out.println("Khách hàng không đủ tiền");
            if(customer.getOnlineWallet()<0)
            {
                System.out.println("Nạp thêm tiền vào thẻ");
                double value =new Scanner(System.in).nextDouble();
                checkValue(value,customer.getWallet());
                customer.setOnlineWallet(customer.getOnlineWallet()+value);
                customer.setWallet(customer.getWallet()-value);
            }else if(customer.getWallet()<0)
            {
                System.out.println("Nạp rút thêm tiền mặt");
                double value =new Scanner(System.in).nextDouble();
                checkValue(value,customer.getOnlineWallet());
                customer.setWallet(customer.getWallet()+value);
                customer.setOnlineWallet(customer.getOnlineWallet()-value);
            }
        }
    }
    public void checkValue(double numberOne,double numberTwo )
    {
        if(numberTwo<numberOne)
        {
            System.out.println("Không thể thực hiện do không đủ tiền");
        }
    }
}
