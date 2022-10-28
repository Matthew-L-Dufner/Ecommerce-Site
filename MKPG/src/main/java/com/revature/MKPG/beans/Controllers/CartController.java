package com.revature.MKPG.beans.Controllers;

import com.revature.MKPG.beans.Services.CartService;
import com.revature.MKPG.entities.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cart")
public class CartController {
    private CartService service;

    @Autowired
    public CartController(CartService cartService) {
        this.service = cartService;
    }





    @GetMapping(value = "/{cartId}")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody Cart getCartById(@PathVariable Integer cartId) {
        Optional<Cart> optionalCart = service.getCartById(cartId);
        return optionalCart.get();
    }

    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody List<Cart> getAllCart() {
        return service.getAllCart();
    }

    @PutMapping()
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void createCart(@RequestBody Cart cart){

        service.createCart(cart);
    }

    @PostMapping()
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void updateCart(@RequestBody Cart cart) {
        service.updateCart(cart);
    }

    @DeleteMapping(value = "/{cartId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteCart(@PathVariable(name = "cartId") Integer id) {
        service.deleteById(id);
    }

}