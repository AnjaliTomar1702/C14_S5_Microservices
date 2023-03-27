package com.bej.userauthenticationservice.repository;


import com.bej.userauthenticationservice.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,String> {
    Customer findByCustomerIdAndPassword(String id, String password);
}
