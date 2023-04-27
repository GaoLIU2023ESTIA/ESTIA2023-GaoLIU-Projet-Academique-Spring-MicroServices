package com.estia.mbds.client.controllers;

import com.estia.mbds.client.beans.*;
import com.estia.mbds.client.proxies.MsCartProxy;
import com.estia.mbds.client.proxies.MsOrderProxy;
import com.estia.mbds.client.proxies.MsProductProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class ClientController {

    @Autowired
    MsProductProxy msProductProxy;

    @Autowired
    MsCartProxy msCartProxy;

    @Autowired
    MsOrderProxy msOrderProxy;

    @RequestMapping("/")
    public String index(Model model)
    {
        List<ProductBean> productBeanList = msProductProxy.list();
        model.addAttribute("products", productBeanList);
        return "index";
    }

    @GetMapping("/product-detail/{id}")
    public String showProduct(Model model, @PathVariable long id)
    {
        ProductBean productBean = msProductProxy.get(id).get();

        model.addAttribute("product", productBean);
        return "productDetail";
    }

    @RequestMapping("/addToCart/product/{id}")
    public String addProductToCart(Model model, @PathVariable long id, Integer quantity)
    {
        Optional<ProductBean> optionalProductBean = msProductProxy.get(id);
        ProductBean productBean = optionalProductBean.get();
        CartItemBean cartItemBean = new CartItemBean( productBean.getId(), productBean.getTitle(), productBean.getDescription(), productBean.getIllustration(), productBean.getPrice(), quantity);
        ResponseEntity<CartBean> cartBeanResponseEntity;
        cartBeanResponseEntity = msCartProxy.addProductToCart( 1L,cartItemBean);

        List<CartItemBean> cartItemBeanList = cartBeanResponseEntity.getBody().getItems();

        model.addAttribute("cartItemList", cartItemBeanList);
        return "cartDetail";
    }

    @RequestMapping("/order/{id}/addProduct")
    public String addCartToCmd(Model model, @PathVariable Long id, Integer quantity)
    {

        ResponseEntity<CartBean> cartBean = msCartProxy.getCart(1L);
        List<CartItemBean> cartItemBeanList = cartBean.getBody().getItems();

        for (int i = 0; i < cartItemBeanList.size(); i++) {
            CartItemBean cartItemBean = cartItemBeanList.get(i);
            OrderItemBean orderItemBean = new OrderItemBean(id,cartItemBean.getId(), cartItemBean.getTitle(), cartItemBean.getDescription(), cartItemBean.getIllustration(), cartItemBean.getPrice(), quantity);
            msOrderProxy.addProductToOrder(1L,orderItemBean);
        }

        ResponseEntity<OrderBean> orderBean = msOrderProxy.getOrder(1L);

        List<OrderItemBean> orderItemBeanList = orderBean.getBody().getOrderItems();
        model.addAttribute("orderItemList", orderItemBeanList );


        return "orderDetail";
    }

}
