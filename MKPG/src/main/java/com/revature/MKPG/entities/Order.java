package com.revature.MKPG.entities;

import lombok.Builder;

import javax.persistence.*;
@Builder
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orders_id")
    private Integer orderId;

    @Column
    private Integer quantity;

    @Column
    private String deliveryDate;

    @Column
    private String status;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "item_id")
    private Item item;

    public Order() {
    }

    public Order(int quantity, String deliveryDate, String status) {
        this.quantity = quantity;
        this.deliveryDate = deliveryDate;
        this.status = status;
    }

    public Order(Integer orderId, Integer quantity, String deliveryDate, String status, Cart cart, Item item) {
        this.orderId = orderId;
        this.quantity = quantity;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.cart = cart;
        this.item = item;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", quantity=" + quantity +
                ", deliveryDate='" + deliveryDate + '\'' +
                ", status='" + status + '\'' +
                ", cart=" + cart +
                ", item=" + item +
                '}';
    }
}