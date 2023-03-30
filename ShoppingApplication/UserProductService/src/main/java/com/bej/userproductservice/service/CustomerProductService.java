package com.bej.userproductservice.service;

import com.bej.userproductservice.domain.Customer;
import com.bej.userproductservice.domain.Product;
import com.bej.userproductservice.exception.CustomerAlreadyExistsException;
import com.bej.userproductservice.exception.CustomerNotFoundException;
import com.bej.userproductservice.exception.ProductNotFoundException;

import java.util.List;

public interface CustomerProductService {
    Customer registerNewCustomer(Customer user) throws CustomerAlreadyExistsException;
    Customer saveCustomerProduct(Product product, String id) throws CustomerNotFoundException;
    Customer deleteProductOfACustomer(String id,String productId) throws CustomerNotFoundException, ProductNotFoundException;
    List<Product> getAllProductOfCustomer(String id) throws CustomerNotFoundException;
}
