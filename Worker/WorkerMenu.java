package Worker;


import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class WorkerMenu {
    WorkerManagement workerManagement = WorkerManagement.getWorkerManagement();

    public WorkerMenu() {
    }

    public void displayMenu() {
        System.out.println("_______________________________________");
        System.out.println("|                MENU                 |");
        System.out.println("---------------------------------------");
        System.out.println("|        Quản lý nhân viên            |");
        System.out.println("|1. Thêm nhân viên                    |");
        System.out.println("|2. Xóa nhân viên                     |");
        System.out.println("|3. Tìm theo ID nhân viên             |");
        System.out.println("|4. Tìm theo tên nhân viên            |");
        System.out.println("|5. Hiển thị danh sách theo lương     |");
        System.out.println("|6. Hiển thị danh sách được thưởng    |");
        System.out.println("|7. Cập nhật nhân viên                |");
        System.out.println("|8. Hiển thị toàn bộ nhân viên        |");
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
                    this.showBySalary();
                    break;
                case 6:
                    this.showAward();
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
        System.out.println("Nhập id nhân viên");
        String WorkerId = scanner.nextLine();
        while (this.workerManagement.checkId(WorkerId)) {
            System.out.println("ID trùng vui lòng nhập lại");
            WorkerId = scanner.nextLine();
        }
        System.out.println("Nhập tên nhân viên");
        String WorkerName = scanner.nextLine();
        System.out.println("Nhập số điện thoại nhân viên");
        String phoneNumber = scanner.nextLine();
        System.out.println("Nhập số ca làm việc");
        int totalShift = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Nhập loại nhân viên");
        Type type = Type.valueOf(scanner.nextLine());
        Worker newWorker = new Worker.WorkerBuilder().id(WorkerId).name(WorkerName).phoneNumber(phoneNumber).totalShift(totalShift).type(type).build();
        this.workerManagement.add(newWorker);
    }
    public void remove() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập id nhân viên");
        String WorkerId = scanner.nextLine();
        if (this.workerManagement.removeById(WorkerId)) {
            System.out.println("Xóa Thành Công");
        } else {
            System.out.println("Xóa Thất Bại");
        }

    }
    public void searchById() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập id nhân viên");
        String WorkerId = scanner.nextLine();
        Worker searchWorkerById = this.workerManagement.searchById(WorkerId);
        if (searchWorkerById != null) {
            System.out.println(searchWorkerById.toString());
        } else {
            System.out.println("Không tìm thấy thông tin nhân viên");
        }
    }
    public void searchByName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tên nhân viên");
        String searchWorkerName = scanner.nextLine();
        List<Worker> WorkerrList = this.workerManagement.searchByName(searchWorkerName);
        if (WorkerrList.size() != 0) {
            Iterator var4 = WorkerrList.iterator();

            while(var4.hasNext()) {
                Worker c = (Worker)var4.next();
                System.out.println(c);
            }
        } else {
            System.out.println("Không tìm thấy thông tin nhân viên");
        }
    }
    public void searchByPhoneNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập số điện thoại");
        String searchPhoneNumber = scanner.nextLine();
        Worker searchByPhoneNumber = this.workerManagement.searchByPhoneNumber(searchPhoneNumber);
        if (searchByPhoneNumber != null) {
            System.out.println(searchByPhoneNumber);
        } else {
            System.out.println("Không tìm thấy thông tin nhân viên");
        }

    }
    public void update()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập id nhân viên cần cập nhật");
        String WorkerId = scanner.nextLine();
        System.out.println("Nhập tên nhân viên mới");
        String WorkerName = scanner.nextLine();
        System.out.println("Nhập số điện thoại mới");
        String phoneNumber = scanner.nextLine();
        System.out.println("Nhập số ca làm việc mới");
        int shift = scanner.nextInt();
        System.out.println("Nhập kiểu nhân viên mới");
        Type type = Type.valueOf(scanner.nextLine());
        Worker newWorker = new Worker.WorkerBuilder().id(WorkerId).name(WorkerName).phoneNumber(phoneNumber).type(type).totalShift(shift).build();
        this.workerManagement.updateWorkerById(newWorker.getId(), newWorker);
    }
    public void printAll()
    {
        System.out.println("Hiển thị danh sách toàn bộ nhân viên");
        System.out.println(workerManagement.displayWorkers());
    }
    public void printByID()
    {
        System.out.println("Danh sách nhân viên theo ID");
        System.out.println(workerManagement.arrangeByID());
    }
    public void printByName()
    {
        System.out.println("Danh sách nhân viên theo tên");
        System.out.println(workerManagement.arrangeByName());
    }
    public void showAward()
    {
        System.out.println("Danh sách nhân viên được thưởng");
        System.out.println(workerManagement.listAwardWorker());
    }
    public void showBySalary()
    {
        System.out.println("Danh sách nhân viên theo lương");
        System.out.println(workerManagement.arrangeBySalary());
    }

}
