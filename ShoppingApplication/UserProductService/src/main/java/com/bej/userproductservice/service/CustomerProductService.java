package com.bej.userproductservice.service;

import com.bej.userproductservice.domain.Customer;
import com.bej.userproductservice.domain.Product;
import com.bej.userproductservice.exception.CustomerAlreadyExistsException;
import com.bej.userproductservice.exception.CustomerNotFoundException;
import com.bej.userproductservice.exception.ProductNotFoundException;

import java.util.List;

public interface CustomerProductService {
    Customer registerNewCustomer(Customer user) throws CustomerAlreadyExistsException;
    Customer saveCustomerProduct(Product movie, String email) throws CustomerNotFoundException;
    Customer deleteProductOfACustomer(String email,String movieId) throws CustomerNotFoundException, ProductNotFoundException;
    List<Product> getAllProductOfCustomer(String email) throws CustomerNotFoundException;
}
