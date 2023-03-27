package com.bej.userauthenticationservice.security;


import com.bej.userauthenticationservice.domain.Customer;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTSecurityTokenGeneratorImpl implements SecurityTokenGenerator {
    public String createToken(Customer user){
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getCustomerID());
        return generateToken(claims,user.getCustomerID());
    }

    public String generateToken(Map<String,Object> claims,String subject) {
        String jwtToken = Jwts.builder().setIssuer("ProductZone")
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,"mysecret")
                //mysecret is the key that has to be shared everytime you do encrypt and decrypt process
                .compact();
        return jwtToken;

    }


}
