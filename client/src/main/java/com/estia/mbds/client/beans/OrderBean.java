package com.estia.mbds.client.beans;

import java.util.List;

public class OrderBean {

    private long id;

    private List<OrderItemBean> orderItems;

    public OrderBean() {
    }

    public OrderBean(long id, List<OrderItemBean> orderItems) {
        this.id = id;
        this.orderItems = orderItems;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<OrderItemBean> getOrderItems() {
        return orderItems;
    }

    public void addOrderItemsBean(OrderItemBean orderItemBean) {this.orderItems.add(orderItemBean);}

}
