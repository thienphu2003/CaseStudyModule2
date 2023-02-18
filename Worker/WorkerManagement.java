package Worker;

import Order.Order;
import Worker.Worker;
import Worker.Worker;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class WorkerManagement {
    private static List<Worker> workers = new ArrayList<>();
    private static final String FILE_PATH = "D:\\module2CODEGYM\\MODULE2_CASESTUDY\\MyBookStore\\Worker\\workers.csv";

    private static WorkerManagement workerManagement = new WorkerManagement();

    private WorkerManagement() {
        this.readFromFile();
    }


    public static WorkerManagement getWorkerManagement() {
        return workerManagement;
    }

    public static List<Worker> getAllWorkers() {
        return workers;
    }

    private static double num ;

    public void count()
    {
        this.readFromFile();
        for(Worker worker :workers)
        {
            num+=worker.getTotalSalary();
        }
    }

    public static double getNum() {
        return num;
    }

    public void countSalary(Worker worker)
    {
        int salary = 0;
        if(worker.getType().equals(Type.FULLTIME))
        {
            if(worker.getTotalShift()>=25)
            {
                salary=salary+(worker.getType().getValue()*worker.getTotalShift())*2;
            }else
            {
                salary=salary+(worker.getType().getValue()*worker.getTotalShift());
            }
        } else if (worker.getType().equals(Type.PARTTIME)) {
            if(worker.getTotalShift()>=25)
            {
                salary=salary+(worker.getType().getValue()*worker.getTotalShift())*2;
            }else
            {
                salary=salary+(worker.getType().getValue()*worker.getTotalShift());
            }
        }
        worker.setTotalSalary(salary);
    }

    public void add(Worker worker)
    {
        this.countSalary(worker);
        workers.add(worker);
        this.writeFile();
    }

    public Worker searchById(String Id) {
        Iterator<Worker> var2 = workers.iterator();

        Worker c;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            c = (Worker)var2.next();
        } while(!c.getId().equals(Id));
        this.writeFile();

        return c;
    }
    public List<Worker> searchByName(String Name) {
        List<Worker> WorkerrArrayList = new ArrayList();

        for (Worker c : workers) {
            if (c.getName().contains(Name)) {
                WorkerrArrayList.add(c);
            }
        }
        this.writeFile();

        return WorkerrArrayList;
    }
    public Worker searchByPhoneNumber(String phoneNumber) {
        Iterator<Worker> var2 = workers.iterator();

        Worker c;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            c = var2.next();
        } while(!c.getPhoneNumber().equals(phoneNumber));
        this.writeFile();

        return c;
    }
    public boolean removeById(String Id) {
        Worker c = this.searchById(Id);
        if (c != null) {
            workers.remove(c);
            this.writeFile();
            return true;
        } else {
            return false;
        }
    }
    public List<Worker> displayWorkers() {
        return new ArrayList(workers);
    }
    public void updateWorkerById(String WorkerId, Worker newWorker) {
        Worker c = this.searchById(WorkerId);
        if (c != null) {
            c.setName(newWorker.getName());
            c.setPhoneNumber(newWorker.getPhoneNumber());
            c.setType(newWorker.getType());
            c.setTotalShift(newWorker.getTotalShift());
            c.setTotalSalary(newWorker.getTotalSalary());
        }

        this.writeFile();
    }
    public boolean checkId(String newId) {
        Iterator<Worker> var2 = workers.iterator();

        Worker c;
        do {
            if (!var2.hasNext()) {
                return false;
            }

            c = var2.next();
        } while(!c.getId().equals(newId));

        return true;
    }
    public List<Worker> arrangeByID()
    {
        Collections.sort(workers,new CompareByID());
        List<Worker> Workers = new ArrayList<>(workers);
        return Workers;
    }
    public List<Worker> arrangeByName()
    {
        Collections.sort(workers,new CompareByName());
        List<Worker> Workers = new ArrayList<>(workers);
        return Workers;
    }
    public List<Worker> listAwardWorker()
    {
        List<Worker> newWorkerList =new ArrayList<>();
        for(Worker worker : workers)
        {
            if(worker.award())
            {
                newWorkerList.add(worker);
            }
        }
        this.writeFile();
        return newWorkerList;
    }
    public List<Worker> arrangeBySalary()
    {
        Collections.sort(workers,new CompareBySalary());
        List<Worker> workerList = new ArrayList<>(workers);
        return workerList;
    }


    public void writeFile() {
        try {
            FileWriter engine = new FileWriter(FILE_PATH);
            BufferedWriter bf = new BufferedWriter(engine);
            for (Worker worker : workers) {
                bf.write(worker.toFile());
                bf.newLine();
            }
            bf.close();
            engine.close();
        } catch (IOException e) {
            throw new IllegalArgumentException("Không thể ghi ra File");
        }
    }

    public void readFromFile() {
        workers.clear();
        try {
            FileReader engine = new FileReader(FILE_PATH);
            BufferedReader bf = new BufferedReader(engine);
            String line = "";
            while ((line = bf.readLine()) != null) {
                Worker worker = this.handleLine(line);
                workers.add(worker);
            }
            bf.close();
            engine.close();
        } catch (IOException e) {
            throw new IllegalArgumentException("Không thể đọc từ File");
        }
    }

    public  Worker handleLine(String line) {
        String[] strings = line.split(",");

        return new Worker.WorkerBuilder().name(strings[0]).id(strings[1]).phoneNumber(strings[2]).type(Type.valueOf(strings[3])).totalShift(Integer.parseInt(strings[4])).totalSalary(Integer.parseInt(strings[5])).build();

    }
}
