package com.revature.MKPG.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Integer cartId;
    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Order> orders;

    @Column
    private Integer total;
    @Temporal(TemporalType.DATE)
    @Column
    private Date checkOutDate;

    public Cart() {
    }

    public Cart(Integer cartId, Integer total, Date checkOutDate) {
        this.cartId = cartId;
        this.total = total;
        this.checkOutDate = checkOutDate;
    }
    public Cart(Integer cartId, Customer customer, List<Order> orders, Integer total, Date checkOutDate) {
        this.cartId = cartId;
        this.customer = customer;
        this.orders = orders;
        this.total = total;
        this.checkOutDate = checkOutDate;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order) {
        if (this.orders == null) {
            this.orders = new ArrayList<>();
        }
        getOrders().add(order);
        order.setCart(this);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", customer=" + customer +
                ", orders=" + orders +
                ", total=" + total +
                ", checkOutDate=" + checkOutDate +
                '}';
    }
}