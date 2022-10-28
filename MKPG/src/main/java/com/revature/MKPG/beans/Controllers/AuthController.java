/*
package com.revature.eCommerce.beans.controllers;


import com.revature.eCommerce.beans.services.AuthService;
import com.revature.eCommerce.dtos.Principal;
import com.revature.eCommerce.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;

@RestController
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/auth")
    public Customer authenticate(@RequestBody Principal principal) throws AccessDeniedException{
        return authService.authenticate(principal.getEmail(), principal.getPassword());
    }

}

 */