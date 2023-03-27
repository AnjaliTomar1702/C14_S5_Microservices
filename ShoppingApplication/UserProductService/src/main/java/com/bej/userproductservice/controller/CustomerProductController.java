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
    private CustomerProductService userMovieService;
    private ResponseEntity<?> responseEntity;
    @Autowired
    public CustomerProductController(CustomerProductService userMovieService) {
        this.userMovieService = userMovieService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Customer user) throws CustomerAlreadyExistsException {
        try {
            responseEntity =  new ResponseEntity<>(userMovieService.registerNewCustomer(user), HttpStatus.CREATED);
        }
        catch(CustomerAlreadyExistsException e)
        {
            throw new CustomerAlreadyExistsException();
        }
        return responseEntity;
    }
    @PostMapping("/user/movie")
    public ResponseEntity<?> saveUserMovieToList(@RequestBody Product movie, HttpServletRequest request) throws CustomerNotFoundException {
        try {
            System.out.println("header" +request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("email from claims :: " + claims.getSubject());
            String email = claims.getSubject();
            System.out.println("email :: "+email);
            responseEntity = new ResponseEntity<>(userMovieService.saveCustomerProduct(movie, email), HttpStatus.CREATED);
        }
        catch (CustomerNotFoundException e)
        {
            throw new CustomerNotFoundException();
        }
        return responseEntity;
    }
    @GetMapping("/user/movies")
    public ResponseEntity<?> getAllUserMoviesFromList(HttpServletRequest request) throws CustomerNotFoundException {
        try{
            System.out.println("header" +request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("email from claims :: " + claims.getSubject());
            String email = claims.getSubject();
            System.out.println("email :: "+email);
            responseEntity = new ResponseEntity<>(userMovieService.getAllProductOfCustomer(email), HttpStatus.OK);
        }catch(CustomerNotFoundException e)
        {
            throw new CustomerNotFoundException();
        }
        return responseEntity;
    }
    @DeleteMapping("/user/{movieId}")
    public ResponseEntity<?> deleteUserProductFromList(@PathVariable String movieId,HttpServletRequest request)
            throws CustomerNotFoundException, ProductNotFoundException
    {
        Claims claims = (Claims) request.getAttribute("claims");
        System.out.println("email from claims :: " + claims.getSubject());
        String email = claims.getSubject();
        System.out.println("email :: "+email);
        try {
            responseEntity = new ResponseEntity<>(userMovieService.deleteProductOfACustomer(email, movieId), HttpStatus.OK);
        } catch (CustomerNotFoundException | ProductNotFoundException m) {
            throw new ProductNotFoundException();
        }
        return responseEntity;
    }
}

