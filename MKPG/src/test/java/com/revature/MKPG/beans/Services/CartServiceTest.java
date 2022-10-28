package com.revature.MKPG.beans.Services;

import com.revature.MKPG.beans.Repositories.CartRepository;
import com.revature.MKPG.entities.Cart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    CartRepository cartRepository;

    @InjectMocks
    CartService cartService;

    Cart cart;

    @BeforeEach
    public void setup(){
        try{
         cart = Cart.builder()
        .cartId(1)
        //.customer()
        //.orders()
        .total(123)
        .checkOutDate(new SimpleDateFormat("dd/MM/yyyy").parse("16/05/2022"))
                 .build();
    } catch (
    ParseException e) {
        throw new RuntimeException(e);
    }
    }

    @Test
    void getAllCart() {
    }

    @Test
    void getCartById() {
    }

    @Test
    void createCart() {
    }

    @Test
    void updateCart() {
    }

    @Test
    void deleteById() {
    }
}