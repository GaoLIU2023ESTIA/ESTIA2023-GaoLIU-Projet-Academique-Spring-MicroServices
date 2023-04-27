package com.estia.mbds.msorder.model;

import jakarta.persistence.*;

import java.util.List;

@Table(name = "commande")
@Entity
public class Order {

    @Id
    @GeneratedValue
    private long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    public Order() {
    }

    public Order(long id, List<OrderItem> orderItems) {
        this.id = id;
        this.orderItems = orderItems;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void addOrderItems(OrderItem orderItem) {
        this.orderItems.add(orderItem);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderItems=" + orderItems +
                '}';
    }
}
