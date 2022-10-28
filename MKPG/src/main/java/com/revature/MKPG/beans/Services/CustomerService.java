package com.revature.MKPG.beans.Services;

import com.revature.MKPG.beans.Repositories.CustomerRepo;
import com.revature.MKPG.entities.Customer;
import com.revature.MKPG.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    CustomerRepo repo;

    @Autowired
    public CustomerService(CustomerRepo repo) {
        this.repo = repo;
    }

    public Customer createCustomer(Customer customer) {
        Optional<Customer> savedCustomer = repo.findByEmail(customer.getEmail());
        if(savedCustomer.isPresent()){
            throw new ResourceNotFoundException("Customer already exist with given email: " + customer.getEmail());
        }
        return repo.save(customer);
    }
    public List<Customer> getAllCustomers() {
        return repo.findAll();
    }

    public Optional<Customer> getCustomerById(Integer id) {
        return repo.findById(id);
    }

    public Optional<Customer> getCustomerByEmail(String email){ return repo.findByEmail(email);}

    public Customer updateCustomer(Customer customer) {
        return repo.save(customer);
    }

    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
}
