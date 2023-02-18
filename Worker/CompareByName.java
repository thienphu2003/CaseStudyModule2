package Worker;

import java.util.Comparator;

public class CompareByName implements Comparator<Worker> {
    @Override
    public int compare(Worker workerOne , Worker workerTwo)
    {
        return workerOne.getName().compareTo(workerTwo.getName());
    }
}
