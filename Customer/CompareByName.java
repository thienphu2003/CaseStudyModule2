package Customer;

import java.util.Comparator;

public class CompareByName implements Comparator<Customer> {
    @Override
    public int compare(Customer a, Customer b)
    {
        return a.getCustomerName().compareTo(b.getCustomerName());
    }
}
