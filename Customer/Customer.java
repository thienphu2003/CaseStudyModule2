package Customer;
import Book.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Customer {
    private String customerID ;
    private String customerName;
    private String phoneNumber;
    private String address;
    private HashMap<String,Integer> cart;
    private double wallet;
    private double onlineWallet;
    public Customer()
    {}
    public Customer(CustomerBuilder builder)
    {
        this.customerID=builder.customerID;
        this.customerName=builder.customerName;
        this.phoneNumber=builder.phoneNumber;
        this.address=builder.address;
        this.wallet=builder.wallet;
        this.onlineWallet=builder.onlineWallet;
        this.cart = new HashMap<>();
    }

    public String getCustomerID() {
        return customerID;
    }

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    public double getOnlineWallet() {
        return onlineWallet;
    }

    public void setOnlineWallet(double onlineWallet) {
        this.onlineWallet = onlineWallet;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public HashMap<String, Integer> getCart() {
        return cart;
    }

    public void setCart(HashMap<String,Integer> cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return this.customerID + ", "+this.customerName + ", " + this.address + ", "+this.phoneNumber+", "
                +this.wallet+", "+this.onlineWallet;
    }



    public static class CustomerBuilder{
        private String customerID ;
        private String customerName;
        private String phoneNumber;
        private String address;
        private double wallet;
        private double onlineWallet;

        public CustomerBuilder(){}
        public CustomerBuilder customerID(String customerID)
        {
            this.customerID=customerID;
            return this;
        }
        public CustomerBuilder customerName(String customerName)
        {
            this.customerName=customerName;
            return this;
        }
        public CustomerBuilder phoneNumber(String phoneNumber)
        {
            this.phoneNumber=phoneNumber;
            return this;
        }
        public CustomerBuilder address(String address)
        {
            this.address=address;
            return this;
        }
        public CustomerBuilder wallet(double wallet)
        {
            this.wallet=wallet;
            return this;
        }
        public CustomerBuilder onlineWallet(double onlineWallet)
        {
            this.onlineWallet=onlineWallet;
            return this;
        }
        public Customer build()
        {
            return new Customer(this);
        }
    }
}
