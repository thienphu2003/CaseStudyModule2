import Book.*;
import Customer.*;
import Order.*;
import Worker.Worker;
import Worker.WorkerManagement;
import Worker.WorkerMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PhuStore {

    private static double initialMoney = 1000000000;

    public static double getInitialMoney() {
        return initialMoney;
    }

    public static void setInitialMoney(double initialMoney) {
        PhuStore.initialMoney = initialMoney;
    }

    public static void main(String[] args) {
        int choose;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("1. Quản lý khách hàng");
            System.out.println("2. Quản lý sách");
            System.out.println("3. Quản lý đơn hàng");
            System.out.println("4. Quản lý nhân viên");
            System.out.println("5.Hiển thị doanh thu cửa hàng");
            choose = scanner.nextInt();
            scanner.nextLine();
            if (choose == 1) {
                (new CustomerManagementMenu()).menu();
            } else if (choose == 2) {
                (new BookMenu()).menu();
            } else if (choose == 3) {
                (new OrderMenu()).menu();
            } else if (choose == 4) {
                (new WorkerMenu()).menu();
            } else if (choose == 5) {
                System.out.println("Doanh thu cửa hàng là ");
                WorkerManagement.getWorkerManagement().count();
                OrderManagement.getOrderManagement().count();
                System.out.println(OrderManagement.getNum()-WorkerManagement.getNum());
                System.out.println(OrderManagement.getNum()<WorkerManagement.getNum()?"Lỗ vốn":"Lời");
                PhuStore.setInitialMoney(OrderManagement.getNum()-WorkerManagement.getNum());
                if(PhuStore.getInitialMoney()<0)
                {
                    System.out.println("Cửa hàng phá sản");
                }
            }
        } while (choose != 0);

    }
}
