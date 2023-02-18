package Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class CustomerManagement {
    private static List<Customer> customerList = new ArrayList<>();
    private static final String FILE_PATH = "D:\\module2CODEGYM\\MODULE2_CASESTUDY\\MyBookStore\\Customer\\customer.csv";
    private static CustomerManagement customerManagement = new CustomerManagement();
    private CustomerManagement()
    {
        try
        {
            this.readFromFile();
        }catch (Exception e)
        {
            this.saveFileIfNull();
        }
    }
    public static CustomerManagement getCustomerManagement()
    {
        return  customerManagement;
    }
    public void add(Customer customer)
    {
        customerList.add(customer);
        this.saveFile();
    }
    public Customer searchById(String customerId) {
        Iterator<Customer> var2 = customerList.iterator();

        Customer c;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            c = var2.next();
        } while(!c.getCustomerID().equals(customerId));

        return c;
    }
    public List<Customer> searchByName(String customerName) {
        List<Customer> customerrArrayList = new ArrayList();

        for (Customer c : customerList) {
            if (c.getCustomerName().contains(customerName)) {
                customerrArrayList.add(c);
            }
        }

        return customerrArrayList;
    }
    public Customer searchByPhoneNumber(String phoneNumber) {
        Iterator<Customer> var2 = customerList.iterator();

        Customer c;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            c = var2.next();
        } while(!c.getPhoneNumber().equals(phoneNumber));

        return c;
    }

    public Customer searchByAddress(String address) {
        Iterator<Customer> var2 = customerList.iterator();

        Customer c;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            c = var2.next();
        } while(!c.getAddress().equals(address));

        return c;
    }
    public boolean removeById(String customerId) {
        Customer c = this.searchById(customerId);
        if (c != null) {
            customerList.remove(c);
            this.saveFile();
            return true;
        } else {
            return false;
        }
    }
    public List<Customer> displayCustomer() {
        return new ArrayList(customerList);
    }
    public void updateCustomerById(String customerId, Customer newCustomer) {
        Customer c = this.searchById(customerId);
        if (c != null) {
            c.setCustomerName(newCustomer.getCustomerName());
            c.setPhoneNumber(newCustomer.getPhoneNumber());
            c.setAddress(newCustomer.getAddress());
            c.setOnlineWallet(newCustomer.getOnlineWallet());
            c.setWallet(newCustomer.getWallet());
        }

        this.saveFile();
    }
    public boolean checkId(String newId) {
        Iterator<Customer> var2 = customerList.iterator();

        Customer c;
        do {
            if (!var2.hasNext()) {
                return false;
            }

            c = var2.next();
        } while(!c.getCustomerID().equals(newId));

        return true;
    }
    public List<Customer> arrangeByID()
    {
        Collections.sort(customerList,new CompareByID());
        List<Customer> customers = new ArrayList<>(customerList);
        this.saveFile();
        return customers;
    }
    public List<Customer> arrangeByName()
    {
        Collections.sort(customerList,new CompareByName());
        List<Customer> customers = new ArrayList<>(customerList);
        this.saveFile();
        return customers;
    }
    public void saveFile() {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Customer c : customerList) {
                bufferedWriter.write(c.toString());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException var5) {
            throw new RuntimeException(var5);
        }
    }
    public void readFromFile() {
        customerList.clear();

        try {
            FileReader fileReader = new FileReader("D:\\module2CODEGYM\\MODULE2_CASESTUDY\\MyBookStore\\Customer\\customer.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = "";

            while((line = bufferedReader.readLine()) != null) {
                Customer customer = this.handleLine(line);
                customerList.add(customer);
            }

            bufferedReader.close();
            fileReader.close();
        } catch (IOException var5) {
            throw new RuntimeException(var5);
        }
    }
    public Customer handleLine(String line) {
        String[] strings = line.split(",");

        return new Customer.CustomerBuilder().customerID(strings[0]).customerName(strings[1]).address(strings[2])
                .phoneNumber(strings[3]).wallet(Double.parseDouble(strings[4])).onlineWallet(Double.parseDouble(strings[5])).build();
    }
    public void saveFileIfNull() {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write("");

            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException var5) {
            throw new RuntimeException(var5);
        }
    }


}
