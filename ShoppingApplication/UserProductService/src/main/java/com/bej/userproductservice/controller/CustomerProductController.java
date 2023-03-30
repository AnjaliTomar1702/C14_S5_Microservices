package com.bej.userproductservice.controller;


import com.bej.userproductservice.domain.Product;
import com.bej.userproductservice.service.CustomerProductService;
        import com.bej.userproductservice.domain.Customer;
        import com.bej.userproductservice.exception.ProductNotFoundException;
        import com.bej.userproductservice.exception.CustomerAlreadyExistsException;
        import com.bej.userproductservice.exception.CustomerNotFoundException;
        import io.jsonwebtoken.Claims;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;

        import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1")
public class CustomerProductController {
    private CustomerProductService customerProductService;
    private ResponseEntity<?> responseEntity;
    @Autowired
    public CustomerProductController(CustomerProductService customerProductService) {
        this.customerProductService = customerProductService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Customer user) throws CustomerAlreadyExistsException {
        try {
            responseEntity =  new ResponseEntity<>(customerProductService.registerNewCustomer(user), HttpStatus.CREATED);
        }
        catch(CustomerAlreadyExistsException e)
        {
            throw new CustomerAlreadyExistsException();
        }
        return responseEntity;
    }
    @PostMapping("/user/product")
    public ResponseEntity<?> saveUserProductToList(@RequestBody Product product, HttpServletRequest request) throws CustomerNotFoundException {
        try {
            System.out.println("header" +request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("id from claims :: " + claims.getSubject());
            String id = claims.getSubject();
            System.out.println("id :: "+id);
            responseEntity = new ResponseEntity<>(customerProductService.saveCustomerProduct(product, id), HttpStatus.CREATED);
        }
        catch (CustomerNotFoundException e)
        {
            throw new CustomerNotFoundException();
        }
        return responseEntity;
    }
    @GetMapping("/user/products")
    public ResponseEntity<?> getAllUserProductFromList(HttpServletRequest request) throws CustomerNotFoundException {
        try{
            System.out.println("header" +request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("email from claims :: " + claims.getSubject());
            String email = claims.getSubject();
            System.out.println("email :: "+email);
            responseEntity = new ResponseEntity<>(customerProductService.getAllProductOfCustomer(email), HttpStatus.OK);
        }catch(CustomerNotFoundException e)
        {
            throw new CustomerNotFoundException();
        }
        return responseEntity;
    }
    @DeleteMapping("/user/{productId}")
    public ResponseEntity<?> deleteUserProductFromList(@PathVariable String productId,HttpServletRequest request)
            throws CustomerNotFoundException, ProductNotFoundException
    {
        Claims claims = (Claims) request.getAttribute("claims");
        System.out.println("id from claims :: " + claims.getSubject());
        String id = claims.getSubject();
        System.out.println("id :: "+id);
        try {
            responseEntity = new ResponseEntity<>(customerProductService.deleteProductOfACustomer(id, productId), HttpStatus.OK);
        } catch (CustomerNotFoundException | ProductNotFoundException m) {
            throw new ProductNotFoundException();
        }
        return responseEntity;
    }
}

