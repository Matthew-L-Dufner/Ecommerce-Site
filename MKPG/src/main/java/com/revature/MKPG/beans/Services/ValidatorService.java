package com.revature.MKPG.beans.Services;

import com.revature.MKPG.entities.Address;
import com.revature.MKPG.entities.Customer;
import java.util.regex.Pattern;

public class ValidatorService {
    public boolean validateCustomer(Customer customer) {
    boolean valid = true;

        if(customer.getFirstName() == null || !Pattern.matches("^[A-Za-z' -\\.]{1,25}$", customer.getFirstName())) {
        valid = false;
    }
        if(customer.getLastName() == null || !Pattern.matches("^[A-Za-z' -\\.]{1,25}$", customer.getFirstName())) {
        valid = false;
    }

        if (customer.getEmail() == null || !Pattern.matches("^[A-Za-z0-9\\.\\-_]+@[A-Za-z0-9\\-]+\\.[A-Za-z]{2,5}$", customer.getEmail())) {
            valid = false;
        }

        if(customer.getPhone() == null || !Pattern.matches("[1-9]\\d{2}-[1-9]\\d{2}-\\d{4}", customer.getPhone())) {
            valid = false;
        }

        return valid;
}

    public boolean validateAddress(Address address){
        boolean valid = true;
//        if(address.getStreet() == null || !Pattern.matches("\\d+\\s+([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)", address.getStreet())){
//            valid = false;
//        }
        if(address.getCity() == null || !Pattern.matches("([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)", address.getCity())){
            valid = false;
        }
        if(address.getState() == null || !Pattern.matches("([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)", address.getState())){
            valid = false;
        }
        if(address.getState() == null || !Pattern.matches("\\d{5}", address.getState())){
            valid = false;
        }
        return valid;
    }

}
