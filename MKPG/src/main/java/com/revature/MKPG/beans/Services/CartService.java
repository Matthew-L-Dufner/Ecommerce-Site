package com.revature.MKPG.beans.Services;

import com.revature.MKPG.beans.Repositories.CartRepository;
import com.revature.MKPG.entities.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    CartRepository repo;
    //ValidatorService validator;

    @Autowired
    public CartService(CartRepository CartRepository) {
        this.repo = CartRepository;
    }

    public List<Cart> getAllCart() {
        return repo.findAll();
    }

    public Optional<Cart> getCartById(Integer id) {
        return repo.findById(id);
    }

    public void createCart(Cart cart) {
        repo.save(cart);
    }

    public void updateCart(Cart cart) {
        repo.save(cart);
    }

    public void deleteById(Integer id) {
        repo.deleteById(id);
    }


}