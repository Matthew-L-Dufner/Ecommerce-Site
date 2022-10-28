package com.revature.MKPG.beans.Controllers;

import com.revature.MKPG.beans.Services.CustomerService;
import com.revature.MKPG.entities.Customer;
import com.revature.MKPG.exceptions.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    private CustomerService service;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.service = customerService;
    }

    @GetMapping( "/{customerId}")
    @ResponseStatus(value = HttpStatus.OK)
    public Customer getCustomerById(@PathVariable Integer customerId) {
        Optional<Customer> optionalCustomer = service.getCustomerById(customerId);

        Customer customer = null;

        if(optionalCustomer.isPresent()) {
            customer = optionalCustomer.get();
        }else {
            throw new CustomerNotFoundException( "Customer id " + customerId + " not found");
        }

        return customer;
    }

    @GetMapping( "/email/{email}")
    @ResponseStatus(value = HttpStatus.OK)
    public Customer getCustomerByEmail(@PathVariable String email) {
        Optional<Customer> optionalCustomer = service.getCustomerByEmail(email);

        Customer customer = null;

        if(optionalCustomer.isPresent()) {
            customer = optionalCustomer.get();
        }else {
            throw new CustomerNotFoundException( "Customer id " + email + " not found");
        }

        return customer;
    }

    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public List<Customer> getAllCustomers() {
        return service.getAllCustomers();
    }

    @PostMapping()
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Customer createCustomer(@Valid @RequestBody Customer customer) {
        service.createCustomer(customer);
        return customer;
    }

    @PutMapping()
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Customer updateCustomer(@RequestBody Customer customer) {
        service.updateCustomer(customer);
        return customer;
    }

    @DeleteMapping("/{customerId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteCustomer(@PathVariable(name = "customerId") Integer id) {
        service.deleteById(id);
    }
}
