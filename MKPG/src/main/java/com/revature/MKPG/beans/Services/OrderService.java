package com.revature.MKPG.beans.Services;

import com.revature.MKPG.beans.Repositories.OrderRepo;
import com.revature.MKPG.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    OrderRepo repo;

    @Autowired
    public OrderService(OrderRepo repo) {
        this.repo = repo;
    }

    public List<Order> getAllOrder() {
        return repo.findAll();
    }

    public Optional<Order> getOrderById(Integer id) {
        return repo.findById(id);
    }

    public Order createOrder(Order order) {
        return repo.save(order);
    }

    public Order updateOrder(Order order) {
        return repo.save(order);
    }

    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
}
