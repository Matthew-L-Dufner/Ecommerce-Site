package com.revature.MKPG.beans.Controllers;

import com.revature.MKPG.beans.Services.CartService;
import com.revature.MKPG.beans.Services.ItemService;
import com.revature.MKPG.beans.Services.OrderService;
import com.revature.MKPG.entities.Cart;
import com.revature.MKPG.entities.Item;
import com.revature.MKPG.entities.Order;
import com.revature.MKPG.exceptions.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
    private OrderService service;
    private CartService cartService;
    private ItemService itemService;

    @Autowired
    public OrderController(OrderService orderService, CartService cartService, ItemService itemService) {

        this.service = orderService;
        this.cartService = cartService;
        this.itemService = itemService;
    }

    @GetMapping( "/{orderId}")
    @ResponseStatus(value = HttpStatus.OK)
    public Order getOrderById(@PathVariable Integer orderId) {
        Optional<Order> optionalOrder = service.getOrderById(orderId);

        Order order = null;

        if(optionalOrder.isPresent()) {
            order = optionalOrder.get();
        }else {
            throw new CustomerNotFoundException( "Order id " + orderId + " not found");
        }

        return order;
    }

    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public List<Order> getAllOrders() {
        return service.getAllOrder();
    }

    @PostMapping()
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Order createOrder(@RequestBody Order order) {
        Integer cartId = order.getCart().getCartId();
        Integer itemId = order.getItem().getItemId();
        Optional<Cart> optionalCart = cartService.getCartById(cartId);
        Optional<Item> optionalItem = itemService.getItemById(itemId);

        Cart cart = null;
        Item item = null;

        if (optionalCart.isPresent()) {
            cart = optionalCart.get();
            cart.addOrder(order);

        } else {
            throw new CustomerNotFoundException("Did not find cart id - " + cartId);
        }

        if (optionalItem.isPresent()) {
            item = optionalItem.get();
            item.addOrder(order);

        } else {
            throw new CustomerNotFoundException("Did not find item id - " + itemId);
        }


        service.createOrder(order);

        return order;
    }

    @PutMapping()
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void updateOrder(@RequestBody Order order) {

        service.updateOrder(order);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteOrder(@PathVariable(name = "orderId") Integer id) {
        service.deleteById(id);
    }
}
