package com.revature.MKPG.beans.Services;

import com.revature.MKPG.beans.Repositories.CustomerRepo;
import com.revature.MKPG.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.Optional;

@Service
public class AuthService {

    private CustomerRepo customerRepo;

    @Autowired
    public AuthService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    /*
    public Customer authenticate(String email, String password) throws AccessDeniedException{
        Optional<Customer> optionalCustomer = customerRepo.findByEmail(email);
        if(optionalCustomer.isPresent() && optionalCustomer.get().getPassword().equals(password)){
            return optionalCustomer.get();
        }else {
            throw new AccessDeniedException("Email or Password mismatch");
        }
    }
     */

}
