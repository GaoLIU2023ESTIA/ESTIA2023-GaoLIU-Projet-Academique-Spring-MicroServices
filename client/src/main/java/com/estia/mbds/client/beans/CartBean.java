package com.estia.mbds.client.beans;

import java.util.List;

public class CartBean {
    private long id;
    private List<CartItemBean> items;

    public CartBean() {
    }

    public CartBean(long id, List<CartItemBean> items) {
        this.id = id;
        this.items = items;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<CartItemBean> getItems() {
        return items;
    }

    public void addItem(CartItemBean itemBean) {
        this.items.add(itemBean);
    }
}
