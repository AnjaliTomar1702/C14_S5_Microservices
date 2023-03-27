package com.bej.userauthenticationservice.security;



import com.bej.userauthenticationservice.domain.Customer;

public interface SecurityTokenGenerator {
    String createToken(Customer user);
}
