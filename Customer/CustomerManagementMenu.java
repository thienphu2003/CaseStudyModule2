package Customer;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class CustomerManagementMenu {
    CustomerManagement customerManagement = CustomerManagement.getCustomerManagement();

    public CustomerManagementMenu() {
    }

    public void displayMenu() {
        System.out.println("_______________________________________");
        System.out.println("|                MENU                 |");
        System.out.println("---------------------------------------");
        System.out.println("|        Quản lý khách hàng           |");
        System.out.println("|1. Thêm khách hàng                   |");
        System.out.println("|2. Xóa khách hàng                    |");
        System.out.println("|3. Tìm theo ID khách hàng            |");
        System.out.println("|4. Tìm theo tên khách hàng           |");
        System.out.println("|5. Tìm theo số điện thoại khách hàng |");
        System.out.println("|6. Tìm theo địa chỉ khách hàng       |");
        System.out.println("|7. Cập nhật khách hàng               |");
        System.out.println("|8. Hiển thị toàn bộ khách hàng       |");
        System.out.println("|9. Hiển thị theo thứ tự ID           |");
        System.out.println("|10. Hiển thị theo thứ tự tên         |");
        System.out.println("|0. Thoát                             |");
        System.out.println("---------------------------------------");
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
                    this.searchById();
                    break;
                case 4:
                    this.searchByName();
                    break;
                case 5:
                    this.searchByPhoneNumber();
                    break;
                case 6:
                    this.searchByAddress();
                    break;
                case 7:
                    this.update();
                    break;
                case 8:
                    this.printAll();
                    break;
                case 9:
                    this.printByID();
                    break;
                case 10:
                    this.printByName();
                    break;
            }
        }
    }
    public void add() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập id khách hàng");
        String customerId = scanner.nextLine();
        while (this.customerManagement.checkId(customerId)) {
            System.out.println("ID trùng vui lòng nhập lại");
            customerId = scanner.nextLine();
        }
            System.out.println("Nhập tên khách hàng");
            String customerName = scanner.nextLine();
            System.out.println("Nhập số điện thoại khách hàng");
            String phoneNumber = scanner.nextLine();
            System.out.println("Nhập địa chỉ khách hàng");
            String address = scanner.nextLine();
            System.out.println("Nhập số lượng tiền mặt");
            double wallet = scanner.nextDouble();
            System.out.println("Nhập số lượng tiền trong thẻ");
            double onWallet = scanner.nextDouble();
            Customer newCustomer = new Customer.CustomerBuilder().customerID(customerId).customerName(customerName).phoneNumber(phoneNumber).address(address).onlineWallet(onWallet).wallet(wallet).build();
            this.customerManagement.add(newCustomer);
        }
    public void remove() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập id khách hàng");
        String customerId = scanner.nextLine();
        if (this.customerManagement.removeById(customerId)) {
            System.out.println("Xóa Thành Công");
        } else {
            System.out.println("Xóa Thất Bại");
        }

    }
    public void searchById() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập id khách hàng");
        String customerId = scanner.nextLine();
        Customer searchCustomerById = this.customerManagement.searchById(customerId);
        if (searchCustomerById != null) {
            System.out.println(searchCustomerById);
        } else {
            System.out.println("Không tìm thấy thông tin khách hàng");
        }
    }
    public void searchByName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tên khách hàng");
        String searchCustomerName = scanner.nextLine();
        List<Customer> customerrList = this.customerManagement.searchByName(searchCustomerName);
        if (customerrList.size() != 0) {
            Iterator var4 = customerrList.iterator();

            while(var4.hasNext()) {
                Customer c = (Customer)var4.next();
                System.out.println(c);
            }
        } else {
            System.out.println("Không tìm thấy thông tin khách hàng");
        }
    }
    public void searchByPhoneNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập số điện thoại");
        String searchPhoneNumber = scanner.nextLine();
        Customer searchByPhoneNumber = this.customerManagement.searchByPhoneNumber(searchPhoneNumber);
        if (searchByPhoneNumber != null) {
            System.out.println(searchByPhoneNumber);
        } else {
            System.out.println("Không tìm thấy thông tin khách hàng");
        }

    }
    public void searchByAddress()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập địa chỉ khách hàng");
        String address = scanner.nextLine();
        Customer customer = customerManagement.searchByAddress(address);
        if(customer!=null)
        {
            System.out.println(customer);
        }else
            System.out.println("Không tìm thấy thông tin khách hàng");
    }
    public void update()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập id khách hàng cần cập nhật");
        String customerId = scanner.nextLine();
        System.out.println("Nhập tên khách hàng mới");
        String customerName = scanner.nextLine();
        System.out.println("Nhập số điện thoại mới");
        String phoneNumber = scanner.nextLine();
        System.out.println("Nhập địa chỉ mới");
        String address = scanner.nextLine();
        System.out.println("Nhập số tiền mặt mới");
        double wallet = scanner.nextDouble();
        System.out.println("Nhập số tiền trong thẻ mới");
        double onlineWallet =scanner.nextDouble();
        Customer newCustomer = new Customer.CustomerBuilder().customerID(customerId).customerName(customerName).phoneNumber(phoneNumber).address(address).onlineWallet(onlineWallet).wallet(wallet).build();
        this.customerManagement.updateCustomerById(newCustomer.getCustomerID(), newCustomer);
    }
    public void printAll()
    {
        System.out.println("Hiển thị danh sách toàn bộ khách hàng");
        System.out.println(customerManagement.displayCustomer());
    }
    public void printByID()
    {
        System.out.println("Danh sách khách hàng theo ID");
        System.out.println(customerManagement.arrangeByID());
    }
    public void printByName()
    {
        System.out.println("Danh sách khách hàng theo tên");
        System.out.println(customerManagement.arrangeByName());
    }

    public static void main(String[] args) {
        CustomerManagementMenu menu = new CustomerManagementMenu();
        menu.menu();
    }
}
