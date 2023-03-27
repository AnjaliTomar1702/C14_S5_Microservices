package com.bej.userauthenticationservice.controller;

import com.bej.userauthenticationservice.domain.Customer;
import com.bej.userauthenticationservice.exception.InvalidCredentialsException;
import com.bej.userauthenticationservice.exception.CustomerAlreadyExistsException;
import com.bej.userauthenticationservice.security.SecurityTokenGenerator;

import com.bej.userauthenticationservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {
    private CustomerService userService;
    private SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    public CustomerController(CustomerService userService, SecurityTokenGenerator securityTokenGenerator) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }
    @PostMapping("/user")
    public ResponseEntity<?> saveUser(@RequestBody Customer user) throws CustomerAlreadyExistsException {
        return new ResponseEntity<>(userService.saveCustomer(user),HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Customer user) throws InvalidCredentialsException
    {
        Customer retrievedUser = userService.findCustomerByCustomerIdAndPassword(user.getCustomerID(),user.getPassword());
        if(retrievedUser==null)
        {
            throw new InvalidCredentialsException();
        }
        String token = securityTokenGenerator.createToken(user);
        return new ResponseEntity<>(token,HttpStatus.OK);
    }
}

