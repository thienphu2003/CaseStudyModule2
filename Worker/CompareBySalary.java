package Worker;

import java.util.Comparator;

public class CompareBySalary implements Comparator<Worker> {
    @Override
    public int compare (Worker worker, Worker newWorker)
    {
        if(worker.getTotalSalary()>newWorker.getTotalSalary())
        {
            return 1;
        } else if (worker.getTotalSalary()<newWorker.getTotalSalary()) {
            return -1;
        }
        return 0;
    }
}
