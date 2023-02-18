package Customer;

import java.util.Comparator;

public class CompareByID implements Comparator<Customer> {
    @Override
    public int compare(Customer a, Customer b)
    {
        return a.getCustomerID().compareTo(b.getCustomerID());
    }
}
