package Worker;

import java.util.Comparator;

public class CompareByID implements Comparator<Worker> {
    @Override
    public int compare(Worker workerOne , Worker workerTwo)
    {
        return workerOne.getId().compareTo(workerTwo.getId());
    }
}
