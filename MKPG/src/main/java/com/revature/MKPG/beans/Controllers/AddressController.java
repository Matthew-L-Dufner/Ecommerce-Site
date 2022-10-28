package com.revature.MKPG.beans.Controllers;

import com.revature.MKPG.beans.Services.AddressService;
import com.revature.MKPG.beans.Services.CustomerService;
import com.revature.MKPG.entities.Address;
import com.revature.MKPG.entities.Customer;
import com.revature.MKPG.exceptions.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/addresses")
public class AddressController {

    private AddressService addressService;
    private CustomerService customerService;

    @Autowired
    public AddressController(AddressService addressService, CustomerService customerService) {
        this.addressService = addressService;
        this.customerService = customerService;
    }

    @RequestMapping(value = "/{addressId}", method = RequestMethod.GET)
    public @ResponseBody Address getAddressById(@PathVariable Integer addressId){
        Optional<Address> optionalAddress = addressService.getAddressById((addressId));
        return optionalAddress.get();
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody List<Address> getAllAddresses(){ return addressService.getAddresses();}

    @PostMapping
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Address createAddress(@RequestBody Address address){
        searchCustomer(address);
        addressService.createAddress(address);

        return address;
    }

    @PutMapping
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Address updateAddress(@RequestBody Address address){
        searchCustomer(address);
        addressService.updateAddress(address);

        return address;
    }

    private void searchCustomer(@RequestBody Address address) {
        Integer customerId = address.getCustomer().getCustomerId();
        Optional<Customer> optionalCustomer = customerService.getCustomerById(customerId);
        Customer customer = null;

        if (optionalCustomer.isPresent()) {
            customer = optionalCustomer.get();
            customer.addAddress(address);

        } else {
            throw new CustomerNotFoundException("Did not find customer id - " + customerId);
        }
    }

    @DeleteMapping(value = "/{addressId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteCustomer(@PathVariable(name = "addressId") Integer id) { addressService.deleteById(id);}

}
