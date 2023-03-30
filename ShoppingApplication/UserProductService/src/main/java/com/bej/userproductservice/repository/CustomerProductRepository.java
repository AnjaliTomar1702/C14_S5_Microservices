package com.bej.userproductservice.repository;

import com.bej.userproductservice.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerProductRepository extends MongoRepository<Customer,String> {
    Customer findByCustomerId(String id);
}
