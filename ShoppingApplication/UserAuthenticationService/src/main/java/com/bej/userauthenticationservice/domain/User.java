package com.bej.userauthenticationservice.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    private String customerId;
    private String password;

    public User() {
    }

    public User(String email, String password) {
        this.customerId = email;
        this.password = password;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "customerId='" + customerId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
