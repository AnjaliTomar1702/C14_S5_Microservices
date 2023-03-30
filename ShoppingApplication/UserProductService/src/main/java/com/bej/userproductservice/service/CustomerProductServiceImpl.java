package com.bej.userproductservice.service;


import com.bej.userproductservice.domain.Product;
        import com.bej.userproductservice.exception.ProductNotFoundException;
        import com.bej.userproductservice.exception.CustomerNotFoundException;
        import com.bej.userproductservice.exception.CustomerAlreadyExistsException;
        import com.bej.userproductservice.repository.CustomerProductRepository;
        import com.bej.userproductservice.domain.Customer;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.Arrays;
        import java.util.List;
@Service
public class CustomerProductServiceImpl implements CustomerProductService{
    private CustomerProductRepository userMovieRepository;
    @Autowired
    public CustomerProductServiceImpl(CustomerProductRepository userMovieRepository) {
        this.userMovieRepository = userMovieRepository;
    }

    @Override
    public Customer registerNewCustomer(Customer user) throws CustomerAlreadyExistsException {
        if(userMovieRepository.findById(user.getCustomerId()).isPresent())
        {
            throw new CustomerAlreadyExistsException();
        }
        return userMovieRepository.save(user);
    }

    @Override
    public Customer saveCustomerProduct(Product product, String id) throws CustomerNotFoundException {
        if(userMovieRepository.findById(id).isEmpty())
        {
            throw new CustomerNotFoundException();
        }
        Customer user = userMovieRepository.findByCustomerId(id);
        if(user.getProductList() == null)
        {
            user.setProductList(Arrays.asList(product));
        }
        else {
            List<Product> movies = user.getProductList();
            movies.add(product);
            user.setProductList(movies);
        }
        return userMovieRepository.save(user);
    }

    @Override
    public Customer deleteProductOfACustomer(String id, String movieId) throws CustomerNotFoundException, ProductNotFoundException {
        boolean movieIdIsPresent = false;
        if(userMovieRepository.findById(id).isEmpty())
        {
            throw new CustomerNotFoundException();
        }
        Customer user = userMovieRepository.findById(id).get();
        List<Product> movies = user.getProductList();
        movieIdIsPresent = movies.removeIf(x->x.getProductCode().equals(movieId));
        if(!movieIdIsPresent)
        {
            throw new ProductNotFoundException();
        }
        user.setProductList(movies);
        return userMovieRepository.save(user);
    }

    @Override
    public List<Product> getAllProductOfCustomer(String email) throws CustomerNotFoundException {
        if(userMovieRepository.findById(email).isEmpty())
        {
            throw new CustomerNotFoundException();
        }
        return userMovieRepository.findById(email).get().getProductList();
    }


}
