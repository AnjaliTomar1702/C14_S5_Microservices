package com.bej.userauthenticationservice.service;


import com.bej.userauthenticationservice.domain.Customer;
import com.bej.userauthenticationservice.exception.CustomerAlreadyExistsException;
import com.bej.userauthenticationservice.exception.InvalidCredentialsException;

public interface CustomerService {

    Customer saveCustomer(Customer user) throws CustomerAlreadyExistsException;
    Customer findCustomerByCustomerIdAndPassword(String id,String password) throws InvalidCredentialsException;



}
