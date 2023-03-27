package com.bej.userauthenticationservice.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Customer {
    @Id
    private String customerID;
    private String password;

    public Customer() {
    }

    public Customer(String customerID, String password) {
        this.customerID = customerID;
        this.password = password;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerID=" + customerID +
                ", password='" + password + '\'' +
                '}';
    }
}
